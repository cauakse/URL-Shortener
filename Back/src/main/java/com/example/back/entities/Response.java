package com.example.back.entities;

public class Response {
    private String mensagem;
    private boolean erro;
    private Object data;

    public Response(String mensagemErro, boolean erro, Object data) {
        this.mensagem = mensagemErro;
        this.erro = erro;
        this.data = data;
    }

    public Response() {
        this("",false,null);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
