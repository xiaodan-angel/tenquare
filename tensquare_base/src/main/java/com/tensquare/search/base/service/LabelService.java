package com.tensquare.search.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.entity.Result;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
@Service
public class LabelService {
        @Autowired
    private LabelDao labelDao;
        @Autowired
    private IdWorker idWorker;
    int i;
        //查询全部
    public List<Label> findAll(){
          return labelDao.findAll();
    }

    //通过id来查询
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }
   //增加
    public void add(Label label) {
        // i=i/0;
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void delete(String id) {

        labelDao.deleteById(id);
    }
    //根据查询条件来查询
    public List<Label> findSearch(Label label){
       return labelDao.findAll(new Specification<Label>(){
           @Override
           public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
               List<Predicate> list = new ArrayList<>();
               if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                   Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                   list.add(predicate);
               }
               if (label.getState() != null && !"".equals(label.getState())) {
                   Predicate statusPredicate = cb.equal(root.get("Status").as(String.class), label.getState());
                   list.add(statusPredicate);

               }
               //这里跟之前的不一样的是cb.and里面的参数是可变参数，之前是一个一个的传，现在是把predicate放在一个集合中，然后在把集合转换为数组
               Predicate[] predicates=new Predicate[list.size()];
               Predicate[] predicates1 = list.toArray(predicates);
               return cb.or(predicates1);


           }
       });
    }
    //分页查询
    public Page<Label> findPage(Label label,int page,int size){
      return   labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                ArrayList<Predicate> list = new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                Predicate[] predicates=new Predicate[list.size()];
                Predicate[] predicates1 = list.toArray(predicates);

                return cb.and(predicates1);
            }
        }, PageRequest.of(page-1,size));

    }


}
