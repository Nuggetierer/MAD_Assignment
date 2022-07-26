package sg.edu.np.mad.mad_assignment.ui.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sg.edu.np.mad.mad_assignment.Block;
import sg.edu.np.mad.mad_assignment.BlockDetails;
import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.databinding.ActivityYellowZoneBinding;

public class YellowZone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_zone);
        DBHandler dbHandler = new DBHandler(YellowZone.this, null, null, 1);

        //Main Field
        Button MainField = (Button) findViewById(R.id.MainField);
        MainField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Main Field";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(YellowZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        // Admin Building
        Button AdminBuilding = (Button) findViewById(R.id.AdminBuilding);
        AdminBuilding.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Admin Block";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(YellowZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Engineering(East)
        Button SchoolofEngineeringEast  = (Button)findViewById(R.id.SchoolofEngineeringEast);
        SchoolofEngineeringEast.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Engineering East";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(YellowZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });


        //Dialogue In The Dark
        Button DialogueInTheDark = (Button)findViewById(R.id.DialogueInTheDark);
        DialogueInTheDark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Dialogue in the Dark";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(YellowZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //PoolSide
        Button PoolSide = (Button) findViewById(R.id.PoolSide);
        PoolSide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "PoolSide";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(YellowZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Sports Complex
        Button SportsComplex = (Button) findViewById(R.id.SportsComplex);
        SportsComplex.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Sports Complex";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(YellowZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });
    }
}