package com.dqys.core.base;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * Created by pan on 16-5-20.
 */
public abstract class BaseApiContorller {

    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public JsonResponse serverErrException(Exception e) {
        e.printStackTrace();
        return JsonResponseTool.serverErr();
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class})
    public JsonResponse illegalException(Exception e) {
        e.printStackTrace();
        return JsonResponseTool.illegalRequest();
    }

    @ExceptionHandler({NoSuchRequestHandlingMethodException.class})
    public JsonResponse notFoundException(Exception e) {
        e.printStackTrace();
        return JsonResponseTool.notFound();
    }
}
