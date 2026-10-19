package saltmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Csv {

    public static void forEachRow(Path file, Consumer<Map<String, String>> action) throws IOException {
        try (BufferedReader r = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line = r.readLine();
            if (line == null) return;
            if (line.startsWith("\uFEFF")) line = line.substring(1); // strip BOM
            List<String> header = split(line);
            while ((line = r.readLine()) != null) {
                if (line.isBlank()) continue;
                List<String> values = split(line);
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < header.size() && i < values.size(); i++) {
                    row.put(header.get(i), values.get(i));
                }
                action.accept(row);
            }
        }
    }

    // Splits one CSV line, handling "quoted, fields" and "" escapes.
    static List<String> split(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        sb.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    sb.append(c);
                }
            } else if (c == '"') {
                inQuotes = true;
            } else if (c == ',') {
                out.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        out.add(sb.toString());
        return out;
    }
}