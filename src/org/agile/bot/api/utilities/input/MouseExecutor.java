package org.agile.bot.api.utilities.input;

import org.agile.bot.api.utilities.Random;
import org.agile.bot.api.utilities.Time;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 15/08/13
 * Time: 8:40 AM
 * Project: Client
 * Package: org.agile.bot.api.utilities.input
 */
public class MouseExecutor {

    public final int msPerBit = Random.nextInt(50, 100);
    public final int reactionTime = Random.nextInt(50, 100);
    public final int speed = 5;

    public boolean step(int x, int y) {
        try {
            int mkx = Mouse.getX();
            int mky = Mouse.getY();
            Point[] controls = generateControls(Mouse.getX(), Mouse.getY(), x, y, 50, 120);
            Point[] spline = generateSpline(controls);
            long time = fittsLaw(Math.sqrt(Math.pow(mkx - Mouse.getX(), 2) + Math.pow(mky - Mouse.getY(), 2)), 10);
            Point[] path = applyDynamism(spline, (int) time, 10);
            for (Point aPath : path) {
                Mouse.hopMouse(aPath.x, aPath.y);
                Time.sleep(Random.nextGaussian((speed * 10) / 3, speed * 10, (speed * 10) / 2));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private final Point[] generateControls(int sx, int sy, int ex, int ey, int ctrlSpacing, int ctrlVariance) {
        double dist = Math.sqrt((sx - ex) * (sx - ex) + (sy - ey) * (sy - ey));
        double angle = Math.atan2(ey - sy, ex - sx);
        int ctrlPoints = (int) Math.floor(dist / ctrlSpacing);
        ctrlPoints = ctrlPoints * ctrlSpacing == dist ? ctrlPoints - 1 : ctrlPoints;
        if (ctrlPoints <= 1) {
            ctrlPoints = 2;
            ctrlSpacing = (int) dist / 3;
            ctrlVariance = (int) dist / 2;
        }
        Point[] result = new Point[ctrlPoints + 2];
        result[0] = new Point(sx, sy);
        for (int i = 1; i < ctrlPoints + 1; i++) {
            double radius = ctrlSpacing * i;
            Point cur = new Point((int) (sx + radius * Math.cos(angle)), (int) (sy + radius * Math.sin(angle)));
            double percent = 1D - ((double) (i - 1) / (double) ctrlPoints);
            percent = percent > 0.5 ? percent - 0.5 : percent;
            percent += 0.25;
            int curVariance = (int) (ctrlVariance * percent);
            cur.x = Random.nextInt(cur.x, cur.x + curVariance * 2);
            cur.y = Random.nextInt(cur.y, cur.y + curVariance * 2);
            result[i] = cur;
        }
        result[ctrlPoints + 1] = new Point(ex, ey);
        return result;
    }

    private final double fact(int n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private final double nCk(int n, int k) {
        return fact(n) / (fact(k) * fact(n - k));
    }

    private final void adaptiveMidpoints(java.util.Vector<Point> points) {
        int i = 0;
        while (i < points.size() - 1) {
            Point a = points.get(i++);
            Point b = points.get(i);
            if (Math.abs(a.x - b.x) > 1 || Math.abs(a.y - b.y) > 1) {
                if (Math.abs(a.x - b.x) != 0) {
                    double slope = (double) (a.y - b.y) / (double) (a.x - b.x);
                    double incpt = a.y - slope * a.x;
                    for (int c = a.x < b.x ? a.x + 1 : b.x - 1; a.x < b.x ? c < b.x : c > a.x; c += a.x < b.x ? 1 : -1) {
                        points.add(i++, new Point(c, (int) Math.round(incpt + slope * c)));
                    }
                } else {
                    for (int c = a.y < b.y ? a.y + 1 : b.y - 1; a.y < b.y ? c < b.y : c > a.y; c += a.y < b.y ? 1 : -1) {
                        points.add(i++, new Point(a.x, c));
                    }
                }
            }
        }
    }

    private final Point[] generateSpline(Point[] controls) {
        double degree = controls.length - 1;
        java.util.Vector<Point> spline = new java.util.Vector<>();
        boolean lastFlag = false;
        for (double theta = 0; theta <= 1; theta += 0.01) {
            double x = 0;
            double y = 0;
            for (double index = 0; index <= degree; index++) {
                double probPoly = nCk((int) degree, (int) index) * Math.pow(theta, index) * Math.pow(1D - theta, degree - index);
                x += probPoly * controls[(int) index].x;
                y += probPoly * controls[(int) index].y;
            }
            Point temp = new Point((int) x, (int) y);
            try {
                if (!temp.equals(spline.lastElement())) {
                    spline.add(temp);
                }
            } catch (Exception e) {
                spline.add(temp);
            }
            lastFlag = theta != 1.0;
        }
        if (lastFlag) {
            spline.add(new Point(controls[(int) degree].x, controls[(int) degree].y));
        }
        adaptiveMidpoints(spline);
        return spline.toArray(new Point[0]);
    }

    private final double gaussian(double t) {
        t = 10D * (t) - 5D;
        return (1D / (Math.sqrt(5D) * Math.sqrt(2D * Math.PI))) * Math.exp((-t * t) / 20D);
    }

    private final double[] gaussTable(int steps) {
        double[] table = new double[steps];
        double step = 1D / (double) steps;
        double sum = 0;
        for (int i = 0; i < steps; i++) {
            sum += gaussian(i * step);
        }
        for (int i = 0; i < steps; i++) {
            table[i] = gaussian(i * step) / sum;
        }
        return table;
    }

    private final Point[] applyDynamism(Point[] spline, int msForMove, int msPerMove) {
        int numPoints = spline.length;
        double msPerPoint = (double) msForMove / (double) numPoints;
        int undistStep = (int) Math.round((double) msPerMove / msPerPoint);
        int steps = (int) Math.floor(numPoints / undistStep);
        Point[] result = new Point[steps];
        double[] gaussValues = gaussTable(result.length);
        double currentPercent = 0;
        for (int i = 0; i < steps; i++) {
            currentPercent += gaussValues[i];
            int nextIndex = (int) Math.floor((double) numPoints * currentPercent);
            if (nextIndex < numPoints) {
                result[i] = spline[nextIndex];
            } else {
                result[i] = spline[numPoints - 1];
            }
        }
        if (currentPercent < 1D) {
            result[steps - 1] = spline[numPoints - 1];
        }
        return result;
    }

    private final long fittsLaw(double targetDist, double targetSize) {
        return (long) (reactionTime + msPerBit * (Math.log10(targetDist / targetSize + 1) / Math.log10(2)));
    }

}
