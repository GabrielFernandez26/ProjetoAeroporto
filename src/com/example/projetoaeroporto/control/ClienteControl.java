package src.com.example.projetoaeroporto.control;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.com.example.projetoaeroporto.DAO.ClienteDAO;
import src.com.example.projetoaeroporto.DAO.ClienteDAOImplements;
import src.com.example.projetoaeroporto.entity.Cliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteControl {

        private ClienteDAO clienteDAO = new ClienteDAOImplements();

        public IntegerProperty id = new SimpleIntegerProperty(0);
        public StringProperty nome = new SimpleStringProperty("");
        public ObjectProperty nascimento = new SimpleObjectProperty(null);
        public IntegerProperty cpf = new SimpleIntegerProperty(0);
        private List<Cliente> cliente = new ArrayList<Cliente>();
        private ObservableList<Cliente> c1 = FXCollections.observableArrayList();

        public ClienteControl() {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public void pesquisar() {
            cliente.clear();
            List<Cliente> encontrados = clienteDAO.pesquisarPorId( id.get() );
            cliente.addAll( encontrados );
            if (!cliente.isEmpty()) {
                fromEntity(cliente.get(0));
            }
        }

        public Cliente toEntity() {
            Cliente c = new Cliente();
            c.setId(id.get());
            c.setNome(nome.get());
            c.setNascimento((LocalDate) nascimento.get());
            c.setCpf(cpf.get());
            return c;
        }

        public void fromEntity(Cliente c) {
            id.set(c.getId());
            nome.set(c.getNome());
            nascimento.set(c.getNascimento());
            cpf.set(c.getCpf());
        }
        public void salvar() {
            Cliente cliente = toEntity();
            if (cliente.getId() == 0) {
                clienteDAO.salvar(cliente);
            } else {
                clienteDAO.atualizar( id.get(), cliente );
            }
        }

        public void alterar() {
            Cliente cliente = toEntity();
            if (cliente.getId() == 0) {
                clienteDAO.salvar(cliente);
            } else {
                clienteDAO.atualizar( id.get(), cliente );
            }
        }
        public void remover(int id) {
            clienteDAO.remover(id);
        }
        public ObservableList<Cliente> getLista() {
            return c1;
        }

}
