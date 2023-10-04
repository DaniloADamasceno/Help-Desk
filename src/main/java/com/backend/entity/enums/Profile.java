package com.backend.entity.enums;


public enum Profile {

    ADMIN(0, "PERFIL_ADMIN"),
    CUSTOMER(1, "PERFIL_CLIENTE"),
    TECHNICIAN(2, "PERFIL_TECNICO");

    //! -------------------------------------------------  Constructor  ------------------------------------------------
    private Integer cod;
    private String description;

    private Profile(Integer cod, String description) {
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

    public static Profile toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Profile numberProfile : Profile.values()) {
            if (cod.equals(numberProfile.getCod())) {
                return numberProfile;
            }
        }

        throw new IllegalArgumentException("⚠️ ⚠️ ️invalid PROFILE ID: / ID invalido de PERFIL ⚠️ ⚠️" + cod);
    }
}
