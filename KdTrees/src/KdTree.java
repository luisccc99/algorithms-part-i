import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KdTree {

    private int size;
    private Node root;
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private static class Node {
        // the point
        private final Point2D p;

        // the axis-aligned rectangle corresponding to this node
        private RectHV rect;

        // the left/bottom subtree
        private Node lb;

        // the right/top subtree
        private Node rt;

        public Node(Point2D p) {
            this.p = p;
        }
    }

    // construct empty set of points
    public KdTree() {
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        validate(p);
        if (!contains(p)) {
            root = insert(root, p, HORIZONTAL, null);
            size++;
        }
    }

    private Node insert(Node current, Point2D p,
                        boolean orientation, Node parent) {
        if (current == null) {
            Node node = new Node(p);
            // setting rect for node
            // if parent is null it means is node for root
            // else determine min and max of x and y for node
            if (parent == null) {
                node.rect = new RectHV(0, 0, 1, 1);
            } else {
                Point2D q = parent.p;
                RectHV rectHV = parent.rect;
                if (orientation == HORIZONTAL) {
                    if (less(p, q, !orientation)) {
                        node.rect = new RectHV(rectHV.xmin(), rectHV.ymin(),
                                rectHV.xmax(), q.y());
                    } else {
                        node.rect = new RectHV(rectHV.xmin(), q.y(),
                                rectHV.xmax(), rectHV.ymax());
                    }
                } else {
                    if (less(p, q, !orientation)) {
                        node.rect = new RectHV(rectHV.xmin(), rectHV.ymin(),
                                q.x(), rectHV.ymax());
                    } else {
                        node.rect = new RectHV(q.x(), rectHV.ymin(),
                                rectHV.xmax(), rectHV.ymax());
                    }
                }
            }
            return node;
        }

        if (less(p, current.p, orientation)) { // go left or below
            current.lb = insert(current.lb, p, !orientation, current);
        } else { // go right or above
            current.rt = insert(current.rt, p, !orientation, current);
        }
        return current;
    }

    private boolean less(Point2D p, Point2D q, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Point2D.X_ORDER.compare(p, q) < 0;
        } else {
            return Point2D.Y_ORDER.compare(p, q) < 0;
        }
    }

    // does the set contain p?
    public boolean contains(Point2D p) {
        validate(p);
        return contains(p, root, HORIZONTAL);
    }

    private boolean contains(Point2D p, Node node, boolean orientation) {
        if (node == null) {
            return false;
        }

        if (less(p, node.p, orientation)) { // go left or bottom
            return contains(p, node.lb, !orientation);
        } else if (p.compareTo(node.p) == 0) { // search hit
            return true;
        } else { // go right or top
            return contains(p, node.rt, !orientation);
        }
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, HORIZONTAL);
    }

    private void draw(Node node, boolean orientation) {
        if (node == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();
        StdDraw.setPenRadius();
        if (orientation == HORIZONTAL) {
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.line(node.p.x(), node.rect.ymin(),
                    node.p.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(),
                    node.rect.xmax(), node.p.y());
        }
        draw(node.lb, !orientation);
        draw(node.rt, !orientation);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        validate(rect);
        List<Point2D> pointsInRange = new ArrayList<>();
        range(rect, root, pointsInRange);
        return pointsInRange;
    }

    private void range(RectHV queryRect, Node node, List<Point2D> pointsInRange) {
        if (node == null) {
            return;
        }
        if (queryRect.contains(node.p)) {
            pointsInRange.add(node.p);
        }
        if (queryRect.intersects(node.lb.rect)
                && queryRect.intersects(node.rt.rect)) {
            range(queryRect, node.lb, pointsInRange);
            range(queryRect, node.rt, pointsInRange);
        } else if (queryRect.intersects(node.lb.rect)) {
            range(queryRect, node.lb, pointsInRange);
        } else {
            range(queryRect, node.rt, pointsInRange);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        validate(p);
        return nearest(root, p, null, root.p);
    }

    private Point2D nearest(Node node, Point2D queryPoint,
                            RectHV rect, Point2D champ) {
        if (node == null) {
            return champ;
        }

        if (node.lb.rect.contains(queryPoint)) {
            // go left-bottom
        } else {
            // go right-top
        }
        return null;
    }

    private void validate(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        KdTree tree2d = new KdTree();
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            tree2d.insert(new Point2D(r.nextDouble(), r.nextDouble()));
        }
        tree2d.draw();
    }
}
