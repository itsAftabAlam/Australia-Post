# Australia Post API
This is an attempt to integrate Australia Post API through a backend application.
This application can be used to quote cost, create shipment, track shipment, and to create a pickup.
The entry point of the application is through the Main class and the method to facilitate the APIs are implemented in AustraliaPost class.
<br>
The description on integration of these 4 API are listed below:

## Get Quote API
### Purpose
This API is used to quote cost of a particular parcel. This is implemented through the use of the method getQuote() which takes parameters such as source and destination postal codes and dimensions of the parcel and additionally a service code to quote the cost.
A sample code to execute the method is also provided.
Note : This can only calculate cost for standard parcels i.e domestic parcels. Cost of non standard parcels such as international parcel, international letter and domestic letter can also be retrieved in a similar fashion by using their APIs.

### Endpoint
HTTP Method : GET
<br>
Resource URL : ```https://digitalapi.auspost.com.au/postage/parcel/domestic/service ```

### Headers
|Name|Req/Optional|Description|
|----|------------|------------|
|auth-key|required|Your PAC API Key (HTTP Header)|

### Required Parameters
| Name           | Req/Optional | Description                                           |
|----------------|--------------|-------------------------------------------------------|
| from_postcode  | Required     | The postcode the parcel will be sent from.            |
| to_postcode    | Required     | The postcode the parcel will be sent to.              |
| length         | Required     | The parcel length in CMs.                             |
| width          | Required     | The parcel width in CMs.                              |
| height         | Required     | The parcel height in CMs.                             |
| weight         | Required     | The parcel weight in KGs.                             |
| service_code   | Required     | The chosen product / service code.                    |
| option_code    | Optional     | The chosen option code for the product option.        |
| suboption_code | Optional     | The chosen suboption code for the product suboption.  |
| extra_cover    | Optional     | The dollar amount of the extra cover required         |

### Sample Request 
```
GET
https://digitalapi.auspost.com.au/postage/parcel/international/calculate.{format}?country_code=NZ&weight=1.0&service_code=INTL_SERVICE_EPI
```
Note: This request can be invoked from getQuote() method.

### Sample Response
```
{
   "postage_result":{
      "service":"Express Post International",
      "total_cost":"45.00",
      "costs":{
         "cost":{
            "cost":"45.00",
            "item":"Express Post International"
         }
      }
   }
}
```
## Create Order API
### Purpose
This API is used to create new shipments. These shipments are categorized into two parts, domestic and international, both being handled separately. For creation of new domestic shipment use createShipment() method and for international shipment use createInternationalShipment() method.
A sample code to execute the method is also provided.

#### For domestic shipments
### Endpoint
HTTP Method : POST
<br>
Resource URL : ```https://digitalapi.auspost.com.au/shipping/v1/shipments```

### Headers
|Name|Req/Optional|Description|
|----|------------|------------|
|authentication|required|Your username and password as a HTTP Basic Auth hash|
|account-number|required|A 10 digit number for an Australia Post charge account, or an 8 digit number for a StarTrack account. |

