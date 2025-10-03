import { Form } from "react-bootstrap";
import styled from "styled-components";

const BlueSelect = styled(Form.Select)`
  background-color: #2476b0;
  color: #fffdfdff;
  font-family: "Arial", sans-serif;
  font-weight: bold;
  font-style: italic;
  border: 1px solid #7fbfff;
  border-radius: 5px;
  padding: 6px 12px;
  transition: all 0.3s;
`;

function SelectCountry({ country, selectedCountryToExclude, setSelectedCountry }) {
  const handleCountryChange = (e) => {
    setSelectedCountry(Number(e.target.value));
  };

  return (
    <BlueSelect aria-label="seleziona un paese" onChange={handleCountryChange}>
      <option value="">Seleziona un paese</option>
      {country
        .filter((c) => c.country_id !== selectedCountryToExclude)
        .map((c) => (
          <option key={c.country_id} value={c.country_id}>
            {c.name}
          </option>
        ))}
    </BlueSelect>
  );
}

export default SelectCountry;
