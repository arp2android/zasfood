package zasfood.ec.edu.espol.zasfood;

import android.content.Context;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class RutaFragment extends Fragment {

    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    Geocoder geocoder;
    Marker marker = null;

    public RutaFragment() {
        // Required empty public constructor
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        geocoder = new Geocoder(this.getContext());
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_location);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ruta, container, false);
        if(mapFragment == null){
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    mMap.setMinZoomPreference(11);
                    LatLng gyeLocation = new LatLng(-2.2058400, -79.9079500);
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(gyeLocation));
                }
            });
        }
        getChildFragmentManager().beginTransaction().replace(R.id.map_location, mapFragment).commit();
        return rootView;
    }

    private void addMarker(final double lat, final double lng, String title, boolean moveCam){

        LatLng place = new LatLng(lat, lng);
        if(marker != null) {
            marker.remove();
        }
        marker = mMap.addMarker(new MarkerOptions()
                .position(place)
                .title(title)
                .draggable(true)
        );
        if(moveCam) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 17));
        }
    }
}
