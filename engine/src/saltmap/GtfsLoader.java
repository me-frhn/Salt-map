package saltmap;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GtfsLoader {

    public static Map<String, Stop> loadStops(Path dir) throws IOException {
        Map<String, Stop> stops = new HashMap<>();
        Csv.forEachRow(dir.resolve("stops.txt"), r -> {
            String lat = r.get("stop_lat"), lon = r.get("stop_lon");
            if (lat == null || lat.isBlank() || lon == null || lon.isBlank()) return;
            stops.put(r.get("stop_id"), new Stop(
                r.get("stop_id"), r.get("stop_name"), r.get("stop_desc"),
                Double.parseDouble(lat), Double.parseDouble(lon)));
        });
        return stops;
    }

    public static Map<String, Route> loadRoutes(Path dir) throws IOException {
        Map<String, Route> routes = new HashMap<>();
        Csv.forEachRow(dir.resolve("routes.txt"), r ->
            routes.put(r.get("route_id"), new Route(
                r.get("route_id"), r.get("route_short_name"), r.get("route_long_name"))));
        return routes;
    }
}