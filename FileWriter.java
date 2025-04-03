import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.TreeMap;
public class FileWriter {
    public FileWriter(){

    }
    public void CreerUnFichier(Path dicoAlgorithme) {
        if (!Files.exists(dicoAlgorithme)) {
            try {
                // Créez un nouveau fichier
                Files.createFile(dicoAlgorithme);
                System.out.println("Le fichier a été créé avec succès.");
            } catch (Exception e) {
                System.err.println("Une erreur s'est produite lors de la création du fichier : " + e.getMessage());
            }
        } else {
            System.out.println("Le fichier existe déjà.");
        }
        System.out.println("Sortie Création");
        // Ouvrir le fichier en mode APPEND
    }

    public void EcrireSurUnFichier(Path dicoAlgorithme,String key, StringBuilder keyHash){
        StandardOpenOption mode = StandardOpenOption.APPEND;
        Charset c = Charset.forName("UTF-8");
        try {
            // Créer un BufferedWriter pour écrire dans le fichier
            BufferedWriter ecritSurDicoAlgorithme = Files.newBufferedWriter(dicoAlgorithme, c, mode);
            // Ajouter du contenu au fichier
            long tailleFichier = Files.size(dicoAlgorithme);
            if (tailleFichier != 0) {
                ecritSurDicoAlgorithme.newLine();
            } 
            ecritSurDicoAlgorithme.write(key+"="+keyHash);
            // Fermer le BufferedWriter
            ecritSurDicoAlgorithme.close();
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public TreeMap<String,String> lireFichierDictionnaire(String dicoAlgorithm){
        Path f =Paths.get(dicoAlgorithm);
        TreeMap<String,String> lines=new TreeMap<String,String>();

        try {
            BufferedReader bfr =Files.newBufferedReader(f);
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    // Stocker la première partie comme clé et la deuxième partie comme valeur
                    lines.put(key, value);
                }
            }
            bfr.close();
        } catch (IOException e) {
            
        }
        return lines;
    }

    
}
