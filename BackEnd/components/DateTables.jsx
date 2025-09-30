import { Table, Modal, Button } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPencil, faCirclePlus } from "@fortawesome/free-solid-svg-icons";
import { toast, ToastContainer } from "react-toastify";
import { useState, useEffect } from "react";
import styled from "styled-components";
import FormDatesUpdate from "../components/FormDatesUpdate";
import FormDatesInsert from "./FormDatesInsert";

const GradientWrapper = styled.div`
  background: linear-gradient(to right, #2980b9, #2c3e50);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
`;

const StyledTable = styled(Table)`
  background: transparent;
  border-collapse: separate;
  border-spacing: 0;
  width: 100%;

  th,
  td {
    background: transparent;
    color: #fff;
    text-align: center;
    padding: 12px 15px;
    border: none;
  }

  thead th {
    background-color: rgba(255, 255, 255, 0.2);
    text-transform: uppercase;
    font-weight: 500;
    font-size: 16px;
  }

  tbody tr:nth-child(even) {
    background-color: rgba(255, 255, 255, 0.05);
  }

  tbody tr:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }

  .action-list {
    padding: 0;
    margin: 0;
    list-style: none;
    display: flex;
    justify-content: center;
  }

  .action-list li {
    margin: 0 5px;
  }

  .action-list li a {
    color: #fff;
    font-size: 15px;
    cursor: pointer;
    transition: all 0.3s;
  }

  .action-list li a:hover {
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
  }
`;

function DateTables({ date, setDate, tableName, token }) {
  if (!date || date.length === 0) return <p></p>;

  const [showUpdate, setShowUpdate] = useState(false);
  const [showInsert, setShowInsert] = useState(false);
  const [selectedRow, setSelectedRow] = useState(null);
  const [showConfirmDelete, setShowConfirmDelete] = useState(false);

  const [options, setOptions] = useState({ country: [], degree: [] });

  // Fetch options for foreign keys
  useEffect(() => {
    const fetchOptions = async () => {
      try {
        const [countriesRes, degreesRes] = await Promise.all([
          fetch("http://localhost:3001/countries", {
            headers: { Authorization: `Bearer ${token}` },
          }),
          fetch("http://localhost:3001/degrees", {
            headers: { Authorization: `Bearer ${token}` },
          }),
        ]);

        if (!countriesRes.ok || !degreesRes.ok) throw new Error("Errore nel fetch");

        const [countries, degrees] = await Promise.all([countriesRes.json(), degreesRes.json()]);

        setOptions({ country: countries, degree: degrees });
      } catch (err) {
        console.error("Errore caricamento options:", err);
        toast.error("Errore nel caricamento delle opzioni!");
      }
    };

    fetchOptions();
  }, [token]);

  const idField = Object.keys(date[0]).find((col) => col.endsWith("_id"));
  const columns = Object.keys(date[0]).filter((col) => col !== idField);
  const formatHeader = (key) => key.replace(/_/g, " ").replace(/\b\w/g, (l) => l.toUpperCase());

  // Delete
  const handleDelete = async (id) => {
    try {
      const res = await fetch(`http://localhost:3001/${tableName}/${id}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      if (!res.ok) {
        toast.error("Errore nella fetch!");
      } else {
        setDate((prevDate) => prevDate.filter((i) => i[idField] !== id));
        toast.success("Cancellazione effettuata!");
      }
    } catch (err) {
      console.error(err);
      toast.error("Errore nel server!");
    }
  };

  // Update state after successful insert
  const handleInsertSuccess = (newRow) => {
    setDate((prev) => [...prev, newRow]);
  };

  // Update state after successful update
  const handleUpdateSuccess = (updatedRow) => {
    setDate((prev) => prev.map((row) => (row[idField] === updatedRow[idField] ? updatedRow : row)));
  };

  return (
    <GradientWrapper>
      <StyledTable hover responsive>
        <thead>
          <tr>
            {columns.map((col) => (
              <th style={{ fontWeight: "bold" }} key={col}>
                {formatHeader(col)}
              </th>
            ))}
            <th>
              <FontAwesomeIcon icon={faCirclePlus} style={{ fontSize: "22px", cursor: "pointer", color: "light" }} onClick={() => setShowInsert(true)} />
            </th>
          </tr>
        </thead>
        <tbody>
          {date.map((row, rowIndex) => (
            <tr key={row[idField] ?? rowIndex} style={{ fontStyle: "italic", fontFamily: "Arial, sans-serif" }}>
              {columns.map((col, colIndex) => {
                const value = row[col];
                return (
                  <td key={`${row[idField] ?? rowIndex}-${col}-${colIndex}`}>
                    {value && typeof value === "object" ? value.name : typeof value === "boolean" ? (value ? "Yes" : "No") : value}
                  </td>
                );
              })}
              <td>
                <ul className="action-list">
                  <li>
                    <FontAwesomeIcon
                      icon={faTrash}
                      style={{ fontSize: "20px", cursor: "pointer", color: "red" }}
                      onClick={() => {
                        setSelectedRow(row);
                        setShowConfirmDelete(true);
                      }}
                    />
                  </li>
                  <li>
                    <FontAwesomeIcon
                      icon={faPencil}
                      style={{ fontSize: "20px", cursor: "pointer", color: "light" }}
                      onClick={() => {
                        setSelectedRow(row);
                        setShowUpdate(true);
                      }}
                    />
                  </li>
                </ul>
              </td>
            </tr>
          ))}
        </tbody>
      </StyledTable>

      {/* Confirm Delete Modal */}
      <Modal show={showConfirmDelete} onHide={() => setShowConfirmDelete(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Conferma Eliminazione</Modal.Title>
        </Modal.Header>
        <Modal.Body>Sei sicuro di voler eliminare l'elemento selezionato?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowConfirmDelete(false)}>
            Annulla
          </Button>
          <Button
            variant="danger"
            onClick={() => {
              handleDelete(selectedRow[idField]);
              setShowConfirmDelete(false);
            }}
          >
            Elimina
          </Button>
        </Modal.Footer>
      </Modal>

      {/* Update Form */}
      <FormDatesUpdate
        show={showUpdate}
        handleClose={() => setShowUpdate(false)}
        row={selectedRow}
        date={date}
        setDate={setDate}
        tableName={tableName}
        token={token}
        options={options}
        onUpdateSuccess={handleUpdateSuccess}
      />

      {/* Insert Form */}
      <FormDatesInsert
        show={showInsert}
        handleClose={() => setShowInsert(false)}
        date={date}
        setDate={setDate}
        tableName={tableName}
        token={token}
        options={options}
        onInsertSuccess={handleInsertSuccess}
      />

      <ToastContainer position="top-right" autoClose={3000} />
    </GradientWrapper>
  );
}

export default DateTables;
