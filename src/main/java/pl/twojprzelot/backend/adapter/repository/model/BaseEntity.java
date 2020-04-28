package pl.twojprzelot.backend.adapter.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Id
    private String id;
}
