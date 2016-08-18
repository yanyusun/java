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
import java.util.Iterator;
import java.util.List;

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
     * 保存临时文件(原)
     * @param type
     * @param userId
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static String saveFileSyncTmp(String type, Integer userId, MultipartFile multipartFile) throws IOException {
        TSysProperty tSysProperty = SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE, type);
        //验证允许上传的业务类型 或者 文件是否为空
        if(null == multipartFile || multipartFile.isEmpty()) {
            return "err:上传的文件为空";
        }
        if(null == tSysProperty) {
            return "err:不支持的业务类型";
        }

        long curTime = System.currentTimeMillis();
        String fileName = type + "_" + userId + "_" + curTime;

        //验证业务类型对应的文件类型
        if(null != multipartFile && !multipartFile.isEmpty()) {
            String endfix = multipartFile.getOriginalFilename().substring(
                    multipartFile.getOriginalFilename().indexOf(".")+1, multipartFile.getOriginalFilename().length());
            if(0 > tSysProperty.getPropertyValue().indexOf(endfix)) {
                return "err:不支持的文件类型";
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
        }, new Date(curTime + Long.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_TMP_DEL_TIMER_KEY).getPropertyValue()) * 3600 * 1000));

        return fileName;
    }

    /**
     * 保存临时文件(新)
     * @param userId 当前操作用户
     * @param multipartFile
     * @return 返回保存的文件名称
     * @throws IOException
     */
    public static String saveFileTmp(Integer userId, MultipartFile multipartFile) throws IOException {
        //验证允许上传的业务类型 或者 文件是否为空
        if(null == multipartFile || multipartFile.isEmpty()) {
            return "err:上传的文件为空";
        }
        // 验证上传限制
        String fileName = multipartFile.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if(fileType == null || fileType.length() == 0){
            return "err:暂不支持该文件类型";
        }
        // 获取系统文件的配置
        List<TSysProperty> propertyList = SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE);
        Iterator iterator = propertyList.iterator();
        boolean flag = false;
        while (iterator.hasNext()){
            TSysProperty property = (TSysProperty) iterator.next();
            if(property.getPropertyValue().indexOf(fileType) > 0){
                flag = true;break;
            }
        }
        if(!flag){
            return "err:暂不支持该文件类型";
        }
        // 保存文件
        long curTime = System.currentTimeMillis();
        String saveName = "file_" + userId + "_" + curTime + "." + fileType; // 命名格式 tmp_1_123131132.file
        String savePath = SysPropertyTool.getProperty(
                SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue()
                + "/temp/" + "file/" + userId + "/";
        File dirTmp = new File(savePath);
        if(!dirTmp.exists()) {
            dirTmp.mkdirs();
        }
        File fileTmp = new File(savePath + saveName);
        if(fileTmp.exists()) {
            fileTmp.delete();
        }
        fileTmp.createNewFile();
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), fileTmp);
        // 添加删除定时器
        addDeleteScheduler(fileTmp, new Date(curTime + Long.decode(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_TMP_DEL_TIMER_KEY).getPropertyValue())
                * 3600 * 1000));
        return saveName;
    }

    /**
     * 保存文件(对外封闭)
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
        File destFile = new File(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue() + "/" +
                strs[0] + "/" + strs[1] + "/" + fileName);
        FileUtils.moveFile(srcFile, destFile);

        return true;
    }

    /**
     * 保存文件(对外开放)
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean saveOpenSource(String fileName) throws IOException{
        String[] strs = fileName.split("_");
        if(strs.length != 3) {
            return false;
        }
        File srcFile = new File(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_OPEN_PATH_KEY).getPropertyValue() + "/temp/"
                + strs[0] + "/" + strs[1] + "/" + fileName);
        if(!srcFile.exists()) {
            return false;
        }
        File destFile = new File(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_OPEN_PATH_KEY).getPropertyValue() + "/" +
                strs[0] + "/" + strs[1] + "/" + fileName);
        FileUtils.moveFile(srcFile, destFile);

        return true;
    }

    /**
     * 文件定时删除
     * @param file 删除的源文件
     * @param date 删除的时间
     */
    public static void addDeleteScheduler(File file, Date date){
        taskScheduler.schedule(() -> {
            file.delete();
        }, date);
    }

}