import java.io.*;

public class Position {

    private double x,y,z;

    public Position() {
        x = 0; y = 0; z = 0;
    }

    public Position(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }


    public String toString() {
        String msg = x+","+y+","+z;
        return msg;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Position clone() {
        return new Position(x,y,z);
    }

    public boolean equals(Position p, double eps) {
        if ( (Math.abs(p.x-x)>eps)||(Math.abs(p.y-y)>eps)||(Math.abs(p.z-z)>eps) ) return false;
        return true;
    }

    public double distance(Position p) {
        double d  = 0.0;
        d = Math.sqrt((p.x-x)*(p.x-x) + (p.y-y)*(p.y-y) + (p.z-z)*(p.z-z));
        return d;
    }
}
