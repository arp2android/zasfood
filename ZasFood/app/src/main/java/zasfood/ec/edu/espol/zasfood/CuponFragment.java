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
public class CuponFragment extends Fragment {


    public CuponFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView cupones = (RecyclerView) view.findViewById(R.id.cupones);
        cupones.setHasFixedSize(true);
        cupones.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DatabaseReference db = FirebaseDatabase.getInstance().getReference()
                .child("users").child(MainActivity.idUser).child("retos");

        FirebaseRecyclerOptions<String> options =
                new FirebaseRecyclerOptions.Builder<String>()
                        .setQuery(db, String.class).build();

        FirebaseRecyclerAdapter adapter =
                new FirebaseRecyclerAdapter<String, CuponHolder>(options){

                    @NonNull
                    @Override
                    public CuponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View mView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_cupon, parent, false);
                        return new CuponHolder(mView);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull final CuponHolder holder, int position, @NonNull final String model) {
                        FirebaseDatabase.getInstance()
                                .getReference().child("retos")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Reto reto = dataSnapshot.child(model).getValue(Reto.class);
                                        if(reto != null && !reto.getCupon().isEmpty()){
                                            holder.setCupon(reto.getCupon());
                                            holder.setIdLocal(reto.getIdLocal());
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }
                };
        cupones.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cupon, container, false);
    }

}
