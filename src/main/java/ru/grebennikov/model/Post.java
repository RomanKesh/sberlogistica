package ru.grebennikov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Post {
    public static final String ID_FIELD = "id";
    public static final String TITLE_FIELD = "title";
    public static final String BODY_FIELD = "body";
    public static final String PUB_DATE_FIELD = "pub_date";
    public static final String CATEGORY_ID_FIELD = "category_id";
    public static final String CATEGORY_FIELD = "category";

    private Integer id;
    @JsonProperty(required = true)
    private String title;
    @JsonProperty(required = true)
    private String body;
    @JsonProperty("pub_date")
    private String pubDate;
    @JsonProperty("category_id")
    private Integer categoryId;
    private String category;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
