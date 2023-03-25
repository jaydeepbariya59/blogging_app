package com.jaydeep.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaydeep.blogapplication.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
