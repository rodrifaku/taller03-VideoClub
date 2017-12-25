/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Pelicula;
import sql.Conexion;

/**
 *
 * @author Alumno
 */
public class RegistroVideoClub {
    public boolean agregarPelicula(Pelicula pelicula) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "INSERT INTO pelicula(codigo, nombre, precio,"
                    + " id_categoria, formato,"
                    + " activo) VALUES(?,?,?,?,?,?)";
            PreparedStatement ins = conexion.prepareStatement(query);
            if(pelicula.getCodigo()>= 10000 && pelicula.getCodigo()<= 99999){
                ins.setInt(1, pelicula.getCodigo());
                if(pelicula.getNombre().length()>=3){
                    ins.setString(2, pelicula.getNombre());
                    if(pelicula.getPrecio()> 1000){
                        ins.setInt(3, pelicula.getPrecio());
                        ins.setInt(4, pelicula.getId_categoria());
                        ins.setString(5, pelicula.getFormato());
                        ins.setBoolean(6, pelicula.isActivo());
                    }
                }
            } 
            if(ins.executeUpdate()>0){
                return true;
                
            }
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }
    public Pelicula buscarPorCodigo(int codigo) {
        Pelicula pelicula = null;
        try {
            Connection con = Conexion.getConexion();
            String query = "SELECT * FROM pelicula WHERE codigo=? AND activo=1";
            PreparedStatement buscar = con.prepareStatement(query);
            buscar.setInt(1, codigo);
            //OJO todo SELECT REQUIERE UN LLAMADO executeQuery
            ResultSet rs = buscar.executeQuery();
            while (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setCodigo(rs.getInt("codigo"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setPrecio(rs.getInt("precio"));
                pelicula.setId_categoria(rs.getInt("id_categoria"));
                pelicula.setFormato(rs.getString("formato"));
                
           
                
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error la buscar por codigo " + e.getMessage());
        }
        return pelicula;
    }
    
    public boolean EliminarPelicula(Pelicula p){
        
          try {
            Connection con = Conexion.getConexion();
            if (con != null) {
                //estamos conectados
                String sql = "UPDATE pelicula SET "
                        + "activo = '0' "
                        + "WHERE codigo = ?";
                System.out.println(sql);
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, p.getCodigo());
                if (ps.executeUpdate() > 0) {//executeUpdate: termina haciendo un COMMIT implicito
                    return true;
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error Agregar chocolate: " + e.getMessage());
        }
        return false;  
            
      }  

    public boolean ActualizarPelicula(Pelicula p) {
        try {
            Connection con = Conexion.getConexion();
            if (con != null) {
                //estamos conectados
                String sql = "UPDATE pelicula SET "
                        + "nombre = '" + p.getNombre() + "', "
                        + "precio = '" + p.getPrecio()+ "', "
                        + "id_categoria = '" + p.getId_categoria() + "', " 
                        + "formato = '" + p.getFormato() + "' "
                        + "WHERE codigo = '"+p.getCodigo()+"'";
                System.out.println(sql);
                System.out.println(p.getCodigo());
                PreparedStatement ps = con.prepareStatement(sql);
                //ps.setInt(1, );
                if (ps.executeUpdate() > 0) {//executeUpdate: termina haciendo un COMMIT implicito
                    return true;
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error Actualizar Pelicula : " + e.getMessage());
        }
        return false;
    }    

  
    public ResultSet sql_query(String query) {
        try {
           // Conexion  cc = new Conexion();
            Connection cn = Conexion.getConexion();
            if (cn != null) {;
                Statement stm = cn.createStatement();
                ResultSet rs = stm.executeQuery(query);
                cn.close();
                return rs;
            }
        } catch (Exception e) {
           System.out.println("Error query: " + e.getMessage());
        } 
        return null;
    }
    
    public ResultSet peliculasActivasList(){
        return sql_query("SELECT PELICULA.CODIGO, PELICULA.NOMBRE, PELICULA.PRECIO, CATEGORIA.DESCRIPCION,PELICULA.FORMATO\n" +
"FROM PELICULA,CATEGORIA WHERE PELICULA.activo=1 AND PELICULA.ID_CATEGORIA=CATEGORIA.ID" );
    }
}
