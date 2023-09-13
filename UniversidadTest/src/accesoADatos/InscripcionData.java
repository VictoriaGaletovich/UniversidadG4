package accesoadatos;

import entidades.Inscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author Veronica
 */
public class InscripcionData {

    private Connection con = null;
    private AlumnoData alumnoData;
    private MateriaData materiaData;

    //pruebo agregar este constructor vacio para poder hacer las pruebas desde el main
    public InscripcionData() {
    }

    public InscripcionData(Connection con, AlumnoData alumnoData, MateriaData materiaData) {
        this.con = Conexion.getConexion();
        this.alumnoData = alumnoData;
        this.materiaData = materiaData;
    }

    public void guardarInscripcion(Inscripcion inscripcion) {

        String sql = "INSERT INTO inscripcion(idAlumno, idMmateria, nota)VALUES(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.setDouble(3, inscripcion.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se inscribió a " + inscripcion.getMateria().getNombre());
            } else {
                JOptionPane.showMessageDialog(null, "No se puedo inscribir");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo inscribir " + ex.getMessage());

        }

    }

}
