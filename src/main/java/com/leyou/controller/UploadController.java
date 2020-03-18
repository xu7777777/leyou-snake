package com.leyou.controller;


import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.Output;
import com.leyou.util.OutputUtil;
import com.leyou.util.UploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author XuQiaoYang
 * @Date 2020/2/14 12:23
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Resource
    private UploadUtils uploadUtils;

    /**
     * upload image
     * @param file imageFile
     * @return src url
     */
    @PostMapping("image")
    public Output<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = this.uploadUtils.uploadImage(file);
        System.out.println(url);
        // 由于网监的限制，暂时写死图片，待网站备案。
        if (StringUtils.isNotBlank(url)) {
            return OutputUtil.ok(url);
//            return OutputUtil.ok("http://106.54.86.212/rBEABl5wXhmAXCBwAAAYvvnfa6Y041.gif");
        }
        return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
    }

}
