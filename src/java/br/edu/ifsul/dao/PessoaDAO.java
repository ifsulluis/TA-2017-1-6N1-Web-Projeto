
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

@Stateful
public class PessoaDAO<T> extends DAOGenerico<Pessoa> implements Serializable {

    public PessoaDAO(){
        super();
        super.classePersistente = Pessoa.class;
    }
    
    public Pessoa localizaPorNomePessoa(String pessoa){
        Query query = em.createQuery("from Pessoa where uppper(pessoa) = :pessoa");
        query.setParameter("pessoa", pessoa.toUpperCase());;
        Pessoa obj = (Pessoa) query.getSingleResult();
        obj.getFavoritos().size();
        return obj;
    }
            
}
