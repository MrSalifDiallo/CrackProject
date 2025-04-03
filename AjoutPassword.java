import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
/**
 * Cette classe est une extension de InformationPassword
 * @author Mariama Baldé/Salif Diallo/Ndeye Penda Diene/Mame Sira Diop/Ndeye Ndioro Diop
 * @version 1.0
 * @param 
 * @return Il retourne le sous Menu (4-Connaitre la sortie de Hashage d'un mot de passe) 
*/
public class AjoutPassword extends InformationPassword implements PartieDev {
    static CommandeLine affichage= new CommandeLine();
    public AjoutPassword(){
        this.nomPartieInfoPassword="Ajout Password";
    }
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader bread = new BufferedReader(isr);
    static StringBuilder texte=new StringBuilder("Rien");
    /**Permet de retourner le Sous Menu (4-Connaitre la sortie de Hashage d'un mot de passe)
     * 
     */
    public void menuPrintAjout(){
         System.out.println("                                                                             \r\n" + //
                            "    ██████████████████████████████████████████████                             \r\n" + //
                            "███                                            ███                           \r\n" + //
                            "██   █████                                        ██                          \r\n" + //
                            "██  ██  ██                                        ██                          \r\n" + //
                            "██   █████                                        ██                  1.Chiffrer Un Mot de Passe\r\n" + //
                            "██                                                ██                          \r\n" + //
                            "████████████████████████████████████████████████████                          \r\n" + //
                            "██                                                ██                  2.Lister les mots de passes chiffrés\r\n" + //
                            "██                     ██████        ███████████  ██                          \r\n" + //
                            "██                 █████     ████  ███         ███                            \r\n" + //
                            "██              ███              ███     ███     ███                  3.Rechercher un Mot de Passe Chiffré\r\n" + //
                            "██            ███      ██████   ██    ██████████   ██                         \r\n" + //
                            "██           ███      ██    ██████  ███       ███   ██                        \r\n" + //
                            "██          ██       ██      ████   ██          ██  ██                4.Revenir en Arrière\r\n" + //
                            "██          ██        ██    █████  ███          ██  ███                       \r\n" + //
                            "██         ██         ████████ ██   ██          ██  ███                       \r\n" + //
                            "██         ██       ███      ████   ██          ██  ███                       \r\n" + //
                            "██         ██      ██      ███████████████████████████████                    \r\n" + //
                            "██          ██    ██      ██                             ███                  \r\n" + //
                            "██          ██    ██      ██   ███  █████    ███  █████   ██                  \r\n" + //
                            "██           ██   ██████████  ████  ██ ██   ████  ██ ██   ██                  \r\n" + //
                            "██            ███         ██    ██  █████    ███  █████   ██                  \r\n" + //
                            "██              ████      ██                              ██                  \r\n" + //
                            "██                ██████  ██   ███   ████   ████    ██    ██                  \r\n" + //
                            "██                    ███ ██  ████  ██ ██  ██ ██   ███    ██                  \r\n" + //
                            "██                        ██    ██  █████  █████    ██    ██                  \r\n" + //
                            "███                      ██          █      █            ██                  \r\n" + //
                            "    ████████████████████████████████████████████████████████          \r\n");
}

    
    /**Appel la fonction menuPrintAjout() 
     * Une boucle while qui ne sort de celle ci que si le choix est 4-Revenir en arriere
     */
    public  void afficherMenu() {
    boolean choixValide = false;
    boolean quitter = false;     
    affichage.effacerConsole(); 
    menuPrintAjout();
    System.out.print("\t\t\tFaites Votre Choix(1\\2\\3\\4):");
            do {
                String userChoix=null;  
                try {
                    userChoix = bread.readLine().trim();
                } catch (IOException e) {
                    
                }
                texte.append(userChoix);    
                if (userChoix.matches("[1-4]")) {
                    choixValide = true;
                    switch (userChoix) {
                        case "1":
                            afficherMenuChiffrer();
                            affichage.effacerConsole();
                            menuPrintAjout();
                            quitter=false;
                            break;
                        case "2":
                            affichage.effacerConsole();
                            menuPasswordCrypt();
                            affichage.effacerConsole();
                            menuPrintAjout();
                            quitter=false;
                            // Afficher le menu pour lister les mots de passe chiffrés
                            break;
                        case "3":
                            affichage.effacerConsole();
                            menuPrintTemporary();
                            affichage.effacerConsole();
                            menuPrintAjout();
                            quitter=false;
                            break;
                            // Afficher le menu pour rechercher un mot de passe chiffré
                        case "4":
                            quitter = true;
                            break;
                    }
                    System.out.println();
                }
                else {
                    quitter=false;
                    choixValide=false;
                     
                }
                    System.out.print("\033[1A"); // Remonter d'une ligne
                    System.out.print ("\033[K"); // supprime la ligne courante
                    System.out.print("\t\t\tFaites Votre Choix(1\\2\\3\\4):");
                // System.out.print("\t\t\tFaites Votre Choix(1\\2\\3\\4):");
        } while (!choixValide || !quitter);
    }
// 1.Chiffrer Un Mot de Passe
    public static void afficherMenuChiffrer(){
        boolean choixValide = false;
        boolean quit = false;
        affichage.effacerConsole();
        texte.delete(0, texte.length()-1);
        System.out.println(".------..------..------.\r\n" + //
                        "|S.--. ||E.--. ||N.--. |\r\n" + //
                        "| :/\\: || (\\/) || :(): |\r\n" + //
                        "| :\\/: || :\\/: || ()() |\r\n" + //
                        "| '--'S|| '--'E|| '--'N|\r\n" + //
                        "`------'`------'`------'\r\n" + //
                        ".------..------..------..------..------..------..------..------..------..------..------.\r\n" + //
                        "|C.--. ||H.--. ||I.--. ||F.--. ||F.--. ||R.--. ||E.--. ||M.--. ||E.--. ||N.--. ||T.--. |\r\n" + //
                        "| :/\\: || :/\\: || (\\/) || :(): || :(): || :(): || (\\/) || (\\/) || (\\/) || :(): || :/\\: |\r\n" + //
                        "| :\\/: || (__) || :\\/: || ()() || ()() || ()() || :\\/: || :\\/: || :\\/: || ()() || (__) |\r\n" + //
                        "| '--'C|| '--'H|| '--'I|| '--'F|| '--'F|| '--'R|| '--'E|| '--'M|| '--'E|| '--'N|| '--'T|\r\n" + //
                        "`------'`------'`------'`------'`------'`------'`------'`------'`------'`------'`------'                                                                                                                                    \r\n" + //
                        "                ########                                              \r\n" + //
                        "             ##############                                           \r\n" + //
                        "             ### ####### ###                                          \r\n" + //
                        "                ############## ##      #####    #           ##            \r\n" + //
                        "           ######       ###################################           \r\n" + //
                        "           #####        ###### # ##########   #############           \r\n" + //
                        "           ######       ############################ ######           \r\n" + //
                        "           ######### #########                 ############           \r\n" + //
                        "             ### ####### ##                    ############           \r\n" + //
                        "              ###### ######                    ###     ####           \r\n" + //
                        "                ########");
        do {
            System.out.print("Ecrire le mot que vous voulez hasher:");
            texte.append("Ecrire le mot que vous voulez hasher:");
            try {
                String codeACrypter=bread.readLine();
                String codechiffrer=chiffrementMotDePasse(codeACrypter);
                System.out.println("Mot de passe '"+codeACrypter+"' donne au chiffrement:"+codechiffrer);
                texte.append(codeACrypter);
                texte.append("Mot de passe ");
                texte.append(codeACrypter);
                texte.append(" donne au chiffrement:");
                texte.append(codechiffrer);
            } catch (IOException e) {

            }
            String userChoix=null;
            int tailleChaine=0;
            System.out.print("Voulez Refaire un Chiffrement(1=>Oui/2=>Non):");
            texte.append("Voulez Refaire un Chiffrement(1=>Oui/2=>Non):");
            try {
                userChoix = bread.readLine().trim();
                texte.append(userChoix);
            } catch (IOException e) {
                
            }         
            tailleChaine=texte.length();
            texte.delete(0, tailleChaine-1);
            if (userChoix.matches("[1-2]")) {
                choixValide = true;
                switch (userChoix) {
                    case "1":
                        quit=false;
                        break;
                    case "2":
                        quit = true; // Mettre quit à vrai pour sortir de la boucle
                        break;
                    default:
                        break;
                }
            }
            else{
                for (int i = 0; i < tailleChaine; i++) {
                    System.out.print("\b");
                }
                choixValide=false;
                quit=false;
            } 
        /************************************ */
    } while (!choixValide || !quit); // Continuer tant que le choix n'est pas valide et que l'utilisateur ne veut pas quitter
}

//***************************************************************************** */


