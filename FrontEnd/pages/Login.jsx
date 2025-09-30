import Col from "react-bootstrap/Col";
import { Form, InputGroup } from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import Image from "react-bootstrap/Image";
import "../css/Login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { toast, ToastContainer } from "react-toastify";

import { faUser, faLock } from "@fortawesome/free-solid-svg-icons";
import logo from "../assets/EduAtlas_logo.png";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    const res = await fetch("http://localhost:3001/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email, password }),
    });
    if (res.ok) {
      const loginData = await res.json();
      const { accessToken } = loginData;
      localStorage.setItem("accessToken", accessToken);
      toast.success("Login riuscito!", {
        onClose: () => navigate("/settingsAdmin", { replace: true }),
      });
    } else toast.error("Login fallito: credenziali errate o non autorizzato");
  };

  return (
    <>
      <Container fluid className="d-flex justify-content-center align-items-center vh-100 background-log">
        <Row>
          <Col>
            <Form
              onSubmit={(e) => {
                e.preventDefault();
                handleLogin();
              }}
              className="p-4 "
              style={{ minWidth: "350px" }}
            >
              <Form.Group className="mb-3" controlId="formPlaintextEmail">
                <InputGroup>
                  <InputGroup.Text>
                    <FontAwesomeIcon icon={faUser} style={{ fontSize: "20px" }} className="py-2" />
                  </InputGroup.Text>
                  <Form.Control type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                </InputGroup>
              </Form.Group>
              <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                <InputGroup>
                  <InputGroup.Text>
                    <FontAwesomeIcon icon={faLock} style={{ fontSize: "20px" }} className="py-2" />
                  </InputGroup.Text>
                  <Form.Control type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                </InputGroup>
              </Form.Group>
              <Row>
                <Col md={{ span: 4, offset: 8 }}>
                  <button type="submit" className="btn btn-primary w-100 py-2">
                    Login
                  </button>
                </Col>
              </Row>
            </Form>
          </Col>

          <Col className="justify-content-center border-start border-white">
            <Image src={logo} style={{ width: "100%", height: "180px" }} />
          </Col>
        </Row>
      </Container>
      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}

export default Login;
