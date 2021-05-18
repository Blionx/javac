
## Parts controller
**POST - Create new part**
	api/v1/parts/
```
{
  "description": "Estereo Ford Fiesta base",
  "stock": 18,
  "partCode": "00000004",
  "providerId": 1,
  "netWeight": 12,
  "longDimension": 10,
  "widthDimension": 9,
  "talDimension": 4,
  "lastModification": "2021-05-12",
  "discountTypeId": 1,
  "normalPrice": 16000,
  "urgentPrice": 23000
}
```

**PATCH - Update stock**

	api/v1/parts?partCode=00000001&quantity=32
	*Query string parameters:*
 - partCode: string
 - quantity: integer
 
 **GET - Parts list**

	/api/v1/parts/list?queryType=P&date=1996-01-21&order=1
	*Query string parameters:*
 - queryType: string
 - date: YYYY-MM-DD (**not required**)
 - order: integer (**not required**)

## Orders Controller

**POST - Generate new order**

	api/v1/parts/orders/
```
{
  "centralHouseId": 1,
  "consessionarieId": 1,
  "shippingType": "Mercado Envios",
  "parts": [
    {
      "partCode": "00000002",
      "description": "Paragolpes de Fiat 147",
      "quantity": 2,
      "accountType": "Repuestos",
      "reason": "I hit a tree at 40mph. Car's good though"
    }
  ]
}
```
  **GET - Fetch order**

​/api​/v1​/parts​/orders​/{orderId}
	*Query string parameters:*
 - orderId: integer 
*Note: orderId is composed by the dealer number(D), the central house number(C), and the order code(O). It must respect the following format: *
DDDD-CCCC-OOOOOOOO
*Should any number have less than four digits, it must be completed with zeros. E.G: 0001-0001-00000025*

**GET - List all orders**

/api/v1/parts/orders/list?dealerNumber=1&deliveryStatus=D&order=0
	*Query string parameters:*
 - dealerNumber: integer
 - deliveryStatus: string (**not required**)
 - order: integer (**not required**)

**PATCH - Update order status**

/api/v1/parts/orders/update_status?orderStatus=D&orderNumberCM=25
	*Query string parameters:*
 - orderStatus: string
 - orderNumberCM: integer



## Users Controller

**POST - Create new user - Requires admin role**

api/v1/users/new/
*x-www-form-encoded parameters:*
- username: string
- password: string

**PATCH - Update user role - Requires admin role**

_Switches between admin and regular roles_

/api/v1/users/changeRole
*Query string parameters:*
- username: string

## Parts controller ( 6th Requirement)
**POST - create a new PartRecord**
api/v1/parts/price/
```
{
    "discountType":1,
    "normalPrice":6051,
    "urgentPrice": 11000,
    "partCode":"00000003"
}
```

**GET - The History of prices changes and its variances.**
api/v1/parts/priccehistory/{partCode}?fromDate=1992-01-01

***Response***
```
{{
    "starting_normal_price": 5000.0,
    "ending_normal_price": 10000.0,
    "starting_urgent_price": 10000.0,
    "ending_urgent_price": 20000.0,
    "price_change_variance_list": [
        {
            "date": "1992-01-28",
            "normal_price": 5000.0,
            "urgent_price": 10000.0,
            "normal_price_variation": 0.0,
            "urgent_price_variation": 0.0
        },
        {
            "date": "1992-02-28",
            "normal_price": 6000.0,
            "urgent_price": 9000.0,
            "normal_price_variation": 20.0,
            "urgent_price_variation": -10.0
        },
        {
            "date": "1992-03-28",
            "normal_price": 4000.0,
            "urgent_price": 7000.0,
            "normal_price_variation": -33.33333333333333,
            "urgent_price_variation": -22.22222222222223
        },
        {
            "date": "1992-04-28",
            "normal_price": 3000.0,
            "urgent_price": 6000.0,
            "normal_price_variation": -25.0,
            "urgent_price_variation": -14.285714285714292
        },
        {
            "date": "1992-05-28",
            "normal_price": 5000.0,
            "urgent_price": 11000.0,
            "normal_price_variation": 66.66666666666666,
            "urgent_price_variation": 83.33333333333334
        },
        {
            "date": "1992-06-28",
            "normal_price": 7000.0,
            "urgent_price": 15000.0,
            "normal_price_variation": 40.0,
            "urgent_price_variation": 36.363636363636374
        },
        {
            "date": "1992-07-28",
            "normal_price": 10000.0,
            "urgent_price": 20000.0,
            "normal_price_variation": 42.85714285714286,
            "urgent_price_variation": 33.33333333333334
        }
    ],
    "normal_price_variance": 100.0,
    "urgent_price_variance": 100.0
}
```







