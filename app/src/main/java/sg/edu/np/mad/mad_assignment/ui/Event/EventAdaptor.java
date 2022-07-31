package sg.edu.np.mad.mad_assignment.ui.Event;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Study.DAOStudyPlaces;


public class EventAdaptor extends RecyclerView.Adapter<EventResultViewHolder> {
    private String TAG = "My Adaptor";

    private Context context;
    ArrayList<Event> EList;
    ArrayList<Event> EList2;
    DBHandler dbhandler;
//    private Context context;

//    public EventAdaptor(ArrayList<Event> input,DBHandler dbHandler) {
//        this.EList = input;
//        this.dbhandler = dbHandler;
//    }

    public EventAdaptor(ArrayList<Event> input, Context ctx) {
        this.EList = input;
        this.context = ctx;
    }
    public Boolean setItems(ArrayList<Event> e){
        int count = EList.size();
        int counter = 0;
        for(int i = 0; i < e.size(); i++ ){
            Boolean exist = FALSE;
            Event sp1 = e.get(i);
            Log.d("E1 ", ""+ sp1.getEventName());

            for(int j = 0; j < EList.size(); j++ ){
                Event sp2 = EList.get(j);
                Log.d("E2 ","" + sp2.getEventName());
                if(sp1.getEventName() == sp2.getEventName()){
                    exist = TRUE;
                    counter += 1;
                }
            }
            if(exist != TRUE) {
                Log.d("E3 ","" + sp1.getKey());
                EList.add(sp1);
            }
        }
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
        Event i = EList.get(position);
        String Edate = i.getEventDate();
        String Ename  = i.getEventName();
        String EDesc = i.getEventDescription();
        DAOStudyPlaces dao = new DAOStudyPlaces();

        //using data to set text
        String Eattend = i.getAttend();
//       Eattend = !Eattend;
        String Header = "Date: " + Edate + ", " +  Ename;

        holder.etxt1.setText(Header);
        holder.etxt2.setText(EDesc);
        holder.attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Eattend = i.getAttend();
                if (Eattend == "0"){
                    holder.attend.setText("Attending");
                    Toast.makeText(view.getContext(), "Attending",Toast.LENGTH_SHORT).show();
                    i.setAttend("1");
                    holder.image.setImageResource(R.drawable.ic_cross_x_foreground);

//                    Intent intent = new Intent(holder.attend.getContext(), Updateeventactivity.class);
//                    intent.putExtra("Name",Ename);
//                    intent.putExtra("Attend","1");
//                    holder.attend.getContext().startActivity(intent);
//                    dbhandler.updateEvent(Ename, "1");

                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("attend","1");
                    dao.updateAttende(i.getKey(),hashMap);
                    Log.d("Mytag0", i.getAttend() + Ename);
                }
                else {
                    holder.attend.setText("Cancel");
                    Toast.makeText(view.getContext(), "Not attending",Toast.LENGTH_SHORT).show();
                    i.setAttend("0");
                    holder.image.setImageResource(R.drawable.ic_check_y_foreground);
//                    Intent intent = new Intent(holder.attend.getContext(), Updateeventactivity.class);
//                    intent.putExtra("Name",Ename);
//                    intent.putExtra("Attend","0");
//                    holder.attend.getContext().startActivity(intent);
//                    dbhandler.updateEvent(Ename, "0");

                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("attend","0");
                    dao.updateAttende(i.getKey(),hashMap);
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
