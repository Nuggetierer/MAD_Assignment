package sg.edu.np.mad.mad_assignment.ui.Event;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.Block;
import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.Event;
import sg.edu.np.mad.mad_assignment.EventAdaptor;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.databinding.FragmentEventBinding;
import sg.edu.np.mad.mad_assignment.ui.Study.StudyDetailsPage;

public class EventFragment extends Fragment {

    String Ename, Eattend;

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

        final RecyclerView erecyclerView = binding.Erecyclerview;

        erecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        erecyclerView.setAdapter(new EventAdaptor(eventlist));

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
    private void getData() {
    if (getActivity().getIntent().hasExtra("Name") &&
            getActivity().getIntent().hasExtra("Attend")) {

        Ename = getActivity().getIntent().getStringExtra("Name");
        Eattend = getActivity().getIntent().getStringExtra("Attend");}
    else {
        Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    private void setdata (DBHandler dbHandler) {
        dbHandler.updateEvent(Ename, Eattend);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}