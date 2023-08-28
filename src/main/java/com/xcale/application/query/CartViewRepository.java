package com.xcale.application.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartViewRepository extends JpaRepository<CartView, UUID> {
}