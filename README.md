# 👩🏻‍💻💵Consulta de gastos de viaje emepleados SURA
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

## ⚙️ Ejecución del proyecto
1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/lauraagudelo04/pruebatecnicasura.git
    cd pruebatecnicasura
    ```
2. Asegúrate de tener PostgreSQL instalado y en ejecución. Crea una base de datos llamada `travelexpensesdb` y actualiza las credenciales de password en `src/main/resources/application.properties` si es necesario.


3. Ejecuta la aplicación utilizando Gradle:
   ```bash
   ./gradlew bootRun
   ```
   
4. Acceder a la aplicación en:

   - API principal: http://localhost:8080/api/v1/expenses
   - Documentación Swagger: http://localhost:8080/swagger-ui.html

---
## 📄 Documentación de la API
La API REST proporciona los siguientes endpoints:
- `GET /api/expenses/summary`: Devuelve el detalle de los gastos agrupados por mes y empleado, indicando totales, IVA aplicado y quién asume los costos.
  
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

