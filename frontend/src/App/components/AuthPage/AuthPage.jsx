import React from "react";
import {Card, Pane} from "evergreen-ui";
import "./AuthPage.scss";

function AuthPage() {
    return (
        <Pane height="100vh" display="flex" justifyContent="center" alignItems="center" background={"#F0F4F8"}>
            <Card className="card" height={746} width={1350} background={"white"} borderRadius={16}></Card>
        </Pane>
    );
}

export default AuthPage;
