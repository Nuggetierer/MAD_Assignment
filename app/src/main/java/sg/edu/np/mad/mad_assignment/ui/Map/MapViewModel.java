package sg.edu.np.mad.mad_assignment.ui.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Ngee Ann Campus map");
    }

    public LiveData<String> getText() {
        return mText;
    }
}