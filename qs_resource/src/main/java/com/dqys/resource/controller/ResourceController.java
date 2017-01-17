package com.dqys.resource.controller;

import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.*;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author by pan on 16-4-20.
 */
@RestController
@RequestMapping("/res")
public class ResourceController {
    @RequestMapping("/res_types")
    public Callable<JsonResponse<List>> businessResTypes() {
        return () -> JsonResponseTool.success(ApiParseTool.parseApiList(SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE), KeyEnum.API_SYS_PROPERTY_KEY));
    }

//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public Callable<String> upload(@RequestParam  String type, MultipartFile file) {
//        Integer userId = UserSession.getCurrent().getUserId();
//        Integer status = UserSession.getCurrent().getStatus();
//        return () -> {
//            //正常状态的用户才能上传
//            if(status.intValue() <= 0) {
//                return JsonResponseTool.authFailure("该账户暂无权限");
//            }
//
//
//            String fileName = null;
//            try {
//                fileName = FileTool.saveFileSyncTmp(type, userId, file);
//                if(fileName.startsWith("err:")) {
//                    return JsonResponseTool.failure(fileName);
//                }
//            } catch (IOException e) {
//                return JsonResponseTool.serverErr();
//            }
//
//            return JsonResponseTool.success(fileName);
//        };
//    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Callable<String> upload(@RequestParam String type, MultipartFile file) {
        Integer userId = UserSession.getCurrent().getUserId();
        Integer status = UserSession.getCurrent().getStatus();
        return () -> {
            //正常状态的用户才能上传
            if (status.intValue() <= 0) {
                return getStringJson(ResponseCodeEnum.AUTH_FAILURE.getValue(), "该账户暂无权限", null);
            }


            String fileName = null;
            try {
                fileName = FileTool.saveFileSyncTmp(type, userId, file);
                if (fileName.startsWith("err:")) {
                    return getStringJson(ResponseCodeEnum.FAILURE.getValue(), fileName, null);
                }
            } catch (IOException e) {
                return getStringJson(ResponseCodeEnum.SERVER_ERR.getValue(), "服务器错误", null);
            }

            return getStringJson(ResponseCodeEnum.SUCCESS.getValue(), "成功", fileName);
        };
    }


    /**
     * 用于下载
     *
     * @param fileName
     * @param isTmp
     * @param imgSize  图片大小（M最小,L小号,LX中号,LXX大号，不带该参数就为原图）
     * @return
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String fileName, boolean isTmp, String imgSize) {
        if (imgSize != null) {
            fileName = getImg(fileName, isTmp, imgSize);
        }
        File file = FileTool.getFile(fileName, isTmp);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename="
                + fileName);
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(bytes, httpHeaders, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity(bytes, httpHeaders, HttpStatus.OK);
    }

    /**
     * 用与显示
     *
     * @param response
     * @param fileName
     * @param isTmp
     * @param imgSize  图片大小（M最小,L小号,LX中号,LXX大号，不带该参数就为原图）
     */
    @RequestMapping("/getSource")
    public String showPic(HttpServletResponse response, @RequestParam String fileName, boolean isTmp, String imgSize) {
        if (imgSize != null) {
            fileName = getImg(fileName, isTmp, imgSize);
        }
        File file = FileTool.getFile(fileName, isTmp);

        OutputStream os = null;
        FileInputStream fis = null;

        try {
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    private String getImg(String fileName, boolean isTmp, String imgSize) {
        String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + imgSize + fileName.substring(fileName.lastIndexOf("."));
        File file = FileTool.getFile(newFileName, isTmp);
        if (file.getPath().lastIndexOf("notfound") > -1) {//该图片是否存在
            //图片不存在
            File file2 = FileTool.getFile(fileName, isTmp);
            String filePathByOutput = file2.getPath().replace(fileName, newFileName);
            try {
                Integer[] size = {200, 400, 600, 800};
                String[] img = {"M", "L", "LX", "LXX"};
                ImgPool imgPool = new ImgPool(file2, filePathByOutput);//图片压缩
                int num = 0;
                for (int i = 0; i < img.length; i++) {
                    if (img[i].equals(imgSize)) {
                        num = i;
                        break;
                    }
                }
                if (imgPool.getWidth() < imgPool.getHeight()) {
                    imgPool.resizeByHeight(size[num]);
                } else {
                    imgPool.resizeByWidth(size[num]);
                }
                return newFileName;
            } catch (IOException e) {
                return fileName;
            }
        } else {
//            存在返回
            return newFileName;
        }
    }

    /**
     * 解决一些浏览器在一些上传空间上试图转换成ｘｍｌ的问题，所以这里增加了手工
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    private String getStringJson(Integer code, String msg, String data) {
        String s = "{ \"code\":" + code + ",\"msg\":\"" + msg + "\",\"data\":\"" + data + "\"}";
        return s;
    }

}


