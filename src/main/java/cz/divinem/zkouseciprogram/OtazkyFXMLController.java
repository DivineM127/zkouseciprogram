package cz.divinem.zkouseciprogram;

import cz.divinem.zkouseciprogram.predmet.MoznostOdpovedi;
import cz.divinem.zkouseciprogram.predmet.Otazka;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class OtazkyFXMLController implements Initializable {
    private Stage stage;
    int pocetOtazek = 0;
    double pocetBodu = 0;
    int prumer = 0;
    int zodpovezeneOtazky = 0;
    double procenticka = 0;
    private Random random = new Random();
    private List<Integer> pouziteOtazky = new ArrayList<>();

    private Otazka aktualniOtazka;

    @FXML
    private TextArea otazkaArea;

    @FXML
    private Button moznostA;

    @FXML
    private Button moznostB;

    @FXML
    private Button moznostC;

    @FXML
    private Button moznostD;

    @FXML
    private Button zpetBtn;

    @FXML
    private Button genOtazBtn;

    @FXML
    void vygenerovatOtazecku(ActionEvent event) {
        // z texťáku sem narvat otázku a odebrat ji z dalších možných otázek aby se neopakovaly

        List<Otazka> dostupneOtazky = Poskytovatel.POSKYTOVATEL.getDostupneOtazky();
        if (pouziteOtazky.size() == dostupneOtazky.size()) {
            //TODO: vsechny otazky uz byly pouzite!
            System.out.println("Vsechny otázky byly použity!");
            otazkaArea.setText("Všechny otázky byly použity!");
            //ukoncitBtn.fire();
            return;
        }

        Otazka otazka = dostupneOtazky.get(random.nextInt(dostupneOtazky.size() - 1));
        if (pouziteOtazky.contains(otazka.getCisloOtazky())) {
            otazka = dostupneOtazky.get(random.nextInt(dostupneOtazky.size() - 1));
        }

        aktualniOtazka = otazka;

        pouziteOtazky.add(otazka.getCisloOtazky());
        otazkaArea.clear();
        otazkaArea.appendText(otazka.getOtazka()
                + System.lineSeparator()
                + otazka.getOdpovedA()
                + System.lineSeparator()
                + otazka.getOdpovedB()
                + System.lineSeparator()
                + otazka.getOdpovedC()
                + System.lineSeparator()
                + otazka.getOdpovedD());
        pocetOtazek++;
        cisloOtazkyLbl.setText(Integer.toString(pocetOtazek) + ".");

    }

    @FXML
    void vybratA(ActionEvent event) {
        kliknutiNaTlacitko(MoznostOdpovedi.A);
    }

    @FXML
    void vybratB(ActionEvent event) {
        kliknutiNaTlacitko(MoznostOdpovedi.B);
    }

    @FXML
    void vybratC(ActionEvent event) {
        kliknutiNaTlacitko(MoznostOdpovedi.C);
    }

    @FXML
    void vybratD(ActionEvent event) {
        kliknutiNaTlacitko(MoznostOdpovedi.D);
    }

    private void kliknutiNaTlacitko(MoznostOdpovedi typTlacitka) {
        if (aktualniOtazka == null) {
            return;
        }

        if (zodpovezeneOtazky == Poskytovatel.POSKYTOVATEL.getDostupneOtazky().size()) {
            return;
        }

        if (typTlacitka == aktualniOtazka.getSpravnaOdpoved()) {
            System.out.println("SPRAVNA ODPOVED!");
            pocetBodu++;

            //bodyLbl.setText(Double.toString(pocetBodu));      
        } else {
            System.out.println("Spatna odpoved :(");
        }

        zodpovezeneOtazky++;
        zodpovezeneLbl.setText(Integer.toString(zodpovezeneOtazky));
        vypocet();

        bodyLbl.setText(Double.toString(procenticka) + " %");
        genOtazBtn.fire();

    }

    public void vypocet() {
        System.out.println(pocetBodu);
        System.out.println((pocetBodu / (double) zodpovezeneOtazky) * 100D);
        this.procenticka = (pocetBodu / (double) zodpovezeneOtazky) * 100D;
        this.procenticka = (double) Math.round(procenticka * 100) / 100;

    }

    @FXML
    private Label jmenoLbl;

    @FXML
    private Label predmetLbl;

    @FXML
    private Label cisloOtazkyLbl;

    @FXML
    private Label bodyLbl;

    @FXML
    private Label zodpovezeneLbl;

    @FXML
    private Button ukoncitBtn;


    @FXML
    void zpatkyNaVyber(ActionEvent event) throws IOException {
        if (stage == null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/VyberZakaFXML.fxml"));
            stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.getIcons().add(new Image("/obrazky/ikona.png"));//chtělo by aby stačilo udělat jednou na celý program a ne pokaždé
            stage.setTitle("Zkoušecí program");
        }

        stage.show();
        Stage stage = (Stage) zpetBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ukoncitZkouseni(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //získat nějak všechny proměnné z minule ať může generovat otázky ze správného texťáku
        genOtazBtn.fire();//vygeneruje otázku nahned bez kliknutí tlačítka, ale hodí to tam že všechny otázky byly použity, takže na to sa chce podívat

        String jmenoZkouseneho = Poskytovatel.POSKYTOVATEL.getStudent().getName();
        String jmenoPr = Poskytovatel.POSKYTOVATEL.getPredmet().getJmeno();
        jmenoLbl.setText(jmenoZkouseneho);
        predmetLbl.setText(jmenoPr);
    }

}
