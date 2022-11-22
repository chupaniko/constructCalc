import Header from "../components/Header";
import {
  Button,
  Dialog,
  Heading,
  Pane,
  Paragraph,
  Spinner,
  TextInputField,
} from "evergreen-ui";
import { useEffect, useState } from "react";
import { apiCreateClient, apiGetClients } from "../../api/api";

const ClientsPage = () => {
  const [clients, setClients] = useState(undefined);
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

  const onConfirm = (close) => {
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

      <Pane className="container">
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

        {clients === undefined && (
          <Pane display="flex" alignItems="center" justifyContent="center">
            <Spinner />
          </Pane>
        )}

        {clients?.length === 0 && (
          <Pane textAlign="center">
            <Paragraph size={500}>Клиенты не найдены</Paragraph>
          </Pane>
        )}

        {clients?.length > 0 && (
          <Paragraph size={500}>{JSON.stringify(clients)}</Paragraph>
        )}

        <Dialog
          isShown={isCreateDialogShown}
          title="Создать клиента"
          onCloseComplete={() => setIsCreateDialogShown(false)}
          confirmLabel="Создать"
          hasCancel={false}
          onConfirm={onConfirm}
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
