package rodrigo.zaniolo.myweatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rrzaniolo on 24/08/17.
 */

@SuppressWarnings("unused")
public class Wind implements Parcelable{

    /* Variables. */
    @SerializedName("speed")
    private float speed;

    @SerializedName("deg")
    private double deg;

    /* Constructor. */
    public Wind() { }

    public Wind(float speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    protected Wind(Parcel in) {
        speed = in.readFloat();
        deg = in.readDouble();
    }

    /* Getter and Setters. */
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(getSpeed());
        dest.writeDouble(getDeg());
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };
}