### Sample Request 
```
POST
https://digitalapi.auspost.com.au/shipping/v1/shipments
```
JSON
```
 {
    "shipments":[
        {
            "shipment_reference":"XYZ-001-01",
            "customer_reference_1":"Order 001",
            "customer_reference_2":"SKU-1, SKU-2, SKU-3",
            "email_tracking_enabled":true,
            "from":{
                "name":"John Citizen",
                "lines": [
                    "1 Main Street"
                ],
                "suburb": "MELBOURNE",
                "state": "VIC",
                "postcode": "3000",
                "phone": "0401234567",
                "email":"john.citizen@citizen.com"
            },
            "to":{
                "name":"Jane Smith",
                "business_name":"Smith Pty Ltd",
                "lines":[
                    "123 Centre Road"
                ],
                "suburb":"Sydney",
                "state":"NSW",
                "postcode":"2000",
                "phone":"0412345678",
                "email":"jane.smith@smith.com"
            },
            "items":[
                {
                    "item_reference":"SKU-1",
                    "product_id":"T28S",
                    "length":"10",
                    "height":"10",
                    "width":"10",
                    "weight":"1",
                    "authority_to_leave":false,
                    "allow_partial_delivery":true,
                    "features":{
                        "TRANSIT_COVER":{
                             "attributes":{
                                 "cover_amount":1000
                              }
                         }
                     }
                },
                {
                    "item_reference":"SKU-2",
                    "product_id":"T28S",
                    "length":"10",
                    "height":"10",
                    "width":"10",
                    "weight":"1",
                    "authority_to_leave":false,
                    "allow_partial_delivery":true
                },
                {
                    "item_reference":"SKU-3",
                    "product_id":"T28S",
                    "length":"10",
                    "height":"10",
                    "width":"10",
                    "weight":"1",
                    "authority_to_leave":false,
                    "allow_partial_delivery":true
                }
            ]
        }
    ]
}
```
Note: This request can be invoked from createShipment() method.

### Sample Response
HTTP Response Code: 201
```
 {
    "shipments": [
        {
            "shipment_id": "9lesEAOvOm4AAAFI3swaDRYB",
            "shipment_reference": "XYZ-001-01",
            "shipment_creation_date": "2014-08-27T15:48:09+10:00",
            "shipment_modified_date": "2014-08-27T15:48:09+10:00",
            "customer_reference_1": "Order 001",
            "customer_reference_2": "SKU-1, SKU-2, SKU-3",
            "sender_references": [
            "Order 001",
            "SKU-1, SKU-2, SKU-3"
      	     ],
            "email_tracking_enabled":true,
            "items": [
                {
                    "item_id": "LDCsEAOvU_oAAAFI6MwaDRYB",
                    "item_reference": "SKU-1",
                    "tracking_details": {
                        "article_id": "ABC000128B4C5",
                        "consignment_id": "ABC000128"
                    },
                    "product_id": "T28S",
                    "item_summary": {
                        "total_cost": 5,
                        "total_cost_ex_gst": 4.55,
                        "total_gst": 0.45,
                        "manual_handling_surcharge": 7.60,
                        "status": "Created"
                    }
                },
                {
                    "item_id": "ynesEAOvnlAAAAFI88waDRYB",
                    "item_reference": "SKU-3",
                    "tracking_details": {
                        "article_id": "ABC000128B4C6",
                        "consignment_id": "ABC000128"
                    },
                    "product_id": "T28S",
                    "item_summary": {
                        "total_cost": 4,
                        "total_cost_ex_gst": 3.64,
                        "total_gst": 0.36,
                        "status": "Created"
                    }
                },
                {
                    "item_id": "TkGsEAOv9.4AAAFI8MwaDRYB",
                    "item_reference": "SKU-2",
                    "tracking_details": {
                        "article_id": "ABC000128B4C7",
                        "consignment_id": "ABC000128"
                    },
                    "product_id": "T28S",
                    "item_summary": {
                        "total_cost": 4,
                        "total_cost_ex_gst": 3.64,
                        "total_gst": 0.36,
                        "status": "Created"
                    }
                }
            ],
            "shipment_summary": {
                "total_cost": 13,
                "total_cost_ex_gst": 11.82,
                "fuel_surcharge": 2.15,
                "total_gst": 1.18,
                "manual_handling_surcharge": 7.60,
                "status": "Created",
                "number_of_items": 3,
                "tracking_summary": {
                    "Initiated": 3
                },
            }
        }
    ]
}
```
HTTP Response Code: 400
```
 {
    "errors": [
        {
            "code": "44003",
            "name": "DANGEROUS_GOODS_NOT_SUPPORTED_BY_PRODUCT_ERROR",
            "field": "shipments[0].items[0]",
            "message": "The product T28S specified in an item has indicated that dangerous goods will be included in the parcel, however, the product does not allow dangerous goods to be sent using the service.  Please choose a product that allows dangerous goods to be included within the parcel to be sent."
        }
    ]
}
```
#### For international shipments
### Endpoint
HTTP Method : POST
<br>
Resource URL : ```https://digitalapi.auspost.com.au/shipping/v1/shipments```

