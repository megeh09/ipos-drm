/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.entity.enums;

/**
 *
 * @author megeh
 */
public enum Warehouse {
    BODEGA_MAIN("BODEGA MAIN"),
    BODEGA_1("BODEGA 1"),
    BODEGA_2("BODEGA 2");

    private final String name;

    private Warehouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
