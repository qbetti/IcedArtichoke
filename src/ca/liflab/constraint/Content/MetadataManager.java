package ca.liflab.constraint.Content;


import ca.liflab.Action;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.text.ParseException;
import javax.xml.transform.TransformerException;

public final class MetadataManager
{
    public static void main( String[] args )
    {
        String s = searchTrace("test/trace-test.txt");
        File file = new File("Page.pdf");
        try {
            PDDocument doc = PDFManager.getDocument(file);
            addMetadata(doc, s);
            String trace = extractMetadataTrace(file);
            Action[] actions = stringToAction(trace);
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère trace dans le fichier txt
     * @return
     */
    public static String searchTrace(String file)
    {
        String ligne = "";
        try{
            //Contenu du fichier txt
            InputStream flux = new FileInputStream(file);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);

            //Parcours les lignes
            while ((ligne = buff.readLine())!=null){
                //Si la ligne est une trace
                if (ligne.length() !=0 && ligne.charAt(0) == '{') {
                    break;
                }
            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return ligne;
    }

    /**
     * Ajoute la trace en metadata au document PDF
     * @param trace
     * @throws IOException
     * @throws TransformerException
     */
    public static void addMetadata(PDDocument doc, String trace) throws IOException, TransformerException
    {
        try
        {
            //Informations du PDF
            PDDocumentInformation info = doc.getDocumentInformation();

            //Ajoute la trace en metadata
            info.setCustomMetadataValue("Trace", trace);

            doc.save( "Page.pdf" );
        }
        finally
        {
            if( doc != null )
            {
                doc.close();
            }
        }
    }

    /**
     * Récupère la trace en metadata du PDF
     * @return
     * @throws TikaException
     * @throws SAXException
     * @throws IOException
     */
    public static String extractMetadataTrace(File file) throws TikaException, SAXException, IOException
    {
        //Méthodes de parse
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        parser.parse(inputstream, handler, metadata, context);

        //Récupérer la liste des noms de metadata
        String[] metadataNames = metadata.names();

        String trace = "";

        //Si la metadata est la Trace on récupère son contenu
        for(String name : metadataNames) {
            if (name.equals("Trace")) {
                trace = metadata.get(name);
            }
        }

        return trace;
    }

    /**
     * Transforme la trace en tableau d'actions
     * @param trace
     * @return
     * @throws ParseException
     */
    public static Action[] stringToAction(String trace) throws ParseException
    {
        //Délimite chaque partie de la trace
        String[] actionsString = trace.split(",");
        Action[] actions = new Action[actionsString.length];
        int i = 0;
        //Ajoute l'action
        for (String s : actionsString) {
            actions[i] = Action.parseString(s);
            System.out.println(actions[i].toString());
            i++;
        }
        return actions;
    }

}