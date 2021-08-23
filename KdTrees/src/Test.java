
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Test {

    public static void main(String[] args) {
        RectHV rect = new RectHV(0.05, 0.05, 0.4, 0.4);
        Point2D first = new Point2D(0, 0);
        Point2D second = new Point2D(0.1, 0);
        Point2D third = new Point2D(0, 0.1);
        Point2D fourth = new Point2D(0.2, 0);
        rect.draw();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        first.draw();
        second.draw();
        third.draw();
        fourth.draw();
        System.out.println(rect.distanceSquaredTo(first));
        System.out.println(rect.distanceSquaredTo(second));
        System.out.println(rect.distanceSquaredTo(third));
        System.out.println(rect.distanceSquaredTo(fourth));
    }
}
