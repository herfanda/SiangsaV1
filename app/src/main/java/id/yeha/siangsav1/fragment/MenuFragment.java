package id.yeha.siangsav1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.yeha.siangsav1.R;

public class MenuFragment extends Fragment{
    private static final String TEXT = "TEXTt";
    private static final String COLOR = "COLOR";

    private String strText;
    private int intColor;

    private View viewContent;
    private TextView txtView;

    public static Fragment newInstance(String text, int color) {
        Fragment frag = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        args.putInt(COLOR, color);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // retrieve text and color from bundle or savedInstanceState
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            strText = args.getString(TEXT);
            intColor = args.getInt(COLOR);
        } else {
            strText = savedInstanceState.getString(TEXT);
            intColor = savedInstanceState.getInt(COLOR);
        }

        // initialize views
        viewContent = view.findViewById(R.id.fragment_content);
        txtView = (TextView) view.findViewById(R.id.text);

        // set text and background color
        txtView.setText(strText);
        viewContent.setBackgroundColor(intColor);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT, strText);
        outState.putInt(COLOR, intColor);
        super.onSaveInstanceState(outState);
    }
}
