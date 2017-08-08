package com.supreti.hw2017mobiledev.lab7;


import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference pingsRef = mDatabase.getReference("pings");
        Query last50PingsQuery = pingsRef.limitToLast(50);
        last50PingsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Ping> pings_list = new ArrayList<>();
                Iterable<DataSnapshot> pingDataSnapshots = dataSnapshot.getChildren();
                for(DataSnapshot snapShot: pingDataSnapshots) {
                    Ping ping = new Ping(snapShot);
                    pings_list.add(ping);
                }
                pingListener.onPingsReceived(pings_list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getPingsForUserId(String userId, final PingListener pingListener) {
        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference pingsRef = mDatabase.getReference("pings");
        Query userQuery = pingsRef.orderByChild("userId").equalTo(userId).limitToLast(50);

        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Ping> pings_list = new ArrayList<>();
                Iterable<DataSnapshot> pingSnaphots = dataSnapshot.getChildren();
                for (DataSnapshot mySnapshot: pingSnaphots){
                    Ping myPing = new Ping(mySnapshot);
                    pings_list.add(myPing);
                }
                pingListener.onPingsReceived(pings_list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void sendPing(Ping ping) {
        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference pingsRef = mDatabase.getReference("pings");

        DatabaseReference newPingRef = pingsRef.push();
        Map<String, Object> pingValMap = new HashMap<>();
        pingValMap.put("userName", ping.getUserName());
        pingValMap.put("userId", ping.getUserId());
        pingValMap.put("timestamp", ServerValue.TIMESTAMP);
        newPingRef.setValue(pingValMap);
    }
}
