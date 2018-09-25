package com.tensquare.search.controller;

import com.tensquare.search.entity.PageResult;
import com.tensquare.search.entity.Result;
import com.tensquare.search.entity.StatusCode;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK,"添加文章成功");

    }
    //根据关键词搜索
    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findByKeyword(@PathVariable String keywords,@PathVariable int page,@PathVariable int size){
        Page<Article> articlePage = articleSearchService.findByTitleOrContent(keywords, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(articlePage.getTotalElements(),articlePage.getContent()));
    }
}
