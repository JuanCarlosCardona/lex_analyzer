package com.analyzer.lexical_analyzer;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LexController implements Initializable {

    @FXML private Button btnAnalyze;
    @FXML private TreeView<String> filesView;

   @FXML private TableView<Lex> tokenTable;
   @FXML private TextArea txtCode;

   @FXML private TableColumn<String, String> columnLexeme;
   @FXML private  TableColumn<String, String> columnToken;
   @FXML private TableColumn<String, String> columnPattern;

    private static String file;

    public LexController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnLexeme.setCellValueFactory(new PropertyValueFactory<>("lexeme"));
        columnToken.setCellValueFactory(new PropertyValueFactory<>("token"));
        columnPattern.setCellValueFactory(new PropertyValueFactory<>("pattern"));

        txtCode.setEditable(false);

        TreeItem<String> filesRoot = new TreeItem<>("Files");
        filesRoot.setExpanded(true);

        EventHandler<MouseEvent> file1Event = (MouseEvent event) -> {
            try {

                Node node = event.getPickResult().getIntersectedNode();

                if (!(node instanceof Text ||  (node instanceof TreeCell && ((TreeCell) node).getText() != null))){
                    return;
                }

                file = (String) ((TreeItem<?>) filesView.getSelectionModel().getSelectedItem()).getValue();

                FileReader reader = new FileReader(Paths.PATH.getPath() + file);
                BufferedReader bufferedReader = new BufferedReader(reader);

                StringBuilder contents = new StringBuilder();


                while (bufferedReader.ready()){
                    contents.append(bufferedReader.readLine()).append("\n");
                }

                reader.close();
                bufferedReader.close();

                txtCode.setText(contents.toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };


        TreeItem<String> file1 = new TreeItem<>("test1.lya");
        TreeItem<String> file2 = new TreeItem<>("test2.lya");
        TreeItem<String> file3 = new TreeItem<>("test3.lya");
        TreeItem<String> file4 = new TreeItem<>("test4.lya");

        filesRoot.getChildren().addAll(List.of(file1, file2,file3,file4));
        filesView.setRoot(filesRoot);
        filesView.addEventHandler(MouseEvent.MOUSE_CLICKED,file1Event);

        btnAnalyze.setOnAction(event -> {
            tokenTable.getItems().clear();
            try {
                List<String> commands = new ArrayList<>();
                commands.add("python");
                commands.add(Paths.EXECUTE_FILE.getPath());
                commands.add(Paths.PATH.getPath() + file);

                ProcessBuilder pb =  new ProcessBuilder(commands)
                        .redirectErrorStream(true);

                System.out.println(pb.command());
                Process p = pb.start();

                FileReader fileReader = new FileReader("output.txt");
                BufferedReader std = new BufferedReader(fileReader);

                while (std.ready()){
                    String[] line = std.readLine().split("\t");
                    Lex tmpLex = new Lex(line[0], line[1], line[2]);
                    System.out.println(tmpLex);
                    tokenTable.getItems().add(tmpLex);
                }
                std.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });



    }
}