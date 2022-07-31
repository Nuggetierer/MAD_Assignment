package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import sg.edu.np.mad.mad_assignment.R;

public class ImportImages extends AppCompatActivity {

    private Button uploadBtn;
    private ImageView uploadimage;
    private ProgressBar progressBar;
    private Uri imageUri;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_images);

        uploadBtn = findViewById(R.id.UploadImagesbutton);
        uploadimage = findViewById(R.id.AddimageView2);
        progressBar = findViewById(R.id.AddImageprogressBar);

        progressBar.setVisibility(View.INVISIBLE);

        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryintent = new Intent();
                galleryintent.setAction(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,2);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri != null){
                    uploadToFirebase(imageUri);
                }
                else{
                    Toast.makeText(ImportImages.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data!=null){

            imageUri = data.getData();
            uploadimage.setImageURI(imageUri);
        }
    }

    private void uploadToFirebase(Uri uri){

        StorageReference fileref = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(ImportImages.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        DAOStudyPlaces daosp = new DAOStudyPlaces();
                        daosp.addStorageImages(uri);
                        progressBar.setVisibility(View.INVISIBLE);

                        Intent intent =  new Intent(ImportImages.this, AddStudyLocation.class);
                        intent.putExtra("Uri", uri.toString());
                        setResult(Activity.RESULT_OK, intent);
                        finish();
//                        uploadimage.setImageResource(R.drawable.);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(ImportImages.this, "Uploading Failed!", Toast.LENGTH_SHORT).show();
                Log.d("ErrorImage", "Error "+ e.toString());
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}