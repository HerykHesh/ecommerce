package com.xcale.application.command;

import com.xcale.domain.command.AddProductCommand;
import com.xcale.domain.command.CreateCartCommand;
import com.xcale.domain.command.DeleteCartCommand;
import com.xcale.domain.command.DeleteProductCommand;
import com.xcale.domain.event.CartCreatedEvent;
import com.xcale.domain.event.CartDeletedEvent;
import com.xcale.domain.event.ProductAddedEvent;
import com.xcale.domain.event.ProductDeletedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@NoArgsConstructor
@Aggregate
public class Cart {

    private static final Logger logger = LoggerFactory.getLogger(Cart.class);

    @AggregateIdentifier
    private UUID cartId;
    private Map<UUID, Integer> addedProduct;

    @CommandHandler
    public Cart(CreateCartCommand command) {
        AggregateLifecycle.apply(new CartCreatedEvent(command.getCartId()));
    }

    @CommandHandler
    public Cart(DeleteCartCommand command) {
        AggregateLifecycle.apply(new CartDeletedEvent(command.getCartId()));
    }

    @CommandHandler
    public void handle(AddProductCommand command) {
       AggregateLifecycle.apply(new ProductAddedEvent(cartId, command.getProductId(), command.getQuantity()));
    }

    @CommandHandler
    public void handle(DeleteProductCommand command) {
        AggregateLifecycle.apply(new ProductDeletedEvent(cartId, command.getProductId(), command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(CartCreatedEvent event) {
        cartId = event.getCartId();
        addedProduct = new HashMap<>();
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent event) {
       addedProduct.merge(event.getCartId(), event.getQuantity(), Integer::sum);
    }
    @EventSourcingHandler
    public void on(ProductDeletedEvent event) {
        addedProduct.computeIfPresent(
                event.getProductId(),
                (productId, quantity) -> quantity -= event.getQuantity()
        );
    }


}
