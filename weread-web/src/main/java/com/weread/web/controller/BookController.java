package com.weread.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.java2nb.novel.core.bean.ResultBean;
import com.java2nb.novel.core.bean.UserDetails;
import com.java2nb.novel.core.enums.ResponseStatus;
import com.java2nb.novel.entity.BookComment;
import com.java2nb.novel.search.BookSP;
import com.java2nb.novel.service.BookService;
import com.java2nb.novel.vo.BookVO;
import com.weread.service.read.service.IBookService;

/**
 * @author 11797
 */
@RequestMapping("book")
@RestController
public class BookController extends BaseController{

	@Autowired
    private IBookService bookService;
	
    /**
     * 查询首页小说设置列表数据
     * @throws Exception 
     * */
    @GetMapping("listBookSetting")
    public ResultBean listBookSetting() throws Exception{
        return ResultBean.ok(bookService.listBookSettingVO());
    }

    /**
     * 查询首页点击榜单数据
     * */
    @GetMapping("listClickRank")
    public ResultBean listClickRank(){
        return ResultBean.ok(bookService.listClickRank());
    }

    /**
     * 查询首页新书榜单数据
     * */
    @GetMapping("listNewRank")
    public ResultBean listNewRank(){
        return ResultBean.ok(bookService.listNewRank());
    }

    /**
     * 查询首页更新榜单数据
     * */
    @GetMapping("listUpdateRank")
    public ResultBean listUpdateRank(){
        return ResultBean.ok(bookService.listUpdateRank());
    }

    /**
     * 查询小说分类列表
     * */
    @GetMapping("listBookCategory")
    public ResultBean listBookCategory(){
        return ResultBean.ok(bookService.listBookCategory());
    }

    /**
     * 分页搜索
     * */
    @GetMapping("searchByPage")
    public ResultBean searchByPage(BookSP bookSP, @RequestParam(value = "curr", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "20") int pageSize){
        PageInfo<BookVO> pageInfo = bookService.searchByPage(bookSP,page,pageSize);
        return ResultBean.ok(pageInfo);
    }

    /**
     * 查询小说详情信息
     * */
    @GetMapping("queryBookDetail/{id}")
    public ResultBean queryBookDetail(@PathVariable("id") Long id){
        return ResultBean.ok(bookService.queryBookDetail(id));
    }


    /**
     * 查询小说排行信息
     * */
    @GetMapping("listRank")
    public ResultBean listRank(@RequestParam(value = "type",defaultValue = "0") Byte type,@RequestParam(value = "limit",defaultValue = "30") Integer limit){
        return ResultBean.ok(bookService.listRank(type,limit));
    }

    /**
     * 增加点击次数
     * */
    @PostMapping("addVisitCount")
    public ResultBean addVisitCount(Long bookId){
        if(enableMq == 1) {
            rabbitTemplate.convertAndSend("ADD-BOOK-VISIT-EXCHANGE", null, bookId);
        }else {
            bookService.addVisitCount(bookId, 1);
        }
        return ResultBean.ok();
    }

    /**
     * 查询章节相关信息
     * */
    @GetMapping("queryBookIndexAbout")
    public ResultBean queryBookIndexAbout(Long bookId,Long lastBookIndexId) {
        Map<String,Object> data = new HashMap<>(2);
        data.put("bookIndexCount",bookService.queryIndexCount(bookId));
        String lastBookContent = bookService.queryBookContent(lastBookIndexId).getContent();
        if(lastBookContent.length()>42){
            lastBookContent=lastBookContent.substring(0,42);
        }
        data.put("lastBookContent",lastBookContent);
        return ResultBean.ok(data);
    }

    /**
     * 根据分类id查询同类推荐书籍
     * */
    @GetMapping("listRecBookByCatId")
    public ResultBean listRecBookByCatId(Integer catId) {
        return ResultBean.ok(bookService.listRecBookByCatId(catId));
    }


    /**
     *分页查询书籍评论列表
     * */
    @GetMapping("listCommentByPage")
    public ResultBean listCommentByPage(@RequestParam("bookId") Long bookId,@RequestParam(value = "curr", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "5") int pageSize) {
        return ResultBean.ok(new PageInfo<>(bookService.listCommentByPage(null,bookId,page,pageSize)));
    }

    /**
     * 新增评价
     * */
    @PostMapping("addBookComment")
    public ResultBean addBookComment(BookComment comment, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(request);
        if (userDetails == null) {
            return ResultBean.fail(ResponseStatus.NO_LOGIN);
        }
        bookService.addBookComment(userDetails.getId(),comment);
        return ResultBean.ok();
    }

    /**
     * 根据小说ID查询小说前十条最新更新目录集合
     * */
    @GetMapping("queryNewIndexList")
    public ResultBean queryNewIndexList(Long bookId){
        return ResultBean.ok(bookService.queryIndexList(bookId,"index_num desc",1,10));
    }

    /**
     * 目录页
     * */
    @GetMapping("/queryIndexList")
    public ResultBean indexList(Long bookId,@RequestParam(value = "curr", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "5") int pageSize,@RequestParam(value = "orderBy",defaultValue = "index_num desc") String orderBy) {
        return ResultBean.ok(new PageInfo<>(bookService.queryIndexList(bookId,orderBy,page,pageSize)));
    }






}
