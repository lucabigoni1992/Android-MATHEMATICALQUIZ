package luca.bigoni.mathematicalquiz.ui.NumericSeries;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import luca.bigoni.mathematicalquiz.DataBase.DataBaseHandler;
import luca.bigoni.mathematicalquiz.DataBase.MappingExercises;
import luca.bigoni.mathematicalquiz.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class NumericSeriesFragment extends Fragment {

    private  NumericSeriesViewModel galleryViewModel;
    private View root;
    private LayoutInflater inf = null ;
    private View customView = null;
    private PopupWindow mPopupWindow =null;
    private MappingExercises exer;

    //qua scrivo da tastiera a label
    private View.OnClickListener clickKey = new View.OnClickListener() {
        public void onClick(View v) {
            String buttonValue = ((Button) v).getText().toString();
            if (buttonValue.contains("Cancel")) {
                setLabelUsetText(false, "");
            } else {
                setLabelUsetText(true, buttonValue);
            }
        }
    };

    //qua leggo la label
    private TextWatcher txtWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String read = s.toString();
            if (exer == null) {
                //clicco a caso----completato il liverllo o finiti esercizi
                LoadComplite();
            } else if (read.equals(exer.S_RIS)) {
                //successooo Update current value con il successo
                DataBaseHandler.UpdateSuccessExercises(exer);
                loadCorrectExerrcise();
                if (exer == null) {
                    //finiti
                    LoadComplite();
                } else {
                    PopupAll("Congratualtion!\n Exercise complite!",
                            "Go to next!",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    setLabelUsetText(false, "");
                                    mPopupWindow.dismiss();
                                }
                            });
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    public  View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(NumericSeriesViewModel.class);
        root = inflater.inflate(R.layout.fragment_numeric_series, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        inf = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        customView = inf.inflate(R.layout.dialog_complite, null);
        mPopupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, 500);
        //attivo il click su tutti i bottoni nella tastiera
        ArrayList<View> touchables = root.getTouchables();
        for (int i = 0; i < touchables.size(); i++) {
            View touchable = touchables.get(i);
            if (root.getResources().getResourceName(touchable.getId()).toString().split(":id/")[1].contains("button"))
                ;
            touchable.setOnClickListener(clickKey);
        }
        loadCorrectExerrcise();
        TextView editText = (TextView) root.findViewById(R.id.main_EditText_result);
        editText.addTextChangedListener(txtWatch);
        return root;
    }

    private void loadCorrectExerrcise() {

        //prendo l'esercizio corrente
        exer = DataBaseHandler.readAllExercises();
        if (exer == null || exer.S_EXER == "")
            LoadComplite();
        else
        setLabelExerText(exer.S_EXER);
    }

    private void LoadComplite() {
        PopupAll("All EXERCISES HAS BEEN COMPLETE \n Congratulation! you are the best!\n we will add new exercises soon!",
                "Go back on Main Page!",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
    }

    private void PopupAll(String MessageText, String ButtonText, View.OnClickListener buttonAction) {

        if (!MessageText.isEmpty()) {
            TextView textView = (TextView) customView.findViewById(R.id.text_popup_ex_success);
            textView.setText(MessageText);
        }
        Button closeButton = (Button) customView.findViewById(R.id.btn_popup_close);
        if (!ButtonText.isEmpty()) closeButton.setText(ButtonText);
        closeButton.setOnClickListener(buttonAction);
        mPopupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);
    }

    private void setLabelExerText(String valueShow) {
        TextView lab = (TextView) root.findViewById(R.id.text_main_ex_text);
        lab.setText(valueShow);
    }

    private void setLabelUsetText(boolean manten, String text) {
        TextView lab = (TextView) root.findViewById(R.id.main_EditText_result);
        if (manten) {
            lab.setText(lab.getText() + text);
        } else {
            lab.setText(text);
        }
    }
}