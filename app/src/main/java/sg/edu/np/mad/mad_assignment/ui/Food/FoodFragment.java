package sg.edu.np.mad.mad_assignment.ui.Food;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.databinding.FragmentFoodBinding;
import sg.edu.np.mad.mad_assignment.ui.Food.FoodClub.FdCb;

public class FoodFragment extends Fragment {

    private FragmentFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button FC = root.findViewById(R.id.foodClub);
        FC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getActivity(), FdCb.class);
                startActivity(searchIntent);
            }
        });

        Button MKP = root.findViewById(R.id.mknPlc);
        MKP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getActivity(), FdCb.class);
                startActivity(searchIntent);
            }
        });

        Button OTH = root.findViewById(R.id.othrs);
        OTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getActivity(), FdCb.class);
                startActivity(searchIntent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}