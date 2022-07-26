package sg.edu.np.mad.mad_assignment.ui.Study;

import android.content.Context;
import android.content.Intent;
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
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Event.EventFragment;


public class StudyAdaptor extends RecyclerView.Adapter<StudyViewHolder> {
    private String TAG = "My Adaptor";

    ArrayList<StudyPlaces> SList;
    Context context;

    public StudyAdaptor(ArrayList<StudyPlaces> input) {
        this.SList = input;
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

        holder.stxt1.setText(Sname);

        holder.viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.viewmore.getContext(), StudyDetailsPage.class);
                intent.putExtra("Name",Sname);
                intent.putExtra("Desc",Sdesc);
                intent.putExtra("Loc",Sloc);
                holder.viewmore.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return SList.size();
    }
}

