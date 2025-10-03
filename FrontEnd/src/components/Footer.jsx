import { Col, Container, Row } from "react-bootstrap";
import logo from "../assets/EduAtlas_logo.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faLinkedin, faWhatsapp, faYoutube } from "@fortawesome/free-brands-svg-icons";

function Footer() {
  return (
    <Container fluid className="py-3" style={{ color: "#2476B0" }}>
      <Row className="justify-content-center ">
        <Col md={3} className="text-center border-bottom pb-4">
          <img src={logo} width="100" height="60" className="d-inline-block align-top" alt="React Bootstrap logo" />
        </Col>
        <Col md={3} className="text-center border-bottom pb-4">
          <h6>CONTACT</h6>
          <div className="d-flex justify-content-center gap-2 mt-3">
            <FontAwesomeIcon icon={faFacebook} style={{ fontSize: "20px" }} />
            <FontAwesomeIcon icon={faLinkedin} style={{ fontSize: "20px" }} />
            <FontAwesomeIcon icon={faYoutube} style={{ fontSize: "20px" }} />
            <FontAwesomeIcon icon={faWhatsapp} style={{ fontSize: "20px" }} />
          </div>
        </Col>
        <Col md={3} className="text-center border-bottom pb-4">
          <ul className="list-unstyled">
            <li>
              <a href="#" className="link-opacity-100 text-decoration-none fw-semibold">
                About Us
              </a>
            </li>
            <li>
              <a href="#" className="link-opacity-100 text-decoration-none fw-semibold">
                Affiliates
              </a>
            </li>
            <li>
              <a href="#" className="link-opacity-100 text-decoration-none fw-semibold">
                Resources
              </a>
            </li>
          </ul>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md={8} className="text-center mt-2 ">
          <p>Copyright © 1993-2025 EduAtlas - All rights reserved | Privacy Statement | Website Disclaimer | Accessibility Statement</p>
        </Col>
      </Row>
    </Container>
  );
}

export default Footer;
