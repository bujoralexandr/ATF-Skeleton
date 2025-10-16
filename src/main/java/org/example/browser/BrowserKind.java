package org.example.browser;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public enum BrowserKind {
    CHROMIUM, FIREFOX, WEBKIT;

    private static final String SUPPORTED = Arrays.stream(values())
            .map(e -> e.name().toLowerCase(Locale.ROOT))
            .collect(Collectors.joining(","));

    public static BrowserKind from(String raw) {
        return Optional.ofNullable(raw)
                .map(String::trim)
                .flatMap(s -> Arrays.stream(values())
                        .filter(k -> k.name().equalsIgnoreCase(s))
                        .findFirst())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported browser type: " + raw + ". Supported types are: " + SUPPORTED + "."));
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }
}
