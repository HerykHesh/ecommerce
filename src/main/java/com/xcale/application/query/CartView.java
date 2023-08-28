package com.xcale.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class CartView {

    @Id
    @Getter
    private UUID cartId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<UUID, Integer> products;



//    public CartView() {
//        this.cartId = UUID.randomUUID();
//        this.products = new HashMap<>();
//    }

    public Map<UUID, Integer> getProducts() {
        return products;
    }

    public void addProducts(UUID productId, int amount) {
        products.compute(productId, (key, quantity) -> (quantity != null ? quantity : 0) + amount);
    }

    public void removeProducts(UUID productId, int amount) {
        Integer leftOverQuantity = products.compute(productId, (key, quantity) -> (quantity != null ? quantity : 0) - amount);
        if (leftOverQuantity != null && leftOverQuantity <= 0) {
            products.remove(productId);
        }
    }
}