package pl.twojprzelot.backend.application.spring_app.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Slf4j
@RequiredArgsConstructor
final class AuthenticationFilter implements Filter {
    private final String applicationKeyHeaderName;
    private final String applicationKey;
    private final FilterExceptionHandler filterExceptionHandler;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String appKey = request.getHeader(applicationKeyHeaderName);
        if (!isValidApplicationKey(appKey)) {
            log.warn("Passed invalid application key: {}", appKey);

            HttpServletResponse response = (HttpServletResponse) servletResponse;

            filterExceptionHandler.handleAccessDeniedException(new AccessDeniedException("Wrong application key"), response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isValidApplicationKey(String appKey) {
        return applicationKey.equals(appKey);
    }
}
