import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/*
 * Extension de la classe PasswordCracker
 */
public class BruteForcePasswordCracker extends PasswordCracker {
    public BruteForcePasswordCracker() {
        this.TypeCrackage = "Brute Force";
    }

    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader bread = new BufferedReader(isr);
    static StringBuilder texte = new StringBuilder("Rien");
/**
 * Cette fonction complete le tableau pour estimer a peu pres le temps de décryptage des mots de passes 
 * Selon le nombre de caractère et les types. 
 * Qu'on a trouvé grace 
 * @param instructionParSecondes
 */
    public void menuPrint(long instructionParSecondes) {
        affichage.effacerConsole();
        System.out.println(" _______________________________________________________________________________________");
        System.out.println("|N Carac |   Nombres    |  Min || Maj   |   Min && Maj  | Alphanumerique |     Tous     |");
        System.out.println("|________|____(10)______|_____(26)______|______(52)_____|______(62)______|______(95)____|");
        // Ici ce format nous permet de dire que pour la premiere colonne il a un espace
        // maximal de 8 caractere
        String format = "|%1$-8s|%2$-14s|%3$-15s|%4$-15s|%5$-16s|%6$-14s|%n";
        String[] tabMenu = { "1.Nombres", "2.Min ou Maj", "3.Min et Maj", "4.AlphaNum", "5.Tous" };
        int positionTabMenu = 0;
        String formatMenu = "|%1$-8s|%2$-14s|%3$-15s|%4$-15s|%5$-16s|%6$-14s|    %7$-15s%n";
        double nombres = 0;
        double minMaj = 0;
        double minMajComb = 0;
        double alphanumerique = 0;
        double tous = 0;
        for (int i = 1; i <= 3; i++) {
            nombres += Math.pow(10, i);
            minMaj += Math.pow(26, i);
            minMajComb += Math.pow(52, i);
            alphanumerique += Math.pow(62, i);
            tous += Math.pow(95, i);
        }

        for (int nCarac = 4; nCarac <= 16; nCarac++) {
            nombres += Math.pow(10, nCarac);
            minMaj += Math.pow(26, nCarac);
            minMajComb += Math.pow(52, nCarac);
            alphanumerique += Math.pow(62, nCarac);
            tous += Math.pow(95, nCarac);
            String nombreResult = (instructionParSecondes == 0) ? "-" : formatTime(nombres / instructionParSecondes);
            String minMajResult = (instructionParSecondes == 0) ? "-" : formatTime(minMaj / instructionParSecondes);
            String minMajCombResult = (instructionParSecondes == 0) ? "-"
                    : formatTime(minMajComb / instructionParSecondes);
            String alphanumeriqueResult = (instructionParSecondes == 0) ? "-"
                    : formatTime(alphanumerique / instructionParSecondes);
            String tousResult = (instructionParSecondes == 0) ? "-" : formatTime(tous / instructionParSecondes);

            if (instructionParSecondes != 0 && (nCarac == 4 || (nCarac - 1) % 3 == 0)) {
                System.out.printf(formatMenu, nCarac, nombreResult, minMajResult, minMajCombResult,
                        alphanumeriqueResult, tousResult, tabMenu[positionTabMenu++]);
            } else {
                System.out.printf(format, nCarac, nombreResult, minMajResult, minMajCombResult, alphanumeriqueResult,
                        tousResult);
            }
        }

        System.out.println("|________|______________|_______________|_______________|________________|______________|");
        if (instructionParSecondes != 0) {
            int numCores = Runtime.getRuntime().availableProcessors();
            String osName = System.getProperty("os.name");
            System.out.println("Nombre de coeurs CPU disponibles : " + numCores);
            System.out.println("Systeme d'Exploitation:" + osName);
            System.out.println("Processeur:" + System.getenv("PROCESSOR_IDENTIFIER"));
        }
    }

    private String formatTime(double secondes) {
        double millisecondes = secondes * 1000;
        if (millisecondes < 1000) {
            return Math.round(millisecondes) + "ms";
        }

        long totalSeconds = (long) secondes;
        // long seconds = totalSeconds % 60;

        long totalMinutes = totalSeconds / 60;
        // long minutes = totalMinutes % 60;

        long totalHours = totalMinutes / 60;
        // long hours = totalHours % 24;

        long totalDays = totalHours / 24;
        // long days = totalDays % 365;

        long totalYears = totalDays / 365;
        // long years = totalYears % 365;

        long totalMillionsYears = totalYears / 1000000;
        // long millionsYears = totalMillionsYears % 1000000;

        long totalBillionsYears = totalYears / 1000000000;

        if (totalBillionsYears > 0) {
            return totalBillionsYears + "Mds an";
        } else if (totalMillionsYears > 0) {
            return totalMillionsYears + "Mns an";
        } else if (totalYears > 0) {
            if (totalDays == 1) {
                return totalYears + "an";
            } else {
                return totalYears + "ans";
            }
        } else if (totalDays > 0) {
            return totalDays + "j";
        } else if (totalHours > 0) {
            return totalHours + "h";
        } else if (totalMinutes > 0) {
            return totalMinutes + "mn";
        } else {
            return totalSeconds + "s";
        }
    }

