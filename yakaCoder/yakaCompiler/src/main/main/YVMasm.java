package main;
import java.io.File;
import java.io.OutputStream;

/**
 * Classe d'�criture du fichier ASM
 * 
 */
public class YVMasm extends YVM {
	private OutputStream f;
	private int noMess;
	private File fic;
	private boolean status_ok = true;
	
	/**
	 * D�but du programme
	 * @param nomProg	Nom du programme, utilis� pour nomme le fichier de sortie
	 */
	public void startProg(String nomProg){
		fic  = new File(nomProg);
		f = Ecriture.ouvrir(nomProg + ".asm");
		noMess = 0;
		entete();
	}
	
	/**
	 * Fin du programme et fermeture du fichier de sortie
	 */
	public void endProg(){
		queue();
		Ecriture.fermer(f);
		if (!status_ok)
			fic.delete();
	}
	
	/**
	 * D�but du programme
	 */
	public void entete(){
		System.out.println("extrn lirent:proc,");
		System.out.println("extrn ecrent:proc,");
		System.out.println("extrn ecrbool:proc,");
		System.out.println("extrn ecrch:proc,");
		System.out.println("extrn ligsuiv:proc");
		System.out.println(".model SMALL");
		System.out.println(".586");
		System.out.println("");
		System.out.println(".CODE");
		
		Ecriture.ecrireStringln(f,"\t; entete");
		Ecriture.ecrireStringln(f,"extrn lirent:proc,");
		Ecriture.ecrireStringln(f,"extrn ecrent:proc,");
		Ecriture.ecrireStringln(f,"extrn ecrbool:proc,");
		Ecriture.ecrireStringln(f,"extrn ecrch:proc,");
		Ecriture.ecrireStringln(f,"extrn ligsuiv:proc");
		Ecriture.ecrireStringln(f,".model SMALL");
		Ecriture.ecrireStringln(f,".586");
		Ecriture.ecrireStringln(f,"");
		Ecriture.ecrireStringln(f,".CODE");
	}
	
