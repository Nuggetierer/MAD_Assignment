package sg.edu.np.mad.mad_assignment.ui.Event;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Study.DAOStudyPlaces;


public class EventAdaptor extends RecyclerView.Adapter<EventResultViewHolder> {
    private String TAG = "My Adaptor";

    private Context context;
    private FirebaseUser mAuth;
    private String currentUserID;
    ArrayList<Event> EList =  new ArrayList<>();
    ArrayList<Event> EList2;
    DBHandler dbhandler;
//    private Context context;

//    public EventAdaptor(ArrayList<Event> input,DBHandler dbHandler) {
//        this.EList = input;
//        this.dbhandler = dbHandler;
//    }

    public EventAdaptor(Context ctx) {
//        this.EList = input;
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth != null) {
            currentUserID = mAuth.getUid();
            Log.d("UidAdaptor","Uid: " + currentUserID);
        }

        this.context = ctx;
    }
    public Boolean setItems(ArrayList<Event> e){
        int count = e.size();
        int counter = 0;
        for(int i = 0; i < e.size(); i++ ){
            Boolean exist = FALSE;
            Event sp1 = e.get(i);
            Log.d("E1 ", ""+ sp1.getEventName());

            for(int j = 0; j < EList.size(); j++ ){
                Event sp2 = EList.get(j);
                Log.d("E2 ","" + sp2.getEventName());
                if(sp1.getKey().equals(sp2.getKey())){
                    exist = TRUE;
                    break;
                }
            }
            if(exist != TRUE) {
                Log.d("E3 ","" + sp1.getKey() + " Name: " + sp1.getEventName() + " " + sp1.getAttend());
                EList.add(sp1);
            }
            else{
                counter += 1;
            }
        }
        Log.d("Count  ","count: " + count + " counter: " + counter);
        return(count == counter);
//       EList.addAll(e);
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
        int pos = position;
        int s = 1000;

        Event i = EList.get(position);
        String Edate = i.getEventDate();
        String Ename  = i.getEventName();
        String EDesc = i.getEventDescription();
        DAOStudyPlaces dao = new DAOStudyPlaces();
        DAOEvent daoE = new DAOEvent();

        if(i.getAttend() == null){
            i.setAttend("1");
        }

        String Eattend = i.getAttend();
        String Header = "Date: " + Edate + ", " +  Ename;

        holder.etxt1.setText(Header);
        holder.etxt2.setText(EDesc);
        if(Eattend.equals("0")){
            holder.attend.setText("Cancel");
            holder.image.setImageResource(R.drawable.ic_check_y_foreground);
        }
        else
        {
            holder.attend.setText("Attending");
            holder.image.setImageResource(R.drawable.ic_cross_x_foreground);
        }

        holder.attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String Eattend = i.getAttend();
                Log.d("EventAdap", "Attend? "+i.getAttend());

                if (Eattend.equals("0")){
                    holder.attend.setText("Attending");

                    Toast toast = Toast.makeText(view.getContext(), "Attending",Toast.LENGTH_SHORT);
                    toast.setDuration(s);
                    toast.show();

                    i.setAttend("1");
                    holder.image.setImageResource(R.drawable.ic_cross_x_foreground);

                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("attend","1");
                    Log.d("UID Before update", " " + currentUserID);
                    if(currentUserID != null){
                        daoE.updateAttendeUser(i.getKey(),hashMap);
                    }
                    else{
                        dao.updateAttende(i.getKey(),hashMap);
                    }

                    notifyDataSetChanged();
                    notifyItemChanged(pos);
                    Log.d("Mytag0", i.getAttend() + Ename);
                }
                else {
                    holder.attend.setText("Cancel");
                    Toast toast = Toast.makeText(view.getContext(), "Not attending",Toast.LENGTH_SHORT);
                    toast.setDuration(s);
                    toast.show();

                    i.setAttend("0");
                    holder.image.setImageResource(R.drawable.ic_check_y_foreground);
//                    Intent intent = new Intent(holder.attend.getContext(), Updateeventactivity.class);
//                    intent.putExtra("Name",Ename);
//                    intent.putExtra("Attend","0");
//                    holder.attend.getContext().startActivity(intent);
//                    dbhandler.updateEvent(Ename, "0");

                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("attend","0");
                    Log.d("UID Before update", " " + currentUserID);
                    if(currentUserID != null){
                        daoE.updateAttendeUser(i.getKey(),hashMap);
                    }
                    else{
                        dao.updateAttende(i.getKey(),hashMap);
                    }

//                    Intent intent = new Intent(holder.attend.getContext(), Updateeventactivity.class);
//                    intent.putExtra("update","1");
//                    holder.attend.getContext().startActivity(intent);
                    notifyDataSetChanged();
                    notifyItemChanged(pos );
                    Log.d("Mytag1", i.getAttend() + Ename);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
            return EList.size();
    }
}
