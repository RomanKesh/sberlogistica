package ru.grebennikov.base;

import com.google.common.collect.ImmutableMap;
import ru.grebennikov.client.BlogAPIClient;
import ru.grebennikov.model.Post;

public class Utils {

    // Patch. The service works extremely strange and does not return the created object.
    public static Post getCreatedPost(Post post, BlogAPIClient client) {
        return client.getBlogPostHigh().list(ImmutableMap.of()).getItems().stream()
                .filter(p -> (p.getTitle().equals(post.getTitle()) && p.getBody().equals(post.getBody())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The needed post is not found!"));
    }
}
