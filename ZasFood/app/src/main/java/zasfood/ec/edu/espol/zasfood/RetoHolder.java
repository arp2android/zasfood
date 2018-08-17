package zasfood.ec.edu.espol.zasfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RetoHolder extends RecyclerView.ViewHolder {

    private View view;

    public RetoHolder(View item) {
        super(item);
        view = item;
    }

    public void setReto(String reto) {
        ((TextView)view.findViewById(R.id.reto)).setText(reto);
    }

    public void setHueca(String hueca) {
        ((TextView)view.findViewById(R.id.nombre)).setText(hueca);
    }

    public void setFechaInicio(String fi) {
        ((TextView)view.findViewById(R.id.fi)).setText(fi);
    }

    public void setFechaFin(String ff) {
        ((TextView)view.findViewById(R.id.ff)).setText(ff);
    }
}
