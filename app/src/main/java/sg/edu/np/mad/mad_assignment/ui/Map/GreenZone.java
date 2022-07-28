package sg.edu.np.mad.mad_assignment.ui.Map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import sg.edu.np.mad.mad_assignment.Block;
import sg.edu.np.mad.mad_assignment.BlockDetails;
import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.R;

public class GreenZone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_zone);
        DBHandler dbHandler = new DBHandler(GreenZone.this, null, null, 1);

        //Convention Center
        Button ConventionCenter = (Button) findViewById(R.id.ConventionCenter);
        ConventionCenter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Convention Center";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Interdisciplinary Sciences
        Button InterdisciplinarySciences  = (Button) findViewById(R.id.InterdisciplinarySciences);
        InterdisciplinarySciences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Interdisciplinary Sciences";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Infocomm Technology
        Button SchoolofInfocommTechnology  = (Button)findViewById(R.id.SchoolofInfocommTechnology);
        SchoolofInfocommTechnology.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Infocomm Technology";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Food Club
        Button FoodClub = (Button) findViewById(R.id.FoodClub);
        FoodClub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Food Club";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Aerospace Hub
        Button AerospaceHub = (Button) findViewById(R.id.AerospaceHub);
        AerospaceHub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Aerospace Hub";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Solar Tech Centre
        Button Solar_TechCentre = (Button)findViewById(R.id.SolarTechCentre);
        Solar_TechCentre.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Solar Tech Centre";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Engineering(West)
        Button SchoolofEngineeringWest  = (Button)findViewById(R.id.SchoolofEngineeringWest);
        SchoolofEngineeringWest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Engineering West";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });


        //School of Design and Environment
        Button SchoolofDesignandEnvironment  = (Button) findViewById(R.id.SchoolofDesignandEnvironment);
        SchoolofDesignandEnvironment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Design and Environment";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(GreenZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });
    }
}