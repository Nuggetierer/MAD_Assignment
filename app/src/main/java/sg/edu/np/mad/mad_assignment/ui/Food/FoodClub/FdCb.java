package sg.edu.np.mad.mad_assignment.ui.Food.FoodClub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.FoodCourt;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.myAdapter;

public class FdCb extends AppCompatActivity {

    RecyclerView stallRecycler;
    ArrayList<FoodCourt> stallList;
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

        stallRecycler = findViewById(R.id.stallRecycler);
        stallRecycler.setHasFixedSize(true);
        stallRecycler.setLayoutManager(new LinearLayoutManager(this));
        stallList = new ArrayList<>();

        //add information using db
        stallList = dbHandler.retrieveFoodCourt();

        fcdAdapter = new FCAdapter(stallList);
        stallRecycler.setAdapter(fcdAdapter);
    }
}