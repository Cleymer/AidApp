package com.teamponytta.aidapp.fragment;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.adapter.BotiquinAdapter;
import com.teamponytta.aidapp.adapter.PastillaAdapter;
import com.teamponytta.aidapp.model.Botiquin;
import com.teamponytta.aidapp.model.Pastilla;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastillaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastillaFragment extends Fragment {

    private static final String TAG = "PastillaFragment";

    private RecyclerView recyclerView;
    private PastillaAdapter adapter;
    private ArrayList<Pastilla> pList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String nombre;

    public PastillaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastillaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastillaFragment newInstance(String param1, String param2) {
        PastillaFragment fragment = new PastillaFragment();
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

        View vista = inflater.inflate(R.layout.fragment_pastilla, container, false);

        String category = "Dolores de Cabeza";

        if (getArguments() != null) {
            category = getArguments().getString("name");
        }

        initView(vista, category);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*TextView txt = view.findViewById(R.id.ejemplo);
        if (getArguments() != null) {

            String nombre = getArguments().getString("name");
            txt.setText(nombre);

        }*/
    }


    private void initView(View vista, String category){
        recyclerView = (RecyclerView) vista.findViewById(R.id.rv_pastilla);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pList = new ArrayList<>();

        //createListData();
        addItemsFormJSON(category);


        adapter = new PastillaAdapter(pList);
        recyclerView.setAdapter(adapter);
    }


    private void addItemsFormJSON(String category)
    {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<=jsonArray.length(); i++){
                JSONObject itemObject = jsonArray.getJSONObject(i);
                String name = itemObject.getString("name");
                String desc = itemObject.getString("description");
                String cat = itemObject.getString("category");

                if (cat.equals(category)){
                    Pastilla pastilla = new Pastilla(1, name, desc, cat);
                    pList.add(pastilla);
                }
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.pastillas);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8")
            );

            while ((jsonString = bufferedReader.readLine()) != null)
            {
                builder.append(jsonString);
            }
        }finally {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }

        return new String(builder);
    }

}