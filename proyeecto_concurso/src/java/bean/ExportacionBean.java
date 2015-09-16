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

          InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/home/favio/Descargas/downloaded_optimus.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
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

    public void exportarCargos() {
        
           
    }
}
