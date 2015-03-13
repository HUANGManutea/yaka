package main;
import java.io.OutputStream;
import java.io.File;

/**
 * Classe de simulation de la Virtual Machine
 *
 */
public class YVM {
	private OutputStream f;
	private File fic;
	private boolean status_ok;
	private String nomProg;
	
	/**
	 * Passage à un statut d'erreur
	 * Une fois passé au statut d'erreur, le fichier ne sera pas créé
	 */
	public void erreur(){
		status_ok = false;
	}
	
	/**
	 * Début du programme
	 * @param nomProg	Nom du programme, utilisé pour nomme le fichier de sortie
	 */
	public void startProg(String nomProg){
		fic = new File(nomProg + ".tmp");
		f = Ecriture.ouvrir(nomProg + ".tmp");
		this.nomProg = nomProg;
		entete();
	}
	
	/**
	 * Fin du programme et fermeture du fichier de sortie
	 */
	public void endProg(){
		queue();
		Ecriture.fermer(f);
		if (!status_ok){
			fic.delete();
		} else {
			File old = new File(nomProg);
			old.delete();
			fic.renameTo(old);
		}
	}
	
	/**
	 * Début du programme
	 */
	public void entete(){
		System.out.println("entete");
		Ecriture.ecrireStringln(f,"\tentete");
	}
	
	/**
	 * Fin du programme
	 */
	public void queue(){
		System.out.println("queue");
		Ecriture.ecrireStringln(f,"\tqueue");
	}
	
	/**
	 * Préparation de la mémoire
	 * @param nbVars	Nombre de variables du programme
	 */
	public void ouvrePrinc(int nbVars){
		System.out.println("ouvrePrinc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\touvrePrinc " + nbVars * 2);
	}
	
