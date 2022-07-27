package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Event.SendEmail;

public class AddStudyLocation extends AppCompatActivity {

    private TextInputEditText title;
    private TextInputEditText location;
    private TextInputEditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_study_location);

        title = (TextInputEditText)findViewById(R.id.StudyPlaceNameText);
        location = (TextInputEditText)findViewById(R.id.StudyPlaceLocationText);
        desc = (TextInputEditText)findViewById(R.id.StudyPlaceDescriptionText);
        Button buttonsubmit = findViewById(R.id.buttonsubmitStudy);

        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                        && !desc.getText().toString().isEmpty()) {

                    String titlestr = title.getText().toString();
                    String locationstr = location.getText().toString();
                    String descstr = desc.getText().toString();


                }
                else {
                    Toast.makeText(AddStudyLocation.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
