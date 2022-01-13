module main.gestionhotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens main.gestionhotel to javafx.fxml;
    exports main.gestionhotel;
    exports main.gestionhotel.Authentification;
    opens main.gestionhotel.Authentification to javafx.fxml;

    opens main.gestionhotel.ClassesPersistants to javafx.base;
    exports main.gestionhotel.EspaceClient;
    opens main.gestionhotel.EspaceClient to javafx.fxml;
    exports main.gestionhotel.EspaceEmploye;
    opens main.gestionhotel.EspaceEmploye to javafx.fxml;

}