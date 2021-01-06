package ru.grebennikov.tests.blog_posts.put;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestIssue;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.base.Utils;
import ru.grebennikov.model.Post;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation update a blog post with the future date",
        acceptanceCriteria = "Method: PUT, URL: /blog/posts/{id}, Status code: 204 Post successfully updated",
        precondition = "1. Create a new post of blog",
        steps = {
                @TestStep(
                        action = "Create a new body post and done PUT method",
                        check = "Check that the response code is equal 204")
        }
)
@TestIssue(
        id = "3",
        title = "Clarification about the \"pub_date\" field for \"Post\" object",
        description = "The \"pub_date\" field does not attribute \"Read only\" but created with a server and the user cannot update it." +
                "The same behaviour exists for the \"id\" field.",
        level = "Medium",
        status = "Open"
)
public class UpdateWithFutureDateOfBody extends BaseTest {
    private Post post;

    @BeforeMethod
    private void precondition() {
        post = createNewPost(getSimplePost());
    }

    @Issues({@Issue("Wrong behaviour with create a new post object"), @Issue("Clarification about the \"pub_date\" field for \"Post\" object")})
    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        Post expectedBody = new Post("New updated post", "The post after update");
        expectedBody.setCategoryId(2);
        Date date = new Date();
        date.setTime(date.getTime() + Integer.MAX_VALUE);
        expectedBody.setPubDate(date.toString());
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
