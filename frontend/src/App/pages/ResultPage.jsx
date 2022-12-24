import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { apiGetFoundationResults } from "../../api/api";
import Header from "../components/Header";
import {Heading, Pane, Spinner, Table} from "evergreen-ui";


const ResultPage = () => {
  const { id } = useParams();

  const [calculationResult, setCalculationResult] = useState({});

  useEffect(() => {
    apiGetFoundationResults(id).then(({ data }) => {
      console.log(data);
      setCalculationResult(data);
    });
  }, [id]);

  function Loader() {
    return (
        <Pane display="flex" alignItems="center" justifyContent="center" height={400}>
          <Spinner />
        </Pane>
    )
  }

  function Result() {
    return (
        <>
          <Header/>

          <Pane className="container">
            <Heading is="h1" size={900}>
              Результаты расчёта
            </Heading>
            <br/>
            <Heading is="h2" size={800}>
              Фундамент
            </Heading>

            <Table marginTop={20} marginBottom={50}>
              <Table.Head paddingRight={0} background="#E2E2E2">
                <Table.TextHeaderCell></Table.TextHeaderCell>
                <Table.TextHeaderCell flex={2}>Материал</Table.TextHeaderCell>
                <Table.TextHeaderCell>Ед. измерения</Table.TextHeaderCell>
                <Table.TextHeaderCell>Количество</Table.TextHeaderCell>
                <Table.TextHeaderCell>Стоимость материалов</Table.TextHeaderCell>
              </Table.Head>

              <Table.Body>
                {calculationResult?.elements?.map((e, index) => (
                    <div key={index}>
                      <Table.Row background="rgba(222, 222, 222, 0.29)">
                        <Table.TextCell flex={5}><b style={{fontSize:'14px'}}>{e.name}</b></Table.TextCell>
                        <Table.TextCell>{e.price}</Table.TextCell>
                      </Table.Row>

                      {e.foundationMaterialElementList.map((me, index) => (
                          <Table.Row key={index}>
                            <Table.TextCell>{me.name}</Table.TextCell>
                            {me.materialCharacteristic.length>0
                                ? <Table.TextCell flex={2}>
                                  {`${me.materialCharacteristic.material.name} ${me.materialCharacteristic.width} * ${me.materialCharacteristic.height} * ${me.materialCharacteristic.length} `}
                                </Table.TextCell>
                                : <Table.TextCell flex={2}>
                                  {me.materialCharacteristic.material.name}
                                </Table.TextCell>}
                            <Table.TextCell>
                              {me.materialCharacteristic.unit.name}
                            </Table.TextCell>
                            <Table.TextCell>{me.count}</Table.TextCell>
                            <Table.TextCell>{me.price}</Table.TextCell>
                          </Table.Row>
                      ))}
                    </div>
                ))}
              </Table.Body>
            </Table>
          </Pane>
        </>
    )
  }
  return calculationResult? <Result/> : <Loader />;
};

export default ResultPage;