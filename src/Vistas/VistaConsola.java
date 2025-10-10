
//package Vistas;
//
//import Modelo.Alumno;
//import Modelo.Conexion;
//import Modelo.Materia;
//import org.mariadb.jdbc.Connection;
//import Persistencia.alumnoData;
//import Persistencia.materiaData;
//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class VistaConsola {
//
////    esto lo probamos antes de hacer las vistas con el frame, queda por si acaso ;)
////    todo funciona (o deberia)
//    
//    public static void main(String[] args) {
//        
//        Connection con = (Connection) Conexion.getConexion();
//        Scanner sc = new Scanner(System.in);
//        int opcion;
//        
//        if (con != null) {
//            System.out.println("Conexion establecida con la base de datos.");
//            alumnoData alumData= new alumnoData(con);
//            materiaData matData = new materiaData(con);
//            
//            
//            do {
//                System.out.println("1. Agregar alumno.");
//                System.out.println("2.Buscar alumno.");
//                System.out.println("3.Ver alumnos.");
//                System.out.println("4.Actualizar alumno.");
//                System.out.println("5.Eliminar alumno.");
//                System.out.println("6.Dar de baja un alumno.");
//                System.out.println("7.Dar de alta un alumno.");
//                System.out.println("--------------------------");
//                System.out.println("8. Agregar materia.");
//                System.out.println("9.Buscar alumno.");
//                System.out.println("10.Ver alumnos.");
//                System.out.println("11.Actualizar alumno.");
//                System.out.println("12.Eliminar alumno.");
//                System.out.println("13.Dar de baja un alumno.");
//                System.out.println("14.Dar de alta un alumno.");
//                System.out.println("15.Salir del menu.");
//                System.out.print("Elija una opcion: ");
//                opcion = sc.nextInt();
//                
//            switch (opcion){
//                case 1: 
//                    System.out.println("Se necesitan los siguientes datos del alumno: ");
//                    
//                    System.out.print("Ingrese dni: ");
//                    int dni = sc.nextInt();
//                    sc.nextLine(); 
//
//                    System.out.print("Ingrese apellido: ");
//                    String apellido = sc.nextLine();
//
//                    System.out.print("Ingrese nombre: ");
//                    String nombre = sc.nextLine();
//
//                    System.out.print("Ingrese fecha de nacimiento (yyyy-mm-dd): ");
//                    String fecha = sc.nextLine();
//                    LocalDate fechaNacimiento = LocalDate.parse(fecha);
//
//                    System.out.print("Esta activo? (true/false): ");
//                    boolean estado = sc.nextBoolean();
//                    Alumno alumno1=new Alumno(dni,apellido,nombre,fechaNacimiento,estado);
//                    alumData.guardarAlumno(alumno1);
//                    break;
//                case 2:
//                    System.out.print("Ingresa el dni del alumno a buscar: ");
//                    int dniBuscado = sc.nextInt();
//                    alumData.buscarAlumno(dniBuscado);
//                    break;
//                    
//                case 3:
//                    alumData.verAlumnos();
//                    break;
//                    
//                case 4:
//                    System.out.print("Ingresa el dni del alumno a actualizar:");
//                    dni = sc.nextInt();
//                    sc.nextLine();
//                    System.out.print("Ingresa el nuevo apellido: ");
//                    apellido = sc.nextLine();
//                    System.out.print("Ingresa el nuevo nombre: ");
//                    nombre = sc.nextLine();
//                    Alumno alumno1 = new Alumno (dni, nombre, apellido, fechaNacimiento, estado);
//                    alumData.actualizarAlumno(alumno1);
////                    break;
//                    
//                case 5:
//                    System.out.print("Ingresa el dni del alumno a eliminar: ");
//                    int eliminado = sc.nextInt();
//                    sc.nextLine();
//                    alumData.eliminarAlumno(eliminado);
//                    break;
//                    
//                case 6:
//                    System.out.print("Ingresa el dni del alumno a dar de baja: ");
//                    dni = sc.nextInt();
//                    alumData.bajaLogica(dni);
//                    break;
//                case 7:
//                    System.out.println("Ingresa el dni del alumno a dar de alta: ");
//                    dni = sc.nextInt();
//                    alumData.altaLogica(dni);
//                    break;
//                case 8: 
//                    System.out.println("Se necesitan los siguientes datos de la materia: ");
//
//                    System.out.print("Ingrese el nombre: ");
//                    String nombreMateria = sc.nextLine();
//                    sc.nextLine();
//                    System.out.print("Ingrese el anio de la materia: ");
//                    int anioMateria = sc.nextInt();
//                    sc.nextLine();
//
//                    System.out.print("La materia esta activa? (true/false): ");
//                    boolean estadoMateria = sc.nextBoolean();
//                    
//                    Materia materia = new Materia(nombreMateria, anioMateria, estadoMateria);
//                    matData.guardarMateria(materia);
//                    break;
//                    
//                case 9:
//                    System.out.print("Ingresa el nombre de la materia a buscar: ");
//                    String materiaBuscada = sc.nextLine();
//                    matData.buscarMateria(materiaBuscada);
//                    break;
//                    
//                case 10:
//                    matData.verMaterias();
//                    break;
//                    
//                case 11:
//                    System.out.print("Ingresa el nombre de la materia a actualizar:");
//                    String nombreMateriaActual = sc.nextLine();
//                    System.out.print("Ingresa el nuevo nombre: ");
//                    String nombreMatActualizado = sc.nextLine();
//                    System.out.print("Ingresa el nuevo anio: ");
//                    int anioMatActualizado = sc.nextInt();
//                    matData.actualizarMateria(nombreMateriaActual, nombreMatActualizado, anioMatActualizado);
//                    break;
//                    
//                case 12:
//                    System.out.print("Ingresa el nombre de la materia a eliminar: ");
//                    String matEliminada = sc.nextLine();
//                    matData.eliminarMateria(matEliminada);
//                    break;
//                    
//                case 13:
//                    System.out.print("Ingresa el nombre de la materia a dar de baja: ");
//                    nombreMateria = sc.nextLine();
//                    matData.bajaLogicaMateria(nombreMateria);
//                    break;
//                case 14:
//                    System.out.println("Ingresa el nombre de la materia a dar de alta: ");
//                    nombreMateria = sc.nextLine();
//                    matData.altaLogicaMateria(nombreMateria);
//                    break;
//                case 15:
//                    System.out.println("Estas saliendo del menu...");
//            }
//        } while (opcion != 15);
//              sc.close();
//        } else {
//            System.out.println("No se pudo establecer la conexion.");
//        }  
//    }
//}
