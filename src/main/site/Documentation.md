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
|auth-key|Required|Your PAC API Key (HTTP Header)|

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
JSON
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
|authentication|Required|Your username and password as a HTTP Basic Auth hash|
|account-number|Required|A 10 digit number for an Australia Post charge account, or an 8 digit number for a StarTrack account. |

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
JSON
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
JSON
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
JSON
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
## Track Order
### Purpose
This API is used to track a particular shipment. This can be invoked using track() method which requires username, password, account number and tracking IDs.
A sample code to execute the method is also provided.

### Endpoint
HTTP Method : GET
<br>
Resource URL : ```https://digitalapi.auspost.com.au/shipping/v1/track?tracking_ids={tracking_ids}```

### Headers
|Name|Req/Optional|Description|
|----|------------|------------|
|authentication|Required|Your username and password as a HTTP Basic Auth hash|
|account-number|Required|A 10 digit number for an Australia Post charge account, or an 8 digit number for a StarTrack account|

### Required Parameters
| Name           | Req/Optional | Description                                           |
|----------------|--------------|-------------------------------------------------------|
|tracking_ids| Required     | A comma separated list of Australia Post tracking numbers or a comma separated list of StarTrack consignment numbers |


### Sample Request 
```
GET
https://digitalapi.auspost.com.au/shipping/v1/track?tracking_ids=7XX1000,7XX1000634011427
```
Note: This request can be invoked from track() method.

### Sample Response
HTTP Response Code: 200
JSON
```
  {
  "tracking_results": [
    {
      "tracking_id": "7XX1000",
      "errors": [
        {
          "code": "ESB-10001",
          "name": "Invalid tracking ID"
        }
      ]
    },
    {
      "tracking_id": "7XX1000634011427",
      "status": "Delivered",
      "trackable_items": [
        {
          "article_id": "7XX1000634011427",
          "product_type": "eParcel",
          "events": [
            {
              "location": "ALEXANDRIA NSW",
              "description": "Delivered",
              "date": "2014-05-30T14:43:09+10:00"
            },
            {
              "location": "ALEXANDRIA NSW",
              "description": "With Australia Post for delivery today",
              "date": "2014-05-30T06:08:51+10:00"
            },
            {
              "location": "CHULLORA NSW",
              "description": "Processed through Australia Post facility",
              "date": "2014-05-29T19:40:19+10:00"
            },
            {
              "location": "SYDNEY (AU)",
              "description": "Arrived at facility in destination country",
              "date": "2014-05-29T10:16:00+10:00"
            },
            {
              "location": "JOHN F. KENNEDY APT\/NEW YORK (US)",
              "description": "Departed facility",
              "date": "2014-05-26T05:00:00+10:00"
            },
            {
              "location": "JOHN F. KENNEDY APT\/NEW YORK (US)",
              "description": "Departed facility",
              "date": "2014-05-26T05:00:00+10:00"
            },
            {
              "description": "Shipping information approved by Australia Post",
              "date": "2014-05-23T14:27:15+10:00"
            }
          ],
          "status": "Delivered"
        }
      ]
    }
  ]
}
```
HTTP Response Code: 400
JSON
```
{
  "errors": [
    {
      "code": "51101",
      "name": "TOO_MANY_AP_TRACKING_IDS",
      "message": "The request must contain 10 or less AP article ids, consignment ids, or barcode ids."
    }
  ]
}
```
HTTP Response Code: 429
JSON
```
 {
  "errors": [
    {
      "message": "Too many requests",
      "error_code": "API_002",
      "error_name": "Too many requests"
    }
  ]
}
```
#### Error Code Reference 
| Code   | Name                       | Example Message                                                             |
|--------|----------------------------|-----------------------------------------------------------------------------|
| ESB-10001 | Invalid Tracking ID      | One or more submitted consignment ids could not be found.                   |
| ESB-10002 | Product Not Trackable   | The query article or query consignment call identified that the article or consignment respectively is not trackable. |
| ESB-20010 | System Error            | An internal technical error occurred.                                       |
| ESB-20050 | System Error            | An internal technical error occurred.                                       |
| 51100  | TRACKING_IDS_MISSING       | The request must contain at least one tracking id.                           |
| 51101  | TOO_MANY_AP_TRACKING_IDS  | The request must contain 10 or less AP article ids, consignment ids, or barcode ids. |
| 51102  | TOO_MANY_ST_TRACKING_IDS  | The request must contain 10 or less StarTrack consignment ids.              |
| 51103  | TRACKING_IDS_MIX_OF_AP_AND_ST | The request must only contain tracking ids for either StarTrack consignment ids or a mix of AP article ids, consignment ids, or barcode ids. |
| 51104  | INVALID_TRACKING_ID        | One or more submitted tracking ids could not be found.                      |

