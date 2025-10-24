module com.purkynka.paintpp.paintpp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.purkynka.paintpp.paintpp to javafx.fxml;
    exports com.purkynka.paintpp.paintpp;
}