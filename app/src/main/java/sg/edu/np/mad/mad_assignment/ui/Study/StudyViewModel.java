package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public StudyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Study in progress.....");
    }

    public LiveData<String> getText() {
        return mText;
    }
}