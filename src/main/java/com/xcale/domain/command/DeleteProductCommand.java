package com.xcale.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DeleteProductCommand {

    @TargetAggregateIdentifier
    private UUID cartId;
    private UUID productId;
    private int quantity;

}