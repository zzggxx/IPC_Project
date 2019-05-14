package com.example.zhougaoxiong.ipc_project.aidl;

import java.util.List;

public interface IBookManager {

    List<Book> getBookList();

    void addBook(Book book);
}
