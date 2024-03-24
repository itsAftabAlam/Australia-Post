import java.util.List;
//describes an international shipment
public class InternationalShipment {
    private String shipment_reference;
    private Address from;
    private Address to;
    private List<ShipmentItem> items;

    // Constructor, getters, and setters

    public InternationalShipment(String shipment_reference, Address from, Address to, List<ShipmentItem> items) {
        this.shipment_reference = shipment_reference;
        this.from = from;
        this.to = to;
        this.items = items;
    }

    public String getShipment_reference() {
        return shipment_reference;
    }

    public void setShipment_reference(String shipment_reference) {
        this.shipment_reference = shipment_reference;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public Address getTo() {
        return to;
    }

    public void setTo(Address to) {
        this.to = to;
    }

    public List<ShipmentItem> getItems() {
        return items;
    }

    public void setItems(List<ShipmentItem> items) {
        this.items = items;
    }
}