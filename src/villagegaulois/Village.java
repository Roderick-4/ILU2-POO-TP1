package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public static class Marche{
		private Etal [] etals;
		
		public Marche (int nbEtal) {
			etals = new Etal [nbEtal];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal < etals.length) {
				etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
			}
		}
		
		public int trouverEtalLibre() {
			for(int i=0;i<etals.length;i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int nbdeproduits = 0;
			int courant = 0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i].contientProduit(produit)) {
					nbdeproduits++;
				}
			}
			if (nbdeproduits == 0) {
				return null;
			}
			Etal [] products = new Etal[nbdeproduits];
			for(int i=0;i<etals.length;i++) {
				if(etals[i].contientProduit(produit)) {
					products[courant] = etals[i];
				}
			}
			return products;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etals.length;i++){
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder leMarche = new StringBuilder();
			int nbEtalVide = 0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					leMarche.append(etals[i].afficherEtal());
				} else {
					nbEtalVide++;
				}
			}
			leMarche.append("�Il reste "+ nbEtalVide +" �tals non utilis�s dans le marche.\n");
			return leMarche.toString();
		}
		
	}
	
	
}