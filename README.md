# constructCalc
## For Setup:
* clone the repository
* mvn clean install and mvn spring-boot:run
* cd frontend
* npm start

**Авторизация**

POST: /api/authentication/

*Body*
```
{
    "username": "ivanov@ssau.ru",
    "password": "ivanov128"
}
```

*Returns*
```
{
    "id": 1,
    "username": "ivanov@ssau.ru",
    "password": "$2a$10$hXttVfHu7tqpSUkWPn3tJeC5z/gp54frP0GR2QgKBh9uAg1l3Insi",
    "enabled": true,
    "authorities": [
        {
            "authority": "ivanov@ssau.ru"
        }
    ],
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "accountNonLocked": true
}
```

**Автозаполнение БД:**

GET: /autocomplete/

## Clients REST Api
**Получить список всех клиентов**

GET: /api/clients/all

**Сохранить/редактировать пользователя**

POST: /api/clients/save

*Body:*
```
{
    "lastName":"Иванов",
    "firstName":"Иван",
    "secondName":"Иванович",
    "phone":"+7-900-876-09-87",
    "email":"ex@ex.com",
    "address":"улица Уличная",
    "usr": {
        "id": "1",
        "username":"ivanov@ssau.ru"
    }
}
```

**Удалить пользователя**

DELETE: /api/clients/delete/{id}

**Получить информацию о пользователе по id**

GET: /api/clients/byId/{id}     

**Получить список клиентов по пользователю**

GET: /api/clients/findByUser/{username}

## Calculation REST Api
**Получить характеристики материалов**

Используем для выпадашек при расчетах (например, получаем различные виды Бетона) - если необходимо, будем улучшать, щас пока по id

GET: /getMaterialCharacteristics/{materialId}

**Получить информацию по бетону (для выпадашек)**

GET: api/calculation/getBetonValues

**Получить информацию по бетонным сваям (для выпадашек)**

GET: api/calculation/getBetonPilesValues

**Получить текущие параметры фундамента по расчету**

GET: /getFoundationByCalculation/{id}

**Удалить расчет**

DELETE: /deleteCalculation/{id}

**Расчет фундамента**

POST: /api/calculation/foundation

Body:
```
{
    "externalWallsPerimeter": 36,
    "internalWallLength": 25,
    "concretePiles":
    {
        "id": 15
    },
    "concrete":
    {
        "id": 23
    },
    "client":{
        "id": 1
    },
   "objectAddress": "ул.Гдетошная, д.33",
   "calculation": { //если мы добавляем фундамент к уже существующему расчету
       "id": 58
   },
   "foundation":{ //если мы редактируем расчет фундамента
       "id": 51
   }
}
```

