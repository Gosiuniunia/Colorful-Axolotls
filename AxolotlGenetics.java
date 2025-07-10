
import AxolotlGenetics.Gene;
import AxolotlGenetics.Genotype;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.awt.Color;

public class AxolotlGenetics {
    public static void main(String[] args) {
        Genotype geno1 = new Genotype(
                new Gene('A', 'a', 'A', 'a'),
                new Gene('B', 'b', 'b', 'b'),
                new Gene('c', 'c', 'C', 'C'),
                new Gene('D', 'd'));

        Genotype geno2 = new Genotype(
                new Gene('A', 'a', 'A', 'a'),
                new Gene('B', 'B', 'b', 'b'),
                new Gene('c', 'c', 'C', 'c'),
                new Gene('D', 'D'));
        System.out.println(geno1.getRGBColor());        
        System.out.println(geno2.getRGBColor());

        Genotype offspring = breed(geno1, geno2);
        Color rgbcolour = offspring.getRGBColor();
        System.out.println(offspring);
        System.out.println(rgbcolour);

    }

    public static Genotype breed(Genotype parent1, Genotype parent2) {
        Color rgbColorParent1 = parent1.getRGBColor();
        Color rgbColorParent2 = parent2.getRGBColor();
        Genotype offspring;
        if (rgbColorParent1.equals(rgbColorParent2)) {
            System.out.println("Takie same");
            offspring = mutateOffspring(parent1, parent2);
        } else {
            System.out.println("Inne");
            offspring = createOffspring(parent1, parent2);
        }
        return offspring;
    }

    public static Genotype createOffspring(Genotype parent1, Genotype parent2) {
        Map<Genotype.geneNames, Gene> offspingGenes = new EnumMap<>(Genotype.geneNames.class);
        for (Genotype.geneNames geneName : Genotype.geneNames.values()) {
            Gene gene1 = parent1.getGene(geneName);
            Gene gene2 = parent2.getGene(geneName);
            Gene gamete1 = gene1.generateGamete();
            Gene gamete2 = gene2.generateGamete();
            Gene newGene = Genotype.combineGametes(gamete1, gamete2);
            offspingGenes.put(geneName, newGene);
        }
        Genotype offspring = new Genotype(offspingGenes);
        return offspring;
    }

    public static Genotype mutateOffspring(Genotype parent1, Genotype parent2) {
        Map<Genotype.geneNames, Gene> offspingGenes = new EnumMap<>(Genotype.geneNames.class);
        Random random = new Random();
        for (Genotype.geneNames geneName : Genotype.geneNames.values()) {
            if (random.nextDouble() < 0.5) {
                offspingGenes.put(geneName, parent1.getGene(geneName));
            } else {
                offspingGenes.put(geneName, parent2.getGene(geneName));
            }
        }

        Genotype offspring = new Genotype(offspingGenes);
        mutateOffspringAllele(offspring);
        return offspring;
    }

    private static Genotype.geneNames getGeneToMutate() {
        Random random = new Random();
        Genotype.geneNames[] kolory = Genotype.geneNames.values();
        Genotype.geneNames losowyGen = kolory[random.nextInt(kolory.length)];
        return losowyGen;
    }

    private static Genotype mutateOffspringAllele(Genotype offspring) {
        Genotype.geneNames geneToMutate = getGeneToMutate();
        Random random = new Random();
        int allele;
        if (geneToMutate == Genotype.geneNames.T) {
            allele = random.nextInt(2);
        } else {
            allele = random.nextInt(4);
        }
        System.out.println(geneToMutate);
        System.out.println(allele);
        Gene alleleToMutate = offspring.getGene(geneToMutate);
        switch (allele) {
            case 0:
                char allele1 = alleleToMutate.getAllele1();
                allele1 = reverseFondCase(allele1);
                alleleToMutate.setAllele1(allele1);
                break;
            case 1:
                char allele2 = alleleToMutate.getAllele2();
                allele2 = reverseFondCase(allele2);
                alleleToMutate.setAllele2(allele2);
                break;
            case 2:
                char allele3 = alleleToMutate.getAllele3();
                allele3 = reverseFondCase(allele3);
                alleleToMutate.setAllele3(allele3);
                break;
            case 3:
                char allele4 = alleleToMutate.getAllele4();
                allele4 = reverseFondCase(allele4);
                alleleToMutate.setAllele4(allele4);
                break;
        }

        return offspring;
    }

    private static char reverseFondCase(char allele) {
        if (Character.isLowerCase(allele)) {
            return Character.toUpperCase(allele);
        } else {
            return Character.toLowerCase(allele);
        }

    }
}
