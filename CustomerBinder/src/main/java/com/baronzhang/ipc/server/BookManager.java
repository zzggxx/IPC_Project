package com.baronzhang.ipc.server;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import com.baronzhang.ipc.Book;

import java.util.List;

/**
 * 这个类用来定义服务端 RemoteService 具备什么样的能力
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 * 05/01/2018
 */
public interface BookManager extends IInterface {

//    binder的唯一标识,一般用类名表示
    static final String DESCRIPTOR = "com.baronzhang.ipc.server.BookManager";

//    方法的区别标识
    static final int TRANSAVTION_getBooks = IBinder.FIRST_CALL_TRANSACTION;
    static final int TRANSAVTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

//    由客户端进行调用.
//    创建Parcel data, Parcel reply,在由transact方法发起RPC(远程请求),同时线程挂起,然后服务端的
//    onTransact会被调用,完事后开始reply写返回值,
    List<Book> getBooks() throws RemoteException;

    void addBook(Book book) throws RemoteException;
}
