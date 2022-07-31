package sg.edu.np.mad.mad_assignment.ui.Study;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static sg.edu.np.mad.mad_assignment.myImageAdapter.getDrawable;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;


public class StudyAdaptor extends RecyclerView.Adapter<StudyViewHolder> implements Serializable {
    private String TAG = "My Adaptor";

    ArrayList<StudyPlaces> SList;
    ArrayList<StudyPlaces> SListcheck;
    private String Uri1 = "";
    Context context;

    public StudyAdaptor(ArrayList<StudyPlaces> input, Context contex) {
        this.SList = input;
        this.context = contex;
    }

    public boolean setItems(ArrayList<StudyPlaces> spl){
        int count = spl.size();
        int counter = 0;
        for(int i = 0; i < spl.size(); i++ ){
            Boolean exist = FALSE;
            StudyPlaces sp1 = spl.get(i);
            Log.d("sp1 ", ""+ sp1.getKey() + " " + sp1.getStudyName());

            for(int j = 0; j < SList.size(); j++ ){
                StudyPlaces sp2 = SList.get(j);
                Log.d("sp2 ","" + sp2.getKey()+ " " + sp2.getStudyName() );

                String sp1key = sp1.getKey();
                String sp2key = sp2.getKey();

                if(sp1key.equals(sp2key)){
                    exist = TRUE;
                    Log.d("spTest ","found");
                    if(!sp1.getStudyName().equals(sp2.getStudyName()) || !sp1.getStudyLocation().equals(sp2.getStudyLocation()) ||
                            !sp1.getStudyDescription().equals(sp2.getStudyDescription())){
                        Uri1 = "";
                        SList.set(j, sp1 );
                        notifyItemChanged(i);
                        Log.d("Update List ", i + " " + j + " size: " + SList.size());
                    }
                    break;
                }
            }
            if(exist != TRUE) {
                SList.add(sp1);
            }
            else{
                counter += 1;
            }
        }
//        replace(spl);
        Log.d("spCount  ","count: " + count + " counter: " + counter + " Size: " + SList.size());
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

//        int drawable = getDrawable( holder.simage.getContext(), parse_img_name);
//        holder.simage.setImageURI(Uri.parse("android.resource://" + MainActivity.PACKAGENAME + "/" + drawable));

        if(Uri1.equals("")){
            DAOStudyPlaces daosd = new DAOStudyPlaces();

            daosd.getimage(sp.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot data : snapshot.getChildren()){
//                        holder.simage.setImageURI(Uri.parse(Uri1));
                        Images i =  data.getValue(Images.class);
                        i.setKey(data.getKey());
                        Log.d("SAUri1", "" + i.getUri() +" " + Sname);
                        Uri1 = i.getUri();
                        sp.setUri(Uri1);
                        Glide.with(context).load(i.getUri()).into(holder.simage);
                        break;
                    }
                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            Glide.with(context).load(sp.getUri()).into(holder.simage);
        }

        holder.stxt1.setText(Sname);

        holder.viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.viewmore.getContext(), StudyDetailsPage.class);
                intent.putExtra("Name",Sname);
                intent.putExtra("Desc",Sdesc);
                intent.putExtra("Loc",Sloc);
                intent.putExtra("Key", sp.getKey());
                holder.viewmore.getContext().startActivity(intent);
            }
        });
        holder.options.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                if(!Sname.equals("Atrium") && !Sname.equals("StudyLounge 22")) {

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
                                        .setMessage("The StudyPlace will be permanently deleted\nand is not recoverable")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dao.remove(sp.getKey()).addOnSuccessListener(suc ->
                                                {
                                                    Toast.makeText(holder.options.getContext(), "Record is removed", Toast.LENGTH_SHORT).show();
                                                    SList.remove(pos);
                                                    notifyItemRemoved(pos);
                                                    notifyDataSetChanged();
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
//                else{
//                    Toast.makeText(holder.options.getContext(), "Admin Generated Data, not editable or removable", Toast.LENGTH_SHORT).show();
//                }
        });
        Log.d("spSize  ","size: " + SList.size());
    }
     public void replace(ArrayList<StudyPlaces> sp ){

         for(int i = 0; i < sp.size(); i++ ){
             StudyPlaces sp1 = sp.get(i);
             Log.d("InitialSize ","Size: " + sp.size());
             SListcheck = new ArrayList<>(SList);
             Log.d("SizeAfter ","Size: " + SListcheck.size());
             SListcheck.remove(i+2);

             String sp1key = sp1.getKey();
             Log.d("SizeAfter ","Size: " + SListcheck.size());

             for(int j = 0; j < SListcheck.size(); j++ ){
                 StudyPlaces sp2 = SListcheck.get(j);
                 String sp2key = sp2.getKey();
                 if(sp1key == null){
                     sp1key = "null";
                 }
                 if(sp2key == null){
                     sp2key = "null";
                 }
                 Log.d("spKeys","Keys " + sp1key + " key2: " + sp2key);
                 if((sp1key.equals(sp2key)) && sp1.getKey() != null){
                     Log.d("spTestDupe ","found Duplicate" + " Size: " + SList.size() + " Key: "
                             + sp1.getKey() + " Name: " + sp1.getStudyName() + " Key: " + sp2.getKey() + " Name: " + sp2.getStudyName());

                     SList.set(i, sp1 );
                     notifyItemChanged(i);
                     int index = j + 1;
                     SList.remove(index);
                     Log.d("spTestSize ","found Duplicate" + " Size: " + SList.size() + " " + j);
                 }
             }
         }
         notifyDataSetChanged();
     }

    @Override
    public int getItemCount() {
        return SList.size();
    }
}

