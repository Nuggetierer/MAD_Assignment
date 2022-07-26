package sg.edu.np.mad.mad_assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.ui.Event.EventFragment;


public class EventAdaptor extends RecyclerView.Adapter<EventResultViewHolder> {
    private String TAG = "My Adaptor";

    ArrayList<Event> EList;
    ArrayList<Event> EList2;
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
        if(i.getAttend() == null){
            i.setAttend(Boolean.TRUE);
        }
//        Boolean ebool = i.getAttend();
//        String Etype = i.getEventType();
//        EList2.add(new Event(Edate,Ename,EDesc,Etype,ebool));
        //using data to set text
        Boolean Eattend = i.getAttend();
//       Eattend = !Eattend;
        String Header = "Date: " + Edate + ", " +  Ename;

        holder.etxt1.setText(Header);
        holder.etxt2.setText(EDesc);
        holder.attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean Eattend = i.getAttend();
                if (Eattend == Boolean.FALSE){
                    holder.attend.setText("Attending");
                    Toast.makeText(view.getContext(), "Attending",Toast.LENGTH_SHORT).show();
                    i.setAttend(Boolean.TRUE);
                    holder.image.setImageResource(R.drawable.ic_cross_x_foreground);
                }
                else {
                    holder.attend.setText("Cancel");
                    Toast.makeText(view.getContext(), "Not attending",Toast.LENGTH_SHORT).show();
                    i.setAttend(Boolean.FALSE);
                    holder.image.setImageResource(R.drawable.ic_check_y_foreground);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return EList.size();
    }
}
