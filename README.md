# constructCalc
## For Setup:
* clone the repository
* mvn clean install and mvn spring-boot:run
* cd frontend
* npm start

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



