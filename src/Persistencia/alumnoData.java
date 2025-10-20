package Persistencia;

import Modelo.Alumno;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AlumnoData {
    
    public AlumnoData() {
    }
    
    private Connection con = null;
   
    public AlumnoData(Connection con) {
        this.con = con;
    }
    
    public void guardarAlumno(Alumno alumno){
        String sql= "INSERT INTO alumno (dni, apellido, nombre,fechaNacimiento, estado) " +
            "VALUES (?,?,?,?,?)";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setInt(1, alumno.getDni());
                ps.setString(2, alumno.getApellido());
                ps.setString(3, alumno.getNombre());
                ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento())); 
                ps.setBoolean(5, alumno.isEstado());
        
                int registros = ps.executeUpdate();
                System.out.println("Alumno cargado correctamente. Registros insertados: " + registros);
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }
    public Alumno buscarAlumno(int id){
        Alumno alumno= null;
        String sql = "SELECT dni, apellido, nombre, fechaNacimiento FROM alumno WHERE idAlumno = ? AND estado= 1";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
                alumno = new Alumno();
                alumno.setId(id);
                alumno.setDni(resultado.getInt("dni"));
                alumno.setApellido(resultado.getString("apellido"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setFechaNacimiento(resultado.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            }else {
                JOptionPane.showMessageDialog(null,"No hay ningun alumno con este id");
            }
            resultado.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
        return alumno;
    }
    public Alumno buscarAlumnoPorDni(int dni){
        Alumno alumno= null;
        String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno WHERE dni = ? AND estado= 1";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
                alumno = new Alumno();
                alumno.setId(resultado.getInt("idAlumno"));
                alumno.setDni(resultado.getInt("dni"));
                alumno.setApellido(resultado.getString("apellido"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setFechaNacimiento(resultado.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            }else {
                JOptionPane.showMessageDialog(null,"No hay ningun alumno con este dni.");
            }
            resultado.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
        return alumno;
    }
    
    public List <Alumno> verAlumnos(){
        List <Alumno> alumnos= new ArrayList();
        String sql = "SELECT * FROM alumno";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            
            while (resultado.next()){
                Alumno a= new Alumno();
                a.setDni(resultado.getInt("dni"));
                a.setApellido(resultado.getString("apellido"));
                a.setNombre(resultado.getString("nombre"));
                a.setFechaNacimiento(resultado.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(resultado.getBoolean("estado"));
                alumnos.add(a);
                
            }
            resultado.close();
            ps.close();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error de conexion: " + ex.getMessage());
        }
       return alumnos;
    }
    
    public void actualizarAlumno(Alumno a){
        String sql = "UPDATE alumno SET apellido = ? , nombre = ?, fechaNacimiento = ?, estado = ? WHERE dni= ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setDate(3, java.sql.Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(4, a.isEstado());
            ps.setInt(5, a.getDni());
            
            int registros = ps.executeUpdate();
            
            if (registros > 0) {
                System.out.println("El alumno fue actualizado con exito. Registros actualizados: " + registros);
        } else {
                System.out.println("No se encontró un alumno con ese DNI.");
        }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: "+ ex.getMessage());
        }
    }
    
    public void eliminarAlumno (int dni){
        String sql = "DELETE FROM alumno WHERE dni = ? ";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            int registros = ps.executeUpdate();
            System.out.println("El alumno fue eliminado correctamente. Registros eliminados: " + registros);
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
    }
    
    public void bajaLogica(int dni){
        String sql = "UPDATE alumno SET estado = 0 WHERE dni = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2, dni);
            
            int registros = ps.executeUpdate();
            
            if (registros > 0) {
                System.out.println("El alumno fue dado de baja con exito. Registros actualizados: " + registros);
        } else {
                System.out.println("No se encontró un alumno con ese DNI.");
        
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
    }
    
    public void altaLogica(int dni){
        String sql = "UPDATE alumno SET estado = 1 WHERE dni = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2, dni);
            
            int registros = ps.executeUpdate();
            
            if (registros > 0) {
                System.out.println("El alumno fue dado de alta con exito. Registros actualizados: " + registros);
        } else {
                System.out.println("No se encontró un alumno con ese DNI.");
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
    }
    }
