# Data

Source: community BMTC GTFS feed (github.com/Vonter/bmtc-gtfs).
Official source to compare later: tdh.dult-karnataka.com.
Downloaded: 19 Oct 2026. Files are not committed; re-download with the steps in the main README.

- stops.txt: 9,960 stops (one per direction).
  Columns: stop_name, parent_station, zone_id, stop_id, stop_desc, stop_lat, stop_lon, location_type, platform_code
- routes.txt: 4,434 routes.
  Columns: route_long_name, route_short_name (bus number), agency_id, route_type, route_id
- Parse by column name, not position. Read files as UTF-8.