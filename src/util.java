import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.Map;

public class util extends JPanel {
    // initialize coordinates
    // int[] cord = { 65, 20, 40, 80 };
    int marg = 20;

    public Map<Integer, Point> points; // map of pointsstatic

    public util(Map<Integer, Point> points) {
        this.points = points;
    }

    protected void paintComponent(Graphics grf) {
        // create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;

        // Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        int scalingFactorX = (getMaxX() / width) + 1;
        int scalingFactorY = (getMaxY() / height) + 1;
        graph.setPaint(Color.RED);
        for (Integer p1 : points.keySet()) {
            int x2 = (int) points.get(p1).x / scalingFactorX;
            int y2 = (int) points.get(p1).y / scalingFactorY;
            // System.out.println(x2 + " " + y2);
            graph.fill(new Ellipse2D.Double(-x2+width,
                    -1 * y2 + height,
                    4, 4));
        }
    }

    private int getMaxY() {

        int max = -Integer.MAX_VALUE;
        for (Integer p1 : points.keySet()) {
            if (points.get(p1).getY() > max)
                max = (int) points.get(p1).getY();
        }
        return max;
    }

    private int getMaxX() {

        int max = -Integer.MAX_VALUE;
        for (Integer p1 : points.keySet()) {
            if (points.get(p1).getX() > max)
                max = (int) points.get(p1).getX();
        }
        return max;
    }

    // main() method start
    public void utilDraw() {
        // create an instance of JFrame class
        JFrame frame = new JFrame();
        // set size, layout and location for frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new util(points));
        frame.setSize(1000, 1000);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}
