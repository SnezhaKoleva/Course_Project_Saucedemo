package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvHelper {

    public static Object [][] readScvFile(String fileName) throws IOException, CsvException {

        CSVReader csvReader = new CSVReader(new FileReader(fileName));
        List <String[]> csvData = csvReader.readAll();
        Object [][] result=new Object[csvData.size()][csvReader.getMultilineLimit()];
        for (int i = 0; i < csvData.size(); i++) {
            result[i]=csvData.get(i);
        }
        return result;
    }
}
