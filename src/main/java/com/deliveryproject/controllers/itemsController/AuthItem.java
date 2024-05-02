package com.deliveryproject.controllers.itemsController;

import com.deliveryproject.dto.itemDto.RegisterItemDto;
import com.deliveryproject.model.Item;
import com.deliveryproject.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/items")
public class AuthItem {

    @Autowired
    ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity createItem(@RequestBody RegisterItemDto data){
        if(data.price() <= 0){
            return ResponseEntity.badRequest().body("Por favor insira um valor válido");
        }
        System.out.println(data.price());
        Item newItem = Item.builder().itemName(data.itemName()).description(data.description()).
                price(data.price()).build();

        this.itemRepository.save(newItem);

        return ResponseEntity.ok().body("Item criado com sucesso!");
    }
}
