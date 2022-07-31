package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.R;

public class AddStudyLocation extends AppCompatActivity {

    private TextInputEditText title;
    private TextInputEditText location;
    private TextInputEditText desc;
    private String Uri = "";
    private String Key;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_study_location);

        title = (TextInputEditText)findViewById(R.id.StudyPlaceNameText);
        location = (TextInputEditText)findViewById(R.id.StudyPlaceLocationText);
        desc = (TextInputEditText)findViewById(R.id.StudyPlaceDescriptionText);
        Button buttonsubmit = findViewById(R.id.buttonsubmitStudy);
        Button UploadImage   = findViewById(R.id.UploadImageAddSPbutton);
        DAOStudyPlaces dao = new DAOStudyPlaces();

        getdata();

        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                        && !desc.getText().toString().isEmpty()) {

                    String titlestr = title.getText().toString();
                    String locationstr = location.getText().toString();
                    String descstr = desc.getText().toString();

                    if(!Uri.equals("")){
                        StudyPlaces newsp = new StudyPlaces(titlestr,locationstr,descstr);
                        dao.add(newsp).addOnSuccessListener(suc->
                        {
                            dao.get().addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    ArrayList<StudyPlaces> spal = new ArrayList<>();
                                    for (DataSnapshot data : snapshot.getChildren()) {
                                        StudyPlaces sp = data.getValue(StudyPlaces.class);
                                        sp.setKey(data.getKey());
                                        spal.add(sp);
                                        Key = data.getKey();
                                        if(titlestr.equals(sp.getStudyName()) && count == 0){
                                            count += 1;
                                            dao.addImages(sp.getKey() ,Uri);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            Toast.makeText(AddStudyLocation.this, "Study is Added", Toast.LENGTH_SHORT).show();
                            title.setText("");
                            location.setText("");
                            desc.setText("");
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(AddStudyLocation.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }
                    else{
                        StudyPlaces newsp = new StudyPlaces(titlestr,locationstr,descstr);
                        dao.add(newsp).addOnSuccessListener(suc->
                        {
                            Toast.makeText(AddStudyLocation.this, "Study is Added", Toast.LENGTH_SHORT).show();
                            title.setText("");
                            location.setText("");
                            desc.setText("");
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(AddStudyLocation.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }
                    finish();
                }
                else {
                    Toast.makeText(AddStudyLocation.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        UploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                        && !desc.getText().toString().isEmpty()) {
                    Intent uploadintent = new Intent(AddStudyLocation.this, ImportImages.class);
                    startActivityForResult(uploadintent, 1);
                }
                else {
                    Toast.makeText(AddStudyLocation.this, "Please fill all the fields first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getdata(){
        if(getIntent().hasExtra("Uri")){
            Uri = getIntent().getStringExtra("Name");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri = data.getStringExtra("Uri");
        }
    }

}
