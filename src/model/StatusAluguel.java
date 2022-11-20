package model;

public enum StatusAluguel {
    ALUGADO("Alugado"),
    DESALUGADO("Desalugado");

    private String value;

    StatusAluguel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    public static StatusAluguel getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException();
        for(StatusAluguel v : values())
            if(value.equalsIgnoreCase(v.getValue())) return v;
        throw new IllegalArgumentException();
    }
}
