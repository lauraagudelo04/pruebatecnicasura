# 👩🏻‍💻💵Consulta de gastos de viaje empleados SURA
Este repositorio contiene el desarrollo de la **prueba técnica** para la práctica profesional en el área de tecnología en SURA.  

El reto consiste en una API REST que permite consultar los gastos de viaje de los empleados mostrandolos en una lista con los siguientes datos:

- Totales de gastos por todos los empleados.
- Totales por empleado y por mes.
- Validación para determinar si los gastos los asume la empresa o el empleado (según superen o no 1.000.000 COP con IVA).
- Documentación interactiva con Swagger / OpenAPI. 

## 🚀🌟Tecnologías utilizadas
- **Spring Boot** como framework principal
- **Gradle** como gestor de dependencias
- **Spring Web** para construccion de API REST
- **Spring Data JPA** para persistencia
- **PostgreSQL** como base de datos relacional
- **Swagger / OpenAPI** para documentación interactiva de la API

---
## 📂 Estructura de carpetas

```
src/main/java/com/suraprueba/expensesapi/
├── controller/              # REST API controllers y responses
│   ├── response/
│   │   ├── GetTotalExpensesResponse.java
│   │   ├── GetTravelExpensesResponse.java
│   │   └── Response.java
│   └── rest/
│       ├── EmployeeController.java
│       └── ExpenseController.java
│
├── domain/                  # Entidades del dominio
│   ├── Employee.java
│   └── Expense.java
│
├── dto/                     # Data Transfer Objects
│   ├── EmployeeMonthlyExpensesDTO.java
│   ├── ExpenseDTO.java
│   ├── MonthlyExpensesDTO.java
│   ├── TotalExpensesDTO.java
│   └── TravelExpensesResponseDTO.java
│
├── exceptions/              # Manejo de excepciones
│   └── GlobalExceptionHandler.java
│
├── repository/             
│   ├── EmployeeRepository.java
│   └── IExpenseRepository.java
│
├── service/              
│   ├── impl/
│   │   ├── EmployeeServiceImpl.java
│   │   └── ExpenseServiceImpl.java
│   ├── IEmployeeService.java
│   └── IExpenseService.java
└── util/                    # Utilidades
    └── ExpenseCalculator.java`

```

## ⚙️ Ejecución del proyecto
1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/lauraagudelo04/pruebatecnicasura.git
    cd pruebatecnicasura
    ```
2. Asegúrate de tener PostgreSQL instalado y en ejecución. Crea una base de datos llamada `travelexpensesdb` y actualiza las credenciales de password en `src/main/resources/application.properties` si es necesario.

``` sql
CREATE DATABASE travelexpensesdb;
```

3. Ejecuta la aplicación utilizando Gradle:
   ```bash
   ./gradlew bootRun
   ```
   
4. Acceder a la aplicación en:

   - API principal: http://localhost:8080/api/v1/expenses
   - Documentación Swagger: http://localhost:8080/swagger-ui.html

---

## 🗃️ Datos iniciales de prueba

La aplicación viene preconfigurada para cargar **empleados y gastos de viaje de ejemplo** automáticamente al iniciar.

Esto se realiza en la clase `TravelExpensesApplication` mediante un `CommandLineRunner` que inserta registros en la base de datos si está vacía.

### Empleados cargados:
- Adam
- Bolton
- Chelsea
- Elsy
- Vincent
- Warden

### Ejemplos de gastos registrados:
- **Adam**: 2.000.000 COP (01/01/21), 1.000.000 COP (02/01/21), 500.000 COP (03/02/21)
- **Bolton**: 400.000 COP (01/01/21), 500.000 COP (02/01/21), 1.100.000 COP (03/02/21)
- **Chelsea**: 900.000 COP (02/01/21), 59.999 COP (02/01/21), 1.100.000 COP (03/02/21)
- **Elsy**: 4.000.000 COP (02/01/21)
- **Vincent**: 899.999 COP (03/02/21)
- **Warden**: 5.100.000 COP (02/01/21), 1.100.000 COP (03/02/21)

### ¿Cómo funciona?
- Si las tablas de empleados y gastos están **vacías**, el `CommandLineRunner` las llena automáticamente.
- Si ya existen registros, el cargue inicial no se ejecuta para evitar duplicados.

---
## 📄 Documentación de la API
La API REST proporciona los siguientes endpoints:
- `GET /api/v1/expenses`: Devuelve el detalle de los gastos agrupados por mes y empleado, indicando totales, IVA aplicado y quién asume los costos.

#### Ejemplo de respuesta simplificado:
```json
{
   "message": [
      "Gastos de viaje consultados exitosamente"
   ],
   "data": {
      "months": [
         {
            "month": "2021-01",
            "employees": [
               {
                  "employee": "Adam",
                  "expenses": [
                     {
                        "date": "2021-01-01",
                        "amount": 2000000.00
                     },
                     {
                        "date": "2021-01-02",
                        "amount": 1000000.00
                     }
                  ],
                  "totalWithoutIva": 3000000.00,
                  "ivaAmount": 570000.00,
                  "totalEmployeeWithIva": 3570000.00,
                  "coveredBy": "Company"
               }
            ],
            "totalMonth": 3570000.00
         }
      ],
      "totalAllMonths": 3570000.00
   }
}

```

- `GET /api/v1/expenses/total`: Devuelve el total de gastos de viaje de todos los empleados.
#### Ejemplo de respuesta simplificado:
```json
{
   "message": [
      "Gastos totales de viajes consultados exitosamente"
   ],
   "data": {
      "totalWithoutIva": 18659998.00,
      "totalWithIva": 22205397.6200
   }
}
```

- `GET /api/v1/employees/with-expenses`: Devuelve la lista de empleados que han registrado gastos de viaje en un rango de fechas.
#### Ejemplo de respuesta simplificado:
- path `api/v1/employees/with-expenses?startDate=2021-01-01&endDate=2021-02-01&page=0&size=1`
```json
{
   "content": [
      {
         "id": 1,
         "name": "Adam",
         "expenses": [
            {
               "date": "2021-01-01",
               "amount": 2000000.00
            },
            {
               "date": "2021-01-02",
               "amount": 1000000.00
            },
            {
               "date": "2021-02-03",
               "amount": 500000.00
            }
         ]
      }
   ],
   "page": 0,
   "size": 1,
   "totalElements": 5
}
```
---

## 📬 Colección de Postman

Para facilitar las pruebas de la API, se incluye una colección de **Postman** con los endpoints ya configurados.

### Importar la colección
1. Abre **Postman** en tu pc.
2. Haz clic en **Import** (parte superior izquierda).
3. Selecciona el archivo `EmployeeExpenses.postman_collection.json` que está en la raíz del proyecto
4. Una vez importada, se tendra disponible todos los endpoints con ejemplos listos para ejecutar.

### Contenido de la colección
- `GET /api/v1/expenses` → Resumen de gastos por mes y empleado.
- `GET /api/v1/expenses/total` → Total acumulado de todos los gastos.
- `GET /api/v1/employees/with-expenses?startDate=2021-01-01&endDate=2021-02-01&page=0&size=1` → Lista de empleados con gastos en un rango de fechas (con paginación).

---

📌 **Nota:** Asegúrate de que la aplicación esté corriendo en `http://localhost:8080` y que la base de datos `travelexpensesdb` esté creada antes de probar los endpoints.

