package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoutePatternTest {
    RoutePattern rpNull, rp1, rp2, rp3;
    Route r;

    @BeforeEach
    public void setup() {
        r = new Route("0");
        rpNull = new RoutePattern("", "", "", r);
        rp1 = new RoutePattern("1", "", "", r);
        rp2 = new RoutePattern("2", "", "", r);
        rp3 = new RoutePattern("1", "", "", r);
    }

    @Test
    public void testConstructor() {
        assertEquals("", rpNull.getName());
        assertEquals("", rpNull.getDestination());
        assertEquals("", rpNull.getDirection());
        assertTrue(rpNull.getPath().isEmpty());
    }

    @Test
    public void testEquals() {
        assertFalse(rpNull.equals(null));
        assertFalse(rpNull.equals("Test String"));
        assertFalse(rpNull.equals(rp1));
        assertFalse(rpNull.hashCode() == rp1.hashCode());

        assertTrue(rp1.equals(rp3));
        assertTrue(rp1.hashCode() == rp3.hashCode());
    }

    @Test
    public void testSetAndGet() {
        List<LatLon> path = new ArrayList<>();
        path.add(new LatLon(0.0, 0.0));
        path.add(new LatLon(1.0, 1.0));
        rpNull.setPath(path);
        assertEquals(path, rpNull.getPath());

        rpNull.setDirection("EAST");
        assertEquals("EAST", rpNull.getDirection());

        rpNull.setDestination("UBC");
        assertEquals("UBC", rpNull.getDestination());
    }
}
