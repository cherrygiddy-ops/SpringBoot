package com.morrisco.net.store.onlineStoreSystem.mappers;

import com.morrisco.net.store.onlineStoreSystem.dtos.CartDto;
import com.morrisco.net.store.onlineStoreSystem.dtos.CartItemDto;
import com.morrisco.net.store.onlineStoreSystem.entities.Cart;
import com.morrisco.net.store.onlineStoreSystem.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

   @Mapping(target = "totalPrice",expression = "java(cart.getTotalPrice())")
    CartDto toDto (Cart cart);

    @Mapping(target = "totalPrice",expression = "java(cartItem.getTotalPrice())")//mapping a method value to a field
    CartItemDto toDto(CartItem cartItem);


}
