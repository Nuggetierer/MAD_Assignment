package sg.edu.np.mad.mad_assignment.ui.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import sg.edu.np.mad.mad_assignment.DBHandler;
import sg.edu.np.mad.mad_assignment.R;

public class Updateeventactivity extends AppCompatActivity {

    String Ename, Eattend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_event);
        DBHandler dbHandler = new DBHandler(this, null, null, 4);

        getData();
        setdata(dbHandler);
    }
    private void getData() {
        if (getIntent().hasExtra("Name") &&
                getIntent().hasExtra("Attend")) {

            Ename = getIntent().getStringExtra("Name");
            Eattend = getIntent().getStringExtra("Attend");}
        else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    private void setdata (DBHandler dbHandler) {
        dbHandler.updateEvent(Ename, Eattend);
    }
}