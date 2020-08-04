package machine.app;

import machine.io.ConsolePrinter;
import machine.io.DataReader;
import machine.model.*;

class MachineControl {

    private DataReader dataReader = new DataReader();
    private ConsolePrinter printer = new ConsolePrinter();
    private CoffeeMaker machine = new CoffeeMaker();

    public void run() {
        Action action;

        do {
            action = getAction();
            switch (action) {
                case BUY:
                    buyCoffee();
                    break;
                case FILL:
                    machine.fillMachine();
                    break;
                case TAKE:
                    machine.takeMoney();
                    break;
                case REMAINING:
                    printMachineStatus(machine);
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("There is no such option, please try again");
            }
        } while (action != Action.EXIT);
    }

    private void buyCoffee() {
        Option option;
        printer.printLine("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                " back - to main menu:");
        option = Option.createFromString(dataReader.getString());

        switch (option) {
            case ESPRESSO:
                CoffeeType espresso = new Espresso();
                machine.makeCoffee(espresso);
                break;
            case LATTE:
                CoffeeType latte = new Latte();
                machine.makeCoffee(latte);
                break;
            case CAPPUCCINO:
                CoffeeType cappuccino = new Cappuccino();
                machine.makeCoffee(cappuccino);
                break;
            case BACK:
                return;
        }
    }

    private Action getAction() {
        boolean actionOk = false;
        Action result = null;
        do {
            printActions();
            //buy, BUY
            String action = dataReader.getString().toUpperCase();
            try {
                result = Action.valueOf(action);
                actionOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("There is no action like: " + action +
                        "\nPlease try again.\n");
            }
        } while (!actionOk);
        return result;
    }

    private void exit() {
        dataReader.close();
        printer.printLine("Bye bye!");
    }

    private void printMachineStatus(CoffeeMaker machine) {
        printer.printLine(machine.toString());
    }

    private void printActions() {
        printer.printLine("Write action:");
        for (Action action : Action.values()) {
            printer.printLine(action.toString());
        }
    }

    private enum Option {
        ESPRESSO("1"),
        LATTE("2"),
        CAPPUCCINO("3"),
        BACK("back");

        private final String value;

        Option(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        static Option createFromString(String option) {
            Option[] values = values();
            for (Option opt : values) {
                if (opt.getValue().equals(option))
                    return opt;
            }
            return null;
        }
    }

    private enum Action {
        BUY("buy", " - buy one of our wonderful coffee"),
        FILL("fill", " - fill out all the supplies"),
        TAKE("take", " - take money from the coffee machine"),
        REMAINING("remaining", " - show all the resources"),
        EXIT("exit", " - switch off the machine");

        private final String action;
        private final String description;

        Action(String action, String description) {
            this.action = action;
            this.description = description;
        }

        public String getName() {
            return action;
        }

        public String getDescription() {
            return description;
        }

        static Action createFromAction(String action) {
            Action[] values = values();
            for (Action act : values) {
                if (act.getName().equals(action))
                    return act;
            }
            return null;
        }

        @Override
        public String toString() {
            return action + " " + description;
        }
    }
}