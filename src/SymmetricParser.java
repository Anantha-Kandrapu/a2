import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Point {
    int name;
    double x, y;

    // constructor
    Point(int name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // needed when converting a number to a letter and vise versa
    void setName(int a) {
        this.name = a;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}

public class SymmetricParser {

    public static Map<Integer, Point> points; // map of points
    public static ArrayList<ArrayList<Double>> finalArray = new ArrayList<ArrayList<Double>>();

    // ArrayList
    /**
     * @param args
     * @throws IOException
     */
    /**
     * @param args
     * @throws IOException
     */
    public SymmetricParser(String fileName) throws IOException {
        String filename = fileName;
        points = new HashMap<Integer, Point>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String pattern = "(?m)^\\d+\\s\\d+\\.\\d+\\s\\d+\\.\\d+";
            Pattern r = Pattern.compile(pattern);

            String value = null;

            while ((value = reader.readLine()) != null) {
                Matcher m = r.matcher(value);
                if (m.find()) {
                    Point p = new Point(Integer.parseInt(value.split(" ")[0]), Double.parseDouble(value.split(" ")[1]),
                            Double.parseDouble(value.split(" ")[2]));
                    points.put(p.name, p);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // PointsEx ex = new PointsEx();
        // ex.setVisible(true);
        showPoints();
    }
    public void showPoints(){
        util x = new util(points);
        x.utilDraw();
    }

    public Map<Integer, Point> getPoints() {
        return points;
    }

    public double[][] resultArray() {
        points.size();
        for (Integer p1 : points.keySet()) {
            ArrayList<Double> test = new ArrayList<>();
            for (Integer p2 : points.keySet()) {
                double result = Math.pow(points.get(p1).x - points.get(p2).x, 2)
                        + Math.pow(points.get(p1).y - points.get(p2).y, 2);
                double distance = Math.sqrt(result);
                test.add(distance);
            }
            finalArray.add(test);
        }
        double[][] arr = finalArray.stream()
                .map(l -> l.stream().mapToDouble(Double::intValue).toArray())
                .toArray(double[][]::new);
        return arr;
    }

}