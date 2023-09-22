
package accesoADatos;
//
//import entidades.Alumno;

import entidades.Alumno;
import java.sql.Connection;

import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


///**
// * @author Veronica
// */
public class InscripcionData {
  private Connection conexion = null;
  private AlumnoData ad = new AlumnoData();
  private MateriaData md = new MateriaData();
  
///* se declara una variable privada y 
//  se inicializa creando una nueva instancia de la clase AlumnoData por esto
//  la clase InscripcionData tiene una dependencia de la clase AlumnoData 
//  y puede utilizar sus métodos y propiedades a través de esta variable ad.*/  



    public InscripcionData() {
        this.conexion = Conexion.getConexion();
    }
    
    

    public InscripcionData(Connection conexion, AlumnoData alumnoData, MateriaData materiaData) {
        this.conexion = Conexion.getConexion();
        this.ad = alumnoData;
        this.md = materiaData;
    }
    
    
    public void guardarInscripcion(Inscripcion inscripcion){
        
        String sql = "INSERT INTO inscripcion(idAlumno, idMateria, nota)VALUES(?,?,?)"; 
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2,inscripcion.getMateria().getIdMateria());
            ps.setDouble(3,inscripcion.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                inscripcion.setIdInscripcion(rs.getInt(1)); //el 1 es porque quiero el idInscripcion que está en la columna 1 
                JOptionPane.showMessageDialog(null, "Inscripción realizada");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla inscribir");
            }
            ps.close();
            
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo inscribir "+ ex.getMessage());
    
        }
        
    }
    
    public void actualizarNota(double nota, int idAlumno, int idMateria){
        
        String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
      try {
          PreparedStatement ps = conexion.prepareStatement(sql);
          ps.setDouble(1, nota);
          ps.setInt(2, idAlumno);
          ps.setInt(3, idMateria);
          int fila = ps.executeUpdate();
          if(fila>0){
          JOptionPane.showMessageDialog(null, "Nota actualizada");
  
          }
          ps.close();
      } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "No se pudo actualizar nota "+ ex.getMessage());

      }
        
        
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){
        
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
      try {
          PreparedStatement ps = conexion.prepareStatement(sql);
          ps.setInt(1, idAlumno);
          ps.setInt(2, idMateria);
          int filas = ps.executeUpdate();
          if(filas>0){
          JOptionPane.showMessageDialog(null, "Inscripcion borrada");
          }
          ps.close();
      } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla inscripcion");
      }
        
    }

    /*en SELECT usamos executeQuery que no devuelve un entero, 
    devuelve un resultSet que es como una matriz
    conexion tantas  columnas como columna tenga la tabla 
    y tantas filas como elementos tenga 
    ( en este caso elementos = inscripciones)*/
    
    public List<Inscripcion> obtenerInscripciones(){
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";
      try {
          PreparedStatement ps = conexion.prepareStatement(sql);
          ResultSet rs = ps.executeQuery(); 
          
          while(rs.next()){
              
              Inscripcion inscripcion = new Inscripcion();
              inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
              Alumno alumno = ad.buscarAlumno( rs.getInt("idAlumno"));
              Materia materia = md.buscarMateria(rs.getInt("idMateria"));
              inscripcion.setAlumno(alumno);
              inscripcion.setMateria(materia);
              inscripcion.setNota(rs.getDouble("nota"));
              cursadas.add(inscripcion);
              }
          ps.close();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla inscripcion");

      }
      return cursadas;
    }
    
        public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
        ArrayList<Inscripcion> inscripcionPorAlumno = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?";
      try {
          PreparedStatement ps = conexion.prepareStatement(sql);
          ps.setInt(1, idAlumno);
          ResultSet rs = ps.executeQuery(); 
          
          while(rs.next()){
              
              Inscripcion inscripcion = new Inscripcion();
              inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
              Alumno alumno = ad.buscarAlumno( rs.getInt("idAlumno"));
              Materia materia = md.buscarMateria(rs.getInt("idMateria"));
              inscripcion.setAlumno(alumno);
              inscripcion.setMateria(materia);
              inscripcion.setNota(rs.getDouble("nota"));
              inscripcionPorAlumno.add(inscripcion);
              }
          ps.close();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla inscripcion");

      }
      return inscripcionPorAlumno;
    }
    
}
