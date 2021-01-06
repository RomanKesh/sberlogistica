package ru.grebennikov.tests.blog_posts.put;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.base.Utils;
import ru.grebennikov.model.Post;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation update a blog post with the success status code",
        acceptanceCriteria = "Method: PUT, URL: /blog/posts/{id}, Status code: 200 Post successfully updated",
        precondition = "1. Create a new post of blog",
        steps = {
                @TestStep(
                        action = "Create a new body post and done PUT method",
                        check = "Check that the response code is equal 200 because the \"pub_date\" " +
                                "field is not required and setup automatically on a server")
        }
)
public class UpdateSuccessfully extends BaseTest {
    private Post post;

    @BeforeMethod
    private void precondition() {
        post = createNewPost(getSimplePost());
    }

    @Issue("Wrong behaviour with create a new post object")
    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        Post expectedBody = new Post("New updated post", "The post after update");
        expectedBody.setCategoryId(2);
        getClient().getBlogPostHigh().update(post.getId(), expectedBody);
        Post actualResult = Utils.getCreatedPost(expectedBody, getClient());
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResult.getTitle()).isEqualTo(expectedBody.getTitle());
            softAssertions.assertThat(actualResult.getBody()).isEqualTo(expectedBody.getBody());
            softAssertions.assertThat(actualResult.getCategoryId()).isEqualTo(expectedBody.getCategoryId());
            softAssertions.assertThat(actualResult.getId()).isEqualTo(post.getId());
            softAssertions.assertThat(actualResult.getPubDate()).isEqualTo(post.getPubDate());
            softAssertions.assertThat(actualResult.getCategory()).isNotEqualTo(post.getCategory());
        });
    }
}
