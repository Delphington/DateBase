import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SrvFile implements Action, SrvSetting {
    private int id;
    private String body;


    static Map<Integer, String> ourDate = new HashMap<>();

    //Изначальный перенос всех данных в map
    public static void initializationDate() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(":");
                StringBuilder sb = new StringBuilder();
                //собираем данные в строку, разделяя :
                for (int i = 1; i < temp.length; i++) {
                    sb.append(temp[i]);
                    if (temp.length - 1 != i) {
                        sb.append(":");
                    }
                }
                ourDate.put(Integer.parseInt(temp[0]), sb.toString());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    //Сохранения информации в файл
    private static void saveDate() {
        try {
            FileWriter writer = new FileWriter(Config.FILE_PATH);

            for (Map.Entry<Integer, String> entry : ourDate.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
            writer.close(); //  закрыть файл
            System.out.println("Информация записана в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    //Создание нового файла и запись туда всей информации
    private static void createNewFileAndSave(String fileName) {
        try {
            File file = new File(fileName);

            // Если файл не существует, создаем его
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Map.Entry<Integer, String> entry : ourDate.entrySet()) {
                    writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось создать файл" + e.getMessage());
        }
    }


    //Вывод всей информации
    public static void printInfo() {
        System.out.println("----- Вывод -------");
        for (Map.Entry<Integer, String> entry : ourDate.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    //UPDATE(31,CAPY, CAPY, 17,21313)

    private void localParseToIdAndBody(String s) {
        StringBuilder sb_id = new StringBuilder();
        StringBuilder sb_body = new StringBuilder();
        int start_ID = s.indexOf(":");
        for (int i = 0; i < s.length(); i++) {
            if (i < start_ID) {
                sb_id.append(s.charAt(i));
            } else if (i > start_ID) {
                sb_body.append(s.charAt(i));
            }
        }

        id = Integer.parseInt(sb_id.toString());
        body = sb_body.toString();
    }


    @Override
    public void PUT(String s) {
        localParseToIdAndBody(s);

        //Строки не меняю так как нет смысла в UPDATE
        if (ourDate.get(id) != null) {
            System.out.println("Error: данные с таким id уже есть");
        } else {
            ourDate.put(id, body);
            saveDate();
        }

    }

    @Override
    public void GET(String s) {
        id = Integer.parseInt(s);
        if (ourDate.get(id) == null) {
            System.out.println("Error: такого элемента нет");
        } else {
            System.out.println("GET DATE: " + ourDate.get(id));
        }
    }

    @Override
    public void DELETE(String s) {
        id = Integer.parseInt(s);
        if (ourDate.get(id) == null) {
            System.out.println("Error: такого элемента нет");
        } else {
            ourDate.remove(id);
            saveDate();
        }
    }

    @Override
    public void UPDATE(String s) {
        localParseToIdAndBody(s);
        if (ourDate.get(id) == null) {
            System.out.println("Error: такого элемента нет для обновления");
        } else {
            ourDate.put(id, body);
            saveDate();
        }
    }

    @Override
    public void CREATE_FILE(String filename) {
        Config.FILE_PATH = "src/" + filename + ".txt";
        //System.out.println(Config.FILE_PATH);
        createNewFileAndSave(Config.FILE_PATH);
    }
}
