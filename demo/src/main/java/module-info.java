module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.view to javafx.fxml;
    exports org.example.view;
}