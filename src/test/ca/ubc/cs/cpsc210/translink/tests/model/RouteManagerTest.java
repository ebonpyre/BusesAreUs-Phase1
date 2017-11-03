package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test the RouteManager
 */
public class RouteManagerTest {

    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    @Test
    public void testBasic() {
        Route r43 = new Route("43");
        Route r = RouteManager.getInstance().getRouteWithNumber("43");
        assertEquals(r43, r);
    }

    @Test
    public void testGet() {
        Route r44 = new Route("44");
        Route r = RouteManager.getInstance().getRouteWithNumber("44");
        assertEquals(r44, r);

        Route r45 = new Route("45");
        r45.setName("Test");
        Route r2 = RouteManager.getInstance().getRouteWithNumber("45", "Test");
        assertEquals(r45, r2);

        assertEquals(2, RouteManager.getInstance().getNumRoutes());
        RouteManager.getInstance().clearRoutes();
        assertEquals(0, RouteManager.getInstance().getNumRoutes());
    }
}
