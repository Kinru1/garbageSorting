package com.lin.garbagesorting.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.util.StrUtil;
import com.google.protobuf.ServiceException;
import com.lin.garbagesorting.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public R duplicateKeyException(DuplicateKeyException e) {
        log.error("数据添加错误", e);
        return R.error(500, "数据重复");
    }

    @ExceptionHandler(value = NotPermissionException.class)
    public R notPermissionException(NotPermissionException e) {
        log.error("权限验证错误", e);
        return R.error(401, "无权限");
    }

    @ExceptionHandler(value = SaTokenException.class)
    public R notLoginException(SaTokenException e) {
        log.error("权限验证错误", e);
        return R.error(401, "请登录");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg;
        try {
            msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } catch (Exception be) {
            msg = "";
        }
        log.warn("参数校验错误", e);
        return R.error(msg);
    }

//    @ExceptionHandler(value = ServiceException.class)
//    public R serviceExceptionError(ServiceException e) {
//        String code = e.getCode();
//        if (StrUtil.isNotBlank(code)) {
//            return R.error(code, e.getMessage());
//        }
//        return R.error(e.getMessage());
//    }


    @ExceptionHandler(value = Exception.class)
    public R exceptionError(Exception e) {
        log.error("未知错误", e);
        return R.error("未知错误");
    }
    @ExceptionHandler(value = RuntimeException.class)
    public R RuntimeException(Exception e) {
        log.error("运行异常", e);
        return R.error("运行异常");
    }
    @ExceptionHandler(NullPointerException.class)
    public R handleTypeMismatchException(NullPointerException ex) {
        log.error("空指针异常，{}", ex.getMessage());
        return  R.error("：账号不存在");
    }


}
