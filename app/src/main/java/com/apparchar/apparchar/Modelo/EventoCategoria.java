package com.apparchar.apparchar.Modelo;

public class EventoCategoria {

    private int id;
    private int categoria;
    private int evento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "EventoCategoria{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", evento=" + evento +
                '}';
    }
}
