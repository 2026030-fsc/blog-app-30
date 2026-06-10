package com.example.blog_app;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
    private final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Blog> findAll() {
        return jdbcClient.sql("SELECT id, title, texts FROM blogs")
          .query(Blog.class)
          .list();
    }

    // id取得してURLの最後につける
    public Optional<Blog> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, texts FROM blogs WHERE id = :id")
        .param("id", id)
        .query(Blog.class)
        .optional();
    }


    // フォームで入力したものをDBに追加
    public void save(Blog blog) { // DBに追加するだけなのでvoid
        jdbcClient.sql("INSERT INTO blogs (title, texts) VALUES (:title, :texts)") // :入力したものを埋め込む
        .param("title" , blog.getTitle())
        .param("texts", blog.getTexts())
        .update(); // 取り出すときは表のままで返ってくるが、今回は単語なのでlistじゃない
    }
}