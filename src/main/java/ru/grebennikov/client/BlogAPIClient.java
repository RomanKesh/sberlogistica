package ru.grebennikov.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import ru.grebennikov.api.BlogPostHighLevel;
import ru.grebennikov.api.BlogPostLowLevel;

@Getter
public final class BlogAPIClient {
    private String baseUrl;
    private BlogPostLowLevel blogPostLow;
    private BlogPostHighLevel blogPostHigh;

    public BlogAPIClient(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory((type, s) -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                            .registerModule(new Jdk8Module())
                            .registerModule(new JavaTimeModule())
                            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
                }
        ));
        this.blogPostLow = new BlogPostLowLevel();
        this.blogPostHigh = new BlogPostHighLevel();
    }

    public RequestSpecification request() {
        return RestAssured.given()
                .baseUri(baseUrl);
    }
}
