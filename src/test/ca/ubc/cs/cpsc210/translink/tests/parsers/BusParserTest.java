package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.BusParser;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BusParserTest {
    @Test
    public void testBusLocationsParserNormal() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";
        s.addRoute(RouteManager.getInstance().getRouteWithNumber("014"));
        s.addRoute(RouteManager.getInstance().getRouteWithNumber("004"));

        try {
            data = new FileDataProvider("buslocations.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        try {
            BusParser.parseBuses(s, data);
        } catch (JSONException e) {
            fail("Not expected");
        }

        assertEquals(4, s.getBuses().size());
    }

    @Test
    public void testBusLocationsParserMissingField() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";
        s.addRoute(RouteManager.getInstance().getRouteWithNumber("014"));
        s.addRoute(RouteManager.getInstance().getRouteWithNumber("004"));

        try {
            data = new FileDataProvider("busmissingfields.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        try {
            BusParser.parseBuses(s, data);
        } catch (JSONException e) {
            fail("Not expected");
        }

        assertEquals(3, s.getBuses().size());
    }

    @Test
    public void testBusLocationsParserRouteException() {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";
        s.addRoute(RouteManager.getInstance().getRouteWithNumber("014"));
        s.addRoute(RouteManager.getInstance().getRouteWithNumber("004"));

        try {
            data = new FileDataProvider("busrouteexception.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        try {
            BusParser.parseBuses(s, data);
        } catch (JSONException e) {
            fail("Not expected");
        }

        assertEquals(3, s.getBuses().size());
    }
}
