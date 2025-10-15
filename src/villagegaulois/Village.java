package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	
	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i=0; i<nbEtals;i++) {
				etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduits) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduits);
		}
		
		private int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtals = 0;
			for (int i=0; i<etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					nbEtals++;
				}
			}
			if (nbEtals == 0) {
				return new Etal[0];
			}
			Etal [] etalProduit = new Etal[nbEtals];
			int indiceTab = 0;
			for (int i=0; i<etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					etalProduit[indiceTab] = etals[i];
					indiceTab++;
				}
			}
			return etalProduit;			
		}
		
		private Etal trouverEtalVendeur(Gaulois gaulois) {
			for (int i=0; i<etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;
			chaine.append("Le marche du village possede plusieurs etals : \n");
			for(int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
					chaine.append("\n");
				} else {
					nbEtalVide++;
				}
			}
			if (nbEtalVide != 0) {
				chaine.append("Il reste ").append(nbEtalVide).append(" etals non utilises dans le marche.\n");
			}
			return chaine.toString();
		}
		
	}

	
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
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
			chaine.append("Il n'y a encore aucun habitant au village du chef ").append(chef.getNom()).append(".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- ").append(villageois[i].getNom()).append("\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		if (vendeur == null || produit == null || nbProduit < 0) {
			return null;
		}
		StringBuilder chaine = new StringBuilder();
		int indiceEtal = marche.trouverEtalLibre();
		
		if(indiceEtal == -1) {
			chaine.append("Aucun étal n'est disponible pour ");
			chaine.append(vendeur.getNom());
			chaine.append(".\n");
			return chaine.toString();
		}
		chaine.append(vendeur.getNom()).append(" cherche un endroit pour vendre ").append(nbProduit)
			.append(" ").append(produit).append(".\n");
		
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		
		chaine.append("Le vendeur ").append(vendeur.getNom()).append(" vend des ").append(produit)
			.append(" a l'etal n° ").append(indiceEtal + 1).append(".\n");
		
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		if(produit == null) {
			return null;
		}
		StringBuilder chaine  = new StringBuilder();
		Etal[] etals = marche.trouverEtals(produit);
		switch(etals.length) {
			case 0 -> chaine.append("Il n'y a pas de vendeurs qui propose des ").append(produit).append(" au marche.\n");
			case 1 -> chaine.append("Seul le vendeur ").append(etals[0].getVendeur().getNom()).append(" possede des ")
					.append(produit).append(" au marche.\n");
			default -> {
				chaine.append("Les vendeurs qui proposent des ").append(produit).append(" sont : \n");
				for(int i=0; i<etals.length; i++) {
					chaine.append("- ").append(etals[i].getVendeur().getNom()).append("\n");
				}
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		if (vendeur == null) {
			return null;
		}
		return marche.trouverEtalVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		if (vendeur == null) {
			return null;
		}
		Etal etal = rechercherEtal(vendeur);
		if (etal == null) {
			return "Le vendeur " + vendeur.getNom() + " n'a pas d'etal au marche.";
		}
		return etal.libererEtal();
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}

}