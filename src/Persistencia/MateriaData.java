
package Persistencia;

import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MateriaData {

    private Connection con = null;

    public MateriaData(Connection con) {
        this.con = con;
    }

    public MateriaData() {
    }
    
    public void guardarMateria(Materia materia){
        String sql= "INSERT INTO materia (nombreMateria, anioMateria, estadoMateria) " +
            "VALUES (?,?,?)";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, materia.getNombreMateria());
                ps.setInt(2, materia.getAnioMateria());
                ps.setBoolean(3, materia.isEstadoMateria());
                int registros = ps.executeUpdate();
                System.out.println("Materia cargada correctamente. Registros insertados: " + registros);
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }
    public Materia buscarMateria(String nombreMateria){
        String sql = "SELECT * FROM materia WHERE nombreMateria = ?";
        Materia materia = null;
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreMateria);
            
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
                materia = new Materia();
                System.out.println("Id:" + resultado.getInt("idMateria"));
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
        return materia;
    }
    public List <Materia> verMaterias(){
        List <Materia> materias = new ArrayList();
        String sql = "SELECT * FROM materia";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            
            while (resultado.next()){
                Materia m = new Materia ();
                m.setNombreMateria(resultado.getString("nombreMateria"));
                m.setAnioMateria(resultado.getInt("anioMateria"));
                m.setEstadoMateria(resultado.getBoolean("estadoMateria"));
                materias.add(m);
            }
            resultado.close();
            ps.close();
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error de conexion: " + ex.getMessage());
        }
        return materias;
    }
    public void actualizarMateria(Materia m){
        String sql = "UPDATE materia SET nombreMateria = ? , anioMateria = ? , estadoMateria = ? WHERE nombreMateria = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, m.getNombreMateria());
            ps.setInt(2, m.getAnioMateria());
            ps.setBoolean(3, m.isEstadoMateria());
            ps.setString(4, m.getNombreMateria());
            
            int registros = ps.executeUpdate();
            
            if (registros > 0) {
                System.out.println("La materia fue actualizada con exito. Registros actualizados: " + registros);
        } else {
                System.out.println("No se encontro ninguna materia con este nombre.");
        }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: "+ ex.getMessage());
        }
    }
    public void eliminarMateria (String nombreMateria){
        String sql = "DELETE FROM materia WHERE nombreMateria = ? ";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreMateria);
            int registros = ps.executeUpdate();
            System.out.println("La materia fue eliminada correctamente. Registros eliminados: " + registros);
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
    }
    public void bajaLogicaMateria(String nombreMateria){
        String sql = "UPDATE materia SET estadoMateria = 0, WHERE nombreMateria = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setBoolean(1, false);
            ps.setString(2, nombreMateria);
            
            int registros = ps.executeUpdate();
            
            if (registros > 0) {
                System.out.println("La materia fue dada de baja con exito. Registros actualizados: " + registros);
        } else {
                System.out.println("No se encontro ninguna materia con este nombre.");
        
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
    }
    public void altaLogicaMateria(String nombreMateria){
        String sql = "UPDATE materia SET estadoMateria = 1 , WHERE nombreMateria = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setBoolean(1, true);
            ps.setString(2, nombreMateria);
            
            int registros = ps.executeUpdate();
            
            if (registros > 0) {
                System.out.println("La materia fue dada de alta con exito. Registros actualizados: " + registros);
        } else {
                System.out.println("No se encontro ninguna materia con este nombre.");
        
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
    }
}