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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
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
	} catch(SQLIntegrityConstraintViolationException ex){
	    JOptionPane.showMessageDialog(null, "Entrada de datos duplicada, materia ya existente "+ex);
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia " + ex);
	}

    }

    public Materia buscarMateria(int id) {

	String sql = "SELECT nombre, anio, estado FROM materia WHERE idMateria = ? ";
	Materia mat = null;
	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		mat = new Materia();
		mat.setIdMateria(id);
		mat.setNombre(rs.getString("nombre"));
		mat.setAnioMateria(rs.getInt("anio"));
		mat.setActivo(rs.getBoolean("estado"));
	    } else {
		JOptionPane.showMessageDialog(null, "No se encontro materia con el id: " + id);
	    }
	    ps.close();
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia\n" + ex.getMessage());
	}
	return mat;
    }
    
        public Materia buscarMateria(String nombre){
	String sql = "SELECT idMateria, nombre, anio, estado FROM materia WHERE nombre = ? ";
	Materia mat = null;
	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, nombre);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		mat = new Materia();
		mat.setIdMateria(rs.getInt("idMateria"));
		mat.setNombre(rs.getString("nombre"));
		mat.setAnioMateria(rs.getInt("anio"));
		mat.setActivo(rs.getBoolean("estado"));
	    } else {
		JOptionPane.showMessageDialog(null, "No se encontro materia con el nombre: " + nombre);
	    }
	    ps.close();
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia\n" + ex.getMessage());
	}
	return mat;
    }

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
	    } else {
		JOptionPane.showMessageDialog(null, "Error al intentar modificar una materia");
	    }
	    ps.close();

	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia\n" + ex.getMessage());

	}
    }

    public void eliminarMateria(int id) {
	String sql = "UPDATE materia SET estado = 0 WHERE idMateria = ?";
	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, id);
	    int resultado = ps.executeUpdate();
	    if (resultado == 1) {
		JOptionPane.showMessageDialog(null, "Materia eliminada");
	    } else {
		JOptionPane.showMessageDialog(null, "No tuvo exito al eliminar una materia");
	    }
	    ps.close();
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia\n" + ex.getMessage());

	}
    }

    public List<Materia> listarMaterias(){
	String sql = "SELECT idMateria, nombre, anio FROM materia WHERE estado = 1";
	List<Materia> materias = new ArrayList<>();
	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Materia materia = new Materia();
		materia.setIdMateria(rs.getInt("idMateria"));
		materia.setNombre(rs.getString("nombre"));
		materia.setAnioMateria(rs.getInt("anio"));
		materia.setActivo(true);
		
		materias.add(materia);
	    }
	    ps.close();
	} catch (SQLException ex) {
	  JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia\n" + ex.getMessage());
	}
        return materias;
    }
}
