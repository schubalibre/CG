package Geometries;

import Tools.Color;
import Tools.Ray;

/**
 * Created by roberto on 25/10/15.
 */
public class Geometry {
    public final Color color;

    public Geometry(Color color) {
        this.color = color;
    }

    public Hit hit(Ray r){
        return null;
    }

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
