import Header from "../components/Header";
import {
  Button,
  Card,
  Combobox,
  Heading,
  Label,
  Pane,
  Paragraph,
  Popover,
  TextInputField,
} from "evergreen-ui";
import { apiGetClient } from "../../api/api";
import { useState } from "react";

const FoundationPage = () => {
  const [clientInfo, setClientInfo] = useState(undefined);

  const queryParams = new URLSearchParams(window.location.search);
  const clientId = queryParams.get("clientId");

  const getClient = (id) => {
    apiGetClient(id)
      .then(({ data }) => setClientInfo(data))
      .catch((err) => console.error(err));
  };

  const onClientInfoClick = () => {
    getClient(clientId);
  };

  return (
    <>
      <Header />

      <Pane className="container">
        <Pane
          display="flex"
          justifyContent="space-between"
          alignItems="baseline"
        >
          <Pane>
            <Heading is="h1" size={900}>
              Фундамент
            </Heading>

            <Popover
              content={
                <Pane padding={20}>
                  <Paragraph size={500} fontWeight="bold" marginBottom={16}>
                    {`${clientInfo.client.lastName} ${clientInfo.client.firstName} ${clientInfo.client.secondName}`}
                  </Paragraph>

                  <Paragraph>{clientInfo.client.address}</Paragraph>
                  <Paragraph>{clientInfo.client.phone}</Paragraph>
                  <Paragraph>{clientInfo.client.email}</Paragraph>
                </Pane>
              }
            >
              <Button marginTop={12} onClick={onClientInfoClick}>
                Информация о клиенте
              </Button>
            </Popover>
          </Pane>

          <TextInputField label="Адрес объекта" width={600} marginBottom={0} />
        </Pane>

        <Card
          marginTop={32}
          width="100%"
          background="white"
          paddingX={32}
          paddingY={32}
          elevation={1}
        >
          <Pane width={400}>
            <Heading size={700} marginBottom={24}>
              Исходные данные
            </Heading>

            <TextInputField
              className="input-horizontal"
              display="flex"
              alignItems="center"
              justifyContent="space-between"
              marginBottom={12}
              label="Периметр внешних стен"
            />

            <TextInputField
              className="input-horizontal"
              display="flex"
              alignItems="center"
              justifyContent="space-between"
              marginBottom={12}
              label="Длина внутренних стен"
            />

            <Pane
              display="flex"
              justifyContent="space-between"
              alignItems="center"
              marginBottom={12}
            >
              <Label>Бетонные сваи</Label>

              <Pane>
                <Combobox
                  openOnFocus
                  items={["Banana", "Orange", "Apple", "Mango"]}
                  onChange={(selected) => console.log(selected)}
                />
              </Pane>
            </Pane>

            <Pane
              display="flex"
              justifyContent="space-between"
              alignItems="center"
            >
              <Label>Бетон</Label>

              <Pane>
                <Combobox
                  openOnFocus
                  items={["Banana", "Orange", "Apple", "Mango"]}
                  onChange={(selected) => console.log(selected)}
                />
              </Pane>
            </Pane>
          </Pane>
        </Card>

        <Pane textAlign="right" marginTop={40}>
          <Button intent="danger" marginRight={20}>
            Очистить расчёт
          </Button>
          <Button appearance="primary">Рассчитать</Button>
        </Pane>
      </Pane>
    </>
  );
};

export default FoundationPage;
