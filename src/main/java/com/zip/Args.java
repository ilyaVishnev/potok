package com.zip;

public class Args {

    private String[] args;

    public Args(String[] agrs) {
        this.args = agrs;
    }

    public String exclude() {
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-e"))
                return args[++index];
        }
        return "nothing";
    }

    public String directory() {
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-d"))
                return args[++index];
        }
        return "nothing";
    }

    public String output() {
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-o"))
                return args[++index];
        }
        return "nothing";
    }
}
