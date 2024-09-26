import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
//Разработать собственное хранилище данных
// для элементов выбранной предметной области
//Клиент: id, name, sex, age, AOV (average order value)
// PUT (1, capybara, female, 17, 2000)

    //UPDATE (1, capybara, female, 17, 2000)
//Выбрать существующий или создать новый

    public static void printInfo(PrintStream printStream) {
        printStream.print(Config.INFO_MESSAGE_DATABASE); //Информация о бд
        printStream.println(Config.CORRECT_MESSAGE);
        printStream.println(Config.SYNTAX_MESSAGE);
    }


    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        printInfo(System.out);
        SrvFile.initializationDate();

        while (true) {

            if (!SrvInputValid.input(scan, System.out)) {
                break;
            }
            // System.out.println(Arrays.toString(str));


        }
    }
}