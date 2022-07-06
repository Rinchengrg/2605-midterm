module com.donor.donormanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.donor.donormanagement to javafx.fxml;
    exports com.donor.donormanagement;
}