# Descripcion
Servicio Rest para Crear Usuarios con telefonos asociados

## Puerto: 8080
## Persistencia: H2 & JPA


## Endpoints
* Login [POST] localhost:8080/auth/login
```
{
    "email":"admin",
    "password":"12345"
}
```
con ese user se baipasea la seguridad

Tienes que crear el BearerToken e insertarlo en cada peticion
* BuscarTodos [GET] localhost:8080/api/user
* BuscarPorId [GET] localhost:8080/api/user/{id}
* BorrarPorId [DELETE] localhost:8080/api/user/{id}
* Actualizar [PUT] localhost:8080/api/user/1
```
{ "name": "Juan Rodriguez",
  "email": "juan1@rodriguez.org",
  "password": "hunter2",
  "phones": [{
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"}]
}
```
* Crear [POST] localhost:8080/api/user
```
{ "name": "Juan Rodriguez",
  "email": "juan1@rodriguez.org",
  "password": "hunter2",
  "phones": [{
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"}]
}
```

## Swagger
http://localhost:8080/swagger-ui/index.html