import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { apiGetFoundationByCalculation } from "../../api/api";
import Header from "../components/Header";
import { Heading, Pane, Table } from "evergreen-ui";

const ResultPage = () => {
  const { id } = useParams();

  const [calculationResult, setCalculationResult] = useState(undefined);

  const res = {
    calculation: {
      id: 423,
      objectAddress: "Гдетошная д.33",
      status: "Открыт",
    },
    foundation: {
      id: 424,
      externalWallsPerimeter: 16,
      internalWallLength: 18,
      concretePiles: {
        id: 240,
        name: "",
        width: 300.0,
        height: 300.0,
        length: 3000.0,
        volume: 0.0,
        price: 3383.0,
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
        id: 250,
        name: "М450(В35)",
        width: 0.0,
        height: 0.0,
        length: 0.0,
        volume: 0.0,
        price: 3680.0,
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
        id: 423,
        objectAddress: "Гдетошная д.33",
        status: "Открыт",
      },
    },
    elements: [
      {
        name: "Сваи",
        foundationMaterialElementList: [
          {
            id: 425,
            name: "Количество свай",
            materialCharacteristic: {
              id: 240,
              name: "",
              width: 300.0,
              height: 300.0,
              length: 3000.0,
              volume: 0.0,
              price: 3383.0,
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
              id: 423,
              objectAddress: "Гдетошная д.33",
              status: "Открыт",
            },
            count: 17.0,
            price: 57511.0,
            elementType: "F",
          },
        ],
        price: 57511.0,
      },
      {
        name: "Ростверк",
        foundationMaterialElementList: [
          {
            id: 426,
            name: "Бетон",
            materialCharacteristic: {
              id: 250,
              name: "М450(В35)",
              width: 0.0,
              height: 0.0,
              length: 0.0,
              volume: 0.0,
              price: 3680.0,
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
              id: 423,
              objectAddress: "Гдетошная д.33",
              status: "Открыт",
            },
            count: 4.692,
            price: 17266.56,
            elementType: "F",
          },
          {
            id: 427,
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
              id: 423,
              objectAddress: "Гдетошная д.33",
              status: "Открыт",
            },
            count: 23.0,
            price: 529.0,
            elementType: "F",
          },
          {
            id: 428,
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
              id: 423,
              objectAddress: "Гдетошная д.33",
              status: "Открыт",
            },
            count: 19.0,
            price: 437.0,
            elementType: "F",
          },
        ],
        price: 18232.56,
      },
      {
        name: "Опалубка",
        foundationMaterialElementList: [
          {
            id: 429,
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
              id: 423,
              objectAddress: "Гдетошная д.33",
              status: "Открыт",
            },
            count: 1.02,
            price: 7956.0,
            elementType: "F",
          },
          {
            id: 430,
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
              id: 423,
              objectAddress: "Гдетошная д.33",
              status: "Открыт",
            },
            count: 0.1214,
            price: 1105,
            elementType: "F",
          },
        ],
        price: 9061.0,
      },
    ],
    price: 84804.56,
  };

  useEffect(() => {
    apiGetFoundationByCalculation(id).then(({ data }) => {
      console.log(data);

      setCalculationResult(data);
    });
  }, [id]);

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
            {res.elements.map((e) => (
              <>
                <Table.Row background="rgba(222, 222, 222, 0.29)">
                  <Table.TextCell flex={5}>{e.name}</Table.TextCell>
                  <Table.TextCell>{e.price}</Table.TextCell>
                </Table.Row>

                {e.foundationMaterialElementList.map((me) => (
                  <Table.Row>
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
              </>
            ))}
          </Table.Body>
        </Table>
      </Pane>
    </>
  );
};

export default ResultPage;
