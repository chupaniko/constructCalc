import React from "react";
import "./App.scss";
import { Navigate, RouterProvider } from "react-router";
import AuthPage from "./pages/AuthPage";
import { createBrowserRouter } from "react-router-dom";
import ClientsPage from "./pages/ClientsPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigate replace to="/login" />,
  },
  {
    path: "/login",
    element: <AuthPage />,
  },
  {
    path: "/clients",
    element: <ClientsPage />,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
