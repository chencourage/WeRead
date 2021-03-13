package com.weread.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.java2nb.novel.service.NewsService;
import com.weread.common.model.ResultBean;
import com.weread.service.read.service.INewsService;

/**
 * @author 11797
 */
@RequestMapping("news")
@RestController
public class NewsController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
    private INewsService newsService;

    /**
     * 查询首页新闻
     * */
    @GetMapping("listIndexNews")
    public ResultBean listIndexNews(){
        return ResultBean.ok(newsService.listIndexNews());
    }

    /**
     * 分页查询新闻列表
     * */
    @GetMapping("listByPage")
    public ResultBean listByPage(@RequestParam(value = "curr", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "5") int pageSize){
        return ResultBean.ok(new PageInfo<>(newsService.listByPage(page,pageSize)));
    }



}