	/**
	 * D�but du code
	 */
	public void startCode() {
		System.out.println("debut :");
		System.out.println("\tSTARTUPCODE");
		
		Ecriture.ecrireStringln(f,"debut :");
		Ecriture.ecrireStringln(f,"\tSTARTUPCODE");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Fin du programme
	 */
	public void queue(){
		System.out.println("\tnop");
		System.out.println("\texitcode");
		System.out.println("\tend debut");
		
		Ecriture.ecrireStringln(f,"\t; queue");
		Ecriture.ecrireStringln(f,"\tnop");
		Ecriture.ecrireStringln(f,"\texitcode");
		Ecriture.ecrireStringln(f,"\tend debut");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Pr�paration de la m�moire
	 * @param nbVars	Nombre de variables du programme
	 */
	public void ouvrePrinc(int nbVars){
		System.out.println("\tmov bp,sp");
		System.out.println("\tsub sp," + nbVars * 2);
		
		Ecriture.ecrireStringln(f,"\t; ouvrePrinc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\tmov bp,sp");
		Ecriture.ecrireStringln(f,"\tsub sp," + nbVars * 2);
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Empiler une valeur imm�diate
	 * @param val	Valeur � empiler
	 */
	public void iconst(int val){
		System.out.println("\tpush " + val);
		
		Ecriture.ecrireStringln(f,"\t; iconst " + val);
		Ecriture.ecrireStringln(f,"\tpush " + val);
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable � lire
	 */
	public void iload(int offset){
		if (offset >= 0) {
			System.out.println("\tpush word ptr [bp+" + offset + "]");
		} else {
			System.out.println("\tpush word ptr [bp" + offset + "]");
		}
		
		Ecriture.ecrireStringln(f,"\t; iload " + offset);
		if (offset >= 0) {
			Ecriture.ecrireStringln(f,"\tpush word ptr [bp+" + offset + "]");
		} else {
			Ecriture.ecrireStringln(f,"\tpush word ptr [bp" + offset + "]");
		}
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * D�piler et affecter la valeur dans une variable en m�moire
	 * @param offset	Adresse de la variable � affecter
	 */
	public void istore(int offset){
		System.out.println("\tpop ax");
		if (offset >= 0) {
			System.out.println("\tmov word ptr[bp+" + offset + "],ax");
		} else {
			System.out.println("\tmov word ptr[bp" + offset + "],ax");
		}
		
		Ecriture.ecrireStringln(f,"\t; istore " + offset);
		Ecriture.ecrireStringln(f,"\tpop ax");
		if (offset >= 0) {
			Ecriture.ecrireStringln(f,"\tmov word ptr[bp+" + offset + "],ax");
		} else {
			Ecriture.ecrireStringln(f,"\tmov word ptr[bp" + offset + "],ax");
		}
		Ecriture.ecrireStringln(f,"");
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
		
		Ecriture.ecrireStringln(f,"\t; iadd");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tadd ax,bx");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
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
			
		Ecriture.ecrireStringln(f,"\t; isub");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tsub ax,bx");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Multiplication
	 * D�pile deux valeurs enti�res et empile le r�sultat du produit
	 */
	public void imul(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\timul bx");
		System.out.println("\tpush ax");
			
		Ecriture.ecrireStringln(f,"\t; imul");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\timul bx");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Division
	 * D�pile deux valeurs enti�res et empile le r�sultat de la division
	 */
	public void idiv(){
		System.out.println("\tpop bx");
		System.out.println("\tpop ax");
		System.out.println("\tcwd");
		System.out.println("\tidiv bx");
		System.out.println("\tpush ax");
			
		Ecriture.ecrireStringln(f,"\t; idiv");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcwd");
		Ecriture.ecrireStringln(f,"\tidiv bx");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
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
		
		Ecriture.ecrireStringln(f,"\t; ior");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tor ax,bx");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
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
		
		Ecriture.ecrireStringln(f,"\t; iand");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tand ax,bx");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
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
		System.out.println("\tpush " + Constantes.VRAI);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush " + Constantes.FAUX);
		
		Ecriture.ecrireStringln(f,"\t; iinf");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcmp ax,bx");
		Ecriture.ecrireStringln(f,"\tjge $+6");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.VRAI);
		Ecriture.ecrireStringln(f,"\tjmp $+4");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.FAUX);
		Ecriture.ecrireStringln(f,"");
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
		System.out.println("\tpush " + Constantes.VRAI);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush " + Constantes.FAUX);
		
		Ecriture.ecrireStringln(f,"\t; isup");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcmp ax,bx");
		Ecriture.ecrireStringln(f,"\tjle $+6");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.VRAI);
		Ecriture.ecrireStringln(f,"\tjmp $+4");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.FAUX);
		Ecriture.ecrireStringln(f,"");
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
		System.out.println("\tpush " + Constantes.VRAI);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush " + Constantes.FAUX);
		
		Ecriture.ecrireStringln(f,"\t; iinfegal");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcmp ax,bx");
		Ecriture.ecrireStringln(f,"\tjg $+6");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.VRAI);
		Ecriture.ecrireStringln(f,"\tjmp $+4");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.FAUX);
		Ecriture.ecrireStringln(f,"");
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
		System.out.println("\tpush " + Constantes.VRAI);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush " + Constantes.FAUX);
		
		Ecriture.ecrireStringln(f,"\t; isupegal");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcmp ax,bx");
		Ecriture.ecrireStringln(f,"\tjl $+6");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.VRAI);
		Ecriture.ecrireStringln(f,"\tjmp $+4");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.FAUX);
		Ecriture.ecrireStringln(f,"");
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
		System.out.println("\tpush " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush " + Constantes.VRAI);
		
		Ecriture.ecrireStringln(f,"\t; iegal");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcmp ax,bx");
		Ecriture.ecrireStringln(f,"\tje $+6");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.FAUX);
		Ecriture.ecrireStringln(f,"\tjmp $+4");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.VRAI);
		Ecriture.ecrireStringln(f,"");
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
		System.out.println("\tpush " + Constantes.FAUX);
		System.out.println("\tjmp $+4");
		System.out.println("\tpush " + Constantes.VRAI);
		
		Ecriture.ecrireStringln(f,"\t; idiff");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcmp ax,bx");
		Ecriture.ecrireStringln(f,"\tjne $+6");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.FAUX);
		Ecriture.ecrireStringln(f,"\tjmp $+4");
		Ecriture.ecrireStringln(f,"\tpush " + Constantes.VRAI);
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * N�gation
	 * D�pile l'entier A et empile -A
	 */
	public void ineg(){
		System.out.println("\tpop ax");
		System.out.println("\tneg ax");
		System.out.println("\tpush ax");
		
		Ecriture.ecrireStringln(f,"\t; ineg");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tneg ax");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
	}

	/**
	 * NON logique
	 * D�pile un bool�en et empile sa n�gation
	 */
	public void inot(){
		System.out.println("\tpop ax");
		System.out.println("\tnot ax");
		System.out.println("\tpush ax");
		
		Ecriture.ecrireStringln(f,"\t; inot");
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tnot ax");
		Ecriture.ecrireStringln(f,"\tpush ax");
		Ecriture.ecrireStringln(f,"");
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
		System.out.println("\tjmp " + etiq);
		
		Ecriture.ecrireStringln(f,"\t; goto " + etiq);
		Ecriture.ecrireStringln(f,"\tjmp " + etiq);
		Ecriture.ecrireStringln(f,"");
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
		
		Ecriture.ecrireStringln(f,"\t; iffaux " + etiq);
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tcmp ax," + Constantes.VRAI);
		Ecriture.ecrireStringln(f,"\tjne " + etiq);
		Ecriture.ecrireStringln(f,"");
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
		
		Ecriture.ecrireStringln(f,"\t; ifeq " + etiq);
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tpop bx");
		Ecriture.ecrireStringln(f,"\tcmp ax,bx");
		Ecriture.ecrireStringln(f,"\tjne " + etiq);
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * La valeur en sommet de pile est d�pil�e puis affich�e
	 */
	public void ecrireEnt(){
		System.out.println("\tcall ecrent");
		System.out.println();
		
		Ecriture.ecrireStringln(f,"\t; ecrireEnt");
		Ecriture.ecrireStringln(f,"\tcall ecrent");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Le bool�en en sommet de pile est d�pil�e puis affich�e
	 */
	public void ecrireBool(){
		System.out.println("\tcall ecrbool");
		System.out.println();
		
		Ecriture.ecrireStringln(f,"\t; ecrireBool");
		Ecriture.ecrireStringln(f,"\tcall ecrbool");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Lecture d'un entier au clavier
	 * Affectation de cet entier � l'adresse donn�e par le sommet de pile
	 * D�piler le sommet de pile
	 */
	public void lireEnt(int offset) {
		if (offset >= 0) {
			System.out.println("\tlea dx,[bp+" + offset + "]");
		} else {
			System.out.println("\tlea dx,[bp" + offset + "]");		
		}
		System.out.println("\tpush dx");
		System.out.println("\tcall lirent");
		
		Ecriture.ecrireStringln(f,"\t; lireEnt");
		if (offset >= 0) {
			Ecriture.ecrireStringln(f,"\tlea dx,[bp+" + offset + "]");
		} else {
			Ecriture.ecrireStringln(f,"\tlea dx,[bp" + offset + "]");		
		}
		Ecriture.ecrireStringln(f,"\tpush dx");
		Ecriture.ecrireStringln(f,"\tcall lirent");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Passage � la ligne
	 */
	public void aLaLigne(){
		System.out.println("\tcall ligsuiv");
		
		Ecriture.ecrireStringln(f,"\t; aLaLigne");
		Ecriture.ecrireStringln(f,"\tcall ligsuiv");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * �criture � l'�cran
	 * @param chaine
	 */
	public void ecrireChaine(String chaine){
		String asmString = chaine.substring(0, chaine.length()-1) + "$\"";
		System.out.println(".DATA");
		System.out.println("\tmess" + noMess + " DB " + asmString);
		System.out.println(".CODE");
		System.out.println("\tlea dx,mess" + noMess);
		System.out.println("\tpush dx");
		System.out.println("\tcall ecrch");
		
		Ecriture.ecrireStringln(f,"\t; ecrireChaine " + chaine);
		Ecriture.ecrireStringln(f,".DATA");
		Ecriture.ecrireStringln(f,"\tmess" + noMess + " DB " + asmString);
		Ecriture.ecrireStringln(f,".CODE");
		Ecriture.ecrireStringln(f,"\tlea dx,mess" + noMess);
		Ecriture.ecrireStringln(f,"\tpush dx");
		Ecriture.ecrireStringln(f,"\tcall ecrch");
		Ecriture.ecrireStringln(f,"");
		
		noMess++;
	}
	
	/**
	 * Ouverture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("\tenter " + nbVars * 2 + ",0");
		
		Ecriture.ecrireStringln(f,"\t; ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\tenter " + nbVars * 2 + ",0");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables m�moire � allouer
	 */
	public void fermeBloc(int nbVars){
		System.out.println("\tleave");
		System.out.println("\tret " + nbVars * 2);
		
		Ecriture.ecrireStringln(f,"\t; fermeBloc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\tleave");
		Ecriture.ecrireStringln(f,"\tret " + nbVars * 2);
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Retour d'une valeur � adresse donn�e
	 * @param offset	Adresse o� stocker la valeur
	 */
	public void ireturn(int offset){
		System.out.println("\tpop ax");
		System.out.println("\tmov [bp+" + offset + "],ax");
		
		Ecriture.ecrireStringln(f,"\t; ireturn " + offset);
		Ecriture.ecrireStringln(f,"\tpop ax");
		Ecriture.ecrireStringln(f,"\tmov [bp+" + offset + "],ax");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * R�servation d'une place dans la pile pour le r�sultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("\tsub sp,2");
		
		Ecriture.ecrireStringln(f,"\t; reserveRetour");
		Ecriture.ecrireStringln(f,"\tsub sp,2");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Appel d'une fonction
	 * @param nom	Fonction � appeler
	 */
	public void call(String nom){
		System.out.println("\tcall " + nom);
		
		Ecriture.ecrireStringln(f,"\t; call " + nom);
		Ecriture.ecrireStringln(f,"\tcall " + nom);
		Ecriture.ecrireStringln(f,"");
	}
}
