
package Modelo;

import java.util.Objects;


public class Materia {
    private int idMateria;
    private String nombreMateria;
    private int anioMateria;
    private boolean estadoMateria;

    public Materia() {
    }

    public Materia(String nombreMateria, int anioMateria, boolean estadoMateria) {
        this.nombreMateria = nombreMateria;
        this.anioMateria = anioMateria;
        this.estadoMateria = estadoMateria;
    }

    public Materia(int idMateria, String nombreMateria, int anioMateria, boolean estadoMateria) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.anioMateria = anioMateria;
        this.estadoMateria = estadoMateria;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getAnioMateria() {
        return anioMateria;
    }

    public void setAnioMateria(int anioMateria) {
        this.anioMateria = anioMateria;
    }

    public boolean isEstadoMateria() {
        return estadoMateria;
    }

    public void setEstadoMateria(boolean estadoMateria) {
        this.estadoMateria = estadoMateria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.idMateria;
        hash = 43 * hash + Objects.hashCode(this.nombreMateria);
        hash = 43 * hash + this.anioMateria;
        hash = 43 * hash + (this.estadoMateria ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materia other = (Materia) obj;
        if (this.idMateria != other.idMateria) {
            return false;
        }
        if (this.anioMateria != other.anioMateria) {
            return false;
        }
        if (this.estadoMateria != other.estadoMateria) {
            return false;
        }
        return Objects.equals(this.nombreMateria, other.nombreMateria);
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombreMateria=" + nombreMateria + ", anioMateria=" + anioMateria + ", estadoMateria=" + estadoMateria + '}';
    }
}

