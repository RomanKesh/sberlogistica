package ru.grebennikov.tests.blog_posts.put;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.model.Post;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation update a blog post with the wrong body",
        acceptanceCriteria = "Method: PUT, URL: /blog/posts/{id}, Status code: 400 Invalid data",
        precondition = "1. Create a new post of blog",
        steps = {
                @TestStep(
                        action = "Create a new body post and done PUT method",
                        check = "Check that the response code is equal 400")
        }
)
public class UpdateWithWrongBody extends BaseTest {
    private Integer id;

    @BeforeMethod
    private void precondition() {
        id = createNewPost(getSimplePost()).getId();
    }

    @DataProvider
    private Object[] getPost() {
        Post invalidCategoryId = new Post("New updated post", "The post after update");
        Post categoryIdLessThanZero = new Post("New updated post", "The post after update");
        Post wrongCategory = new Post("New updated post", "The post after update");
        Post emptyTitleField = new Post("New updated post", "The post after update");
        Post emptyBodyField = new Post("New updated post", "The post after update");
        Post wrongId = new Post("New updated post", "The post after update");
        Post wrongDate = new Post("New updated post", "The post after update");
        return new Object[] {
            invalidCategoryId, categoryIdLessThanZero, wrongCategory, emptyTitleField, emptyBodyField, wrongId, wrongDate
        };
    }

    @Test(groups = "implemented", dataProvider = "getPost")
    public void TestUpdateMethod(Post post) {
        getClient().getBlogPostLow().update(id, post)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