## Pickup API
### Purpose
This API is allows the client to create an adhoc pickup booking, and is implemented through the use of createPickup() method.
A sample code to execute the method is also provided.

### Endpoint
HTTP Method : POST
<br>
Resource URL : ```https://digitalapi.auspost.com.au/shipping/v1/pickups/adhoc```

### Headers
|Name|Req/Optional|Description|
|----|------------|------------|
|authentication|Required|Your username and password as a HTTP Basic Auth hash|
|account-number|Required|An 8 digit number for a StarTrack account.|

### Sample Request 
```
POST
https://digitalapi.auspost.com.au/shipping/v1/pickups/adhoc
```
JSON
```
 {
    "adhoc_pickup": {
        "product_id": "EXP",
        "pickup_reference": "1444444444442345678",
        "pickup_date": "2018-05-22",
        "pickup_starttime": "15:30:00",
        "pickup_endtime": "16:00:00",
        "pickup_instructions": "heavy items",
        "from": {
            "lines": [
                "Unit 1",
                "123 Smith st"
            ],
            "suburb": "Fitzroy",
            "postcode": "3065",
            "state": "VIC",
            "business_name": "Smith Pty Ltd",
            "pickup_name": "Jane Smith",
            "pickup_area": "reception on level 10",
            "private_address": true,
            "phone": "0412222222"
        },
        "contains_dangerous_goods": false,
        "consignment_id": "EXP1234567890",
        "parcel_details": [
            {
                "packaging_type": "CTN",
                "comments": "test carton details",
                "length": 10,
                "height": 19,
                "width": 30,
                "count": 2,
                "weight": 17,
                "heaviest_item": 10
            },
            {
                "packaging_type": "SAT",
                "comments": "test satchel details",
                "length": 20,
                "height": 29,
                "width": 15,
                "count": 5,
                "weight": 7,
                "heaviest_item": 3
            }
        ]
        "booking_contact_details": {
            "name": "John Smith",
            "business_name": "Smith Pty Ltd",
            "phone": "0413333333",
            "email": "john.smith@smith.com",
        }
    }
}
```
Note: This request can be invoked from createPickup() method.

