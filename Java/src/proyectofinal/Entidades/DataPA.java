/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Entidades;

/**
 *
 * @author marco
 */
public class DataPA {
    private String distrito;
    private String estructura;
    private String direccion;
    private String codigo;
    
    public DataPA(String distrito, String estructura, String direccion, String codigo){
        this.distrito = distrito;
        this.estructura = estructura;
        this.direccion = direccion;
        this.codigo = codigo;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getEstructura() {
        return estructura;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
}
