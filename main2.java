import java.io.File;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // 1. Дана json строка { {
        // "фамилия":"Иванов","оценка":"5","предмет":"Математика"},
        // {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
        // {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}}
        // Задача написать метод(ы), который распарсить строку и выдаст ответ
        // вида: Студент Иванов получил 5 по предмету Математика. Студент Петрова
        // получил 4 по предмету Информатика.
        // Студент Краснов получил 5 по предмету Физика. Используйте StringBuilder для
        // подготовки ответа

        // 2. Создать метод, который запишет результат работы в файл
        // Обработайте исключения и запишите ошибки в лог файл

        String jsonStr = "{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},\n" +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},\n" +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}";

        String filePath = "file.txt";
        String s1 = schoolClildren(jsonStr).toString();
        writeToFile(s1, filePath);

    }

    static StringBuilder schoolClildren(String name) {

        String value[] = new String[] { "Студент", "получил", "по предмету" };
        String strArray[] = name.split(",");

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0, j = 0; i < strArray.length; i++, j++) {
            if (j == value.length) {
                j = 0;
                stringBuilder.append("\n");
            }
            String str = strArray[i].replaceAll("[^а-яА-Я0-9:]", "");
            String strArray2[] = str.split(":");
            stringBuilder.append(value[j] + " " + strArray2[1] + " ");
        }
        return stringBuilder;
    }

    static void writeToFile(String str, String filePath) {
        try {
            Logger logger = Logger.getAnonymousLogger();
            FileHandler fileHandler = new FileHandler("log.txt", true);
            logger.addHandler(fileHandler);
            try (FileWriter writer = new FileWriter(filePath, false)) {
                writer.write(str);
                writer.flush();
                logger.log(Level.INFO, "Запись успешно создана");
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.WARNING, e.getMessage());
            }
            fileHandler.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}