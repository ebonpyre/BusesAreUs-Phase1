package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import ca.ubc.cs.cpsc210.translink.providers.DataProvider;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * A parser for the data returned by Translink stops query
 */
public class StopParser {

    private String filename;

    public StopParser(String filename) {
        this.filename = filename;
    }
    /**
     * Parse stop data from the file and add all stops to stop manager.
     *
     */
    public void parse() throws IOException, StopDataMissingException, JSONException{
        DataProvider dataProvider = new FileDataProvider(filename);

        parseStops(dataProvider.dataSourceToString());
    }
    /**
     * Parse stop information from JSON response produced by Translink.
     * Stores all stops and routes found in the StopManager and RouteManager.
     *
     * @param  jsonResponse    string encoding JSON data to be parsed
     * @throws JSONException when:
     * <ul>
     *     <li>JSON data does not have expected format (JSON syntax problem)</li>
     *     <li>JSON data is not an array</li>
     * </ul>
     * If a JSONException is thrown, no stops should be added to the stop manager
     * @throws StopDataMissingException when
     * <ul>
     *  <li> JSON data is missing Name, StopNo, Routes or location (Latitude or Longitude) elements for any stop</li>
     * </ul>
     * If a StopDataMissingException is thrown, all correct stops are first added to the stop manager.
     */

    public void parseStops(String jsonResponse)
            throws JSONException, StopDataMissingException {
        // TODO: Task 4: Implement this method
        JSONArray stops = new JSONArray(jsonResponse);
        Stop sFinal = null;
        for (int i = 0; i < stops.length(); i++) {
            try {
                JSONObject stop = stops.getJSONObject(i);
                Stop s = parseStop(stop);
                sFinal = StopManager.getInstance().getStopWithNumber(s.getNumber(),
                        s.getName(), s.getLocn());
                String routes = stop.getString("Routes");
                String[] routeList = routes.split(",", 0);
                for (String rNo : routeList) {
                    Route r = RouteManager.getInstance().getRouteWithNumber(rNo);
                    sFinal.addRoute(r);
                }
            } catch (StopDataMissingException e) {
                // nothing
            }
        }
    }

    public Stop parseStop (JSONObject stop) throws JSONException, StopDataMissingException {
        String name = "";
        int stopNo = 0;
        Double lat = 0.0, lon = 0.0;
        name = stop.getString("Name");
        stopNo = stop.getInt("StopNo");
        lat = stop.getDouble("Latitude");
        lon = stop.getDouble("Longitude");

        if (name == "" || stopNo == 0 || lat == 0.0 || lon == 0.0) {
            throw new StopDataMissingException();
        }
        return new Stop(stopNo, name, new LatLon(lat, lon));
    }
}
