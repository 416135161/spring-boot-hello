package com.kfit.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.kfit.demo.bean.Cat;

public interface CatRepository extends CrudRepository<Cat, Integer>{

}
