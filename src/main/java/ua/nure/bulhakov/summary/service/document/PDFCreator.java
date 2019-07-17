package ua.nure.bulhakov.summary.service.document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.bulhakov.summary.database.*;
import ua.nure.bulhakov.summary.service.email.database.*;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.model.PhoneConnection;
import ua.nure.bulhakov.summary.model.Television;
import ua.nure.bulhakov.summary.model.Work;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Class that is used to create PDF document with services
 * @author A.Bulhakov
 */
public class PDFCreator {

    private static PDFCreator instance;

    private PDFCreator(){

    }

    public static PDFCreator getInstance(){
        if(instance == null){
            instance = new PDFCreator();
        }
        return instance;
    }

    /**
     * @param root root path of the application
     * @throws DocumentException when can't create file successfully
     * @throws DBException when gets services from database
     */
    public void generateDocument(String root) throws DocumentException, DBException {
        Document document = new Document();
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(root + "/docs/tariffs.pdf");
        }catch(IOException e){
            throw new DocumentException("Can't create file");
        }
        PdfWriter.getInstance(document, stream);
        document.open();

        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
        Paragraph title = new Paragraph("Raiden Tariffs", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));

        generateInternet(document);
        generateTelevision(document);
        generatePhone(document);
        generateServices(document);

        document.add(new Paragraph(" "));
        Paragraph date = new Paragraph("Date of generation: " + new Date(System.currentTimeMillis()).toString());
        date.setAlignment(Element.ALIGN_BOTTOM);
        document.add(date);

        document.close();
    }

    private PdfPTable getTable(Document document, String tableName, String ... columns) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.BOLD);
        Paragraph paragraph = new Paragraph(tableName, titleFont);
        paragraph.add(new Paragraph(" "));
        document.add(paragraph);

        PdfPTable table = new PdfPTable(columns.length);
        for (String column : columns) {
            PdfPCell c1 = new PdfPCell(new Phrase(column));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);

        return table;
    }

    private void generateInternet(Document document) throws DocumentException, DBException {
        PdfPTable table = getTable(document, "Internet", "Internet Tariff", "Speed of Internet", "Month Price");

        List<Internet> list = InternetDatabaseManager.getInstance().findAll();
        for(Internet inet : list){
            table.addCell(inet.getName());
            table.addCell(inet.getSpeed() + " Mb/s");
            table.addCell(inet.getMonthPrice() + " ₴");
        }

        document.add(table);
        document.add(new Paragraph(" "));
    }

    private void generateTelevision(Document document) throws DocumentException, DBException {
        PdfPTable table = getTable(document, "Television", "Television Tariff",
                "Number of Channels", "Format", "Month Price");

        List<Television> list = TelevisionDatabaseManager.getInstance().findAll();
        for(Television television : list){
            table.addCell(television.getName());
            table.addCell(String.valueOf(television.getChannels()));
            table.addCell(television.getFormat());
            table.addCell(television.getMonthPrice() + " ₴");
        }

        document.add(table);
        document.add(new Paragraph(" "));
    }

    private void generatePhone(Document document) throws DocumentException, DBException {
        PdfPTable table = getTable(document, "Telephony", "Phone Connection Tariff",
                "Minutes for mobile operators", "Month Price");

        List<PhoneConnection> list = PhoneConnectionDatabaseManager.getInstance().findAll();
        for(PhoneConnection phone : list){
            table.addCell(phone.getName());
            table.addCell(phone.getMobileMinutes() + " min");
            table.addCell(phone.getMonthPrice() + " ₴");
        }

        document.add(table);
        document.add(new Paragraph(" "));
    }

    private void generateServices(Document document) throws DocumentException, DBException {
        PdfPTable table = getTable(document, "Services", "Name of Service",
                "Measure", "Price");

        List<Work> list = WorkDatabaseManager.getInstance().findAll();
        for(Work work : list){
            table.addCell(work.getName());
            table.addCell(work.getMeasure());
            table.addCell(work.getPrice() + " ₴");
        }

        document.add(table);
        document.add(new Paragraph(" "));
    }
}
