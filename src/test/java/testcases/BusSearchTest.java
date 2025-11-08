package testcases;

import Base_Class.BaseTest;
import POM.bus_Bookingpage;
import org.testng.annotations.*;
import java.io.*;
import java.util.*;
import com.opencsv.*;

public class BusSearchTest extends BaseTest {

    List<String[]> inputData;
    List<String[]> outputData = new ArrayList<>();

    @BeforeClass
    public void readCSV() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("input.csv"));
        inputData = reader.readAll();
        reader.close();
        outputData.add(new String[]{"From", "To", "Date", "Bus count"});
    }

    @Test
    public void testSearchBuses() throws InterruptedException {
        bus_Bookingpage bookingPage = new bus_Bookingpage(driver);

        for (int i = 1; i < inputData.size(); i++) {
            String[] row = inputData.get(i);

            // Check for minimum 3 columns: from, to, date
            if (row.length < 3) {
                System.out.println("Skipping malformed row at line " + (i + 1) + ": " + Arrays.toString(row));
                continue;
            }

            String from = row[0].trim();
            String to = row[1].trim();
            String date = row[2].trim();

            try {
                driver.get("https://www.redbus.in/");
                bookingPage.searchBuses(from, to, date);
                String count = bookingPage.getBusCount();

                outputData.add(new String[]{from, to, date, String.valueOf(count)});
            } catch (Exception e) {
                System.out.println("Error processing row " + (i + 1) + ": " + Arrays.toString(row));
                e.printStackTrace();
                outputData.add(new String[]{from, to, date, "Error"});
            }
        }
    }

    @AfterClass
    public void writeCSV() throws Exception {
        FileWriter writer = new FileWriter("output.csv");
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeAll(outputData);
        csvWriter.close();
        System.out.println(" Output saved to " + new File("output.csv").getAbsolutePath());
    }
}
