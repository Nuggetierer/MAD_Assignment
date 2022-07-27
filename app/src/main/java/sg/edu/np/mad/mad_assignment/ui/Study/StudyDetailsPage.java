package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;

public class StudyDetailsPage extends AppCompatActivity {
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    StudyDetailsAdapter mysdAdapter;
    TextView sdname, sddesc, sdloc;
    String sdnamestr, sddescstr, sdlocstr;
    Integer sdimgstr;
    ImageView sdimg;
    int images[] = {R.drawable.atrium_map};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_details_page);

        sdname = findViewById(R.id.StudyDetailsTop);
        sdloc = findViewById(R.id.StudyDetailsBottom);
        sddesc = findViewById(R.id.StudyDetailsDescription);
        sdimg = findViewById(R.id.SDimageview);

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
                && getIntent().hasExtra("Loc") && getIntent().hasExtra("Draw")) {

            sdnamestr = getIntent().getStringExtra("Name");
            sddescstr = getIntent().getStringExtra("Desc");
            sdlocstr = getIntent().getStringExtra("Loc");
            sdimgstr = getIntent().getIntExtra("Draw",0);
        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    private void setdata () {
        sdimg.setImageURI(Uri.parse("android.resource://" + MainActivity.PACKAGENAME + "/" + sdimgstr));
        sdname.setText(sdnamestr);
        sddesc.setText(sddescstr);
        sdloc.setText(sdlocstr);
    }
}