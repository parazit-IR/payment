package com.starzplay.video.payment.util;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
public class SpringUtil {

    private final MessageSource messageSource;

    public SpringUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, new Locale("fa", "IR"));
    }

}
