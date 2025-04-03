import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DictionaryPasswordCracker extends PasswordCracker {
    public DictionaryPasswordCracker(){
        this.TypeCrackage="Dictionnaire";
    }

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader bread = new BufferedReader(isr);
    static StringBuilder texte=new StringBuilder("Rien");
    public static void menuPrintDico(){
        System.out.println("                          #################################                              \r\n" + //
                           "                         ##                               ##                              \r\n" + //
                           "                         #                                ####                              \r\n" + //
                           "                         ##                                #####                              \r\n" + //
                           "                          ####                              #####        1.Faire L'attaque                       \r\n" + //
                           "                          ###### ################################                          \r\n" + //
                           "                          ###### ################################                          \r\n" + //
                           "                          ###### ##                          ####                          \r\n" + //
                           "                          ###### ################################        2.Revenir en Arrière                  \r\n" + //
                           "                          ###### ################################                          \r\n" + //
                           "                          ###### #####                     ######                          \r\n" + //
                           "                          ###### #####                     ######                          \r\n" + //
                           "                          ###### #####                     ######                          \r\n" + //
                           "                          ###### #####    /\\         ####  ######                          \r\n" + //
                           "                          ###### #####   /  \\         //   ######                          \r\n" + //
                           "                          ###### #####  /####\\ ####  //    ######                          \r\n" + //
                           "                          ###### ##### /      \\     //###  ######                          \r\n" + //
                           "                          ###### #####                     ######                          \r\n" + //
                           "                          ###### #####                     ######                          \r\n" + //
                           "                          ###### #####                     ######                          \r\n" + //
                           "                          ###### ################################                          \r\n" + //
                           "                          ###### ################################                          \r\n" + //
                           "                            #####################################                          \r\n" + //
                           "    ,---,                       ___                                                                            \r\n" + //
                           "  .'  .' `\\   ,--,            ,--.'|_   ,--,                                             ,--,                  \r\n" + //
                           ",---.'     \\,--.'|            |  | :,',--.'|    ,---.       ,---,      ,---,           ,--.'|    __  ,-.       \r\n" + //
                           "|   |  .`\\  |  |,             :  : ' :|  |,    '   ,'\\  ,-+-. /  | ,-+-. /  |          |  |,   ,' ,'/ /|       \r\n" + //
                           ":   : |  '  `--'_      ,---..;__,'  / `--'_   /   /   |,--.'|'   |,--.'|'   | ,--.--.  `--'_   '  | |' |,---.  \r\n" + //
                           "|   ' '  ;  ,' ,'|    /     |  |   |  ,' ,'| .   ; ,. |   |  ,\"' |   |  ,\"' |/       \\ ,' ,'|  |  |   ,/     \\ \r\n" + //
                           "'   | ;  .  '  | |   /    / :__,'| :  '  | | '   | |: |   | /  | |   | /  | .--.  .-. |'  | |  '  :  //    /  |\r\n" + //
                           "|   | :  |  |  | :  .    ' /  '  : |__|  | : '   | .; |   | |  | |   | |  | |\\__\\/: . .|  | :  |  | '.    ' / |\r\n" + //
                           "'   : | /  ;'  : |__'   ; :__ |  | '.''  : |_|   :    |   | |  |/|   | |  |/ ,\" .--.; |'  : |__;  : |'   ;   /|\r\n" + //
                           "|   | '` ,/ |  | '.''   | '.'|;  :    |  | '.'\\   \\  /|   | |--' |   | |--' /  /  ,.  ||  | '.'|  , ;'   |  / |\r\n" + //
                           ";   :  .'   ;  :    |   :    :|  ,   /;  :    ;`----' |   |/     |   |/    ;  :   .'   ;  :    ;---' |   :    |\r\n" + //
                           "|   ,.'     |  ,   / \\   \\  /  ---`-' |  ,   /        '---'      '---'     |  ,     .-.|  ,   /       \\   \\  / \r\n" + //
                           "'---'        ---`-'   `----'           ---`-'                               `--`---'    ---`-'         `----'     ");
    }

    public void afficherMenu(){
        affichage.effacerConsole();
        menuPrintDico();
        String userCodeChiffrer=null;
        boolean choixValide = false;
        boolean quitter = false;     
        affichage.effacerConsole(); 
        menuPrintDico();
            do {
                System.out.print("\t\t\tFaites Votre Choix(1\\2):");
                String userChoix=null;  
                try {
                    userChoix = bread.readLine().trim();
                } catch (IOException e) {
                    
                }
                if (userChoix.matches("[1-2]")) {
                    choixValide = true;
                    switch (userChoix) {
                        case "1":
                                quitter=false;
                                System.out.print("Mettez le Code Chiffrer:");
                                try {
                                    userCodeChiffrer=bread.readLine().trim();
                                    int i=0;
                                    while (userCodeChiffrer.length() != 64 || !userCodeChiffrer.matches("[a-z0-9]+")) {
                                        if (i==0) {
                                            System.out.println("Mettez le Code Chiffrer:");
                                            i++;
                                        }
                                        System.out.println("\033[1A");  //Remonter d'une ligne
                                        System.out.print("\033[K"); //supprime la ligne courante
                                        System.out.print("\033[1A");
                                        System.out.println("\033[1A");  //Remonter d'une ligne
                                        System.out.print("\033[K"); //supprime la ligne courante
                                        System.out.print("\033[1A");
                                        System.out.print("\033[K"); //supprime la ligne courante
                                        System.out.println("Saisie Incorrecte:");
                                        System.out.print("Mettez le Code Chiffrer:"); 
                                        userCodeChiffrer=bread.readLine().trim();
                                    }
                                    crack(userCodeChiffrer);
                                    recommencerCrack();
                                } catch (IOException e) {
                                }
                            quitter=true;
                            break;
                        case "2":
                            quitter=true;
                            break;
                    }
                }
                else {
                    quitter=false;
                    choixValide=false;
                    System.out.println("\033[1A");  //Remonter d'une ligne
                    System.out.print("\033[K"); //supprime la ligne courante
                    System.out.print("\033[1A");
                    System.out.println("\033[1A");  //Remonter d'une ligne
                    System.out.print("\033[K"); //supprime la ligne courante
                    // System.out.print("\033[1A");
                }
        } while (!choixValide || !quitter);
    }
    public static void crack(String codeHashe) {
        System.out.println("Parcours Du Dictionnaire...");
        Path chemin = Paths.get("Sha256.txt");
        StringBuilder code = null; 
        try {
            BufferedReader lirefichier = Files.newBufferedReader(chemin);
            String splitchaine;
            while ((splitchaine = lirefichier.readLine()) != null) {
                String[] tokens = splitchaine.split("=");
                if (codeHashe.equals(tokens[1])) {
                    code = new StringBuilder(tokens[0]); 
                    break;
                }
            }
            // Afficher le code déchiffré (si trouvé)
            if (code != null) {
                System.out.println("Code Déchiffré: "+code);
            } else {
                System.out.println("Le code n'a pas été trouvé dans le dictionnaire");
            }
        } catch (IOException e) {
            // Gérer l'exception ici
            System.err.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
        }
    }



    public static void recommencerCrack(){
        System.out.println("Voulez-vous dechiffrer un autre mot de passe");
        System.out.print("\t\tFaites Votre Choix(1-Oui\\2-Non):");
        String userCodeChiffrer=null;
        String userChoix=null;
        boolean quitter=false;
        while (!quitter) {
            try {
                userChoix = bread.readLine().trim();
                System.out.println();
                switch (userChoix) {
                    case "1":
                        System.out.print("Mettez le Code Chiffrer:"); 
                        userCodeChiffrer=bread.readLine().trim();
                        while (userCodeChiffrer.length() != 64 || !userCodeChiffrer.matches("[a-z0-9]+")) {
                            System.out.println("\033[1A");  //Remonter d'une ligne
                            System.out.print("\033[K"); //supprime la ligne courante
                            System.out.print("\033[1A");
                            System.out.println("\033[1A");  //Remonter d'une ligne
                            System.out.print("\033[K"); //supprime la ligne courante
                            System.out.print("\033[1A");
                            System.out.print("\033[K"); //supprime la ligne courante
                            System.out.println("Saisie Incorrecte:");
                            System.out.print("Mettez le Code Chiffrer:"); 
                            userCodeChiffrer=bread.readLine().trim();

                        }                                                
                        crack(userCodeChiffrer);
                        System.out.println("Voulez-vous encore chiffrer par dictionnaire");
                        System.out.print("\t\tFaites Votre Choix(1-Oui\\2-Non):");    
                        quitter=false;
                        break;
                    case "2":
                        quitter=true;
                        break;
                    default:
                        System.out.println("\033[1A");  //Remonter d'une ligne
                        System.out.print("\033[K"); //supprime la ligne courante
                        System.out.print("\033[1A");
                        System.out.println("\033[1A");  //Remonter d'une ligne
                        System.out.print("\033[K"); //supprime la ligne courante
                        System.out.print("\033[1A");
                        System.out.print("\033[K"); //supprime la ligne courante
                        System.out.print("\tFaites Votre Choix(1-Oui\\2-Non):");                                          
                        break;
                }
            } catch (Exception e) {
            }
        }
    }



}
