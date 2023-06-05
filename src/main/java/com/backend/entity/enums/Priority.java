package com.backend.entity.enums;


public enum Priority {

    LOW(0, "PRIORIDADE_BAIXA"),
    AVERAGE(1, "PRIORIDADE_MEDIA"),
    HIGH(2, "PRIORIDADE_ALTA");

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    private Integer cod;
    private String description;

    private Priority(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    //?--------------------------------------------------  Getters  ----------------------------------------------------
    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    //?--------------------------------------------------  Methods  ----------------------------------------------------

    public static Priority toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Priority numberPriority : Priority.values()) {
            if (cod.equals(numberPriority.getCod())) {
                return numberPriority;
            }
        }

        throw new IllegalArgumentException("⚠️ ⚠️ ️ID PRIORITY invalidity: / ID PRIORITARIO invalido ⚠️ ⚠️" + cod);
    }
}
