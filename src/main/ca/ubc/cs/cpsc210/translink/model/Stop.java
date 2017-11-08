package ca.ubc.cs.cpsc210.translink.model;

import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.util.*;

/**
 * Represents a bus stop with an number, name, location (lat/lon)
 * set of routes which stop at this stop, list of buses that pass
 * through this stop and a list of arrivals.
 */
// TODO: Task 2: Complete all the methods of this class

public class Stop implements Iterable<Arrival> {
    private List<Arrival> arrivals;
    private Set<Route> routes;
    private List<Bus> buses;
    private int number;
    private String name;
    private LatLon locn;

    /**
     * Constructs a stop with given number, name and location.
     * Set of routes and list of arrivals are empty.
     *
     * @param number    the number of this stop
     * @param name      name of this stop
     * @param locn      location of this stop
     */
    public Stop(int number, String name, LatLon locn) {
        this.number = number;
        setName(name);
        setLocn(locn);

        arrivals = new ArrayList<>();
        routes = new HashSet<>();
        buses = new ArrayList<>();
    }

    /**
     * getter for name
     * @return      the name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for locn
     * @return      the location
     */
    public LatLon getLocn() {
        return locn;
    }

    /**
     * getter for number
     * @return      the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * getter for set of routes
     * @return      an unmodifiable set of routes using this stop
     */
    public Set<Route> getRoutes() {
        return Collections.unmodifiableSet(routes);
    }

    /**
     * Add route to set of routes with stops at this stop.
     *
     * @param route  the route to add
     */
    public void addRoute(Route route) {
        routes.add(route);
    }

    /**
     * Remove route from set of routes with stops at this stop
     *
     * @param route the route to remove
     */
    public void removeRoute(Route route) {
        routes.remove(route);
    }

    /**
     * Determine if this stop is on a given route
     * @param route  the route
     * @return  true if this stop is on given route
     */
    public boolean onRoute(Route route) {
        return route.getStops().contains(this);
    }

    /**
     * Add bus arrival travelling on a particular route at this stop.
     * Arrivals are to be sorted in order by arrival time
     *
     * @param arrival  the bus arrival to add to stop
     */
    public void addArrival(Arrival arrival) {
        int flag = 0;
        int index = 0;
        for (Arrival a : arrivals) {
            if (a.compareTo(arrival) > 0) {
                arrivals.add(index, arrival);
                flag = 1;
                break;
            }
            index++;
        }
        if (flag == 0) {
            arrivals.add(arrival);
        }
    }

    /**
     * Remove all arrivals from this stop
     */
    public void clearArrivals() {
        arrivals.clear();
    }

    /**
     * Adds bus that is on a route passing through this stop
     * @param bus  bus to add
     * @throws RouteException if bus is not on a route on which this stop lies
     */
    public void addBus(Bus bus) throws RouteException {
        if (this.routes.contains(bus.getRoute())) {
            buses.add(bus);
        }
        else {
            throw new RouteException("");
        }
    }

    /**
     * Get unmodifiable list of buses on routes serving this stop
     * @return  unmodifiable list of buses
     */
    public List<Bus> getBuses() {
        return Collections.unmodifiableList(buses);
    }

    /**
     * Clear all buses from this stop
     */
    public void clearBuses() {
        buses.clear();
    }

    /**
     * Two stops are equal if their numbers are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() == Stop.class) {
            return (o.hashCode() == this.hashCode());
        }
        else {
            return false;
        }
    }

    /**
     * Two stops are equal if their numbers are equal.
     * Therefore hashCode only pays attention to number.
     */
    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public Iterator<Arrival> iterator() {
        // Do not modify the implementation of this method!
        return arrivals.iterator();
    }

    /**
     * setter for name
     *
     * @param name      the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for location
     * @param locn      the new location
     */
    public void setLocn(LatLon locn) {
        this.locn = locn;
    }
}
