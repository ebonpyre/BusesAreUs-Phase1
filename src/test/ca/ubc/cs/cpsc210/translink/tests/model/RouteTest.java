package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteTest {
    Route rNull;

    @BeforeEach
    public void setup() {
        rNull = new Route("0");
    }

    @Test
    public void testConstructor() {
        assertEquals("", rNull.getName());
        assertEquals("0", rNull.getNumber());
        assertTrue(rNull.getPatterns().isEmpty());
        assertTrue(rNull.getStops().isEmpty());
    }

    @Test
    public void testAdd() {
        RoutePattern rp = new RoutePattern("", "", "", null);
        rNull.addPattern(rp);
        assertTrue(rNull.getPatterns().contains(rp));

        Stop s = new Stop(0, "", null);
        rNull.addStop(s);
        assertTrue(rNull.hasStop(s));
    }
}
