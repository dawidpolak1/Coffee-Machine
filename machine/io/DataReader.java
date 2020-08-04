package machine.io;

import java.util.Scanner;

public class DataReader {
    private Scanner input = new Scanner(System.in);
    private ConsolePrinter printer;

    public int getInt() {
        try {
            return input.nextInt();
        } finally {
            input.nextLine();
        }
    }

    public String getString() { return  input.nextLine(); }

    public void close() {
        input.close();
    }
}
