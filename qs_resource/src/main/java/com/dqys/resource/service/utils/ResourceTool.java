package com.dqys.resource.service.utils;

import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.utils.SysPropertyTool;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author by pan on 16-4-20.
 */
@Component
public class ResourceTool implements ApplicationContextAware {

    //上传文件路径
    private static final String SYS_FILE_UPLOAD_PATH_KEY = "sys_uploadPath";
    //临时文件自动删除时间 hour
    private static final String SYS_TMP_DEL_TIMER_KEY = "sys_file_tmp_del_timer";

    private static RedisTemplate<String, Object> redisTemplate;
    private static TaskScheduler taskScheduler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        taskScheduler = applicationContext.getBean(TaskScheduler.class);
    }


    public static String saveFileSyncTmp(String type, Integer userId, MultipartFile multipartFile) throws IOException {
        TSysProperty tSysProperty = SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE, type);
        //验证允许上传的业务类型 或者 文件是否为空
        if(null == tSysProperty || null == multipartFile || multipartFile.isEmpty()) {
            return null;
        }

        long curTime = System.currentTimeMillis();
        String fileName = userId + "_" + type + "_" + curTime;

        //验证业务类型对应的文件类型
        if(null != multipartFile && !multipartFile.isEmpty()) {
            String endfix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf(".")+1, multipartFile.getOriginalFilename().length());
            if(0 > tSysProperty.getPropertyValue().indexOf(endfix)) {
                return null;
            }

            fileName += "." + endfix;
        }

        //保存到临时目录
        String path = SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue() + "/temp/" + type + "/" + userId + "/";
        File dirTmp = new File(path);
        if(!dirTmp.exists()) {
            dirTmp.mkdirs();
        }
        File fileTmp = new File(path + fileName);
        if(fileTmp.exists()) {
            fileTmp.delete();
        }
        fileTmp.createNewFile();
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), fileTmp);

        //定时删除
        taskScheduler.schedule(() -> {
            fileTmp.delete();
        }, new Date(curTime + Long.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, SYS_TMP_DEL_TIMER_KEY).getPropertyValue()) * 60 * 24));


        return fileName;
    }
}