package AxolotlGenetics;
import java.util.EnumMap;
import java.util.Map;
import java.awt.Color;


public class Genotype {
    public enum geneNames {
        R, G, B, T;
    }

    private Map<geneNames, Gene> genes;

    public Genotype(Gene R, Gene G, Gene B, Gene T) {
        genes = new EnumMap<>(geneNames.class);
        genes.put(geneNames.R, R);
        genes.put(geneNames.G, G);
        genes.put(geneNames.B, B);
        genes.put(geneNames.T, T);
    }

    public Genotype(Map<geneNames, Gene> geneMap) {
        genes = new EnumMap<>(geneMap);
    }

    public Gene getGene(geneNames name) {
        return genes.get(name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (geneNames geneName : geneNames.values()) {
            sb.append(geneName).append(": ").append(genes.get(geneName)).append("\n");
        }
        return sb.toString();
    }

    public static Gene combineGametes(Gene Gamete1, Gene Gamete2) {
        Gene newGene = new Gene(Gamete1.getAllele1(),
                Gamete2.getAllele1(),
                Gamete1.getAllele2(),
                Gamete2.getAllele2());
        newGene.sortGenes();
        return newGene;
    }

    public Color getRGBColor() {
        int RValue = getGene(geneNames.R).convertToHueValue();
        int GValue = getGene(geneNames.G).convertToHueValue();
        int BValue = getGene(geneNames.B).convertToHueValue();
        Color RGBColor = new Color(RValue, GValue, BValue);
        return RGBColor;
    }
}
