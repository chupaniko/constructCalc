import { Heading, Pane } from "evergreen-ui";
import logo from "../../assets/logo.png";

const Header = () => {
  return (
    <header>
      <Pane backgroundColor="white" elevation={1} marginBottom={40}>
        <Pane
          className="container"
          display="flex"
          justifyContent="space-between"
          paddingY={16}
        >
          <Pane display="flex" alignItems="center">

            <Pane display="flex" alignItems="center">
              <img src={logo} alt="Logo" height={60} />

              <Heading is="h1" className="title" fontSize={28} fontWeight={700}>
                НеСтройСам
              </Heading>
            </Pane>
          </Pane>

          <Pane display="flex" alignItems="center">
            Петров Петр Петрович
          </Pane>
        </Pane>
      </Pane>
    </header>
  );
};

export default Header;
