package main;
import java.io.OutputStream;

/**
 * Classe d'�criture du fichier ASM
 * 
 */
public class YVMasm extends YVM {
	private OutputStream f;
	private int noMess;
	
	/**
	 * D�but du programme
	 * @param nomProg	Nom du programme, utilis� pour nomme le fichier de sortie
	 */
	public void startProg(String nomProg){
		Ecriture.ouvrir(nomProg + ".asm");
		noMess = 0;
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
		System.out.println("extern lirent:proc,");
		System.out.println("extern ecrent:proc,");
		System.out.println("extern ecrbool:proc,");
		System.out.println("extern ecrch:proc,");
		System.out.println("extern ligsuiv:proc");
		System.out.println(".model SMALL");
		System.out.println(".586");
		System.out.println("");
		System.out.println(".CODE");
		System.out.println("debut :");
		System.out.println("\tSTARTUPCODE");
		
		Ecriture.ecrireStringln("\t; entete");
		Ecriture.ecrireStringln("extern lirent:proc,");
		Ecriture.ecrireStringln("extern ecrent:proc,");
		Ecriture.ecrireStringln("extern ecrbool:proc,");
		Ecriture.ecrireStringln("extern ecrch:proc,");
		Ecriture.ecrireStringln("extern ligsuiv:proc");
		Ecriture.ecrireStringln(".model SMALL");
		Ecriture.ecrireStringln(".586");
		Ecriture.ecrireStringln("");
		Ecriture.ecrireStringln(".CODE");
		Ecriture.ecrireStringln("debut :");
		Ecriture.ecrireStringln("\tSTARTUPCODE");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Fin du programme
	 */
	public void queue(){
		System.out.println("\tnop");
		System.out.println("\texitcode");
		System.out.println("\tend debut");
		
		Ecriture.ecrireStringln("\t; queue");
		Ecriture.ecrireStringln("\tnop");
		Ecriture.ecrireStringln("\texitcode");
		Ecriture.ecrireStringln("\tend debut");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Pr�paration de la m�moire
	 * @param nbVars	Nombre de variables du programme
	 */
	public void ouvrePrinc(int nbVars){
		System.out.println("\tmov bp,sp");
		System.out.println("\tsup sp," + nbVars * 2);
		
		Ecriture.ecrireStringln("\t; ouvrePrinc " + nbVars * 2);
		Ecriture.ecrireStringln("\tmov bp,sp");
		Ecriture.ecrireStringln("\tsup sp," + nbVars * 2);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Empiler une valeur imm�diate
	 * @param val	Valeur � empiler
	 */
	public void iconst(int val){
		System.out.println("\tpush word ptr  " + val);
		
		Ecriture.ecrireStringln("\t; iconst val " + val);
		Ecriture.ecrireStringln("\tpush word ptr " + val);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable � lire
	 */
	public void iload(int offset){
		System.out.println("\tpush word ptr [bp+" + offset + "]");
		
		Ecriture.ecrireStringln("\t; iload " + offset);
		Ecriture.ecrireStringln("\tpush word ptr [bp+" + offset + "]");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * D�piler et affecter la valeur dans une variable en m�moire
	 * @param offset	Adresse de la variable � affecter
	 */
	public void istore(int offset){
		System.out.println("\tpop ax");
		System.out.println("\tmov word ptr[bp+" + offset + "],ax");
		
		Ecriture.ecrireStringln("\t; istore " + offset);
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tmov word ptr[bp+" + offset + "],ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Addition
	 * D�pile deux valeurs enti�res et empile le r�sultat de la somme
	 */
	public void iadd(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tadd ax,bx");
		System.out.println("\tpush ax");
		
		Ecriture.ecrireStringln("\t; iadd");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tadd ax,bx");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Soustraction
	 * D�pile deux valeurs enti�res et empile le r�sultat de la soustraction
	 */
	public void isub(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tsub ax,bx");
		System.out.println("\tpush ax");
			
		Ecriture.ecrireStringln("\t; isub");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tsub ax,bx");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Multiplication
	 * D�pile deux valeurs enti�res et empile le r�sultat du produit
	 */
	public void imul(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\timul ax,bx");
		System.out.println("\tpush ax");
			
		Ecriture.ecrireStringln("\t; imul");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\timul ax,bx");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Division
	 * D�pile deux valeurs enti�res et empile le r�sultat de la division
	 */
	public void idiv(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcwd");
		System.out.println("\tidiv ax,bx");
		System.out.println("\tpush ax");
			
		Ecriture.ecrireStringln("\t; idiv");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcwd");
		Ecriture.ecrireStringln("\tidiv ax,bx");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * OU logique
	 * D�pile deux valeurs bool�ennes et empile le r�sultat de A OU B
	 */
	public void ior(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tor ax,bx");
		System.out.println("\tpush ax");
		
		Ecriture.ecrireStringln("\t; ior");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tor ax,bx");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}

	/**
	 * ET logique
	 * D�pile deux valeurs bool�ennes et empile le r�sultat de A ET B
	 */
	public void iand(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tand ax,bx");
		System.out.println("\tpush ax");
		
		Ecriture.ecrireStringln("\t; iand");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tand ax,bx");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Test d'inf�riorit� stricte
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A < B
	 */
	public void iinf(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcmp ax,bx");
		System.out.println("\tjge $+6");
		System.out.println("\tpush word ptr " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush word ptr " + Constantes.VRAI);
		
		Ecriture.ecrireStringln("\t; iinf");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcmp ax,bx");
		Ecriture.ecrireStringln("\tjge $+6");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.FAUX);
		Ecriture.ecrireStringln("\tjmp $+4");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.VRAI);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Test de superiorit� stricte
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A > B
	 */
	public void isup(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcmp ax,bx");
		System.out.println("\tjle $+6");
		System.out.println("\tpush word ptr " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush word ptr " + Constantes.VRAI);
		
		Ecriture.ecrireStringln("\t; isup");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcmp ax,bx");
		Ecriture.ecrireStringln("\tjle $+6");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.FAUX);
		Ecriture.ecrireStringln("\tjmp $+4");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.VRAI);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Test d'inf�riorit� 
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A <= B
	 */
	public void iinfegal(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcmp ax,bx");
		System.out.println("\tjg $+6");
		System.out.println("\tpush word ptr " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush word ptr " + Constantes.VRAI);
		
		Ecriture.ecrireStringln("\t; iinfegal");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcmp ax,bx");
		Ecriture.ecrireStringln("\tjg $+6");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.FAUX);
		Ecriture.ecrireStringln("\tjmp $+4");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.VRAI);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Test de sup�riorit�
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A >= B
	 */
	public void isupegal(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcmp ax,bx");
		System.out.println("\tjl $+6");
		System.out.println("\tpush word ptr " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush word ptr " + Constantes.VRAI);
		
		Ecriture.ecrireStringln("\t; isupegal");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcmp ax,bx");
		Ecriture.ecrireStringln("\tjl $+6");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.FAUX);
		Ecriture.ecrireStringln("\tjmp $+4");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.VRAI);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Test d'�galit�
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A == B
	 */
	public void iegal(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcmp ax,bx");
		System.out.println("\tje $+6");
		System.out.println("\tpush word ptr " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush word ptr " + Constantes.VRAI);
		
		Ecriture.ecrireStringln("\t; iegal");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcmp ax,bx");
		Ecriture.ecrireStringln("\tje $+6");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.FAUX);
		Ecriture.ecrireStringln("\tjmp $+4");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.VRAI);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Test de diff�rence
	 * D�pile deux valeurs enti�res et empile le r�sultat du test A != B
	 */
	public void idiff(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcmp ax,bx");
		System.out.println("\tjne $+6");
		System.out.println("\tpush word ptr " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush word ptr " + Constantes.VRAI);
		
		Ecriture.ecrireStringln("\t; idiff");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcmp ax,bx");
		Ecriture.ecrireStringln("\tjne $+6");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.FAUX);
		Ecriture.ecrireStringln("\tjmp $+4");
		Ecriture.ecrireStringln("\tpush word ptr " + Constantes.VRAI);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * N�gation
	 * D�pile l'entier A et empile -A
	 */
	public void ineg(){
		System.out.println("\tpop ax");
		System.out.println("\tneg ax");
		System.out.println("\tpush ax");
		
		Ecriture.ecrireStringln("\t; ineg");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tneg ax");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}

	/**
	 * NON logique
	 * D�pile un bool�en et empile sa n�gation
	 */
	public void inot(){
		System.out.println("\tpop ax");
		System.out.println("\tnot ax");
		System.out.println("\tpush ax");
		
		Ecriture.ecrireStringln("\t; inot");
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tnot ax");
		Ecriture.ecrireStringln("\tpush ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Saut inconditionnel
	 * @param etiq	�tiquette o� l'on se situe apr�s l'appel de goto
	 */
	public void goto_(String etiq){
		System.out.println("\tjmp " + etiq);
		
		Ecriture.ecrireStringln("\t; goto " + etiq);
		Ecriture.ecrireStringln("\tjmp " + etiq);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Saut sur une valeur faux
	 * On d�pile le sommet de pile. S'il vaut faux, on saute � l'�tiquette voulue
	 * @param etiq	�tiquette 
	 */
	public void iffaux(String etiq){
		System.out.println("\tpop ax");
		System.out.println("\tcmp ax," + Constantes.VRAI);
		System.out.println("\tjne " + etiq);
		
		Ecriture.ecrireStringln("\t; iffaux " + etiq);
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tcmp ax," + Constantes.VRAI);
		Ecriture.ecrireStringln("\tjne " + etiq);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Saut en cas d'�galit�
	 * Les deux valeurs en sommet de pile sont d�pil�es.
	 * Si elles sont �gales, on effetue un saut
	 * @param etiq	�tiquette
	 */
	public void ifeq(String etiq){
		System.out.println("\tpop ax");
		System.out.println("\tpop bx");
		System.out.println("\tcmp ax,bx");
		System.out.println("\tjne " + etiq);
		
		Ecriture.ecrireStringln("\t; ifeq " + etiq);
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tpop bx");
		Ecriture.ecrireStringln("\tcmp ax,bx");
		Ecriture.ecrireStringln("\tjne " + etiq);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * La valeur en sommet de pile est d�pil�e puis affich�e
	 */
	public void ecrireEnt(){
		System.out.println("\tcall ecrent");
		System.out.println();
		
		Ecriture.ecrireStringln("\t; ecrireEnt");
		Ecriture.ecrireStringln("\tcall ecrent");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Lecture d'un entier au clavier
	 * Affectation de cet entier � l'adresse donn�e par le sommet de pile
	 * D�piler le sommet de pile
	 */
	public void lireEnt(){
		System.out.println("\tlea dx,[bp-6]");
		System.out.println("\tpush dx");
		System.out.println("\tcall lirent");
		
		Ecriture.ecrireStringln("\t; lireEnt");
		Ecriture.ecrireStringln("\tlea dx,[bp-6]");
		Ecriture.ecrireStringln("\tpush dx");
		Ecriture.ecrireStringln("\tcall lirent");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Passage � la ligne
	 */
	public void aLaLigne(){
		System.out.println("\tcall ligsuiv");
		
		Ecriture.ecrireStringln("\t; aLaLigne");
		Ecriture.ecrireStringln("\tcall ligsuiv");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * �criture � l'�cran
	 * @param chaine
	 */
	public void ecrireChaine(String chaine){
		System.out.println(".DATA");
		System.out.println("\tmess" + noMess + " DB \"" + chaine + "\"");
		System.out.println(".CODE");
		System.out.println("\tlea dx,mess" + noMess);
		System.out.println("\tpush dx");
		System.out.println("\tcall ecrch");
		
		Ecriture.ecrireStringln("\t; ecrireChaine \"" + chaine + "\"");
		Ecriture.ecrireStringln(".DATA");
		Ecriture.ecrireStringln("\tmess" + noMess + " DB \"" + chaine + "\"");
		Ecriture.ecrireStringln(".CODE");
		Ecriture.ecrireStringln("\tlea dx,mess" + noMess);
		Ecriture.ecrireStringln("\tpush dx");
		Ecriture.ecrireStringln("\tcall ecrch");
		Ecriture.ecrireStringln("");
		
		noMess++;
	}
	
	/**
	 * Ouverture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("\tenter " + nbVars * 2 + ",0");
		
		Ecriture.ecrireStringln("\t; ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln("\tenter " + nbVars * 2 + ",0");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void fermeBloc(int nbVars){
		System.out.println("leave");
		System.out.println("ret " + nbVars * 2);
		
		Ecriture.ecrireStringln("; fermeBloc " + nbVars * 2);
		Ecriture.ecrireStringln("leave");
		Ecriture.ecrireStringln("ret " + nbVars * 2);
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Retour d'une valeur � adresse donn�e
	 * @param offset	Adresse o� stocker la valeur
	 */
	public void ireturn(int offset){
		System.out.println("\tpop ax");
		System.out.println("\tmov [bp+" + offset + "],ax");
		
		Ecriture.ecrireStringln("\t; ireturn " + offset);
		Ecriture.ecrireStringln("\tpop ax");
		Ecriture.ecrireStringln("\tmov [bp+" + offset + "],ax");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * R�servation d'une place dans la pile pour le r�sultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("\tsub sp,2");
		
		Ecriture.ecrireStringln("\t; reserveRetour");
		Ecriture.ecrireStringln("\tsub sp,2");
		Ecriture.ecrireStringln("");
	}
	
	/**
	 * Appel d'une fonction
	 * @param nom	Fonction � appeler
	 */
	public void call(String nom){
		System.out.println("\tcall " + nom);
		
		Ecriture.ecrireStringln("\t; call " + nom);
		Ecriture.ecrireStringln("\tcall " + nom);
		Ecriture.ecrireStringln("");
	}
}
