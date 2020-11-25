package com.teamponytta.aidapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.adapter.BotiquinAdapter;
import com.teamponytta.aidapp.adapter.PrimerosAuxiliosAdapter;
import com.teamponytta.aidapp.model.Botiquin;
import com.teamponytta.aidapp.model.PrimerosAuxilios;

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
 * Use the {@link BotiquinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BotiquinFragment extends Fragment {

    private static final String TAG = "BotiquinFragment";

    private RecyclerView recyclerView;
    private BotiquinAdapter adapter;
    private ArrayList<Botiquin> bList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BotiquinFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BotiquinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BotiquinFragment newInstance(String param1, String param2) {
        BotiquinFragment fragment = new BotiquinFragment();
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
        View vista = inflater.inflate(R.layout.fragment_botiquin, container, false);
        initView(vista);

        return vista;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = bList.get(recyclerView.getChildAdapterPosition(view)).getNombre();

                Bundle bundle = new Bundle();
                bundle.putString("name", nombre);

                Navigation.findNavController(view).navigate(R.id.nav_pastilla, bundle);
            }
        });
    }

    private void initView(View vista){
        recyclerView = (RecyclerView) vista.findViewById(R.id.rv_botiquin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bList = new ArrayList<>();

        //createListData();
        addItemsFormJSON();


        adapter = new BotiquinAdapter(bList);
        recyclerView.setAdapter(adapter);
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
                String img = itemObject.getString("photoIcon");

                Botiquin botiquin = new Botiquin(1, name, desc, idPhoto(img));
                bList.add(botiquin);
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
            inputStream = getResources().openRawResource(R.raw.botiquin);
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

    private int idPhoto(String photo){

        int idFotoB = R.drawable.ic_add_box_24px;

        if (photo.equals("dolorc")){
            idFotoB = R.drawable.dolorc;
            return idFotoB;
        }else if (photo.equals("dolorm")){
            idFotoB = R.drawable.dolorm;
            return idFotoB;
        }else if (photo.equals("shock")){
            idFotoB = R.drawable.shock;
            return idFotoB;
        }else if (photo.equals("dolore")){
            idFotoB = R.drawable.dolore;
            return idFotoB;
        }else if (photo.equals("doloro")){
            idFotoB = R.drawable.doloro;
            return idFotoB;
        }else if (photo.equals("dolorg")){
            idFotoB = R.drawable.dolorg;
            return idFotoB;
        }else if (photo.equals("acidez")){
            idFotoB = R.drawable.acidez;
            return idFotoB;
        }else if (photo.equals("gastri")){
            idFotoB = R.drawable.gastri;
            return idFotoB;
        }else if (photo.equals("calent")){
            idFotoB= R.drawable.calent;
            return idFotoB;
        }else if (photo.equals("insomnio")){
            idFotoB= R.drawable.insomnio;
            return idFotoB;
        }else if (photo.equals("metabol")){
            idFotoB= R.drawable.metabol;
            return idFotoB;
        }else if (photo.equals("alergia")){
            idFotoB= R.drawable.alergia;
            return idFotoB;
        }else if (photo.equals("inflamacion")){
            idFotoB= R.drawable.inflamacion;
            return idFotoB;
        }else if (photo.equals("diarrea")){
            idFotoB= R.drawable.diarrea;
            return idFotoB;
        }

        return idFotoB;

    }
}