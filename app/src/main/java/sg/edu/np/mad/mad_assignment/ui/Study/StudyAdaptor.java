package sg.edu.np.mad.mad_assignment.ui.Study;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static sg.edu.np.mad.mad_assignment.myImageAdapter.getDrawable;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;


public class StudyAdaptor extends RecyclerView.Adapter<StudyViewHolder> implements Serializable {
    private String TAG = "My Adaptor";

    ArrayList<StudyPlaces> SList;

    public StudyAdaptor(ArrayList<StudyPlaces> input) {
        this.SList = input;
    }

    public boolean setItems(ArrayList<StudyPlaces> spl){
        int count = SList.size();
        int counter = 0;
        for(int i = 0; i < spl.size(); i++ ){
            Boolean exist = FALSE;
            StudyPlaces sp1 = spl.get(i);
            Log.d("sp1 ", ""+ sp1.getStudyName());

            for(int j = 0; j < SList.size(); j++ ){
                StudyPlaces sp2 = SList.get(j);
                Log.d("sp2 ","" + sp2.getStudyName());
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
        int pos = position;
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
        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(holder.options.getContext(), holder.options);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(item ->
                {
                    switch (item.getItemId()) {
                        case R.id.menu_edit:
                            Intent intent = new Intent(holder.options.getContext(), EditStudyLocation.class);
                            intent.putExtra("EDIT", (Serializable) sp);
                            holder.options.getContext().startActivity(intent);
                            break;

                        case R.id.menu_remove:
                            DAOStudyPlaces dao = new DAOStudyPlaces();
                            new AlertDialog.Builder(holder.options.getContext())
                                    .setTitle("Confirm Delete?")
                                    .setMessage("StudyPlaces will be permanently deleted\nand are not recoverable")
                                    .setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dao.remove(sp.getKey()).addOnSuccessListener(suc ->
                                            {
                                                Toast.makeText(holder.options.getContext(), "Record is removed", Toast.LENGTH_SHORT).show();
                                                SList.remove(pos);
                                                notifyItemRemoved(pos);
                                            }).addOnFailureListener(er ->
                                            {
                                                Toast.makeText(holder.options.getContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                                            });
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .create().show();
                            break;
                    }
                    return false;
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return SList.size();
    }
}

