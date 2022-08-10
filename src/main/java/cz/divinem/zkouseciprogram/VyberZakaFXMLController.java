package cz.divinem.zkouseciprogram;

import cz.divinem.zkouseciprogram.predmet.MoznostOdpovedi;
import cz.divinem.zkouseciprogram.predmet.Otazka;
import cz.divinem.zkouseciprogram.predmet.Predmet;
import cz.divinem.zkouseciprogram.student.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VyberZakaFXMLController implements Initializable {
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    private Stage stage;
    Random randomGenerator = new Random();
    private String cestaSouboru = "";
    private Predmet predmet;
    ObservableList<String> jmenaStudentu = FXCollections.observableArrayList();
    ObservableList<String> tridy = FXCollections.observableArrayList();
    ObservableList<String> predmety = FXCollections.observableArrayList();
    Student student = new Student();

    @FXML
    private Button dalsiBtn;

    @FXML
    private Button zpetBtn;

    @FXML
    private ComboBox<String> studentiCB;

    @FXML
    private ComboBox<String> tridyCB;

    @FXML
    private ComboBox<String> predmetyCB;

    @FXML
    private TextArea genStudArea;

    @FXML
    void vybratPredmet(ActionEvent event) {
        Predmet vybranyPredmet = Predmet.getPredmet(predmetyCB.getValue());
        if (vybranyPredmet == null) {
            JOptionPane.showMessageDialog(null, "Vyberte předmět");
            return;
        }

        Poskytovatel.POSKYTOVATEL.setPredmet(vybranyPredmet);
        predmet = vybranyPredmet;

        System.out.println("Vybrany predmet: " + predmet);

    }

    @FXML
    void vybratTridu(ActionEvent event) {

        if (tridyCB.getValue() == "4D") {
            System.out.println("Ahoj 4D");
            cestaSouboru = "/seznamy/seznam4D.txt";

        } else if (tridyCB.getValue() == "3D") {
            System.out.println("Ahoj 3D");
            cestaSouboru = "/seznamy/seznam3D.txt";
        } else {
            JOptionPane.showMessageDialog(null, "Vyberte třídu");
        }

        try {
            jmenaStudentu.clear();
            Scanner scStudenti = null;
            scStudenti = new Scanner(this.getClass().getResourceAsStream(cestaSouboru), StandardCharsets.UTF_8);

            while (scStudenti.hasNextLine()) {
                jmenaStudentu.add(scStudenti.nextLine());

            }
            scStudenti.close();
            studentiCB.setItems(jmenaStudentu);

        } catch (Exception ex) {
            Logger.getLogger(VyberZakaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void vybratStud(ActionEvent event) {
        student.setName(studentiCB.getValue().toString());
        Poskytovatel.POSKYTOVATEL.setStudent(student);
        System.out.println("Zkousime: " + student.getName());
    }

    @FXML
    void vygenerovatStud(ActionEvent event) {
        if (Objects.equals(cestaSouboru, "")) {
            JOptionPane.showMessageDialog(null, "Vyberte třídu");

        } else {
            int index = randomGenerator.nextInt(jmenaStudentu.size());
            student.setName(jmenaStudentu.get(index));

            Poskytovatel.POSKYTOVATEL.setStudent(student);
            System.out.println("Zkoušíme: " + student.getName());
            genStudArea.setText(student.getName());
        }
    }

    @FXML
    void zpatkyNaZacatek(ActionEvent event) throws IOException {
        if (stage == null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/FXMLDocument.fxml"));
            stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.getIcons().add(new Image("/obrazky/ikona.png"));
            stage.setTitle("Zkoušecí program");
        }

        stage.show();
        Stage stage = (Stage) zpetBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void dalsiNaZkouseni(ActionEvent event) throws IOException {

        System.out.println("Jdeme na to");
        vygenerovatOtazky();

        if (stage == null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/OtazkyFXML.fxml"));
            stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.getIcons().add(new Image("/obrazky/ikona.png"));
            stage.setTitle("Zkoušecí program");
        }
        stage.show();
        Stage stage = (Stage) zpetBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tridy.addAll("4D", "3D");
        tridyCB.setItems(tridy);

        for (Predmet predmet : Predmet.values()) {
            predmety.add(predmet.getJmeno());
        }

        predmetyCB.setItems(predmety);
    }

    private void vygenerovatOtazky() {
        if (predmet == null) {
            //TODO: dodat vystrahu na nevybrany predmet!
            System.out.println("Nemam vybrany predmet!");
            return;
        }

        List<String> precteneOtazky = new ArrayList<>();
        InputStream souborOtazek = this.getClass().getResourceAsStream("/" + predmet.getJmenoSouboru());
        Scanner otazkovyScanner = new Scanner(souborOtazek);

        while (otazkovyScanner.hasNextLine()) {
            precteneOtazky.add(otazkovyScanner.nextLine());
        }

        System.out.println("Pocet prectenych otazek: " + precteneOtazky.size());

        List<Otazka> prevedeneOtazky = new ArrayList<>();
        for (String otazka : precteneOtazky) {
            String[] rozdelenaOtazka = otazka.split(";");

            Otazka prevedenaOtazka = new Otazka();
            prevedenaOtazka.setCisloOtazky(Integer.parseInt(rozdelenaOtazka[0]));
            prevedenaOtazka.setOtazka(rozdelenaOtazka[1]);
            prevedenaOtazka.pridatOdpoved(MoznostOdpovedi.A, rozdelenaOtazka[2]);
            prevedenaOtazka.setOdpovedA(rozdelenaOtazka[2]);
            prevedenaOtazka.pridatOdpoved(MoznostOdpovedi.B, rozdelenaOtazka[3]);
            prevedenaOtazka.setOdpovedB(rozdelenaOtazka[3]);
            prevedenaOtazka.pridatOdpoved(MoznostOdpovedi.C, rozdelenaOtazka[4]);
            prevedenaOtazka.setOdpovedC(rozdelenaOtazka[4]);
            prevedenaOtazka.pridatOdpoved(MoznostOdpovedi.D, rozdelenaOtazka[5]);
            prevedenaOtazka.setOdpovedD(rozdelenaOtazka[5]);
            prevedenaOtazka.setSpravnaOdpoved(MoznostOdpovedi.valueOf(rozdelenaOtazka[6]));

            prevedeneOtazky.add(prevedenaOtazka);
        }

        Poskytovatel.POSKYTOVATEL.setDostupneOtazky(prevedeneOtazky);
    }
}

