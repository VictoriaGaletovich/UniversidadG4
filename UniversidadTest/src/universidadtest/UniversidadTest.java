/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadtest;

import accesoADatos.AlumnoData;
import accesoADatos.Conexion;
import accesoADatos.MateriaData;
//import accesoadatos.InscripcionData;
import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Manuel Biagioli
 */
public class UniversidadTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlumnoData alu=new AlumnoData();
        for(Alumno alumno:alu.listarAlumnos()){
            System.out.println(alumno.getDni());
            System.out.println(alumno.getApellido());
            System.out.println(alumno.getNombre());
            System.out.println(alumno.getFechaNac());
        }
    }
}
    

//        Connection con = Conexion.getConexion();
//        Alumno a;
//        a = new Alumno(43653968, "Galetovich", "Victoria", LocalDate.of(2001, Month.SEPTEMBER, 12), true);
//        System.out.println(a);
  
      //  Alumno juan=new Alumno(8,15254855,"Lopez","juan martin Pedro",LocalDate.of(2001, 5, 9),true);
       /* AlumnoData ad=new AlumnoData();
        //ad.eliminarAlumno(8);
        Alumno alumnoEncontrado=ad.buscarAlumnoPorDni(15487256);
        if(alumnoEncontrado!=null){
            
          
        System.out.println("dni "+alumnoEncontrado.getDni());
        System.out.println("apellido "+alumnoEncontrado.getApellido());
       
    }
    
        
        //ad.guardarAlumno(juan);
       // ad.modificarAlumno(juan);
       
        
        
       // Alumno juan=new Alumno(6,15254856,"Lopez","jorge Enrique",LocalDate.of(2001, 2, 9),true);
       //AlumnoData ad=new AlumnoData();
      // ad.modificarAlumno(juan);
        // ad.guardarAlumno(jorge);
         //Alumno silvia = new Alumno(32586012,"Vera","silvia",LocalDate.of(2003, 3, 6),true);
        // ad.guardarAlumno(silvia);
        
	/*
	//Agregar materias - prueba de metodo guardarMateria
	Materia lengua = new Materia("Lengua", 1, true);
	MateriaData a = new MateriaData();
	a.guardarMateria(lengua);
	a.guardarMateria(new Materia("Matematica", 1, true));
	//Modificar una materia - prueba de metodo modificarMateria
	MateriaData a = new MateriaData();
	Materia lengua = new Materia(3,"Literatura", 2, true);
	a.modificarMateria(lengua);
	//Eliminar una materia - prueba metodo eliminarMateria
	MateriaData a = new MateriaData();
	a.eliminarMateria(3);
	//Buscar materia por id - prubea metodo buscarMateria
	MateriaData a = new MateriaData();
	Materia materiaEncontrada = a.buscarMateria(4);
	System.out.println(materiaEncontrada.toString());
	 */


//********************************** I N S C R I P C I O N **********************************       
//************************************** METODOS  CRUD **************************************
//*************************************** C R E A T E ***************************************
//                 *****************************************************

//        AlumnoData ad = new AlumnoData();
//        MateriaData md = new MateriaData();
//        InscripcionData id = new InscripcionData();
//
//        Alumno alumno4 = ad.buscarAlumnoPorID(3);
//        Materia materia4 = md.buscarMateriaPorID(1);
//        Inscripcion inscripcion = new Inscripcion(alumno4, materia4, 9.5);                //Se respetó el esquema de BD del PDF tiene nota como INT
//        id.guardarInscripcion(inscripcion);


//***************************************   R E A D   ***************************************
//***************************************   LISTAR   **************************************** 

    
//        InscripcionData id = new InscripcionData();
//
//        for(Inscripcion inscripcion:id.obtenerInscripciones()){
//             System.out.println("ID " + inscripcion.getIdInscripcion());
//             System.out.println("Apellido "+inscripcion.getAlumno().getApellido());
//             System.out.println("Materia " + inscripcion.getMateria().getNombre());
//        }

//                                    LISTAR POR ALUMNO 
    
//        InscripcionData id = new InscripcionData();
//
//        for(Inscripcion inscripcion:id.obtenerInscripcionesPorAlumno(1)){
//             System.out.println("ID " + inscripcion.getIdInscripcion());
//             System.out.println("Apellido "+inscripcion.getAlumno().getApellido());
//             System.out.println("Materia " + inscripcion.getMateria().getNombre());
//        }
//        

//*************************************** U P D A T E ***************************************

//        InscripcionData id = new InscripcionData();
//        id.actualizarNota(0, 3, 10);

//*************************************** D E L E T E ***************************************
//        InscripcionData id = new InscripcionData();
//        id.borrarInscripcionMateriaAlumno(3, 10);
