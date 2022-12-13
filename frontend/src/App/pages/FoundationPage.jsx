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
  Spinner,
  TextInputField,
} from "evergreen-ui";
import {
  apiGetBetonPilesValues,
  apiGetClient,
  apiGetConcreteValues,
  apiGetFoundationCalculation,
} from "../../api/api";
import { useEffect, useState } from "react";

const FoundationPage = () => {
  const [clientInfo, setClientInfo] = useState(undefined);

  const [address, setAddress] = useState("");
  const [externalWallsPerimeter, setExternalWallsPerimeter] = useState("");
  const [internalWallsLength, setInternalWallsLength] = useState("");
  const [concrete, setConcrete] = useState("");
  const [concretePiles, setConcretePiles] = useState("");

  const [concreteValues, setConcreteValues] = useState([]);
  const [concretePilesValues, setConcretePilesValues] = useState([]);

  const queryParams = new URLSearchParams(window.location.search);
  const clientId = queryParams.get("clientId");

  useEffect(() => {
    apiGetConcreteValues().then(({ data }) => {
      setConcreteValues(
        data.map((d) => ({
          name: d.name,
          id: d.id,
        }))
      );
    });

    apiGetBetonPilesValues().then(({ data }) => {
      setConcretePilesValues(
        data.map((d) => ({
          name: `${d.width} x ${d.height} x ${d.length}`,
          id: d.id,
        }))
      );
    });
  }, []);

  useEffect(() => {
    apiGetClient(clientId).then(({ data }) => setClientInfo(data));
  }, [clientId]);

  const getClient = (id) => {
    apiGetClient(id)
      .then(({ data }) => setClientInfo(data))
      .catch((err) => console.error(err));
  };

  const isFieldsFilled = () => {
    return (
      address &&
      externalWallsPerimeter &&
      internalWallsLength &&
      concrete &&
      concretePiles
    );
  };

  const onClientInfoClick = () => {
    getClient(clientId);
  };

  const onClean = () => {
    setAddress("");
    setExternalWallsPerimeter("");
    setInternalWallsLength("");
    setConcretePiles("");
    setConcrete("");
  };

  const onCalculate = () => {
    const json = JSON.stringify({
      externalWallsPerimeter: externalWallsPerimeter,
      internalWallLength: internalWallsLength,
      concretePiles: {
        id: concretePiles,
      },
      concrete: {
        id: concrete,
      },
      client: {
        id: clientId,
      },
      objectAddress: address,
      // calculation: {
      //   //если мы добавляем фундамент к уже существующему расчету
      //   id: 58,
      // },
      // foundation: {
      //   //если мы редактируем расчет фундамента
      //   id: 51,
      // },
    });

    apiGetFoundationCalculation(json).then(({ data }) => console.log(data));
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
                    {`${clientInfo?.client.lastName} ${clientInfo?.client.firstName} ${clientInfo?.client.secondName}`}
                  </Paragraph>

                  <Paragraph>{clientInfo?.client.address}</Paragraph>
                  <Paragraph>{clientInfo?.client.phone}</Paragraph>
                  <Paragraph>{clientInfo?.client.email}</Paragraph>
                </Pane>
              }
            >
              {clientInfo ? (
                <Button marginTop={12} onClick={onClientInfoClick}>
                  Информация о клиенте
                </Button>
              ) : (
                <Spinner marginTop={12} size={24} />
              )}
            </Popover>
          </Pane>

          <TextInputField
            label="Адрес объекта"
            width={600}
            marginBottom={0}
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
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
              type="number"
              value={externalWallsPerimeter}
              onChange={(e) => setExternalWallsPerimeter(e.target.value)}
            />

            <TextInputField
              className="input-horizontal"
              display="flex"
              alignItems="center"
              justifyContent="space-between"
              marginBottom={12}
              label="Длина внутренних стен"
              type="number"
              value={internalWallsLength}
              onChange={(e) => setInternalWallsLength(e.target.value)}
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
                  isLoading={!concretePilesValues}
                  items={concretePilesValues}
                  itemToString={(item) => (item ? item.name : "")}
                  value={concretePiles}
                  onChange={(selected) => setConcretePiles(selected.id)}
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
                  isLoading={!concreteValues}
                  items={concreteValues}
                  itemToString={(item) => (item ? item.name : "")}
                  value={concrete}
                  onChange={(selected) => setConcrete(selected.id)}
                />
              </Pane>
            </Pane>
          </Pane>
        </Card>

        <Pane textAlign="right" marginTop={40}>
          <Button intent="danger" marginRight={20} onClick={onClean}>
            Очистить расчёт
          </Button>

          <Button
            appearance="primary"
            disabled={!isFieldsFilled()}
            onClick={onCalculate}
          >
            Рассчитать
          </Button>
        </Pane>
      </Pane>
    </>
  );
};

export default FoundationPage;
