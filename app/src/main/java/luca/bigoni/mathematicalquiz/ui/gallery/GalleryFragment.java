package luca.bigoni.mathematicalquiz.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import luca.bigoni.mathematicalquiz.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private View root;
    private View.OnClickListener clickKey=new View.OnClickListener() {
        public void onClick(View v) {
            String buttonValue =((AppCompatButton) v).getText().toString();
            TextView lab = (TextView) root.findViewById(R.id.lab_result);
            lab.setText(lab.getText()+buttonValue);
        }
    };

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
        for(int i = 0; i < touchables.size(); i++)
        {
            View touchable = touchables.get(i);
            if(root.getResources().getResourceName(touchable.getId()).toString().split(":id/")[1].contains("button"));
                touchable.setOnClickListener(clickKey);
        }
        return root;
    }
}