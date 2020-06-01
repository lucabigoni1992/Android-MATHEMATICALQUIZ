package luca.bigoni.mathematicalquiz.ui.gallery;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import luca.bigoni.mathematicalquiz.DataBase.DataBaseHandler;
import luca.bigoni.mathematicalquiz.DataBase.MappingExercises;
import luca.bigoni.mathematicalquiz.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private View root;
    //qua scrivo da tastiera a label
    private View.OnClickListener clickKey = new View.OnClickListener() {
        public void onClick(View v) {
            String buttonValue = ((Button) v).getText().toString();
            TextView lab = (TextView) root.findViewById(R.id.main_EditText_result);
            if (buttonValue.contains("Cancel")) {
                lab.setText("");
            } else {
                lab.setText(lab.getText() + buttonValue);
            }
        }
    };
    private PopupWindow mPopupWindow;

    //qua leggo la label
    private TextWatcher txtWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String read = s.toString();
            if (exer == null) {
                setLabelExerText("Winnerinoooo");
            }
            if (read.equals(exer.S_RIS)) {
                //successooo Update current value con il successo
                DataBaseHandler.UpdateSuccessExercises(exer);
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                View customView = inflater.inflate(R.layout.dialog_complite, null);
                mPopupWindow = new PopupWindow (
                        customView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        500
                );
                Button closeButton = (Button) customView.findViewById(R.id.btn_popup_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setLabelExerText("");
                        loadCorrectExerrcise();
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private MappingExercises exer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //attivo il click su tutti i bottoni nella tastiera
        ArrayList<View> touchables = root.getTouchables();
        for (int i = 0; i < touchables.size(); i++) {
            View touchable = touchables.get(i);
            if (root.getResources().getResourceName(touchable.getId()).toString().split(":id/")[1].contains("button"))
                ;
            touchable.setOnClickListener(clickKey);
        }
        loadCorrectExerrcise();
        TextView edittext = (TextView) root.findViewById(R.id.main_EditText_result);
        edittext.addTextChangedListener(txtWatch);
        return root;
    }

    private void loadCorrectExerrcise() {

        //prendo l'esercizio corrente
        exer = DataBaseHandler.readAllExercises();
        if (exer == null || exer.S_EXER == "") {
            //prendere l'ultimo
            return;
        }
        setLabelExerText(exer.S_EXER);
    }


    private void setLabelExerText(String valueShow) {
        TextView lab = (TextView) root.findViewById(R.id.text_main_ex_text);
        lab.setText(valueShow);
    }
}