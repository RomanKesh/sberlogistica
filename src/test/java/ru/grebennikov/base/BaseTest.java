package ru.grebennikov.base;

import lombok.Getter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.grebennikov.client.BlogAPIClient;
import ru.grebennikov.model.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BaseTest {
    private static final String URL = "http://it-is-not.fun/api";
    private BlogAPIClient client;
    private List<Integer> createdPosts = new ArrayList<>();

    @BeforeClass
    private void beforeInit() {
        client = new BlogAPIClient(URL);
    }

    protected Post createNewPost(Post post) {
        client.getBlogPostHigh().create(post);
        Post createdPost = Utils.getCreatedPost(post, client);
        createdPosts.add(createdPost.getId());
        return createdPost;
    }

    protected Post getSimplePost() {
        return new Post("New title", "New body");
    }

    @AfterClass
    private void removeAllCreatedObjects() {
        createdPosts.forEach(post -> client.getBlogPostLow().delete(post));
        createdPosts.clear();
    }
}
