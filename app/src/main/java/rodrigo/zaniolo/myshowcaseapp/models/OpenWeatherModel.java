package rodrigo.zaniolo.myshowcaseapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class OpenWeatherModel implements Parcelable{

    /* Variables. */
    @SerializedName("id")
    private int cityId;

    @SerializedName("name")
    private String cityName;

    @SerializedName("weatherList")
    private List<Weather> weatherList;

    @SerializedName("main")
    private Main main;

    @SerializedName("wind")
    private Wind wind;

    /* Constructor. */
    public OpenWeatherModel() { }

    public OpenWeatherModel(int cityId, String cityName, List<Weather> weatherList, Main main, Wind wind) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.weatherList = weatherList;
        this.main = main;
        this.wind = wind;
    }

    protected OpenWeatherModel(Parcel in) {
        cityId = in.readInt();
        cityName = in.readString();
        weatherList = in.createTypedArrayList(Weather.CREATOR);
        main = in.readParcelable(Main.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
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

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getCityId());
        dest.writeString(getCityName());
        dest.writeList(getWeatherList());
        dest.writeValue(getMain());
        dest.writeValue(getWind());
    }

    public static final Creator<OpenWeatherModel> CREATOR = new Creator<OpenWeatherModel>() {
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
