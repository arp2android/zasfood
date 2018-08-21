package zasfood.ec.edu.espol.zasfood;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFragment extends Fragment {
    private EditText idLocal;
    private DatePicker inicio;
    private DatePicker fin;
    private MultiAutoCompleteTextView reto;
    private CheckBox tieneCupon;
    private MultiAutoCompleteTextView cupon;
    private Button crear;

    Spinner locales;
    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idLocal = view.findViewById(R.id.id);
        inicio = view.findViewById(R.id.inicio);
        fin = view.findViewById(R.id.fin);
        reto = view.findViewById(R.id.txtreto);
        tieneCupon = view.findViewById(R.id.tiene);
        cupon = view.findViewById(R.id.txtcupon);
        crear = view.findViewById(R.id.crear);

        tieneCupon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    cupon.setVisibility(View.VISIBLE);
                else
                    cupon.setVisibility(View.INVISIBLE);
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idLocal.getText().toString();
                String re = reto.getText().toString();
                String cu = cupon.getText().toString();
                if(tieneCupon.isChecked()) {
                    if(id.isEmpty() || re.isEmpty() || cu.isEmpty())
                        Toast.makeText(AdminFragment.this.getContext(),
                                "Por favor llene todoas los campos", Toast.LENGTH_SHORT).show();
                    else{
                        crearReto(Integer.parseInt(id), getFecha(inicio), getFecha(fin), re, cu);
                        clear();
                        Toast.makeText(AdminFragment.this.getContext(),
                                "Creado con exito", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if(id.isEmpty() || re.isEmpty())
                        Toast.makeText(AdminFragment.this.getContext(),
                                "Por favor llene todoas los campos", Toast.LENGTH_SHORT).show();
                    else {
                        crearReto(Integer.parseInt(id), getFecha(inicio), getFecha(fin), re, "");
                        clear();
                        Toast.makeText(AdminFragment.this.getContext(),
                                "Creado con exito", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void crearReto(int i, String fecha, String fecha1, String re, String cu) {
        Reto reto = new Reto(i, re, fecha, fecha1, cu);
        FirebaseDatabase.getInstance().getReference()
                .child("retos").push().setValue(reto);
    }

    private void clear(){
        idLocal.setText("");
        tieneCupon.setChecked(false);
        cupon.setText("");
        reto.setText("");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    private String getFecha(DatePicker dp) {
        int dia = dp.getDayOfMonth();
        int mes = dp.getMonth();
        int year = dp.getYear();
        String d = dia + "";
        String m = mes + "";
        if(dia < 10) {
            d = "0" + d;
        }
        if(mes < 10){
            m = "0"+m;
        }
        return d + "/" + m + "/" + year;
    }

}
