
package br.com.dlweb.lvm.objeto;

public class objeto {

    private int id;
    private String nome;
    private String nivel_de_ensino;
    private String endereco_eletronico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getnivel_de_ensino() {
        return nivel_de_ensino;
    }

    public void setnivel_de_ensino(String nivel_de_ensino) {
        this.nivel_de_ensino = nivel_de_ensino;
    }

    public String getendereco_eletronico() {
        return endereco_eletronico;
    }

    public void setendereco_eletronico(String endereco_eletronico) {
        this.endereco_eletronico = endereco_eletronico;
    }
}