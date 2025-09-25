import { Form, Modal, Button } from "react-bootstrap";
import { toast } from "react-toastify";
import { useState, useEffect, useRef } from "react";

function FormDatesInsert({ show, handleClose, date, setDate, tableName, token, foreignKeys = ["country", "degree"], options = {} }) {
  const [formValues, setFormValues] = useState({});
  const [formErrors, setFormErrors] = useState({});
  const [showConfirm, setShowConfirm] = useState(false);
  const formRef = useRef(null);

  const allColumns = date && date.length > 0 ? Object.keys(date[0]) : [];
  const columns = allColumns.filter((col) => !col.endsWith("_id"));

  const fieldTypes =
    date && date.length > 0
      ? Object.fromEntries(
          Object.entries(date[0]).map(([key, value]) => {
            if (key.endsWith("_id")) return [key, "skip"];
            if (foreignKeys.includes(key)) return [key, "foreign"];

            // boolean prima dei number
            if (value === true || value === false) return [key, "boolean"];

            // numero (come prima)
            if (typeof value === "number" || (!isNaN(value) && value !== "")) return [key, "number"];

            // stringa
            return [key, "string"];
          })
        )
      : {};

  useEffect(() => {
    if (show) {
      const initialValues = Object.fromEntries(columns.map((col) => [col, fieldTypes[col] === "boolean" ? "" : fieldTypes[col] === "foreign" ? "" : ""]));
      setFormValues(initialValues);
      setFormErrors({});
    }
  }, [show]);

  const formatHeader = (key) => key.replace(/_/g, " ").replace(/\b\w/g, (l) => l.toUpperCase());

  const handleInputChange = (col, value) => {
    setFormValues((prev) => ({ ...prev, [col]: value }));

    let errorMessage = "";
    const type = fieldTypes[col];

    if (value === "") {
      // campo vuoto → nessun errore
      errorMessage = "";
    } else if (col.toLowerCase() === "email") {
      // validazione email: deve contenere almeno una @
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
      toast.error("Ci sono errori nei campi o valori mancanti. Correggili prima di salvare.");
      return;
    }

    try {
      const dataToInsert = Object.fromEntries(
        Object.entries(formValues).map(([key, value]) => {
          if (foreignKeys.includes(key)) {
            const idField = `${key}_id`;
            return [idField, Number(value)];
          }
          return [key, value];
        })
      );

      const res = await fetch(`http://localhost:3001/${tableName}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(dataToInsert),
      });

      if (!res.ok) {
        toast.error("Errore! Controlla che i campi inseriti siano validi o esistenti.");
        return;
      }

      const newRow = await res.json();
      setDate((prevDate) => [...prevDate, newRow]);
      toast.success("Inserimento effettuato!");
      setShowConfirm(false);
      handleClose();
    } catch (err) {
      console.error(err);
      toast.error("Errore nel server!");
    }
  };

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Inserisci</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form ref={formRef}>
            {columns.map((col) => {
              const type = fieldTypes[col];
              if (type === "skip") return null;

              const value = formValues[col] ?? "";
              const error = formErrors[col];

              return (
                <Form.Group className="mb-3" controlId={`form-${col}`} key={col}>
                  <Form.Label>{formatHeader(col)}</Form.Label>

                  {type === "boolean" ? (
                    <Form.Select
                      value={value === "" ? "" : value ? "true" : "false"}
                      onChange={(e) => handleInputChange(col, e.target.value === "" ? "" : e.target.value === "true")}
                    >
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
          <Button
            variant="primary"
            onClick={() => {
              if (Object.keys(formErrors).length > 0) {
                toast.error("Ci sono errori nei campi. Correggili prima di confermare.");
              } else {
                setShowConfirm(true);
                handleClose();
              }
            }}
          >
            Salva
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showConfirm} onHide={() => setShowConfirm(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Conferma Inserimento</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>Sei sicuro di voler inserire i seguenti dati?</p>
          <ul>
            {Object.entries(formValues).map(([key, value]) => (
              <li key={key}>
                <strong>{formatHeader(key)}:</strong>{" "}
                {foreignKeys.includes(key) ? options[key]?.find((o) => o[`${key}_id`] === Number(value))?.name ?? "N/A" : value?.toString()}
              </li>
            ))}
          </ul>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowConfirm(false)}>
            Annulla
          </Button>
          <Button variant="success" onClick={handleSaveChanges}>
            Conferma
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default FormDatesInsert;