    public static String chiffrementMotDePasse(String codeACrypter){

        Path chemin = Paths.get("Sha256.txt");
        FileWriter ecrit = new FileWriter();
        MessageDigest msg;
        try {
            msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(codeACrypter.getBytes(StandardCharsets.UTF_8));
            StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        ecrit.EcrireSurUnFichier(chemin, codeACrypter,s);
        return s.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Erreur";
        }
    }

    /************************************************************************* */


    //2.Lister Mot de passe chiffrés
    public static void menuPasswordCrypt(){
        boolean choixValide = false;
        boolean quit = false;
        TreeMap<String,String> lines=new TreeMap<String,String>();
        FileWriter fichierDico= new FileWriter();
        lines=fichierDico.lireFichierDictionnaire("Sha256.txt");
        texte.delete(0, texte.length()-1);
                System.out.print("------------------------------------------------------------------------------------------------+\r\n" + //
                           "==================================#%@##########%%@%@###########*=================================  \r\n" + //
                           "====++=====+++=====++=====++======#%@########%@#####@##########*=%@*=======@%=======*@#=======*@#  \r\n" + //
                           "||@%%@@#=@%%@%=%@%*(*+@(##%(@@(@*=#@@###%*%*#**%*##%##@%###@@###%#@*@@*+@@#%%#@@+#@@#@#%@%+@@#%%#@@  \r\n" + //
                           "||*==*@@+||*#%||=(@@%*==(@@#+=*@%=%@@###@+*#*#*#+##@@@%###@##%###@@%*====+%@@#+====*@@@*====+%@@#  \r\n" + //
                           "||#==#@@+@@==*@@====*@]====%@]=@@%@@@############%%%####%%%#######@*%@*+@%*%%*@@+*@%*@*#@%=*@%*@*#@  \r\n" + //
                           "||#@@@#==||@@#||=(@@%*+(@@(#===@@@%@#############%%####%%#####*=%@*=======@@=======*@#=======*@#  \r\n" + //
                           "||*===============================#%@############%#%##%#%######*================================= \r\n" + //
                           "                                  |         Sha-256             |\r\n" + //
                           "+------------------------------------------------------------------------------------------------+\r\n");
                           String format = "|%1$-33s|%2$-29s|%3$-32s|%n";
                           for (Map.Entry<String,String> ligneDictionnaire : lines.entrySet()) {
                                String part1 =ligneDictionnaire.getValue().substring(0, 32);
                                String part2=ligneDictionnaire.getValue().substring(32);
                                System.out.printf(format, "","",part1);             
                                System.out.printf(format,ligneDictionnaire.getKey(),"Sha-256",part2);  
                                System.out.printf(format,"_________________________________","_____________________________","________________________________");                        
                           }
                           System.out.println("+================================================================================================+");
        do {
            // System.out.print("Recherche de Mot:");
            System.out.print("Tapez Entrée pour Quitter!");
            String userChoix=null;
            texte.append("Recherche de Mot:");
            try {
                userChoix = bread.readLine().trim();
                texte.append(userChoix);
            } catch (IOException e) {
                
            }         
            quit=true;
            choixValide=true;
        /************************************ */
    } while (!choixValide || !quit);


    }
    
}





