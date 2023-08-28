package com.xcale.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CartDeletedEvent {

    UUID cartId;

}
