package factory;

import classes.Loterias;
import classes.Lotofacil;
import classes.MegaSena;
import classes.Quina;

public class LoteriasFactory {

    public static Loterias getLoterias(int opc) {
       if (opc == 1) {
            return new MegaSena();
        } else if (opc == 2) {
            return new Quina();
        } else if (opc == 3) {
            return new Lotofacil();
        }
        return null;
    }
}