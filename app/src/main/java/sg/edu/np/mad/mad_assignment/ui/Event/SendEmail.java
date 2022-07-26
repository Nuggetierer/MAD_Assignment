package sg.edu.np.mad.mad_assignment.ui.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sg.edu.np.mad.mad_assignment.R;

public class SendEmail extends AppCompatActivity {

    private TextInputEditText subject;
    private TextInputEditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        //EditText email = findViewById(R.id.etTo);
        subject = (TextInputEditText)findViewById(R.id.etSubject_text);
        body = (TextInputEditText)findViewById(R.id.etBody_text);
        Button buttonSend = findViewById(R.id.btnSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subject.getText().toString().isEmpty() && !body.getText().toString().isEmpty()) {
                    String email = "NpGuideApp@gmail.com";
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_EMAIL, email);
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
                    intent.setData(Uri.parse("mailto:"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(SendEmail.this, "There is no application that support this action",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(SendEmail.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
