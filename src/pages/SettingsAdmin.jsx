import { useState } from "react";
import SettingNavbar from "../components/SettingNavbar";
import DateTables from "../components/DateTables";
import { Container } from "react-bootstrap";
import "../css/Login.css";

function SettingsAdmin() {
  const [date, setDate] = useState([]);
  const [tableName, setTableName] = useState("");
  const token = localStorage.getItem("accessToken");

  const fetchTableData = async (tableName) => {
    try {
      setTableName(tableName);
      const res = await fetch(`http://localhost:3001/${tableName}`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      if (!res.ok) throw new Error("Errore fetch");
      const data = await res.json();
      setDate(data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <>
      <SettingNavbar onSelectTable={fetchTableData} />
      <Container style={{ marginTop: "150px" }}>
        <DateTables date={date} setDate={setDate} tableName={tableName} token={token} />
      </Container>
    </>
  );
}

export default SettingsAdmin;
