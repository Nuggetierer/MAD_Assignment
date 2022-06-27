package sg.edu.np.mad.mad_assignment.ui.Event;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EventFragment extends Fragment {

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

//        View view = inflater.inflate(R.layout.fragment_event, container, false);
//        add information using db
        ArrayList<Event> eventlist = dbHandler.retrieveEvent();

        final RecyclerView erecyclerView = binding.Erecyclerview;

        erecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        erecyclerView.setAdapter(new EventAdaptor(eventlist));

//        recyclerView = view.findViewById(R.id.Erecyclerview);
//        EventAdaptor eventAdaptor = new EventAdaptor(eventlist);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(eventAdaptor);

        final FloatingActionButton newevent = binding.EventfloatingActionButton;
        newevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("This is a work in progress...")
                        .setMessage("The events will only be allowed to be added by verified user suchs as CCA or ClUB exco teams.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
        return binding.getRoot();
    }


//        add information using db
//        ArrayList<Event> eventlist = dbHandler.retrieveEvent();

//        for(int i = 0; i < 20; i++){
//            String Name = "Name";
//            String Description = "Description ";
//            String Date = "Aug 15";
//            String Type = "ICT";
//            UserList20.add(new Event(Date,Name,Description,Type));
//        }
    // Inflate the layout for this fragment

//    View root = binding.getRoot();

//        final RecyclerView erecyclerView = binding.ErecyclerView;
//
//        erecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        erecyclerView.setAdapter(new EventAdaptor(getContext(), UserList20));

//        View view = inflater.inflate(R.layout.fragment_event, container, false);
//         Add the following lines to create RecyclerView
//        RecyclerView ErecyclerView = view.findViewById(R.id.ErecyclerView);
//        EventAdaptor eadaptor = new EventAdaptor(Eventlist);
//        ErecyclerView.setHasFixedSize(true);
//        ErecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        ErecyclerView.setAdapter(eadaptor);

//        final TextView textView = binding.textEvent;
//        eventViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
/*Button newEvent = view.findViewById(R.id.EventfloatingActionButton);
        newEvent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new AlertDialog.Builder(getContext())
                    .setTitle("This is a work in progress...")
                    .setMessage("The events will only be allowed to be added by verified user suchs as CCA or ClUB exco teams.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
    });*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}