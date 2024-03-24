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
<br>
Required Parameters
<br>

