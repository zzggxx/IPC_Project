1,remoteservice是app的远程服务
    创建的方法和详细步骤中不一样,需要在main文件下创建aidl的文件夹.
    在app中添加的本地文件写入的方式共享,即文件共享的方式
2,CustomerBinder是不适用系统提供的aidl方法实现的binder.gauge
3,app中增加进程间通过文件共享方式交换数据,但是有相当大的问题没有得到解决
4,Messenger,串行的执行消息,消息机制实现,底层其实也是AIDL实现的
5,BinderPool:binder连接池,设计场景:假如有100个地方要用到AIDL不可能实现100个Service,进行封装.
