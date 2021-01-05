package ru.grebennikov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Category {
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";

    private Integer id;
    @JsonProperty(required = true)
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
