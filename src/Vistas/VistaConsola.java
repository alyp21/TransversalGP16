
package Vistas;

import Modelo.Alumno;
import Modelo.Conexion;
import org.mariadb.jdbc.Connection;
import Persistencia.alumnoData;
import java.time.LocalDate;
import java.util.Scanner;

public class VistaConsola {

    public static void main(String[] args) {
        
        Connection con = (Connection) Conexion.getConexion();
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        if (con != null) {
            System.out.println("Conexion establecida con la base de datos.");
            alumnoData alumData= new alumnoData(con);
            
            
            do {
                System.out.println("1. Agregar alumno.");
                System.out.println("2.Buscar alumno.");
                System.out.println("3.Ver alumnos.");
                System.out.println("4.Actualizar alumno.");
                System.out.println("5.Eliminar alumno.");
                System.out.println("6.Dar de baja un alumno.");
                System.out.println("7.Dar de alta un alumno.");
                System.out.println("8.Salir del menu.");
                System.out.print("Elija una opcion: ");
                opcion = sc.nextInt();
                
            switch (opcion){
                case 1: 
                    System.out.println("Se necesitan los siguientes datos del alumno: ");
                    
                    System.out.print("Ingrese dni: ");
                    int dni = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese fecha de nacimiento (yyyy-mm-dd): ");
                    String fecha = sc.nextLine();
                    LocalDate fechaNacimiento = LocalDate.parse(fecha);

                    System.out.print("Esta activo? (true/false): ");
                    boolean estado = sc.nextBoolean();
                    Alumno alumno1=new Alumno(dni,apellido,nombre,fechaNacimiento,estado);
                    alumData.guardarAlumno(alumno1);
                    break;
                case 2:
                    System.out.print("Ingresa el dni del alumno a buscar: ");
                    int dniBuscado = sc.nextInt();
                    alumData.buscarAlumno(dniBuscado);
                    break;
                    
                case 3:
                    alumData.verAlumnos();
                    break;
                    
                case 4:
                    System.out.print("Ingresa el dni del alumno a actualizar:");
                    int dniActual = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingresa el nuevo apellido: ");
                    String apellidoActual = sc.nextLine();
                    System.out.print("Ingresa el nuevo nombre: ");
                    String nombreActual = sc.nextLine();
                    alumData.actualizarAlumno(dniActual, apellidoActual, nombreActual);
                    break;
                    
                case 5:
                    System.out.print("Ingresa el dni del alumno a eliminar: ");
                    int eliminado = sc.nextInt();
                    sc.nextLine();
                    alumData.eliminarAlumno(eliminado);
                    break;
                    
                case 6:
                    System.out.print("Ingresa el dni del alumno a dar de baja: ");
                    dni = sc.nextInt();
                    alumData.bajaLogica(dni);
                    break;
                case 7:
                    System.out.println("Ingresa el dni del alumno a dar de alta: ");
                    dni = sc.nextInt();
                    alumData.altaLogica(dni);
                    break;
                case 8:
                    System.out.println("Estas saliendo del menu...");
            }
        } while (opcion != 8);
              sc.close();
        } else {
            System.out.println("No se pudo establecer la conexion.");
        }  
    }
}
