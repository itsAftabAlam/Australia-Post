import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AustraliaPost {
    private String API_KEY = null;

    private static class Shipment {
        private List<Item> shipments;

        public Shipment(List<Item> shipments) {
            this.shipments = shipments;
        }

        public List<Item> getShipments() {
            return shipments;
        }

        public void setShipments(List<Item> shipments) {
            this.shipments = shipments;
        }
    }

    AustraliaPost(String key){
        this.API_KEY = key;
    }

    //there are several cases here
    //and for each case there is a separate api
    //the method below quotes the cost for only the standard domestic parcel
    //similar other variations can be added for the other 3 apis that handle cost for international letter, international parcel, domestic letter
    public void getQuote(int fromPostalCode, int toPostalCode, int length, int width, int height, double weight, String serviceCode){
        String urlString = String.format("https://digitalapi.auspost.com.au/postage/parcel/domestic/calculate?" +
                        "from_postcode=%s&" +
                        "to_postcode=%s&" +
                        "length=%d&" +
                        "width=%d&" +
                        "height=%d&" +
                        "weight=%.2f&" +
                        "service_code=%s",
                fromPostalCode, toPostalCode, length, width, height, weight, serviceCode);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("auth-key", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                //Raw JSON
                System.out.println(responseBody);
//                Can be used at later stage for JSON mapping
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode jsonNode = objectMapper.readTree(responseBody);
//                System.out.println(jsonNode.toString());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //this is used to track a particular shipment
    public void track(String username, String password, String accountNumber, String trackingIds){
        // Construct authentication header
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // Construct request URI
        String url = "https://digitalapi.auspost.com.au/shipping/v1/track?tracking_ids=" + trackingIds;

        // Create HTTP client and request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("authorization", "Basic " + encodedAuth)
                    .header("account-number", accountNumber)
                    .GET()
                    .build();

        // Send request and handle response
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println(response.body());
            }
            else {
                throw new Exception("Error: " + response.statusCode() + " - " + response.body());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //this method is used for creating a domestic shipment
    public void createShipment(String username, String password, String accountNumber, List<Item> items) throws Exception {
        // Construct authentication header
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // Construct request URI
        String url = "https://digitalapi.auspost.com.au/shipping/v1/shipments";

        // Create shipment object
        Shipment shipment = new Shipment(items);

        // Convert shipment object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String jsonBody = objectMapper.writeValueAsString(shipment);

        // Create HTTP client and request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("authorization", "Basic " + encodedAuth)
                .header("account-number", accountNumber)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Send request and handle response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("Shipment created successfully: " + response.body());
        } else {
            throw new Exception("Error: " + response.statusCode() + " - " + response.body());
        }
    }

    //this method is used for creating international shipment
    public void createInternationalShipment(String username, String password, String accountNumber, InternationalShipment shipmentInfo) throws Exception {
        // Construct authentication header
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // Construct request URI
        String url = "https://digitalapi.auspost.com.au/shipping/v1/shipments";

        // Convert shipment info object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String jsonBody = objectMapper.writeValueAsString(shipmentInfo);

        // Create HTTP client and request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("authorization", "Basic " + encodedAuth)
                .header("account-number", accountNumber)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Send request and handle response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("International shipment created successfully: " + response.body());
        } else {
            throw new Exception("Error: " + response.statusCode() + " - " + response.body());
        }
    }

    //this method is used to create a pickup
    public void createPickup(String username, String password, String accountNumber, PickupDetails pickupDetails) throws Exception {
        // Construct authentication header
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // Construct request URI
        String url = "https://digitalapi.auspost.com.au/shipping/v1/pickups/adhoc";

        // Convert pickup details object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String jsonBody = objectMapper.writeValueAsString(pickupDetails);

        // Create HTTP client and request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("authorization", "Basic " + encodedAuth)
                .header("account-number", accountNumber)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Send request and handle response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("Adhoc pickup created successfully: " + response.body());
        } else {
            throw new Exception("Error: " + response.statusCode() + " - " + response.body());
        }
    }
}
