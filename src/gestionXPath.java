/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xp
 */
public class gestionXPath {
    Document doc = null;
    String datos_nodo[]= null;
    public int abrir_XML_Path (File fichero){
        try {
            //Se crea un objeto DocumentBuiderFactory.
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            //Indica que el modelo DOM no debe contemplar los comentarios
            //que tenga el XML
            factory.setIgnoringComments(true);
            //Ignora los espacios en blanco que tenga el documento
            factory.setIgnoringElementContentWhitespace(true);
            //Se crea un objeto DocumentBuilder para cargar en él la
            //estructura de árbol DOM a partir del XML seleccionado.
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Interpreta (parsear) el documento XML (file) y genera el
            //DOM equivalente.
            doc= builder.parse(fichero);
            //Ahora doc apunta al árbol DOM listo para ser recorrido
            return 0;
        } catch (Exception e) {
        }
        return -1;
        
    }
    public String EjecutaXPath(String nombre, String busqueda){
        try {    
            //Creamos un string vacio llamado salida.
            String salida = "";
            //Crea el objeto XPath
            XPath xPath = XPathFactory.newInstance().newXPath();
            //Crea un XPathExpresion con la consulta deseada
            XPathExpression exp = xPath.compile(busqueda);
            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá el resultado como una lista de nodos.
            Object result = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            //Ahora recorre la lista para sacar los resultados.
            //Como sé que me devuelve nodos autor, su hijo será el nodos
            //Text que contiene el valor , es decir el nombre del autor.
            for(int i = 0; i < nodeList.getLength(); i++){
                //En el caso de que querramos sacar todos los libros usaremos la funcion procesar libro anteriormente creada.
                salida = salida + "\n" + nodeList.item(i).getFirstChild().getNodeValue();
            }
            return salida;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return null;
        }
    }
    
    private String[] procesarLibro(Node n){
        try {
            //Creamos una lista de strings para guardar todos los datos del libro.
            String datos[]= new String[3];
            //Creamos un nodo temporal para el bucle.
            Node ntemp= null;
            //Creamos un contador para contar los datos añadidos.
            int contador = 1;
            //Sacamos el valor del nodo enviado a la funcion cuando sea llamada en otra funcion.
            datos[0] = n.getAttributes().item(0).getNodeValue();
            //Creamos una lista de nodos con los nodos hijos del nodo anteriormente seleccionado.
            NodeList nodos = n.getChildNodes();
            
            for(int i=0; i<nodos.getLength(); i++){
                //Guardamos en la variable cada temporal cada nodo de la lista para hacer una funcion if despues.
                ntemp = nodos.item(i);
                //Sacamos el valor del nodo metido en la variable anteriormente
                if(ntemp.getNodeType() == Node.ELEMENT_NODE){
                    datos[contador] = ntemp.getFirstChild().getNodeValue();
                    contador++;
                }
            }
            return datos;
        } catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
}
