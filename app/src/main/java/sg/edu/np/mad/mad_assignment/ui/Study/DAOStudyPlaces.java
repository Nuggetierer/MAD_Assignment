package sg.edu.np.mad.mad_assignment.ui.Study;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.ui.Event.Event;
import sg.edu.np.mad.mad_assignment.ui.Event.EventFragment;
import sg.edu.np.mad.mad_assignment.ui.Event.Updateeventactivity;

public class DAOStudyPlaces {

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private DatabaseReference databaseReference3;
    private FirebaseUser mAuth;
    private String currentUserID;

    public DAOStudyPlaces(){

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://mad-assg2-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = db.getReference(StudyPlaces.class.getSimpleName());
        databaseReference2 = db.getReference(Event.class.getSimpleName());

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth != null) {
            currentUserID = mAuth.getUid();
            Log.d("Uid","Uid: " + currentUserID);
            databaseReference3 = db.getReference(currentUserID);
        }
    }
    public Task<Void> add(StudyPlaces studyPlaces)
    {
        return databaseReference.push().setValue(studyPlaces);
    }
    public Task<Void> update(String key, HashMap<String,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String Key)
    {
        return databaseReference.child(Key).removeValue();
    }

    public Query get()
    {
        return databaseReference.orderByKey();
    }

    public Task<Void> adde(Event event)
    {
        return databaseReference2.push().setValue(event);
    }
    public Task<Void> updatee(String key, HashMap<String,Object> hashMap)
    {
        return databaseReference2.child(key).updateChildren(hashMap);
    }
    public Task<Void> updateAttende(String key, HashMap<String,Object> hashMap)
    {
        return databaseReference2.child(key).updateChildren(hashMap);
    }
    public Query gete()
    {
        return databaseReference2.orderByKey();
    }

//    public Task<Void> addeUser(Event event)
//    {
//        return databaseReference3.child("Event").push().setValue(event);
//    }
//
//    public Task<Void> updateAttendeUser(String key, HashMap<String,Object> hashMap)
//    {
//        return databaseReference3.child("Event").child(key).updateChildren(hashMap);
//    }
//    public Query geteUser()
//    {
//        return databaseReference3.child("Event").orderByKey();
//    }
}
