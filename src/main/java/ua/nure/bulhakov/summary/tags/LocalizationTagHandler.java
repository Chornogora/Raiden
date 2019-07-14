package ua.nure.bulhakov.summary.tags;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
public class LocalizationTagHandler extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(LocalizationTagHandler.class);

    private static Map<String, ResourceBundle> bundles;

    @Override
    public int doAfterBody(){

        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();

        String localization = (String)session.getAttribute("localization");
        if(localization == null){
            localization = "ru";
            session.setAttribute("localization", localization);
        }

        String s = this.bodyContent.getString().trim();

        try {
            String translate = bundles.get(localization).getString(s);
            translate = new String(translate.getBytes("ISO-8859-1"), "UTF-8");
            print(translate);
        }catch(NullPointerException e){
            logger.info("Localization resources were not connected");
            print(s);
        }catch (MissingResourceException e) {
            logger.info("Requested string is missing: " + s);
            print(s);
        }catch(UnsupportedEncodingException e){
            //never  will be thrown
        }

        return SKIP_BODY;
    }

    private void print(String translate){
        JspWriter out=bodyContent.getEnclosingWriter();
        try {
            out.print(translate);
        } catch (IOException e) {
            //Impossible to do something
        }
    }

    public static void setBundles(Map<String, ResourceBundle> b){
        bundles = b;
    }

}