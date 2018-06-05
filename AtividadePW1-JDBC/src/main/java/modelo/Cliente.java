package modelo;

import java.io.File;
import java.util.Objects;

public class Cliente {
    
    private int id;
    private String nome;
    private String documento;
    private float saldo;
    private boolean ativo;
    
    private File imagem;

    public Cliente(int id, String nome, String documento, float saldo, boolean ativo, String imgPath) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.saldo = saldo;
        this.ativo = ativo;
        this.imagem = new File(imgPath);
    }

    public Cliente(int id, String nome, String documento, float saldo, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.saldo = saldo;
        this.ativo = ativo;
        this.imagem = new File("Default.img");
    }

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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public File getImagem() {
        return imagem;
    }
    
    public String getImgPath(){
        return imagem.getPath();
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }
    
    public void setImagem(String imgPath) {
        this.imagem = new File (imgPath);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.documento);
        hash = 47 * hash + Float.floatToIntBits(this.saldo);
        hash = 47 * hash + (this.ativo ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.imagem);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.saldo) != Float.floatToIntBits(other.saldo)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.imagem, other.imagem)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", documento=" + documento + ", saldo=" + saldo + ", ativo=" + ativo + ", imagem=" + imagem + '}';
    }
    
}
