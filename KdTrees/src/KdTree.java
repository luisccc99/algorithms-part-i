import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

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

    private Node insert(Node node, Point2D p,
                        boolean orientation, Point2D parent) {
        if (node == null) {
            Node elem = new Node(p);
            // setting rect for node
            // if parent is null it means is node for root
            // else determine min and max of x and y for node
            if (parent == null) {
                elem.rect = new RectHV(0, 0, 1, 1);
            } else {
                if (orientation == HORIZONTAL) {
                    double y = p.y();
                    if (less(p, parent, orientation)) {
                        elem.rect = new RectHV(0, y, parent.x(), y);
                    } else {
                        elem.rect = new RectHV(parent.x(), y, 1, y);
                    }
                } else {
                    double x = p.x();
                    if (less(p, parent, orientation)) {
                        elem.rect = new RectHV(x, 0, x, parent.y());
                    } else {
                        elem.rect = new RectHV(x, parent.y(), x, 1);
                    }
                }
            }
            return elem;
        }

        if (less(p, node.p, orientation)) { // go left or below
            node.lb = insert(node.lb, p, !orientation, node.p);
        } else { // go right or above
            node.rt = insert(node.rt, p, !orientation, node.p);
        }

        return node;
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
        
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        validate(rect);
        List<Point2D> point2DList = new ArrayList<>();
        // traverse 2d tree
        return point2DList;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        validate(p);
        // traverse 2d tree
        return null;
    }

    private void validate(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        KdTree tree2d = new KdTree();
        Point2D p1 = new Point2D(0.3, 0.3);
        Point2D p2 = new Point2D(0.7, 0.2);
        Point2D p3 = new Point2D(0.2, 0.1);
        Point2D p4 = new Point2D(0.1, 0.1);
        Point2D p5 = new Point2D(0.5, 0.2);
        tree2d.insert(p1);
        tree2d.insert(p2);
        tree2d.insert(p3);
        tree2d.insert(p4);
        System.out.println(tree2d.contains(p1));
        System.out.println(tree2d.contains(p4));
        System.out.println(tree2d.contains(p5));
    }
}
