
package br.com.dlweb.Objeto.conteudo;

public class conteudo implements java.io.Serializable {

    private int id;
    private int id_unidade;
        private String nome;
    

    public conteudo() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(int id_unidade) {
        this.id_unidade = id_unidade;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  

}