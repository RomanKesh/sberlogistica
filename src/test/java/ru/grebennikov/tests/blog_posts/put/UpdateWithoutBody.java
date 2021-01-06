package ru.grebennikov.tests.blog_posts.put;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.grebennikov.annotation.TestIssue;
import ru.grebennikov.annotation.TestScenario;
import ru.grebennikov.annotation.TestStep;
import ru.grebennikov.base.BaseTest;

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
@TestIssue(
        id = "2",
        title = "Incorrect status code by update the existing post object without body",
        description = "The method PUT returns the successfully status code after update existing Post object without body.\n" +
                "According the swagger's rule the \"Post\" object contains two required fields (title and body) but the service " +
                "succesfully updated object without this field. Needs clarification about this rule.",
        level = "Critical",
        status = "Open"
)
public class UpdateWithoutBody  extends BaseTest {
    private Integer id;

    @BeforeMethod
    private void precondition() {
        id = createNewPost(getSimplePost()).getId();
    }

    @Issues({@Issue("Wrong behaviour with create a new post object"), @Issue("Incorrect status code by update the existing post object without body")})
    @Test(groups = "implemented")
    public void TestUpdateMethod() {
        getClient().getBlogPostLow().update(id, null)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
