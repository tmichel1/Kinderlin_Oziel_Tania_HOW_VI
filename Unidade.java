//ternidade-22-1-ads/app/src/main/java/br/com/dlweb/lvm/unidade/Unidade.java 

package br.com.dlweb.lvm.unidade;

public class unidade implements java.io.Serializable {

    private int id;
    private String nome;
    

    public unidade() {}

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


}