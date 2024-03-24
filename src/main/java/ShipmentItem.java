import java.util.List;
//describes an international item
public class ShipmentItem {
    private String classification_type;
    private boolean commercial_value;
    private String description_of_other;
    private String export_declaration_number;
    private String import_reference_number;
    private List<ItemContent> item_contents;
    private String item_description;
    private String item_reference;
    private double length;
    private double height;
    private double width;
    private double weight;
    private String product_id;

    // Constructor, getters, and setters

    public ShipmentItem(String classification_type, boolean commercial_value, String description_of_other, String export_declaration_number, String import_reference_number, List<ItemContent> item_contents, String item_description, String item_reference, double length, double height, double width, double weight, String product_id) {
        this.classification_type = classification_type;
        this.commercial_value = commercial_value;
        this.description_of_other = description_of_other;
        this.export_declaration_number = export_declaration_number;
        this.import_reference_number = import_reference_number;
        this.item_contents = item_contents;
        this.item_description = item_description;
        this.item_reference = item_reference;
        this.length = length;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.product_id = product_id;
    }

    public String getClassification_type() {
        return classification_type;
    }

    public void setClassification_type(String classification_type) {
        this.classification_type = classification_type;
    }

    public boolean isCommercial_value() {
        return commercial_value;
    }

    public void setCommercial_value(boolean commercial_value) {
        this.commercial_value = commercial_value;
    }

    public String getDescription_of_other() {
        return description_of_other;
    }

    public void setDescription_of_other(String description_of_other) {
        this.description_of_other = description_of_other;
    }

    public String getExport_declaration_number() {
        return export_declaration_number;
    }

    public void setExport_declaration_number(String export_declaration_number) {
        this.export_declaration_number = export_declaration_number;
    }

    public String getImport_reference_number() {
        return import_reference_number;
    }

    public void setImport_reference_number(String import_reference_number) {
        this.import_reference_number = import_reference_number;
    }

    public List<ItemContent> getItem_contents() {
        return item_contents;
    }

    public void setItem_contents(List<ItemContent> item_contents) {
        this.item_contents = item_contents;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_reference() {
        return item_reference;
    }

    public void setItem_reference(String item_reference) {
        this.item_reference = item_reference;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}