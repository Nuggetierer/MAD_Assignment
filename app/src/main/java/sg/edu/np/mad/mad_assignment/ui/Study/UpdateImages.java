package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

import sg.edu.np.mad.mad_assignment.R;

public class UpdateImages extends AppCompatActivity {

    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    UpdateImageAdapter myEiAdapter;
    public static String Eikey = "";
    private ArrayList<Images> images = new ArrayList<>();
    private String Newuri = "";
    private DAOStudyPlaces Dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_images);

        rv = findViewById(R.id.EditImagehorizontalrv);
        Button addimage = findViewById(R.id.AddImagebutton);

        Dao = new DAOStudyPlaces();

        getData();
        loadData();

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        myEiAdapter = new UpdateImageAdapter(images, this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myEiAdapter);


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addintent = new Intent(UpdateImages.this, ImportImages.class);
                startActivityForResult(addintent, 2);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Newuri = data.getStringExtra("Uri");

            Dao.addImages(Eikey ,new Images(Newuri));
            myEiAdapter.notifyDataSetChanged();
        }
    }

    private void loadData()
    {
        Dao.getimage(Eikey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                images.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    Images i =  data.getValue(Images.class);
                    i.setKey(data.getKey());
//                    String uri1 =  data.getValue(String.class);
                    Log.d("Uri1", "" + i.getUri());
                    images.add(i);
                }
                Log.d("UpdateImages","size" + images.size());
                myEiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getData() {
        if (getIntent().hasExtra("Key") ) {

            Eikey = getIntent().getStringExtra("Key");

        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}