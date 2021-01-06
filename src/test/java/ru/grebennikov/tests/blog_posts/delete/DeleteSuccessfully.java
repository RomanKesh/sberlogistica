package ru.grebennikov.tests.blog_posts.delete;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.model.Post;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation delete the existing blog post with the success status code",
        acceptanceCriteria = "Method: DELETE, URL: /blog/posts/{id}, Status code: 204 No content",
        precondition = "1. Create a new post of blog",
        steps = {
                @TestStep(
                        action = "Done DELETE method",
                        check = "Check that the response code is equal 204"),
                @TestStep(
                        action = "Done GET by id method",
                        check = "Check that the response code is equal 404")
        }
)
public class DeleteSuccessfully extends BaseTest {
    private Integer id;

    @BeforeMethod
    private void precondition() {
        Post post = getSimplePost();
        id = createNewPost(post).getId();
    }

    @Issue("Wrong behaviour with create a new post object")
    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        getClient().getBlogPostHigh().delete(id);
        getClient().getBlogPostLow().get(id)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