	/**
	 * Empiler une valeur immédiate
	 * @param val	Valeur à empiler
	 */
	public void iconst(int val){
		System.out.println("iconst " + val);
		Ecriture.ecrireStringln(f,"\ticonst " + val);
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable à lire
	 */
	public void iload(int offset){
		System.out.println("iload " + offset);
		Ecriture.ecrireStringln(f,"\tiload " + offset);
	}
	
	/**
	 * Dépiler et affecter la valeur dans une variable en mémoire
	 * @param offset	Adresse de la variable à affecter
	 */
	public void istore(int offset){
		System.out.println("istore " + offset);
		Ecriture.ecrireStringln(f,"\tistore " + offset);
	}
	
	/**
	 * Addition
	 * Dépile deux valeurs entières et empile le résultat de la somme
	 * @return
	 */
	public void iadd(){
		System.out.println("iadd");
		Ecriture.ecrireStringln(f,"\tiadd");
	}
	
	/**
	 * Soustraction
	 * Dépile deux valeurs entières et empile le résultat de la soustraction
	 */
	public void isub(){
		System.out.println("isub");
		Ecriture.ecrireStringln(f,"\tisub");
	}
	
	/**
	 * Multiplication
	 * Dépile deux valeurs entières et empile le résultat du produit
	 */
	public void imul(){
		System.out.println("imul");
		Ecriture.ecrireStringln(f,"\timul");
	}
	
	/**
	 * Division
	 * Dépile deux valeurs entières et empile le résultat de la division
	 */
	public void idiv(){
		System.out.println("idiv");
		Ecriture.ecrireStringln(f,"\tidiv");
	}
	
	/**
	 * OU logique
	 * Dépile deux valeurs booléennes et empile le résultat de A OU B
	 */
	public void ior(){
		System.out.println("ior");
		Ecriture.ecrireStringln(f,"\tior");
	}

	/**
	 * ET logique
	 * Dépile deux valeurs booléennes et empile le résultat de A ET B
	 */
	public void iand(){
		System.out.println("iand");
		Ecriture.ecrireStringln(f,"\tiand");
	}
	
	/**
	 * Test d'infériorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A < B
	 */
	public void iinf(){
		System.out.println("iinf");
		Ecriture.ecrireStringln(f,"\tiinf");
	}
	
	/**
	 * Test de superiorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A > B
	 */
	public void isup(){
		System.out.println("isup");
		Ecriture.ecrireStringln(f,"\tisup");
	}
	
	/**
	 * Test d'infériorité 
	 * Dépile deux valeurs entières et empile le résultat du test A <= B
	 */
	public void iinfegal(){
		System.out.println("iinfegal");
		Ecriture.ecrireStringln(f,"\tiinfegal");
	}
	
	/**
	 * Test de supériorité
	 * Dépile deux valeurs entières et empile le résultat du test A >= B
	 */
	public void isupegal(){
		System.out.println("isupegal");
		Ecriture.ecrireStringln(f,"\tisupegal");
	}
	
	/**
	 * Test d'égalité
	 * Dépile deux valeurs entières et empile le résultat du test A == B
	 */
	public void iegal(){
		System.out.println("iegal");
		Ecriture.ecrireStringln(f,"\tiegal");
	}
	
	/**
	 * Test de différence
	 * Dépile deux valeurs entières et empile le résultat du test A != B
	 */
	public void idiff(){
		System.out.println("idiff");
		Ecriture.ecrireStringln(f,"\tidiff");
	}
	
	/**
	 * Négation
	 * Dépile l'entier A et empile -A
	 */
	public void ineg(){
		System.out.println("ineg");
		Ecriture.ecrireStringln(f,"\tineg");
	}

	/**
	 * NON logique
	 * Dépile un booléen et empile sa négation
	 */
	public void inot(){
		System.out.println("inot");
		Ecriture.ecrireStringln(f,"\tinot");
	}
	
	/**
	 * Ajout d'une étiquette
	 * @param etiq Étiquette
	 */
	public void label(String etiq) {
		System.out.println("\n" + etiq + ":");
		Ecriture.ecrireStringln(f, "\n" + etiq + ":");
	}
	
	/**
	 * Saut inconditionnel
	 * @param etiq	Étiquette où l'on se situe après l'appel de goto
	 */
	public void goto_(String etiq){
		System.out.println("goto " + etiq);
		Ecriture.ecrireStringln(f,"\tgoto " + etiq);
	}
	
	/**
	 * Saut sur une valeur faux
	 * On dépile le sommet de pile. S'il vaut faux, on saute à l'étiquette voulue
	 * @param etiq	Étiquette 
	 */
	public void iffaux(String etiq){
		System.out.println("iffaux " + etiq);
		Ecriture.ecrireStringln(f,"\tiffaux " + etiq);
	}
	
	/**
	 * Saut en cas d'égalité
	 * Les deux valeurs en sommet de pile sont dépilées.
	 * Si elles sont égales, on effetue un saut
	 * @param etiq	Étiquette
	 */
	public void ifeq(String etiq){
		System.out.println("ifeq " + etiq);
		Ecriture.ecrireStringln(f,"\tifeq " + etiq);
	}
	
	/**
	 * La valeur en sommet de pile est dépilée puis affichée
	 */
	public void ecrireEnt(){
		System.out.println("ecrireEnt");
		Ecriture.ecrireStringln(f,"\tecrireEnt");
	}
	
	/**
	 * Le booléen en sommet de pile est dépilée puis affichée
	 */
	public void ecrireBool(){
		System.out.println("ecrireBool");
		Ecriture.ecrireStringln(f,"\tecrireBool");
	}
	
	/**
	 * Lecture d'un entier au clavier
	 * Affectation de cet entier à l'adresse donnée par le sommet de pile
	 * Dépiler le sommet de pile
	 */
	public void lireEnt(int offset){
		System.out.println("lireEnt " + offset);
		Ecriture.ecrireStringln(f,"\tlireEnt " + offset);
	}
	
	/**
	 * Passage à la ligne
	 */
	public void aLaLigne(){
		System.out.println("aLaLigne");
		Ecriture.ecrireStringln(f,"\taLaLigne");
	}
	
	/**
	 * Écriture à l'écran
	 * @param chaine
	 */
	public void ecrireChaine(String chaine){
		System.out.println("ecrireChaine " + chaine);
		Ecriture.ecrireStringln(f,"\tecrireChaine " + chaine);
	}
	
	/**
	 * Ouverture d'un bloc de code
	 * @param nbVars	Nombre de variables mémoire à allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\touvreBloc " + nbVars * 2);
	}
	
	/**
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables mémoire à allouer
	 */
	public void fermeBloc(int nbVars){
		System.out.println("fermeBloc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\tfermeBloc " + nbVars * 2);
	}
	
	/**
	 * Retour d'une valeur à une adresse donnée
	 * @param offset	Adresse où stocker
	 */
	public void ireturn(int offset){
		System.out.println("ireturn " + offset);
		Ecriture.ecrireStringln(f,"\tireturn " + offset);
	}
	
	/**
	 * Réservation d'une place dans la pile pour le résultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("reserveRetour");
		Ecriture.ecrireStringln(f,"\treserveRetour");
	}
	
	/**
	 * Appel d'une fonction
	 * @param nom	Fonction à appeler
	 */
	public void call(String nom){
		System.out.println("call " + nom);
		Ecriture.ecrireStringln(f,"\tcall " + nom);
	}
}
