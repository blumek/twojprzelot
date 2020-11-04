package pl.twojprzelot.backend.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Time {

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
