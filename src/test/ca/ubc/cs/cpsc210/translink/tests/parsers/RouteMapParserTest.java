package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.parsers.RouteMapParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Test the parser for route pattern map information
 */

// TODO: Write more tests

public class RouteMapParserTest {
    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    private int countNumRoutePatterns() {
        int count = 0;
        for (Route r : RouteManager.getInstance()) {
            for (RoutePattern rp : r.getPatterns()) {
                count ++;
            }
        }
        return count;
    }

    @Test
    public void testRouteParserNormal() {
        RouteMapParser p = new RouteMapParser("allroutemaps.txt");
        try {
            p.parse();
        } catch (IOException e) {
            fail("Not expected");
        }
        assertEquals(1232, countNumRoutePatterns());
    }

    @Test
    public void testRouteParserNoPoints() {
        RouteMapParser p = new RouteMapParser("allroutemapsnopoints.txt");
        try {
            p.parse();
        } catch (IOException e) {
            fail("Not expected");
        }
        assertEquals(2, countNumRoutePatterns());
    }

    @Test
    public void testRouteParserEmpty() {
        RouteMapParser p = new RouteMapParser("allroutemapsempty.txt");
        try {
            p.parse();
        } catch (IOException e) {
            fail("Not expected");
        }
        assertEquals(0, countNumRoutePatterns());
    }

    @Test
    public void testRouteParserIO() {
        RouteMapParser p = new RouteMapParser("allroutesdoesnotexist.txt");
        try {
            p.parse();
            fail("Not expected");
        } catch (IOException e) {
            // expected
        }
        assertEquals(0, countNumRoutePatterns());
    }
}
