package com.example.food_project.services.impl;

import com.example.food_project.entity.CategoryEntity;
import com.example.food_project.entity.FoodEntity;
import com.example.food_project.repository.CategoryRepository;
import com.example.food_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getExplorerCategory() {
        return categoryRepository.getExplorer();
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Integer> findCategoryIdListByResFood(List<FoodEntity> foods) {
        //Set add unique value
        Set<Integer> listId = new HashSet<>();
        List<CategoryEntity> list = categoryRepository.findByFoodsIsIn(foods);
        for(CategoryEntity data : list ){
            int index = data.getId();
            listId.add(index);
        }
        List<Integer> listIdAfterAddSet = new ArrayList<>(listId);
        return listIdAfterAddSet;
    }

    @Override
    public List<CategoryEntity> findByIdIsIn(List<Integer> listIdCategory) {
        return categoryRepository.findByIdIsIn(listIdCategory);
    }


}
