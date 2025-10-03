import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";
import logo from "../assets/EduAtlas_logo.png";
import home from "../assets/home_img.jpg";
import homeVideo from "../assets/home_video.mp4";
import { Col, Dropdown, Row } from "react-bootstrap";
import styled from "styled-components";
import Footer from "../components/Footer";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faGlobe, faTable, faUser, faHandshake, faBriefcase, faCertificate } from "@fortawesome/free-solid-svg-icons";

const AvatarToggle = styled(Dropdown.Toggle)`
  background: transparent !important;
  border: none !important;
  padding: 0 !important;

  &::after {
    display: none !important;
  }
`;
function HomePage() {
  return (
    <>
      <Navbar expand={false} className="bg-body-tertiary">
        <Container fluid>
          <Navbar.Brand as={Link} to="/">
            <img src={logo} width="100" height="60" className="d-inline-block align-top" alt="React Bootstrap logo" />
          </Navbar.Brand>

          <Dropdown align="end" className="ms-auto">
            <AvatarToggle id="dropdown-avatar">
              <FontAwesomeIcon icon={faBars} style={{ color: "black" }} />
            </AvatarToggle>

            <Dropdown.Menu>
              <Dropdown.Item as={Link} to="/dashboard">
                <FontAwesomeIcon icon={faTable} style={{ color: "black" }} className="pe-1" />
                Credit Table
              </Dropdown.Item>
              <Dropdown.Item as={Link} to="/login">
                <FontAwesomeIcon icon={faUser} style={{ color: "black" }} className="pe-1" />
                Login
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </Container>
      </Navbar>

      <Container fluid className="p-0 m-0">
        <video src={homeVideo} style={{ width: "100%", height: "600px", objectFit: "cover", objectPosition: "center", display: "block" }} autoPlay loop muted />
        <Col className="text-center fw-semibold" style={{ color: "#2476B0" }}>
          <h1 className="mt-3 ">Why EduAtlas?</h1>
          <p className="mt-3 fw-semibold">In an increasingly interconnected world, having a unified education system is essential. EDUATLAS facilitates:</p>
        </Col>
        <div
          style={{
            width: "100%",
            minHeight: "500px",
            backgroundImage: `linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url(${home})`,
            backgroundSize: "cover",
            backgroundPosition: "center",
            color: "white",
          }}
        >
          <Container>
            <Row>
              <Col md={6}>
                <div className="mt-5 text-left bg-dark bg-opacity-25 text-white p-4">
                  <div className="d-flex align-items-center">
                    <h1>Transparency of academic qualifications</h1>
                    <FontAwesomeIcon icon={faCertificate} className="text-success" style={{ fontSize: "30px" }} />
                  </div>

                  <p className="mt-3">
                    The platform allows you to easily verify the authenticity and value of qualifications, ensuring clarity and trust for students and
                    institutions.
                  </p>
                </div>
                <div className="mt-5 text-left bg-dark bg-opacity-25 text-white p-4">
                  <div className="d-flex align-items-center">
                    <h1>Collaboration between universities and educational institutions</h1>
                    <FontAwesomeIcon icon={faHandshake} className="text-warning" style={{ fontSize: "30px" }} />
                  </div>
                  <p className="mt-3">
                    EDUATLAS fosters connections between schools, universities, and educational institutions, simplifying the exchange of information and the
                    creation of shared projects.
                  </p>
                </div>
              </Col>
              <Col md={6}>
                <div className="mt-5 text-right bg-dark bg-opacity-25 text-white p-4">
                  <div className="d-flex align-items-center">
                    <h1>International student mobility to programs</h1>
                    <FontAwesomeIcon icon={faGlobe} className="text-primary" style={{ fontSize: "30px" }} />
                  </div>

                  <p className="mt-3">
                    EDUATLAS facilitates student movement between countries, making it easier for students to access international educational programs and
                    universities.
                  </p>
                </div>
                <div className="mt-5 text-right bg-dark bg-opacity-25 text-white p-4">
                  <div className="d-flex align-items-center">
                    <h1>Creating global job opportunities</h1>
                    <FontAwesomeIcon icon={faBriefcase} className="text-danger" style={{ fontSize: "30px" }} />
                  </div>
                  <p className="mt-3">
                    With internationally recognized qualifications, students can more easily access job and career opportunities around the world.
                  </p>
                </div>
              </Col>
            </Row>
          </Container>
        </div>
        <Footer />
      </Container>
    </>
  );
}
export default HomePage;
