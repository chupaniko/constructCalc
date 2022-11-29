import Header from "../components/Header";
import {
  Button,
  Card,
  Combobox,
  Heading,
  Label,
  Pane,
  TextInputField,
} from "evergreen-ui";

const FoundationPage = () => {
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

            <Button marginTop={12}>Информация о клиенте</Button>
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
