
// Java program for the above approach
import java.util.*;

public class TSPGreedy {

    // Function to find the minimum
    // cost path for all the paths
    double tsp[][];
    int startingCity;
    List<Integer> minRoute;

    public TSPGreedy(double tsp[][], int startingCity) {
        this.tsp = tsp;
        this.startingCity = startingCity;
    }

    public double findMinRoute() {
        double sum = 0;
        int counter = 0;
        int j = 0, i = 0;
        double min = Integer.MAX_VALUE;
        List<Integer> visitedRouteList = new ArrayList<>();

        visitedRouteList.add(startingCity);
        int[] route = new int[tsp.length];

        while (i < tsp.length
                && j < tsp[i].length) {

            if (counter >= tsp[i].length - 1) {
                break;
            }

            if (j != i
                    && !(visitedRouteList.contains(j))) {
                if (tsp[i][j] < min) {
                    min = tsp[i][j];
                    route[counter] = j + 1;
                }
            }
            j++;

            if (j == tsp[i].length) {
                sum += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(route[counter] - 1);
                j = 0;
                i = route[counter] - 1;
                counter++;
                minRoute = visitedRouteList;
            }
        }

        i = route[counter - 1] - 1;

        for (j = 0; j < tsp.length; j++) {

            if ((i != j) && tsp[i][j] < min) {
                min = tsp[i][j];
                route[counter] = j + 1;
            }
        }
        sum += min;

        return sum;
    }

    public List<Integer> getMinRoute() {
        return minRoute;
    }
}
