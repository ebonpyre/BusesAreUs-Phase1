package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.parsers.RouteParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.RouteDataMissingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for the RouteParser
 */
// TODO: Write more tests

public class RouteParserTest {
    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    @Test
    public void testRouteParserNormal() {
        RouteParser p = new RouteParser("allroutes.json");
        try {
            p.parse();
        } catch (RouteDataMissingException e) {
            fail("Not expected");
        } catch (JSONException e) {
            fail("Not expected");
        } catch (IOException e) {
            fail("Not expected");
        }
        assertEquals(229, RouteManager.getInstance().getNumRoutes());
    }

    @Test
    public void testRouteParserMissing() {
        RouteParser p = new RouteParser("allroutesmissingfields.json");
        try {
            p.parse();
            fail("Not expected");
        } catch (RouteDataMissingException e) {
            // expected
        } catch (JSONException e) {
            fail("Not expected");
        } catch (IOException e) {
            fail("Not expected");
        }
        assertEquals(3, RouteManager.getInstance().getNumRoutes());
    }

    @Test
    public void testRouteParserMissingPattern() {
        RouteParser p = new RouteParser("allroutesmissingpatterns.json");
        try {
            p.parse();
            fail("Not expected");
        } catch (RouteDataMissingException e) {
            // expected
        } catch (JSONException e) {
            fail("Not expected");
        } catch (IOException e) {
            fail("Not expected");
        }
        assertEquals(1, RouteManager.getInstance().getNumRoutes());
    }

    @Test
    public void testRouteParserNotJSON() {
        RouteParser p = new RouteParser("allroutesnotjson.json");
        try {
            p.parse();
            fail("Not expected");
        } catch (RouteDataMissingException e) {
            fail("Not expected");
        } catch (JSONException e) {
            //expected
        } catch (IOException e) {
            fail("Not expected");
        }
        assertEquals(0, RouteManager.getInstance().getNumRoutes());
    }
}
