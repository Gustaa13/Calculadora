module com.github.gustaa13 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.github.gustaa13.application to javafx.fxml;
    opens com.github.gustaa13.controller to javafx.fxml;

    exports com.github.gustaa13.application;
    exports com.github.gustaa13.controller;
    exports com.github.gustaa13.model;
    exports com.github.gustaa13.util;
}
