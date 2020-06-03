package luca.bigoni.mathematicalquiz.ui.contatSupport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import luca.bigoni.mathematicalquiz.R;

@SuppressLint("RestrictedApi")
public class ContatSupportFragment extends Fragment {

    private ContatSupportViewModel ContatSupportViewModel;
private   View root;
private FloatingActionButton floatingAction;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContatSupportViewModel =
                ViewModelProviders.of(this).get(ContatSupportViewModel.class);
        root= inflater.inflate(R.layout.fragment_contat_support, container, false);
        final TextView textView = root.findViewById(R.id.text_contat_support);
        ContatSupportViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         FloatingActionButton floatingAction =(FloatingActionButton) root.findViewById(R.id.SendMail);
        floatingAction.setVisibility(View.VISIBLE);
        floatingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return root;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         floatingAction =(FloatingActionButton) root.findViewById(R.id.SendMail);
        floatingAction.setVisibility(View.INVISIBLE);
        //Save the fragment's state here
    }

}