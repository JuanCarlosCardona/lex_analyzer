package com.analyzer.lexical_analyzer;

public class Lex {

    private String lexeme;
    private String token;
    private String pattern;

    public Lex(String lexeme, String token, String pattern) {
        this.lexeme = lexeme;
        this.token = token;
        this.pattern = pattern;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return "Lex{" +
                "lexeme='" + lexeme + '\'' +
                ", token='" + token + '\'' +
                ", pattern='" + pattern + '\'' +
                '}';
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
