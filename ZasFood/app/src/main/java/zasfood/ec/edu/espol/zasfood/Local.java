package zasfood.ec.edu.espol.zasfood;


public class Local {

    private String nombre;
    private String direccion;
    private double lat;
    private double lon;

    public Local() {
        //vacio
    }

    public Local(String nombre, String direccion, double lat, double lon) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.lat = lat;
        this.lon = lon;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Local{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Local))
            return false;
        Local l = (Local)o;
        return l.direccion.equals(direccion) && lat == l.lat && lon == l.lon;
    }

}
