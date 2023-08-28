package com.xcale.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.RoutingKey;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DeleteCartCommand {

    @TargetAggregateIdentifier
    UUID cartId;

}
