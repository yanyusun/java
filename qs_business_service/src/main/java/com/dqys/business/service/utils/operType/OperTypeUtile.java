package com.dqys.business.service.utils.operType;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化
 * Created by mkfeng on 2016/7/7.
 */
@Component
public class OperTypeUtile implements ApplicationContextAware {
    private static RedisTemplate<String, Object> redisTemplate;
    private static OperTypeService operTypeService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        operTypeService = applicationContext.getBean(OperTypeService.class);
        //// TODO: 16-9-18  上线要去掉下面的注释
//        opertype();
    }

    public void opertype() {
        List<Integer> userIds = operTypeService.selectByUserIds();//?????
        List<Integer> roleIds = operTypeService.selectByRoleIds();
        List<Integer> objectIds = operTypeService.selectByObjectIds();
        for (Integer use : userIds) {
            for (Integer rol : roleIds) {
                for (Integer obj : objectIds) {
                    List<OperType> operTypes = operTypeService.selectByRoleToOperType(rol, use, obj);
                    redisTemplate.opsForValue().set(use + "_" + rol + "_" + obj, operTypes);
                }
            }
        }
    }

    /**
     * 请完善这个方法????
     *
     * @param userType 用户类型
     * @param userRole 用户角色
     * @param objType  对象类型
     * @return 该用户对这种操作对象所具有的操作列表
     */
    public static List<OperType> operTypes(int userType, int userRole, int objType) {
        String key = userType + "_" + userRole + "_" + objType;
        return null;
    }

    /**
     * 请完善这个方法????
     *
     * @param userType 用户类型
     * @param userRole 用户角色
     * @param objType  对象类型
     * @param Oper     操作类型
     * @return 该用户是否具有对应操作的权限
     */
    public static boolean hasAuth(int userType, int userRole, int objType, int Oper) {
        return true;
    }
}
