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
    public final void beforeInit() {
        client = new BlogAPIClient(URL);
    }

    protected Post createNewPost(Post post) {
        client.getBlogPostHigh().create(post);
        Post createdPost = Utils.getCreatedPost(post, client);
        createdPosts.add(createdPost.getId());
        return createdPost;
    }

    protected Post getSimplePost() {
        Post post = new Post("New test title", "New test body");
        post.setCategoryId(1);
        post.setCategory("Sci-Fi");
        return post;
    }

    @AfterClass
    public final void removeAllCreatedObjects() {
        createdPosts.forEach(post -> client.getBlogPostLow().delete(post));
        createdPosts.clear();
    }
}
