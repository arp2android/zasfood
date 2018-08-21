package zasfood.ec.edu.espol.zasfood;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetoHolder extends RecyclerView.ViewHolder {

    private View mView;
    private DatabaseReference query;

    public RetoHolder(View item) {
        super(item);
        mView = item;
        query = FirebaseDatabase.getInstance().getReference();
    }

    public void setReto(String reto) {
        TextView field = (TextView) mView.findViewById(R.id.lblFecha);
        field.setText(reto);
    }

    public void setIdLocal(int id) {
                query.child("locales").child(id+"")
                .child("nombre").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombre = (String) dataSnapshot.getValue();
                TextView field = (TextView) mView.findViewById(R.id.lblCielo);
                field.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setInicio(String inicio) {
        TextView field = (TextView) mView.findViewById(R.id.lblTemperatura);
        field.setText(inicio);
    }

    public void setFin(String fin) {
        TextView field = (TextView) mView.findViewById(R.id.lblHumedad);
        field.setText(fin);
    }

    public void setId(final Reto reto) {
        query.child("retos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Reto r = ds.getValue(Reto.class);
                    if(r.equals(reto)){
                        TextView field = (TextView) mView.findViewById(R.id.idReto);
                        field.setText("Id: " + ds.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addEvent(int id) {
        query.child("locales").child(id+"").addValueEventListener(new ValueEventListener() {
            double lat = 0;
            double lon = 0;
            String nombre = "";
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.getKey().equals("lat"))
                        lat = (double)ds.getValue();
                    else if(ds.getKey().equals("lon"))
                        lon = (double)ds.getValue();
                    else if(ds.getKey().equals("nombre"))
                        nombre = (String)ds.getValue();
                }
                ImageButton ib = (ImageButton)mView.findViewById(R.id.verMapa);
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LatLng latLng = new LatLng(lat, lon);
                        //MainActivity.rutaView.addMarker(latLng, nombre, true);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
