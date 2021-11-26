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
                cadena_resultado = cadena_resultado + "-Los articulos disponibles para Hombre son: " + "\n                                                                                                            .";
            }else if(qName.equals("Mujer")){
                cadena_resultado = cadena_resultado + "-Los articulos disponibles para Mujer son: " + "\n                                                                                                            .";
            }else if(qName == "Abrigos"){
                cadena_resultado = cadena_resultado + "--Los Abrigos: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Sudaderas"){
                cadena_resultado = cadena_resultado + "--Las Sudaderas: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Chaquetas"){
                cadena_resultado = cadena_resultado + "--Las Chaquetas: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Camisetas"){
                cadena_resultado = cadena_resultado + "--Las Camisetas: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Pantalones"){
                cadena_resultado = cadena_resultado + "--Los Pantalones: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Cinturones"){
                cadena_resultado = cadena_resultado + "--Los Cinturones: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Bufandas"){
                cadena_resultado = cadena_resultado + "--Las Bufandas: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Gemelos"){
                cadena_resultado = cadena_resultado + "--Los Gemelos: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Perfumes"){
                cadena_resultado = cadena_resultado + "--Los Perfumes: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Colonias"){
                cadena_resultado = cadena_resultado + "--Las Colonias: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "eau_de_toilette"){
                cadena_resultado = cadena_resultado + "--Los eau de toilette: ".trim()+ "\n                                                                                                            .";
            }else if(qName == "Abrigo" || qName == "Abrigo" || qName == "Abrigo" || qName == "Abrigo" || qName == "Sudadera" || qName == "Chaqueta" || qName == "Camiseta" || qName == "Pantalon" || qName == "Cinturon" || qName == "Bufanda" || qName == "Perfume" || qName == "Colonia" || qName == "eau"){
                cadena_resultado = cadena_resultado + "Con ID: " + attributes.getValue(attributes.getQName(0).trim());
            }
        }

    }
