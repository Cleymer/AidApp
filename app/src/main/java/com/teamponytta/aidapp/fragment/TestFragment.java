package com.teamponytta.aidapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.teamponytta.aidapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button nivel1, nivel2, nivel3;


    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_test, container, false);
        nivel1 = vista.findViewById(R.id.nivel1);
        nivel2 = vista.findViewById(R.id.nivel2);
        nivel3 = vista.findViewById(R.id.nivel3);

        nivel1.setOnClickListener(this);
        nivel2.setOnClickListener(this);
        nivel3.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nivel1:
                gotoUrl("https://docs.google.com/forms/d/e/1FAIpQLSe37qyRCgkva5vQ4EUmvnyeahzS8wFVzPSRuGVRgey1SPm9-Q/viewform?usp=sf_link");
                break;

            case R.id.nivel2:
                gotoUrl("https://forms.gle/eJxVGohDdPhfn7qj8");
                break;

            case R.id.nivel3:
                gotoUrl("https://forms.gle/vvLLNnFUxcj1cxpR7");
                break;
        }
    }
    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}