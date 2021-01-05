package ru.grebennikov.tests.blog_posts.put;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.model.Post;

import java.util.Random;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation update a blog post without a body",
        acceptanceCriteria = "Method: PUT, URL: /blog/posts/{id}, Status code: 400 Invalid data",
        precondition = "1. Create a new post of blog",
        steps = {
                @TestStep(
                        action = "Create a new body post and done PUT method",
                        check = "Check that the response code is equal 400")
        }
)
public class UpdateWithoutBody  extends BaseTest {
    private Integer id;

    @BeforeMethod
    private void precondition() {
        id = createNewPost(getSimplePost()).getId();
    }

    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        Post expectedBody = new Post();
        getClient().getBlogPostLow().update(id, expectedBody)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
