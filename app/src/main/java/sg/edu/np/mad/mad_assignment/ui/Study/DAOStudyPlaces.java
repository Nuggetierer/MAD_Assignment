package sg.edu.np.mad.mad_assignment.ui.Study;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Queue;

import sg.edu.np.mad.mad_assignment.Event;
import sg.edu.np.mad.mad_assignment.ui.Study.StudyPlaces;

public class DAOStudyPlaces {

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    public DAOStudyPlaces(){

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://mad-assg2-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = db.getReference(StudyPlaces.class.getSimpleName());
        databaseReference2 = db.getReference(Event.class.getSimpleName());
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
}
