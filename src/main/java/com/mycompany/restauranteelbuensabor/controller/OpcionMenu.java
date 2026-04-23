package com.mycompany.restauranteelbuensabor.controller;

public enum OpcionMenu {
    VER_CARTA(1),
    AGREGAR_PRODUCTO(2),
    VER_PEDIDO(3),
    GENERAR_FACTURA(4),
    NUEVA_MESA(5),
    SALIR(0);

    private final int codigo;

    OpcionMenu(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static OpcionMenu fromCodigo(int codigo) {
        for (OpcionMenu opcion : values()) {
            if (opcion.codigo == codigo) {
                return opcion;
            }
        }
        return null;
    }
}