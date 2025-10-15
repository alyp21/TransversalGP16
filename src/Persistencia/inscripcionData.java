
package Persistencia;

import org.mariadb.jdbc.Connection;

public class inscripcionData {

    public inscripcionData() {
    }
    
    private Connection con = null;
    
    
    public inscripcionData(Connection con) {
        this.con = con;
    }
    
}
