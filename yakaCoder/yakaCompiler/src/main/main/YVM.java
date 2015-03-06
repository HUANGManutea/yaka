package main;
import java.io.OutputStream;

/**
 * Classe de simulation de la Virtual Machine
 *
 */
public class YVM {
	private OutputStream f;
	
	/**
	 * D�but du programme
	 * @param nomProg	Nom du programme, utilis� pour nomme le fichier de sortie
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
	 * D�but du programme
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
	 * Pr�paration de la m�moire
	 * @param nbVars	Nombre de variables du programme
	 */
	public void ouvrePrinc(int nbVars){
		System.out.println("ouvrePrinc " + nbVars * 2);
		Ecriture.ecrireStringln("ouvrePrinc " + nbVars * 2);
	}
	
	/**
	 * Empiler une valeur imm�diate
	 * @param val	Valeur � empiler
	 */
	public void iconst(int val){
		System.out.println("iconst " + val);
		Ecriture.ecrireStringln("iconst " + val);
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable � lire
	 */
	public void iload(int offset){
		System.out.println("iload " + offset);
		Ecriture.ecrireStringln("iload " + offset);
	}
	
	/**
	 * D�piler et affecter la valeur dans une variable en m�moire
	 * @param offset	Adresse de la variable � affecter
	 */
	public void istore(int offset){
		System.out.println("istore " + offset);
		Ecriture.ecrireStringln("istore " + offset);
	}
	
	/**
	 * Addition
	 * D�pile deux valeurs enti�res et empile le r�sultat de la somme
	 * @return
	 */
	public void iadd(){
		System.out.println("iadd");
		Ecriture.ecrireStringln("iadd");
	}
	
	/**
	 * Soustraction
	 * D�pile deux valeurs enti�res et empile le r�sultat de la soustraction
	 */
	public void isub(){
		System.out.println("isub");
		Ecriture.ecrireStringln("isub");
	}
	
	/**
	 * Multiplication
	 * D�pile deux valeurs enti�res et empile le r�sultat du produit
	 */
	public void imul(){
		System.out.println("imul");
		Ecriture.ecrireStringln("imul");
	}
	
	/**
	 * Division
	 * D�pile deux valeurs enti�res et empile le r�sultat de la division
	 */
	public void idiv(){
		System.out.println("idiv");
		Ecriture.ecrireStringln("idiv");
	}
	
	/**
	 * OU logique
	 * D�pile deux valeurs bool�ennes et empile le r�sultat de A OU B
	 */
	public void ior(){
		System.out.println("ior");
		Ecriture.ecrireStringln("ior");
	}

	/**
	 * ET logique
	 * D�pile deux valeurs bool�ennes et empile le r�sultat de A ET B
	 */
	public void iand(){
		System.out.println("iand");
		Ecriture.ecrireStringln("iand");
	}
	
	/**
	 * Test d'inf�riorit� stricte
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A < B
	 */
	public void iinf(){
		System.out.println("iinf");
		Ecriture.ecrireStringln("iinf");
	}
	
	/**
	 * Test de superiorit� stricte
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A > B
	 */
	public void isup(){
		System.out.println("isup");
		Ecriture.ecrireStringln("isup");
	}
	
	/**
	 * Test d'inf�riorit� 
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A <= B
	 */
	public void iinfegal(){
		System.out.println("iinfegal");
		Ecriture.ecrireStringln("iinfegal");
	}
	
	/**
	 * Test de sup�riorit�
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A >= B
	 */
	public void isupegal(){
		System.out.println("isupegal");
		Ecriture.ecrireStringln("isupegal");
	}
	
	/**
	 * Test d'�galit�
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A == B
	 */
	public void iegal(){
		System.out.println("iegal");
		Ecriture.ecrireStringln("iegal");
	}
	
	/**
	 * Test de diff�rence
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A != B
	 */
	public void idiff(){
		System.out.println("idiff");
		Ecriture.ecrireStringln("idiff");
	}
	
	/**
	 * N�gation
	 * D�pile l'entier A et empile -A
	 */
	public void ineg(){
		System.out.println("ineg");
		Ecriture.ecrireStringln("ineg");
	}

	/**
	 * NON logique
	 * D�pile un bool�en et empile sa n�gation
	 */
	public void inot(){
		System.out.println("inot");
		Ecriture.ecrireStringln("inot");
	}
	
	/**
	 * Saut inconditionnel
	 * @param etiq	�tiquette o� l'on se situe apr�s l'appel de goto
	 */
	public void goto_(String etiq){
		System.out.println("goto " + etiq);
		Ecriture.ecrireStringln("goto " + etiq);
	}
	
	/**
	 * Saut sur une valeur faux
	 * On d�pile le sommet de pile. S'il vaut faux, on saute � l'�tiquette voulue
	 * @param etiq	�tiquette 
	 */
	public void iffaux(String etiq){
		System.out.println("iffaux " + etiq);
		Ecriture.ecrireStringln("iffaux " + etiq);
	}
	
	/**
	 * Saut en cas d'�galit�
	 * Les deux valeurs en sommet de pile sont d�pil�es.
	 * Si elles sont �gales, on effetue un saut
	 * @param etiq	�tiquette
	 */
	public void ifeq(String etiq){
		System.out.println("ifeq " + etiq);
		Ecriture.ecrireStringln("ifeq " + etiq);
	}
	
	/**
	 * La valeur en sommet de pile est d�pil�e puis affich�e
	 */
	public void ecrireEnt(){
		System.out.println("ecrireEnt");
		Ecriture.ecrireStringln("ecrireEnt");
	}
	
	/**
	 * Lecture d'un entier au clavier
	 * Affectation de cet entier � l'adresse donn�e par le sommet de pile
	 * D�piler le sommet de pile
	 */
	public void lireEnt(){
		System.out.println("lireEnt");
		Ecriture.ecrireStringln("lireEnt");
	}
	
	/**
	 * Passage � la ligne
	 */
	public void aLaLigne(){
		System.out.println("aLaLigne");
		Ecriture.ecrireStringln("aLaLigne");
	}
	
	/**
	 * �criture � l'�cran
	 * @param chaine
	 */
	public void ecrireChaine(String chaine){
		System.out.println("ecrireChaine \"" + chaine + "\"");
		Ecriture.ecrireStringln("ecrireChaine \"" + chaine + "\"");
	}
	
	/**
	 * Ouverture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln("ouvreBloc " + nbVars * 2);
	}
	
	/**
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void fermeBloc(int nbVars){
		System.out.println("fermeBloc " + nbVars * 2);
		Ecriture.ecrireStringln("fermeBloc " + nbVars * 2);
	}
	
	/**
	 * Retour � une adresse donn�e
	 * @param offset	Adresse o� repartir
	 */
	public void ireturn(int offset){
		System.out.println("ireturn " + offset);
		Ecriture.ecrireStringln("ireturn " + offset);
	}
	
	/**
	 * R�servation d'une place dans la pile pour le r�sultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("reserveRetour");
		Ecriture.ecrireStringln("reserveRetour");
	}
	
	/**
	 * Appel d'une fonction
	 * @param nom	Fonction � appeler
	 */
	public void call(String nom){
		System.out.println("call " + nom);
		Ecriture.ecrireStringln("call " + nom);
	}
}