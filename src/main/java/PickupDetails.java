import java.util.List;

public class PickupDetails {
    private String product_id;
    private String pickup_reference;
    private String pickup_date;
    private String pickup_starttime;
    private String pickup_endtime;
    private String pickup_instructions;
    private PickupAddress from;
    private boolean contains_dangerous_goods;
    private String consignment_id;
    private List<ParcelDetail> parcel_details;
    private BookingContactDetails booking_contact_details;

    // Constructor, getters, and setters

    public PickupDetails(String product_id, String pickup_reference, String pickup_date, String pickup_starttime, String pickup_endtime, String pickup_instructions, PickupAddress from, boolean contains_dangerous_goods, String consignment_id, List<ParcelDetail> parcel_details, BookingContactDetails booking_contact_details) {
        this.product_id = product_id;
        this.pickup_reference = pickup_reference;
        this.pickup_date = pickup_date;
        this.pickup_starttime = pickup_starttime;
        this.pickup_endtime = pickup_endtime;
        this.pickup_instructions = pickup_instructions;
        this.from = from;
        this.contains_dangerous_goods = contains_dangerous_goods;
        this.consignment_id = consignment_id;
        this.parcel_details = parcel_details;
        this.booking_contact_details = booking_contact_details;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPickup_reference() {
        return pickup_reference;
    }

    public void setPickup_reference(String pickup_reference) {
        this.pickup_reference = pickup_reference;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getPickup_starttime() {
        return pickup_starttime;
    }

    public void setPickup_starttime(String pickup_starttime) {
        this.pickup_starttime = pickup_starttime;
    }

    public String getPickup_endtime() {
        return pickup_endtime;
    }

    public void setPickup_endtime(String pickup_endtime) {
        this.pickup_endtime = pickup_endtime;
    }

    public String getPickup_instructions() {
        return pickup_instructions;
    }

    public void setPickup_instructions(String pickup_instructions) {
        this.pickup_instructions = pickup_instructions;
    }

    public PickupAddress getFrom() {
        return from;
    }

    public void setFrom(PickupAddress from) {
        this.from = from;
    }

    public boolean isContains_dangerous_goods() {
        return contains_dangerous_goods;
    }

    public void setContains_dangerous_goods(boolean contains_dangerous_goods) {
        this.contains_dangerous_goods = contains_dangerous_goods;
    }

    public String getConsignment_id() {
        return consignment_id;
    }

    public void setConsignment_id(String consignment_id) {
        this.consignment_id = consignment_id;
    }

    public List<ParcelDetail> getParcel_details() {
        return parcel_details;
    }

    public void setParcel_details(List<ParcelDetail> parcel_details) {
        this.parcel_details = parcel_details;
    }

    public BookingContactDetails getBooking_contact_details() {
        return booking_contact_details;
    }

    public void setBooking_contact_details(BookingContactDetails booking_contact_details) {
        this.booking_contact_details = booking_contact_details;
    }
}