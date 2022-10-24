package com.analyzer.lexical_analyzer;

public enum Paths {
    EXECUTE_FILE("backend/LexicalAnalyzer/lexical_analyzer.py"),
    FILE1("test1.lya"),
    FILE2("test2.lya"),
    PATH("backend/LexicalAnalyzer/");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
