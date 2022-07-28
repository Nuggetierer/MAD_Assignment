package sg.edu.np.mad.mad_assignment.ui.Event;

import static android.content.ContentValues.TAG;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.databinding.FragmentEventBinding;
import sg.edu.np.mad.mad_assignment.ui.Study.DAOStudyPlaces;
import sg.edu.np.mad.mad_assignment.ui.Study.StudyPlaces;

public class EventFragment extends Fragment {

    String Ename, Eattend;
    DAOStudyPlaces dao;
    String Key;
    private FirebaseAuth mAuth;
    private FirebaseUser FirebaseUser;
    String currentUserID;
    EventAdaptor adapter;

    private FragmentEventBinding binding;
    RecyclerView recyclerView;
    ArrayList<Event> UserList20 = new ArrayList<>();

//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        EventViewModel eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
//        binding = FragmentEventBinding.inflate(inflater, container, false);
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventViewModel eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        binding = FragmentEventBinding.inflate(inflater, container, false);
        DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);

//        getData();
//        setdata(dbHandler);

        ArrayList<Event> eventlist = dbHandler.retrieveEvent();

        ArrayList<Event> eventlist1 = new ArrayList<Event>();

        final RecyclerView erecyclerView = binding.Erecyclerview;

        dao = new DAOStudyPlaces();
        if (dao == null) {
            for (int i = 0; i < eventlist.size(); i++) {
                dao.adde(eventlist.get(i));
            }
        }
        loadData();

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            currentUserID = mFirebaseUser.getUid();
        }

//        adapter = new EventAdaptor(eventlist, dbHandler);
        adapter = new EventAdaptor(eventlist1, getContext());

        erecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        erecyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();

        final FloatingActionButton newevent = binding.EventfloatingActionButton;
        newevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsendemail = new Intent(getContext(), SendEmail.class);
                startActivity(intentsendemail);
            }
        });
        return binding.getRoot();
    }

    //                new AlertDialog.Builder(getContext())
//                        .setTitle("This is a work in progress...")
//                        .setMessage("The events will only be allowed to be added by verified user such as CCA or SIGS exco teams.")
//                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .create().show();
    private void loadData() {
        dao.gete().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Event> events = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Event event = data.getValue(Event.class);
                    event.setKey(data.getKey());
                    events.add(event);
                    Key = data.getKey();
                }
                if( events != null){
                    boolean check = adapter.setItems(events);
                    if(check == TRUE){
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    private void getData() {
        if (getActivity().getIntent().hasExtra("Name") &&
                getActivity().getIntent().hasExtra("Attend")) {

            Ename = getActivity().getIntent().getStringExtra("Name");
            Eattend = getActivity().getIntent().getStringExtra("Attend");
        } else {
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setdata(DBHandler dbHandler) {
        dbHandler.updateEvent(Ename, Eattend);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}