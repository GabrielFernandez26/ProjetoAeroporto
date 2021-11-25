package src.com.example.projetoaeroporto.control;

import src.com.example.projetoaeroporto.DAO.VooDAO;
import src.com.example.projetoaeroporto.DAO.VooDAOImplements;
import src.com.example.projetoaeroporto.entity.Voo;
import javafx.beans.property.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VooControl {

    private VooDAO vooDAO = new VooDAOImplements();

    public IntegerProperty id = new SimpleIntegerProperty(0);
    public StringProperty origem = new SimpleStringProperty("");
    public StringProperty destino = new SimpleStringProperty("");
    public ObjectProperty decolagem = new SimpleObjectProperty(LocalDateTime.now());
    public ObjectProperty pouso = new SimpleObjectProperty(LocalDateTime.now());
    public DoubleProperty preco= new SimpleDoubleProperty(0.0);

    private List<Voo> vooGeral = new ArrayList<>();
    private ObservableList<Voo>  voo = FXCollections.observableArrayList();

    public VooControl() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisar() {
        voo.clear();
        List<Voo> encontrados = vooDAO.pesquisarPorId( id.get() );
        voo.addAll( encontrados );
        if (!voo.isEmpty()) {
            fromEntity(voo.get(0));
        }
    }

    public Voo toEntity() {
        Voo v = new Voo();
        v.setId(id.get());
        v.setOrigem(origem.get());
        v.setDestino(destino.get());
        v.setDecolagem((LocalDateTime)decolagem.get());
        v.setPreco(preco.get());
        return v;
    }
    public void atualizar() {
        Voo voo = toEntity();
        if (voo.getId() == 0) {
            vooDAO.salvar(voo);
        } else {
            vooDAO.atualizar( id.get(), voo );
        }
    }
    public void fromEntity(Voo v) {
        id.set(v.getId());
        origem.set(v.getOrigem());
        destino.set(v.getDestino());
        decolagem.set(v.getDecolagem());
        preco.set(v.getPreco());
    }
    


    public void remover(int id) {
        vooDAO.remover(id);
    }

    public void salvar(){
        Voo v = new Voo();
        fromEntity(v);
    }

    public ObservableList<Voo> getLista() {
        return voo;
    }
}
