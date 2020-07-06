package com.together.advice;

import com.together.enun.TipMsgEnum;
import com.together.util.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Agu
 */
@ControllerAdvice
public class BaseExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R customException(Exception e) {
        e.printStackTrace();
        TipMsgEnum tipMsgEnum = TipMsgEnum.forMsg(e.getMessage());
        return R.error(tipMsgEnum);
    }

}
