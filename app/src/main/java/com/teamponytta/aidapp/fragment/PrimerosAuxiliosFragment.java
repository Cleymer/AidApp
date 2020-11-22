package com.teamponytta.aidapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.adapter.PrimerosAuxiliosAdapter;
import com.teamponytta.aidapp.model.PrimerosAuxilios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrimerosAuxiliosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrimerosAuxiliosFragment extends Fragment {

    private RecyclerView recyclerView;
    private PrimerosAuxiliosAdapter adapter;
    private ArrayList<PrimerosAuxilios> paList;

    private static final String TAG = "PAFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrimerosAuxiliosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrimerosAuxiliosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrimerosAuxiliosFragment newInstance(String param1, String param2) {
        PrimerosAuxiliosFragment fragment = new PrimerosAuxiliosFragment();
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
        View vista = inflater.inflate(R.layout.fragment_primeros_auxilios, container, false);

        recyclerView = (RecyclerView) vista.findViewById(R.id.rv_pa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        paList = new ArrayList<>();

        //createListData();
        addItemsFormJSON();

        adapter = new PrimerosAuxiliosAdapter(paList);
        recyclerView.setAdapter(adapter);

        return vista;
    }

    private void addItemsFormJSON()
    {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<=jsonArray.length(); i++){
                JSONObject itemObject = jsonArray.getJSONObject(i);
                String name = itemObject.getString("name");
                String desc = itemObject.getString("description");

                PrimerosAuxilios pa = new PrimerosAuxilios(1, name, desc, 1);
                paList.add(pa);
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
            inputStream = getResources().openRawResource(R.raw.pauxilios);
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