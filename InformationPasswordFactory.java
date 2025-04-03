public class InformationPasswordFactory {
    public static InformationPassword getInstance(String choix) throws Throwable {
        InformationPassword info =null;
        CommandeLine affichage = new CommandeLine();
        affichage.effacerConsole();
        if (choix.equalsIgnoreCase("4")) {
            info=new AjoutPassword();
        }
        else if(choix.equalsIgnoreCase("5")){
            info=new VoirSortiePassword();
        }
        else{
            throw new Throwable();
        }
        return info;
    }
}
