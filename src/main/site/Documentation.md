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
