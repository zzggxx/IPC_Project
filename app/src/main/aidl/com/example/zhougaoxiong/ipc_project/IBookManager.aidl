// IBookManager.aidl
package com.example.zhougaoxiong.ipc_project;

import com.example.zhougaoxiong.ipc_project.aidl.Book;

// Declare any non-default types here with import statements

interface IBookManager {

    List<Book> getBookList();

        void addBook(Book book);
}
