package ru.grebennikov.api;

final class APIEndpoints {

    // blog/posts   Operations related to blog posts
    static final String CREATE = "/blog/posts/";                                         // Creates a new blog post
    static final String LIST = "/blog/posts/";                                           // Returns list of blog posts
    static final String ARCHIVE_BY_YEAR = "/blog/posts/archive/{year}/";                 // Returns list of blog posts from a specified time period
    static final String ARCHIVE_BY_MONTH = "/blog/posts/archive/{year}/{month}/";        // Returns list of blog posts from a specified time period
    static final String ARCHIVE_BY_DAY = "/blog/posts/archive/{year}/{month}/{day}/";    // Returns list of blog posts from a specified time period
    static final String DELETE = "/blog/posts/{id}";                                     // Deletes blog post
    static final String GET = "/blog/posts/{id}";                                        // Returns a blog post
    static final String UPDATE = "/blog/posts/{id}";                                     // Updates a blog post
}