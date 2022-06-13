package com.peter.moon.config;

import com.peter.moon.common.EmptyBean;
import com.peter.moon.common.ResponseBean;
import com.peter.moon.exception.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

import static com.peter.moon.common.Config.ResponseCodeAndMsg.DATASET_REQ_PARAM_ERR_CODE;
import static com.peter.moon.common.Config.ResponseCodeAndMsg.DATASET_REQ_PARAM_ERR_MSG;

@RestControllerAdvice(basePackages = "com.peter.moon")
public class GlobalExceptionHandler {

    private static final Logger log = LogManager.getLogger();

    private static final String COMMON_ERR_CODE = "9999";
    private static final String COMMON_ERR_MSG = "系统繁忙,请稍后重试";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBean<EmptyBean> handleValidationExceptions(MethodArgumentNotValidException ex) {
        EmptyBean emptyBean = new EmptyBean();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();
//            emptyBean.put(fieldName, errorMessage);
        });
        return new ResponseBean<>(DATASET_REQ_PARAM_ERR_CODE, DATASET_REQ_PARAM_ERR_MSG, emptyBean);

    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseBean<EmptyBean> handler(BusinessException e) {
        log.error("#### global handle BusinessException ####", e);
        return new ResponseBean<>(e.getCode(), e.getMessage(), new EmptyBean());

    }

//    @ExceptionHandler(KgAccessException.class)
//    @ResponseBody
//    public ResponseBean<EmptyBean> handlerKgAccessException(KgAccessException e) {
//        log.error("#### global handle KgAccessException ####", e);
//        return new ResponseBean<>(AUTH_UNAUTHORIZED_CODE, e.getMsg(), new EmptyBean());
//
//    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseBean<String> commonHandler(Throwable e) throws AccessDeniedException {
        //由于WebPermissionEvaluator中抛出的AccessDeniedException异常会优先被这里抓住
        //然而希望AccessDeniedException异常被JwtRestAccessDeniedHandler处理
        //因此这里不需要处理AccessDeniedException,直接抛出
        if (e instanceof AccessDeniedException) {
            throw (AccessDeniedException) e;
        }
        String uuid = UUID.randomUUID().toString();
        log.error("#### global handle exception ### uuid {} ####", uuid, e);
        return new ResponseBean<>(COMMON_ERR_CODE, COMMON_ERR_MSG, uuid);

    }
}
