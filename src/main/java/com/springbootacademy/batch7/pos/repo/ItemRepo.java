package com.springbootacademy.batch7.pos.repo;

import com.springbootacademy.batch7.pos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {
//    List<Item> fwegwregjgergher(String itemName, boolean b); This method was replaced with the below method.
    List<Item> findAllByItemNameEqualsAndActiveStateEquals(String itemName, boolean b); // <L,R> :- L is the Item is the primary key field. R is the data type of the primary key. The logic is findAllBy because there can be many items with the same name. As the first variable name is itemName, itemName is the first option to filter. Find if the itemName is the given one and the b(activeState) is the given one. So the itemName is assigned to first one (ItemName in the method) and b is assigned to ActiveState in the method, and it is done by the And in the middle of the method.
}
