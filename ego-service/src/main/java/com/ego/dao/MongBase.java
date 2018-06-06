package com.ego.dao;

import java.util.Map;

import com.ego.document.Goods;
import com.ego.utils.ReturnMessage;
import com.mongodb.BasicDBObject;


public interface MongBase<T> {
	
//    public ReturnMessage<Goods> insert(T object,String collectionName);    
    
//    public T findOne(BasicDBObject query,String collectionName);    
    
//    public List<T> findAll(Map<String,Object> params,String collectionName);    
    
    public void update(Map<String,Object> params,String collectionName);   
    
    public void createCollection(String collectionName);  
    
    public void remove(Map<String,Object> params,String collectionName);
    
    public void dropCollection(String collectionName);
    
    public Integer count(String collectionName);
}
