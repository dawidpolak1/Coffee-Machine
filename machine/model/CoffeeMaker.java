package machine.model;


import machine.io.ConsolePrinter;
import machine.io.DataReader;

public class CoffeeMaker {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader();

    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;

    public CoffeeMaker() {
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        cups = 9;
        money = 550;
    }

    public void makeCoffee(CoffeeType coffeeType) {
        ingredientsValidation(coffeeType);
        validationOk(coffeeType);
    }

    private void validationOk(CoffeeType coffeeType) {
        if (coffeeType.getWaterCost() < water && coffeeType.getMilkCost() < milk
        && coffeeType.getCoffeeBeansCost() < coffeeBeans && cups > 0) {
            printer.printLine("I have enough resources, making you a coffee!\n");
            water -= coffeeType.getWaterCost();
            milk -= coffeeType.getMilkCost();
            coffeeBeans -= coffeeType.getCoffeeBeansCost();
            cups--;
            money += coffeeType.getPrice();
        }
    }

    private void ingredientsValidation(CoffeeType coffeeType) {
        if (coffeeType.getWaterCost() > water) {
            printer.printLine("Sorry, not enough water. Please refill machine\n");
        }
        if (coffeeType.getMilkCost() > milk) {
            printer.printLine("Sorry, not enough milk. Please refill machine\n");
        }
        if (coffeeType.getCoffeeBeansCost() > coffeeBeans) {
            printer.printLine("Sorry, not enough coffee beans. Please refill machine\n");
        }
        if (cups <= 0) {
            printer.printLine("Sorry, not enough disposable cups!\n");
            cups = 0;
        }
    }

    public void fillMachine() {
        printer.printLine("Write how many ml of water do you want to add:");
        water += dataReader.getInt();
        printer.printLine("Write how many ml of milk do you want to add:");
        milk += dataReader.getInt();
        printer.printLine("Write how many grams of coffee beans do you want do add:");
        coffeeBeans += dataReader.getInt();
        printer.printLine("Write how many disposable cups of coffee do you want to add:");
        cups += dataReader.getInt();
    }

    public void takeMoney() {
        printer.printLine("\nI gave you $" + money + "\n");
        money = 0;
    }

    @Override
        public String toString () {
            return "\nThe coffee machine has:\n" +
                    water + " of water\n" +
                    milk + " of milk\n" +
                    coffeeBeans + " of coffee beans\n" +
                    cups + " of disposable cups\n" +
                    money + " of money\n"; }
}