package App.ApiRest.Infra.Persistence.Enum;

public enum GrauEstudo {


    Analfabeto("Nível 1","Analfabeto"),
    QuintoIncompleto("Nível 1","Até 5º Ano Incompleto"),
    QuintoCompleto("Nivel 1","5º Ano Completo"),
    SextoAoNonoFundamental("Nivel 1","6º ao 9º Ano do Fundamental"),
    FundamentalCompleto("Nivel 2","Fundamental Completo"),
    MédioIncompleto("Nivel 2","Médio Incompleto"),
    MédioCompleto("Nível 3","Médio Completo"),
    SuperiorIncompleto("Nível 3","Superior Incompleto"),
    SuperiorCompleto("Nível 4","Superior Completo"),
    Mestrado("Nível 5","Mestrado"),
    Doutorado("Nível 5","Doutorado"),
    Ignorado("Sem Nivel","Ignorado");

    private String Nivel;
    private String GrauEstudo;

    GrauEstudo(String nivel, String grauEstudo) {
        Nivel = nivel;
        GrauEstudo = grauEstudo;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public String getGrauEstudo() {
        return GrauEstudo;
    }

    public void setGrauEstudo(String grauEstudo) {
        GrauEstudo = grauEstudo;
    }
}
