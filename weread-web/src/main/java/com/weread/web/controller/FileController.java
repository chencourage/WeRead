package com.weread.web.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.weread.common.base.SystemConfig;
import com.weread.common.model.ResultBean;
import com.weread.common.redis.IRedisService;
import com.weread.common.utils.UUIDUtil;

/**
 * @author 11797
 */
@Controller
@RequestMapping("file")
public class FileController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private IRedisService cacheService;

    @Value("${pic.save.path}")
    private String picSavePath;

    /**
     * 生成验证码
     */
    @GetMapping(value = "getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            //输出验证码图片方法
            randomValidateCode.getRandcode(cacheService, response);
        } catch (Exception e) {
            log.error("获取验证码失败>>>> ", e);
        }
    }

    /**
     * 文件上传
     */
    @ResponseBody
    @PostMapping("/upload")
    ResultBean upload(@RequestParam("file") MultipartFile file) {
        Date currentDate = new Date();
        try {
            String savePath =
                    SystemConfig.LOCAL_PIC_PREFIX + DateUtils.formatDate(currentDate, "yyyy") + "/" +
                    DateUtils.formatDate(currentDate, "MM") + "/" +
                    DateUtils.formatDate(currentDate, "dd") ;
            String oriName = file.getOriginalFilename();
            String saveFileName = UUIDUtil.getUUID32() + oriName.substring(oriName.lastIndexOf("."));
            File saveFile = new File( picSavePath + savePath, saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
            return ResultBean.ok(savePath+"/"+saveFileName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultBean.error();
        }

    }


}
