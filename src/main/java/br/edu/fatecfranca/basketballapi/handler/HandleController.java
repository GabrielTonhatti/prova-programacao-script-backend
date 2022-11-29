package br.edu.fatecfranca.basketballapi.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleController {

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(ErrorException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> validationError(ErrorException ex) {
        return List.of(new ErrorMessage(ex.getMessage(), ex.getField()));
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> bindValidationError(BindException ex) {
        return createMessageFromValidationException(ex.getBindingResult());
    }

    private List<ErrorMessage> createMessageFromValidationException(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    var message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                    return new ErrorMessage(message, fieldError.getField());
                })
                .collect(Collectors.toList());
    }
}