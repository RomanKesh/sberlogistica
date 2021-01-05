package ru.grebennikov.api;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import ru.grebennikov.model.PagePost;
import ru.grebennikov.model.Post;

import java.util.Map;

public final class BlogPostHighLevel extends HighApiLevel {
    private BlogPostLowLevel blogPostLow;

    public BlogPostHighLevel(BlogPostLowLevel blogPostLow) {
        this.blogPostLow = blogPostLow;
    }

    @Step
    public Post create(Post post) {
        return validateResponse(blogPostLow.create(post), HttpStatus.SC_CREATED, Post.class);
    }

    @Step
    public PagePost list(Map<String, ?> parameters) {
        return validateResponse(blogPostLow.list(parameters), HttpStatus.SC_OK, PagePost.class);
    }

    @Step
    public PagePost getArchiveByYear(Integer year, Map<String, ?> parameters) {
        return validateResponse(blogPostLow.getArchiveByYear(year, parameters), HttpStatus.SC_OK, PagePost.class);
    }

    @Step
    public PagePost getArchiveByMonth(Integer year, Integer month, Map<String, ?> parameters) {
        return validateResponse(blogPostLow.getArchiveByMonth(year, month, parameters), HttpStatus.SC_OK, PagePost.class);
    }

    @Step
    public PagePost getArchiveByDay(Integer year, Integer month, Integer day, Map<String, ?> parameters) {
        return validateResponse(blogPostLow.getArchiveByDay(year, month, day, parameters), HttpStatus.SC_OK, PagePost.class);
    }

    @Step
    public Post get(Integer id) {
        return validateResponse(blogPostLow.get(id), HttpStatus.SC_OK, Post.class);
    }

    @Step
    public void delete(Integer id) {
        validateResponse(blogPostLow.delete(id), HttpStatus.SC_NO_CONTENT);
    }

    @Step
    public Post update(Integer id, Post post) {
        return validateResponse(blogPostLow.update(id, post), HttpStatus.SC_OK, Post.class);
    }
}
