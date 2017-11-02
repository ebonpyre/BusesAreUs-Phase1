package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Arrival;
import ca.ubc.cs.cpsc210.translink.model.Bus;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class StopTest {
    Stop sNull;
    LatLon l;
    Arrival a1, a2, a3;
    Bus b1, b2;
    Route r;

    @BeforeEach
    public void setup() {
        l = new LatLon(0, 0);
        sNull = new Stop(0, "", l);
        r = new Route("44");
        b1 = new Bus(r, 0, 0, "", " ");
        b2 = new Bus(null, 0, 0, "", " ");
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
    public void testAdd() {
        sNull.addRoute(r);
        assertTrue(sNull.getRoutes().contains(r));

        assertEquals(r, b1.getRoute());
        try {
            sNull.addBus(b1);
        } catch (RouteException e) {
            fail("");
        }
        assertTrue(sNull.getBuses().contains(b1));

        try {
            sNull.addBus(b2);
            fail("Not supposed to succeed.");
        } catch (RouteException e) {
            // intended
        }
    }
}
