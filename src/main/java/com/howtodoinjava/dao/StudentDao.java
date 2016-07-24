package com.howtodoinjava.dao;


 
import java.util.List;
 
public interface StudentDao {
    public List<Object> getStudent(int page,int rows);
    public int getTotalPages(int rows);
}
