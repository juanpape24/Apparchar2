package com.apparchar.apparchar.Modelo;

import java.io.Serializable;

/**
 *
 * @author jeffe
 */
@SuppressWarnings("serial")
public class Token implements Serializable {

    public String accion;
    public String clase;
    public Object objecto;

    public Token() {
    }

    public Token(String accion, String clase, Object objecto) {
        this.accion = accion;
        this.clase = clase;
        this.objecto = objecto;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Object getObjecto() {
        return objecto;
    }

    public void setObjecto(Object objecto) {
        this.objecto = objecto;
    }

    @Override
    public String toString() {
        return "Token{" + "accion=" + accion + ", clase=" + clase + ", objecto=" + objecto + '}';
    }

}
