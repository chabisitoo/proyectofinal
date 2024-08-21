module com.mycompany.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    //para la base de datos
    requires java.base;
    requires java.sql;
    requires jasperreports;
    opens com.mycompany.proyectofinal to javafx.fxml;
    exports com.mycompany.proyectofinal;
    exports com.mycompany.proyectofinal.modelo;
    
    

    opens com.mycompany.proyectofinal.modelo to javafx.base;

    
    
}

