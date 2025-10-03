import { Col, Container, Row } from "react-bootstrap";
import { useEffect, useState } from "react";

import EduAtlasNavBar from "../components/EduAtlasNavBar";
import SelectCountry from "../components/SelectCountry";
import CreditTable from "../components/CreditTable";

function Dashboard() {
  const [countries, setCountries] = useState([]);
  const [selectedCountry1, setSelectedCountry1] = useState(null);
  const [selectedCountry2, setSelectedCountry2] = useState(null);
  const [credits, setCredits] = useState({ first: [], second: [] });
  const token = localStorage.getItem("accessToken");

  useEffect(() => {
    fetch("http://localhost:3001/countries", {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => setCountries(data))
      .catch((err) => console.error("Errore nel fetch paesi:", err));
  }, [token]);

  const fetchCredits = async (countryId, key) => {
    if (!countryId) return;
    try {
      const res = await fetch(`http://localhost:3001/credits/country/${countryId}`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      if (!res.ok) throw new Error("Errore nella risposta del server");
      const data = await res.json();
      setCredits((prev) => ({ ...prev, [key]: data }));
    } catch (err) {
      console.error("Errore nel fetch credits:", err);
    }
  };

  useEffect(() => {
    fetchCredits(selectedCountry1, "first");
  }, [selectedCountry1]);

  useEffect(() => {
    fetchCredits(selectedCountry2, "second");
  }, [selectedCountry2]);

  return (
    <>
      <EduAtlasNavBar />
      <Container className="mt-5">
        <Row>
          <Col md={4}>
            <SelectCountry country={countries} selectedCountryToExclude={selectedCountry2} setSelectedCountry={setSelectedCountry1} />
          </Col>
          <Col md={4}></Col>
          <Col md={4}>
            <SelectCountry country={countries} selectedCountryToExclude={selectedCountry1} setSelectedCountry={setSelectedCountry2} />
          </Col>
        </Row>

        <Row className="mt-5">
          <CreditTable credit={[...credits.first, ...credits.second]} />
        </Row>
      </Container>
    </>
  );
}
export default Dashboard;
