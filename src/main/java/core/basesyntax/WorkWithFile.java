package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        int supply = 0;
        int buy = 0;
        try (BufferedReader bReader = new BufferedReader( new FileReader(fromFileName))) {
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[OPERATION_INDEX].equals("supply")) {
                    supply += Integer.parseInt(data[AMOUNT_INDEX]);
                } else if (data[OPERATION_INDEX].equals("buy")) {
                    buy += Integer.parseInt(data[AMOUNT_INDEX]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        String result = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
        try (FileWriter fWriter = new FileWriter(toFileName)) {
            fWriter.write(result);
        } catch (Exception e) {
            throw new RuntimeException("Write error " + toFileName, e);
        }
    }
}
