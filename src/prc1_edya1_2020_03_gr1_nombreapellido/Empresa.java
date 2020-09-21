
/**
 *  
 *  @author Prof: J. Antonio Lemos B.
 *  
 *  Estructuras de Datos y Algoritmos 1, Grupo 1.
 *  Práctica del Corte 1.
 *  Fecha: Cali, Septiembre 10 de 2020.
 *
 */
package prc1_edya1_2020_03_gr1_nombreapellido;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Empresa {
    // Atributos:
    private String empresa; // Nombre de la empresa.
    private String NIT;     // NIT (Número de Identificación Tributaria) de la empresa.
    // Arreglo de tamaño variable con los atributos de los trabajadores:
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Trabajador> trabajadores;
    // Arreglo de tamaño variable que contiene las cédulas de los trabajadores:
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<String> cedulas;
    @SuppressWarnings("FieldMayBeFinal")
    private boolean arreglosCreados;    // Si ya han sido creados, es true 
                                        // De lo contrario, es false
    @SuppressWarnings("FieldMayBeFinal")
    private DecimalFormat df;
    
    // Constructor:
    public Empresa( String empresa, String NIT ) {
        this.empresa = empresa.trim().toUpperCase();
        this.NIT = NIT.trim().toUpperCase();
        // Crea dos arreglos vacíos: 
        trabajadores = new ArrayList<>();
        cedulas = new ArrayList<>();
        arreglosCreados = true;
        df = new DecimalFormat("0.00");
    }   // Fin del constructor.
    
    // Métodos Getters (o de consulta):
    public String getEmpresa() {  return empresa;  }
    public String getNIT() {  return NIT;  }
    public boolean getArreglosCreados() {
        return arreglosCreados;
    }
    
    // Métodos Setters (o modificadores):
    public void setEmpresa(String empresa) {
        if(empresa.trim().length() == 0) return;
        this.empresa = empresa.trim().toUpperCase();
    }
    public void setNIT(String NIT) {
        if(NIT.trim().length() == 0) return;
        this.NIT = NIT.trim().toUpperCase();
    }
    
    // Construye y retorna un reporte con el estado de la empresa; es decir, 
    // con los valores actuales de sus atributos (incluye a sus trabajadores):
    @Override
    public String toString() {
        String estado = "";
        // *********************************************************************
        String trabajadoreslist="";
        for (Trabajador trabajador : trabajadores) {
            trabajadoreslist += trabajador.toString()+"\n";
        }
        estado="Nombre: "+empresa+", NIT: "+NIT+"\nTrabajadores:"
                +trabajadoreslist; 
        // *********************************************************************
        return estado;    
    }
    
    // Verifica que la cédula del trabajador no vaya a estar repetida:
    private boolean yaExiste( String cedula ) {
        boolean existe = true;
        for( String  ced: cedulas ) {
            if( ced.equals(cedula) ) {
                return existe;  // Salga del método. 
            }   // if
        }   // for
        cedulas.add( cedula ) ;
        return ! existe;
    }   // NO BORRAR: FIN DEL MÉTODO yaExiste( ... )
    
    // Si la cédula del trabajador no está repetida (es ÚNICA), lo agrega al
    // final de la lista de trabajadores. Si ya existe, lo ignora y no lo agrega
    // a la lista de trabajadores.
    public void agregarTrabajador( Trabajador  t ) {
        if( yaExiste( t.getCedula() ) ) {
            String msj = "Ya existe un trabajador con la cédula " + 
                t.getCedula() ;
            JOptionPane.showMessageDialog(null, msj, 
                "ERROR: la cédula ya existe", 0);
            System.out.println(msj);
            return; // Salga del m�todo.
        }   // if
        trabajadores.add( t ) ;                
    }   // NO BORRAR: FIN DEL MÉTODO agregarTrabajador( ... )
    
    // A partir de la cédula de un trabajador, busca su posición en el arreglo de
    // trabajadores. Si no lo encuentra, retorna la posición inválida -1. 
    public int buscarPosicionTrabajador( String  cedula ) {
        int posicion = -1;
        // *********************************************************************
        for (int i =0; i<trabajadores.size(); i++) {
            if(trabajadores.get(i).getCedula().equals(cedula)){
                return i;
            }
        }
        // *********************************************************************
        return posicion;  
    }
    
    // A partir de la cédula de un trabajador, busca sus datos en el arreglo de
    // trabajadores. Si no lo encuentra, informa que no existe. 
    public  String  buscarDatosTrabajador( String  cedula ) {
        String datosTrab = "";
        // *********************************************************************
        int indice= buscarPosicionTrabajador(cedula);
        if(indice==-1){
            datosTrab="NO existe el trabajador de cedula:"+cedula;
        }else{
            datosTrab=trabajadores.get(indice).toString();
        }
        // *********************************************************************
        return datosTrab ;        
    }
    
    // Elimina al trabajador con cédula cedula. Si no existe, lo informa. Tener
    // presente que también se debe eliminar cedula del arreglo de cédulas.
    @SuppressWarnings("UnusedAssignment")
    public  String  eliminar( String  cedula ) {
        String msj;
        int posicion = this.buscarPosicionTrabajador( cedula ) ;
        if( posicion == -1 ) {
            msj = "No existe un trabajador con cédula " + cedula;            
        }
        else {
            // Obtener el índice de cedula en el arreglo cedulas:
            int indCedTrab = cedulas.indexOf( cedula ) ;
            // Elimine cedula del arreglo cedulas:
            cedulas.remove( indCedTrab ) ;
            // Elimínelo del arreglo de trabajadores:
            //Trabajador trab = trabajadores.remove( posicion );
            //msj = "Se ha eliminado al trabajador:\n" + trab.toString();
            trabajadores.remove( posicion ) ;
            msj = "Se ha eliminado al trabajador con cédula " + cedula;
        }
        return msj;
    }
    
    //
    public  String  quienesAportanAlBono( ) {
        if( trabajadores.isEmpty() ) return  "Arreglo Vacío: No hay aún trabajadores";
        String aportaron = "Los siguientes trabajadores aportaron al Bono de " + 
            "Solidaridad:\n";
        // *********************************************************************
        for (Trabajador trabajador : trabajadores) {
            if(trabajador.aportoAlBono()){
                aportaron += trabajador.toString();
            }
        }
        // *********************************************************************
        return  aportaron ;
    }
    
    //
    public  String  obtenerAltosSalarios( double  smnBase ) {
        if( trabajadores.isEmpty() ) return  "Arreglo Vacío: No hay aún trabajadores";
        String altos = "Los siguientes trabajadores devengan un SMN (Salario " + 
            "Mensual Nominal) mayor que " + smnBase + ":\n";
        // *********************************************************************
        for (Trabajador trabajador : trabajadores) {
            if(trabajador.getSmn()>smnBase){
                altos += trabajador.toString();
            }
        }
        // *********************************************************************
        return  altos ;
    }
    
    // Método que determina y retorna los datos del trabajador con el mayor SMN 
    // (Salario Mensual Nominal). Si hay 2 o más con ese mayor SMN, el método
    // retorna los datos de la primer ocurrencia:
    public String datosTrabajadorConElMayorSMN() {
        if( trabajadores.isEmpty() ) return  "Arreglo Vacío: No hay aún trabajadores";
        // Suponer que el primer trabajador tiene el mayor SMN:
        String conElMayorSMN = trabajadores.get( 0 ).toString( ) ; 
        double mayorSMN = trabajadores.get( 0 ).calcularSalarioNeto() ;
        // *********************************************************************
        for (Trabajador trabajador : trabajadores) {
            if(trabajador.calcularSalarioNeto()>mayorSMN){
                mayorSMN=trabajador.calcularSalarioNeto();
                conElMayorSMN =trabajador.toString();
            }
        }
        // *********************************************************************
        return conElMayorSMN ;
    }   // NO BORRAR: FIN DEL MÉTODO datosTrabajadorConElMayorSMN( )
    
    //
    public double calcularSMNpromedio() {
        if( trabajadores.isEmpty() ) {
            String msj = "Arreglo Vacío: No hay aún trabajadores";
            JOptionPane.showMessageDialog(null, msj, "Arreglo Vacío", 1);
            System.out.println(msj);
            return Double.NaN;
        }
        double suma = 0, prom = 0;
        // *********************************************************************
        for (Trabajador trabajador : trabajadores) {
            suma+= trabajador.calcularSalarioNeto();
        }
        prom=suma/trabajadores.size();
        // *********************************************************************
        return prom;
    }   // calcularSMNpromedio() 
    
    // Algoritmo de ordenamiento burbuja (bubble):
    public void ordenarPorApellidos( ) {
        Trabajador auxi;
        Trabajador  obj1, obj2 ;
        for( int i = 0; i < trabajadores.size( ) - 1; i ++ ) {
            for( int j = 0; j < trabajadores.size( ) - 1 - i; j ++ ) { 
                obj1 = trabajadores.get( j ) ;
                obj2 = trabajadores.get(j + 1) ;
                if( obj1.compareTo( obj2 ) > 0 ) {
                    // Intercambie los objetos de posición:
                    auxi = obj1;
                    trabajadores.set( j, obj2 );
                    trabajadores.set( j + 1, auxi );
                }   // if
            }   // for j
        }   // for i    
    }
    
    // Algoritmo de ordenamiento burbuja (bubble):
    public void ordenarPorNombres_OLD() {
        Trabajador  auxi ;
        String  apeNom1, apeNom2;
        for( int i = 0; i < trabajadores.size() - 1; i ++ ) {
            for( int j = 0; j < trabajadores.size() - 1 - i; j ++ ) { 
                apeNom1  =  trabajadores.get( j ).getApeNom( ) ;
                apeNom2  = trabajadores.get(j + 1).getApeNom( ) ;
                if( apeNom1.compareTo( apeNom2 ) > 0 ) {
                    // Intercambie los objetos de posición:
                    auxi  =  trabajadores.get( j ) ;
                    trabajadores.set(j, trabajadores.get(j + 1) ) ;
                    trabajadores.set( j + 1, auxi );
                }   // if
            }   // for j
        }   // for i
    }
    
    
    
}