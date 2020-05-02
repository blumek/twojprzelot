package pl.twojprzelot.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@Builder(toBuilder = true)
@Value
public class Currency {
    int id;
    String name;
    String code;
    int isoNumber;
}
