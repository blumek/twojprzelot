package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity {
    @Id
    private String id;
}
