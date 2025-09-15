package src;

import java.util.Scanner;

public class Lab5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the coordinates of the points, each on a separate line");

        Point3d point_1 = new Point3d(sc.nextInt(), sc.nextInt(), sc.nextInt());
        Point3d point_2 = new Point3d(sc.nextInt(), sc.nextInt(), sc.nextInt());
        Point3d point_3 = new Point3d(sc.nextInt(), sc.nextInt(), sc.nextInt());

        CalculateArea(point_1, point_2, point_3);

    }

    public static void  CalculateArea(Point3d p_1, Point3d p_2, Point3d p_3) {
        double edge_1 = p_1.distanceTo(p_2);
        double edge_2 = p_2.distanceTo(p_3);
        double edge_3 = p_3.distanceTo(p_1);


        if (edge_1 + edge_2 > edge_3
                && edge_1 + edge_3 > edge_2
                && edge_2 + edge_3 > edge_1) {
            System.out.println("There is no triangle");
        } else {
            double semi_perimeter = (edge_1 + edge_2 + edge_3) / 2;
            double square = Math.sqrt(semi_perimeter * (semi_perimeter - edge_1) * (semi_perimeter - edge_2) * (semi_perimeter - edge_3));
            System.out.printf("Square = %.2f", square);
        }

    }

}