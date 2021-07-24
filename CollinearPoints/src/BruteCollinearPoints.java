import java.util.Arrays;

public class BruteCollinearPoints {

    private final LineSegment[] segments;
    private int numberOfLIneSegments;

    /**
     * finds all line segments containing 4 points
     *
     * @param points array of points to look through
     */
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        int n = points.length;
        segments = new LineSegment[n];
        Arrays.sort(points);
        numberOfLIneSegments = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[m];
                        if (p.slopeTo(q) == p.slopeTo(r)
                                && p.slopeTo(r) == p.slopeTo(s)) {
                            segments[numberOfLIneSegments++] = new LineSegment(p, s);
                        }
                    }
                }
            }
        }

    }

    private void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return numberOfLIneSegments;
    }

    /**
     * @return the line segments
     */
    public LineSegment[] segments() {
        return segments;
    }
}
