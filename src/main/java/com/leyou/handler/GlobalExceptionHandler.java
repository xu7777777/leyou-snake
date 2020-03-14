package com.leyou.handler;

import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.Output;
import com.leyou.util.CommonUtil;
import com.leyou.util.OutputUtil;
import com.leyou.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 异常统一处理
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Output global(Exception ex){
        String activeProfile = SpringContextUtils.getActiveProfile();
        String stackTrace = CommonUtil.getStackTrace(ex);
        if (activeProfile.equals("dev")) {   //测试环境
            log.info("GlobalExceptionHandler...错误信息：");
            log.info(stackTrace);
        } else {    //生产环境
            log.info("GlobalExceptionHandler...错误信息：");
            log.info(stackTrace);
        }
        return OutputUtil.fail(ErrorEnum.APP_ERR.getCode(), ErrorEnum.APP_ERR.getMsg());

    }

    /**
     * 校验规则绑定到entity 验证失败
     * @param ex 绑定异常
     * @return output
     */
    @ResponseBody
    @ExceptionHandler({BindException.class, ConstraintViolationException.class})
    public Output validError(Exception ex){
        StringBuilder msg = new StringBuilder();
        if (ex instanceof BindException) {
            BindingResult result = ((BindException)ex).getBindingResult();
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();
                for(ObjectError fieldError : errors){
                    String errMsg = fieldError.getDefaultMessage();
                    msg.append(errMsg);
                }
            }
        } else if (ex instanceof ConstraintViolationException) {
            msg = new StringBuilder(ex.getMessage());
        }

        return OutputUtil.fail(ErrorEnum.VALID_ERR.getCode(), msg.toString());
    }

}