package com.l.remoteservicee;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.l.remoteservicee.bean.Book;

import java.util.ArrayList;
import java.util.List;

public class RemoteServicee extends Service {

    IBookManager.Stub mStub = new IBookManager.Stub() {

        ArrayList<Book> books = new ArrayList<>();

        @Override
        public List<Book> getBookList() throws RemoteException {
            Book a = new Book(1, "A");
            Book b = new Book(2, "B");
            books.add(a);
            books.add(b);
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}