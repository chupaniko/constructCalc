import React from "react";
import "./App.scss";
import {RouterProvider} from "react-router";
import AuthPage from "./components/AuthPage/AuthPage";
import {createBrowserRouter} from "react-router-dom";

const router = createBrowserRouter([
    {
        path: "/",
        element: <div className="App">
            <header className="App-header">
                <p>
                    Edit <code>src/App.js</code> and save to reload.
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
            </header>
        </div>,
    },
    {
        path: "/login",
        element: <AuthPage/>,
    },
]);

function App() {
    return (
        <RouterProvider router={router}/>
    );
}

export default App;
