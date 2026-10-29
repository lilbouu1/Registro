package Modelo;

import java.util.LinkedList;
import java.util.stream.Collectors;
import Controlador.Persona;

public class GestorPersonas {
    private LinkedList<Persona> listaPersonas;

    public GestorPersonas() {
        this.listaPersonas = new LinkedList<>();
    }

    public void registrarPersona(Persona persona) {
        listaPersonas.add(persona);
    }

    public boolean actualizarPersona(Persona personaActualizada) {
        for (int i = 0; i < listaPersonas.size(); i++) {
            if (listaPersonas.get(i).getId() == personaActualizada.getId()) {
                listaPersonas.set(i, personaActualizada);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPersona(int id) {
        return listaPersonas.removeIf(p -> p.getId() == id);
    }

    public Persona buscarPersona(int id) {
        for (Persona p : listaPersonas) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean existeId(int id) {
        return buscarPersona(id) != null;
    }

    public LinkedList<Persona> buscarPorNombre(String nombre) {
        return listaPersonas.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Persona> filtrarPorRangoEdad(int edadMin, int edadMax) {
        return listaPersonas.stream()
                .filter(p -> p.getEdad() >= edadMin && p.getEdad() <= edadMax)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Persona> obtenerTodas() {
        return listaPersonas;
    }
}