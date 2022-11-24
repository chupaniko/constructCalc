import React from "react";
import "./App.scss";
import { Navigate, Route, Routes } from "react-router";
import AuthPage from "./pages/AuthPage";
import ClientsPage from "./pages/ClientsPage";
import ClientPage from "./pages/ClientPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate replace to="/login" />} />
      <Route path="/login" element={<AuthPage />} />
      <Route path="/clients" element={<ClientsPage />} />
      <Route path="/clients/:id" element={<ClientPage />} />
    </Routes>
  );
}

export default App;
