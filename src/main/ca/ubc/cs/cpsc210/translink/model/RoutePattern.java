package ca.ubc.cs.cpsc210.translink.model;

import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A description of one pattern of a route
 * Each pattern has a name, destination, direction, list of points (of class LatLon), and Route
 */

// TODO: Task 2: Complete all the methods in this class

public class RoutePattern {

    private String name;
    private String dest;
    private String dir;
    private List<LatLon> path;
    private Route route;

    /**
     * Construct a new RoutePattern with the given information
     * @param name          the name of the pattern
     * @param destination   the destination
     * @param direction     the direction
     * @param route         the Route of which this is a pattern
     */
    public RoutePattern(String name, String destination, String direction, Route route) {
        this.name = name;
        this.dest = destination;
        this.dir = direction;
        this.route = route;
        this.path = new ArrayList<>();
    }

    /**
     * Get the pattern name
     * @return      the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the pattern destination
     * @return      the destination
     */
    public String getDestination() {
        return dest;
    }

    /**
     * Get the pattern direction
     * @return      the direction
     */
    public String getDirection() {
        return dir;
    }

    /**
     * Decide if two RoutePatterns are equal. Two route patterns are equal if their names are equal.
     * @param o         the other route pattern to compare to
     * @return          true if this is equal to o
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass() == (RoutePattern.class)) {
            RoutePattern temp = (RoutePattern) o;
            if (Objects.equals(temp.getName(), name)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(name);
    }

    /**
     * Set the pattern path: list of coordinates
     * @param path      the path
     */
    public void setPath(List<LatLon> path) {
        this.path = path;
    }

    /**
     * Return the list of coordinates making up this pattern
     *
     * @return      an unmodifiable list of the coordinates on this route pattern
     */
    public List<LatLon> getPath() {
        return Collections.unmodifiableList(path);
    }

    /**
     * Set the direction
     * @param direction     the direction
     */
    public void setDirection(String direction) {
        this.dir = direction;
    }

    /**
     * Set the destination
     * @param destination     the destination
     */
    public void setDestination(String destination) {
        this.dest = destination;
    }
}
