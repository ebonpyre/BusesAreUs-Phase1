package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Arrival;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.ArrivalsParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.ArrivalsDataMissingException;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Test the ArrivalsParser
 */
public class ArrivalsParserTest {

    @Test
    public void testConstructor() {
        ArrivalsParser a = new ArrivalsParser();  // for the coverage XD
    }

    @Test
    public void testArrivalsParserNormal() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearArrivals();
        String data = "";
        try {
            data = new FileDataProvider("arrivals43.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the arrivals data");
        }
        try {
            ArrivalsParser.parseArrivals(s, data);
        } catch (JSONException e) {
            fail("Not expected");
        } catch (ArrivalsDataMissingException e) {
            fail("Not expected");
        }
        int count = 0;
        for (Arrival a : s) {
            assertTrue(a.getTimeToStopInMins() <= 120);
            count++;
        }
        assertEquals(6, count);
    }

    @Test
    public void testArrivalsParserNotJSON() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearArrivals();
        String data = "";
        try {
            data = new FileDataProvider("arrivalsnotjson.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the arrivals data");
        }
        try {
            ArrivalsParser.parseArrivals(s, data);
            fail("Not expected");
        } catch (JSONException e) {
            // expected
        } catch (ArrivalsDataMissingException e) {
            fail("Not expected");
        }
        int count = 0;
        for (Arrival a : s) {
            assertTrue(a.getTimeToStopInMins() <= 120);
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void testArrivalsParserMissingData() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearArrivals();
        String data = "";
        try {
            data = new FileDataProvider("arrivalsmissingdata.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the arrivals data");
        }
        try {
            ArrivalsParser.parseArrivals(s, data);
        } catch (JSONException e) {
            fail("Not expected");
        } catch (ArrivalsDataMissingException e) {
            fail("Not expected");
        }
        int count = 0;
        for (Arrival a : s) {
            assertTrue(a.getTimeToStopInMins() <= 120);
            count++;
        }
        assertEquals(4, count);
    }

    @Test
    public void testArrivalsParserMissingSchedules() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearArrivals();
        String data = "";
        try {
            data = new FileDataProvider("arrivalsmissingschedules.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the arrivals data");
        }
        try {
            ArrivalsParser.parseArrivals(s, data);
        } catch (JSONException e) {
            fail("Not expected");
        } catch (ArrivalsDataMissingException e) {
            fail("Not expected");
        }
        int count = 0;
        for (Arrival a : s) {
            assertTrue(a.getTimeToStopInMins() <= 120);
            count++;
        }
        assertEquals(5, count);
    }

    @Test
    public void testArrivalsParserArrivalException() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearArrivals();
        String data = "";
        try {
            data = new FileDataProvider("arrivalsnodata.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the arrivals data");
        }
        try {
            ArrivalsParser.parseArrivals(s, data);
            fail("Not expected");
        } catch (JSONException e) {
            fail("Not expected");
        } catch (ArrivalsDataMissingException e) {
            // expected
        }
        int count = 0;
        for (Arrival a : s) {
            assertTrue(a.getTimeToStopInMins() <= 120);
            count++;
        }
        assertEquals(0, count);
    }
}
