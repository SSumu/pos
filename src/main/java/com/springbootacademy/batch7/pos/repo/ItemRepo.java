package com.springbootacademy.batch7.pos.repo;

import com.springbootacademy.batch7.pos.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

//    Page<Item> findAllByActiveStateEquals(boolean activeStatus, Pageable pageable); // This pageable reference from the Pageable interface catches the both page and size parameters.

//    int coeregfrrAllByActiveStateEquals(boolean activeStatus); // The below method was created by changing this method.
    int
    countAllByActiveStateEquals(boolean activeStatus); // There is a filter named countAllBy get a count from the incoming filters. It is much better to copy this method from the above code and put it to here. Because if the filters change, the resulting count will be incorrect. findAllBy() retrieves all the data. countAllBy() checks the retrieved data again from the database and counts it.


//    Page<Item> gergergterg(boolean activeState, PageRequest of); // The below method was created by changing this method.
//    Page<Item> findAllByActiveStateEquals(boolean activeState, PageRequest of); // Here we filter the data from the database. There is a known issue ( not exactly sure whether it is an issue ) here. In the video, this did not work because of the PageRequest. But it was worked in this Spring Boot version.

//  The ItemServiceIMPL passes a PageRequest. It does not work to have a PageRequest in this method in ItemRepo to capture the PageRequest. There is something called Pageable. It needs to put the Pageable to capture the PageRequest.
//Page<Item> findAllByActiveStateEquals(boolean activeState, Pageable of); // In the video, it was worked only with this method because of Pageable.
Page<Item> findAllByActiveStateEquals(boolean activeState, Pageable pageable); // In the video, it was worked only with this method because of Pageable. This method was also worked in this Spring Boot version. Typically, when we use Pageable, it does not put of with that. pageable is the reference.

}
