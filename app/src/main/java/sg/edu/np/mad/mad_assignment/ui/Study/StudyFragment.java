package sg.edu.np.mad.mad_assignment.ui.Study;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.Event;
import sg.edu.np.mad.mad_assignment.EventAdaptor;
import sg.edu.np.mad.mad_assignment.databinding.FragmentStudyBinding;

public class StudyFragment extends Fragment {

    private FragmentStudyBinding binding;
    DatabaseReference databaseReference;
    public String TAG = "Main Acitivty: ";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StudyViewModel studyViewModel =
                new ViewModelProvider(this).get(StudyViewModel.class);

        binding = FragmentStudyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);

        ArrayList<StudyPlaces> Studylist = dbHandler.retrieveStudy();

        final RecyclerView studyRecyclerview = binding.StudyRecyclerview;

        studyRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        studyRecyclerview.setAdapter(new StudyAdaptor(Studylist));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}