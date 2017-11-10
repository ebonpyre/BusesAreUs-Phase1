package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {
    Route rNull, r1;

    @BeforeEach
    public void setup() {
        rNull = new Route("0");
        r1 = new Route("44");
    }

    @Test
    public void testConstructor() {
        assertEquals("", rNull.getName());
        assertEquals("0", rNull.getNumber());
        assertTrue(rNull.getPatterns().isEmpty());
        assertTrue(rNull.getStops().isEmpty());
    }

    @Test
    public void testAddAndRemove() {
        RoutePattern rp = new RoutePattern("", "", "", rNull);
        assertTrue(rNull.getPatterns().contains(rp));

        Stop s = new Stop(0, "", null);
        rNull.addStop(s);
        assertTrue(rNull.hasStop(s));
        rNull.addStop(s);
        assertEquals(1, rNull.getStops().size());
        rNull.removeStop(s);
        assertEquals(0, rNull.getStops().size());
    }

    @Test
    public void testSetName() {
        r1.setName("Test");
        assertEquals("Test", r1.getName());
    }

    @Test
    public void testEquals() {
        r1.setName("Test");
        rNull.setName("Test");
        assertFalse(r1.equals(rNull));
        assertFalse(r1.equals(null));
        assertFalse(r1.equals("Test String"));

        Route r2 = new Route("44");
        assertTrue(r1.equals(r2));
        assertEquals(r1.hashCode(), r2.hashCode());
        assertEquals(r1.toString(), r2.toString());
    }

    @Test
    public void testGetPattern() {
        RoutePattern rpNull = new RoutePattern("", "", "", rNull);
        RoutePattern rp1 = new RoutePattern("1", "", "", rNull);
        RoutePattern rp2 = new RoutePattern("2", "", "", rNull);
        r1.addPattern(rpNull);
        r1.addPattern(rp1);
        r1.addPattern(rp2);
        assertEquals(rpNull, r1.getPattern(""));
        assertEquals(rp1, r1.getPattern("1", "", ""));
        assertEquals(rp2, r1.getPattern("2"));
        assertEquals(3, r1.getPatterns().size());

        RoutePattern rp3 = new RoutePattern("3", "", "", r1);
        assertEquals(rp3, r1.getPattern("3"));
        assertEquals(r1.getPattern("4"), new RoutePattern("4", "", "", r1));
        assertEquals(r1.getPattern("5", "", ""), new RoutePattern("5", "", "", r1));
    }

    @Test
    public void testIterator() {
        r1.addStop(new Stop(0, "", null));
        r1.addStop(new Stop(1, "", null));
        r1.addStop(new Stop(2, "", null));
        int count = 0;
        for (Stop s : r1) {
            count++;
        }
        assertEquals(3, count);
    }
}
