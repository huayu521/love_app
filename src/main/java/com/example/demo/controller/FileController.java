package com.example.demo.controller;

import com.example.demo.tools.Respond;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 上传图片
 *
 * @author 尘落
 * @date 2023/03/21
 * @email
 **/
@Controller
@CrossOrigin(origins = "*")
public class FileController {
    @PostMapping("/fileImage")
    @ResponseBody
    public String fileImage(@RequestParam("file") MultipartFile file) {
//        System.out.println(file.getOriginalFilename());
        if (file.isEmpty()) {
            return "错误";
        }

        //获取原来的文件和后缀
        String fileName = null;
        String originalFilename = file.getOriginalFilename();

        //获取照片后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf('.'));

        //使用uuid
        fileName = UUID.randomUUID().toString()+suffixName;

        //拼接地址给到前端
        String path = "http://jiaoyou.blogcl.cn/image/"+fileName;

        try {
//            File path1 = new File("C:/Users/Lenovo/Desktop/blind_box-master/src/main/resources/static/image/");
            File path1 = new File("/www/wwwroot/jiaoyou/");
            File file_temp=new File(path1,fileName);
            if (!file_temp.getParentFile().exists()) {
                file_temp.getParentFile().mkdir();
            }
            if (file_temp.exists()) {
                file_temp.delete();
            }
            file_temp.createNewFile();
            file.transferTo(file_temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
