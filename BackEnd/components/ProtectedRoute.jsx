import { Navigate } from "react-router-dom";

function ProtectedRoute({ children }) {
  const token = localStorage.getItem("accessToken");
  if (!token) {
    localStorage.removeItem("accessToken");
    return <Navigate to={"/login"} replace />;
  }
  return children;
}
export default ProtectedRoute;
