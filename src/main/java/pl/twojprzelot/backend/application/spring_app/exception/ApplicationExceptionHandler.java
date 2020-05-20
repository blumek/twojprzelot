package pl.twojprzelot.backend.application.spring_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.twojprzelot.backend.adapter.controller.ResponseWeb;

import java.nio.file.AccessDeniedException;

import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.ERROR;

@ControllerAdvice
public final class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWeb<Object>> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseWeb.builder()
                        .status(ERROR)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseWeb<Object>> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ResponseWeb.builder()
                        .status(ERROR)
                        .message(exception.getMessage())
                        .build());
    }

}
