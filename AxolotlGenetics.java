
import AxolotlGenetics.Gene;
import AxolotlGenetics.Genotype;

import java.util.EnumMap;
import java.util.Map;
import java.awt.Color;

public class AxolotlGenetics {
     public static void main(String[] args) {
        Genotype geno1 = new Genotype(
                new Gene('A', 'a', 'A', 'a'),
                new Gene('B', 'b', 'B', 'b'),
                new Gene('C', 'c', 'C', 'c'),
                new Gene('D', 'd', 'D', 'd'));

        Genotype geno2 = new Genotype(
                new Gene('A', 'A', 'a', 'a'),
                new Gene('B', 'B', 'b', 'b'),
                new Gene('C', 'C', 'c', 'c'),
                new Gene('D', 'D', 'd', 'd'));

        Genotype offspring = createOffspring(geno1, geno2);
        Color rgbcolour = offspring.getRGBColor();
        System.out.println(offspring);
        System.out.println(rgbcolour);

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

}
