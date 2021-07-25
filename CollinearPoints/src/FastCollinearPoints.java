import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        validateNullPoints(points);
        Point[] copy = copyOf(points);
        validateRepeatedPoints(copy);
        lineSegments = new ArrayList<>();
        for (Point origin : points) {
            Arrays.sort(copy, origin.slopeOrder());
            Point[] segmentPoints;
            for (int j = 1; j < copy.length - 1; j++) {
                Point p = copy[j - 1];
                Point q = copy[j];
                Point r = copy[j + 1];
                double originSlopeToP = origin.slopeTo(p);
                double originSlopeToQ = origin.slopeTo(q);
                double originSlopeToR = origin.slopeTo(r);
                if (originSlopeToP == 0 || originSlopeToQ == 0
                        || originSlopeToR == 0) {
                    continue;
                }
                if (originSlopeToP == originSlopeToQ
                        && originSlopeToP == originSlopeToR) {
                    segmentPoints = new Point[]{p, q, r, origin};
                    Arrays.sort(segmentPoints);
                    if (origin == segmentPoints[segmentPoints.length - 1]) {
                        lineSegments.add(new LineSegment(segmentPoints[0], origin));
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

    private void validateNullPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRepeatedPoints(Point[] points) {
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            Point p = points[i - 1];
            Point q = points[i];
            if (p.compareTo(q) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
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

