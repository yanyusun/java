package com.dqys.core.utils;

import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.PagingResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by pan on 16-4-8.
 */
public abstract class ApiParseTool {

    /**
     * 从NoSQL获取API接口定义，并转换
     *
     * @param t
     * @param key
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static <T> Map<Object, Object> parseApiList(T t, String key) throws NoSuchFieldException, IllegalAccessException {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        for (String field : SysPropertyTool.getProperty(SysPropertyTypeEnum.API, key).getPropertyValue().split(",")) {
            Field fieldTmp = null;
            try {
                fieldTmp = t.getClass().getDeclaredField(field.trim());
            } catch (NoSuchFieldException e) {
                fieldTmp = t.getClass().getSuperclass().getDeclaredField(field.trim());
            } catch (SecurityException e) {
                throw e;
            }
            fieldTmp.setAccessible(true);
            resultMap.put(field, fieldTmp.get(t));
        }

        return resultMap;
    }

    /**
     * 从NoSQL获取API接口定义，并转换
     *
     * @param datas
     * @param key
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static <T> List<Map<Object, Object>> parseApiList(List<T> datas, String key) throws NoSuchFieldException, IllegalAccessException {
        List<Map<Object, Object>> resultL = new ArrayList<Map<Object, Object>>();
        for (T t : datas) {
            Map<Object, Object> resultMap = parseApiList(t, key);
            resultL.add(resultMap);
        }

        return resultL;
    }

    /**
     * 从NoSQL获取API接口定义，并转换
     *
     * @param pagingResult
     * @param key
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static <T> PagingResult<Map<Object, Object>> parseApiList(PagingResult<T> pagingResult, String key) throws NoSuchFieldException, IllegalAccessException {
        List<Map<Object, Object>> resultL = parseApiList(pagingResult.getData(), key);
        return new PagingResult<Map<Object, Object>>(pagingResult.getPageNum(), pagingResult.getCurPage(), resultL);
    }
}
