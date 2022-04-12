package zaraev.multicarta.ru;



import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest {

    @Test
    public void findStringWithIdFromStringArrayTest() {
        String stringWithId = ",T1007,";
        String[] arrayWithId = new String[]{
                "T1005, ,T1004,",
               " T1008, ,T1004, ,T1005, ",
                "T1009, ,T1005, ",
                "T1004, ,T1006, T1007, ",
                " T1006, ,T1001, ,",
                " T1706, ,T1081, , T1509,T1007,",
                "T1005, ,T1004, T1008, ,,,T1004, ,T1005, T1009, T1045,T1005, T1004, ,T1006, ,,, ,,T1006, ,T1001,, ,,"};

        List<String> expectedResult = Arrays.asList(("T1004, ,T1006, T1007, "), (" T1706, ,T1081, , T1509,T1007,"));

        List<String> actualResult = Service.findStringWithIdFromStringArray(stringWithId, arrayWithId);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void writeArrayListToFileCsvTest() {
        List<String> listWithId = Arrays.asList(("T1004, ,T1006, T1007, "), (" T1706, ,T1081, , T1509,T1007,"));

        Service.writeArrayListToFileCsv(listWithId, "test");
        File actualResultFile = new File("test");

        assertEquals("test", actualResultFile.getName());
    }
}