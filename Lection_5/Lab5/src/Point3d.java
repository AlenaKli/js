package src;

public class Point3d {

    private double xCoord;
    private double yCoord;
    private double zCoord;

//Конструктор
    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d() {
        this(0.0, 0.0, 0.0);
    }

// Геттеры
    public double getX() {
        return xCoord;
    }

    public double getY() {
        return yCoord;
    }

    public double getZ() {
        return zCoord;
    }

// Сеттеры
    public void setX(double val) {
        xCoord = val;
    }

    public void setY(double val) {
        yCoord = val;
    }

    public void setZ(double val) {
        zCoord = val;
    }

    public boolean equals(Point3d other) {
        return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ();

    }

    public double distanceTo(Point3d other) {
        double distance = Math.sqrt(Math.pow((this.getX() - other.getX()), 2) + Math.pow((this.getY() - other.getY()), 2) + Math.pow((this.getZ() - other.getZ()), 2));
        return Math.round(distance * 100) / 100;
    }

}
