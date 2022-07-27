package sg.edu.np.mad.mad_assignment.ui.Map;

import android.content.Intent;
import android.os.Build;
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

        binding.imageView.setEnabled(false);

        View myView = inflater.inflate(R.layout.fragment_map, container, false);

        final Button goToSearch = binding.goToSearch;
        goToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getActivity(), MainActivity2.class);
                startActivity(searchIntent);
            }
        });

        final Button Directions = binding.Directions;
        Directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Map_Google.class);
                startActivity(intent);
            }
        });

        final Button RedZone = binding.RedZonebutton;
        RedZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RedZone.class);
                startActivity(intent);
            }
        });

        final Button YellowZone = binding.YellowZonebutton;
        YellowZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), YellowZone.class);
                startActivity(intent);
            }
        });

        final Button GreenZone = binding.GreenZonebutton;
        GreenZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GreenZone.class);
                startActivity(intent);
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