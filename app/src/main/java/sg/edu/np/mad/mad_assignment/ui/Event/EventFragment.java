package sg.edu.np.mad.mad_assignment.ui.Event;

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
//        for(int i = 0; i < 20; i++){
//            String Name = "Name";
//            String Description = "Description ";
//            String Date = "Aug 15";
//            String Type = "ICT";
//            UserList20.add(new Event(Date,Name,Description,Type));
//        }
        // Inflate the layout for this fragment
        DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);

        View view = inflater.inflate(R.layout.fragment_event, container, false);
//        add information using db
        ArrayList<Event> eventlist = dbHandler.retrieveEvent();

        recyclerView = view.findViewById(R.id.Erecyclerview);
        EventAdaptor eventAdaptor = new EventAdaptor(eventlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventAdaptor);


        return view;
    }


//        add information using db
//        ArrayList<Event> eventlist = dbHandler.retrieveEvent();


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


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}