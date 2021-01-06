package ru.grebennikov.tests.blog_posts.list;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;
import ru.grebennikov.model.Post;

import java.util.List;
import java.util.stream.Collectors;

@Feature("My Blog API")
@Story("blog/posts Operations related to blog posts")
@TestScenario(
        title = "Check an operation update a blog post with the success status code",
        acceptanceCriteria = "Method: GET, URL: /blog/posts/, Status code: 200 Return list successfully",
        precondition = "1. Create a new post of blog",
        steps = {
                @TestStep(
                        action = "Create a new body post and done PUT method",
                        check = "Check that the response code is equal 200 and the list is not empty and contains the created post")
        }
)
public class GetListSuccessfully extends BaseTest {
    private Integer id;

    @BeforeMethod
    private void precondition() {
        id = createNewPost(getSimplePost()).getId();
    }

    @Issue("Wrong behaviour with create a new post object")
    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        List<Post> posts = getClient().getBlogPostHigh().list(ImmutableMap.of()).getItems();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(posts.size()).isGreaterThan(0);
            softAssertions.assertThat(posts.stream().map(Post::getId).collect(Collectors.toList())).contains(id);
        });
    }
}
