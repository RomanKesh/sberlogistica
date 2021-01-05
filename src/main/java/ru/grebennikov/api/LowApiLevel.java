package ru.grebennikov.api;

import io.restassured.specification.RequestSpecification;

import java.util.function.Supplier;

public abstract class LowApiLevel {
    private Supplier<RequestSpecification> request;

    protected LowApiLevel(Supplier<RequestSpecification> request) {
        this.request = request;
    }

    protected RequestSpecification request() {
        return request.get();
    }
}
