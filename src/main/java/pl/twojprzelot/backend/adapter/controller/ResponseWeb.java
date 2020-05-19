package pl.twojprzelot.backend.adapter.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class ResponseWeb<T> {
    private Status status;
    private T data;
    private String message;

    public enum Status {
        @JsonProperty("success")
        SUCCESS,
        @JsonProperty("error")
        ERROR
    }
}
