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
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Juan Manuel Biagioli
 */
public class UniversidadTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Connection con = Conexion.getConexion();
//        Alumno a;
//        a = new Alumno(43653968, "Galetovich", "Victoria", LocalDate.of(2001, Month.SEPTEMBER, 12), true);
//        System.out.println(a);

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

    }
}
