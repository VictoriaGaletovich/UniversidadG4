/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadtest;

import accesoADatos.Conexion;
import accesoADatos.MateriaData;
import entidades.Alumno;
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

	   Materia lengua = new Materia("Lengua", 2, true);
	   MateriaData a = new MateriaData();
	   a.guardarMateria(lengua);
        
    }
    }
    

