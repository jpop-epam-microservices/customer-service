## Product-Service
Customer Service REST API

## Author
Chacko Mathew

### This Rest API supports the following endpoints

| Functionality                   | End-point                                     | HTTP Method | Security | Comments |
|---------------------------------|-----------------------------------------------|-------------|----------|----------|
| Get all customers               | /customers                                    | GET         |          |          |
| Create a customer               | /customers                                    | POST        |          |          |
| Get a customer by Id            | /customers/{customerId}                       | GET         |          |          |
| Update a customer details       | /customers/{customerId}                       | PUT         |          |          |
| Delete a customer               | /customers/{customerId}                       | DELETE      |          |          |
| Add an address to a customer    | /customers/{customerId}/addresses             | POST        |          |          |
| Get all addresses of a customer | /customers/{customerId}/addresses             | GET         |          |          |
| Get home address of a customer  | /customers/{customerId}/addresses?type=home   | GET         |          |          |
| Update a customer address       | /customers/{customerId}/addresses/{addressId} | PUT         |          |          |
| Delete a customer address       | /customers/{customerId}/addresses/{addressId} | DELETE      |          |          |



### Maven Project