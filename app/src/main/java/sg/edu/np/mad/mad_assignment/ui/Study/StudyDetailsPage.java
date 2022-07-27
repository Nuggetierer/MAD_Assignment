package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.R;

public class StudyDetailsPage extends AppCompatActivity {
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    StudyDetailsAdapter mysdAdapter;
    TextView sdname, sddesc, sdloc;
    String sdnamestr, sddescstr, sdlocstr;
    int images[] = {R.drawable.atrium_mad};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_details_page);

        sdname = findViewById(R.id.StudyDetailsTop);
        sdloc = findViewById(R.id.StudyDetailsBottom);
        sddesc = findViewById(R.id.StudyDetailsDescription);

        getData();
        setdata();

        //Setting the data source

//        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        mysdAdapter = new StudyDetailsAdapter(images);
//        rv = findViewById(R.id.StudyDetailshorizontalRv);
//        rv.setLayoutManager(linearLayoutManager);
//        rv.setAdapter(mysdAdapter);
    }

    private void getData() {
            if (getIntent().hasExtra("Name") && getIntent().hasExtra("Desc")
                && getIntent().hasExtra("Loc")) {

            sdnamestr = getIntent().getStringExtra("Name");
            sddescstr = getIntent().getStringExtra("Desc");
            sdlocstr = getIntent().getStringExtra("Loc");
        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    private void setdata () {
        sdname.setText(sdnamestr);
        sddesc.setText(sddescstr);
        sdloc.setText(sdlocstr);
    }
}