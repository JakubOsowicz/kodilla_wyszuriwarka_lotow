public class Journey {
    private Flight first;
    private Flight second;
    private int price;

    public Journey(Flight first, Flight second, int price) {
        this.first = first;
        this.second = second;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Journey: Flight from " + first.getDeparture() + " to " + second.getArrival() + " with stop in " + second.getDeparture() + ", price: " + price;
    }

    public Flight getFirst() {
        return first;
    }

    public void setFirst(Flight first) {
        this.first = first;
    }

    public Flight getSecond() {
        return second;
    }

    public void setSecond(Flight second) {
        this.second = second;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
