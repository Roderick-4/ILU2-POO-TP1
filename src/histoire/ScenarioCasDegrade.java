package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
	    // Etal etal = new Etal();
	    // try {
	    //     etal.acheterProduit(420, new Gaulois("idefix", 69));
	    // } catch (IllegalArgumentException e) {
	    //     System.out.println("Marche po, quantitee negative");
	    // } catch (IllegalStateException e) {
	    //     System.out.println("Marche po, etal vide");
	    // }
	    Village village = new Village("aze", 20, 5);
	    try {
	        village.afficherVillageois();    
	    } catch (VillageSansChefException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("Fin du test");
	    }

}
