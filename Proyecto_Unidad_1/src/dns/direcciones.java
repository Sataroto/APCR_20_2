/*
Nombre: Gerardo Ayala Juarez
Tema del programa:DNS simple con Sockets de flujo
Descripcion: Clase que contiene un dominio con sus distinas ip;
Fecha:30 de enero del 2020

 */
package dns;
public class direcciones{
    private String Dominio;
    private String[] repertorio;
    public direcciones(String Dominio){
        this.Dominio=Dominio;
    }
    public void setRepertorio(String[] repertorio){
        this.repertorio=repertorio;
    }
    public String getDominio(String muestra){
        for(int i =0 ; i < repertorio.length;i++){
            if(repertorio[i].equals(muestra)){
                return this.Dominio;
            }
            
        }
        return "-1";
    }
    public String getNombre(){
        return this.Dominio;
    }
    public String getOpciones(){
        String aux="";
        for(int i=0;i<this.repertorio.length;i++){
            aux=aux+"\n"+this.repertorio[i];
        }
        return aux;
    }
    public String[] getRepertorio(String muestra){
        String[] aux = {""};
        if(this.Dominio.equals(muestra)){
            aux = this.repertorio;
        }
        return aux;
    }
}