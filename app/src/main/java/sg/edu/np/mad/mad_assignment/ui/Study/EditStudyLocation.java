package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.HashMap;

import sg.edu.np.mad.mad_assignment.R;

public class EditStudyLocation extends AppCompatActivity implements Serializable {

    private TextInputEditText title;
    private TextInputEditText location;
    private TextInputEditText desc;

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
        if(sp_edit != null)
        {
            title.setText(sp_edit.getStudyName());
            location.setText(sp_edit.getStudyLocation());
            desc.setText(sp_edit.getStudyDescription());

            editbuttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
            });
        }
        else
        {
            Intent newintent = new  Intent(this, AddStudyLocation.class);
            startActivity(newintent);
        }
    }
}