package main;
import java.io.OutputStream;

/**
 * Classe de simulation de la Virtual Machine
 *
 */
public class YVM {
	private OutputStream f;
	
	/**
	 * Début du programme
	 * @param nomProg	Nom du programme, utilisé pour nomme le fichier de sortie
	 */
	public void startProg(String nomProg){
		Ecriture.ouvrir(nomProg);
		entete();
	}
	
	/**
	 * Fin du programme et fermeture du fichier de sortie
	 */
	public void endProg(){
		Ecriture.fermer(f);
		queue();
	}
	
	/**
	 * Début du programme
	 */
	public void entete(){
		System.out.println("entete");
		Ecriture.ecrireStringln("entete");
	}
	
	/**
	 * Fin du programme
	 */
	public void queue(){
		System.out.println("queue");
		Ecriture.ecrireStringln("queue");
	}
	
	/**
	 * Préparation de la mémoire
	 * @param nbVars	Nombre de variables du programme
	 */
	public void ouvrePrinc(int nbVars){
		System.out.println("ouvrePrinc " + nbVars * 2);
		Ecriture.ecrireStringln("ouvrePrinc " + nbVars * 2);
	}
	
	/**
	 * Empiler une valeur immédiate
	 * @param val	Valeur à empiler
	 */
	public void iconst(int val){
		System.out.println("iconst " + val);
		Ecriture.ecrireStringln("iconst " + val);
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable à lire
	 */
	public void iload(int offset){
		System.out.println("iload " + offset);
		Ecriture.ecrireStringln("iload " + offset);
	}
	
	/**
	 * Dépiler et affecter la valeur dans une variable en mémoire
	 * @param offset	Adresse de la variable à affecter
	 */
	public void istore(int offset){
		System.out.println("istore " + offset);
		Ecriture.ecrireStringln("istore " + offset);
	}
	
	/**
	 * Addition
	 * Dépile deux valeurs entières et empile le résultat de la somme
	 * @return
	 */
	public void iadd(){
		System.out.println("iadd");
		Ecriture.ecrireStringln("iadd");
	}
	
	/**
	 * Soustraction
	 * Dépile deux valeurs entières et empile le résultat de la soustraction
	 */
	public void isub(){
		System.out.println("isub");
		Ecriture.ecrireStringln("isub");
	}
	
	/**
	 * Multiplication
	 * Dépile deux valeurs entières et empile le résultat du produit
	 */
	public void imul(){
		System.out.println("imul");
		Ecriture.ecrireStringln("imul");
	}
	
	/**
	 * Division
	 * Dépile deux valeurs entières et empile le résultat de la division
	 */
	public void idiv(){
		System.out.println("idiv");
		Ecriture.ecrireStringln("idiv");
	}
	
	/**
	 * OU logique
	 * Dépile deux valeurs booléennes et empile le résultat de A OU B
	 */
	public void ior(){
		System.out.println("ior");
		Ecriture.ecrireStringln("ior");
	}

	/**
	 * ET logique
	 * Dépile deux valeurs booléennes et empile le résultat de A ET B
	 */
	public void iand(){
		System.out.println("iand");
		Ecriture.ecrireStringln("iand");
	}
	
	/**
	 * Test d'infériorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A < B
	 */
	public void iinf(){
		System.out.println("iinf");
		Ecriture.ecrireStringln("iinf");
	}
	
	/**
	 * Test de superiorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A > B
	 */
	public void isup(){
		System.out.println("isup");
		Ecriture.ecrireStringln("isup");
	}
	
	/**
	 * Test d'infériorité 
	 * Dépile deux valeurs entières et empile le résultat du test A <= B
	 */
	public void iinfegal(){
		System.out.println("iinfegal");
		Ecriture.ecrireStringln("iinfegal");
	}
	
	/**
	 * Test de supériorité
	 * Dépile deux valeurs entières et empile le résultat du test A >= B
	 */
	public void isupegal(){
		System.out.println("isupegal");
		Ecriture.ecrireStringln("isupegal");
	}
	
	/**
	 * Test d'égalité
	 * Dépile deux valeurs entières et empile le résultat du test A == B
	 */
	public void iegal(){
		System.out.println("iegal");
		Ecriture.ecrireStringln("iegal");
	}
	
	/**
	 * Test de différence
	 * Dépile deux valeurs entières et empile le résultat du test A != B
	 */
	public void idiff(){
		System.out.println("idiff");
		Ecriture.ecrireStringln("idiff");
	}
	
	/**
	 * Négation
	 * Dépile l'entier A et empile -A
	 */
	public void ineg(){
		System.out.println("ineg");
		Ecriture.ecrireStringln("ineg");
	}

	/**
	 * NON logique
	 * Dépile un booléen et empile sa négation
	 */
	public void inot(){
		System.out.println("inot");
		Ecriture.ecrireStringln("inot");
	}
	
	/**
	 * Saut inconditionnel
	 * @param etiq	Étiquette où l'on se situe après l'appel de goto
	 */
	public void goto_(String etiq){
		System.out.println("goto " + etiq);
		Ecriture.ecrireStringln("goto " + etiq);
	}
	
	/**
	 * Saut sur une valeur faux
	 * On dépile le sommet de pile. S'il vaut faux, on saute à l'étiquette voulue
	 * @param etiq	Étiquette 
	 */
	public void iffaux(String etiq){
		System.out.println("iffaux " + etiq);
		Ecriture.ecrireStringln("iffaux " + etiq);
	}
	
	/**
	 * Saut en cas d'égalité
	 * Les deux valeurs en sommet de pile sont dépilées.
	 * Si elles sont égales, on effetue un saut
	 * @param etiq	Étiquette
	 */
	public void ifeq(String etiq){
		System.out.println("ifeq " + etiq);
		Ecriture.ecrireStringln("ifeq " + etiq);
	}
	
	/**
	 * La valeur en sommet de pile est dépilée puis affichée
	 */
	public void ecrireEnt(){
		System.out.println("ecrireEnt");
		Ecriture.ecrireStringln("ecrireEnt");
	}
	
	/**
	 * Lecture d'un entier au clavier
	 * Affectation de cet entier à l'adresse donnée par le sommet de pile
	 * Dépiler le sommet de pile
	 */
	public void lireEnt(){
		System.out.println("lireEnt");
		Ecriture.ecrireStringln("lireEnt");
	}
	
	/**
	 * Passage à la ligne
	 */
	public void aLaLigne(){
		System.out.println("aLaLigne");
		Ecriture.ecrireStringln("aLaLigne");
	}
	
	/**
	 * Écriture à l'écran
	 * @param chaine
	 */
	public void ecrireChaine(String chaine){
		System.out.println("ecrireChaine \"" + chaine + "\"");
		Ecriture.ecrireStringln("ecrireChaine \"" + chaine + "\"");
	}
	
	/**
	 * Ouverture d'un bloc de code
	 * @param nbVars	Nombre de variables mémoire à allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln("ouvreBloc " + nbVars * 2);
	}
	
	/**
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables mémoire à allouer
	 */
	public void fermeBloc(int nbVars){
		System.out.println("fermeBloc " + nbVars * 2);
		Ecriture.ecrireStringln("fermeBloc " + nbVars * 2);
	}
	
	/**
	 * Retour d'une valeur à une adresse donnée
	 * @param offset	Adresse où stocker
	 */
	public void ireturn(int offset){
		System.out.println("ireturn " + offset);
		Ecriture.ecrireStringln("ireturn " + offset);
	}
	
	/**
	 * Réservation d'une place dans la pile pour le résultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("reserveRetour");
		Ecriture.ecrireStringln("reserveRetour");
	}
	
	/**
	 * Appel d'une fonction
	 * @param nom	Fonction à appeler
	 */
	public void call(String nom){
		System.out.println("call " + nom);
		Ecriture.ecrireStringln("call " + nom);
	}
}
