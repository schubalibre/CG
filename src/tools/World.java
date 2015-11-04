package tools;

import geometries.Geometry;
import geometries.Hit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roberto on 03.11.15.
 */
public class World {

    final private Color background;
    private List<Geometry> objects;

    public World(Color background) {
        this.background = background;
        objects = new ArrayList<>();
    }

    public void addObject(Geometry g){
        if (g == null) {
            throw new IllegalArgumentException("The g cannot be null!");
        }
        objects.add(g);
    }

    public Color hit(final Ray r) {
        if (r == null) {
            throw new IllegalArgumentException("The r cannot be null!");
        }

        Hit min = null;

        for (Geometry g : objects){

            final Hit hit = g.hit(r);

            if(hit != null && (min == null || hit.t < min.t)){
                min = hit;
            }
        }

        return (min != null) ? min.geo.color : background;
    }
}
