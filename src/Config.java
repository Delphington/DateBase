public class Config {
    private Config() {
    }


    public final static String INFO_DATABASE = "#### DateBase Client ####\n" +
            "===============================\n" +
            "| id | name | sex | age | AOV | \n"
            +"==============================\n";
    public final static String TYPE_VALUE_DATABASE = "---------- TYPE Dates ---------- \n" +
            "id - Integer NOT NULL \n"
            + "Name - String (буквы Англ и Русские) NOT NULL\n"
            + "sex female/male NOT NULL \n"
            + "age - Integer NOT NULL\n"
            + "AOV - Integer NOT NULL \n";

    public final static String RULE_COMMAND  =
            "----------- INFO COMMAND  --------- \n" +
                    "GET - получение элемент по ID \n" +
                    "PUT - добавление элемента, если его нет \n" +
                    "UPDATE - обновление элемента, если он есть \n" +
                    "DELETE - удаление элемента, если он есть \n" +
                    "CREATE - создание нового файла, если его нету и добавление предыдущих данных \n" +
                    "[E] - выход \n" +
                    "[P] - вывод всей БД \n" ;

    public final static String SYNTAX_DATABASE =
            "----------- SYNTAX --------- \n" +
                    "GET id \n" +
                    "PUT (id, name, sex, age, AVO) \n" +
                    "UPDATE (id, name, sex, age, AVO) \n" +
                    "DELETE id \n" +
                    "CREATE (file name) \n" +
                    "[E] - exit \n" +
                    "-------------------------------------------------";


    public final static String INPUT_MESSAGE = "Введите действие: ";
    public final static String WARING_MESSAGE = "Синтаксическая ошибка ввода: ";


    public static String FILE_PATH = "src/info.txt";
}