    public void afficherMenu() {
        boolean choixValide = false;
        boolean quitter = false;
        String userChoix = null;
        String userCodeChiffrer = null;
        long instructionParSecondes = 0;
        affichage.effacerConsole();
        menuPrint(0);
        System.out.println("\t\tNous vous conseillons de faire une evaluation afin de completer le tableau ci dessus");
        System.out.println("\t\t\tAvant d'utiliser ce type d'attaque");
        texte.delete(0, texte.length());
        System.out.print("1-Evaluation 2-Pas D'evaluation 3-Revenir en Arrière:");
        // texte.append("1-Evaluation 2-Pas D'evaluation 3-Revenir en Arrière:")
        do {
            try {
                userChoix = bread.readLine().trim();
                texte.append(userChoix);
                if (userChoix.matches("[1-3]")) {
                    choixValide = true;
                    switch (userChoix) {
                        case "1":
                        case "2":
                            if (userChoix.equals("1")) {
                                instructionParSecondes = evaluationPc();
                                menuPrint(instructionParSecondes);
                            }
                            System.out.println("Taper 1 pour annuler");
                            System.out.print("Mettez le Code Chiffrer(64 caracteres[a-z0-9]):");
                            int i = 0;
                            userCodeChiffrer = bread.readLine().trim();
                            while ((userCodeChiffrer.length() != 64 || !userCodeChiffrer.matches("[a-z0-9]+"))
                                    && !userCodeChiffrer.equals("1")) {
                                if (i == 0) {
                                    System.out.println("Mettez le Code Chiffrer:");
                                    i++;
                                }
                                System.out.println("\033[1A"); // Remonter d'une ligne
                                System.out.print("\033[K"); // supprime la ligne courante
                                System.out.print("\033[1A");
                                System.out.println("\033[1A"); // Remonter d'une ligne
                                System.out.print("\033[K"); // supprime la ligne courante
                                System.out.print("\033[1A");
                                System.out.print("\033[K"); // supprime la ligne courante
                                System.out.println("Saisie Incorrecte Retaper ou Saisir 1 Pour annuler");
                                System.out.print("Mettez le Code Chiffrer(64 caracteres[a-z0-9]):");
                                userCodeChiffrer = bread.readLine().trim();
                            }
                            if (userCodeChiffrer.matches("1")) {
                                quitter = true;
                                break;
                            }
                            crackWithChrono(userCodeChiffrer);
                            System.out.println("Voulez-vous dechiffrer un autre mot de passe");
                            System.out.print("\tFaites Votre Choix(1-Oui\\2-Non):");
                            while (!quitter) {
                                try {
                                    userChoix = bread.readLine().trim();
                                    switch (userChoix) {
                                        case "1":
                                            System.out.print("Mettez le Code Chiffrer:");
                                            userCodeChiffrer = bread.readLine().trim();
                                            while ((userCodeChiffrer.length() != 64
                                                    || !userCodeChiffrer.matches("[a-z0-9]+"))
                                                    && !userCodeChiffrer.equals("1")) {
                                                System.out.println("\033[1A"); // Remonter d'une ligne
                                                System.out.print("\033[K"); // supprime la ligne courante
                                                System.out.print("\033[1A");
                                                System.out.println("\033[1A"); // Remonter d'une ligne
                                                System.out.print("\033[K"); // supprime la ligne courante
                                                System.out.print("\033[1A");
                                                System.out.print("\033[K"); // supprime la ligne courante
                                                System.out
                                                        .println("Saisie Incorrecte Retaper ou Saisir 1 Pour annuler");
                                                System.out.print("Mettez le Code Chiffrer(64 caracteres[a-z0-9]):");
                                                userCodeChiffrer = bread.readLine().trim();

                                            }
                                            if (userCodeChiffrer.matches("1")) {
                                                quitter = true;
                                                break;
                                            }
                                            crackWithChrono(userCodeChiffrer);
                                            System.out.println("Voulez-vous encore chiffrer");
                                            System.out.print("\tFaites Votre Choix(1-Oui\\2-Non):");
                                            quitter = false;
                                            break;
                                        case "2":
                                            quitter = true;
                                            break;
                                        default:
                                            System.out.println("\033[1A"); // Remonter d'une ligne
                                            System.out.print("\033[K"); // supprime la ligne courante
                                            System.out.print("\033[1A");
                                            System.out.println("\033[1A"); // Remonter d'une ligne
                                            System.out.print("\033[K"); // supprime la ligne courante
                                            System.out.print("\033[1A");
                                            System.out.print("\033[K"); // supprime la ligne courante
                                            System.out.print("\tFaites Votre Choix(1-Oui\\2-Non):");
                                            break;
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    System.out.println(e.getStackTrace());
                                    break;
                                }
                            }
                            break;
                        case "3":
                            quitter = true;
                            break;
                    }
                } else {
                    quitter = false;
                    choixValide = false;
                    System.out.print("\033[1A"); // Remonter d'une ligne
                    System.out.println("\033[K"); // supprime la ligne courante
                    System.out.print("\033[1A");
                    System.out.print("1-Evaluation 2-Pas D'evaluation 3-Revenir en Arrière:");

                }
            } catch (IOException e) {

            }
        } while (!quitter || !choixValide);
    }

    public static void crack(String codeHashe) {
        /** *********************************************/
        String[] tableau = { "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x",
                "y", "z",
                "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X",
                "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        boolean[] motDePasseTrouve = { false }; // Utilisation d'un tableau pour stocker la valeur
        String combinaisonActuelle = "";
        for (int i = 0; i < 6; i++) {
            if (motDePasseTrouve[0]) {
                break;
            } else {
                genererCombinaisons(tableau, i, combinaisonActuelle, codeHashe, motDePasseTrouve);
            }
        }
        /********************************************* */
    }

    /**************************************************************** */

    public static long evaluationPc() {
        String[] tableau = { "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x",
                "y", "z",
                "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X",
                "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        boolean[] motDePasseTrouve = { false };
        long nombreEssai = 0;
        int i = 0;

        // Utiliser ScheduledExecutorService pour arrêter l'exécution après 10 secondes
        System.out.println("Temps Maximal d'évaluation 10s");
        String codeHash = "fd5f56b40a79a385708428e7b32ab996a681080a166a2206e750eb4819186145";// 99999
        long startTime = System.currentTimeMillis();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Thread mainThread = Thread.currentThread();
        scheduler.schedule(() -> {
            mainThread.interrupt();
        }, 10, TimeUnit.SECONDS);

        try {
            while (!motDePasseTrouve[0] && !Thread.currentThread().isInterrupted()) {
                try {
                    nombreEssai += genererCombinaisons2(tableau, i, "", codeHash, motDePasseTrouve);
                } catch (InterruptedException e) {
                    // Interruption détectée, réagir en conséquence
                    break;
                }
                i++;
                if (motDePasseTrouve[0]) {
                    break; // Si le mot de passe est trouvé, sortir de la boucle
                }
            }
        } finally {
            scheduler.shutdown();
        }
        affichage.effacerConsole();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return nombreEssai * 1000 / duration;
    }

    /*************************************************************************** */
    public static void crackWithChrono(String codeACracker) {
        System.out.println("Appuyez sur Entrée pour arrêter...");
        ExecutorService ex = Executors.newCachedThreadPool();
        Runnable task1 = () -> {
            System.out.println("Execution du decryptage");
            crackw(codeACracker, 0);
        };
        Runnable task2 = () -> {
            while (true) {
                Scanner lire = new Scanner(System.in);
                try {
                    if (System.in.available() > 0) {
                        lire.nextLine();
                        lire.close();
                    }
                } catch (Exception e) {
                    break;
                }
                // Thread.currentThread().
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        };

        Runnable task3 = () -> {
            boolean demarrer = true;
            int secondpasse = 0;
            StringBuilder printDansChrono = new StringBuilder();
            while (demarrer) {
                if (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        if (secondpasse == 0 || secondpasse == 1) {
                            printDansChrono.append("Temps ecoule:" + secondpasse + " seconde");
                        } else {
                            printDansChrono.append("Temps ecoule:" + secondpasse + " secondes");
                        }
                        int decalage = printDansChrono.length();
                        System.out.print("\r");
                        System.out.print(printDansChrono);
                        printDansChrono.delete(0, decalage);
                        secondpasse++;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }

                }
            }
        };

        Future<?> futresult1 = ex.submit(task1);
        Future<?> futresult2 = ex.submit(task2);
        Future<?> futresult3 = ex.submit(task3);

        try {
            while (true) {
                if (futresult1.isDone()) {
                    futresult2.cancel(true);
                    futresult3.cancel(true);
                    break;
                } else if (futresult2.isDone()) {
                    // System.out.print("2 is done");
                    futresult1.cancel(true);
                    futresult3.cancel(true);
                    break;
                }
            }
            // Arrêter l'exécuteur et attendre la terminaison des tâches
            ex.shutdown();
            // Attendre que l'exécuteur termine toutes les tâches en cours
            ex.awaitTermination(1, TimeUnit.MINUTES);
        } catch (Exception e) {
        } finally {
            ex.shutdownNow();
        }
    }

    public static void crackw(String codeHashe, int i) {
        if (!Thread.currentThread().isInterrupted()) {
            String[] tableau = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                    "r",
                    "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                    "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5",
                    "6", "7", "8", "9" };
            boolean[] motDePasseTrouve = { false }; // Utilisation d'un tableau pour stocker la valeur
            String combinaisonActuelle = "";
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                if (motDePasseTrouve[0]) {
                    break;
                } else {
                    genererCombinaisonsw(tableau, i++, combinaisonActuelle, codeHashe, motDePasseTrouve);
                }
            }
        }
    }
    /**
     * Ok
     * @param tab C'est le tableau contenant tous les caractères ici on a utilisé un tableau alphanumerique
     * @param longueur la longueur de la combinaision incrementé jusqu'a ce qu'on trouve 
     * @param combinaisonActuelle combinaison actuelle qui est ren
     * @param codeHasheRechercher
     * @param motDePasseTrouve
     * @return 
     */
    public static long genererCombinaisonsw(String[] tab, int longueur, String combinaisonActuelle,
            String codeHasheRechercher, boolean[] motDePasseTrouve) {
        long nombreEssais = 0;
        if (!Thread.currentThread().isInterrupted()) {
            if (combinaisonActuelle.length() == longueur) {
                nombreEssais++;
                if (codeHash(combinaisonActuelle).equals(codeHasheRechercher)) {
                    motDePasseTrouve[0] = true; // Modifier la valeur dans le tableau
                    System.err.println();
                    System.out.print("Code Déchiffré:" + combinaisonActuelle);
                    System.out.println();
                }
            } else {
                for (int i = 0; i < tab.length; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        return nombreEssais;
                    }
                    if (motDePasseTrouve[0]) {
                        return nombreEssais;
                    }
                    nombreEssais += genererCombinaisonsw(tab, longueur, combinaisonActuelle + tab[i],
                            codeHasheRechercher, motDePasseTrouve);
                }
            }
            return nombreEssais;
        } else {
            return nombreEssais;
        }

    }

