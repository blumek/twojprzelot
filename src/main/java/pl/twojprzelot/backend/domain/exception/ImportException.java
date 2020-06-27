package pl.twojprzelot.backend.domain.exception;

public class ImportException extends RuntimeException {
    public ImportException(String message) {
        super(message);
    }
}
