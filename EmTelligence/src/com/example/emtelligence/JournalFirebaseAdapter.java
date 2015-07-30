package com.example.emtelligence;

import android.content.res.Resources;

import com.firebase.client.Firebase;

/**
 * Created by Kemari Legg on 5/21/2015.
 */
import com.example.emtelligence.MainActivity;
public class JournalFirebaseAdapter {
    String FIREBASE_REFERENCE = "https://emtelligence.firebaseio.com/";
    Firebase myFirebaseRef =  new Firebase(FIREBASE_REFERENCE);
    
}
