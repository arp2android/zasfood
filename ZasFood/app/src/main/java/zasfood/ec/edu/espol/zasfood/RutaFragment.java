package zasfood.ec.edu.espol.zasfood;

import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class RutaFragment extends Fragment {

    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    Geocoder geocoder;
    Marker marker = null;
    View view;
    List<Marker> localMar = new LinkedList<>();
    List<Marker> retoMar = new LinkedList<>();
    List<Marker> retoMarC = new LinkedList<>();
    FloatingActionButton bt_lo;
    FloatingActionButton bt_re;
    FloatingActionButton bt_ub;
    FloatingActionButton bt_op;
    FloatingActionButton bt_rec;
    boolean estado = true;
    boolean esLocal = true;
    boolean esReto = true;
    boolean esRetoC = true;

    public RutaFragment() {
        // Required empty public constructor
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        geocoder = new Geocoder(this.getContext());
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_location);
        bt_lo = view.findViewById(R.id.bt_lo);
        bt_re = view.findViewById(R.id.bt_re);
        bt_rec = view.findViewById(R.id.bt_rec);
        bt_ub = view.findViewById(R.id.bt_ub);
        bt_op = view.findViewById(R.id.bt_op);

        bt_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!estado){
                    bt_ub.setVisibility(View.VISIBLE);
                    bt_re.setVisibility(View.VISIBLE);
                    bt_rec.setVisibility(View.VISIBLE);
                    bt_lo.setVisibility(View.VISIBLE);
                    estado = true;
                }else {
                    bt_ub.setVisibility(View.INVISIBLE);
                    bt_re.setVisibility(View.INVISIBLE);
                    bt_rec.setVisibility(View.INVISIBLE);
                    bt_lo.setVisibility(View.INVISIBLE);
                    estado = false;
                }
            }
        });

        bt_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(Marker m: localMar){
                    if(esLocal){
                        m.setVisible(false);
                    }else{
                        m.setVisible(true);
                    }
                }
                esLocal = !esLocal;
            }
        });

        bt_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(Marker m: retoMar){
                    if(esReto){
                        m.setVisible(false);
                    }else{
                        m.setVisible(true);
                    }
                }
                esReto = !esReto;
            }
        });

        bt_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(Marker m: retoMarC){
                    if(esRetoC){
                        m.setVisible(false);
                    }else{
                        m.setVisible(true);
                    }
                }
                esRetoC = !esRetoC;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ruta, container, false);
        if(mapFragment == null){
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    mMap.setMinZoomPreference(12);
                    LatLng gyeLocation = new LatLng(-2.2058400, -79.9079500);
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(gyeLocation));

                    FirebaseDatabase.getInstance().getReference()
                            .child("locales")
                            .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds: dataSnapshot.getChildren()){
                            Local local = ds.getValue(Local.class);
                            addMarker(
                                    new LatLng(local.getLat(), local.getLon()),
                                    local.getNombre(), R.drawable.ic_local, false);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    FirebaseDatabase.getInstance().getReference()
                            .child("retos")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                                        final Reto reto = ds.getValue(Reto.class);
                                            final String k = ds.getKey();
                                        if(reto != null){
                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("users").child(MainActivity.idUser)
                                                    .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            User user = dataSnapshot.getValue(User.class);
                                                            if(user != null ){
                                                                if(user.getRetos().contains(k)){
                                                                    FirebaseDatabase.getInstance().getReference()
                                                                            .child("locales").child(reto.getIdLocal()+"")
                                                                            .addValueEventListener(new ValueEventListener() {
                                                                                @Override
                                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                    Local local = dataSnapshot.getValue(Local.class);
                                                                                    if(local != null)
                                                                                        addMarker(
                                                                                                new LatLng(local.getLat(),
                                                                                                        local.getLon()), reto.getReto(),
                                                                                                R.drawable.ic_star_on, false);
                                                                                }

                                                                                @Override
                                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                }
                                                                            });
                                                                }else{
                                                                    FirebaseDatabase.getInstance().getReference()
                                                                            .child("locales").child(reto.getIdLocal()+"")
                                                                            .addValueEventListener(new ValueEventListener() {
                                                                                @Override
                                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                    Local local = dataSnapshot.getValue(Local.class);
                                                                                    if(local != null)
                                                                                        addMarker(
                                                                                                new LatLng(local.getLat(),
                                                                                                        local.getLon()), reto.getReto(),
                                                                                                R.drawable.ic_star_of, false);
                                                                                }

                                                                                @Override
                                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                }
                                                                            });
                                                                }

                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });

                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                }
            });
        }

        getChildFragmentManager().beginTransaction().replace(R.id.map_location, mapFragment).commit();
        return rootView;
    }

    public void addMarker(LatLng place, String title, int iconID, boolean moveCam){
        marker = mMap.addMarker(new MarkerOptions()
                .position(place)
                .title(title)
                .draggable(false)
                .icon(BitmapDescriptorFactory.fromResource(iconID))
        );
        if(iconID == R.drawable.ic_local)
            localMar.add(marker);
        else if(iconID == R.drawable.ic_star_of)
            retoMar.add(marker);
        else if(iconID == R.drawable.ic_star_on)
            retoMarC.add(marker);
        if(moveCam) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 17));
        }
    }
}
