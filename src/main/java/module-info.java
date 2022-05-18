module com.example.pjkt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pjkt to javafx.fxml;
    exports com.example.pjkt;
}