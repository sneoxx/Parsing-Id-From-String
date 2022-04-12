package zaraev.multicarta.ru;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

//    Необходимо написать статичный метод, принимающий в качестве аргументов строку String string
//    и массив строк String[] array и возвращающий список (количество вспомогательных методов не
//    ограничено).

//    В строке String string содержится некоторое количество номеров задач id, разделённых
//    запятыми. Запятых в string может быть любое количество, слева и справа от любого id или
//    запятой могут быть пробелы. Количество id в строке string от 1 до 3, id в string не дублируются,
//    строка string обязательно содержит хотя бы один id
//    Массив строк String[] array состоит из элементов вида string, описанных выше.

//    8. Необходимо найти все элементы массива array, удовлетворяющий условию: все id из строки
//    string содержатся в элементе массива array.
//    Примеры срабатывания условия:
//    если string = “T1004, , T1005, “, то элементы массива “T1005, ,T1004,“ и “ T1008, ,T1004, ,T1005, “
//    удовлетворяют условию, а элемент “T1009, ,T1005, “ – нет;
//    если string = “,T1006,“, то элементы массива “T1004, ,T1006, T1007, “ и “ T1006, ,T1001, ,“
//    удовлетворяют условию, а элемент “T1009, ,T1005, “ – нет;
//    9.Найденные элементы необходимо вернуть в списке или в любой другой коллекции;
//    10. *Сохранить найденные элементы массива в файл csv.
//    11. *При владении темой написать необходимые тесты

@Slf4j
public class Main {

    public static void main(String[] args)  {
        log.debug("Старт программы");

        String example1 = "T1001,, T1002, T1003";
        String example2 = "T1004, , T1005, ";
        String example3 = ",T1006,";

        String[] arrayWithId = new String[]{"T1005, ,T1004,",
                " T1008, ,T1004, ,T1005, ",
                "T1009, ,T1005, ",
                "T1004, ,T1006, T1007, ",
                " T1006, ,T1001, ,",
                "T1005, ,T1004, T1008, ,,,T1404, ,T1505, T1009, T1045,T1075, T1094, ,T1000, T1007, ,,, ,,T1006, ,T1001,, ,,"};

        List<String> result1 = Service.findStringWithIdFromStringArray(example1, arrayWithId);
        if (!result1.isEmpty()) {
            Service.writeArrayListToFileCsv(result1, "Id1.csv");
        }
        List<String> result2 = Service.findStringWithIdFromStringArray(example2, arrayWithId);
        if (!result2.isEmpty()) {
            Service.writeArrayListToFileCsv(result2, "Id2.csv");
        }
        List<String> result3 = Service.findStringWithIdFromStringArray(example3, arrayWithId);
        if (!result3.isEmpty()) {
            Service.writeArrayListToFileCsv(result3, "Id3.csv");
        }

    }
}