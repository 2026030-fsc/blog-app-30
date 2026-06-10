package com.example.blog_app;

public class BlogForm {
    // フィールド：後から値を返れるようにprivate finalにはしない
    private String title;
    private String texts;

    // デフォルトコンストラクタ
    public BlogForm() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public String getTitle() {
        return title;
    }

    public String getTexts() {
        return texts;
    }
}