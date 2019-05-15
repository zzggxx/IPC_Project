package com.baronzhang.ipc.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.baronzhang.ipc.Book;
import com.baronzhang.ipc.proxy.Proxy;

import java.util.List;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 * 05/01/2018
 */
public abstract class Stub extends Binder implements BookManager {


    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    //    将服务端的binder装换成客户端所需要的aidl接口类型的对象
    //    如果服务端和客户端不是同一进程则返回的是系统封装的Stub.proxy对象,负责返回了服务端Stub本身
    public static BookManager asInterface(IBinder binder) {
        if (binder == null)
            return null;
        IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
        if (iin != null && iin instanceof BookManager)
            return (BookManager) iin;
        return new Proxy(binder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    /**
     * 运行在服务端的binder线程池中
     *
     * @param code  第一步:区分请求的方法是那一个
     * @param data  第二步:请求方法所携带的参数
     * @param reply 第三步:执行请求方法完毕后开始写入返回值
     * @param flags
     * @return
     * @throws RemoteException
     */
    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {

            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;

            case TRANSAVTION_getBooks:
                data.enforceInterface(DESCRIPTOR);
                List<Book> result = this.getBooks();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;

            case TRANSAVTION_addBook:
                data.enforceInterface(DESCRIPTOR);
                Book arg0 = null;
                if (data.readInt() != 0) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                }
                this.addBook(arg0);
                reply.writeNoException();
                return true;

        }
        return super.onTransact(code, data, reply, flags);
    }
}
