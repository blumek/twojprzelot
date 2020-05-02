package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;
}
