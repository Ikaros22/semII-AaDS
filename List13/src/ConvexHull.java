
import java.awt.*;
import java.util.List;
import java.util.*;

public class ConvexHull {
    public static List<Point> solve(List<Point> points) throws IllegalArgumentException {
        if (points.size() < 3)
            throw new IllegalArgumentException();

        PointCalculator calculator = (a, b, c) -> (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        points = listSorter(points);
        Stack<Point> frame = new Stack<>();

        frame.push(points.get(0));
        frame.push(points.get(1));

        for (int i = 2; i < points.size(); i++) {
            Point A = findTop(frame);
            Point B = frame.peek();
            Point C = points.get(i);

            int position = calculator.positions(A, B, C);

            if (position == 0) {
                frame.pop();
                frame.push(C);
            }
            if (position > 0) {
                frame.pop();
                i--;
            }
            if (position < 0) {
                frame.push(C);
            }
        }
        if (frame.size() <= 2)
            throw new IllegalArgumentException();

        frame.push(frame.get(0)); //just for test

        return new LinkedList<>(frame);
    }

    private static List<Point> listSorter(List<Point> points) {
        Point startPoint = findStartPoint(points);
        MathCalculator mathCalculator = (a,b) -> (Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));

        TreeSet<Point> set = new TreeSet<>((a, b) -> {
            if (a.equals(b))
                return 0;

            double angle1 = Math.atan2(a.y - startPoint.y, a.x - startPoint.x); //is an inbuilt method in Java that is used to return the theta component from the polar coordinate
            double angle2 = Math.atan2(b.y - startPoint.y, b.x - startPoint.x);

            if (angle1 < angle2) {
                return -1;
            } else if (angle1 > angle2) {
                return 1;
            } else {
                double distanceSquareP1 = mathCalculator.powerSum(a, startPoint) ;
                double distanceSquareP2 =  mathCalculator.powerSum(b, startPoint);

                if (distanceSquareP1 < distanceSquareP2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        set.addAll(points);
        return new ArrayList<>(set);
    }

    private static Point findStartPoint(List<Point> points) {
        Point minDistancePoint = points.get(0);
        for (Point point : points) {
            if (point.getY() < minDistancePoint.getY()) {
                minDistancePoint = point;
            } else if (point.getY() == minDistancePoint.getY()) {
                if (point.getX() < minDistancePoint.getX())
                    minDistancePoint = point;
            }
        }
        return minDistancePoint;
    }

    private static Point findTop( Stack<Point> stack) {
        Point temp = stack.pop();
        Point point = stack.peek();
        stack.push(temp);
        return point;
    }

    interface PointCalculator {
        int positions(Point A, Point B, Point C);
    }

    interface MathCalculator {
        double powerSum(Point A, Point B);
    }

}