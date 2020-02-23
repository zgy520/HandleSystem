package com.zgy.handle.userService.service;

import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public abstract class SystemService<T> {
    private SystemRepository systemRepository;

    @Autowired
    public SystemService(SystemRepository systemRepository){
        this.systemRepository = systemRepository;
    }

    /**
     * 获取所有的数据
     * @return
     */
    public List<T> findAll(){
        return systemRepository.findAll();
    }

    public Optional<T> findById(Long id){
        return systemRepository.findById(id);
    }


    /**
     * 根据分页获取数据
     * @param pageable
     * @return
     */
    public Page<T> findAll(Pageable pageable){
        return systemRepository.findAll(pageable);
    }

    /**
     * 更新操作之前执行
     * 可根据需要进行重载
     * @param t
     */
    public void beforeUpdate(Long id,T t){}

    /**
     * 更新操作之后执行
     * 可对更新后的对象进行操作
     * @param t
     */
    public void postUpdate(T t){}

    public T update(Long id,T t){
        beforeUpdate(id,t);
        systemRepository.save(t);
        postUpdate(t);
        return t;
    }

    public void delete(Long id){
        systemRepository.deleteById(id);
    }
}
