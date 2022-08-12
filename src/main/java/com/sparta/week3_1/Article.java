package com.sparta.week3_1;

public class Article {
    String title;
    String content;
    String author;
    int password;

    public Article() {

    }

    public Article(String title, String content, String author, int password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
