package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Potter
 */

@Named(value = "controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {
    
    @EJB
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;
    @EJB
    private ServicoDAO<Servico> daoServico;
    private Boolean editando;
    @EJB
    private ProdutoDAO<Produto> daoProduto;
    private Produto produto;
    private Boolean editandoProduto;
    
    public ControlePessoa() {
        editando = false;
        editandoProduto = false;
    }
    
    public String listar() {
        editando = false;
        return "/privado/pessoa/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Pessoa();
        editando = true;
        editandoProduto = false;
    }
    
    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoProduto = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void novaProduto() {
        editandoProduto = true;
    }
    
    public void salvarProduto() {
        if (!objeto.getFavoritos().contains(produto)) {
            objeto.getFavoritos().add(produto);
            Util.mensagemInformacao("Produto adicionado com sucesso!");
        } else {
            Util.mensagemErro("A pessoa já possui este produto em seus favoritos!");
        }
        editandoProduto = false;
    }
    
    public void removerProduto(Produto obj) {
        objeto.getFavoritos().remove(obj);
        Util.mensagemInformacao("Produto removido com sucesso!");
    }
    
    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }
    
    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }
    
    public Pessoa getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }
    
    public ServicoDAO<Servico> getDaoServico() {
        return daoServico;
    }
    
    public void setDaoServico(ServicoDAO<Servico> daoServico) {
        this.daoServico = daoServico;
    }
    
    public Boolean getEditando() {
        return editando;
    }
    
    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }
    
    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public Boolean getEditandoProduto() {
        return editandoProduto;
    }
    
    public void setEditandoProduto(Boolean editandoProduto) {
        this.editandoProduto = editandoProduto;
    }
}
