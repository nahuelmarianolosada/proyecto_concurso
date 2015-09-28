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
import dominio.Profesion;
import dominio.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedProperty;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author favio
 */
@ManagedBean(name = "beanExportacion")
@ViewScoped
public class ExportacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private DefaultStreamedContent file;
    private Profesion profesion;
    private String FechaDesde;
    private String FechaHasta;
    private String nombreProfesion;

    @ManagedProperty("#{beanLogin}")
    private LoginBean beanLogin;

    /**
     * Creates a new instance of CargoBean
     */
    public ExportacionBean() {
//
//        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/home/favio/Descargas/downloaded_optimus.jpg");
//        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
        System.out.println("ExporeacionBean) => Se ah creado el bean ExporacionBean");
        
        
        
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

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public String getFechaDesde() {
        return FechaDesde;
    }

    public void setFechaDesde(String FechaDesde) {
        this.FechaDesde = FechaDesde;
    }

    public String getFechaHasta() {
        return FechaHasta;
    }

    public void setFechaHasta(String FechaHasta) {
        this.FechaHasta = FechaHasta;
    }

    public LoginBean getBeanLogin() {
        return beanLogin;
    }

    public void setBeanLogin(LoginBean beanLogin) {
        this.beanLogin = beanLogin;
    }

    public String getNombreProfesion() {
        return nombreProfesion;
    }

    public void setNombreProfesion(String nombreProfesion) {
        this.nombreProfesion = nombreProfesion;
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

    public void creacionExportarCargosCsv() throws IOException, SQLException, ClassNotFoundException {
        //To change body of generated methods, choose Tools | Templates.

        String outputFile = "/home/favio/NetBeansProjects/proyecto_concurso/proyecto_concurso/cargosexportados.csv";
        System.out.println("beanExportacion ==> creacion CSV");
        boolean alreadyExists = new File(outputFile).exists();

        if (alreadyExists) {
            File fichero = new File(outputFile);
            fichero.delete();
        }
        ConexionRemota conexion = new ConexionRemota();
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/concursosDB";
        String user = "nmlosada";
        String password = "siisa1234";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            Statement stmt = con.createStatement();

            //UTILIZANDO UNA CONSULTA NORMAL
            String consultaSQL = "SELECT * FROM \"vw_archivoExportacion\";";
            ResultSet consulta = stmt.executeQuery(consultaSQL);

            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

// Para generar el archivo csv con encabezado si es necesario.
            csvOutput.write("numero_expediente");
            csvOutput.write("numero_resolucion");
            csvOutput.write("modificacion");
            csvOutput.write("modifica_resolucion");
            csvOutput.write("prorroga");
            csvOutput.write("prorroga_resolucion");
            csvOutput.write("antecedente");
            csvOutput.write("oposicion");
            csvOutput.write("clase");
            csvOutput.write("agrupamiento");
            csvOutput.write("fecha_apertura");
            csvOutput.write("fecha_cierre");
            csvOutput.write("fecha_ejecucion");
            csvOutput.write("fecha_publicacion");
            csvOutput.write("id_cargo");
            csvOutput.write("nombre_profesion");
            csvOutput.write("especialidad");
            csvOutput.write("categoria");
            csvOutput.write("funcion");
            csvOutput.write("area_de_desempenio");
            csvOutput.write("nombre_de_establecimiento");
            csvOutput.write("modalidad");
            csvOutput.write("fecha_acta_formulacion_perfil");
            csvOutput.write("enunciacion");
            csvOutput.write("es_desierto");
            csvOutput.write("nombres");
            csvOutput.write("apellido");
            csvOutput.write("dni");

            csvOutput.endRecord(); //Cierro la primera fila,
            while (consulta.next()) {

                csvOutput.write(consulta.getString("numero_expediente"));
                csvOutput.write(consulta.getString("numero_resolucion"));

                if (consulta.getBoolean("modificacion")) {
                    csvOutput.write("VERDADERO");
                } else {
                    csvOutput.write("FALSO");
                }
                csvOutput.write(consulta.getString("modifica_resolucion"));
                if (consulta.getBoolean("prorroga")) {
                    csvOutput.write("VERDADERO");
                } else {
                    csvOutput.write("FALSO");
                }
                csvOutput.write(consulta.getString("prorroga_resolucion"));
                if (consulta.getBoolean("antecedente")) {
                    csvOutput.write("VERDADERO");
                } else {
                    csvOutput.write("FALSO");
                }
                if (consulta.getBoolean("oposicion")) {
                    csvOutput.write("VERDADERO");
                } else {
                    csvOutput.write("FALSO");
                }
                csvOutput.write(consulta.getString("clase"));
                csvOutput.write(consulta.getString("agrupamiento"));
                csvOutput.write(consulta.getString("fecha_apertura"));
                csvOutput.write(consulta.getString("fecha_cierre"));
                csvOutput.write(consulta.getString("fecha_ejecucion"));
                csvOutput.write(consulta.getString("fecha_publicacion"));
                csvOutput.write(consulta.getString("id_cargo"));
                csvOutput.write(consulta.getString("nombre_profesion"));
                csvOutput.write(consulta.getString("especialidad"));
                csvOutput.write(consulta.getString("categoria"));
                csvOutput.write(consulta.getString("funcion"));
                csvOutput.write(consulta.getString("area_de_desempenio"));
                csvOutput.write(consulta.getString("nombre_de_establecimiento"));
                csvOutput.write(consulta.getString("modalidad"));
                csvOutput.write(consulta.getString("fecha_acta_formulacion_perfil"));
                csvOutput.write(consulta.getString("enunciacion"));
                if (consulta.getBoolean("es_desierto")) {
                    csvOutput.write("VERDADERO");
                } else {
                    csvOutput.write("FALSO");
                }
                csvOutput.write(consulta.getString("nombres"));
                csvOutput.write(consulta.getString("apellido"));
                csvOutput.write(consulta.getString("dni"));
                csvOutput.endRecord();
            }
            csvOutput.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void crecionExportarCargosXls() throws IOException, SQLException, ClassNotFoundException {
        //To change body of generated methods, choose Tools | Templates.

        try {
            System.out.println("beanExportacion ==> creacion XLS");
            ConexionRemota conexion = new ConexionRemota();
            String driver = "org.postgresql.Driver";
            String connectString = "jdbc:postgresql://localhost:5432/concursosDB";
            String user = "nmlosada";
            String password = "siisa1234";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            ResultSet consultaFechaServidor = stmt2.executeQuery("SELECT * FROM \"vw_Fecha\";");

            String outputFile = "/home/favio/NetBeansProjects/proyecto_concurso/proyecto_concurso/cargosexportados.xls";

            File archivoXLS = new File(outputFile);
            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            //Creamos el archivo xls
            archivoXLS.createNewFile();
            //Creamos el libro
            Workbook libro = new HSSFWorkbook();
            //Inicializamos un flujo de datos
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            //Creamos una hoja dentro del libro
            org.apache.poi.ss.usermodel.Sheet hoja = libro.createSheet("Cargos exportados");

            // Generar el archivo XLS con encabezado 
            int numeroFila = 0;
            int numeroColumna = 28;
            org.apache.poi.ss.usermodel.Row fila = hoja.createRow(0);

            //CellStyle stilo = libro.createCellStyle();
            HSSFCellStyle stilo = (HSSFCellStyle) libro.createCellStyle();
            //Cambio el color de las celdas de la fila
            stilo.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            //solido la fila
            stilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            //Para agregar bordes
            stilo.setBorderRight(CellStyle.BORDER_THIN);
            stilo.setRightBorderColor(IndexedColors.BLACK.getIndex());
            stilo.setBorderBottom(CellStyle.BORDER_THIN);
            stilo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            stilo.setBorderLeft(CellStyle.BORDER_THIN);
            stilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            stilo.setBorderTop(CellStyle.BORDER_THIN);
            stilo.setTopBorderColor(IndexedColors.BLACK.getIndex());

            //Creo fuente 
            Font font = libro.createFont();
            //Color de fuente.
            font.setColor(HSSFColor.BLUE.index);
            //Fuente gruesa.
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            //Tamaño de fuente.
            font.setFontHeightInPoints((short) 12);
            stilo.setFont(font);
            HSSFCellStyle stilo2 = (HSSFCellStyle) libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            stilo2.setFont(font2);

            //Creación del encabezado.
            fila = hoja.createRow(1);
            Cell celda = fila.createCell(0);
            celda.setCellStyle(stilo2);

            while (consultaFechaServidor.next()) {
                char[] charArray = consultaFechaServidor.getString("now").toCharArray(); //Convierto la cadena en un array de caracteres.
                char[] soloFecha;
                soloFecha = new char[20];
                for (int i = 0; i < 20; i++) {
                    soloFecha[i] = charArray[i];
                }

                celda.setCellValue("Fecha de exportación del archivo: " + String.valueOf(soloFecha));
            }
            fila = hoja.createRow(2);
            celda = fila.createCell(0);
            celda.setCellStyle(stilo2);
            celda.setCellValue("Usuario: " + beanLogin.getUsuarioLogeado().getNombre() + " " + beanLogin.getUsuarioLogeado().getApellido() + ".");
            fila = hoja.createRow(3);
            celda = fila.createCell(0);
            celda.setCellStyle(stilo2);
            celda.setCellValue("Filtro por profesion: "+nombreProfesion+";");
            fila = hoja.createRow(4);
            celda = fila.createCell(0);
            celda.setCellStyle(stilo2);
            celda.setCellValue("Fecha de publicación desde: " + "05-07-2015");
            celda = fila.createCell(1);
            celda.setCellStyle(stilo2);
            celda.setCellValue("Fecha de publicación hasta: " + "18-09-2015");

            numeroFila = 6;
            fila = hoja.createRow(numeroFila);
            for (int c = 0; c < numeroColumna; c++) {
                celda = fila.createCell(c);
                celda.setCellStyle(stilo);
                switch (c) {

                    case 0:
                        celda.setCellValue("numero_expediente");
                        break;
                    case 1:
                        celda.setCellValue("numero_resolucion");
                        break;
                    case 2:
                        celda.setCellValue("modificacion");
                        break;
                    case 3:
                        celda.setCellValue("modifica_resolucion");
                        break;
                    case 4:
                        celda.setCellValue("prorroga");
                        break;
                    case 5:
                        celda.setCellValue("prorroga_resolucion");
                        break;
                    case 6:
                        celda.setCellValue("antecedente");
                        break;
                    case 7:
                        celda.setCellValue("oposicion");
                        break;
                    case 8:
                        celda.setCellValue("clase");
                        break;
                    case 9:
                        celda.setCellValue("agrupamiento");
                        break;
                    case 10:
                        celda.setCellValue("fecha_apertura");
                        break;
                    case 11:
                        celda.setCellValue("fecha_cierre");
                        break;
                    case 12:
                        celda.setCellValue("fecha_ejecucion");
                        break;
                    case 13:
                        celda.setCellValue("fecha_publicacion");
                        break;
                    case 14:
                        celda.setCellValue("id_cargo");
                        break;
                    case 15:
                        celda.setCellValue("nombre_profesion");
                        break;
                    case 16:
                        celda.setCellValue("especialidad");
                        break;
                    case 17:
                        celda.setCellValue("categoria");
                        break;
                    case 18:
                        celda.setCellValue("funcion");
                        break;
                    case 19:
                        celda.setCellValue("area_de_desmpenio");
                        break;
                    case 20:
                        celda.setCellValue("nombre_de_establecimiento");
                        break;
                    case 21:
                        celda.setCellValue("modalidad");
                        break;
                    case 22:
                        celda.setCellValue("fecha_acta_formulacion_perfil");
                        break;
                    case 23:
                        celda.setCellValue("enunciacion");
                        break;
                    case 24:
                        celda.setCellValue("es_desierto");
                        break;
                    case 25:
                        celda.setCellValue("nombres");
                        break;
                    case 26:
                        celda.setCellValue("apellido");
                        break;
                    case 27:
                        celda.setCellValue("dni");
                        break;
                }
            }

            //UTILIZANDO UNA CONSULTA NORMAL
            String consultaSQL = "SELECT * FROM \"vw_archivoExportacion\";";

            ResultSet consulta = stmt.executeQuery(consultaSQL);

            //Agregado de los registros al archivo.
            while (consulta.next()) {
                numeroFila++;
                org.apache.poi.ss.usermodel.Row fila2 = hoja.createRow(numeroFila);
                for (int c = 0; c < numeroColumna; c++) {
                    celda = fila2.createCell(c);

                    switch (c) {

                        case 0:
                            celda.setCellValue(consulta.getString("numero_expediente"));
                            break;
                        case 1:
                            celda.setCellValue(consulta.getString("numero_resolucion"));
                            break;
                        case 2:
                            if (consulta.getBoolean("modificacion")) {
                                celda.setCellValue("VERDADERO");
                            } else {
                                celda.setCellValue("FALSO");
                            }
                            break;
                        case 3:
                            celda.setCellValue(consulta.getString("modifica_resolucion"));
                            break;
                        case 4:
                            if (consulta.getBoolean("prorroga")) {
                                celda.setCellValue("VERDADERO");
                            } else {
                                celda.setCellValue("FALSO");
                            }
                            break;
                        case 5:
                            celda.setCellValue(consulta.getString("prorroga_resolucion"));
                            break;
                        case 6:
                            if (consulta.getBoolean("antecedente")) {
                                celda.setCellValue("VERDADERO");
                            } else {
                                celda.setCellValue("FALSO");
                            }
                            break;
                        case 7:
                            if (consulta.getBoolean("oposicion")) {
                                celda.setCellValue("VERDADERO");
                            } else {
                                celda.setCellValue("FALSO");
                            }
                            break;
                        case 8:
                            celda.setCellValue(consulta.getString("clase"));
                            break;
                        case 9:
                            celda.setCellValue(consulta.getString("agrupamiento"));
                            break;
                        case 10:
                            celda.setCellValue(consulta.getString("fecha_apertura"));
                            break;
                        case 11:
                            celda.setCellValue(consulta.getString("fecha_cierre"));
                            break;
                        case 12:
                            celda.setCellValue(consulta.getString("fecha_ejecucion"));
                            break;
                        case 13:
                            celda.setCellValue(consulta.getString("fecha_publicacion"));
                            break;
                        case 14:
                            celda.setCellValue(consulta.getString("id_cargo"));
                            break;
                        case 15:
                            celda.setCellValue(consulta.getString("nombre_profesion"));
                            break;
                        case 16:
                            celda.setCellValue(consulta.getString("especialidad"));
                            break;
                        case 17:
                            celda.setCellValue(consulta.getString("categoria"));
                        case 18:
                            celda.setCellValue(consulta.getString("funcion"));
                            break;
                        case 19:
                            celda.setCellValue(consulta.getString("area_de_desempenio"));
                            break;
                        case 20:
                            celda.setCellValue(consulta.getString("nombre_de_establecimiento"));
                            break;
                        case 21:
                            celda.setCellValue(consulta.getString("modalidad"));
                            break;
                        case 22:
                            celda.setCellValue(consulta.getString("fecha_acta_formulacion_perfil"));
                            break;
                        case 23:
                            celda.setCellValue(consulta.getString("enunciacion"));
                            break;
                        case 24:
                            if (consulta.getBoolean("es_desierto")) {
                                celda.setCellValue("VERDADERO");
                            } else {
                                celda.setCellValue("FALSO");
                            }
                            break;
                        case 25:
                            celda.setCellValue(consulta.getString("nombres"));
                            break;
                        case 26:
                            celda.setCellValue(consulta.getString("apellido"));
                            break;
                        case 27:
                            celda.setCellValue(consulta.getString("dni"));
                            break;
                    }
                }
            }

            //Ajuste del ancho de las columnas del archivo.
            for (int i = 0; i < numeroColumna; i++) {
                hoja.autoSizeColumn(i);
            }
            //Escribiendo Archivo
            libro.write(archivo);
            //Cerrando Archivo.
            archivo.close();
            //Cierre de la consulta.
            stmt.close();
            stmt2.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void insertarImagen() {
//        try {
////            File archivoXLS = new File("/home/favio/NetBeansProjects/proyecto_concurso/proyecto_concurso/cargosexportados2.xls");
////            if (archivoXLS.exists()) {
////                archivoXLS.delete();
////            }
////            //Creamos el archivo xls
////            archivoXLS.createNewFile();
//            //Creamos el libro
//            Workbook libro2 = new HSSFWorkbook();
//            //Inicializamos un flujo de datos
//       //     FileOutputStream archivo = new FileOutputStream(archivoXLS);
//            //Creamos una hoja dentro del libro
//            org.apache.poi.ss.usermodel.Sheet hoja2 = libro2.createSheet("Cargos exportados");
//
//            //FileInputStream obtains input bytes from the image file
//            InputStream inputStream = new FileInputStream("/home/favio/NetBeansProjects/proyecto_concurso/proyecto_concurso/proyeecto_concurso/logoJujuy.png");
//            //Get the contents of an InputStream as a byte[].
//            byte[] bytes = IOUtils.toByteArray(inputStream);
//            //Adds a picture to the workbook
//            int pictureIdx = libro2.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
//            //close the input stream
//            inputStream.close();
//            //Returns an object that handles instantiating concrete classes
//            CreationHelper helper = libro2.getCreationHelper();
//
//            //Creates the top-level drawing patriarch.
//            Drawing drawing = hoja2.createDrawingPatriarch();
//
//            //Create an anchor that is attached to the worksheet
//            ClientAnchor anchor = helper.createClientAnchor();
//            //set top-left corner for the image
//            anchor.setCol1(1);
//            anchor.setRow1(2);
//
//            //Creates a picture
//            Picture pict = drawing.createPicture(anchor, pictureIdx);
//            //Reset the image to the original size
//            pict.resize();
//            FileOutputStream fileOut = null;
//            fileOut = new FileOutputStream("/home/favio/NetBeansProjects/proyecto_concurso/proyecto_concurso/cargosexportados2.xls");
//            libro2.write(fileOut);
//            fileOut.close();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }

    }
}
