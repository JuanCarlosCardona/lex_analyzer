module com.analyzer.lexical_analyzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires jython.slim;


    opens com.analyzer.lexical_analyzer to javafx.fxml;
    exports com.analyzer.lexical_analyzer;
}