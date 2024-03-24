//this class is used to describe a particular item in a shipment
public class Item {
    private String item_reference;
    private String article_id;
    private String consignment_id;
    private String product_id;
    private double total_cost;
    private double total_cost_ex_gst;
    private double total_gst;

    public Item(String item_reference, String article_id, String consignment_id, String product_id, double total_cost, double total_cost_ex_gst, double total_gst) {
        this.item_reference = item_reference;
        this.article_id = article_id;
        this.consignment_id = consignment_id;
        this.product_id = product_id;
        this.total_cost = total_cost;
        this.total_cost_ex_gst = total_cost_ex_gst;
        this.total_gst = total_gst;
    }
}