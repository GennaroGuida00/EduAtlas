import { Form, Modal, Button } from "react-bootstrap";
import { toast } from "react-toastify";
import { useState, useEffect, useRef } from "react";

function FormDatesUpdate({ show, handleClose, row, date, setDate, tableName, token, foreignKeys = ["country", "degree"], options = {} }) {
  const [formValues, setFormValues] = useState({});
  const [originalValues, setOriginalValues] = useState({});
  const [formErrors, setFormErrors] = useState({});
  const formRef = useRef(null);

  const allColumns = row ? Object.keys(row) : [];
  const columns = allColumns.filter((col) => !col.endsWith("_id"));

  const fieldTypes = Object.fromEntries(
    allColumns.map((key) => {
      if (key.endsWith("_id")) return [key, "skip"];
      if (foreignKeys.includes(key)) return [key, "foreign"];

      const val = row[key];

      if (val === true || val === false) return [key, "boolean"];

      if (typeof val === "number" || (!isNaN(val) && val !== "")) return [key, "number"];

      return [key, "string"];
    })
  );

  useEffect(() => {
    if (show && row) {
      const initialValues = {};
      const original = {};

      columns.forEach((col) => {
        let value;
        if (foreignKeys.includes(col)) {
          if (row[`${col}_id`] !== undefined) value = String(row[`${col}_id`]);
          else if (row[col]?.[`${col}_id`] !== undefined) value = String(row[col][`${col}_id`]);
          else value = "";
        } else if (fieldTypes[col] === "boolean") {
          value = row[col] === true ? "true" : row[col] === false ? "false" : "";
        } else {
          value = row[col] ?? "";
        }

        initialValues[col] = value;
        original[col] = value;
      });

      setFormValues(initialValues);
      setOriginalValues(original);
      setFormErrors({});
    }
  }, [show, row]);

  const formatHeader = (key) => key.replace(/_/g, " ").replace(/\b\w/g, (l) => l.toUpperCase());

  const handleInputChange = (col, value) => {
    setFormValues((prev) => ({ ...prev, [col]: value }));

    let errorMessage = "";
    const type = fieldTypes[col];

    if (value === "") {
      errorMessage = "";
    } else if (col.toLowerCase() === "email") {
      if (!value.includes("@")) errorMessage = "Deve contenere una @";
    } else if (col.toLowerCase() === "password") {
      errorMessage = "";
    } else if (type === "number") {
      if (isNaN(Number(value))) errorMessage = "Deve essere un numero";
    } else if (type === "boolean") {
      if (value !== true && value !== false) errorMessage = "Devi selezionare Yes o No";
    } else if (type === "string") {
      if (!/^[a-zA-Z\s]*$/.test(value)) errorMessage = "Deve contenere solo lettere";
    } else if (type === "foreign") {
      if (!value) errorMessage = `Devi selezionare un ${formatHeader(col)}`;
    }

    setFormErrors((prev) => {
      const copy = { ...prev };
      if (errorMessage) copy[col] = errorMessage;
      else delete copy[col];
      return copy;
    });
  };

  const handleSaveChanges = async () => {
    if (Object.keys(formErrors).length > 0) {
      toast.error("Ci sono errori nei campi. Correggili prima di salvare.");
      return;
    }

    let changed = false;
    for (let key of columns) {
      let current = formValues[key] ?? null;
      let original = originalValues[key] ?? null;

      if (fieldTypes[key] === "number") {
        current = current !== null ? Number(current) : null;
        original = original !== null ? Number(original) : null;
      } else if (fieldTypes[key] === "boolean") {
        current = current === "true" ? true : current === "false" ? false : null;
        original = original === "true" ? true : original === "false" ? false : null;
      } else if (fieldTypes[key] === "foreign") {
        current = current !== null ? Number(current) : null;
        original = original !== null ? Number(original) : null;
      }

      if (current !== original) {
        changed = true;
        break;
      }
    }

    if (!changed) {
      toast.info("Nessuna modifica rilevata.");
      return;
    }

    const dataToUpdate = Object.fromEntries(
      Object.entries(formValues).map(([key, value]) => {
        if (foreignKeys.includes(key)) return [`${key}_id`, Number(value)];
        if (fieldTypes[key] === "boolean") return [key, value === "true"];
        if (fieldTypes[key] === "number") return [key, Number(value)];
        return [key, value];
      })
    );

    try {
      const idField = Object.keys(row).find((col) => col.endsWith("_id"));
      const res = await fetch(`http://localhost:3001/${tableName}/${row[idField]}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(dataToUpdate),
      });

      if (!res.ok) {
        toast.error("Errore nell'aggiornamento!");
        return;
      }

      const updatedRow = await res.json();
      setDate((prev) => prev.map((r) => (r[idField] === updatedRow[idField] ? updatedRow : r)));

      toast.success("Aggiornamento effettuato!");
      handleClose();
    } catch (err) {
      console.error(err);
      toast.error("Errore server!");
    }
  };

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Update </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form ref={formRef}>
          {columns.map((col) => {
            const type = fieldTypes[col];
            if (type === "skip") return null;

            const value = formValues[col] ?? "";
            const error = formErrors[col];

            return (
              <Form.Group key={col} className="mb-3" controlId={col}>
                <Form.Label>{formatHeader(col)}</Form.Label>

                {type === "boolean" ? (
                  <Form.Select value={value} onChange={(e) => handleInputChange(col, e.target.value)}>
                    <option value="">SELEZIONA</option>
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                  </Form.Select>
                ) : type === "foreign" ? (
                  <Form.Select value={value} onChange={(e) => handleInputChange(col, e.target.value)}>
                    <option value="">Seleziona {formatHeader(col)}</option>
                    {options[col]?.map((opt) => (
                      <option key={opt[`${col}_id`]} value={String(opt[`${col}_id`])}>
                        {opt.name}
                      </option>
                    ))}
                  </Form.Select>
                ) : (
                  <Form.Control type="text" value={value} onChange={(e) => handleInputChange(col, e.target.value)} />
                )}

                {error && <Form.Text className="text-danger">{error}</Form.Text>}
              </Form.Group>
            );
          })}
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Chiudi
        </Button>
        <Button variant="success" onClick={handleSaveChanges}>
          Salva
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default FormDatesUpdate;
