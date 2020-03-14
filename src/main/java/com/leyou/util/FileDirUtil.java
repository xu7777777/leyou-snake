package com.leyou.util;

import com.leyou.LeyouApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;

import java.io.*;

@Slf4j
public class FileDirUtil {

    //获取上传的目录
    public static String getUploadDir() {
        File upload = new File(getAppJarDir(), "upload/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }

    //获取jar包所在的目录
    public static String getAppJarDir() {
        ApplicationHome home = new ApplicationHome(LeyouApplication.class);
        File jarFile = home.getSource();
        return jarFile.getParentFile().getAbsolutePath();
    }

    //获取classpath
    public static String getClassPath() {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        return path;
    }

    //读取jar包内的文件
    public static byte[] getResourceContent(String path) throws IOException {
        byte[] bdata;
        ClassPathResource classPathResource = new ClassPathResource(path);
        bdata = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());

        return bdata;
    }

    /**
     * 读取jar静态资源文件,返回流
     *
     * @param path 静态资源文件地址
     * @return
     */
    public static InputStream getAppJarInput(String path) {
        return FileDirUtil.class.getResourceAsStream(path);
    }

    /**
     * InputStream –> File
     *
     * @param ins  文件流
     * @param file 文件地址
     */
    public static void inputstreamtofile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}
