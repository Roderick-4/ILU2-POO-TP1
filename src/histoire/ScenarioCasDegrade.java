package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois asterix = new Gaulois("Asterix", 10);
		//1 liberer etal vide
		try {
			etal.libererEtal();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		//2 acheter produit
		//a) acheteur null
		try {
			etal.acheterProduit(5, null);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		//b) quantite negative
		try {
			etal.acheterProduit(-3, asterix);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		//c) acheter produit etal non occupe
		try {
			etal.acheterProduit(3, asterix);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		
		//3 village sans chef
		Village village = new Village("paschef", 20, 5);
		try {
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Fin du test");
		}

}
