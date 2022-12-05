import React from "react";
import { Button, Card, Heading, Pane, TextInputField } from "evergreen-ui";
import BuildingImage from "../../assets/building.jpg";
import logo from "../../assets/logo.png";
import { useNavigate } from "react-router";
import { apiAuth } from "../../api/api";

const AuthPage = () => {
  const [login, setLogin] = React.useState("");
  const [password, setPassword] = React.useState("");

  let navigate = useNavigate();

  function handleSubmit(e) {
    e.preventDefault();
    e.stopPropagation();

    let json = JSON.stringify({
      username: login,
      password: password,
    });

    apiAuth(json)
      .then((response) => {
        sessionStorage.setItem("username", response.data["username"]);
        sessionStorage.setItem("userId", response.data["id"]);

        navigate("/clients");
      })
      .catch((error) => {
        console.error(error);

        if (error.response) {
          sessionStorage.setItem("username", "");
          sessionStorage.setItem("userId", "");
        }
      });
  }

  return (
    <Pane
      height={"100%"}
      display="flex"
      justifyContent="center"
      alignItems="center"
    >
      <Card
        height={600}
        width={1200}
        background={"white"}
        borderRadius={16}
        display="flex"
        boxShadow="4px 4px 4px rgba(0, 0, 0, 0.25)"
      >
        <Pane
          flex="1"
          backgroundImage={`url(${BuildingImage})`}
          backgroundSize="cover"
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
            <img src={logo} alt="Logo" />

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
                isInvalid={!login}
                validationMessage={!login ? "Введите логин" : false}
                value={login}
                onChange={(e) => setLogin(e.target.value)}
                marginBottom={4}
                width={412}
              />

              <TextInputField
                placeholder="Пароль"
                isInvalid={!password}
                validationMessage={!password ? "Введите пароль" : false}
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
