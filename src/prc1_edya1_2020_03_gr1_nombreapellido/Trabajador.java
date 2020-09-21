
/**
 *  
 *  @author Prof: J. Antonio Lemos B.
 *  
 *  Estructuras de Datos y Algoritmos 1, Grupo 1.
 *  Práctica del Corte 1.
 *  Fecha: Cali, Septiembre 10 de 2020.
 * 
 *  HarbyTorresDagua:
 *  2170920:
 * 
 *
 */

package prc1_edya1_2020_03_gr1_nombreapellido;

import java.text.DecimalFormat;

@SuppressWarnings("EqualsAndHashcode")
public class Trabajador implements Comparable {
    // Atributos:
    private String cedula;          // Cédula del trabajador.
    private String apeNom;          // Apellidos y Nombres del trabajador.
    private int antLab;             // Antiguedad laboral.
    private double smn;             // Salario mensual nominal.
    private double prestaciones;    // Prestaciones sociales.
    private double bono;            // Aporte al bono de seguridad.
    @SuppressWarnings("FieldMayBeFinal")
    private DecimalFormat df;
    
    // Constante pública de clase:
    public static final double SMMLV = 877803;  // SMMLV: Salario Mensual Mínimo Legal Vigente.
    public static final double AUXILIO = 115000;  // auxilio de transporte.
    
    // Constructor de la clase:
    public Trabajador(String cedula, String apeNom, int antLab, double smn) {
        this.cedula = cedula.trim();
        this.apeNom = apeNom.trim().toUpperCase();
        this.antLab = antLab;
        this.smn = smn;
        df = new DecimalFormat("0.00");
    }   // Fin del constructor.
    
    // Métodos Getters (o de consulta):
    public String getCedula(  ) {  return cedula;  }
    public String getApeNom() {  return apeNom;  }
    public int getAntLab() {  return antLab;  }    
    public double getSmn() {  return smn;  }
    
    // Métodos Setters (o modificadores):
    public void setCedula(String cedula) {
        if( cedula.trim( ).length( ) == 0 ) return;
        this.cedula = cedula.trim( );
    }
    public void setApeNom( String apeNom ) {
        if( apeNom.trim( ).length( ) == 0 ) return;
        this.apeNom = apeNom.trim( ).toUpperCase( );
    }
    public void setAntLab(int antLab) {
        if(antLab < 0) return;
        this.antLab = antLab;
    }
    public void setSmn(double smn) {
        if(smn <= 0) return;
        this.smn = smn;
    }
    
    // Construye y retorna un reporte con el estado del trabajador; es decir, 
    // con los valores actuales de sus atributos:
    @Override
    public String toString( ) {
        return "Cédula: " + cedula + ", Trabajador: " + apeNom + ", Antiguedad" 
            + " laboral: " + antLab + " años, Salario mensual nominal: $ " + 
            df.format(smn) + ", Salario mensual neto: $ " + 
            df.format( calcularSalarioNeto( ) );
    }
    
    // Método que calcula y retorna el valor del salario neto 
    public double calcularSalarioNeto() {
        double salarioNeto = 0;
        // *********************************************************************
        double SMMLV = 877803;
        double temp =smn-(smn*0.08);
        if(smn<=SMMLV*2){
            salarioNeto=temp+115000;
        }else if(smn>SMMLV*4){
            salarioNeto=temp*0.01;
        }else{
            salarioNeto=temp;
        }
        // *********************************************************************
        return salarioNeto;
    }
    
    // Método que determina si el trabajador aprtó al bono de solidaridad, así:
    // true --> significa que aportó; false --> significa que no aportó.
    public boolean aportoAlBono() {
        boolean aporto = false;
        // *********************************************************************
        double SMMLV = 877803;
        if(smn>SMMLV*4){
            aporto= true;
        }
        // *********************************************************************
        return aporto;
    }
    
    /**
     * Establece la comparación por apellidos, para el ordenamiento alfabético.
     * @param obj
     * @return El valor arrojado por el método compareTo(...) de la clase String. 
     */ 
    @Override
    public  int  compareTo( Object  obj ) {
        @SuppressWarnings("UnusedAssignment")
        Trabajador t = null;
        try {
           t = (Trabajador) obj;   // Try casting.            
        }
        catch( ClassCastException  errorCast ) {
            System.out.println( errorCast );
            System.exit(0);
        }
        // Usar el compareTo(...) de la clase String:
        return  this.getApeNom( ).compareTo( t.getApeNom( ) ) ;
    }
    
    /**
     * Establece la igualdad por cédulas de trabajadores, para las búsquedas.
     * @param obj
     * @return true si esta (this) cédula es igual a la cédula de obj. 
     * En caso contrario, retorna false 
     */
    @Override
    public  boolean  equals( Object  obj ) {
        if( obj instanceof Trabajador ) {
            Trabajador  t  =  (Trabajador) obj ;
            // Comparar cédulas:
            return  this.getCedula( ).equals(t.getCedula( ) ) ;  // true/false
        }
        return  false ;
    }
    
}