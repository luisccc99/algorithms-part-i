import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private int numberOfSegments;
    private List<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        validate(points);
        lineSegments = new ArrayList<>();
        Point[] cp = copyOf(points);
        for (Point origin : points) {
            Arrays.sort(cp, origin.slopeOrder());
            for (int j = 1; j < cp.length - 1; j++) {
                Point p = cp[j - 1];
                Point q = cp[j];
                Point r = cp[j + 1];
                if (origin.equals(p) || origin.equals(q) || origin.equals(r)) {
                    continue;
                }
                Point[] segment;
                if (origin.slopeTo(p) == origin.slopeTo(q) &&
                        origin.slopeTo(q) == origin.slopeTo(r)) {
                    segment = new Point[]{origin, p, q, r};
                    Arrays.sort(segment);
                    if (origin.equals(segment[points.length - 1])) {
                        lineSegments.add(new LineSegment(points[0], origin));
                        numberOfSegments++;
                    }
                }
            }
        }
    }

    private Point[] copyOf(Point[] points) {
        Point[] cp = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            cp[i] = points[i];
        }
        return cp;
    }

    private void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1] == null || points[i - 1].equals(points[i])) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numberOfSegments]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
