package ru.grebennikov.tests.blog_posts.post;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.model.Post;

import java.util.stream.Collectors;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation create a blog post with the success status code",
        acceptanceCriteria = "Method: POST, URL: /blog/posts/, Status code: 201 Create post successfully",
        precondition = "",
        steps = {
                @TestStep(
                        action = "Create a new body post and done POST method",
                        check = "Check that the response code is equal 201")
        }
)
public class CreateSuccessfully extends BaseTest {

    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        Post post = createNewPost(getSimplePost());
        Assert.assertTrue(getClient().getBlogPostHigh().list(ImmutableMap.of()).getItems().contains(post), "FAIL -> The list is not contains " + post.toString());
    }
}
