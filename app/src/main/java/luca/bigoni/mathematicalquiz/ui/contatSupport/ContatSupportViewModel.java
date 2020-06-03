package luca.bigoni.mathematicalquiz.ui.contatSupport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContatSupportViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContatSupportViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contat_support fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}