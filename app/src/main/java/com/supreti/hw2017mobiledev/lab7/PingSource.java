package com.supreti.hw2017mobiledev.lab7;


import android.content.Context;

import java.util.List;

public class PingSource {

    public interface PingListener {
        void onPingsReceived(List<Ping> pingList);
    }

    private static PingSource sNewsSource;

    private Context mContext;

    public static PingSource get(Context context) {
        if (sNewsSource == null) {
            sNewsSource = new PingSource(context);
        }
        return sNewsSource;
    }

    private PingSource(Context context) {
        mContext = context;
    }

    // Firebase methods for you to implement.

    public void getPings(final PingListener pingListener) {


    }

    public void getPingsForUserId(String userId, final PingListener pingListener) {

    }

    public void sendPing(Ping ping) {

    }
}
