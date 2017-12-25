/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Alumno
 */
public class Pelicula {
    private int id_categoria;
    private int codigo;
    private String nombre;
    private int precio;
    private String formato;
    private boolean activo;

    public Pelicula() {
        
    }

    public Pelicula(int id_categoria, int codigo, String nombre, int precio, String formato, boolean activo) {
        this.id_categoria = id_categoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.formato = formato;
        this.activo = activo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}