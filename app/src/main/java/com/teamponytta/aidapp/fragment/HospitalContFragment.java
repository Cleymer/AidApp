package com.teamponytta.aidapp.fragment;

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

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.adapter.HospitalAdapter;
import com.teamponytta.aidapp.adapter.PastillaAdapter;
import com.teamponytta.aidapp.model.Hospital;
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
 * Use the {@link HospitalContFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HospitalContFragment extends Fragment {

    public static final String TAG = "HospitalFragment";

    RecyclerView recyclerView;
    ArrayList<Hospital> hList;
    HospitalAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HospitalContFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HospitalContFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HospitalContFragment newInstance(String param1, String param2) {
        HospitalContFragment fragment = new HospitalContFragment();
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
        View vista = inflater.inflate(R.layout.fragment_hospital_cont, container, false);

        String departamento = "";

        if(getArguments()!=null){
            departamento = getArguments().getString("name");
        }

        initView(vista, departamento);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    private void initView(View vista, String departamento){
        recyclerView = (RecyclerView) vista.findViewById(R.id.rv_hospitalCont);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hList = new ArrayList<>();

        //createListData();
        addItemsFormJSON(departamento);


        adapter = new HospitalAdapter(hList);
        recyclerView.setAdapter(adapter);
    }


    private void addItemsFormJSON(String departamento)
    {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<=jsonArray.length(); i++){
                JSONObject itemObject = jsonArray.getJSONObject(i);
                String name = itemObject.getString("nombre");
                String depart = itemObject.getString("departamento");
                String municipio = itemObject.getString("municipio");
                String telefono = itemObject.getString("teléfono");
                String direccion = itemObject.getString("dirección");
                String servicio = itemObject.getString("servicio");


                if (depart.equals(departamento)){
                    Hospital hospital = new Hospital(1, name, direccion, telefono, departamento, municipio, servicio);
                    hList.add(hospital);
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
            inputStream = getResources().openRawResource(R.raw.hospitales);
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