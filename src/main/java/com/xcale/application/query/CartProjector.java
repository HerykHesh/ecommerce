package com.xcale.application.query;

import com.xcale.domain.command.FindCartQuery;
import com.xcale.domain.event.CartCreatedEvent;
import com.xcale.domain.event.CartDeletedEvent;
import com.xcale.domain.event.ProductAddedEvent;
import com.xcale.domain.event.ProductDeletedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class CartProjector {
    private final CartViewRepository cartViewRepository;

    @EventHandler
    public void on(CartCreatedEvent event) {
        CartView cartView = new CartView(event.getCartId(), Collections.emptyMap());
        cartViewRepository.save(cartView);
    }

    @EventHandler
    public void on(CartDeletedEvent event) {
        CartView cartView = new CartView(event.getCartId(), Collections.emptyMap());
        cartViewRepository.delete(cartView);
    }

    @EventHandler
    public void on(ProductAddedEvent event) {
        cartViewRepository.findById(event.getCartId()).ifPresent(
                cartView -> cartView.addProducts(event.getProductId(), event.getQuantity())
        );
    }

    @EventHandler
    public void on(ProductDeletedEvent event) {
        cartViewRepository.findById(event.getCartId()).ifPresent(
                cartView -> cartView.removeProducts(event.getProductId(), event.getQuantity())
        );
    }

    @QueryHandler
    public CartView handle(FindCartQuery query) {
        return cartViewRepository.findById(query.getCartId()).orElse(null);
    }
}
