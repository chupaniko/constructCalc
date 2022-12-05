import React from "react";
import {Button, Card, Heading, Pane, TextInputField} from "evergreen-ui";
import BuildingImage from "../../assets/building.jpg";
import logo from "../../assets/logo.png";
import axios from "axios";
import {useNavigate} from "react-router";

const AuthPage = () => {
    const [login, setLogin] = React.useState("");
    const [password, setPassword] = React.useState("");

    const headers = {
        'Content-Type': 'application/json;charset=utf-8',
    };

    let navigate = useNavigate();

    function handleSubmit(e) {
        e.preventDefault();
        e.stopPropagation();
        let json = JSON.stringify({
            username: login,
            password: password
        });
        axios.post('http://localhost:6579/api/authentication',
            json,
            {headers}).then(response => {
            sessionStorage.setItem("username", response.data["username"]);
            sessionStorage.setItem("userId", response.data["id"]);
            console.log(sessionStorage.getItem("username"));
            console.log(sessionStorage.getItem("userId"));
        });
        if (sessionStorage.getItem("login") !== "" && sessionStorage.getItem("login") !== null) {
            navigate("/clients", {replace: true});
        }
    }

    return (
        <Pane
            height={"100%"}
            display="flex"
            justifyContent="center"
            alignItems="center"
        >
            <Card
                height={746}
                width={1350}
                background={"white"}
                borderRadius={16}
                display="flex"
                boxShadow="4px 4px 4px rgba(0, 0, 0, 0.25)"
            >
                <Pane
                    flex="1"
                    backgroundImage={`url(${BuildingImage})`}
                    borderBottomLeftRadius={16}
                    borderTopLeftRadius={16}
                />

                <Pane
                    display="flex"
                    flex="1"
                    flexDirection="column"
                    gap={32}
                    justifyContent="center"
                >
                    <Pane textAlign="center">
                        <img src={logo} alt="Logo"/>

                        <Heading
                            is="h1"
                            className="title"
                            fontSize={44}
                            fontWeight={700}
                            lineHeight="52px"
                        >
                            Название
                        </Heading>
                    </Pane>

                    <form className="login-form" onSubmit={handleSubmit}>
                        <Pane display="flex" flexDirection="column" alignItems="center">
                            <TextInputField
                                placeholder="Логин"
                                validationMessage="Введите логин"
                                value={login}
                                onChange={(e) => setLogin(e.target.value)}
                                marginBottom={4}
                                width={412}
                            />

                            <TextInputField
                                placeholder="Пароль"
                                validationMessage="Введите пароль"
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                marginTop={0}
                                width={412}
                            />

                            <Button
                                appearance="primary"
                                type="submit"
                                height={40}
                                width={160}
                                disabled={!login || !password}
                            >
                                Войти
                            </Button>
                        </Pane>
                    </form>
                </Pane>
            </Card>
        </Pane>
    );
};

export default AuthPage;
