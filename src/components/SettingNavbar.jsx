import { useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import logo from "../assets/EduAtlas_logo.png";
import avatar from "../assets/img_avatar.png";
import styled from "styled-components";
import { Dropdown } from "react-bootstrap";
import Image from "react-bootstrap/Image";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEarthEurope, faGraduationCap, faScroll, faTable, faUser } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

const StyledNavLink = styled(Nav.Link)`
  background-color: ${(props) => (props.$active ? "#2476b0" : "white")};
  color: ${(props) => (props.$active ? "white !important" : "black !important")};
  padding: 10px 20px;
  border-radius: 50px;
  border: none;
  cursor: pointer;

  &:hover {
    background-color: #2476b0;
    color: white !important;
    padding: 10px 20px;
  }
`;

const AvatarToggle = styled(Dropdown.Toggle)`
  background: transparent !important;
  border: none !important;
  padding: 0 !important;

  &::after {
    display: none !important;
  }
`;

function SettingNavbar({ onSelectTable }) {
  const [activeTab, setActiveTab] = useState("");

  const handleSelect = (tableName) => {
    setActiveTab(tableName);
    onSelectTable(tableName);
    window.scrollTo({ top: 0, behavior: "auto" });
  };

  return (
    <Navbar bg="white" data-bs-theme="light" fixed="top" style={{ boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)" }}>
      <Container fluid>
        <Navbar.Brand>
          <img src={logo} width="100" height="60" className="d-inline-block align-top" alt="Logo" />
        </Navbar.Brand>

        <Nav className="d-flex justify-content-center w-100 gap-5">
          <Nav.Item>
            <StyledNavLink $active={activeTab === "countries"} onClick={() => handleSelect("countries")}>
              <FontAwesomeIcon icon={faEarthEurope} className="pe-1" />
              Countries
            </StyledNavLink>
          </Nav.Item>
          <Nav.Item>
            <StyledNavLink $active={activeTab === "credits"} onClick={() => handleSelect("credits")}>
              <FontAwesomeIcon icon={faScroll} className="pe-1" />
              Credits
            </StyledNavLink>
          </Nav.Item>
          <Nav.Item>
            <StyledNavLink $active={activeTab === "degrees"} onClick={() => handleSelect("degrees")}>
              <FontAwesomeIcon icon={faGraduationCap} className="pe-1" />
              Degrees
            </StyledNavLink>
          </Nav.Item>
          <Nav.Item>
            <StyledNavLink $active={activeTab === "gradescales"} onClick={() => handleSelect("gradescales")}>
              <FontAwesomeIcon icon={faTable} className="pe-1" />
              Grade Scales
            </StyledNavLink>
          </Nav.Item>
          <Nav.Item>
            <StyledNavLink $active={activeTab === "users"} onClick={() => handleSelect("users")}>
              <FontAwesomeIcon icon={faUser} className="pe-1" />
              Users
            </StyledNavLink>
          </Nav.Item>
        </Nav>

        <Dropdown align="end" className="ms-auto">
          <AvatarToggle id="dropdown-avatar">
            <Image src={avatar} style={{ width: "50px", height: "50px", borderRadius: "50%" }} alt="Avatar" />
          </AvatarToggle>

          <Dropdown.Menu>
            <Dropdown.Item as={Link} to="/" onClick={() => localStorage.removeItem("accessToken")}>
              Logout
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </Container>
    </Navbar>
  );
}

export default SettingNavbar;
