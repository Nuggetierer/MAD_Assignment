package sg.edu.np.mad.mad_assignment.ui.Study;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.R;

public class UpdateImageAdapter extends RecyclerView.Adapter<UpdateImageAdapter.MyHolder> {
    private ArrayList<Images> data;
    Context context;
    String key;

    public UpdateImageAdapter(ArrayList data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.editimage_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        int pos = position;
        Images i1 =  data.get(position);
        String uri2 = (String) (i1.getUri());
        Log.d("UIApos", "pos: " + String.valueOf(pos));
        Log.d("ParseUri", "" + uri2);
//        holder.sdimage.setImageURI(Uri.parse(uri2));
        Glide.with(context).load(uri2).into(holder.Eiimage);

        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(holder.options.getContext(), holder.options);
                popupMenu.inflate(R.menu.edit_image);
                popupMenu.setOnMenuItemClickListener(item ->
                {
                    switch (item.getItemId()) {
                        case R.id.menu_remove:
                            DAOStudyPlaces dao = new DAOStudyPlaces();
                            if(data.size() != 1){
                                new AlertDialog.Builder(holder.options.getContext())
                                        .setTitle("Confirm Delete?")
                                        .setMessage("Image will be deleted forever!")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dao.removeImage(UpdateImages.Eikey, i1.getKey()).addOnSuccessListener(suc ->
                                                {
                                                    Toast.makeText(holder.options.getContext(), "Image is removed", Toast.LENGTH_SHORT).show();
                                                    data.remove(i1);
                                                    notifyItemRemoved(pos);
//                                                    notifyDataSetChanged();
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
                            }
                            else{
                                Toast.makeText(holder.options.getContext(), "Cannot Delete, Only 1 Image left", Toast.LENGTH_SHORT).show();
                            }
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
        Log.d("UpdateImagesAdaptor","size" + data.size());
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView Eiimage;
        TextView options;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            Eiimage = itemView.findViewById(R.id.EditImageLayout);
            options = itemView.findViewById(R.id.EditImagetxt_option);

        }
    }

}

