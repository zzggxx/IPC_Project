package com.l.remoteservicee;

import com.l.remoteservicee.bean.Book;
//import com.ryg.chapter_2.aidl.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
//     void registerListener(IOnNewBookArrivedListener listener);
//     void unregisterListener(IOnNewBookArrivedListener listener);
}