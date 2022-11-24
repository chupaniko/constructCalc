import React from "react";
import "./App.scss";
import { Navigate, Route, Routes } from "react-router";
import AuthPage from "./pages/AuthPage";
import ClientsPage from "./pages/ClientsPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate replace to="/login" />} />
      <Route path="/login" element={<AuthPage />} />
      <Route path="/clients" element={<ClientsPage />} />
    </Routes>
  );
}

export default App;
