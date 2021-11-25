package src.com.example.projetoaeroporto.boundary;

import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import src.com.example.projetoaeroporto.Telas.TelaReserva;
import src.com.example.projetoaeroporto.control.ReservaControl;
import src.com.example.projetoaeroporto.entity.Reserva;

public class ReservaBoundary extends TelaReserva {
    private TextField txtId = new TextField();
    private TextField txtClienteId = new TextField();
    private TextField txtVooId = new TextField();

    private TableView<Reserva> table=new TableView<>();


    private final ReservaControl control = new ReservaControl();

    private Button btnSalvar= new Button("Salvar");
    private Button btnRemover= new Button("Remover");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAtualizar = new Button("Atualizar");

    private void criarTabela(){
        TableColumn<Reserva,Integer> col1=new TableColumn<>("Id");
        col1.setCellValueFactory(
                new PropertyValueFactory<Reserva,Integer>("id")
        );
        TableColumn<Reserva,Integer> col2=new TableColumn<>("Cliente Id");
        col2.setCellValueFactory(
                new PropertyValueFactory<Reserva,Integer>("cliente id")
        );
        TableColumn<Reserva,Integer> col3=new TableColumn<>("Voo Id");
        col3.setCellValueFactory(
                new PropertyValueFactory<Reserva,Integer>("voo id")
        );

        TableColumn<Reserva, String> col4 = new TableColumn<>("Ações");
        col4.setCellFactory( (tbcol) -> {
                    Button btnRemover = new Button("Remover");
                    TableCell<Reserva, String> tcell = new TableCell<Reserva, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnRemover.setOnAction( (e) -> {
                                    Reserva r = getTableView().getItems().get(getIndex());
                                    control.remover(r.getId());
                                });
                                setGraphic(btnRemover);
                                setText(null);
                            }
                        }
                    };
                    return tcell;
                }
        );
        table.getSelectionModel().selectedItemProperty().addListener( (obs, old, novo) -> {
                    control.fromEntity(novo);
                }
        );
        table.getColumns().addAll(col1, col2, col3, col4);
        table.setItems(control.getLista());
    }
    @Override
    public Pane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();
        txtId.setEditable(false);
        criarTabela();

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);


        Bindings.bindBidirectional(txtId.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtClienteId.textProperty(), control.cliente, new NumberStringConverter());
        Bindings.bindBidirectional(txtVooId.textProperty(), control.voo, new NumberStringConverter());

        panCampos.add(new Label("Id:"), 0, 0);
        panCampos.add(txtId, 1, 0);
        panCampos.add(new Label("Cliente Id:"), 0, 1);
        panCampos.add(txtClienteId, 1, 1);
        panCampos.add(new Label("Voo Id:"), 0, 2);
        panCampos.add(txtClienteId, 1, 2);


        panCampos.add(btnSalvar, 0, 5);
        panCampos.add(btnRemover,1,5);
        panCampos.add(btnPesquisar, 2, 5);
        panCampos.add(btnAtualizar,3,5);

        btnSalvar.setOnAction((e) -> {
            control.reservar();
            new Alert(Alert.AlertType.INFORMATION, "Reserva feita com sucesso");
        });

        btnAtualizar.setOnAction((e)->{
            control.atualizar();
            new Alert(Alert.AlertType.INFORMATION, "Reserva feita com sucesso");
        });

        btnPesquisar.setOnAction( (e) -> {
            control.pesquisar();
        });

        return panPrincipal;
    }
}
