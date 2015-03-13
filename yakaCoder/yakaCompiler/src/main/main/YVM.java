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
	 * Passage � un statut d'erreur
	 * Une fois pass� au statut d'erreur, le fichier ne sera pas cr��
	 */
	public void erreur(){
		status_ok = false;
	}
	
	/**
	 * D�but du programme
	 * @param nomProg	Nom du programme, utilis� pour nomme le fichier de sortie
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
	 * D�but du programme
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
	 * Pr�paration de la m�moire
	 * @param nbVars	Nombre de variables du programme
	 */
	public void ouvrePrinc(int nbVars){
		System.out.println("ouvrePrinc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\touvrePrinc " + nbVars * 2);
	}
	
	/**
	 * Empiler une valeur imm�diate
	 * @param val	Valeur � empiler
	 */
	public void iconst(int val){
		System.out.println("iconst " + val);
		Ecriture.ecrireStringln(f,"\ticonst " + val);
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable � lire
	 */
	public void iload(int offset){
		System.out.println("iload " + offset);
		Ecriture.ecrireStringln(f,"\tiload " + offset);
	}
	
	/**
	 * D�piler et affecter la valeur dans une variable en m�moire
	 * @param offset	Adresse de la variable � affecter
	 */
	public void istore(int offset){
		System.out.println("istore " + offset);
		Ecriture.ecrireStringln(f,"\tistore " + offset);
	}
	
	/**
	 * Addition
	 * D�pile deux valeurs enti�res et empile le r�sultat de la somme
	 * @return
	 */
	public void iadd(){
		System.out.println("iadd");
		Ecriture.ecrireStringln(f,"\tiadd");
	}
	
	/**
	 * Soustraction
	 * D�pile deux valeurs enti�res et empile le r�sultat de la soustraction
	 */
	public void isub(){
		System.out.println("isub");
		Ecriture.ecrireStringln(f,"\tisub");
	}
	
	/**
	 * Multiplication
	 * D�pile deux valeurs enti�res et empile le r�sultat du produit
	 */
	public void imul(){
		System.out.println("imul");
		Ecriture.ecrireStringln(f,"\timul");
	}
	
	/**
	 * Division
	 * D�pile deux valeurs enti�res et empile le r�sultat de la division
	 */
	public void idiv(){
		System.out.println("idiv");
		Ecriture.ecrireStringln(f,"\tidiv");
	}
	
	/**
	 * OU logique
	 * D�pile deux valeurs bool�ennes et empile le r�sultat de A OU B
	 */
	public void ior(){
		System.out.println("ior");
		Ecriture.ecrireStringln(f,"\tior");
	}

	/**
	 * ET logique
	 * D�pile deux valeurs bool�ennes et empile le r�sultat de A ET B
	 */
	public void iand(){
		System.out.println("iand");
		Ecriture.ecrireStringln(f,"\tiand");
	}
	
	/**
	 * Test d'inf�riorit� stricte
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A < B
	 */
	public void iinf(){
		System.out.println("iinf");
		Ecriture.ecrireStringln(f,"\tiinf");
	}
	
	/**
	 * Test de superiorit� stricte
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A > B
	 */
	public void isup(){
		System.out.println("isup");
		Ecriture.ecrireStringln(f,"\tisup");
	}
	
	/**
	 * Test d'inf�riorit� 
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A <= B
	 */
	public void iinfegal(){
		System.out.println("iinfegal");
		Ecriture.ecrireStringln(f,"\tiinfegal");
	}
	
	/**
	 * Test de sup�riorit�
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A >= B
	 */
	public void isupegal(){
		System.out.println("isupegal");
		Ecriture.ecrireStringln(f,"\tisupegal");
	}
	
	/**
	 * Test d'�galit�
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A == B
	 */
	public void iegal(){
		System.out.println("iegal");
		Ecriture.ecrireStringln(f,"\tiegal");
	}
	
	/**
	 * Test de diff�rence
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A != B
	 */
	public void idiff(){
		System.out.println("idiff");
		Ecriture.ecrireStringln(f,"\tidiff");
	}
	
	/**
	 * N�gation
	 * D�pile l'entier A et empile -A
	 */
	public void ineg(){
		System.out.println("ineg");
		Ecriture.ecrireStringln(f,"\tineg");
	}

	/**
	 * NON logique
	 * D�pile un bool�en et empile sa n�gation
	 */
	public void inot(){
		System.out.println("inot");
		Ecriture.ecrireStringln(f,"\tinot");
	}
	
	/**
	 * Ajout d'une �tiquette
	 * @param etiq �tiquette
	 */
	public void label(String etiq) {
		System.out.println("\n" + etiq + ":");
		Ecriture.ecrireStringln(f, "\n" + etiq + ":");
	}
	
	/**
	 * Saut inconditionnel
	 * @param etiq	�tiquette o� l'on se situe apr�s l'appel de goto
	 */
	public void goto_(String etiq){
		System.out.println("goto " + etiq);
		Ecriture.ecrireStringln(f,"\tgoto " + etiq);
	}
	
	/**
	 * Saut sur une valeur faux
	 * On d�pile le sommet de pile. S'il vaut faux, on saute � l'�tiquette voulue
	 * @param etiq	�tiquette 
	 */
	public void iffaux(String etiq){
		System.out.println("iffaux " + etiq);
		Ecriture.ecrireStringln(f,"\tiffaux " + etiq);
	}
	
	/**
	 * Saut en cas d'�galit�
	 * Les deux valeurs en sommet de pile sont d�pil�es.
	 * Si elles sont �gales, on effetue un saut
	 * @param etiq	�tiquette
	 */
	public void ifeq(String etiq){
		System.out.println("ifeq " + etiq);
		Ecriture.ecrireStringln(f,"\tifeq " + etiq);
	}
	
	/**
	 * La valeur en sommet de pile est d�pil�e puis affich�e
	 */
	public void ecrireEnt(){
		System.out.println("ecrireEnt");
		Ecriture.ecrireStringln(f,"\tecrireEnt");
	}
	
	/**
	 * Le bool�en en sommet de pile est d�pil�e puis affich�e
	 */
	public void ecrireBool(){
		System.out.println("ecrireBool");
		Ecriture.ecrireStringln(f,"\tecrireBool");
	}
	
	/**
	 * Lecture d'un entier au clavier
	 * Affectation de cet entier � l'adresse donn�e par le sommet de pile
	 * D�piler le sommet de pile
	 */
	public void lireEnt(int offset){
		System.out.println("lireEnt " + offset);
		Ecriture.ecrireStringln(f,"\tlireEnt " + offset);
	}
	
	/**
	 * Passage � la ligne
	 */
	public void aLaLigne(){
		System.out.println("aLaLigne");
		Ecriture.ecrireStringln(f,"\taLaLigne");
	}
	
	/**
	 * �criture � l'�cran
	 * @param chaine
	 */
	public void ecrireChaine(String chaine){
		System.out.println("ecrireChaine " + chaine);
		Ecriture.ecrireStringln(f,"\tecrireChaine " + chaine);
	}
	
	/**
	 * Ouverture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\touvreBloc " + nbVars * 2);
	}
	
	/**
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void fermeBloc(int nbVars){
		System.out.println("fermeBloc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\tfermeBloc " + nbVars * 2);
	}
	
	/**
	 * Retour d'une valeur � une adresse donn�e
	 * @param offset	Adresse o� stocker
	 */
	public void ireturn(int offset){
		System.out.println("ireturn " + offset);
		Ecriture.ecrireStringln(f,"\tireturn " + offset);
	}
	
	/**
	 * R�servation d'une place dans la pile pour le r�sultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("reserveRetour");
		Ecriture.ecrireStringln(f,"\treserveRetour");
	}
	
	/**
	 * Appel d'une fonction
	 * @param nom	Fonction � appeler
	 */
	public void call(String nom){
		System.out.println("call " + nom);
		Ecriture.ecrireStringln(f,"\tcall " + nom);
	}
}
