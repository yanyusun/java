package com.dqys.resource.service.utils;

import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author by pan on 16-4-20.
 */
@Component
public class ResourceTool implements ApplicationContextAware {

    //文件类型
    private static final String FILE_TYPE_KEY = "";
    //文件最大值 kb
    private static final String FILE_MAX_SIZE_KEY = "";
    //临时文件自动删除时间 second
    private static final String SYS_TMP_DEL_TIMER_KEY = "sys_file_tmp_del_timer";
    //业务文件名前缀  后加type值 1/2/3/4....
    private static final String FILE_BUSINESS_TYPE_PREFIX_KEY = "file_business_type_";

    //文件业务类型-营业执照
    private static final String FILE_BUSINESS_LICENCE_KEY = "1";

    private static RedisTemplate<String, Object> redisTemplate;
    private static TaskScheduler taskScheduler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        taskScheduler = applicationContext.getBean(TaskScheduler.class);
    }


    public static String saveFileSyncTmp(String type, Integer userId, MultipartFile multipartFile) {
        if(!validateFile(type, multipartFile)) {
            return null;
        }




        String fileName = userId + "_" + type + "_" + System.currentTimeMillis();

        return fileName;
    }


    private static boolean validateFile(String type, MultipartFile multipartFile) {
        TSysProperty tSysProperty = SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE, type);
        //验证允许上传的业务类型 或者 文件是否为空
        if(null == tSysProperty || null == multipartFile || multipartFile.isEmpty()) {
            return false;
        }
        //

        //验证业务类型对应的文件类型
        if(null != multipartFile && !multipartFile.isEmpty()) {

        }

        //multipartFile.getSize()
        return true;
    }





    public static String saveFileSync(Integer type, Integer id, MultipartFile multipartFile) {
        Date curDate = new Date();
        String fileName = type + "_" + curDate.getTime() + "_" + id;
        return null;
    }





    /*public static String saveFileSync(String fileName, MultipartFile file, Integer type) throws Exception {
        //记录图片 只能用同步 不能用异步
        if(file.isEmpty()) {
            throw new Exception("上传文件为空");
        }
        try {
            File fileNew = null;
            try {
                fileNew = new File(FILE_UPLOAD_PATH + uploadTypeEnum.getPath() + fileName);
                if(fileNew.exists()) {
                    fileNew.delete();
                } else {
                    fileNew.createNewFile();
                }
            } catch (IOException e) {
                File dir = new File(GlobalConfTool.getByKey(SystemConfTypeEnum.SYS.getValue(), SystemConfKeyEnum.UPLOAD_PATH.getValue()) + uploadTypeEnum.getPath());
                if(!dir.exists() && !dir.isDirectory()) {
                    dir.mkdirs();
                    fileNew = new File(GlobalConfTool.getByKey(SystemConfTypeEnum.SYS.getValue(), SystemConfKeyEnum.UPLOAD_PATH.getValue()) + uploadTypeEnum.getPath() + fileName);
                    if(fileNew.exists()) {
                        fileNew.delete();
                    } else {
                        fileNew.createNewFile();
                    }
                }
            }
            FileChannel fileChannel = new FileOutputStream(fileNew).getChannel();
            fileChannel.write(ByteBuffer.wrap(file.getBytes()));
            fileChannel.close();
        } catch (Exception e) {
            throw new UnexpectedRollbackException("记录文件异常");
        }

        return fileName;
    }

    public static boolean delFile(String fileName, Integer type) {
        try {
            File file = new File(GlobalConfTool.getByKey(SystemConfTypeEnum.SYS.getValue(), SystemConfKeyEnum.UPLOAD_PATH.getValue()) + uploadTypeEnum.getPath() + fileName);
            if(file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }*/
}
