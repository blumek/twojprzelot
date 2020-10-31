package pl.twojprzelot.backend.application.spring_app.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.adapter.controller.ResponseWeb;
import pl.twojprzelot.backend.application.spring_app.exception.ApplicationExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

@Component
@RequiredArgsConstructor
final class FilterExceptionHandler {
    private final ApplicationExceptionHandler applicationExceptionHandler;
    private final ObjectMapper objectMapper;

    void handleAccessDeniedException(AccessDeniedException exception, HttpServletResponse response)
            throws IOException {
        ResponseEntity<ResponseWeb<Object>> errorResponse = applicationExceptionHandler
                .handleAccessDeniedException(exception);

        response.setStatus(errorResponse.getStatusCodeValue());

        MediaType contentType = errorResponse.getHeaders().getContentType();
        if (contentType != null)
            response.setContentType(contentType.toString());

        PrintWriter responseWriter = response.getWriter();
        responseWriter.print(objectMapper.writeValueAsString(errorResponse.getBody()));
        responseWriter.flush();
    }
}
