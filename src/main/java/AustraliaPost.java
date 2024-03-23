import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AustraliaPost {
    private String API_KEY = null;

    AustraliaPost(String key){
        this.API_KEY = key;
    }
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
}
