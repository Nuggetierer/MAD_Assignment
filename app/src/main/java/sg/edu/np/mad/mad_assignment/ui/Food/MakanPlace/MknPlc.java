package sg.edu.np.mad.mad_assignment.ui.Food.MakanPlace;

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

public class MknPlc extends AppCompatActivity {

    RecyclerView MKNRecycler;
    ArrayList<FoodCourt> MKNList;
    MKNAdapter mknAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mkn_plc);

        //refresh database for use in this activity
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        TextView title = findViewById(R.id.FCtitle);
        title.setText("MAKAN PLACE");

        TextView loca = findViewById(R.id.FCloca);
        loca.setText("Located at Block 51");

        MKNRecycler = findViewById(R.id.MknRecycler);
        MKNRecycler.setHasFixedSize(true);
        MKNRecycler.setLayoutManager(new LinearLayoutManager(this));
        MKNList = new ArrayList<>();

        //add information using db
        MKNList = dbHandler.retrieveFoodCourt();

        mknAdapter = new MKNAdapter(MKNList);
        MKNRecycler.setAdapter(mknAdapter);
    }

}