### Sample Response
HTTP Response Code: 201
JSON
```
  {
    "adhoc_pickups": [
        {
            "booking_id": "1234567"
        }
    ]
}
```
HTTP Response Code: 400
```
 {
    "errors": [
        {
            "code": "400",
            "name": "INVALID_PRODUCT_ID",
            "message": "The product ID ABC is not available",
            "field": "adhocPickup.productId"
        }
    ]
}
```
HTTP Response Code: 412
```
 {
    "errors": [
        {
            "code": "412",
            "name": "STOP_CREDIT",
            "message": "This action can’t be performed due to a charge account error.
                        For further assistance, contact your Credit Officer (details
                        are on your tax invoice)."
        }
    ]
}
```
HTTP Response Code: 500
```
 {
    "errors": [
        {
            "code": "99999",
            "name": "INTERNAL_ERROR",
            "message": "An internal system error has occurred when processing your
                        request.  Please attempt the request again at a later time,
                        or if the issue persists, contact Australia Post Lodgement
                        Support at auspost.com.au/lodgement-techsupport"
        }
    ]
}
```
#### Error Response Reference
| Code   | Name                                | Example Message                                                                        | HTTP Status Code |
|--------|-------------------------------------|----------------------------------------------------------------------------------------|------------------|
| 40002  | JSON_MANDATORY_FIELD_MISSING        | The input request is missing the mandatory field with the name <field name>. Please resubmit the request including the required fields and values. | 400              |
| 49202  | ACCOUNT_NOT_FOUND                   | The account number does not exist.                                                     | 400              |
| 49204  | INVALID_EMAIL_ADDRESS               | The email address is not in the right format.                                           | 400              |
| 49220  | MAX_ADDRESS_LINES_EXCEEDED          | The maximum of 3 address lines has been exceeded.                                       | 400              |
| 49224  | PAST_PICKUP_DATE_NOT_ALLOWED        | The pickup date cannot be in the past.                                                  | 400              |
| 49225  | INVALID_FUTURE_PICKUP_DATE          | The pickup date cannot be more than 30 days in the future.                               | 400              |
| 49226  | PICKUP_DATE_NOT_BUSINESS_DATE       | The pickup date is not a valid business day.                                            | 400              |
| 49228  | PICKUP_TIME_IN_PAST                 | The pickup time cannot be in the past.                                                  | 400              |
| 49229  | PICKUP_TIME_AFTER_FUTURE_CUTOFF     | The pickup start time is after the pickup cutoff time. Please book your pickup for the next business day. | 400          |
| 49240  | PICKUP_START_TIME_IS_IN_PAST        | The pickup start time cannot be in the past.                                             | 400              |
| 49243  | PICKUP_START_TIME_AFTER_PICKUP_END_TIME | The pickup start time is after the pickup end time.                                  | 400              |
| 49244  | PICKUP_END_TIME_HAS_PASSED          | The current time is after the pickup end time.                                          | 400              |
| 49245  | INVALID_PACKAGING_TYPE              | Packaging type <packaging_type> is not a valid value. Please select a valid packaging type from the list of available options: BUN, CTN, JIF, PAL, SAT, SKI, OTH. | |
| 49246  | MAX_ITEM_COUNT_EXCEEDED             | Count per packaging type must be between <min value> and <max value>.                   | 400              |
| 49248  | WEIGHT_LIMIT_EXCEEDED               | Total weight exceeds max limit <max weight> kg.                                          | 400              |
| 49249  | INVALID_WEIGHT                      | The weight <weight> kg must be at least <min weight> kg.                                  | 400              |
| 49250  | INVALID_WEIGHT_FORMAT               | The weight provided exceeds the maximum of 5 integral digits or 1 decimal places.        | 400              |
| 49251  | INVALID_HEIGHT_FORMAT               | The height provided exceeds the maximum of 4 integral digits or 1 decimal places.        | 400              |
| 49252  | INVALID_LENGTH_FORMAT               | The length provided exceeds the maximum of 4 integral digits or 1 decimal places.        | 400              |
| 49253  | INVALID_WIDTH_FORMAT                | The width provided exceeds the maximum of 4 integral digits or 1 decimal places.         | 400              |
| 49254  | INVALID_HEIGHT                      | The height <width> must be between <min height> cm and <max height> cm.                   | 400              |
| 49255  | INVALID_LENGTH                      | The length <length> must be between <min length> cm and <max length> cm.                  | 400              |
| 49256  | INVALID_WIDTH                       | The width <width> must be between <min width> cm and <max width> cm.                     | 400              |
| 49258  | INVALID_PARCEL_DETAILS_COUNT        | A booking request may only contain from <min> to <max> parcel_details blocks.            | 400              |
| 49259  | HEAVIEST_ITEM_EXCEEDS_TOTAL_WEIGHT | Weight of heaviest item <heaviest item> kg exceeds total combined weight of <weight> kg in the parcel details. | 400 |
| 49261  | CURRENT_TIME_AFTER_BOOKING_CUTOFF  | The current time is after the booking cutoff time. For further assistance, please contact your Account Manager. | 400 |
| 49262  | PICKUP_ADDRESS_UNSUPPORTED         | The requested address for pickup is outside of the serviced area.                         | 400              |
| 49270  | PICKUP_VALUE_NOT_SUPPLIED          | A value for a mandatory field was not provided, or is empty. Please review and try again. | 400              |
| 49271  | STOP_CREDIT                        | This action can’t be performed due to a charge account error. For further assistance, contact your Credit Officer (details are on your tax invoice). | 412 |

#### Credit
This documentation is compiled taking reference from the official [Australia Post API docs](https://developers.auspost.com.au/docs/reference). For more information regarding usage of any of the Australia Post API used here refer to this official API docs.

