package tutorial.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "LoggingFilter", urlPatterns = {"/*"})
public class LoggingFilter implements Filter {

  Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException { /* */ }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    logger.info(
        String.format("Ricevuta richiesta per %s da ip %s",
            ((HttpServletRequest) request).getRequestURI(), 
            request.getRemoteAddr()));
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {/* */ }
  
}
