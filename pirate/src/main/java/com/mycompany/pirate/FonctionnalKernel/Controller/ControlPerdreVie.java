package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;

public class ControlPerdreVie {
    public ControlPerdreVie() {
      // TODO document why this constructor is empty
    }

    // return vie restante
    public int perdreVie(Pion pion, int degats) {
        pion.setVie(pion.getVie() - degats);
        return pion.getVie();
    }
}
