import { useParams } from "react-router-dom";
import Header from "../components/Header";
import { Button, Card, Pane, Paragraph, Spinner } from "evergreen-ui";
import { useEffect, useState } from "react";
import { apiGetClient } from "../../api/api";
import CalculationCreateDialog from "../components/CalculationCreateDialog";

const ClientPage = () => {
  const { id } = useParams();
  const [client, setClient] = useState(null);
  const [isDialogShown, setIsDialogShown] = useState(false);

  const getClient = (id) => {
    apiGetClient(id)
      .then(({ data }) => setClient(data))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    getClient(id);
  }, [id]);

  return (
    <>
      <Header />

      {client === null ? (
        <Pane
          display="flex"
          flex={1}
          alignItems="center"
          justifyContent="center"
        >
          <Spinner />
        </Pane>
      ) : (
        <Pane className="container">
          <Card backgroundColor="white" paddingX={40} paddingY={32}>
            <Pane display="flex" justifyContent="space-between">
              <Pane>
                <Paragraph fontSize={28} fontWeight="bold" marginBottom={20}>
                  {`${client.lastName} ${client.firstName} ${client.secondName}`}
                </Paragraph>

                <Paragraph>{client.address}</Paragraph>
                <Paragraph>{`тел. ${client.phone}`}</Paragraph>
              </Pane>

              <Button
                appearance="primary"
                onClick={() => setIsDialogShown(true)}
              >
                Создать расчет
              </Button>
            </Pane>
          </Card>

          <CalculationCreateDialog
            isShown={isDialogShown}
            onCloseComplete={() => setIsDialogShown(false)}
          />
        </Pane>
      )}
    </>
  );
};

export default ClientPage;
