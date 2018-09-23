package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpitService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private RedisTemplate redisTemplate;
    public Page<Spit> findAll(Integer page,Integer size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Spit> spitPage = spitDao.findAll(pageRequest);
        return spitPage;
    }
    public Spit findById(String  id){
        Spit spit = spitDao.findById(id).get();
        return spit;
    }
    public void Update(Spit spit){
        spitDao.save(spit);
    }
    public void delete(String id){
        spitDao.deleteById(id);
    }
    public Page<Spit> findByParentId(String parentId,Integer page,Integer size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
      return   spitDao.findAll(pageRequest);
    }
    //吐槽点赞
    public void dianzan(String spitId){

        redisTemplate.boundValueOps("spit"+spitId+)
        Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);
    }
}
