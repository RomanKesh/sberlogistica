package ru.grebennikov.tests.blog_posts.put;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.model.Post;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation update a blog post with the success status code",
        acceptanceCriteria = "Method: PUT, URL: /blog/posts/{id}, Status code: 204 Post successfully updated",
        precondition = "1. Create a new post of blog",
        steps = {
                @TestStep(
                        action = "Create a new post and done PUT method",
                        check = "Check that the response code is equal 204")
        }
)
public class UpdateWithWrongBody extends BaseTest {

    @DataProvider
    private Object[] getPost() {
        return new Object[] {

        };
    }

    @Test(groups = "implemented", dataProvider = "getPost")
    public void TestUpdateMethod(Post post) {

    }
}
