package net.volgatech;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SpreadsheetEngine spreadsheet = new SpreadsheetEngine();
        Scanner input = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("> ");
                String line = input.nextLine();
                String[] command  =  line.split(" ");
                switch(command[0].toLowerCase()) {
                    case "display": {
                        spreadsheet.display();
                        break;
                    }
                    case "set": {
                        spreadsheet.set(command[1], command[2]);
                        break;
                    }
                    case "setformula": {
                        spreadsheet.setFormula(command[1], line.replace(command[0] + " ", "").replace(command[1] + " ", ""));
                        break;
                    }
                    case "break": {
                        return;
                    }
                    default: {
                        System.out.println("Invalid command");
                    }
                }
            }
        }
        catch(StackOverflowError exc) {
           System.out.println("Circular references are noticed");
        }
    }
}
