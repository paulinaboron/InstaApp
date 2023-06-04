package com.example.insta.view.post;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta.R;
import com.example.insta.databinding.FragmentMapBinding;
import com.example.insta.databinding.FragmentTagsBinding;
import com.example.insta.helpers.Utils;
import com.example.insta.viewModel.PostViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private String TAG = "xxx";
    private static FragmentMapBinding binding;
    private PostViewModel postViewModel;
    private GoogleMap map;
    private List<Address> list;
    private Geocoder geocoder;
    private String[] REQUIRED_PERMISSIONS = new String[]{
            "android.permission.ACCESS_FINE_LOCATION"
    };
    private int PERMISSIONS_REQUEST_CODE = 100;
    private String address = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(getLayoutInflater());
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        geocoder = new Geocoder(getContext());

        Utils.checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, 100, getContext(), getActivity());
        Utils.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, 100, getContext(), getActivity());


        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(this);

        Places.initialize(getContext(), "AIzaSyAwu6FO-Vb-ITp39cSydpdr7e6yYjdHP5k");
        PlacesClient placesClient = Places.createClient(getContext());

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setActivityMode(AutocompleteActivityMode.FULLSCREEN);
        autocompleteFragment.getView().setBackgroundColor(0x0000ff);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Log.i("xxx", "Place: " + place.getName() + ", " + place.getId());
                try {
                    geoCode(place.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i("xxx", "error: " + status);
            }
        });

        binding.btnNext.setOnClickListener(v->{
            postViewModel.address = address;
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new UploadFragment()).addToBackStack(null).commit();
        });

        return binding.getRoot();
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onMapReady: no permissions");
            return;
        }
        map.setMyLocationEnabled(true);

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

        map.setOnMapClickListener(latLng->{
            try {
                list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                Log.d(TAG, "geoCode: " + list.get(0).getAddressLine(0));
                address = list.get(0).getAddressLine(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng , 10);
            map.moveCamera(cameraUpdate);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(Utils.checkIfPermissionsGranted(REQUIRED_PERMISSIONS, getContext())) onMapReady(map);
    }

    private void geoCode(String locationName) throws IOException {

        list = geocoder.getFromLocationName(locationName, 1);
        Log.d(TAG, "geoCode: " + list.get(0).getAddressLine(0));
        address =  list.get(0).getAddressLine(0);

        double latitude = list.get(0).getLatitude();
        double longitude = list.get(0).getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);

        map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng , 10);
        map.moveCamera(cameraUpdate);

    }
}