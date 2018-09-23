package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {
    @Autowired
   private LabelService labelService;
    @Autowired
    private IdWorker idWorker;
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        labelService.findAll();
        return new Result(true,StatusCode.OK,"查询成功");
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
           Label label=labelService.findById(id);
        return new Result(true,StatusCode.OK,"查询对象成功");

    }
    //增加
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        label.setId(idWorker.nextId()+"");
         labelService.add(label);
         return new Result(true,StatusCode.OK,"增加成功");
    }
    //修改
    @RequestMapping(value ="/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label,@PathVariable String id){
         label.setId(id);
         labelService.update(label);
         return new Result(true,StatusCode.OK,"修改成功");
    }
    //删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        labelService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    //根据label和status来查询列表
    @RequestMapping(value = "/search",method =RequestMethod.POST )
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"根据条件查询成功",list);
    }
    //根据分页来查询类表
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findPage(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page<Label> labelPage = labelService.findPage(label, page, size);
        return new Result(true,StatusCode.OK,"分页成功",new PageResult<Label>(labelPage.getTotalElements(),labelPage.getContent()));
     }
 }
