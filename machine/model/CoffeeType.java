package machine.model;

public abstract class CoffeeType {
    private int waterCost;
    private int milkCost;
    private int coffeeBeansCost;
    private int price;

    public CoffeeType(int waterCost, int milkCost, int coffeeBeansCost, int price) {
        this.waterCost = waterCost;
        this.milkCost = milkCost;
        this.coffeeBeansCost = coffeeBeansCost;
        this.price = price;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public int getMilkCost() {
        return milkCost;
    }

    public int getCoffeeBeansCost() {
        return coffeeBeansCost;
    }

    public int getPrice() {
        return price;
    }
}

