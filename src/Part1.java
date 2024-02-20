import edu.duke.*;
import javax.print.attribute.standard.Sides;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int currStopCodon;
        int temp;
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        temp = Math.min(tagIndex, taaIndex);
        currStopCodon = Math.min(temp, tgaIndex);
        if (currStopCodon == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, currStopCodon + 3);
        }

    }

    public void testFindStopCodon() {
        int test1, test2, test3;
        test1 = findStopCodon("ATGTTRDRRTGA", 0, "TGA");
        System.out.println(test1);
        test2 = findStopCodon("ATGTTETTAARTETETTTTAATAGTGA", 0, "TAA");
        System.out.println(test2);
        test3 = findStopCodon("TAA", 0, "TAA");
        System.out.println(test3);
    }

    public void findAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = startIndex + gene.length();
        }
    
    }

    public void testOn(String dna) {
        System.out.println("Testing All gene on strand:" + dna);
        StorageResource geneList = new StorageResource();
        geneList = getAllGenes(dna);
        for (String oneGene : geneList.data()) {
            System.err.println(oneGene);
        }

    }

    public void testing() {
        testOn("ATGATCTAATTTATGCTGTGAATG");
        testOn("");
        testOn("ATGTAA");
        testOn("ATG");
        testOn("TAA");
    }

    public StorageResource getAllGenes(String dna) {
        StorageResource geneBase = new StorageResource();
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }

            geneBase.add(gene);
            startIndex = startIndex + gene.length();

        }
        //System.out.println(geneBase.size());
        return geneBase;
    }

    public double cgRatio(String dna) {
        int cRatio = baseInSeq(dna, "C");
        int gRatio = baseInSeq(dna, "G");
        int dnaLength = dna.length();
        //System.out.println(dnaLength);
        Double ratio = ((double) (cRatio + gRatio)) / dnaLength;
        return ratio;
    }

    

    public int baseInSeq(String dna, String base) {
        int count = 0;
        int currentIndex;
        int startIndex = 0;
        while (true) {
            currentIndex = dna.indexOf(base, startIndex);
            if (currentIndex == -1) {
                break;
            }
            count++;
            startIndex = currentIndex + 1;
        }

        return count;
    }
    public int geneCount(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dnaString = fr.asString();
        StorageResource base = new StorageResource();
        base = getAllGenes(dnaString);
        return base.size();
    }
    
    public double codonRatio (String dna){
    int codonNum = baseInSeq(dna, "CTG");
    int dnaLength = dna.length();
    double ratio = (double)codonNum/dnaLength;
    return ratio;
    }
    
    // test codon ration in a strand 
    public void testingCodon(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dnaString = fr.asString();
        System.out.println(" the codon CTG appear times is "+baseInSeq(dnaString,"CTG"));
    }
    /*public void printss(){
        StorageResource ss = new StorageResource();
        ss.add("dfdfd");
        ss.add("dfdfd");
        ss.add("dfdfd");
        ss.add("dfdfd");
        ss.add("dfdfd");
        System.out.println(ss);
        System.out.println(ss.data());
        System.out.println(ss.toString());
    }*/
    public static void main(String[] args) {
        Part1 P1 = new Part1();
        //P1.testing();
        //int pp = P1.baseInSeq("ATGATAAGGTAGTCCTG", "GTC");
        //System.out.println(pp); 
        ProcessGene Pg = new ProcessGene();
        //Pg.test();
        Pg.testProcessGene();
        System.out.println(P1.geneCount());
        P1.testingCodon();
        P1.toString();
        
        
        
        
    }
}
