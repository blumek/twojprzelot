package pl.twojprzelot.backend.adapter.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class GeographicLocationEmbeddable {
    double latitude;
    double longitude;
}
