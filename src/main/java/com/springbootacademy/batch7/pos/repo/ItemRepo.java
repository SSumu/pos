package com.springbootacademy.batch7.pos.repo;

import com.springbootacademy.batch7.pos.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {
//    List<Item> fwegwregjgergher(String itemName, boolean b); This method was replaced with the below method.
    List<Item> findAllByItemNameEqualsAndActiveStateEquals(String itemName, boolean b); // <L,R> :- L is the Item is the primary key field. R is the data type of the primary key. The logic is findAllBy because there can be many items with the same name. As the first variable name is itemName, itemName is the first option to filter. Find if the itemName is the given one and the b(activeState) is the given one. So the itemName is assigned to first one (ItemName in the method) and b is assigned to ActiveState in the method, and it is done by the And in the middle of the method.

    List<Item> findAllByActiveStateEquals(boolean activeStatus);

    Page<Item> findAllByActiveStateEquals(boolean activeStatus, Pageable pageable); // This pageable reference from the Pageable interface catches the both page and size parameters.

//    int coeregfrrAllByActiveStateEquals(boolean activeStatus); // The below method was created by changing this method.
    int countAllByActiveStateEquals(boolean activeStatus); // There is a filter named countAllBy get a count from the incoming filters. It is much better to copy this method from the above code and put it to here. Because if the filters change, the resulting count will be incorrect. findAllBy() retrieves all the data. countAllBy() checks the retrieved data again from the database and counts it.


}
