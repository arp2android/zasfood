package zasfood.ec.edu.espol.zasfood;

import java.time.LocalDate;

public class Reto {
    private Hueca local;
    private String descripcioon;
    private LocalDate fi;
    private LocalDate ff;

    public Reto() {
    }

    public Reto(Hueca local, String descripcioon, LocalDate fi, LocalDate ff) {
        this.local = local;
        this.descripcioon = descripcioon;
        this.fi = fi;
        this.ff = ff;
    }

    public Hueca getLocal() {
        return local;
    }

    public void setLocal(Hueca local) {
        this.local = local;
    }

    public String getDescripcioon() {
        return descripcioon;
    }

    public void setDescripcioon(String descripcioon) {
        this.descripcioon = descripcioon;
    }

    public LocalDate getFi() {
        return fi;
    }

    public void setFi(LocalDate fi) {
        this.fi = fi;
    }

    public LocalDate getFf() {
        return ff;
    }

    public void setFf(LocalDate ff) {
        this.ff = ff;
    }

    @Override
    public String toString() {
        return "Reto{" +
                "local=" + local +
                ", descripcioon='" + descripcioon + '\'' +
                ", fi=" + fi +
                ", ff=" + ff +
                '}';
    }
}
