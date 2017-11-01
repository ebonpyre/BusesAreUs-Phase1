package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Bus;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusTest {
    Bus testBusNull;

    @BeforeEach
    public void setup() {
        testBusNull = new Bus(null, 0, 0, null, null);
    }

    @Test
    public void testConstructor() {
        LatLon temp = new LatLon(0, 0);
        assertEquals(null, testBusNull.getRoute());
        assertEquals(temp, testBusNull.getLatLon());
        assertEquals(null, testBusNull.getDestination());
        assertEquals(null, testBusNull.getTime());
    }
}
