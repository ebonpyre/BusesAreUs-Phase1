package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutePatternTest {
    RoutePattern rpNull;

    @BeforeEach
    public void setup() {
        rpNull = new RoutePattern("", "", "", null);

    }

    @Test
    public void testConstructor() {
        assertEquals("", rpNull.getName());
        assertEquals("", rpNull.getDestination());
        assertEquals("", rpNull.getDirection());
        assertTrue(rpNull.getPath().isEmpty());
    }
}
