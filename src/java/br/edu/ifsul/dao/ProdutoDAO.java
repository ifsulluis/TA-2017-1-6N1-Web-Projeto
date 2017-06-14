package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Produto;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class ProdutoDAO<T> extends DAOGenerico<Produto> implements Serializable {

    public ProdutoDAO(){
        super();
        super.classePersistente = Produto.class;
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }    
}
