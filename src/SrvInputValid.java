import java.io.PrintStream;
import java.util.Scanner;

public class SrvInputValid {

    private static String ourStringSmall;

    static SrvFile srvFile = new SrvFile();

    private SrvInputValid() {
    }

    private static boolean checkCharExit(String s) {
        return s.length() == 1 && Character.isLetter(s.charAt(0))
                //проверка на кириллицу и латиницу
                && (s.equalsIgnoreCase("E") || s.equalsIgnoreCase("Е"));
    }

    private static boolean checkCharPrint(String s) {
        return s.length() == 1 && Character.isLetter(s.charAt(0))
                //проверка на кириллицу и латиницу
                && (s.equalsIgnoreCase("P") || s.equalsIgnoreCase("Р"));
    }


    private static int countChar(String str, char targetChar) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                count++;
            }
        }
        return count;
    }

    public static boolean isValidString(String str) {
        // Регулярное выражение для проверки
        return str.matches("^[a-zA-Zа-яА-ЯёЁ]+$");
    }

    public static boolean isValidInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false; // Если не удалось преобразовать, значит, это не число
        }
    }


    //Функция нужна для UPDATE и PUT чтобы собрать все данные в один String
    private static void localParseToString(String[] arr) {
        StringBuilder temps = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            temps.append(arr[i].trim());
            if (i != arr.length - 1) {
                temps.append(":");
            }
        }
        ourStringSmall = temps.toString();
    }

    private static boolean checkPutAndUpdate(String str) {
        if (countChar(str, '(') == 1 && countChar(str, ')') == 1) {
            StringBuilder stringBuilder = new StringBuilder();
            //Получаем данные внутри скобок
            for (int i = str.indexOf('(') + 1; i < str.indexOf(')'); i++) {
                stringBuilder.append(str.charAt(i));
            }
            //массив данных
            String[] arr = stringBuilder.toString().split(",");

            //Если Put не полный данных
            if (arr.length != 5) {
                return false;
            }
            //Проверка на корреткность данных
            if (isValidInteger(arr[0])
                    && isValidString(arr[1].trim())
                    && isValidString(arr[2].trim())
                    && isValidInteger(arr[3].trim())
                    && isValidInteger(arr[4].trim())) {

                localParseToString(arr); //собираем данные в наш string

                return true;
            }
            return false;
        }
        return false;
    }


    private static boolean checkGet(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        //Отбираем данные
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != 'G' && str.charAt(i) != 'E' && str.charAt(i) != 'T') {
                stringBuilder.append(str.charAt(i));
            }
        }
        if (isValidInteger(stringBuilder.toString())) {
            ourStringSmall = stringBuilder.toString().trim();
            return true;
        }
        return false;
    }

    private static boolean checkDelete(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != 'D' &&
                    str.charAt(i) != 'E' &&
                    str.charAt(i) != 'L'
                    && str.charAt(i) != 'T') {
                stringBuilder.append(str.charAt(i));
            }
        }
        if (isValidInteger(stringBuilder.toString().trim())) {
            ourStringSmall = stringBuilder.toString().trim();
            return true;
        }
        return false;
    }

    private static boolean checkCreate(String str) {
        if (countChar(str, '(') == 1 && countChar(str, ')') == 1) {
            StringBuilder stringBuilder = new StringBuilder();
            //Получаем данные
            for (int i = str.indexOf('(') + 1; i < str.indexOf(')'); i++) {
                stringBuilder.append(str.charAt(i));
            }
            if (isValidString(stringBuilder.toString().trim())) {
                ourStringSmall = stringBuilder.toString(); //наши данные
                return true;
            }
        }
        return false;

    }


    public static boolean input(Scanner scan, PrintStream printStream) {
        while (true) {
            printStream.print(Config.INPUT_MESSAGE);
            String line = scan.nextLine().trim();
            line = line.replaceAll("\\s", ""); //Удаление всех пробелов
            if (!line.isEmpty()) {
                //Выход [E]exit
                if (line.length() == 1 && checkCharExit(line)) {
                    printStream.println("The End");
                    return false;
                    //вывод всей бд
                } else if (line.length() == 1 && checkCharPrint(line)) {
                    srvFile.printInfo();
                    break;
                } else if (line.contains("PUT") && checkPutAndUpdate(line)) {
                    srvFile.PUT(ourStringSmall); //TODO: сделанно
                    break;
                } else if (line.contains("GET") && checkGet(line)) {
                    srvFile.GET(ourStringSmall); //TODO: сделанно
                    break;
                } else if (line.contains("UPDATE") && checkPutAndUpdate(line)) {
                    srvFile.UPDATE(ourStringSmall);
                    break;
                } else if (line.contains("DELETE") && checkDelete(line)) {
                    srvFile.DELETE(ourStringSmall); //TODO: сделанно
                    break;
                } else if (line.contains("CREATE") && checkCreate(line)) {
                    srvFile.CREATE_FILE(ourStringSmall);

                    break;
                }
            }

            printStream.println(Config.WARING_MESSAGE);
        }
        return true;
    }
}