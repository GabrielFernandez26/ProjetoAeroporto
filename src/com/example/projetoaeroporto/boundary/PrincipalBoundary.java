package com.example.projetoaeroporto.boundary;
import com.example.projetoaeroporto.Telas.ExecutorComandos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application implements ExecutorComandos {
    private VooBoundary vooBoundary = new VooBoundary();

    private BorderPane bp = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scn = new Scene(bp, 1024, 768);

        MenuBar menuBar = new MenuBar();

        Menu mnuArquivo = new Menu("Arquivo");
        Menu mnuVoos = new Menu("Cadastro");


        menuBar.getMenus().addAll(mnuArquivo, mnuVoos);

        MenuItem mnuItemSair = new MenuItem("Sair");
        mnuItemSair.setOnAction((e) -> {
            executarComando("SAIR");
        });
        MenuItem mnuItemPets = new MenuItem("Vôos");
        mnuItemPets.setOnAction((e) -> {
            executarComando("TELA-VOO");
        });


        vooBoundary.adicionarExecutor(this);


        mnuArquivo.getItems().addAll(mnuItemSair);
        mnuVoos.getItems().addAll(mnuItemPets);


        bp.setTop(menuBar);

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }

    @Override
    public void executarComando(String comando) {
        if ("TELA-VOO".equals(comando)) {
            bp.setCenter( vooBoundary.render() );
        } else if ("SAIR".equals(comando)) {
            Platform.exit();
            System.exit(0);
        }
    }
}