import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class App implements Runnable {
    static String fileName;

    public static Runnable tspTask(double matrix[][]) {
        List<Integer> Path = new ArrayList<>();
        int startingCity = 0;
        TSPGreedy solver = new TSPGreedy(matrix, startingCity);
        double x = solver.findMinRoute();
        System.out.println("Tour: " + x);
        Path = solver.getMinRoute();
        for (int city : Path) {
            
            //SHOW IN JPANEL ::::::: ::::::
            System.out.print(" city:" + city + " =>");
        }
        return null;
    }

    public void decide() {

    }

    @Override
    public void run() {

        if (fileName.contains("atsp")) {
            AsymmetricParser asymmetricParser = new AsymmetricParser();
            List<String> readResult = asymmetricParser.readFile(fileName);
            double[][] matrix = asymmetricParser.generateMatrix(readResult);
            tspTask(matrix);
        } else {
            double[][] matrix;
            try {
                matrix = new SymmetricParser(fileName).resultArray();
                tspTask(matrix);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Thread.interrupted()) {
            return;
        }
    }

    public static void main(String[] args) throws IOException {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        int MAX_EXECUTION_TIME = 300;
        fileName = "zi929.tsp";
        Future future = executor.submit(new App());
        try {
            future.get(MAX_EXECUTION_TIME, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // executor.shutdownNow();
            System.exit(0);

        }

    }
}
