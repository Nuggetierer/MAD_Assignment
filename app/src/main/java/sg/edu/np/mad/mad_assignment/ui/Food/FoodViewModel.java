package sg.edu.np.mad.mad_assignment.ui.Food;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FoodViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FoodViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Food in progress.....");
    }

    public LiveData<String> getText() {
        return mText;
    }
}