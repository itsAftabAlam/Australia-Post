// this class is used for description of international address
public class Address {
    private String country;
    private String email;
    private String name;
    private String lines;
    private String suburb;
    private String state;
    private String postcode;
    private String phone;

    // Constructor, getters, and setters

    public Address(String country, String email, String name, String lines, String suburb, String state, String postcode, String phone) {
        this.country = country;
        this.email = email;
        this.name = name;
        this.lines = lines;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
