package main.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Generator {

    ArrayList<String> gg = new ArrayList<>();

    ArrayList<GeneratorType> generators;

    public Generator() {

        generators = new ArrayList<>(Arrays.asList(
                new GeneratorType("SDO10", 10000, 12.5, 4.3),
                new GeneratorType("SD015", 15000, 19, 5.6),
                new GeneratorType("SD020", 20000, 25, 6.7),
                new GeneratorType("SD025", 25000, 31, 9.5),
                new GeneratorType("SD030", 30000, 38, 10.4),
                new GeneratorType("SD035", 35000, 44, 11.74),
                new GeneratorType("SD040", 40000, 50, 13.26),
                new GeneratorType("SD050", 50000, 63, 16.36),
                new GeneratorType("SD060", 60000, 75, 18.20),
                new GeneratorType("SD080", 80000, 100, 23.80),
                new GeneratorType("SD0100", 100000, 125, 27.60),
                new GeneratorType("SD01130", 130000, 163, 36.30),
                new GeneratorType("SD0150", 150000, 188, 42.20),
                new GeneratorType("SD0175", 175000, 219, 50.10),
                new GeneratorType("SD0230", 200000, 250, 51.10),
                new GeneratorType("SD0250", 230000, 288, 59.0),
                new GeneratorType("SD0250", 250000, 313, 63.60),
                new GeneratorType("SD0275", 275000, 344, 67.40),
                new GeneratorType("SD0350", 350000, 375, 75.30),
                new GeneratorType("SD0350", 350000, 438, 86.30),
                new GeneratorType("SD0400", 400000, 500, 115.3),
                new GeneratorType("SD035", 500000, 625, 126.3),
                new GeneratorType("SD0600", 600000, 750, 157.8),
                new GeneratorType("SD0750", 750000, 938, 252.1),
                new GeneratorType("SD0800", 800000, 1000, 252.4),
                new GeneratorType("SD0900", 900000, 1125, 299.8),
                new GeneratorType("SD01000", 1000000, 1250, 299.8),
                new GeneratorType("idlc1250-2000", 1250, 1563, 391),
                new GeneratorType("SD01500", 1500000, 1875, 479.2),
                new GeneratorType("SD020000-2M", 2000000, 2500, 603.3)));
    }

    public double getFuelConsumption(double generatorCapacity) {
        for (GeneratorType gen : generators) {
            if (gen.getCapacity_kw() >= generatorCapacity)
                return gen.litre_per_hr;
        }

        return 603.3;
    }


    public double getGeneratorCapacityInKva(double generatorCapacity) {
        for (GeneratorType gen : generators) {
            if (generatorCapacity <= gen.getCapacity_kw())
                return gen.capacity_kva;
        }

        return 2500;
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