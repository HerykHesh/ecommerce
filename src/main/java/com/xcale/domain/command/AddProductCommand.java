package com.xcale.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddProductCommand {

    @TargetAggregateIdentifier
    private UUID cartId;
    private UUID productId;
    private int quantity;

}
