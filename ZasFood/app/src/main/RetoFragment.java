package zasfood.ec.edu.espol.zasfood;


import android.content.pm.FeatureGroupInfo;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * A simple {@link Fragment} subclass.
 */
public class RetoFragment extends Fragment {

    private FirebaseRecyclerAdapter adapter;

    public RetoFragment() {
        // Required empty public constructor
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference dbRetos = FirebaseDatabase.getInstance().getReference().child("Retos");

        RecyclerView recycler = view.findViewById(R.id.lista);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FirebaseRecyclerOptions<Reto> options =
                new FirebaseRecyclerOptions.Builder<Reto>().
                        setQuery(dbRetos, Reto.class).build();

        adapter = new FirebaseRecyclerAdapter<Reto, RetoHolder>(options) {

            @NonNull
            @Override
            public RetoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
                return new RetoHolder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull RetoHolder holder, int position, @NonNull Reto model) {
                holder.setIdLocal(model.getIdLocal());
                holder.setReto(model.getReto());
                holder.setFechaInicio(model.getInicio());
                holder.setFechaFin(model.getFin());
            }
        };
        recycler.setAdapter(adapter);
        adapter.startListening();
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reto, container, false);
    }

    public FirebaseRecyclerAdapter getAdapter() {
        return adapter;
    }
}
