import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import ProtectedRoute from "./components/ProtectedRoute";
import SettingsAdmin from "./pages/SettingsAdmin";
import HomePage from "./pages/HomePage";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/login" element={<Login />} />
        <Route
          path="/settingsAdmin"
          element={
            <ProtectedRoute>
              <SettingsAdmin />
            </ProtectedRoute>
          }
        />
      </Routes>
    </>
  );
}

export default App;
