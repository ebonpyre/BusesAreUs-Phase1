package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Arrival;
import ca.ubc.cs.cpsc210.translink.model.Bus;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StopTest {
    Stop sNull, s1, s2;
    LatLon l;
    Arrival a1, a2, a3;
    Bus b1, b2, b3;
    Route r;

    @BeforeEach
    public void setup() {
        l = new LatLon(0, 0);
        sNull = new Stop(0, "", l);
        s1 = new Stop(1, "", l);
        s2 = new Stop(2, "", l);
        r = new Route("44");
        b1 = new Bus(r, 0, 0, "", " ");
        b2 = new Bus(null, 0, 0, "", " ");
        b3 = new Bus(r, 0, 0, "", " ");
        a1 = new Arrival(10, "", r);
        a2 = new Arrival(5, "", r);
        a3 = new Arrival(1, "", r);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, sNull.getNumber());
        assertEquals("", sNull.getName());
        assertEquals(l, sNull.getLocn());
        assertTrue(sNull.getBuses().isEmpty());
        assertTrue(sNull.getRoutes().isEmpty());
    }

    @Test
    public void testRoutes() {
        r.addStop(sNull);
        sNull.addRoute(r);
        assertTrue(sNull.onRoute(r));
        assertTrue(sNull.getRoutes().contains(r));

        assertEquals(r, b1.getRoute());
        try {
            sNull.addBus(b1);
        } catch (RouteException e) {
            fail("Not supposed to go here.");
        }

        assertTrue(sNull.getBuses().contains(b1));
        try {
            sNull.addBus(b2);
            fail("Not supposed to go here.");
        } catch (RouteException e) {
            // intended
        }

        sNull.removeRoute(r);
        assertEquals(r, b3.getRoute());
        try {
            sNull.addBus(b3);
            fail("Not supposed to go here.");
        } catch (RouteException e) {
            // expected
        }

        assertEquals(1, sNull.getBuses().size());
        sNull.clearBuses();
        assertEquals(0, sNull.getBuses().size());
    }

    @Test
    public void testArrivals() {
        Arrival a4 = new Arrival(20, "", r);
        sNull.addArrival(a1);
        sNull.addArrival(a2);
        sNull.addArrival(a3);
        sNull.addArrival(a4);
        int count = 0;
        List<Arrival> arrivals = new ArrayList<>();
        List<Arrival> arrivalsExpected = new ArrayList<>();
        arrivalsExpected.add(a3);
        arrivalsExpected.add(a2);
        arrivalsExpected.add(a1);
        arrivalsExpected.add(a4);
        for (Arrival a : sNull) {
            count++;
            arrivals.add(a);
        }
        assertEquals(4, count);
        assertEquals(arrivalsExpected, arrivals);

        sNull.clearArrivals();
        count = 0;
        for (Arrival a : sNull) {
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void testEquals() {
        assertFalse(s1.equals(s2));
        assertFalse(s1.hashCode() == s2.hashCode());
        assertFalse(s1.equals(null));
        assertFalse(s1.equals("Test String"));

        Stop s3 = new Stop(1, "", l);
        assertTrue(s1.equals(s3));
        assertTrue(s1.hashCode() == s3.hashCode());
    }
}
