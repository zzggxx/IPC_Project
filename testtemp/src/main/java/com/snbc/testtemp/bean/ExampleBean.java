package com.snbc.testtemp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 自动补齐是有问题的.需要手动补齐的.
 *
 * author: zhougaoxiong
 * date: 2020/9/4,19:07
 * projectName:IPC_Project
 * packageName:com.snbc.testtemp.bean
 */
class ExampleBean implements Parcelable {

    private String typeName;
    private int medalType;
    private String typeId;
    private int sort;
    private User mBean;
    private ArrayList<User> medals = new ArrayList<>(); //这里是集合所以必须得进行初始化. private boolean show;
    private boolean show;

    protected ExampleBean(Parcel in) {
        typeName = in.readString();
        medalType = in.readInt();
        typeId = in.readString();
        sort = in.readInt();
        mBean = in.readParcelable(User.class.getClassLoader());
//        medals = in.createTypedArrayList(User.CREATOR);
        show = in.readByte() != 0;
    }

    public static final Creator<ExampleBean> CREATOR = new Creator<ExampleBean>() {
        @Override
        public ExampleBean createFromParcel(Parcel in) {
            return new ExampleBean(in);
        }

        @Override
        public ExampleBean[] newArray(int size) {
            return new ExampleBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeName);
        dest.writeInt(medalType);
        dest.writeString(typeId);
        dest.writeInt(sort);
        dest.writeByte((byte) (show ? 1 : 0));
    }
}
