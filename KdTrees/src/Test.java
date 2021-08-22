import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RandomSeq;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        RectHV query = new RectHV(.2, .2, .3, .5);
        RectHV right = new RectHV(.5, .1, .9, .9);
        RectHV left = new RectHV(.1, .1, .5, .9);
        RectHV root = new RectHV(0, 0, 1, 1);
        query.draw();
        right.draw();
        left.draw();
        System.out.println(query.intersects(left));
        System.out.println(left.intersects(query));
        System.out.println(query.intersects(right));
        System.out.println(right.intersects(query));
        System.out.println(query.intersects(root));
        System.out.println(root.intersects(query));
    }
}
