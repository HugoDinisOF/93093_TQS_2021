public class Stock {
    private String name;
    private int quantity;
    public Stock(String n, int q){
        name = n;
        quantity = q;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
