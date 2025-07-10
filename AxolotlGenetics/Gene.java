package AxolotlGenetics;

import java.util.Random;

public class Gene {
    private char allele1;
    private char allele2;
    private char allele3;
    private char allele4;

    public Gene(char allele1, char allele2, char allele3, char allele4) {
        this.allele1 = allele1;
        this.allele2 = allele2;
        this.allele3 = allele3;
        this.allele4 = allele4;
    }

    public Gene(char allele1, char allele2) {
        this.allele1 = allele1;
        this.allele2 = allele2;
    }

    public char getAllele1() {
        return allele1;
    }

    public char getAllele2() {
        return allele2;
    }

    public char getAllele3() {
        return allele3;
    }

    public char getAllele4() {
        return allele4;
    }

    public void setAllele1(char allele) {
        this.allele1 = allele;
    }

    public void setAllele2(char allele) {
        this.allele2 = allele;
    }

    public void setAllele3(char allele) {
        this.allele3 = allele;
    }

    public void setAllele4(char allele) {
        this.allele4 = allele;
    }

    public String toString() {
        return "" + allele1 + allele2 + allele3 + allele4;
    }

    public Gene generateGamete() {
        Gene Gamete = new Gene('a', 'a');
        Random random = new Random();
        if (random.nextDouble() <= 0.5) {
            Gamete.allele1 = allele1;
        } else {
            Gamete.allele1 = allele2;
        }
        if (random.nextDouble() <= 0.5) {
            Gamete.allele2 = allele3;
        } else {
            Gamete.allele2 = allele4;
        }

        return Gamete;
    }

    public int convertToHueValue() {
        int hueValue;
        if (isLowerCase(allele1)) {
            if (isUpperCase(allele3)) {
                hueValue = 90;
                return hueValue;
            }
            hueValue = 40;
            return hueValue;
        }
        if (isLowerCase(allele3)) {
            hueValue = 140;
            return hueValue;
        }
        if (isLowerCase(allele4) || isLowerCase(allele2)) {
            hueValue = 190;
            return hueValue;
        } else {
            hueValue = 240;
            return hueValue;
        }
    }

    public void sortGenes() {
        if (isWrongGeneOrder(allele1, allele2)) {
            char allele = allele1;
            allele1 = allele2;
            allele2 = allele;
        }
        if (isWrongGeneOrder(allele3, allele4)) {
            char allele = allele3;
            allele3 = allele4;
            allele4 = allele;
        }
    }

    private boolean isWrongGeneOrder(char allele1, char allele2) {
        if (isLowerCase(allele1)) {
            if (isUpperCase(allele2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLowerCase(char allele) {
        return Character.isLowerCase(allele);
    }

    private boolean isUpperCase(char allele) {
        return Character.isUpperCase(allele);
    }
}
