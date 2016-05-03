package com.dqys.core.utils;

import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.TSysProperty;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class FileTool implements ApplicationContextAware {

    private static TaskScheduler taskScheduler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        taskScheduler = applicationContext.getBean(TaskScheduler.class);
    }

    /**
     * 保存临时文件
     * @param type
     * @param userId
     * @param multipartFile
     * @return
     * @throws IOException
     */
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
        String path = SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue() + "/temp/" + type + "/" + userId + "/";
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
        }, new Date(curTime + Long.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_TMP_DEL_TIMER_KEY).getPropertyValue()) * 3600));


        return fileName;
    }

    /**
     * 保存文件
     * @param fileName
     */
    public static boolean saveFileSync(String fileName) throws IOException {
        String[] strs = fileName.split("_");
        if(strs.length != 3) {
            return false;
        }
        File srcFile = new File(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue() + "/temp/"
                + strs[0] + "/" + strs[1] + "/" + fileName);
        if(!srcFile.exists()) {
            return false;
        }
        File destFile = new File(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue() + "/" + fileName);
        FileUtils.moveFile(srcFile, destFile);

        return true;
    }
}