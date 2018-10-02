package coutinhodeveloper.com.coutinmessenger.model;

/** Created by Guilherme Coutinho
 *  on 01/10/2018
 */

public class Mensagem {
    private String idUsuario;
    private String idMensagem;

    public Mensagem() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(String idMensagem) {
        this.idMensagem = idMensagem;
    }
}
