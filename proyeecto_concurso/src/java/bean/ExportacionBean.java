/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import bd.ConexionRemota;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author favio
 */
@ManagedBean(name = "beanExportacion")
@ViewScoped
public class ExportacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private DefaultStreamedContent file;
    //
    private String nombre;

    /**
     * Creates a new instance of CargoBean
     */
    public ExportacionBean() {
//
//        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/home/favio/Descargas/downloaded_optimus.jpg");
//        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
//        System.out.println("ExporeacionBean) => Se ah creado el bean ExporacionBean");

    }

    /**
     * GETTERS & SETTERS
     */
    public DefaultStreamedContent getFile() {
        return file;
    }

    public void setFile(DefaultStreamedContent file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * MÉTODOS
     */
//    public StreamedContent prepDescarga() throws FileNotFoundException {
//       System.out.println("ENTRO POR  PREDESCARGA");
//        StreamedContent download = new DefaultStreamedContent();
//        File file = new File("/home/favio/Descargas/downloaded_optimus.jpg/");
//        InputStream input = new FileInputStream(file);
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
//        System.out.println("PREP = " + download.getName());
//
//        return download;
//    }
    public void exportarCargos() throws SQLException {

        ConexionRemota conexionRemota = new ConexionRemota();
        conexionRemota.consultaVistaExportacion();

    }

    public void creacionCsv() throws IOException, SQLException {
        //To change body of generated methods, choose Tools | Templates.

        String outputFile = "/home/favio/NetBeansProjects//home/favio/NetBeansProjects/proyecto_concurso/proyecto_concurso/cargosexportados.csv";
        System.out.println("beanExportacion ==> creacion CSV");
        boolean alreadyExists = new File(outputFile).exists();

        if (alreadyExists) {
            File fichero = new File(outputFile);
            fichero.delete();
        }
        ConexionRemota conexion = new ConexionRemota();
        ResultSet consulta = conexion.consultaVistaExportacion();

        try {

            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

// Para generar el archivo csv con encabezado si es necesario.
            csvOutput.write(consulta.getString("numero_expediente"));
            csvOutput.write(consulta.getString("numero_resolucion"));
            csvOutput.write(consulta.getString("modificacion"));
            csvOutput.write(consulta.getString("modifica_resolucion"));
            csvOutput.write(consulta.getString("prorroga"));
            csvOutput.write(consulta.getString("prorroga_resolucion"));
            csvOutput.write(consulta.getString("antecedente"));
            csvOutput.write(consulta.getString("oposicion"));
            csvOutput.write(consulta.getString("clase"));
            csvOutput.endRecord(); //Cierro la primera fila,

//            for (Institucion_formadoraCsv us : instituciones) {
//
//                csvOutput.write(us.getCodigo());
//                csvOutput.write(us.getNombre());
//                csvOutput.write(us.getCod_pais());
//                csvOutput.write(us.getId_localidad());
//                csvOutput.write(us.getNormativa_de_creacion());
//                csvOutput.write(us.getAuxiliarato());
//                csvOutput.write(us.getTecnicatura());
//                csvOutput.write(us.getGrado_universitario());
//                csvOutput.write(us.getDomicilio());
//                csvOutput.write(us.getTelefono());
//                csvOutput.write(us.getDepedencias());
//                csvOutput.write(us.getHabilitado());
//                csvOutput.write(us.getObservacion());
//
//                csvOutput.endRecord();
//
//            }
            while (consulta.next()) {
                csvOutput.write(consulta.getString("numero_expediente"));
                csvOutput.write(consulta.getString("numero_resolucion"));
                csvOutput.write(consulta.getString("modificacion"));
                csvOutput.write(consulta.getString("modifica_resolucion"));
                csvOutput.write(consulta.getString("prorroga"));
                csvOutput.write(consulta.getString("prorroga_resolucion"));
                csvOutput.write(consulta.getString("antecedente"));
                csvOutput.write(consulta.getString("oposicion"));
                csvOutput.write(consulta.getString("clase"));
                csvOutput.endRecord();
            }
            csvOutput.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
