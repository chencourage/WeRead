package com.weread.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java2nb.novel.service.FriendLinkService;
import com.weread.common.model.ResultBean;
import com.weread.service.read.service.IFriendLinkService;

/**
 * @author 11797
 */
@RequestMapping("friendLink")
@RestController
public class FriendLinkController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
    private IFriendLinkService friendLinkService;

    /**
     * 查询首页友情链接
     * */
    @GetMapping("listIndexLink")
    public ResultBean listIndexLink(){
        return ResultBean.ok(friendLinkService.listIndexLink());
    }




}
