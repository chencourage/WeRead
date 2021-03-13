package com.weread.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weread.common.base.ResponseStatus;
import com.weread.common.base.SystemConfig;
import com.weread.common.model.ResultBean;
import com.weread.common.redis.IRedisService;
import com.weread.service.read.service.IBookService;
import com.weread.service.read.service.IFriendLinkService;
import com.weread.service.read.service.INewsService;

/**
 * @author 11797
 */
@RequestMapping("cache")
@RestController
public class CacheController {

    @Value("${cache.manager.password}")
    private String cacheManagerPass;
    @Autowired
    private IRedisService cacheService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private INewsService newsService;
    @Autowired
    private IFriendLinkService friendLinkService;

    /**
     * 刷新缓存
     * @param type 缓存类型，1：首页书籍推荐，2：首页新闻，3：首页友情链接
     * @throws Exception 
     * */
    @GetMapping("refresh/{pass}/{type}")
    public ResultBean refreshCache(@PathVariable("type") Byte type, @PathVariable("pass") String pass) throws Exception{
        if(!cacheManagerPass.equals(pass)){
            return ResultBean.fail(ResponseStatus.PASSWORD_ERROR);
        }
        switch (type){
            case 1:{
                //刷新首页推荐书籍缓存
                cacheService.del(SystemConfig.INDEX_BOOK_SETTINGS_KEY);
                bookService.listBookSettingVO();
                break;
            }
            case 2:{
                //刷新首页新闻缓存
                cacheService.del(SystemConfig.INDEX_NEWS_KEY);
                newsService.listIndexNews();
                break;
            }
            case 3:{
                //刷新首页友情链接
                cacheService.del(SystemConfig.INDEX_LINK_KEY);
                friendLinkService.listIndexLink();
                break;
            }
            default:{
                break;
            }

        }

        return ResultBean.ok();
    }




}
