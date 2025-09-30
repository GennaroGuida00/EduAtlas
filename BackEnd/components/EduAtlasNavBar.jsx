import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";
import logo from "../assets/EduAtlas_logo.png";
import settings from "../assets/settings.png";
import { Dropdown } from "react-bootstrap";
import Image from "react-bootstrap/Image";
import styled from "styled-components";

const AvatarToggle = styled(Dropdown.Toggle)`
  background: transparent !important;
  border: none !important;
  padding: 0 !important;

  &::after {
    display: none !important;
  }
`;

const EduAtlasNavBar = () => {
  return (
    <Navbar expand={false} className="bg-body-tertiary mb-3">
      <Container fluid>
        <Navbar.Brand as={Link} to="/">
          <img src={logo} width="100" height="60" className="d-inline-block align-top" alt="React Bootstrap logo" />
        </Navbar.Brand>

        <Dropdown align="end" className="ms-auto">
          <AvatarToggle id="dropdown-avatar">
            <Image src={settings} style={{ width: "30px", height: "30px", borderRadius: "50%" }} alt="Avatar" />
          </AvatarToggle>

          <Dropdown.Menu>
            <Dropdown.Item as={Link} to="/login">
              Login
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </Container>
    </Navbar>
  );
};

export default EduAtlasNavBar;
