package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test the StopManager
 */
public class StopManagerTest {

    @BeforeEach
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testBasic() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop r = StopManager.getInstance().getStopWithNumber(9999);
        assertEquals(s9999, r);
    }

    @Test
    public void testDist() {
        Stop s1 = new Stop(1, "My house", new LatLon(0, 0));
        Stop s2 = new Stop(2, "My house", new LatLon(1000, 200));
        Stop s3 = new Stop(3, "My house", new LatLon(-50, -100));
        Stop r1 = StopManager.getInstance().getStopWithNumber(1, "My house", new LatLon(0, 0));
        assertEquals(s1, r1);

        Stop r2 = StopManager.getInstance().getStopWithNumber(2, "My house", new LatLon(1000, 200));
        assertEquals(s2, r2);

        Stop r3 = StopManager.getInstance().getStopWithNumber(3, "My house", new LatLon(-50, -100));
        assertEquals(s3, r3);

        assertEquals(3, StopManager.getInstance().getNumStops());
        assertEquals(s1, StopManager.getInstance().findNearestTo(new LatLon(0, 1)));
        assertEquals(s3, StopManager.getInstance().findNearestTo(new LatLon(-50, -50)));
        assertEquals(s2, StopManager.getInstance().findNearestTo(new LatLon(1000, 1)));
    }
}