    /*************************************************************************** */

    public static long genererCombinaisons2(String[] tab, int longueur, String combinaisonActuelle,
            String codeHasheRechercher, boolean[] motDePasseTrouve) throws InterruptedException {
        long nombreEssais = 0;
        if (combinaisonActuelle.length() == longueur) {
            nombreEssais++;
            if (codeHash(combinaisonActuelle).equals(codeHasheRechercher)) {
                motDePasseTrouve[0] = true;
            }
        } else {
            for (int i = 0; i < tab.length; i++) {
                if (motDePasseTrouve[0] || Thread.currentThread().isInterrupted()) {
                    return nombreEssais;
                }
                nombreEssais += genererCombinaisons2(tab, longueur, combinaisonActuelle + tab[i], codeHasheRechercher,
                        motDePasseTrouve);
            }
        }
        return nombreEssais;
    }

    public static String codeHash(String codeNoHasher) {
        MessageDigest msg;
        try {
            msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(codeNoHasher.getBytes(StandardCharsets.UTF_8));
            // convertir bytes en hexadécimal
            StringBuilder s = new StringBuilder();
            for (byte b : hash) {
                s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            String Error = "Cet algorithme n'est pas prise en charge";
            return Error;
        }
    }

    public static long genererCombinaisons(String[] tab, int longueur, String combinaisonActuelle,
            String codeHasheRechercher, boolean[] motDePasseTrouve) {
        long nombreEssais = 0;
        if (combinaisonActuelle.length() == longueur) {
            nombreEssais++;
            if (codeHash(combinaisonActuelle).equals(codeHasheRechercher)) {
                motDePasseTrouve[0] = true; // Modifier la valeur dans le tableau
                System.out.println("Code Déchiffré:" + combinaisonActuelle);
            } else {
            }
        } else {
            for (int i = 0; i < tab.length; i++) {
                if (motDePasseTrouve[0]) {
                    return nombreEssais;
                }
                nombreEssais += genererCombinaisons(tab, longueur, combinaisonActuelle + tab[i], codeHasheRechercher,
                        motDePasseTrouve);
            }
        }
        return nombreEssais;
    }

}
