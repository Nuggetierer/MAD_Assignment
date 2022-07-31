package sg.edu.np.mad.mad_assignment.ui.Study;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;

public class StudyDetailsPage extends AppCompatActivity implements Serializable {
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    StudyDetailsAdapter mysdAdapter;
    TextView sdname, sddesc, sdloc;
    String sdnamestr, sddescstr, sdlocstr, sdkey;
    Integer sdimgstr;
    ImageView sdimg;
    private ArrayList images = new ArrayList<>();
    private DAOStudyPlaces Dao;
    String Key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_details_page);

        sdname = findViewById(R.id.StudyDetailsTop);
        sdloc = findViewById(R.id.StudyDetailsBottom);
        sddesc = findViewById(R.id.StudyDetailsDescription);
        sdimg = findViewById(R.id.SdImageLayout);

        getData();
        setdata();

        Dao = new DAOStudyPlaces();

        loadData();
        //Setting the data source

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mysdAdapter = new StudyDetailsAdapter(images, this);
        rv = findViewById(R.id.StudyDetailshorizontalRv);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(mysdAdapter);
    }

    private void getData() {
            if (getIntent().hasExtra("Name") && getIntent().hasExtra("Desc")
                && getIntent().hasExtra("Loc") && getIntent().hasExtra("Draw")) {

            sdnamestr = getIntent().getStringExtra("Name");
            sddescstr = getIntent().getStringExtra("Desc");
            sdlocstr = getIntent().getStringExtra("Loc");
            sdimgstr = getIntent().getIntExtra("Draw",0);
            sdkey = getIntent().getStringExtra("Key");

        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    private void setdata () {
        sdname.setText(sdnamestr);
        sddesc.setText(sddescstr);
        sdloc.setText(sdlocstr);
    }

    private void loadData()
    {
        Dao.getimage(sdkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot data : snapshot.getChildren()){
                    String uri1 =  data.getValue(String.class);
                    Log.d("Uri1", "" + uri1);
                    images.add(uri1);
                }
                mysdAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}