package com.xcale.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.RoutingKey;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateCartCommand {

    @RoutingKey
    private UUID cartId;

}