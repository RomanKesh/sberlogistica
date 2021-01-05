package ru.grebennikov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PagePost {
    public static final String PAGE_FIELD = "page";
    public static final String PAGES_FIELD = "pages";
    public static final String PER_PAGE_FIELD = "per_page";
    public static final String TOTAL_FIELD = "total";
    public static final String ITEMS_FIELD = "items";

    private Integer page;
    private Integer pages;
    @JsonProperty("per_page")
    private Integer perPage;
    private Integer total;
    private List<Post> items;
}
