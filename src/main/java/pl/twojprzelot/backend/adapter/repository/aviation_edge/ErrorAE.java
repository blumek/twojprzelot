package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class ErrorAE {
    @JsonProperty("error")
    private String message;

    private boolean success;
}
