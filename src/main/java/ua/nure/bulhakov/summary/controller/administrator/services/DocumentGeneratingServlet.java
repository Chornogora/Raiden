package ua.nure.bulhakov.summary.controller.administrator.services;

import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.service.document.PDFCreator;
import ua.nure.bulhakov.summary.database.DBException;

import javax.servlet.http.HttpServlet;

public abstract class DocumentGeneratingServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DocumentGeneratingServlet.class);

    protected void generateDocument(){
        try{
            PDFCreator.getInstance().generateDocument(getServletContext().getRealPath(""));
        }catch(DBException e){
            logger.info("Can't generate PDF document because of troubles with database", e);
        }catch(DocumentException e){
            logger.info("Can't generate PDF document", e);
        }
    }

}
