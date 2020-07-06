package com.together.advice;

import com.together.enun.TipMsgEnum;
import com.together.util.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Agu
 */
@ControllerAdvice
public class BaseExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public R customException(Exception e) {
        TipMsgEnum tipMsgEnum = TipMsgEnum.forMsg(e.getMessage());
        return R.error(tipMsgEnum);
    }

}
