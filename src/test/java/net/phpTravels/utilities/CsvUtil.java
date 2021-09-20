package net.phpTravels.utilities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvUtil {

    private final CSVParser csvParser;
    private final List<CSVRecord> records;

    /**
     * reads data from CSV file with first line as header.
     * @param fileName
     */
    public CsvUtil(String fileName){
        String path = String.format("src/test/resources/%s.csv",fileName);
        try {
            FileInputStream csvFile = new FileInputStream(path);
            csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new InputStreamReader(csvFile));
            records = csvParser.getRecords();
            Assert.assertNotNull(csvParser,"file is empty");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<String> getHeader(){
        return csvParser.getHeaderNames();
    }

    public int rowCount(){
        return records.size();
    }

    public int columnCount(){
        return getHeader().size();
    }

    public List<String> getRecordsByHeader(String header){
        return records.stream().map(record->record.get(header))
                .collect(Collectors.toList());
    }

    public List<Map<String,String>> getRecordsAsList(){
        return records.stream().map(CSVRecord::toMap).
                collect(Collectors.toList());
    }

    /**
     * custom static method to convert a list of map into csv format and create file in resources folder
     * @param records
     * @param fileName
     */
    public static void mapToCSV(List<Map<String,String>> records,String fileName){
        String path = String.format("src/test/resources/%s.csv",fileName);
        StringBuilder sb = new StringBuilder();
        List<String> headers = records.stream().flatMap(map->map.keySet().stream())
                .distinct().collect(Collectors.toList());

        for (int i = 0; i < headers.size(); i++) {
            sb.append(headers.get(i));
            sb.append(i== headers.size()-1? "\n":",");
        }
        for (Map<String, String> map: records){
            for (int i = 0; i < headers.size(); i++) {
                sb.append(map.get(headers.get(i)));
                sb.append(i== headers.size()-1?"\n":",");
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(sb.toString());
            bw.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
