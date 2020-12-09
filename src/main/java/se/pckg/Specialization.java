package se.pckg;

import se.DAO.SpecializationsRepository;

import java.util.Comparator;

public class Specialization implements Comparable<Specialization> {
    private int id;
    private String name;
    private boolean narrow;
    private int amountOfDocs;
    private double wageRate;
    private double costs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNarrow() {
        return narrow;
    }

    public void setNarrow(boolean narrow) {
        this.narrow = narrow;
    }

    public int getAmountOfDocs() {
        return amountOfDocs;
    }

    public void setAmountOfDocs(int amountOfDocs) {
        this.amountOfDocs = amountOfDocs;
    }

    public double getWageRate() {
        return wageRate;
    }

    public void setWageRate(double wageRate) {
        this.wageRate = wageRate;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public int compareTo(Specialization spec) {
        return this.name.compareTo(spec.getName());
    }
}
