package com.ww.spring.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/upload")
    public ModelAndView upload(MultipartFile file) {
        // 设置视图为JSON视图
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new MappingJackson2JsonView());
        
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 目标文件
        File dest = new File(originalFilename);
        
        try {
            // 保持文件
            file.transferTo(dest);
            modelAndView.addObject("success", true);
            modelAndView.addObject("msg", "文件上传成功");
        } catch (Exception e) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("msg", "文件上传失败");
            e.printStackTrace();
        }
        
        return modelAndView;
    }
}
