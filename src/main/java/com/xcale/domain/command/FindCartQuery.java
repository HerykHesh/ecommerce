package com.xcale.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class FindCartQuery {

    private UUID cartId;

}