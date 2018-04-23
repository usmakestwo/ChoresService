# Chore Service

[![Build Status](https://travis-ci.org/usmakestwo/ChoresService.svg?branch=master)](https://travis-ci.org/usmakestwo/ChoresService)

## Start server

Run with

```
mvn package exec:exec
``

## Endpoints Available

### Get Chore by ID

```
GET http://localhost:8080/bankly/v1/chores/?id=1
```

```
[
    {
        "customerID": 1,
        "name": "Wash car",
        "id": 1,
        "completed": true,
        "recurrent": "Weekly"
    }
]
```

### Delete Chore by ID

```
DELETE http://localhost:8080/bankly/v1/chores/?id=1
```

### Get Chore by Customer ID

```
GET http://localhost:8080/bankly/v1/chores?cust_id=2
```

```
[
    {
        "customerID": 2,
        "name": "Do Laundary",
        "id": 3,
        "completed": false,
        "recurrent": "Monthly"
    },
    {
        "customerID": 2,
        "name": "Walk the dog",
        "id": 4,
        "completed": true,
        "recurrent": "Daily"
    }
]
```
