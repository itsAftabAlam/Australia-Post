//this class description content of a international item
public class ItemContent {
    private String country_of_origin;
    private String description;
    private String sku;
    private int quantity;
    private String tariff_code;
    private double value;
    private double weight;
    private String item_contents_reference;

    // Constructor, getters, and setters

    public ItemContent(String country_of_origin, String description, String sku, int quantity, String tariff_code, double value, double weight, String item_contents_reference) {
        this.country_of_origin = country_of_origin;
        this.description = description;
        this.sku = sku;
        this.quantity = quantity;
        this.tariff_code = tariff_code;
        this.value = value;
        this.weight = weight;
        this.item_contents_reference = item_contents_reference;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTariff_code() {
        return tariff_code;
    }

    public void setTariff_code(String tariff_code) {
        this.tariff_code = tariff_code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getItem_contents_reference() {
        return item_contents_reference;
    }

    public void setItem_contents_reference(String item_contents_reference) {
        this.item_contents_reference = item_contents_reference;
    }
}