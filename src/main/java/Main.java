import java.util.List;

public class Main {
    public static void main(String[] args) {
        //starting point of program
        //initiate/manage orders from here
        String key = "your_api_key";
        AustraliaPost order1 = new AustraliaPost(key);
//        order.getQuote(2000,3000,1,1,1,10.5,"AUS_PARCEL_REGULAR");

        //sample on how to track
        AustraliaPost trackOrder = new AustraliaPost(key);
        String username = "your_username";
        String password = "your_password";
        String accountNumber = "your_account_number";
        String trackingIds = "132131,1414245";
        trackOrder.track(username,password,accountNumber,trackingIds);


        //sample on how to create a shipment
        AustraliaPost shipment = new AustraliaPost(key);
        String username = "your_username";
        String password = "your_password";
        String accountNumber = "your_account_number";
        List<Item> items = List.of(
                new Item("SKU-1", "ABC000128B4C5", "ABC000128", "T28S", 5, 4.55, 0.45),
                new Item("SKU-3", "ABC000128B4C6", "ABC000128", "T28S", 4, 3.64, 0.36),
                new Item("SKU-2", "ABC000128B4C7", "ABC000128", "T28S", 4, 3.64, 0.36)
        );
        try {
            shipment.createShipment(username, password, accountNumber, items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
