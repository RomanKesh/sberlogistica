package ru.grebennikov.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.Step;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class HighApiLevel {

    @Step
    protected <P> P validateResponse(Response response, int status, Class<P> clazz) {
        P body = validateResponse(response, status)
                .contentType(ContentType.JSON)
                .extract().as(clazz);
        //checkRequiredFieldsIsNotNull(body);
        return body;
    }

    @Step
    protected ValidatableResponse validateResponse(Response response, int status) {
        return response.then()
                .log().ifValidationFails(LogDetail.BODY)
                .log().ifError()
                .statusCode(status);
    }

    @Step
    protected void checkRequiredFieldsIsNotNull(Object object) {
        try {
            Class clazz = object.getClass();
            do {
                for (Field field : clazz.getDeclaredFields()) {
                    JsonProperty property = field.getAnnotation(JsonProperty.class);
                    if (property != null && property.required()) {
                        String filedName = field.getName();
                        if (object.getClass().getMethod("get" + filedName.substring(0, 1).toUpperCase() +
                                filedName.substring(1)).invoke(object) == null) {
                            throw new AssertionError("FAIL -> Required field " + filedName + "does not have any value!");
                        }
                    }
                }
                clazz = clazz.getSuperclass();
            } while (clazz != null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
