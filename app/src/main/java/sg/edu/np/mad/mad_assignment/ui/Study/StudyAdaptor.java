package sg.edu.np.mad.mad_assignment.ui.Study;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static sg.edu.np.mad.mad_assignment.myImageAdapter.getDrawable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.Event;
import sg.edu.np.mad.mad_assignment.EventResultViewHolder;
import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Event.EventFragment;


public class StudyAdaptor extends RecyclerView.Adapter<StudyViewHolder> {
    private String TAG = "My Adaptor";

    ArrayList<StudyPlaces> SList;
    Context context;

    public StudyAdaptor(ArrayList<StudyPlaces> input) {
        this.SList = input;
    }

    public boolean setItems(ArrayList<StudyPlaces> sp){
        int count = SList.size();
        int counter = 0;
        for(int i = 0; i < sp.size(); i++ ){
            Boolean exist = FALSE;
            StudyPlaces sp1 = sp.get(i);
            Log.d("sp1 ", sp1.getStudyName());

            for(int j = 0; j < SList.size(); j++ ){
                StudyPlaces sp2 = SList.get(j);
                Log.d("sp2 ", sp2.getStudyName());
                if(sp1.getStudyName() == sp2.getStudyName()){
                    exist = TRUE;
                    counter += 1;
                }
            }
            if(exist != TRUE) {
                SList.add(sp1);
            }
        }
        return(count == counter);
    }
    @NonNull
    @Override
    public StudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_layout, parent, false);

        return new StudyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull StudyViewHolder holder, int position) {
        //set data
        StudyPlaces sp = SList.get(position);
        String Sname = sp.getStudyName();
        String Sdesc  = sp.getStudyDescription();
        String Sloc = sp.getStudyLocation();

        String name = Sname.replace(" ", "_").toLowerCase();
        String parse_img_name = name + "_map";

        int drawable = getDrawable( holder.simage.getContext(), parse_img_name);
        holder.simage.setImageURI(Uri.parse("android.resource://" + MainActivity.PACKAGENAME + "/" + drawable));

        holder.stxt1.setText(Sname);

        holder.viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.viewmore.getContext(), StudyDetailsPage.class);
                intent.putExtra("Name",Sname);
                intent.putExtra("Desc",Sdesc);
                intent.putExtra("Loc",Sloc);
                intent.putExtra("Draw", drawable);
                holder.viewmore.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return SList.size();
    }
}

