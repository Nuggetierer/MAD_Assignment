package sg.edu.np.mad.mad_assignment.ui.Study;

import static java.lang.Boolean.FALSE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.databinding.FragmentStudyBinding;

public class StudyFragment extends Fragment implements Serializable {

    private FragmentStudyBinding binding;
    DatabaseReference databaseReference;
    DAOStudyPlaces daosd;
    StudyAdaptor studyAdaptor;
    String Key;

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


        studyAdaptor =  new StudyAdaptor(Studylist);
        studyRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        studyRecyclerview.setAdapter(studyAdaptor);

        daosd = new DAOStudyPlaces();
        loadData();

        final FloatingActionButton newstudy = binding.StudyfloatingActionButton;
        newstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentaddstudy = new Intent(getContext(), AddStudyLocation.class);
                startActivity(intentaddstudy);
            }
        });

        return root;
    }
    private void loadData()
    {
        daosd.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<StudyPlaces> spal = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    StudyPlaces sp =  data.getValue(StudyPlaces.class);
                    sp.setKey(data.getKey());
                    spal.add(sp);
                    Key = data.getKey();
                }
                if( spal != null){
                    boolean check = studyAdaptor.setItems(spal);
                    if(check == FALSE){
                        studyAdaptor.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}