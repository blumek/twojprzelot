package pl.twojprzelot.backend.application.spring_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.twojprzelot.backend.adapter.controller.ResponseWeb;

import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.ERROR;

@ControllerAdvice
class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ResponseWeb<Object>> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseWeb.builder()
                        .status(ERROR)
                        .message(exception.getMessage())
                        .build());
    }

}
