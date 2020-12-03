package com.teamponytta.aidapp.fragment;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.model.Pastilla;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    private static final String TAG = "InfoFragment";

    TextView txtDesc, txtPaso, txtNombre;
    ImageView imgPa;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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

        View vista = inflater.inflate(R.layout.fragment_info, container, false);

        String pa = "";

        if (getArguments()!=null){
            pa = getArguments().getString("name");
        }

        TextView tv = vista.findViewById(R.id.tv_name);
        tv.setText(pa);

        setContent(pa, vista);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setContent(String name_pa, View view)
    {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<=jsonArray.length(); i++){
                JSONObject itemObject = jsonArray.getJSONObject(i);
                String name = itemObject.getString("name");
                String desc = itemObject.getString("description");
                String paso = itemObject.getString("paso");

                if (name.equals(name_pa)){
                    txtDesc = view.findViewById(R.id.tv_desc);
                    txtDesc.setText(desc);

                    txtPaso = view.findViewById(R.id.pasos);
                    txtPaso.setText(paso);

                    imgPa = view.findViewById(R.id.img_pa);
                    imgPa.setImageResource(searchPhoto(name_pa));

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
            inputStream = getResources().openRawResource(R.raw.pasopa);
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


    private int searchPhoto(String nombre){

        int photo = R.drawable.acidez;

        switch (nombre){
            case "Hemorragias":
                photo = R.drawable.hemorragias2;
                return photo;

            case "Asfixia":
                photo = R.drawable.asfixia2;
                return photo;

            case "Shock":
                photo = R.drawable.shock2;
                return photo;

            case "Contusiones":
                photo = R.drawable.contusiones2;
                return photo;

            case "Heridas":
                photo = R.drawable.heridas2;
                return photo;

            case "Esguince":
                photo = R.drawable.esguince2;
                return photo;

            case "Fractura":
                photo = R.drawable.fractura2;
                return photo;

            case "Quemaduras":
                photo = R.drawable.quemaduras2;
                return photo;

            case "Parada Cardiorespiratoria":
                photo = R.drawable.pcardiorespiratoria;
                return photo;

            case "Reanimacion Cardiopulmonar Básica":
                photo = R.drawable.rcp2;
                return photo;

            default:
                break;

        }

        return photo;
    }

}