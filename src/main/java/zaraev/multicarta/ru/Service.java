package zaraev.multicarta.ru;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Сервисный класс со всеми необходимыми методами для решения задачи
 */
@Slf4j
public class Service {

    public static List<String> findStringWithIdFromStringArray(String stringWithId, String[] arrayWithId) {
        List<String> result = new ArrayList<>();
        String[] arrayFromStringWithId = getStringArrayByRegex(stringWithId);

        for (String arrayWithIdValue : arrayWithId) {
            String[] arrayWithIdFromElement = getStringArrayByRegex(arrayWithIdValue);
            int numberOfIdValuesInString = arrayFromStringWithId.length;
            int numberOfIdValuesInStringInArrayElement = 0;

            for (String arrayWithIdFromElementValue : arrayWithIdFromElement) {

                for (String arrayFromStringWithIdValue : arrayFromStringWithId) {
                    if (arrayWithIdFromElementValue.equals(arrayFromStringWithIdValue)) {
                        numberOfIdValuesInStringInArrayElement++;
                    }
                }
                if (numberOfIdValuesInString == numberOfIdValuesInStringInArrayElement) {
                    result.add(arrayWithIdValue);
                    break;
                }
            }
        }

        if(!result.isEmpty()){
            log.debug("findStringWithIdFromStringArray() Найдены элементы массива array, удовлетворяющий условию: все id из строки {} " +
                    " string содержатся в элементе массива array: {} ",stringWithId, StringUtils.join(result, "|"));
        } else {
            log.debug("findStringWithIdFromStringArray() Элементов массива array, удовлетворяющий условию: все id из строки {} " +
                    " string содержатся в элементе массива array, не найдено: {} ",stringWithId, StringUtils.join(result, "|"));
        }

        return result;
    }

    /**
     * Отфильтровать по регулярному выражению и вернуть массив
     *
     * @param stringWithId - входная строка
     */
    private static String[] getStringArrayByRegex(String stringWithId) {
        String[] resultArray = stringWithId.replaceAll(",", " ").replaceAll("\\s+", " ").trim().split(" ");
        log.debug("getStringArrayByRegex() Строка {} отфильтрована в массив {} :",stringWithId, resultArray);
        return resultArray;
    }

    /**
     * Записать из пришедшего List в файл по указанному имени
     *
     * @param listWithId  - ArrayList из которого будем читать
     * @param fileName - имя файла куда будем записывать
     */
    public static void writeArrayListToFileCsv(List<String> listWithId, String fileName) {
        try (Writer writer = new BufferedWriter(
                new FileWriter(fileName)
        )) {
            for (Object person : listWithId) {
                String tempString = (String) person;
                writer.write(tempString + ";");
            }
        } catch (IOException e) {
            log.error("writeArrayListToFileCsv() Ошибка ввода вывода при записи файла", e);
            System.out.println(e.getMessage());
        }
        log.debug("writeArrayListToFileCsv() Файл {} успешно записан ", fileName);
    }
}