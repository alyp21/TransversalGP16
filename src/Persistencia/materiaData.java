
package Persistencia;

import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class materiaData {

    private final Connection con;

    public materiaData(Connection con) {
        this.con = con;
        
    }
    
    public void guardarMateria(Materia materia){
        String sql= "INSERT INTO alumno (nombreMateria, anioMateria, estadoMateria) " +
            "VALUES (?,?,?)";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, materia.getNombreMateria());
                ps.setInt(2, materia.getAnioMateria());
                ps.setBoolean(3, materia.isEstadoMateria());
        
                int registros = ps.executeUpdate();
                System.out.println("Alumno cargado correctamente. Registros insertados: " + registros);
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }
    public void buscarMateria(String nombreMateria){
        String sql = "SELECT * FROM materia WHERE nombreMateria = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreMateria);
            
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
                Materia materia = new Materia();
                System.out.println("ID:" + resultado.getInt("idMateria"));
                System.out.println("Nombre:" + resultado.getString("nombreMateria"));
                System.out.println("Anio:" + resultado.getInt("anioMateria"));
                System.out.println("Estado:" + resultado.getBoolean("estadoMateria"));
            }else {
                System.out.println("No hay ninguna materia con este nombre.");
            }
            resultado.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
    }
    public void verMaterias(){
        String sql = "SELECT * FROM materia";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            
            while (resultado.next()){
                System.out.println("ID:" + resultado.getInt("idMateria"));
                System.out.println("Nombre:" + resultado.getString("nombreMateria"));
                System.out.println("Anio:" + resultado.getInt("anioMateria"));
                System.out.println("Estado:" + resultado.getBoolean("estadoMateria"));
            }
            resultado.close();
            ps.close();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error de conexion: " + ex.getMessage());
        }
    }
}