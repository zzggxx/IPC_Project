package com.example.zhougaoxiong.ipc_project;

import java.io.Serializable;

public class Book implements Serializable {

    private int bookId;
    private String bookPrice;

    public Book(int bookId, String bookPrice) {
        this.bookId = bookId;
        this.bookPrice = bookPrice;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookPrice='" + bookPrice + '\'' +
                '}';
    }
}
