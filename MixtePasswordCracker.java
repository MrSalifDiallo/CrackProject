import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MixtePasswordCracker extends PasswordCracker {

    public MixtePasswordCracker(){
        this.TypeCrackage="Mixte";
    }

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader bread = new BufferedReader(isr);
    static StringBuilder texte=new StringBuilder("Rien");
    
    public static void menuPrintTemporaryMixte(){
        System.out.println("     _            ,'|     _ |`,      _            ,'|     _ |`,      _      \r\n" + //
                        "(:\\          ///     /:) \\\\\\    (:\\          ///     /:) \\\\\\    (:\\     \r\n" + //
                        " \\:\\        )//     /:/   \\\\(    \\:\\        )//     /:/   \\\\(    \\:\\    \r\n" + //
                        "==\\:(======/:(=====):/=====):\\====\\:(======/:(=====):/=====):\\====\\:(===\r\n" + //
                        "   )\\\\    /:/     //(       \\:\\    )\\\\    /:/     //(       \\:\\    )\\\\  \r\n" + //
                        "    \\\\\\  (:/     ///         \\:)    \\\\\\  (:/     ///         \\:)    \\\\\\ \r\n" + //
                        "     `.|  \"     |.'           \"      `.|  \"     |.'           \"      `.|\r\n" + //
                        "88888888888888888888888888888888888888888888888888888888888888888888888\r\n" + //
                        "88.._|      | `-.  | `.  -_-_ _-_  _-  _- -_ -  .'|   |.'|     |  _..88\r\n" + //
                        "88   `-.._  |    |`!  |`.  -_ -__ -_ _- _-_-  .'  |.;'   |   _.!-'|  88\r\n" + //
                        "88      | `-!._  |  `;!  ;. _______________ ,'| .-' |   _!.i'     |  88\r\n" + //
                        "88..__  |     |`-!._ | `.| |_______________||.\"'|  _!.;'   |     _|..88\r\n" + //
                        "88   |``\"..__ |    |`\";.| i|_|           |_|'| _!-|   |   _|..-|'    88\r\n" + //
                        "88   |      |``--..|_ | `;!|l| Partie    |1|.'j   |_..!-'|     |     88\r\n" + //
                        "88   |      |    |   |`-,!_|_|       EN  |_||.!-;'  |    |     |     88\r\n" + //
                        "88___|______|____!.,.!,.!,!|d|Développe- |p|,!,.!.,.!..__|_____|_____88\r\n" + //
                        "88      |     |    |  |  | |_|    ment   |_|| |   |   |    |      |  88\r\n" + //
                        "88      |     |    |..!-;'i|r|           |r| |`-..|   |    |      |  88\r\n" + //
                        "88      |    _!.-j'  | _!,\"|_|           |_||!._|  `i-!.._ |      |  88\r\n" + //
                        "88     _!.-'|    | _.\"|  !;|1|  Bientôt  |l|`.| `-._|    |``-.._  |  88\r\n" + //
                        "88..-i'     |  _.''|  !-| !|_|           |_|.|`-. | ``._ |     |``\"..88\r\n" + //
                        "88   |      |.|    |.|  !| |u| Disponible|n||`. |`!   | `\".    |     88\r\n" + //
                        "88   |  _.-'  |  .'  |.' |/|_|           |_|! |`!  `,.|    |-._|     88\r\n" + //
                        "88  _!\"'|     !.'|  .'| .'|[@]MMMMMMMMMMM[@] \\|  `. | `._  |   `-._  88\r\n" + //
                        "88-'    |   .'   |.|  |/| /                 \\|`.  |`!    |.|      |`-88\r\n" + //
                        "88      |_.'|   .' | .' |/                   \\  \\ |  `.  | `._-Lee|  88\r\n" + //
                        "88     .'   | .'   |/|  /                     \\ |`!   |`.|    `.  |  88\r\n" + //
                        "88  _.'     !'|   .' | /                       \\|  `  |  `.    |`.|  88\r\n" + //
                        "88 vanishing point 888888888888888888888888888888888888888888888(FL)888\r\n" + //
                        "     _            ,'|     _ |`,      _            ,'|     _ |`,      _      \r\n" + //
                        "(:\\          ///     /:) \\\\\\    (:\\          ///     /:) \\\\\\    (:\\     \r\n" + //
                        " \\:\\        )//     /:/   \\\\(    \\:\\        )//     /:/   \\\\(    \\:\\    \r\n" + //
                        "==\\:(======/:(=====):/=====):\\====\\:(======/:(=====):/=====):\\====\\:(===\r\n" + //
                        "   )\\\\    /:/     //(       \\:\\    )\\\\    /:/     //(       \\:\\    )\\\\  \r\n" + //
                        "    \\\\\\  (:/     ///         \\:)    \\\\\\  (:/     ///         \\:)    \\\\\\ \r\n" + //
                        "     `.|  \"     |.'           \"      `.|  \"     |.'           \"      `.|");
    }


    public void afficherMenu(){
        boolean choixValide = false;
        boolean quitter = false; 
        String userChoix=null;
        affichage.effacerConsole();
        menuPrintTemporaryMixte();
        for (int i = 0; i <78; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i <20; i++) {
            System.out.print("\033[1A");
        }
        System.out.print("Cliquez sur entrée pour sortir:");
        do {
            try {
                int key = System.in.read();
                if (key == 13 || key == 10) {
                    // quitter=true;
                    // choixValide=true;
                    break;
                    // Exécutez votre instruction ici
                }
                // Sinon, ne rien faire (ne pas afficher les autres touches)
                // else {
                //     quitter=false;
                //     choixValide=false;
                //     //System.out.println("\033[K"); //supprime la ligne courante 
                //     System.out.print("\033[1A");
                //     System.out.print("\033[K"); //supprime la ligne courante 
                //     System.out.print("88      |     |    |  |  | |_|    ment   |_|| |   |   |    |      |  88       Sortie:");
                // }
            } catch (IOException e) {
                for (int i = 0; i <20; i++) {
                    System.out.print("\n");
                } 
            }
        } while (!quitter || !choixValide); 
    };


    private static void crack(){

    }
}
