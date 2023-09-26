package accesoADatos;

import entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;

public class AlumnoData {

    private Connection con = null;    //no olvidar inicializar en nulo para el singleton
    private int dni;

    public AlumnoData() {

        con = Conexion.getConexion();
    }
//agregar NullpointerException 
    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) "
                + "VALUES(?,?,?,?,1)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            //ps.setBoolean(5, alumno.isActivo());    // LO QUEREMOS INICIALIZAR ACTIVO
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno Guardado");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno" + ex.getMessage());
        }
    }

    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno "
                + "SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento = ? "
                + "WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setInt(5, alumno.getIdAlumno());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno modificado con exito");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }

    }

    public Alumno buscarAlumno(int id) {
        String sql = "SELECT dni, apellido, nombre, fechaNacimiento "
                + "FROM alumno "
                + "WHERE idAlumno= ? ";
        Alumno alumno = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);
            } else {
                JOptionPane.showMessageDialog(null, "El DNI no pertenece a un alumno activo.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno" + ex.getMessage());
        }
        return alumno;
    }

    public void eliminarAlumno(int id) {
        String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno Eliminado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno" + ex.getMessage());

        }
    }

    //Trae SOLO los alumnos activos
    public Alumno buscarAlumnoPorDNI(int dni) {
        String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento "
                + "FROM alumno "
                + "WHERE dni= ? AND estado = 1";
        Alumno alumno = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);
            } else {
                JOptionPane.showMessageDialog(null, "No Existe ese Alumno");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumno;
    }

    public List<Alumno> listarAlumnos() {
        String sql = "SELECT idAlumno,dni,apellido,nombre,fechaNacimiento FROM alumno WHERE estado = 1";
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);

                alumnos.add(alumno);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
        }
        return alumnos;
    }

    //TRAE TODOS
    public Alumno buscarTodosAlumnoPorDNI(int dni) {     //este método trae TODOS los alumnos historicos
        String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento, estado "
                + "FROM alumno "
                + "WHERE dni = ?";
        Alumno al = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                al = new Alumno();
                al.setDni(dni);
                al.setIdAlumno(rs.getInt("idAlumno"));
                al.setApellido(rs.getString("apellido"));
                al.setNombre(rs.getString("nombre"));
                al.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                al.setActivo(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "El DNI ingresado no pertenece a un alumno activo.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno" + ex.getMessage());

        }
        return al;

    }

    public Alumno AlumnosInactivosPorDNI(int dni) {
        String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento "
                + "FROM alumno "
                + "WHERE dni = ? AND estado = 0";
        Alumno al = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                al = new Alumno();
                al.setDni(dni);
                al.setIdAlumno(rs.getInt("idAlumno"));
                al.setApellido(rs.getString("apellido"));
                al.setNombre(rs.getString("nombre"));
                al.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                al.setActivo(false);
            } else {
                JOptionPane.showMessageDialog(null, "El DNI no corresponde a un alumno inactivo ");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno" + ex.getMessage());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "El campo DNI debe estar completo." + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El campo DNI solo acepta números" + ex.getMessage());
        }
        return al;

    }
    //Agregar try-catch numbrerFormatException yNullPointerException

    public void activarAlumno(int id) {

        String sql = "UPDATE alumno "
                + "SET estado = 1 "
                + "WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            ps.close();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se activó el alumno.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la dase de datos: " + ex.getMessage());

        }

    }
}
