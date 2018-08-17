package zasfood.ec.edu.espol.zasfood;

import com.google.android.gms.maps.model.LatLng;

public class Hueca {

    private String nombre;
    private String direccion;
    private LatLng latLng;

    public Hueca() {

    }

    public Hueca(String nombre, String direccion, LatLng latLng) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.latLng = latLng;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public String toString() {
        return "Hueca{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", latLng=" + latLng +
                '}';
    }
}
