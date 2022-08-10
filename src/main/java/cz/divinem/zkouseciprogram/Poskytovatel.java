package cz.divinem.zkouseciprogram;

import cz.divinem.zkouseciprogram.predmet.Otazka;
import cz.divinem.zkouseciprogram.predmet.Predmet;
import cz.divinem.zkouseciprogram.student.Student;

import java.util.ArrayList;
import java.util.List;

public class Poskytovatel {
    public static Poskytovatel POSKYTOVATEL = new Poskytovatel();
    private Student aktualniStudent;
    private Predmet aktualniPredmet;
    private Student aktualniProcenta;
    private List<Otazka> dostupneOtazky = new ArrayList<>();

    public Student getAktualniProcenta() {
        return aktualniProcenta;
    }

    public void setAktualniProcenta(Student aktualniProcenta) {
        this.aktualniProcenta = aktualniProcenta;
    }

    public void setStudent(Student student) {
        this.aktualniStudent = student;
    }

    public Student getStudent() {
        return this.aktualniStudent;
    }

    public void setPredmet(Predmet predmet) {
        this.aktualniPredmet = predmet;
    }

    public Predmet getPredmet() {
        return this.aktualniPredmet;
    }

    public List<Otazka> getDostupneOtazky() {
        return dostupneOtazky;
    }

    public void setDostupneOtazky(List<Otazka> otazky) {
        this.dostupneOtazky = otazky;
    }

}
