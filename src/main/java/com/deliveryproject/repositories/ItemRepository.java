package com.deliveryproject.repositories;

import com.deliveryproject.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,String> {
    AddressRepository findItemByItemName(String name);
}
