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
        String username2 = "your_username";
        String password2 = "your_password";
        String accountNumber2 = "your_account_number";
        List<Item> items = List.of(
                new Item("SKU-1", "ABC000128B4C5", "ABC000128", "T28S", 5, 4.55, 0.45),
                new Item("SKU-3", "ABC000128B4C6", "ABC000128", "T28S", 4, 3.64, 0.36),
                new Item("SKU-2", "ABC000128B4C7", "ABC000128", "T28S", 4, 3.64, 0.36)
        );
        try {
            shipment.createShipment(username2, password2, accountNumber2, items);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //sample on how to create a international shipment
        AustraliaPost shipmentInternational = new AustraliaPost(key);
        String username3 = "your_username";
        String password3 = "your_password";
        String accountNumber3 = "your_account_number";
        InternationalShipment shipmentInfo = new InternationalShipment(
                "shipment reference 1",
                new Address("AU", "larry.citizen@citizen.com", "Larry Smith", "123 Main Street", "Melbourne", "VIC", "3000", "0412345678"),
                new Address("NZ", "jane.buyer@citizen.com", "Jane Buyer", "5 Main Street", "Karori", "WLG", "6012", "1234567890"),
                List.of(new ShipmentItem("OTHER", true, "This is a classification description", "1234567890", "111222333",
                        List.of(new ItemContent("AU", "description", "ABC1243567", 1, "123456", 55.55, 0.5, "IC123456")),
                        "This is a description of the item", "TD1234567", 10, 10, 10, 2, "PTI8"))
        );
        try {
            shipmentInternational.createInternationalShipment(username3, password3, accountNumber3, shipmentInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //sample on how to create a pickup
        AustraliaPost pickup = new AustraliaPost(key);
        String username4 = "your_username";
        String password4 = "your_password";
        String accountNumber4 = "your_account_number";
        PickupDetails pickupDetails = new PickupDetails(
                "EXP",
                "1444444444442345678",
                "2018-05-22",
                "15:30:00",
                "16:00:00",
                "heavy items",
                new PickupAddress("Fitzroy", "3065", "VIC", "Smith Pty Ltd", "Jane Smith", "reception on level 10", true, "0412222222"),
                false,
                "EXP1234567890",
                List.of(
                        new ParcelDetail("CTN", "test carton details", 10, 19, 30, 2, 17, 10),
                        new ParcelDetail("SAT", "test satchel details", 20, 29, 15, 5, 7, 3)
                ),
                new BookingContactDetails("John Smith", "Smith Pty Ltd", "0413333333", "john.smith@smith.com")
        );
        try {
            pickup.createPickup(username4, password4, accountNumber4, pickupDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
