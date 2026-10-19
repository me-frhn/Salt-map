package saltmap;

import java.nio.file.Path;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Path dir = Path.of(args.length > 0 ? args[0] : "../data/gtfs");

        Map<String, Stop> stops = GtfsLoader.loadStops(dir);
        Map<String, Route> routes = GtfsLoader.loadRoutes(dir);

        System.out.println("Stops:  " + stops.size());
        System.out.println("Routes: " + routes.size());

        System.out.println("\nStops with 'Kempegowda' in the name:");
        stops.values().stream()
            .filter(s -> s.name().contains("Kempegowda"))
            .limit(5)
            .forEach(s -> System.out.println("  " + s.id() + " | " + s.desc() + " | " + s.lat() + ", " + s.lon()));

        System.out.println("\nRoutes numbered 296*:");
        routes.values().stream()
            .filter(r -> r.shortName().startsWith("296"))
            .forEach(r -> System.out.println("  " + r.id() + " | " + r.shortName()));
    }
}