### Headers
|Name|Req/Optional|Description|
|----|------------|------------|
|authentication|required|Your username and password as a HTTP Basic Auth hash|
|account-number|required|A 10 digit number for an Australia Post charge account, or an 8 digit number for a StarTrack account. |

### Sample Request 
```
POST
https://digitalapi.auspost.com.au/shipping/v1/shipments
```
JSON
```
  {
   "shipments": [
       {
           "shipment_reference": "shipment reference 1",
           "from": {
               "country": "AU",
               "email": "larry.citizen@citizen.com",
               "lines": [
                   "123 Main Street"
               ],
               "name": "Larry Smith",
               "phone": "0412345678",
               "postcode": "3000",
               "state": "VIC",
               "suburb": "Melbourne"
           },
           "to": {
               "email": "jane.buyer@citizen.com",
               "lines": [
                   "5 Main Street"
               ],
               "name": "Jane Buyer",
               "phone": "1234567890",
               "postcode": "6012",
               "state": "WLG",
               "suburb": "Karori",
               "country": "NZ"
           },
           "items": [
               {
                   "classification_type": "OTHER",
                   "commercial_value": true,
                   "description_of_other": "This is a classification description",
                   "export_declaration_number": "1234567890",
                   "import_reference_number": "111222333",
                   "item_contents": [
                       {
                           "country_of_origin": "AU",
                           "description": "description",
                           "sku": "ABC1243567",
                           "quantity": 1,
                           "tariff_code": "123456",
                           "value": 55.55,
                           "weight": 0.5,
                           "item_contents_reference": "IC123456"
                       }
                   ],
                   "item_description": "This is a description of the item",
                   "item_reference": "TD1234567",
                   "length": 10,
                   "height": 10,
                   "weight": 2,
                   "product_id": "PTI8",
                   "width": 10
               }
           ]
       }
   ]
}
```
Note: This request can be invoked from createInternationalShipment() method.

### Sample Response
HTTP Response Code: 201
```
  {
    "shipments": [
        {
            "shipment_id": "9KsK1UlJwt0AAAFthuwFqUgH",
            "shipment_reference": "shipment reference 1",
            "shipment_creation_date": "2019-09-19T15:39:01+10:00",
            "shipment_modified_date": "2019-09-19T15:39:01+10:00",
            "items": [
                {
                    "weight": 2.000,
                    "item_id": "9RMK1UlJaCYAAAFtiewFqUgH",
                    "item_reference": "item1",
                    "tracking_details": {
                        "article_id": "00793527797000011978",
                        "consignment_id": "GDX5000071"
                    },
                    "product_id": "IC10",
                    "item_summary": {
                        "total_cost": 2.80,
                        "total_cost_ex_gst": 2.80,
                        "total_gst": 0.00,
                        "status": "Created"
                    },
                    "item_contents": []
                }
            ],
            "options": {},
            "shipment_summary": {
                "total_cost": 2.83,
                "total_cost_ex_gst": 2.57,
                "fuel_surcharge": 0.03,
                "total_gst": 0.26,
                "status": "Created",
                "tracking_summary": {
                    "Created": 1
                },
                "number_of_items": 1
            },
            "movement_type": "DESPATCH",
            "charge_to_account": "0006519231"
        }
    ]
}
```
HTTP Response Code: 400
```
 {
    "errors": [
        {
            "code": "44003",
            "name": "DANGEROUS_GOODS_NOT_SUPPORTED_BY_PRODUCT_ERROR",
            "field": "shipments[0].items[0]",
            "message": "The product T28S specified in an item has indicated that dangerous goods will be included in the parcel, however, the product does not allow dangerous goods to be sent using the service.  Please choose a product that allows dangerous goods to be included within the parcel to be sent."
        }
    ]
}
```
