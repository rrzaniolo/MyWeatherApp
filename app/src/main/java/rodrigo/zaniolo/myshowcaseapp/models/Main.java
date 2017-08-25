package rodrigo.zaniolo.myshowcaseapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Main implements Parcelable{

    /* Variables. */
    @SerializedName("temp")
    private float currentTemp;

    @SerializedName("temp_max")
    private float maxTemp;

    @SerializedName("temp_min")
    private float minTemp;

    @SerializedName("humidity")
    private float humidity;

    @SerializedName("pressure")
    private float airPressure;

    /* Constructor. */
    public Main() { }

    public Main(float currentTemp, float maxTemp, float minTemp, float humidity, float airPressure) {
        this.currentTemp = currentTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidity = humidity;
        this.airPressure = airPressure;
    }

    protected Main(Parcel in) {
        currentTemp = in.readFloat();
        maxTemp = in.readFloat();
        minTemp = in.readFloat();
        humidity = in.readFloat();
        airPressure = in.readFloat();
    }

    /* Getters and Setters. */
    public float getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(float currentTemp) {
        this.currentTemp = currentTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(float airPressure) {
        this.airPressure = airPressure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(getCurrentTemp());
        dest.writeFloat(getMaxTemp());
        dest.writeFloat(getMinTemp());
        dest.writeFloat(getHumidity());
        dest.writeFloat(getAirPressure());
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };
}