package com.xcale.interfaces.rest;

import com.xcale.application.command.Cart;
import com.xcale.application.query.CartView;
import com.xcale.domain.command.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ecommerce/cart")
@Api( value = "REST API - Cart", tags = {"Cart"} )
public class CartController {

    public final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public CartController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @ApiOperation(
            value = "Gets a cart",
            response = Cart.class,
            responseContainer = "Cart",
            nickname = "getCart"
    )
    @GetMapping("/{cartId}")
    public CompletableFuture<CartView> findCart(@PathVariable("cartId") String cartId) {
        return queryGateway.query(
                new FindCartQuery(UUID.fromString(cartId)),
                ResponseTypes.instanceOf(CartView.class)
        );
    }

    @ApiOperation(
            value = "Create a new cart",
            response = Cart.class,
            responseContainer = "Cart",
            nickname = "createCart"
    )
    @PostMapping("")
    public CompletableFuture<UUID> createCart() {
        return commandGateway.send(new CreateCartCommand(UUID.randomUUID()));
    }

    @ApiOperation(
            value = "Delete a cart",
            response = Cart.class,
            responseContainer = "Cart",
            nickname = "deleteCart"
    )
    @DeleteMapping("/{cartId}")
    public CompletableFuture<UUID> deleteCart(@PathVariable("cartId") UUID cartId) {
        return commandGateway.send(new DeleteCartCommand(cartId));
    }


    @ApiOperation(
            value = "Add a product to the cart",
            response = Cart.class,
            responseContainer = "Cart",
            nickname = "addProduct"
    )
    @PostMapping("/{cartId}/product/{productId}/{quantity}")
    public void addProduct(@PathVariable("cartId") String cartId,
                           @PathVariable("productId") String productId,
                           @PathVariable("quantity") Integer quantity) {
        commandGateway.send(new AddProductCommand(
                UUID.fromString(cartId),
                UUID.fromString(productId),
                quantity
        ));
    }


    @ApiOperation(
            value = "Delete a product to the cart",
            response = Cart.class,
            responseContainer = "Cart",
            nickname = "deleteProduct"
    )
    @DeleteMapping("/{cartId}/product")
    public void deleteProduct(@PathVariable("cartId") String id,
                           @PathVariable("productId") String productId,
                           @PathVariable("quantity") Integer quantity) {
        commandGateway.send(new DeleteProductCommand(
                UUID.fromString(id),
                UUID.fromString(productId),
                quantity
        ));
    }


}
