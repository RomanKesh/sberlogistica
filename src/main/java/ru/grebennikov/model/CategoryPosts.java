package ru.grebennikov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CategoryPosts {
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String POSTS_FIELD = "posts";

    private Integer id;
    @JsonProperty(required = true)
    private String name;
    private List<Post> posts;

    public CategoryPosts(String name) {
        this.name = name;
    }
}
