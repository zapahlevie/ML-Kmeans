package Kmean;

import java.util.ArrayList;
import java.util.List;

class Kmean {

    public static void main(String args[]) {
        int i, j, n = 0, k = 2;
        ArrayList<Point> c = new ArrayList<>();
        ArrayList<Point> m = new ArrayList<>();
        boolean flag;
        ArrayList<Point> arr = new ArrayList<Point>() {
            {
                add(new Point(2, 5));
                add(new Point(4, 7));
                add(new Point(13, 9));
                add(new Point(30, 1));
                add(new Point(3, 20));
                add(new Point(20, 25));
                add(new Point(10, 4));
                add(new Point(11, 22));
                add(new Point(4, 8));
            }
        };
        ArrayList<ArrayList<Point>> cluster = new ArrayList<>();
        for (i = 0; i < k; i++) {
            cluster.add(new ArrayList<>());
        }

        for (i = 0; i < k; i++) {
            c.add(new Point(arr.get(i).getX(), arr.get(i).getY()));
        }
        for (i = 0; i < k; i++) {
            m.add(new Point(c.get(i).getX(), c.get(i).getY()));
        }

        System.out.println("Data :");
        for (i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i).getX() + "," + arr.get(i).getY() + "\t");
        }
        System.out.println();

        do {
            for (i = 0; i < k; i++) {
                cluster.get(i).clear();
            }
            n++;
            for (i = 0; i < k; i++) {
                System.out.println("m" + (i + 1) + "=" + m.get(i).getX() + "," + m.get(i).getY());
            }
            int dis, min = 0, index = 0;
            for (i = 0; i < arr.size(); i++) {
                for (j = 0; j < k; j++) {
                    dis = (int) Math.abs(jarak(arr.get(i), m.get(j)));
                    if (j == 0) {
                        min = dis;
                        index = j;
                    } else {
                        if (dis < min) {
                            min = dis;
                            index = j;
                        }
                    }
                }
                cluster.get(index).add(arr.get(i));
            }

            System.out.println("Iterasi " + n);
            for (i = 0; i < k; i++) {
                System.out.println("Cluster " + (i + 1) + " :");
                for (j = 0; j < cluster.get(i).size(); j++) {
                    System.out.print(cluster.get(i).get(j).getX() + "," + cluster.get(i).get(j).getY() + "\t");
                }
                System.out.println();
            }

            int sumx, sumy;
            flag = false;
            for (i = 0; i < k; i++) {
                c.get(i).setX(m.get(i).getX());
                c.get(i).setY(m.get(i).getY());
                sumx = 0;
                sumy = 0;
                for (j = 0; j < cluster.get(i).size(); j++) {
                    sumx = sumx + cluster.get(i).get(j).getX();
                    sumy = sumy + cluster.get(i).get(j).getY();
                }
                m.get(i).setX(Math.round(sumx / cluster.get(i).size()));
                m.get(i).setY(Math.round(sumy / cluster.get(i).size()));
                if (c.get(i).getX() != m.get(i).getX() || c.get(i).getY() != m.get(i).getY()) {
                    flag = true;
                }
            }
            for (i = 0; i < k; i++) {
                System.out.println("m" + (i + 1) + "=" + m.get(i).getX() + "," + m.get(i).getY());
            }
            System.out.println();
        } while (flag);

        System.out.println("Cluster Final ");
        for (i = 0; i < k; i++) {
            System.out.println("Cluster " + (i + 1) + " :");
            for (j = 0; j < cluster.get(i).size(); j++) {
                System.out.print(cluster.get(i).get(j).getX() + "," + cluster.get(i).get(j).getY() + "\t");
            }
            System.out.println();
        }

    }

    public static float jarak(Point v1, Point v2) {
        Point v = new Point(0, 0);
        v.setX(v2.getX() - v1.getX());
        v.setY(v2.getY() - v1.getY());
        float D = (float) (Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
        float a = (float) Math.sqrt(D);
        return a;
    }
}