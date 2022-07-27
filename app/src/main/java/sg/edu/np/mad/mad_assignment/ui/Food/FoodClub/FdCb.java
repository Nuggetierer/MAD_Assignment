package sg.edu.np.mad.mad_assignment.ui.Food.FoodClub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.ui.Food.FoodCourt;
import sg.edu.np.mad.mad_assignment.R;

public class FdCb extends AppCompatActivity {

    RecyclerView FCRecycler;
    ArrayList<FoodCourt> FCList;
    FCAdapter fcdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd_cb);

        //refresh database for use in this activity
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        TextView title = findViewById(R.id.FCtitle);
        title.setText("FOOD CLUB");

        TextView loca = findViewById(R.id.FCloca);
        loca.setText("Located at Block 22");

        FCRecycler = findViewById(R.id.FCRecycler);
        FCRecycler.setHasFixedSize(true);
        FCRecycler.setLayoutManager(new LinearLayoutManager(this));
        FCList = new ArrayList<>();

        //add information using db
        FCList = dbHandler.retrieveFoodCourt();

        fcdAdapter = new FCAdapter(FCList);
        FCRecycler.setAdapter(fcdAdapter);
    }
}