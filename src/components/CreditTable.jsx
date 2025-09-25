import { Table } from "react-bootstrap";
import styled from "styled-components";

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

function CreditTable({ credit }) {
  return (
    <GradientWrapper>
      <StyledTable>
        <thead>
          <tr>
            <th>1th credits year</th>
            <th>2th credits year</th>
            <th>3th credits year</th>
            <th>4th is additional</th>
            <th>4th credits year</th>
            <th>5th is additional</th>
            <th>5th credits year</th>
            <th>Degree</th>
          </tr>
        </thead>
        <tbody>
          {credit.map((c) => (
            <tr key={`${c.country_id}-${c.credit_id}`}>
              <td>{c.year_1_credits}</td>
              <td>{c.year_2_credits}</td>
              <td>{c.year_3_credits}</td>
              <td>{c.year_4_is_additional ? "Yes" : "No"}</td>
              <td>{c.year_4_credits}</td>
              <td>{c.year_5_is_additional ? "Yes" : "No"}</td>
              <td>{c.year_5_credits}</td>
              <td>{c.degree.name}</td>
            </tr>
          ))}
        </tbody>
      </StyledTable>
    </GradientWrapper>
  );
}

export default CreditTable;
