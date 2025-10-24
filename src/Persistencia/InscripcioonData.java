
package Persistencia;

import Modelo.Alumno;
import org.mariadb.jdbc.Connection;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcioonData {
    
    private Connection con = null;
    
    public InscripcioonData() {
    }
    
    private MateriaData md;
    private AlumnooData alum;
    
    public InscripcioonData(Connection con) {
        this.con = con;
        this.md = new MateriaData(con);
        this.alum = new AlumnooData(con);
    }
    
    public void guardarIncripcion(Inscripcion insc) {
        
        String sql="INSERT INTO inscripcion (idAlumno, idMateria, nota) VALUES (?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            if (insc.getAlumno() == null || insc.getMateria() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno y una materia antes de inscribir.");
            return;
        }
        if (insc.getAlumno().getId()<= 0) {
            JOptionPane.showMessageDialog(null, "El alumno no está registrado en la base de datos.");
            return;
        }
        if (insc.getMateria().getIdMateria() <= 0) {
            JOptionPane.showMessageDialog(null, "La materia no está registrada en la base de datos.");
            return;
        }
            ps.setInt(1, insc.getAlumno().getId());
            ps.setInt(2, insc.getMateria().getIdMateria());
            ps.setDouble(3,insc.getNota());
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if (rs.next()){
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion Registrada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
    }
    
    public boolean actualizarNota(int idAlumno, int idMateria, double nota){
        
        String sql="UPDATE inscripcion SET nota = ? WHERE idAlumno= ? AND idMateria = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas= ps.executeUpdate();
            
            if(filas>0){
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }return false;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        
        String sql="DELETE FROM inscripcion WHERE idAlumno= ? and idMateria= ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int filas=ps.executeUpdate();
            if(filas>0){
                JOptionPane.showMessageDialog(null, "Inscripcion Borrada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
    }
    
    public List<Inscripcion> obtenerInscripciones() {
    ArrayList<Inscripcion> cursadas = new ArrayList<>();
    String sql = "SELECT * FROM inscripcion";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Inscripcion insc = new Inscripcion();
            insc.setIdInscripcion(rs.getInt("idInscripto"));
            
            // (Estas líneas ya las tenías bien)
            Alumno alu = alum.buscarAlumno(rs.getInt("idAlumno"));
            Materia mat = md.buscarMateriaPorId(rs.getInt("idMateria"));
            
            insc.setAlumno(alu);
            insc.setMateria(mat);
            insc.setNota(rs.getDouble("nota"));
            cursadas.add(insc);
            }
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion: " + ex.getMessage());
        }
    return cursadas;
    }    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
        ArrayList<Inscripcion> cursadas=new ArrayList<>();
        String sql="SELECT * FROM inscripcion WHERE idAlumno= ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion insc= new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno alu = alum.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateriaPorId(rs.getInt("idMateria")); 
            
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return cursadas;
    }
    
    public List<Materia> obtenerMateriasCursadas(int idAlumno){
        ArrayList<Materia> materias=new ArrayList<>();
        
        String sql= "SELECT m.idMateria, m.nombreMateria, m.anioMateria, m.estadoMateria" +
        " FROM inscripcion i, materia m "+
        " WHERE i.idMateria = m.idMateria" +
        " AND i.idAlumno = ?";

        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia materia= new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombreMateria(rs.getString("nombreMateria"));
                materia.setAnioMateria(rs.getInt("anioMateria"));
                materia.setEstadoMateria(rs.getBoolean("estadoMateria"));
                materias.add(materia);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return materias;
    }
    public List<Materia> obtenerMateriasNoCursadas(int idAlumno){
        ArrayList<Materia> materias=new ArrayList<>();
        String sql= "SELECT * FROM materia WHERE estadoMateria = 1 AND idMateria NOT IN "
        + "(SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia materia= new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombreMateria(rs.getString("nombreMateria"));
                materia.setAnioMateria(rs.getInt("anioMateria"));
                materia.setEstadoMateria(rs.getBoolean("estadoMateria"));
                materias.add(materia);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return materias;
    }
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        ArrayList<Alumno> alumnosMateria= new ArrayList<>();
        String sql= "SELECT a.idAlumno, dni, nombre, apellido, fechaNacimiento, estado "
                + "FROM inscripcion i, alumno a"
                + " WHERE i.idAlumno = a.idAlumno AND i.idMateria = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Alumno alumno=new Alumno();
                alumno.setId(rs.getInt("idAlumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnosMateria.add(alumno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return alumnosMateria;
    }
}
