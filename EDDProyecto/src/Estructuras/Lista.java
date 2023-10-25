/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;


public class Lista {
    public User primero;
    public int tamaño;
    
    public Lista(){
        primero = null;
        tamaño = 0;
    }
    
    public void insertar_seg (String user){
        User nuevo_usuario = new User(user);
        if (primero == null){
            primero = nuevo_usuario;
 
            tamaño++;
        }else{
            User nodo_auxiliar = primero;
            while (nodo_auxiliar.getSiguiente() != null){
                nodo_auxiliar = nodo_auxiliar.getSiguiente();
            }
            nodo_auxiliar.setSiguiente(nuevo_usuario);
            tamaño++;
        }
    }
    public void eliminar_seguidor( String nombre_usuario){
        User eliminar = buscar_usuario(nombre_usuario);
        if(eliminar == null){
            System.out.println("Nodo no encontrado");
        }else{
           
            User anterior = primero;
            
            while (anterior != null && anterior.getSiguiente() != eliminar ){
                anterior = anterior.getSiguiente();
            }
            if (anterior != null){
              anterior.setSiguiente(eliminar.getSiguiente());

            }
        }     
    }
    public User buscar_usuario(String nombre_usuario){
        User nuevo_usuario = primero;
        while  (nuevo_usuario != null && !nuevo_usuario.getUsuario().equals(nombre_usuario) ){
            nuevo_usuario = nuevo_usuario.getSiguiente();}    
        return nuevo_usuario;
    }
    public void añadirDePrimero(String nombre_usuario){
        User nuevo_usuario = new User(nombre_usuario);
        
        if (primero==null){
            primero = nuevo_usuario;
            tamaño++;}
        else{
            nuevo_usuario.setSiguiente(primero);
            primero = nuevo_usuario;
            tamaño++;
                    }
    }
    

    
    
    public boolean existe(String nombre_usuario){
        User auxiliar = primero;
        boolean encontrado = false;
        while  (auxiliar != null){
            if (auxiliar.getUsuario().equals(nombre_usuario)){
                encontrado = true;
                break;
            }
            auxiliar = auxiliar.getSiguiente();}    
        return encontrado;
    }
    
   
    public Lista invertir(){
        Lista invertida = new Lista();
        User auxiliar = primero;
        while(auxiliar!=null){
            invertida.añadirDePrimero(auxiliar.getUsuario());
            auxiliar=auxiliar.getSiguiente();
        }
        return invertida;
        
    }
    
    public Lista reps(){
        Lista nueva = new Lista();
        User auxiliar = primero;
        while(auxiliar!= null){
            if(nueva.buscar_usuario(auxiliar.getUsuario())== null){
                nueva.insertar_seg(auxiliar.getUsuario());
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return nueva;
    }
    
}
