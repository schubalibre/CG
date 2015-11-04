package tools;

/**
 * Created by roberto on 25/10/15.
 */
public class Color {
    public final double r;
    public final double g;
    public final double b;

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color add(Color c){
        return null;
    }

    public Color sub(Color c){
        return null;
    }

    public Color mul(Color c){
        return null;
    }

    public Color mul(double v){
        return null;
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (Double.compare(color.r, r) != 0) return false;
        if (Double.compare(color.g, g) != 0) return false;
        return Double.compare(color.b, b) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(r);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
