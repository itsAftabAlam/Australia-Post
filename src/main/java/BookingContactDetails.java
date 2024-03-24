public class BookingContactDetails {
    private String name;
    private String business_name;
    private String phone;
    private String email;

    // Constructor, getters, and setters

    public BookingContactDetails(String name, String business_name, String phone, String email) {
        this.name = name;
        this.business_name = business_name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}