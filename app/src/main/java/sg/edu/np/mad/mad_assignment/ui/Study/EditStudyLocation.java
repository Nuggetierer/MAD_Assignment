package sg.edu.np.mad.mad_assignment.ui.Study;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import sg.edu.np.mad.mad_assignment.R;

public class EditStudyLocation extends AppCompatActivity implements Serializable {

    private TextInputEditText title;
    private TextInputEditText location;
    private TextInputEditText desc;
    StudyPlaces sp;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_study_location);

        title = (TextInputEditText)findViewById(R.id.EditStudyPlaceNameText);
        location = (TextInputEditText)findViewById(R.id.EditStudyPlaceLocationText);
        desc = (TextInputEditText)findViewById(R.id.EditStudyPlaceDescriptionText);
        Button editbuttons = findViewById(R.id.EditbuttonsubmitStudy);
        DAOStudyPlaces dao = new DAOStudyPlaces();

        StudyPlaces sp_edit =  (StudyPlaces) getIntent().getSerializableExtra("EDIT");
        sp = sp_edit;
        key = sp_edit.getKey();
        Button UpdateImage = findViewById(R.id.UpdateImagesButton);

        UpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditStudyLocation.this, UpdateImages.class );
                intent.putExtra("Key" ,sp_edit.getKey());
                startActivity(intent);
            }
        });


        if(sp_edit != null)
        {
            title.setText(sp_edit.getStudyName());
            location.setText(sp_edit.getStudyLocation());
            desc.setText(sp_edit.getStudyDescription());

            editbuttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    title = (TextInputEditText)findViewById(R.id.EditStudyPlaceNameText);
                    location = (TextInputEditText)findViewById(R.id.EditStudyPlaceLocationText);
                    desc = (TextInputEditText)findViewById(R.id.EditStudyPlaceDescriptionText);

                    if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                            && !desc.getText().toString().isEmpty()) {

                        HashMap<String,Object> hashMap=new HashMap<>();
                        hashMap.put("studyName",title.getText().toString());
                        hashMap.put("studyLocation",location.getText().toString());
                        hashMap.put("studyDescription",desc.getText().toString());
                        dao.update(sp_edit.getKey(), hashMap).addOnSuccessListener(suc->
                        {
                            Toast.makeText(EditStudyLocation.this,"Record is updated",Toast.LENGTH_SHORT).show();
                            finish();
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(EditStudyLocation.this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
                        });
                    }
                    else {
                        Toast.makeText(EditStudyLocation.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else
        {
            Intent newintent = new  Intent(this, AddStudyLocation.class);
            startActivity(newintent);
        }
    }

    @Override
    public void onBackPressed() {
//        DAOStudyPlaces iDao = new DAOStudyPlaces();
//
//        sp.setKey(null);
//        sp.setUri(null);
//        iDao.add(sp);
//
//        iDao.get().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot)
//            {
//                ArrayList<StudyPlaces> spal = new ArrayList<>();
//                for(DataSnapshot data : snapshot.getChildren()){
//                    StudyPlaces sp1 =  data.getValue(StudyPlaces.class);
//                    if(sp1.getStudyName().equals(sp.getStudyName())){
//                        sp.setKey(data.getKey());
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        iDao.getimage(key).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot)
//            {
//                for(DataSnapshot data : snapshot.getChildren()){
//                    Images i =  data.getValue(Images.class);
//                    iDao.addImages(sp.getKey(), i);
//                    i.setKey(data.getKey());
//                    Log.d("Uri1", "" + i.getUri());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        iDao.remove(key);

        Toast.makeText(this, "To remove added images, click edit -> edit images, Use the 3 dots in the corner to remove the image", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "To remove added images, click edit -> edit images, Use the 3 dots in the corner to remove the image", Toast.LENGTH_SHORT).show();

        super.onBackPressed();
    }
}