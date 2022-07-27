package sg.edu.np.mad.mad_assignment.ui.Food.Others;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Food.FoodClub.FCAdapter;
import sg.edu.np.mad.mad_assignment.ui.Food.FoodCourt;

public class OtherFood extends AppCompatActivity {

    RecyclerView OTHRecycler;
    ArrayList<FoodCourt> othList;
    OTHAdapter othAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_food);

        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        TextView Title = findViewById(R.id.othTitle);
        Title.setText("Others");

        FoodCourt fct = null;

        OTHRecycler = findViewById(R.id.othRecycler);
        OTHRecycler.setHasFixedSize(true);
        OTHRecycler.setLayoutManager(new LinearLayoutManager(this));
        othList = new ArrayList<>();

        //add information using db
        othList = dbHandler.retrieveFoodCourt();

        othAdapter = new OTHAdapter(othList);
        OTHRecycler.setAdapter(othAdapter);
    }
}