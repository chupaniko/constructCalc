import { useParams } from "react-router-dom";
import Header from "../components/Header";
import { Button, Card, Pane, Paragraph, Spinner } from "evergreen-ui";
import { useEffect, useState } from "react";
import { apiGetClient } from "../../api/api";
import CalculationCreateDialog from "../components/CalculationCreateDialog";
import { useNavigate } from "react-router";

const ClientPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [clientInfo, setClientInfo] = useState(null);
  const [isDialogShown, setIsDialogShown] = useState(false);

  const getClient = (id) => {
    apiGetClient(id)
      .then(({ data }) => setClientInfo(data))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    getClient(id);
  }, [id]);

  return (
    <>
      <Header />

      {clientInfo === null ? (
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
                  {`${clientInfo.client.lastName} ${clientInfo.client.firstName} ${clientInfo.client.secondName}`}
                </Paragraph>

                <Paragraph>{clientInfo.client.address}</Paragraph>
                <Paragraph>{`тел. ${clientInfo.client.phone}`}</Paragraph>
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
            onFoundationCreate={() =>
              navigate(`/foundation?clientId=${clientInfo.client.id}`)
            }
            onCloseComplete={() => setIsDialogShown(false)}
          />
        </Pane>
      )}
    </>
  );
};

export default ClientPage;
