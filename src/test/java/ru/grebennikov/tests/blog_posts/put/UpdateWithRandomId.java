package ru.grebennikov.tests.blog_posts.put;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.model.Post;

import java.util.Random;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation update a blog post with the random id (this id is not existing)",
        acceptanceCriteria = "Method: PUT, URL: /blog/posts/{id}, Status code: 404 Post is not found",
        precondition = "",
        steps = {
                @TestStep(
                        action = "Create a new body post and done PUT method",
                        check = "Check that the response code is equal 404")
        }
)
public class UpdateWithRandomId  extends BaseTest {

    @Issue("Wrong behaviour with create a new post object")
    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        Post expectedBody = new Post("New updated post", "The post after update");
        expectedBody.setCategoryId(2);
        getClient().getBlogPostLow().update(new Random().nextInt(100) + 10, expectedBody)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
