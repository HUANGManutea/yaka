package main;
import java.io.OutputStream;

/**
 * Classe d'écriture du fichier ASM
 * 
 */
public class YVMasm extends YVM {
	private OutputStream f;
	
	/**
	 * Début du programme
	 * @param nomProg	Nom du programme, utilisé pour nomme le fichier de sortie
	 */
	public void startProg(String nomProg){
		Ecriture.ouvrir(nomProg + ".asm");
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
	 * @TODO Transformer tout en code assembleur !
	 * Début du programme
	 */
	public void entete(){
		System.out.println("entete");
		Ecriture.ecrireStringln("entete");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Fin du programme
	 */
	public void queue(){
		System.out.println("queue");
		Ecriture.ecrireStringln("queue");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
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
		System.out.println("push word ptr  " + val);
		
		Ecriture.ecrireStringln("; iconst val " + val);
		Ecriture.ecrireStringln("push word ptr " + val);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable à lire
	 */
	public void iload(int offset){
		System.out.println("push word ptr [bp+" + offset + "]");
		
		Ecriture.ecrireStringln("; iload " + offset);
		Ecriture.ecrireStringln("push word ptr [bp+" + offset + "]");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
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
		System.out.println("pop bx");
		System.out.println("pop ax");
		System.out.println("add ax,bx");
		System.out.println("push ax");
		
		Ecriture.ecrireStringln("; iadd");
		Ecriture.ecrireStringln("pop bx");
		Ecriture.ecrireStringln("pop ax");
		Ecriture.ecrireStringln("add ax,bx");
		Ecriture.ecrireStringln("push ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Soustraction
	 * Dépile deux valeurs entières et empile le résultat de la soustraction
	 */
	public void isub(){
		System.out.println("isub");
		Ecriture.ecrireStringln("isub");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Multiplication
	 * Dépile deux valeurs entières et empile le résultat du produit
	 */
	public void imul(){
		System.out.println("imul");
		Ecriture.ecrireStringln("imul");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Division
	 * Dépile deux valeurs entières et empile le résultat de la division
	 */
	public void idiv(){
		System.out.println("idiv");
		Ecriture.ecrireStringln("idiv");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * OU logique
	 * Dépile deux valeurs booléennes et empile le résultat de A OU B
	 */
	public void ior(){
		System.out.println("ior");
		Ecriture.ecrireStringln("ior");
	}

	/**
	 * @TODO Transformer tout en code assembleur !
	 * ET logique
	 * Dépile deux valeurs booléennes et empile le résultat de A ET B
	 */
	public void iand(){
		System.out.println("iand");
		Ecriture.ecrireStringln("iand");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Test d'infériorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A < B
	 */
	public void iinf(){
		System.out.println("iinf");
		Ecriture.ecrireStringln("iinf");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Test de superiorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A > B
	 */
	public void isup(){
		System.out.println("isup");
		Ecriture.ecrireStringln("isup");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Test d'infériorité 
	 * Dépile deux valeurs entières et empile le résultat du test A <= B
	 */
	public void iinfegal(){
		System.out.println("iinfegal");
		Ecriture.ecrireStringln("iinfegal");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Test de supériorité
	 * Dépile deux valeurs entières et empile le résultat du test A >= B
	 */
	public void isupegal(){
		System.out.println("isupegal");
		Ecriture.ecrireStringln("isupegal");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Test d'égalité
	 * Dépile deux valeurs entières et empile le résultat du test A == B
	 */
	public void iegal(){
		System.out.println("iegal");
		Ecriture.ecrireStringln("iegal");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Test de différence
	 * Dépile deux valeurs entières et empile le résultat du test A != B
	 */
	public void idiff(){
		System.out.println("idiff");
		Ecriture.ecrireStringln("idiff");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Négation
	 * Dépile l'entier A et empile -A
	 */
	public void ineg(){
		System.out.println("ineg");
		Ecriture.ecrireStringln("ineg");
	}

	/**
	 * @TODO Transformer tout en code assembleur !
	 * NON logique
	 * Dépile un booléen et empile sa négation
	 */
	public void inot(){
		System.out.println("inot");
		Ecriture.ecrireStringln("inot");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Saut inconditionnel
	 * @param etiq	Étiquette où l'on se situe après l'appel de goto
	 */
	public void goto_(String etiq){
		System.out.println("goto " + etiq);
		Ecriture.ecrireStringln("goto " + etiq);
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Saut sur une valeur faux
	 * On dépile le sommet de pile. S'il vaut faux, on saute à l'étiquette voulue
	 * @param etiq	Étiquette 
	 */
	public void iffaux(String etiq){
		System.out.println("iffaux " + etiq);
		Ecriture.ecrireStringln("iffaux " + etiq);
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
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
	 * @TODO Transformer tout en code assembleur !
	 * La valeur en sommet de pile est dépilée puis affichée
	 */
	public void ecrireEnt(){
		System.out.println("ecrireEnt");
		Ecriture.ecrireStringln("ecrireEnt");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Lecture d'un entier au clavier
	 * Affectation de cet entier à l'adresse donnée par le sommet de pile
	 * Dépiler le sommet de pile
	 */
	public void lireEnt(){
		System.out.println("lireEnt");
		Ecriture.ecrireStringln("lireEnt");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Passage à la ligne
	 */
	public void aLaLigne(){
		System.out.println("aLaLigne");
		Ecriture.ecrireStringln("aLaLigne");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Écriture à l'écran
	 * @param chaine
	 */
	public void ecrireChaine(String chaine){
		System.out.println("ecrireChaine \"" + chaine + "\"");
		Ecriture.ecrireStringln("ecrireChaine \"" + chaine + "\"");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Ouverture d'un bloc de code
	 * @param nbVars	Nombre de variables mémoire à allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln("ouvreBloc " + nbVars * 2);
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables mémoire à allouer
	 */
	public void fermeBloc(int nbVars){
		System.out.println("fermeBloc " + nbVars * 2);
		Ecriture.ecrireStringln("fermeBloc " + nbVars * 2);
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Retour à une adresse donnée
	 * @param offset	Adresse où repartir
	 */
	public void ireturn(int offset){
		System.out.println("ireturn " + offset);
		Ecriture.ecrireStringln("ireturn " + offset);
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Réservation d'une place dans la pile pour le résultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("reserveRetour");
		Ecriture.ecrireStringln("reserveRetour");
	}
	
	/**
	 * @TODO Transformer tout en code assembleur !
	 * Appel d'une fonction
	 * @param nom	Fonction à appeler
	 */
	public void call(String nom){
		System.out.println("call " + nom);
		Ecriture.ecrireStringln("call " + nom);
	}
}
