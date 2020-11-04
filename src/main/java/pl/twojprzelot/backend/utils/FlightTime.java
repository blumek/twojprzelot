package pl.twojprzelot.backend.utils;

import lombok.NoArgsConstructor;
import pl.twojprzelot.backend.domain.entity.FlightEndpointDetails;

import java.time.LocalDateTime;
import java.util.OptionalLong;

import static java.time.temporal.ChronoUnit.MILLIS;
import static lombok.AccessLevel.PRIVATE;
import static pl.twojprzelot.backend.utils.Time.now;

@NoArgsConstructor(access = PRIVATE)
public class FlightTime {

    public static OptionalLong millisUntilNow(FlightEndpointDetails flightEndpointDetails) {
        LocalDateTime scheduledTime = flightEndpointDetails.getScheduledTime();
        if (scheduledTime != null) {
            return OptionalLong.of(MILLIS.between(now(), scheduledTime));
        }

        LocalDateTime estimatedTime = flightEndpointDetails.getEstimatedTime();
        if (estimatedTime != null) {
            return OptionalLong.of(MILLIS.between(now(), estimatedTime));
        }

        LocalDateTime actualTime = flightEndpointDetails.getActualTime();
        if (actualTime != null)
            return OptionalLong.of(MILLIS.between(now(), actualTime));

        return OptionalLong.empty();
    }
}
