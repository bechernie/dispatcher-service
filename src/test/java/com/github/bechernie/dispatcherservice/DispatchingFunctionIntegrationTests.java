package com.github.bechernie.dispatcherservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

@FunctionalSpringBootTest
@Disabled
class DispatchingFunctionIntegrationTests {

    @Autowired
    private FunctionCatalog catalog;

    @Test
    void packAndLabelOrder() {
        final Function<OrderAcceptedMessage, Flux<OrderDispatchedMessage>> packAndLabel = catalog.lookup(Function.class, "pack|label");
        final var orderId = 121L;
        StepVerifier
                .create(packAndLabel.apply(new OrderAcceptedMessage(orderId)))
                .expectNextMatches(dispatchedOrder -> dispatchedOrder.equals(new OrderDispatchedMessage(orderId)))
                .verifyComplete();
    }
}