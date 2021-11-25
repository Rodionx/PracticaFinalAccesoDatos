import java.io.File;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX {

    SAXParser parser;
    ManejadorSAX sh;
    File ficheroXML;

    public int abrir_XML_SAX(File fichero){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();

            sh = new ManejadorSAX();

            ficheroXML = fichero;

            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    
    public String recorrerSAX(){
        try {
            sh.cadena_resultado = "";
            parser.parse(ficheroXML, sh);
            return sh.cadena_resultado;
        } catch(SAXException ex){
            return "Error al parsear SAX ";
        } catch (IOException e) {
            return "Error al parsear el SAX";
        }
    }
}

 class ManejadorSAX extends DefaultHandler {

        String cadena_resultado = "";

        @Override
        public void characters(char[] ch, int start, int length)
        throws SAXException{
            for(int i = start; i<length+start; i++){
                cadena_resultado = cadena_resultado + ch[i];
            }
            cadena_resultado = cadena_resultado.trim() + "\n";
        }
        
        @Override
        public void endElement(String uri, String localName, String qName)
        throws SAXException{
            if(qName.equals("Tienda")){
                cadena_resultado = cadena_resultado + "-------------------------------------------\n";
            }
        }
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException{
            if(qName.equals("Tienda")){
                cadena_resultado = cadena_resultado + "Se van a mostrar los libros de este documento. \n -------------------------------------------".trim();
            }else if(qName.equals("Hombre")){
                cadena_resultado = cadena_resultado + "Publicado en: " + attributes.getValue(attributes.getQName(0).trim());
            }else if(qName.equals("Mujer")){
                cadena_resultado = cadena_resultado + "El titulo es: ".trim();
            }else if(qName.equals("Autor")){
                cadena_resultado = cadena_resultado + "El autor es: ".trim();
            }
        }

    }
