/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoADatos;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Manuel Biagioli
 */
public class MateriaData {

    private Connection con = null;

    public MateriaData() {

	con = Conexion.getConexion();

    }

    public void guardarMateria(Materia materia) {
	String sql = "INSERT INTO materia(nombre,anio,estado) VALUES (?,?,?)";
	try {
	    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    ps.setString(1, materia.getNombre());
	    ps.setInt(2, materia.getAnioMateria());
	    ps.setBoolean(3, materia.isActivo());
	    ps.executeUpdate();
	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next()) {
		materia.setIdMateria(rs.getInt(1));
		JOptionPane.showMessageDialog(null, "Materia agregada correctamente");
	    }
	    ps.close();
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex);
	}

    }

//    public Materia buscarMateria(int id){
//	return 
//    }
   
    public void modificarMateria(Materia materia) {
	String sql = "UPDATE materia SET nombre = ?, anio = ?, estado = ? WHERE idMateria = ?";
	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, materia.getNombre());
	    ps.setInt(2, materia.getAnioMateria());
	    ps.setBoolean(3, materia.isActivo());
	    ps.setInt(4, materia.getIdMateria());
	    int resultado = ps.executeUpdate();
	    if (resultado == 1) {
		JOptionPane.showMessageDialog(null, "Materia modificada exitosamente");
	    }else{
		JOptionPane.showMessageDialog(null, "Error al intentar modificar una materia");
	    }
	    ps.close();

	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia\n" + ex.getMessage());

	}
    }

    public void eliminarMateria(int id) {

    }

//    public List<Materia> listarMaterias(){
//	
//    }
}
