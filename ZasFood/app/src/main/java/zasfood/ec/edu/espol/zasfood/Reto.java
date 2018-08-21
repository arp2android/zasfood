package zasfood.ec.edu.espol.zasfood;

import java.time.LocalDate;

public class Reto {
    private int idLocal;
    private String reto;
    private String inicio;
    private String fin;

    public Reto() {
        //vacio
    }

    public Reto(int idLocal, String reto, String inicio, String fin) {
        this.idLocal = idLocal;
        this.reto = reto;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getReto() {
        return reto;
    }

    public void setReto(String reto) {
        this.reto = reto;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Reto{" +
                "idLocal=" + idLocal +
                ", reto='" + reto + '\'' +
                ", inicio='" + inicio + '\'' +
                ", fin='" + fin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Reto))
            return false;
        Reto r = (Reto)o;
        return r.inicio.equals(inicio) && r.fin.equals(fin)
                && r.idLocal == idLocal && r.reto.equals(reto);
    }
}
