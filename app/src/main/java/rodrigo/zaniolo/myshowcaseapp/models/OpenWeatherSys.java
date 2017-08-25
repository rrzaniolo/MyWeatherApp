package rodrigo.zaniolo.myshowcaseapp.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OpenWeatherSys implements Parcelable{

    /* Variables. */
    @SerializedName("country")
    private String countryCode;

    /* Constructors. */
    public OpenWeatherSys() { }

    public OpenWeatherSys(String countryCode) {
        this.countryCode = countryCode;
    }

    protected OpenWeatherSys(Parcel in) {
        countryCode = in.readString();
    }

    public static final Creator<OpenWeatherSys> CREATOR = new Creator<OpenWeatherSys>() {
        @Override
        public OpenWeatherSys createFromParcel(Parcel in) {
            return new OpenWeatherSys(in);
        }

        @Override
        public OpenWeatherSys[] newArray(int size) {
            return new OpenWeatherSys[size];
        }
    };

    /* Getters and Setters. */
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getCountryCode());
    }

    /* Methods. */
}