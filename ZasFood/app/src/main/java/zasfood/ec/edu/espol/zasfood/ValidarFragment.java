package zasfood.ec.edu.espol.zasfood;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValidarFragment extends Fragment {

    private EditText email;
    private EditText idReto;
    private Button ok;

    public ValidarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.emailin);
        idReto = view.findViewById(R.id.idin);
        ok = view.findViewById(R.id.btn_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String correo = email.getText().toString();
                final String reto = idReto.getText().toString();
                if(correo.isEmpty() || reto.isEmpty())
                    Toast.makeText(ValidarFragment.this.getContext(),
                            "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    final DatabaseReference query =  FirebaseDatabase.getInstance().getReference()
                            .child("users");

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = null;
                            String key = "";
                            for(DataSnapshot ds: dataSnapshot.getChildren()){
                                user = ds.getValue(User.class);
                                if(user != null && user.getEmail().equals(correo)){
                                    user.getRetos().add(reto);
                                    key = ds.getKey();

                                }
                            }
                            if(user != null && key != null)
                                query.child(key).child("retos").setValue(user.getRetos());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_validar, container, false);
    }

}
