package rodrigo.zaniolo.myshowcaseapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class OpenWeatherListModel implements Parcelable{

    @SerializedName("cnt")
    private int count;

    @SerializedName("list")
    private List<OpenWeatherModel> list;

    /* Constructors. */
    public OpenWeatherListModel() { }

    public OpenWeatherListModel(int count, List<OpenWeatherModel> list) {
        this.count = count;
        this.list = list;
    }

    protected OpenWeatherListModel(Parcel in) {
        count = in.readInt();
        if (in.readByte() == 0x01) {
            list = new ArrayList<OpenWeatherModel>();
            in.readList(list, OpenWeatherModel.class.getClassLoader());
        } else {
            list = null;
        }
    }

    /* Getters and Setters. */
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<OpenWeatherModel> getList() {
        return list;
    }

    public void setList(List<OpenWeatherModel> list) {
        this.list = list;
    }

    /* Methods. */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        if (list == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(list);
        }
    }

    public static final Parcelable.Creator<OpenWeatherListModel> CREATOR = new Parcelable.Creator<OpenWeatherListModel>() {
        @Override
        public OpenWeatherListModel createFromParcel(Parcel in) {
            return new OpenWeatherListModel(in);
        }

        @Override
        public OpenWeatherListModel[] newArray(int size) {
            return new OpenWeatherListModel[size];
        }
    };
}
