package sg.edu.np.mad.mad_assignment.ui.Event;

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

public class DAOEvent {

    private DatabaseReference databaseReference;
    private FirebaseUser mAuth;
    private String currentUserID;

    public DAOEvent(){

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://mad-assg2-default-rtdb.asia-southeast1.firebasedatabase.app/");

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth != null) {
            currentUserID = mAuth.getUid();
            Log.d("Uid","Uid: " + currentUserID);
            databaseReference = db.getReference(currentUserID);
        }
    }

    public Task<Void> addeUser(Event event)
    {
        return databaseReference.child("Event").push().setValue(event);
    }

    public Task<Void> updateAttendeUser(String key, HashMap<String,Object> hashMap)
    {
        return databaseReference.child("Event").child(key).updateChildren(hashMap);
    }
    public Query geteUser()
    {
        return databaseReference.child("Event").orderByKey();
    }
    public Query geteUserCheck()
    {
        return databaseReference.child("Event");
    }
    public Task<Void> remove(String Key)
    {
        return databaseReference.child(Key).removeValue();
    }
}
