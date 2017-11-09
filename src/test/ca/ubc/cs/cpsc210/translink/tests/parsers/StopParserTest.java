package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.StopParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests for the StopParser
 */

// TODO: Write more tests

public class StopParserTest {
    @BeforeEach
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testStopParserNormal() {
        StopParser p = new StopParser("stops.json");
        try{
            p.parse();
        } catch (StopDataMissingException e) {
            fail ("Not expected");
        } catch (JSONException e) {
            fail ("Not expected");
        } catch (IOException e) {
            fail ("Not expected");
        }
        assertEquals(8524, StopManager.getInstance().getNumStops());
    }

    @Test
    public void testStopParserNotJSON() {
        StopParser p = new StopParser("stopsnotjson.json");
        try{
            p.parse();
            fail ("Not expected");
        } catch (StopDataMissingException e) {
            fail ("Not expected");
        } catch (JSONException e) {
            // expected
        } catch (IOException e) {
            fail ("Not expected");
        }
        assertEquals(0, StopManager.getInstance().getNumStops());
    }

    @Test
    public void testStopParserMissingData() {
        StopParser p = new StopParser("stopsmissingdata.json");
        try{
            p.parse();
            fail ("Not expected");
        } catch (StopDataMissingException e) {
            // expected
        } catch (JSONException e) {
            fail ("Not expected");
        } catch (IOException e) {
            fail ("Not expected");
        }
        assertEquals(1, StopManager.getInstance().getNumStops());
    }

    @Test
    public void testStopParserMissingRoutes() {
        StopParser p = new StopParser("stopsmissingroutes.json");
        try{
            p.parse();
            fail ("Not expected");
        } catch (StopDataMissingException e) {
            // expected
        } catch (JSONException e) {
            fail ("Not expected");
        } catch (IOException e) {
            fail ("Not expected");
        }
        assertEquals(2, StopManager.getInstance().getNumStops());
    }
}
