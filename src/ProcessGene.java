import java.io.File;
import java.net.Socket;
import edu.duke.*;

public class ProcessGene {
    public void processGene(StorageResource dnaBese){
        StorageResource longer = new StorageResource(); //longer than 9 charcters
        StorageResource cgRatio = new StorageResource(); //C-G-Ratio higher than 0.35
        Part1 P1 = new Part1();
        int longest = 0;
        for (String dnaSeq : dnaBese.data()){
            if (dnaSeq.length()> longest ){
                longest = dnaSeq.length();
            }
            if (dnaSeq.length() > 60 ){
                longer.add(dnaSeq);
            }
            if (P1.cgRatio(dnaSeq) > 0.35){
                cgRatio.add(dnaSeq);
            }
        }


        /*System.out.println("\nDNA sequences longer than 60 characters: ");
        for (String s : longer.data()){
            System.out.println(s);
        } */
        System.out.println("number of DNA sequences that are longer than 9 characters");
        System.out.println(longer.size());
        //System.out.println("DNA sequences with C-G-Ratio higher than 0.35: \n");
        //for (String s : cgRatio.data()){
        //    System.out.println(s);
        //}

        System.out.println("number of DNA sequences with C-G-Ratio higher than 0.35 :\n" + cgRatio.size());
        System.out.println("longest DNA Squeunce :" + longest); 

        
    }
    //public int 
    public StorageResource fileResource(String fileName){
        Part1 P1 = new Part1();
        FileResource fr = new FileResource(fileName);
        String dna =fr.asString();
        dna = dna.toUpperCase();

        StorageResource dnaBase1 = new StorageResource();
        dnaBase1 = P1.getAllGenes(dna);
        return dnaBase1; 
    }
    public void testProcessGene(){
        StorageResource dnaBase = fileResource("GRch38dnapart.fa");
        processGene(dnaBase);
    }
}
