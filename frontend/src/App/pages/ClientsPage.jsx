import Header from "../components/Header";
import {
  Button,
  Card,
  Dialog,
  Heading,
  Pane,
  Paragraph,
  Spinner,
  Text,
  TextInputField,
} from "evergreen-ui";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { apiCreateClient, apiGetClients } from "../../api/api";

const ClientsPage = () => {
  const [clients, setClients] = useState(null);
  const [isCreateDialogShown, setIsCreateDialogShown] = useState(false);

  const [lastName, setLastName] = useState("");
  const [firstName, setFirstName] = useState("");
  const [secondName, setSecondName] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");

  const getClients = () => {
    apiGetClients()
      .then(({ data }) => setClients(data))
      .catch((err) => console.error(err));
  };

  const createClient = (data, callback) => {
    apiCreateClient({
      firstName,
      lastName,
      secondName,
      phone,
      email,
      address,
    })
      .then(() => {
        getClients();
        callback();
      })
      .catch((err) => console.error(err));
  };

  const onCreateClientConfirm = (close) => {
    createClient(
      {
        firstName,
        lastName,
        secondName,
        phone,
        email,
        address,
      },
      close
    );
  };

  useEffect(() => {
    getClients();
  }, []);

  return (
    <>
      <Header />

      <Pane
        className="container"
        display="flex"
        flex={1}
        flexDirection="column"
      >
        <Pane display="flex" justifyContent="space-between" marginBottom={60}>
          <Heading size={900}>Клиенты</Heading>

          <Button
            appearance="primary"
            height={40}
            onClick={() => setIsCreateDialogShown(true)}
          >
            Создать клиента
          </Button>
        </Pane>

        {clients === null && (
          <Pane
            display="flex"
            flex={1}
            alignItems="center"
            justifyContent="center"
          >
            <Spinner />
          </Pane>
        )}

        {clients?.length === 0 && (
          <Pane textAlign="center">
            <Paragraph size={500}>Клиенты не найдены</Paragraph>
          </Pane>
        )}

        {clients?.length > 0 && (
          <Pane
            display="grid"
            gridTemplateColumns="repeat(4, 1fr)"
            gridGap={20}
          >
            {clients.map((c) => (
              <Link
                to={`/clients/${c.id}`}
                key={c.id}
                className="client-card-link"
              >
                <Card
                  className="client-card"
                  backgroundColor="white"
                  height={80}
                  display="flex"
                  flexDirection="column"
                  justifyContent="center"
                  alignItems="center"
                  elevation={1}
                  hoverElevation={2}
                  cursor="pointer"
                >
                  <Text>{`${c.lastName} ${c.firstName}`}</Text>
                  <Text>{`${c.secondName}`}</Text>
                </Card>
              </Link>
            ))}
          </Pane>
        )}

        <Dialog
          isShown={isCreateDialogShown}
          title="Создать клиента"
          onCloseComplete={() => setIsCreateDialogShown(false)}
          confirmLabel="Создать"
          hasCancel={false}
          onConfirm={onCreateClientConfirm}
        >
          <TextInputField
            label="Фамилия"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />

          <TextInputField
            label="Имя"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />

          <TextInputField
            label="Отчество"
            value={secondName}
            onChange={(e) => setSecondName(e.target.value)}
          />

          <TextInputField
            label="Телефон"
            type="tel"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
          />

          <TextInputField
            label="Адрес электронной почты"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <TextInputField
            label="Адрес"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
        </Dialog>
      </Pane>
    </>
  );
};

export default ClientsPage;
