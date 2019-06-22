package com.just.myproject.Utils;

import com.just.myproject.Entity.User;
import com.just.myproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Configuration
@PropertySource(value = {"classpath:application.properties"})
@Component

public class FileUtils {

    @Value("${file.path}")
    private String webUploadPath;
    @Autowired
    UserService userService;

    public String upload(MultipartFile file, String worknum) {


        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    System.out.println(fileName);
                    // 获取图片的扩展名
                    String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

                    String newFileName = worknum + "_" + fileName ;
                    // 文件路径
                    System.out.println(newFileName);
                    String filePath = webUploadPath;
                    System.out.println(filePath);
                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 判断是否有旧头像，如果有就先删除旧头像，再上传
                    User userInfo = userService.userCheck(worknum);
                    if (userInfo.getHeadimg() != null) {
                        String oldFilePath = userInfo.getHeadimg();
                        File oldFile = new File(oldFilePath);
                        if (oldFile.exists()) {
                            oldFile.delete();
                        }
                    }
                    file.transferTo(dest);
                    return filePath+newFileName;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
