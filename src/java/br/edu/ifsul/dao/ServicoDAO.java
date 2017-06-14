package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Servico;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class ServicoDAO<T> extends DAOGenerico<Servico> implements Serializable {

    public ServicoDAO(){
        super();
        super.classePersistente = Servico.class;
    }
}
