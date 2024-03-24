public class PickupAddress {
    private String suburb;
    private String postcode;
    private String state;
    private String business_name;
    private String pickup_name;
    private String pickup_area;
    private boolean private_address;
    private String phone;

    // Constructor, getters, and setters


    public PickupAddress(String suburb, String postcode, String state, String business_name, String pickup_name, String pickup_area, boolean private_address, String phone) {
        this.suburb = suburb;
        this.postcode = postcode;
        this.state = state;
        this.business_name = business_name;
        this.pickup_name = pickup_name;
        this.pickup_area = pickup_area;
        this.private_address = private_address;
        this.phone = phone;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getPickup_name() {
        return pickup_name;
    }

    public void setPickup_name(String pickup_name) {
        this.pickup_name = pickup_name;
    }

    public String getPickup_area() {
        return pickup_area;
    }

    public void setPickup_area(String pickup_area) {
        this.pickup_area = pickup_area;
    }

    public boolean isPrivate_address() {
        return private_address;
    }

    public void setPrivate_address(boolean private_address) {
        this.private_address = private_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}