package rodrigo.zaniolo.myshowcaseapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class OpenWeatherModel implements Parcelable{

    /* Variables. */
    @SerializedName("id")
    private int cityId;

    @SerializedName("name")
    private String cityName;

    @SerializedName("weather")
    private List<Weather> weatherList;

    @SerializedName("main")
    private WeatherMainInfo weatherMainInfo;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("sys")
    private OpenWeatherSys openWeatherSys;

    /* Constructor. */
    public OpenWeatherModel() { }

    public OpenWeatherModel(int cityId, String cityName, List<Weather> weatherList, WeatherMainInfo weatherMainInfo, Wind wind, OpenWeatherSys openWeatherSys) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.weatherList = weatherList;
        this.weatherMainInfo = weatherMainInfo;
        this.wind = wind;
        this.openWeatherSys = openWeatherSys;
    }

    protected OpenWeatherModel(Parcel in) {
        cityId = in.readInt();
        cityName = in.readString();
        if (in.readByte() == 0x01) {
            weatherList = new ArrayList<Weather>();
            in.readList(weatherList, Weather.class.getClassLoader());
        } else {
            weatherList = null;
        }
        weatherMainInfo = (WeatherMainInfo) in.readValue(WeatherMainInfo.class.getClassLoader());
        wind = (Wind) in.readValue(Wind.class.getClassLoader());
        openWeatherSys = (OpenWeatherSys) in.readValue(OpenWeatherSys.class.getClassLoader());
    }

    /* Getter and Setters. */
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public WeatherMainInfo getWeatherMainInfo() {
        return weatherMainInfo;
    }

    public void setWeatherMainInfo(WeatherMainInfo weatherMainInfo) {
        this.weatherMainInfo = weatherMainInfo;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public OpenWeatherSys getOpenWeatherSys() {
        return openWeatherSys;
    }

    public void setOpenWeatherSys(OpenWeatherSys openWeatherSys) {
        this.openWeatherSys = openWeatherSys;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cityId);
        dest.writeString(cityName);
        if (weatherList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(weatherList);
        }
        dest.writeValue(weatherMainInfo);
        dest.writeValue(wind);
        dest.writeValue(openWeatherSys);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<OpenWeatherModel> CREATOR = new Parcelable.Creator<OpenWeatherModel>() {
        @Override
        public OpenWeatherModel createFromParcel(Parcel in) {
            return new OpenWeatherModel(in);
        }

        @Override
        public OpenWeatherModel[] newArray(int size) {
            return new OpenWeatherModel[size];
        }
    };
}
