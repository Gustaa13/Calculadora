module com.github.gustaa13 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.gustaa13 to javafx.fxml;
    exports com.github.gustaa13;
}
