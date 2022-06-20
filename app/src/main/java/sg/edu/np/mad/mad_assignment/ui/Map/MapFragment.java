package sg.edu.np.mad.mad_assignment.ui.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.databinding.FragmentMapBinding;

public class MapFragment extends Fragment implements View.OnClickListener {

    private FragmentMapBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapViewModel mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.TextMap;

        mapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        View myView = inflater.inflate(R.layout.fragment_map, container, false);

        // The place where button is placed
        // Convection Centre
        Button ConvectionCentre = (Button) myView.findViewById(R.id.ConvectionCentre);
        ConvectionCentre.setOnClickListener(this);

        // Training Field
        Button TrainingFields = (Button) myView.findViewById(R.id.TrainingFields);
        TrainingFields.setOnClickListener(this);

        // AdminBuilding
        Button AdminBuilding = (Button) myView.findViewById(R.id.Admin_Building);
        AdminBuilding.setOnClickListener(this);

        //Dialogue In The Dark
        Button DialogueInTheDark = (Button) myView.findViewById(R.id.DialogueInTheDark);
        DialogueInTheDark.setOnClickListener(this);

        //Aerospace Hub
        Button AerospaceHub = (Button) myView.findViewById(R.id.AerospaceHub);
        AerospaceHub.setOnClickListener(this);

        //Solar Tech Centre
        Button SolarTechCentre = (Button) myView.findViewById(R.id.SolarTechCentre);
        SolarTechCentre.setOnClickListener(this);

        //School of Film & Media Studies
        Button SchoolFilmMediaStudies = (Button) myView.findViewById(R.id.SchoolofFilmMediaStudies);
        SchoolFilmMediaStudies.setOnClickListener(this);

        //School of Infocomm Technology
        Button SchoolofInfocommTechnology = (Button) myView.findViewById(R.id.SchoolofICT);
        SchoolofInfocommTechnology.setOnClickListener(this);

        //School of Business & Accountancy
        Button SchoolofBusinessAccountancy = (Button) myView.findViewById(R.id.SchoolofBA);
        SchoolofBusinessAccountancy.setOnClickListener(this);

        //School of Engineering(West)
        Button SchoolofEngineeringWest = (Button) myView.findViewById(R.id.SchoolofEngineeringWest);
        SchoolofEngineeringWest.setOnClickListener(this);

        //School of Engineering(East)
        Button SchoolofEngineeringEast = (Button) myView.findViewById(R.id.SchoolofEngineeringEast);
        SchoolofEngineeringEast.setOnClickListener(this);

        //School of Health Sciences
        Button SchoolofHealthSciences = (Button) myView.findViewById(R.id.SchoolofHealthSciences);
        SchoolofHealthSciences.setOnClickListener(this);

        //Interdisciplinary Sciences
        Button InterdisciplinarySciences = (Button) myView.findViewById(R.id.InterdisciplinarySciences);
        InterdisciplinarySciences.setOnClickListener(this);

        //School of Design and Environment
        Button SchoolofDesignandEnvironment = (Button) myView.findViewById(R.id.SchoolofDesignandEnvironment);
        SchoolofDesignandEnvironment.setOnClickListener(this);

        //School of Humanities & Social Sciences
        Button SchoolofHumanitiesSocialSciences = (Button) myView.findViewById(R.id.SchoolofHumanitiesSocialSciences);
        SchoolofHumanitiesSocialSciences.setOnClickListener(this);

        //School of Life Sciences & Chemical Technology
        Button SchoolofLifeSciencesChemicalTechnology = (Button) myView.findViewById(R.id.SchoolofLifeSciencesChemicalTechnology);
        SchoolofLifeSciencesChemicalTechnology.setOnClickListener(this);

        //Makan Place
        Button MakanPlace = (Button) myView.findViewById(R.id.MakanPlace);
        MakanPlace.setOnClickListener(this);

        //Food Place
        Button FoodPlace = (Button) myView.findViewById(R.id.FoodPlace);
        FoodPlace.setOnClickListener(this);

        //PoolSide
        Button PoolSide = (Button) myView.findViewById(R.id.PoolSide);
        PoolSide.setOnClickListener(this);

        //Munch
        Button Munch = (Button) myView.findViewById(R.id.Munch);
        Munch.setOnClickListener(this);

        //Sports Complex
        Button SportsComplex = (Button) myView.findViewById(R.id.SportsComplex);
        SportsComplex.setOnClickListener(this);

        //Main Field
        Button MainField = (Button) myView.findViewById(R.id.MainField);
        MainField.setOnClickListener(this);

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