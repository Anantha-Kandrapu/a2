import java.util.ArrayList;
import java.util.List;

public class AsymmetricParser {
    
    

    public int parseGetCities(List<String> readResult) {
        int cities = 0;
        for (String row : readResult) {
            if (row.contains("DIMENSION")) {
                int cut = row.indexOf(':');
                String city = row.substring(cut + 1, row.length()).replaceAll("\\s+", "");
                cities = Integer.parseInt(city);
            }
        }
        return cities;
    }

    public void printCities(double[][] matrix) {
        int cities = matrix.length;
        for (int i = 0; i < cities; i++) {
            for (int j = 0; j < cities; j++) {
                System.out.print(matrix[i][j]+" , ");
            }
            System.out.println();
        }
    }

    public double[][] generateMatrix(List<String> readResult) {

        int cities = parseGetCities(readResult);
        List<String> flatStrings = new ArrayList<>();
        int ignoreAfter = -7;
        for (String row : readResult) {
            if(ignoreAfter++ < 0)
                continue;
            String[] ans = row.replace("\n", "").split("\\s+");
            for (String str : ans)
                flatStrings.add(str);
        }
        int k = 0;
        double[][] matrix = new double[cities][cities];
        for (int i = 0; i < cities; i++) {
            for (int j = 0; j < cities; j++) {
                while (flatStrings.get(k).isEmpty())
                    k += 1;
                matrix[i][j] = Double.parseDouble(flatStrings.get(k));
                k += 1;
            }
        }
        // printCities(matrix);
        return matrix;
    }
}
