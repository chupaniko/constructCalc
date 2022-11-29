import { Button, Dialog, Pane } from "evergreen-ui";

const CalculationCreateDialog = ({
  isShown,
  onCloseComplete,
  onFrameCreate,
  onFoundationCreate,
}) => {
  return (
    <Dialog
      isShown={isShown}
      title="Выбор конструктивного элемента"
      onCloseComplete={onCloseComplete}
      hasFooter={false}
    >
      <Pane
        display="flex"
        flexDirection="column"
        alignItems="center"
        gap={20}
        paddingBottom={32}
      >
        <Button appearance="primary" width={100} onClick={onFrameCreate}>
          Каркас
        </Button>

        <Button appearance="primary" width={100} onClick={onFoundationCreate}>
          Фундамент
        </Button>
      </Pane>
    </Dialog>
  );
};

export default CalculationCreateDialog;
