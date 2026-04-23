package com.mycompany.restauranteelbuensabor.model;

public class Mesa {
    private int numero;
    private boolean ocupada;
    private final Pedido pedido;

    public Mesa() {
        this.pedido = new Pedido();
        this.ocupada = false;
        this.numero = 0;
    }

    public void ocupar(int numeroMesa) {
        this.numero = numeroMesa > 0 ? numeroMesa : 1;
        this.ocupada = true;
    }

    public void liberar() {
        this.ocupada = false;
        this.numero = 0;
        this.pedido.reiniciar();
    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public int getNumero() {
        return numero;
    }

    public Pedido getPedido() {
        return pedido;
    }
}