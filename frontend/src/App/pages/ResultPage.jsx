import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { apiGetFoundationResults } from "../../api/api";
import Header from "../components/Header";
import { Heading, Pane, Table } from "evergreen-ui";

const ResultPage = () => {
  const { id } = useParams();

  const [calculationResult, setCalculationResult] = useState({});

  useEffect(() => {
    apiGetFoundationResults(id).then(({ data }) => {
      console.log(data);
      setCalculationResult(data);
    });
  }, [id]);

  const res = {
    calculation: {
      id: 431,
      objectAddress: "Тестовск",
      status: "Открыт",
    },
    foundation: {
      id: 432,
      externalWallsPerimeter: 14,
      internalWallLength: 18,
      concretePiles: {
        id: 242,
        name: "",
        width: 300.0,
        height: 300.0,
        length: 5000.0,
        volume: 0.0,
        price: 4953.0,
        material: {
          id: 237,
          name: "Бетонные сваи",
        },
        unit: {
          id: 234,
          name: "шт",
        },
      },
      concrete: {
        id: 247,
        name: "М300 (В22.5)",
        width: 0.0,
        height: 0.0,
        length: 0.0,
        volume: 0.0,
        price: 3160.0,
        material: {
          id: 243,
          name: "Бетон",
        },
        unit: {
          id: 235,
          name: "м3",
        },
      },
      calculation: {
        id: 431,
        objectAddress: "Тестовск",
        status: "Открыт",
      },
    },
    elements: [
      {
        name: "Сваи",
        foundationMaterialElementList: [
          {
            id: 433,
            name: "Количество свай",
            materialCharacteristic: {
              id: 242,
              name: "",
              width: 300.0,
              height: 300.0,
              length: 5000.0,
              volume: 0.0,
              price: 4953.0,
              material: {
                id: 237,
                name: "Бетонные сваи",
              },
              unit: {
                id: 234,
                name: "шт",
              },
            },
            calculation: {
              id: 431,
              objectAddress: "Тестовск",
              status: "Открыт",
            },
            count: 16.0,
            price: 79248.0,
            elementType: "F",
          },
        ],
        price: 79248.0,
      },
      {
        name: "Ростверк",
        foundationMaterialElementList: [
          {
            id: 434,
            name: "Бетон",
            materialCharacteristic: {
              id: 247,
              name: "М300 (В22.5)",
              width: 0.0,
              height: 0.0,
              length: 0.0,
              volume: 0.0,
              price: 3160.0,
              material: {
                id: 243,
                name: "Бетон",
              },
              unit: {
                id: 235,
                name: "м3",
              },
            },
            calculation: {
              id: 431,
              objectAddress: "Тестовск",
              status: "Открыт",
            },
            count: 4.4159999999999995,
            price: 13954.559999999998,
            elementType: "F",
          },
          {
            id: 435,
            name: "Арматура 1",
            materialCharacteristic: {
              id: 258,
              name: "",
              width: 14.0,
              height: 0.0,
              length: 0.0,
              volume: 0.0,
              price: 23.0,
              material: {
                id: 256,
                name: "Арматура",
              },
              unit: {
                id: 236,
                name: "м",
              },
            },
            calculation: {
              id: 431,
              objectAddress: "Тестовск",
              status: "Открыт",
            },
            count: 22.0,
            price: 506.0,
            elementType: "F",
          },
          {
            id: 436,
            name: "Арматура 2",
            materialCharacteristic: {
              id: 257,
              name: "",
              width: 8.0,
              height: 0.0,
              length: 0.0,
              volume: 0.0,
              price: 23.0,
              material: {
                id: 256,
                name: "Арматура",
              },
              unit: {
                id: 236,
                name: "м",
              },
            },
            calculation: {
              id: 431,
              objectAddress: "Тестовск",
              status: "Открыт",
            },
            count: 18.0,
            price: 414.0,
            elementType: "F",
          },
        ],
        price: 14874.559999999998,
      },
      {
        name: "Опалубка",
        foundationMaterialElementList: [
          {
            id: 437,
            name: "Доска",
            materialCharacteristic: {
              id: 260,
              name: "",
              width: 30.0,
              height: 100.0,
              length: 3000.0,
              volume: 0.0,
              price: 7800.0,
              material: {
                id: 259,
                name: "Доска",
              },
              unit: {
                id: 235,
                name: "м3",
              },
            },
            calculation: {
              id: 431,
              objectAddress: "Тестовск",
              status: "Открыт",
            },
            count: 0.96,
            price: 7488.0,
            elementType: "F",
          },
          {
            id: 438,
            name: "Брус",
            materialCharacteristic: {
              id: 272,
              name: "",
              width: 50.0,
              height: 50.0,
              length: 3000.0,
              volume: 0.0,
              price: 9100.0,
              material: {
                id: 271,
                name: "Брус",
              },
              unit: {
                id: 235,
                name: "м3",
              },
            },
            calculation: {
              id: 431,
              objectAddress: "Тестовск",
              status: "Открыт",
            },
            count: 0.11428571428571431,
            price: 1040.0000000000002,
            elementType: "F",
          },
        ],
        price: 8528.0,
      },
    ],
    price: 102650.56,
  };

  return (
    <>
      <Header />

      <Pane className="container">
        <Heading is="h1" size={900}>
          Результаты расчёта
        </Heading>

        <Heading is="h2" size={800}>
          Фундамент
        </Heading>

        <Table>
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
                  <Table.TextCell flex={5}>{e.name}</Table.TextCell>
                  <Table.TextCell>{e.price}</Table.TextCell>
                </Table.Row>

                {e.foundationMaterialElementList.map((me, index) => (
                  <Table.Row key={index}>
                    <Table.TextCell>{me.name}</Table.TextCell>
                    <Table.TextCell flex={2}>
                      {`${me.materialCharacteristic.material.name} ${me.materialCharacteristic.width} * ${me.materialCharacteristic.height} * ${me.materialCharacteristic.length}`}
                    </Table.TextCell>
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
  );
};

export default ResultPage;
