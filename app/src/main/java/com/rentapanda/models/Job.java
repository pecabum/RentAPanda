package com.rentapanda.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.rentapanda.R;

import java.util.Date;

/**
 * Created by pecabum on 03.06.16.
 */
public class Job implements Parcelable {

    public static final int ONCE = 0;
    public static final int WEEKLY = 7;
    public static final int TWO_WEEKS = 14;
    public static final int MONTHLY = 28;
    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
    private String __status;
    private String customer_name;
    private String distance;
    private Date job_date;
    private String extras;
    private String order_duration;
    private String order_id;
    private String order_time;
    private String payment_method;
    private String price;
    private int recurrency;
    private String job_city;
    private String job_latitude;
    private String job_longitude;
    private long job_postalcode;
    private String job_street;
    private String status;

    protected Job(Parcel in) {
        __status = in.readString();
        customer_name = in.readString();
        distance = in.readString();
        extras = in.readString();
        order_duration = in.readString();
        order_id = in.readString();
        order_time = in.readString();
        payment_method = in.readString();
        price = in.readString();
        recurrency = in.readInt();
        job_city = in.readString();
        job_latitude = in.readString();
        job_longitude = in.readString();
        job_postalcode = in.readLong();
        job_street = in.readString();
        status = in.readString();
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String get__status() {
        return __status;
    }

    public void set__status(String __status) {
        this.__status = __status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getDistance() {
        if (!distance.equals("")) {
            return String.format("%.2f", Double.parseDouble(distance));
        } else {
            return "";
        }
    }

    public Date getJobDate() {
        return job_date;
    }

    public String getOrderDuration() {
        return order_duration;
    }

    public String getOrderTime() {
        return order_time;
    }

    public String getPaymentMethod() {
        return payment_method;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRecurrency(Context ctx) {
        if (recurrency == Job.ONCE) {
            return ctx.getString(R.string.once);
        } else if (recurrency == Job.WEEKLY) {
            return ctx.getString(R.string.weekly);
        } else if (recurrency == Job.TWO_WEEKS) {
            return ctx.getString(R.string.two_weeks);
        } else {
            return ctx.getString(R.string.monthly);
        }
    }

    public void setRecurrency(int recurrency) {
        this.recurrency = recurrency;
    }

    public String getJobCity() {
        return job_city;
    }

    public long getJobPostalcode() {
        return job_postalcode;
    }

    public String getJobStreet() {
        return job_street;
    }

    public void setJob_street(String job_street) {
        this.job_street = job_street;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(__status);
        dest.writeString(customer_name);
        dest.writeString(distance);
        dest.writeString(extras);
        dest.writeString(order_duration);
        dest.writeString(order_id);
        dest.writeString(order_time);
        dest.writeString(payment_method);
        dest.writeString(price);
        dest.writeInt(recurrency);
        dest.writeString(job_city);
        dest.writeString(job_latitude);
        dest.writeString(job_longitude);
        dest.writeLong(job_postalcode);
        dest.writeString(job_street);
        dest.writeString(status);
    }
}
