package sg.edu.np.mad.mad_assignment.ui.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import sg.edu.np.mad.mad_assignment.Block;
import sg.edu.np.mad.mad_assignment.BlockDetails;
import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.MainActivity2;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.databinding.BlockDetailsBinding;
import sg.edu.np.mad.mad_assignment.databinding.FragmentMapBinding;

public class MapFragment extends Fragment implements View.OnClickListener {

    private FragmentMapBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapViewModel mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);
        DBHandler dbHandler = new DBHandler(getContext(), null, null, 1);
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.imageView2.setEnabled(false);

        View myView = inflater.inflate(R.layout.fragment_map, container, false);

        final Button goToSearch = binding.goToSearch;
        goToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getActivity(), MainActivity2.class);
                startActivity(searchIntent);
            }
        });

        // Admin Building
        Button AdminBuilding = binding.AdminBuilding;
        AdminBuilding.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Admin Block";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Dialogue In The Dark
        Button DialogueInTheDark = binding.DialogueInTheDark;
        DialogueInTheDark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Dialogue in the Dark";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Convention Center
        Button ConventionCenter = binding.ConventionCenter;
        ConventionCenter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Convention Center";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Aerospace Hub
        Button AerospaceHub = binding.AerospaceHub;
        AerospaceHub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Aerospace Hub";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Solar Tech Centre
        Button Solar_TechCentre = binding.SolarTechCentre;
        Solar_TechCentre.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Solar Tech Centre";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Film & Media Studies
        Button SchoolofFilmMediaStudies  = binding.SchoolofFilmMediaStudies;
        SchoolofFilmMediaStudies.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Film and Media Studies";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Infocomm Technology
        Button SchoolofInfocommTechnology  = binding.SchoolofInfocommTechnology;
        SchoolofInfocommTechnology.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Infocomm Technology";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Business & Accountancy
        Button SchoolofBusinessAccountancy  = binding.SchoolofBusinessAccountancy;
        SchoolofBusinessAccountancy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Business and Accountancy";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Engineering(West)
        Button SchoolofEngineeringWest  = binding.SchoolofEngineeringWest;
        SchoolofEngineeringWest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Engineering West";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Engineering(East)
        Button SchoolofEngineeringEast  = binding.SchoolofEngineeringEast;
        SchoolofEngineeringEast.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Engineering East";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Health Sciences
        Button SchoolofHealthSciences  = binding.SchoolofHealthSciences;
        SchoolofHealthSciences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Health Sciences";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Interdisciplinary Sciences
        Button InterdisciplinarySciences  = binding.InterdisciplinarySciences;
        InterdisciplinarySciences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Interdisciplinary Sciences";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Design and Environment
        Button SchoolofDesignandEnvironment  = binding.SchoolofDesignandEnvironment;
        SchoolofDesignandEnvironment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Design and Environment";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Humanities & Social Sciences
        Button SchoolofHumanitiesSocialSciences = binding.SchoolofHumanitiesSocialSciences;
        SchoolofHumanitiesSocialSciences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Humanities and Social Sciences";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //School of Life Sciences & Chemical Technology
        Button SchoolofLifeSciencesChemicalTechnology = binding.SchoolofLifeSciencesChemicalTechnology;
        SchoolofLifeSciencesChemicalTechnology.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "School of Life Sciences and Chemical Technology";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Makan Place
        Button MakanPlace = binding.MakanPlace;
        MakanPlace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Makan Place";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Food Club
        Button FoodClub = binding.FoodClub;
        FoodClub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Food Club";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //PoolSide
        Button PoolSide = binding.PoolSide;
        PoolSide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "PoolSide";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Munch
        Button Munch = binding.Munch;
        Munch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Munch";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Training Field
        Button TrainingField = binding.TrainingField;
        TrainingField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Training Field";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Sports Complex
        Button SportsComplex = binding.SportsComplex;
        SportsComplex.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Sports Complex";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        //Main Field
        Button MainField = binding.MainField;
        MainField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Map", "OnClick : " + ((Button)view).getText());
                String Buildingname = "Main Field";
                Block Building = dbHandler.findblock(Buildingname);
                Intent Search = new Intent(getActivity(), BlockDetails.class);
                Search.putExtra("block_info", Building);
                startActivity(Search);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

    }
}