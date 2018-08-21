package zasfood.ec.edu.espol.zasfood;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class RetoFragment extends Fragment {


    public RetoFragment() {
        // Required empty public constructor
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference dbRetos =
                FirebaseDatabase.getInstance().getReference()
                        .child("retos");

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.lstRetos);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));


        FirebaseRecyclerOptions<Reto> options =
                new FirebaseRecyclerOptions.Builder<Reto>()
                        .setQuery(dbRetos, Reto.class).build();


        FirebaseRecyclerAdapter adapter =
                new FirebaseRecyclerAdapter<Reto, RetoHolder>(options) {
                    @NonNull
                    @Override
                    public RetoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View mView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_lista, parent, false);
                        return new RetoHolder(mView);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull RetoHolder retoHolder, int position, @NonNull Reto reto) {
                        retoHolder.setFin("Fin: " + reto.getFin());
                        retoHolder.setIdLocal(reto.getIdLocal());
                        retoHolder.setInicio("Inicio: " + reto.getInicio());
                        retoHolder.setReto(reto.getReto());
                        retoHolder.addEvent(reto.getIdLocal());
                        retoHolder.setId(reto);
                    }
                };

        recycler.setAdapter(adapter);
        adapter.startListening();
        cumplidos(view);
    }

    private void cumplidos(View view) {
        RecyclerView cumplidos = (RecyclerView) view.findViewById(R.id.cumplidos);
        cumplidos.setHasFixedSize(true);
        cumplidos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DatabaseReference db = FirebaseDatabase.getInstance().getReference()
                .child("users").child(MainActivity.idUser).child("retos");
        FirebaseRecyclerOptions<String> options =
                new FirebaseRecyclerOptions.Builder<String>()
                        .setQuery(db, String.class).build();

        FirebaseRecyclerAdapter adapter =
                new FirebaseRecyclerAdapter<String, RetoHolder>(options) {

                    @NonNull
                    @Override
                    public RetoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View mView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_lista, parent, false);
                        return new RetoHolder(mView);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull final RetoHolder retoHolder, int position, @NonNull final String model) {
                        FirebaseDatabase.getInstance()
                                .getReference().child("retos")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Reto reto = dataSnapshot.child(model).getValue(Reto.class);
                                        retoHolder.setFin("Fin: " + reto.getFin());
                                        retoHolder.setIdLocal(reto.getIdLocal());
                                        retoHolder.setInicio("Inicio: " + reto.getInicio());
                                        retoHolder.setReto(reto.getReto());
                                        retoHolder.addEvent(reto.getIdLocal());
                                        retoHolder.setId(reto);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }
                };
        cumplidos.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reto, container, false);
    }

}
