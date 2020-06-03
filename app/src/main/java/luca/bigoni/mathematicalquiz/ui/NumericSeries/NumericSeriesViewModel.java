package luca.bigoni.mathematicalquiz.ui.NumericSeries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NumericSeriesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NumericSeriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is NumericSeries fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}