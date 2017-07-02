package br.com.crescer.redesocial.handler;

import br.com.crescer.redesocial.exceptions.NotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Carlos H. Nonnemacher
 */
@ControllerAdvice
public class HandlerException {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public HandlerExceptionMessage defaultErrorHandler(final HttpServletRequest httpServletRequest, final Exception exception) throws Exception {
        return makeHandler(exception);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public HandlerExceptionMessage notFoundHandler(final HttpServletRequest httpServletRequest, final Exception exception) throws Exception {
        return makeHandler(exception);
    }
    
    private HandlerExceptionMessage makeHandler(final Exception exception) {
        final HandlerExceptionMessage handlerExceptionMessage = new HandlerExceptionMessage();
        handlerExceptionMessage.setException(exception); // para evitar stacktrace
        handlerExceptionMessage.setMessage(createCustomExceptionMessage(exception));
        return handlerExceptionMessage;
    }

    private String createCustomExceptionMessage(final Exception exception) {
        return exception.getMessage();
    }

    public static class HandlerExceptionMessage {

        public HandlerExceptionMessage() {

        }

        Exception exception;
        String message;

        public Exception getException() {
            return exception;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
