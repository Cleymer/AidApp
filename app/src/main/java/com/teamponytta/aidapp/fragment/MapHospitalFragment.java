package com.teamponytta.aidapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teamponytta.aidapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Double.parseDouble;

public class MapHospitalFragment extends Fragment {

    private static final String TAG = "MapFragment";
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    private LatLng ltn;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng nic = new LatLng(12.865416, -85.207229);

            addMarkerFromJSON(googleMap);

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nic, 7));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_hospital, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void agregarM(GoogleMap googleMap){
        GoogleMap gmpa = googleMap;
        MarkerOptions options = new MarkerOptions();
        ArrayList<String> nombre = new ArrayList<>();
        int io=0;

        for (int i=0; i<=2; i++){
            latlngs.add(new LatLng(12.334343 + i , 33.43434 + i));
            nombre.add("Punto " + i);
        }

        //addMarkerFromJSON();

        for (LatLng point : latlngs){
            options.position(point);
            options.title(nombre.get(io));
            gmpa.addMarker(options);
            io++;
        }

    }

    private void addMarkerFromJSON(GoogleMap googleMap)
    {
        GoogleMap gMap = googleMap;
        LatLng marker;
        MarkerOptions options = new MarkerOptions();

        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<=jsonArray.length(); i++){

                JSONObject itemObject = jsonArray.getJSONObject(i);
                String name = itemObject.getString("nombre");
                Double lat = parseDouble(itemObject.getString("latitud"));
                Double lon = parseDouble(itemObject.getString("longitud"));

                marker = new LatLng(lon, lat);
                options.position(marker)
                        .title(name);
                gMap.addMarker(options);

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