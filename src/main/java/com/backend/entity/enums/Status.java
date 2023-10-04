package com.backend.entity.enums;


public enum Status {

    OPEN(0, "STATUS_ABERTO"),
    PROGRESS(1, "STATUS_ANDAMENTO"),
    CLOSED(2, "STATUS_FECHADO");

    //! -------------------------------------------------  Constructor  ------------------------------------------------
    private Integer cod;
    private String description;

    private Status(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    //! -------------------------------------------------  Getters  ----------------------------------------------------
    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    //! -------------------------------------------------  Methods  ----------------------------------------------------

    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Status numberStatus : Status.values()) {
            if (cod.equals(numberStatus.getCod())) {
                return numberStatus;
            }
        }

        throw new IllegalArgumentException("⚠️ ⚠️ ️invalid STATUS ID: / ID de STATUS invalido ⚠️ ⚠️" + cod);
    }
}

