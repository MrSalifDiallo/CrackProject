public class PasswordCrackerFactory {
    public PasswordCracker getInstance(String typeAttaque) throws Throwable {
        PasswordCracker crackeur = null;    
        CommandeLine affichage = new CommandeLine();
        affichage.effacerConsole();
        if (typeAttaque.equalsIgnoreCase("1")) {
            crackeur = new BruteForcePasswordCracker();
        } 
        else if(typeAttaque.equalsIgnoreCase("2")){
            crackeur = new DictionaryPasswordCracker();
        }
        else if(typeAttaque.equalsIgnoreCase("3")) {
            crackeur=new MixtePasswordCracker();
        } 
        else {
            throw new Throwable();
        }
        return crackeur;
    }
}
