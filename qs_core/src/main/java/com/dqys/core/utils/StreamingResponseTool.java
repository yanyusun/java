package com.dqys.core.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author by pan on 10/14/15.
 */
public class StreamingResponseTool implements StreamingResponseBody {

    private File file;
    private byte[] datas = new byte[]{1};
    private boolean isPass = false;
    private boolean isFile = false;
    private String errMsg = "出错啦";

    public void writeTo(OutputStream outputStream) throws IOException {
        if (isPass) {
            try {
                if (isFile) {
                    FileInputStream stream = new FileInputStream(file);
                    byte[] b = new byte[1024];
                    int n;
                    while ((n = stream.read(b)) != -1) {
                        outputStream.write(b, 0, n);
                    }
                } else {
                    outputStream.write(this.datas);
                }
            } catch (IOException e) {
                String content = new String(IOUtils.toByteArray(this.getClass().getResourceAsStream("/streamerr.html")), "UTF-8");
                content = content.replaceFirst("errmsg", errMsg);
                outputStream.write(content.getBytes());
            }
        } else {
            String content = new String(IOUtils.toByteArray(this.getClass().getResourceAsStream("/streamerr.html")), "UTF-8");
            content = content.replaceFirst("errmsg", errMsg);
            outputStream.write(content.getBytes());
        }

        outputStream.flush();
        outputStream.close();
    }

    public void setIsPass(boolean isPass) {
        this.isPass = isPass;
    }

    public File getFile() {
        return file;
    }

    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public boolean isPass() {
        return isPass;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }
}
