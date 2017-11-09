package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.providers.DataProvider;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for routes stored in a compact format in a txt file
 */
public class RouteMapParser {
    private String fileName;

    public RouteMapParser(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Parse the route map txt file
     */
    public void parse() throws IOException {
        DataProvider dataProvider = new FileDataProvider(fileName);
        String c = dataProvider.dataSourceToString();
        if (!c.equals("")) {
            int posn = 0;
            while (posn < c.length()) {
                int endposn = c.indexOf('\n', posn);
                String line = c.substring(posn, endposn);
                parseOnePattern(line);
                posn = endposn + 1;
            }
        }
    }

    /**
     * Parse one route pattern, adding it to the route that is named within it
     * @param str
     *
     * Each line begins with a capital N, which is not part of the route number, followed by the
     * bus route number, a dash, the pattern name, a semicolon, and a series of 0 or more real
     * numbers corresponding to the latitude and longitude (in that order) of a point in the pattern,
     * separated by semicolons. The 'N' that marks the beginning of the line is not part of the bus
     * route number.
     */
    private void parseOnePattern(String str) {
        // TODO: Task 3: Implement this method
        String routeNumber, patternName, first;
        List<LatLon> elements = new ArrayList<>();
        String[] strings, splitFirst;
        strings = str.split(";");
        first = strings[0];
        splitFirst = first.split("-", 2);
        splitFirst[0] = splitFirst[0].substring(1,splitFirst[0].length());
        routeNumber = splitFirst[0];
        patternName = splitFirst[1];
        for (int i = 1; i < strings.length; i+=2) {
            LatLon temp = new LatLon(Double.valueOf(strings[i]), Double.valueOf(strings[i+1]));
            elements.add(temp);
        }

        storeRouteMap(routeNumber, patternName, elements);
    }

    /**
     * Store the parsed pattern into the named route
     * Your parser should call this method to insert each route pattern into the corresponding route object
     * There should be no need to change this method
     *
     * @param routeNumber       the number of the route
     * @param patternName       the name of the pattern
     * @param elements          the coordinate list of the pattern
     */
    private void storeRouteMap(String routeNumber, String patternName, List<LatLon> elements) {
        Route r = RouteManager.getInstance().getRouteWithNumber(routeNumber);
        RoutePattern rp = r.getPattern(patternName);
        rp.setPath(elements);
    }
}
