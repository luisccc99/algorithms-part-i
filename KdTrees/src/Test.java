import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RandomSeq;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;

public class Test {

    public static void main(String[] args) {

        StdDraw.setPenRadius(0.01);
        Point2D[] points = new Point2D[20];
        Random r = new Random();
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(r.nextDouble(), r.nextDouble());
            if (i % 2 == 0) {
                StdDraw.setPenColor(StdDraw.BOOK_RED);
            } else {
                StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            }
            System.out.println(points[i]);
            points[i].draw();
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        RectHV rect = new RectHV(0, 0, .5, .5);
        rect.draw();
    }
}
