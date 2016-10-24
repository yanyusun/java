package com.dqys.resource.controller;

import com.dqys.core.utils.FileTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 针对火狐没有缓存设计的文件预览
 * Created by yan on 16-10-24.
 */
@Controller
@RequestMapping("/resf")
public class ResourceFController {
    @RequestMapping("/getSource")
    public String getRes(HttpServletResponse response, @RequestParam String fileName, boolean isTmp) {
        File file = FileTool.getFile(fileName,isTmp);

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

}
