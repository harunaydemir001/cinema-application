package com.harun.common.util;

import com.harun.common.constant.GeneralErrorCodeConstants;
import com.harun.common.exception.ResponseException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

    private final MessageSource commonMessageSource;

    private MessageSourceAccessor commonAccessor;

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @Autowired
    public MessageUtil(MessageSource messageSource) {
        ReloadableResourceBundleMessageSource commonMessageSourceLocal = new ReloadableResourceBundleMessageSource();
        commonMessageSourceLocal.setBasename("classpath:messages");
        commonMessageSourceLocal.setDefaultEncoding("UTF-8");
        this.commonMessageSource = commonMessageSourceLocal;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
        commonAccessor = new MessageSourceAccessor(commonMessageSource);
    }

    public String get(String code, String[] params) {
        String message;

        try {
            message = accessor.getMessage(code, params);
        } catch (NoSuchMessageException ex) {
            message = commonAccessor.getMessage(code, params);
        }
        return message;
    }

    public String get(String code) {
        String message;
        try {
            message = accessor.getMessage(code);
        } catch (NoSuchMessageException ex) {
            try {
                message = commonAccessor.getMessage(code);
            } catch (NoSuchMessageException e) {
                Object[] arg = new Object[1];
                arg[0] = code;
                throw new ResponseException(GeneralErrorCodeConstants.NO_SUCH_MESSAGE, get(GeneralErrorCodeConstants.NO_SUCH_MESSAGE, arg), HttpStatus.NOT_FOUND);
            }
        }
        return message;
    }

    public String get(String code, Object[] args) {
        String message;

        try {
            message = accessor.getMessage(code, args);
        } catch (NoSuchMessageException ex) {
            message = commonAccessor.getMessage(code, args);
        }
        return message;
    }
}
