public class ParcelDetail {
    private String packaging_type;
    private String comments;
    private double length;
    private double height;
    private double width;
    private int count;
    private double weight;
    private int heaviest_item;

    // Constructor, getters, and setters

    public ParcelDetail(String packaging_type, String comments, double length, double height, double width, int count, double weight, int heaviest_item) {
        this.packaging_type = packaging_type;
        this.comments = comments;
        this.length = length;
        this.height = height;
        this.width = width;
        this.count = count;
        this.weight = weight;
        this.heaviest_item = heaviest_item;
    }

    public String getPackaging_type() {
        return packaging_type;
    }

    public void setPackaging_type(String packaging_type) {
        this.packaging_type = packaging_type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeaviest_item() {
        return heaviest_item;
    }

    public void setHeaviest_item(int heaviest_item) {
        this.heaviest_item = heaviest_item;
    }
}