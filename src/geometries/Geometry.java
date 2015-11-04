package geometries;

import tools.Color;
import tools.Ray;

/**
 * Created by roberto on 25/10/15.
 */
public abstract class Geometry {
    public final Color color;

    public Geometry(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("The Color cannot be null!");
        }
        this.color = color;
    }

    public abstract Hit hit(Ray r);

    @Override
    public String toString() {
        return "Geometry{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        return !(color != null ? !color.equals(geometry.color) : geometry.color != null);

    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
