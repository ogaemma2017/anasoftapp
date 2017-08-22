package main.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Generator {

    ArrayList<String> gg = new ArrayList<>();

    ArrayList<GeneratorType> generators;

    public Generator() {

        generators = new ArrayList<>(Arrays.asList(
                new GeneratorType("SDO10", 10, 12.5, 4.3),
                new GeneratorType("SD015", 15, 19, 5.2),
                new GeneratorType("SD020", 20, 25, 6.6),
                new GeneratorType("SD025", 25, 31, 9.3),
                new GeneratorType("SD030", 30, 38, 10.2),
                new GeneratorType("SD035", 35, 44, 12),
                new GeneratorType("SD040", 40, 50, 13.6),
                new GeneratorType("SD050", 50, 63, 15.8),
                new GeneratorType("SD060", 60, 75, 18.7),
                new GeneratorType("SD080", 80, 100, 23),
                new GeneratorType("SD0100", 100, 125, 28.4),
                new GeneratorType("SD01130", 130, 163, 37.9),
                new GeneratorType("SD0150", 150, 188, 42.3),
                new GeneratorType("SD0175", 175, 219, 50.7),
                new GeneratorType("SD0230", 200, 250, 57.8),
                new GeneratorType("SD0250", 250, 313, 72.3),
                new GeneratorType("SD0300", 35, 315, 85.6),
                new GeneratorType("SD0275", 275, 344, 76.9),
                new GeneratorType("SD0350", 350, 438, 98.8),
                new GeneratorType("SD0400", 400, 500, 115.3),
                new GeneratorType("SD035", 500, 625, 126.3),
                new GeneratorType("SD0600", 600, 750, 157.8),
                new GeneratorType("SD0750", 750, 938, 252.1),
                new GeneratorType("SD0800", 800, 1000, 252.4),
                new GeneratorType("SD0900", 900, 1125, 299.8),
                new GeneratorType("SD01000", 1000, 1250, 299.8),
                new GeneratorType("idlc1250-2000", 1250, 1563, 391),
                new GeneratorType("SD01500", 1500, 1875, 479.2),
                new GeneratorType("SD020000-2M", 2000, 2500, 603.3)));
    }

    public double getFuelConsumption(double generatorCapacity) {
        for (GeneratorType gen : generators) {
            if (generatorCapacity <= gen.getCapacity_kw())
                return gen.litre_per_hr;
        }

        return 603.3;
    }

    class GeneratorType {
        private String type;
        private double capacity_kw;
        private double capacity_kva;
        private double litre_per_hr;

        public GeneratorType(String type, double capacity_kw, double capacity_kva, double litre_per_hr) {
            this.type = type;
            this.capacity_kw = capacity_kw;
            this.capacity_kva = capacity_kva;
            this.litre_per_hr = litre_per_hr;

        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getCapacity_kw() {
            return capacity_kw;
        }

        public void setCapacity_kw(double capacity_kw) {
            this.capacity_kw = capacity_kw;
        }

        public double getCapacity_kva() {
            return capacity_kva;
        }

        public void setCapacity_kva(double capacity_kva) {
            this.capacity_kva = capacity_kva;
        }

        public double getLitre_per_hr() {
            return litre_per_hr;
        }

        public void setLitre_per_hr(double litre_per_hr) {
            this.litre_per_hr = litre_per_hr;
        }
    }


}