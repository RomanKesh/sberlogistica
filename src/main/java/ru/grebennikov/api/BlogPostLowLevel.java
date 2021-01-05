package ru.grebennikov.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.function.Supplier;

public final class BlogPostLowLevel extends LowApiLevel {

    public BlogPostLowLevel(Supplier<RequestSpecification> request) {
        super(request);
    }

    @Step
    public Response create(Object body) {
        return request()
                .contentType(ContentType.JSON)
                .body(body)
                .post(APIEndpoints.CREATE);
    }

    @Step
    public Response list(Map<String, ?> parameters) {
        return request()
                .params(parameters)
                .get(APIEndpoints.LIST);
    }

    @Step
    public Response getArchiveByYear(Integer year, Map<String, ?> parameters) {
        return request()
                .pathParam("year", year)
                .params(parameters)
                .get(APIEndpoints.ARCHIVE_BY_YEAR);
    }

    @Step
    public Response getArchiveByMonth(Integer year, Integer month, Map<String, ?> parameters) {
        return request()
                .pathParam("year", year)
                .pathParam("month", month)
                .params(parameters)
                .get(APIEndpoints.ARCHIVE_BY_MONTH);
    }

    @Step
    public Response getArchiveByDay(Integer year, Integer month, Integer day, Map<String, ?> parameters) {
        return request()
                .pathParam("year", year)
                .pathParam("month", month)
                .pathParam("day", day)
                .params(parameters)
                .get(APIEndpoints.ARCHIVE_BY_DAY);
    }

    @Step
    public Response get(Integer id) {
        return request()
                .pathParam("id", id)
                .get(APIEndpoints.GET);
    }

    @Step
    public Response delete(Integer id) {
        return request()
                .pathParam("id", id)
                .delete(APIEndpoints.DELETE);
    }

    @Step
    public Response update(Integer id, Object body) {
        return request()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .put(APIEndpoints.UPDATE);
    }
}
