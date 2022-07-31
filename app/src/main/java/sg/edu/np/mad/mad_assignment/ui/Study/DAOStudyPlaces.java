package sg.edu.np.mad.mad_assignment.ui.Study;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import sg.edu.np.mad.mad_assignment.ui.Event.Event;

public class DAOStudyPlaces {

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private DatabaseReference databaseReference3;
    private FirebaseUser mAuth;
    private String currentUserID;
    private StorageReference reference = FirebaseStorage.getInstance("gs://mad-assg2.appspot.com").getReference("Image");

    public DAOStudyPlaces(){

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://mad-assg2-default-rtdb.asia-southeast1.firebasedatabase.app/");
        FirebaseStorage  fbs = FirebaseStorage.getInstance("gs://mad-assg2.appspot.com");

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

    public Task<Void> addImages(String key ,String uri)
    {
        return databaseReference.child(key).child("Images").push().setValue(uri);
    }
    public Task<Void> updateImage(String key, HashMap<String,Object> hashMap)
    {
        return databaseReference.child(key).child("Images").updateChildren(hashMap);
    }

    public UploadTask addStorageImages(Uri uri)
    {
        return reference.putFile(uri);
    }
    public Task<Void> updateStorageImage(String key, HashMap<String,Object> hashMap, String urikey)
    {
        return databaseReference.child(key).child("Uri").child(urikey).updateChildren(hashMap);
    }

    public Query get()
    {
        return databaseReference.orderByKey();
    }

    public Query getimage(String key)
    {
        return databaseReference.child(key).child("Images").orderByKey();
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
