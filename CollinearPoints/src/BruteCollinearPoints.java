import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> segments;

    /**
     * finds all line segments containing 4 points
     *
     * @param points array of points to look through
     */
    public BruteCollinearPoints(Point[] points) {
        validateNullPoints(points);
        int n = points.length;
        Point[] copy = copyOf(points);
        segments = new ArrayList<>();
        Arrays.sort(copy);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        Point p = copy[i];
                        Point q = copy[j];
                        Point r = copy[k];
                        Point s = copy[m];
                        if (p.slopeTo(q) == p.slopeTo(r)
                                && p.slopeTo(r) == p.slopeTo(s)) {
                            segments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

    }

    private void validateNullPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
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

    private void validateRepeatedPoints(Point[] points) {
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            Point p = points[i - 1];
            Point q = points[i - 1];
            if (p.compareTo(q) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return segments.size();
    }

    /**
     * @return the line segments
     */
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
