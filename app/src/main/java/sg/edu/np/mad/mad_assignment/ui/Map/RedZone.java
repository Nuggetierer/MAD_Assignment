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

public class RedZone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_zone);

        DBHandler dbHandler = new DBHandler(RedZone.this, null, null, 1);


        //School of Film & Media Studies
        Button SchoolofFilmMediaStudies  = (Button) findViewById(R.id.SchoolofFilmMediaStudies);
        SchoolofFilmMediaStudies.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Film and Media Studies";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Business & Accountancy
        Button SchoolofBusinessAccountancy  = (Button) findViewById(R.id.SchoolofBusinessAccountancy);
        SchoolofBusinessAccountancy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Business and Accountancy";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Health Sciences
        Button SchoolofHealthSciences  = (Button) findViewById(R.id.SchoolofHealthSciences);
        SchoolofHealthSciences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Health Sciences";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Humanities & Social Sciences
        Button SchoolofHumanitiesSocialSciences =(Button) findViewById(R.id.SchoolofHumanitiesSocialSciences);
        SchoolofHumanitiesSocialSciences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Humanities and Social Sciences";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Life Sciences & Chemical Technology
        Button SchoolofLifeSciencesChemicalTechnology = (Button) findViewById(R.id.SchoolofLifeSciencesChemicalTechnology);
        SchoolofLifeSciencesChemicalTechnology.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Life Sciences and Chemical Technology";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });


        //Makan Place
        Button MakanPlace = (Button) findViewById(R.id.MakanPlace);
        MakanPlace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Makan Place";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Munch
        Button Munch = (Button) findViewById(R.id.Munch);
        Munch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Munch";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Training Field
        Button TrainingField = (Button) findViewById(R.id.TrainingField);
        TrainingField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Training Field";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(RedZone.this, BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });
    }
}