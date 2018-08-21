package zasfood.ec.edu.espol.zasfood;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CuponHolder extends RecyclerView.ViewHolder {

    private View mView;
    private DatabaseReference query;

    public CuponHolder(View v){
        super(v);
        mView = v;
        query = FirebaseDatabase.getInstance().getReference();
    }

    public void setIdLocal(int id) {
        query.child("locales").child(id+"")
                .child("nombre").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombre = (String) dataSnapshot.getValue();
                TextView field = (TextView) mView.findViewById(R.id.local);
                field.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setCupon(String cupon){
        TextView field = (TextView) mView.findViewById(R.id.cup);
        field.setText(cupon);
    }
}
