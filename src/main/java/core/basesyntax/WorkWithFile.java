package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        int supply = 0;
        int buy = 0;
        try (BufferedReader bReader = new BufferedReader( new FileReader(fromFileName))) {
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("supply")) {
                    supply += Integer.parseInt(data[1]);
                } else if (data[0].equals("buy")) {
                    buy += Integer.parseInt(data[1]);
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
