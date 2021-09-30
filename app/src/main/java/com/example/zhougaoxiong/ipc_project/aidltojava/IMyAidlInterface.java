/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\AndroidProject\\IPC_Project\\remoteservice\\src\\main\\aidl\\com\\example\\remoteservice\\IMyAidlInterface.aidl
 */
package com.example.zhougaoxiong.ipc_project.aidltojava;
// Declare any non-default types here with import statements

/**
 * 以下生成的东西是固定的格式(自己容易混淆接口那是因为自己很少用接口,容易和抽象类搞混淆,基础不知识淡忘了而已)
 *
 * 生成的 接口类 继承自 (系统的)接口类IInterface
 */
public interface IMyAidlInterface extends android.os.IInterface {
    /**
     * 这也是自定义的,不是接口类定义的,注意这是一个抽象类继承自系统的Binder,并且实现了自定义的接口类
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements IMyAidlInterface {
        private static final String DESCRIPTOR = "com.example.remoteservice.IMyAidlInterface";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.example.remoteservice.IMyAidlInterface interface,
         * generating a proxy if needed.
         */
        public static IMyAidlInterface asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IMyAidlInterface))) {
                return ((IMyAidlInterface) iin);
            }
            return new Proxy(obj);  //注意这里这个链式文件
        }

//        返回的是这个sub类本身
        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_add: {
                    data.enforceInterface(descriptor);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    int _result = this.add(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_subtract: {
                    data.enforceInterface(descriptor);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    int _result = this.subtract(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements IMyAidlInterface {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public int add(int i, int j) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(i);
                    _data.writeInt(j);
                    mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public int subtract(int i, int j) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(i);
                    _data.writeInt(j);
                    mRemote.transact(Stub.TRANSACTION_subtract, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_subtract = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    public int add(int i, int j) throws android.os.RemoteException;

    public int subtract(int i, int j) throws android.os.RemoteException;
}
