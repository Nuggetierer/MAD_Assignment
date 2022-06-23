package sg.edu.np.mad.mad_assignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.ui.Event.EventFragment;


public class EventAdaptor extends RecyclerView.Adapter<EventResultViewHolder> {
    private String TAG = "My Adaptor";

    ArrayList<Event> EList;
//    private Context context;

    public EventAdaptor(ArrayList<Event> input) {
        this.EList = input;
    }
    @NonNull
    @Override
    public EventResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_fragmentlayout, parent, false);

        return new EventResultViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull EventResultViewHolder holder, int position) {
        //set data
        Event i = EList.get(position);
        String Edate = i.getEventDate();
        String Ename  = i.getEventName();
        String EDesc = i.getEventDescription();

        //using data to set text

        String Header = "Date: " + Edate + ", " +  Ename;

        holder.etxt1.setText(Header);
        holder.etxt2.setText(EDesc);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
