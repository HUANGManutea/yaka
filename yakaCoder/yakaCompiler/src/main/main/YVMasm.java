package main;
import java.io.File;
import java.io.OutputStream;

/**
 * Classe d'écriture du fichier ASM
 * 
 */
public class YVMasm extends YVM {
	private OutputStream f;
	private int noMess;
	private File fic;
	private boolean status_ok = true;
	
	/**
	 * Début du programme
	 * @param nomProg	Nom du programme, utilisé pour nomme le fichier de sortie
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
	 * Début du programme
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
	 * Début du code
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
	 * Préparation de la mémoire
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
	 * Empiler une valeur immédiate
	 * @param val	Valeur à empiler
	 */
	public void iconst(int val){
		System.out.println("\tpush " + val);
		
		Ecriture.ecrireStringln(f,"\t; iconst " + val);
		Ecriture.ecrireStringln(f,"\tpush " + val);
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Empiler le contenu d'une variable
	 * @param offset	Adresse de la variable à lire
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
	 * Dépiler et affecter la valeur dans une variable en mémoire
	 * @param offset	Adresse de la variable à affecter
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
	 * Dépile deux valeurs entières et empile le résultat de la somme
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
	 * Dépile deux valeurs entières et empile le résultat de la soustraction
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
	 * Dépile deux valeurs entières et empile le résultat du produit
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
	 * Dépile deux valeurs entières et empile le résultat de la division
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
	 * Dépile deux valeurs booléennes et empile le résultat de A OU B
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
	 * Dépile deux valeurs booléennes et empile le résultat de A ET B
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
	 * Test d'infériorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A < B
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
	 * Test de superiorité stricte
	 * Dépile deux valeurs entières et empile le résultat du test A > B
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
	 * Test d'infériorité 
	 * Dépile deux valeurs entières et empile le résultat du test A <= B
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
	 * Test de supériorité
	 * Dépile deux valeurs entières et empile le résultat du test A >= B
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
	 * Test d'égalité
	 * Dépile deux valeurs entières et empile le résultat du test A == B
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
	 * Test de différence
	 * Dépile deux valeurs entières et empile le résultat du test A != B
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
	 * Négation
	 * Dépile l'entier A et empile -A
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
	 * Dépile un booléen et empile sa négation
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
		System.out.println("\tjmp " + etiq);
		
		Ecriture.ecrireStringln(f,"\t; goto " + etiq);
		Ecriture.ecrireStringln(f,"\tjmp " + etiq);
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Saut sur une valeur faux
	 * On dépile le sommet de pile. S'il vaut faux, on saute à l'étiquette voulue
	 * @param etiq	Étiquette 
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
	 * Saut en cas d'égalité
	 * Les deux valeurs en sommet de pile sont dépilées.
	 * Si elles sont égales, on effetue un saut
	 * @param etiq	Étiquette
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
	 * La valeur en sommet de pile est dépilée puis affichée
	 */
	public void ecrireEnt(){
		System.out.println("\tcall ecrent");
		System.out.println();
		
		Ecriture.ecrireStringln(f,"\t; ecrireEnt");
		Ecriture.ecrireStringln(f,"\tcall ecrent");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Le booléen en sommet de pile est dépilée puis affichée
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
	 * Affectation de cet entier à l'adresse donnée par le sommet de pile
	 * Dépiler le sommet de pile
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
	 * Passage à la ligne
	 */
	public void aLaLigne(){
		System.out.println("\tcall ligsuiv");
		
		Ecriture.ecrireStringln(f,"\t; aLaLigne");
		Ecriture.ecrireStringln(f,"\tcall ligsuiv");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Écriture à l'écran
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
	 * @param nbVars	Nombre de variables mémoire à allouer
	 */
	public void ouvreBloc(int nbVars){
		System.out.println("\tenter " + nbVars * 2 + ",0");
		
		Ecriture.ecrireStringln(f,"\t; ouvreBloc " + nbVars * 2);
		Ecriture.ecrireStringln(f,"\tenter " + nbVars * 2 + ",0");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Fermeture d'un bloc de code
	 * @param nbVars	Nombre de variables mémoire à allouer
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
	 * Retour d'une valeur à adresse donnée
	 * @param offset	Adresse où stocker la valeur
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
	 * Réservation d'une place dans la pile pour le résultat de retour d'une fonction
	 */
	public void reserveRetour(){
		System.out.println("\tsub sp,2");
		
		Ecriture.ecrireStringln(f,"\t; reserveRetour");
		Ecriture.ecrireStringln(f,"\tsub sp,2");
		Ecriture.ecrireStringln(f,"");
	}
	
	/**
	 * Appel d'une fonction
	 * @param nom	Fonction à appeler
	 */
	public void call(String nom){
		System.out.println("\tcall " + nom);
		
		Ecriture.ecrireStringln(f,"\t; call " + nom);
		Ecriture.ecrireStringln(f,"\tcall " + nom);
		Ecriture.ecrireStringln(f,"");
	}
}
