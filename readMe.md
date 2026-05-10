# Flight Booking API

Simple REST API for booking flight tickets using Spring Boot and Java.

## Features

- Book flight tickets
- Prevent overbooking
- In-memory storage
- Request validation
- Exception handling
- Thread-safe booking flow

---

# How to Run the Service

## Prerequisites

- Java 17
- Maven

## Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

---

# Example Request

## Book Flight Ticket

### API

```http
POST /bookings
```

### CURL Request

```bash
curl --location 'http://localhost:8080/bookings' \
--header 'Content-Type: application/json' \
--data '{
    "flightNumber": "AI101",
    "passengerName": "Mahak"
}'
```

### Success Response

```json
{
    "bookingId": "generated-uuid",
    "flightNumber": "AI101",
    "passengerName": "Mahak",
    "status": "CONFIRMED"
}
```

---

# Error Responses

## Flight Not Found

```text
404 NOT FOUND
```

## No Seats Available

```text
409 CONFLICT
```

## Validation Failure

```text
400 BAD REQUEST
```

---

# Improvements If More Time Was Available

- Add database persistence
- Add distributed locking for multi-instance deployments
- Add booking retrieval APIs
- Add flight search APIs
- Add cancellation functionality
- Add unit and integration tests
- Add Swagger/OpenAPI documentation
- Add Docker support
- Add monitoring and structured logging
- Add optimistic locking and retry handling