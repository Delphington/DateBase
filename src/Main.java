import java.io.PrintStream;
import java.util.Scanner;

public class Main {
//Разработать собственное хранилище данных
// для элементов выбранной предметной области
//Клиент: id, name, sex, age, AOV (average order value)
// PUT (1, capybara, female, 17, 2000)

    //UPDATE (1, capybara, female, 17, 2000)
//Выбрать существующий или создать новый

    public static void printInfo(PrintStream printStream) {
        printStream.print(Config.INFO_DATABASE); //Информация о бд
        printStream.println(Config.TYPE_VALUE_DATABASE); //виды и колонки
        printStream.println(Config.RULE_COMMAND);

        printStream.println(Config.SYNTAX_DATABASE);
    }


    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        printInfo(System.out);
        SrvFile.initializationDate();

        while (true) {

            if (!SrvInputValid.input(scan, System.out)) {
                break;
            }

        }
    }
}