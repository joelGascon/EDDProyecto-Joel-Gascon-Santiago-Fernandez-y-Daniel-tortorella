/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;
import java.util.HashSet;
import java.util.Set;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import java.util.Random;

public class Grafo {
    public int usuarios_maximos;
    public Lista[] bdd_users;
    
    
    public Grafo(int max_u){
        usuarios_maximos = max_u;
        bdd_users = new Lista[max_u];
      
    }
    
    public void nuevo_user(String user){
        boolean espacio_vacio = false;
        /*BUSCAMOS UN ESPACIO VACIO EN EL ARREGLO DE USUARIOS*/
        for (int i = 0; i < usuarios_maximos; i++) {
            if (bdd_users[i] == null){ /*AÑADIMOS EL NUEVO USUARIO EN LA LISTA DE LA POSICION VACIA*/
                bdd_users[i] = new Lista();
                bdd_users[i].añadirDePrimero(user);
                espacio_vacio = true;
                break;
            }
/*SI NO HAY POSICION VACIA, AUMENTAMOS EL GRAFO*/
        }
        if (!espacio_vacio){
            añadir_usuario(user);
        }}
    
    
        public void añadir_usuario(String usuario){
            /*CREAMOS UN NUEVO GRAFO CON UN ESPACIO MÁS PARA EL NUEVO USER*/
            Lista nueva[] = new Lista[usuarios_maximos +1];
           /*RECORREMOS EL GRAFO VIEJO E INSERTAMOS LOS USERS EN EL NUEVO*/

            for (int i = 0; i < usuarios_maximos +1; i++) {
                if (i != usuarios_maximos){
                    nueva[i] = bdd_users[i] ;
                }else{
                    /*AGREGAMOS EL NUEVO USER A LA ULTIMA POSISICION DEL ARRAY DE USUARIOS*/
                    nueva[i]= new Lista();
                    nueva[i].añadirDePrimero(usuario);
                }
                
            }
            /*ACTUALIZAMOS EL ARRAY DEL GRAFO CON LA INFO DEL NUEVO*/
            this.bdd_users = nueva;
            usuarios_maximos++;
        }
            
        
    
    
    public void agregar_seguidor(String nuevo_seguidor, String user){
        boolean seguidor_existe = false;
        boolean user_existe = false;
       /*RECORREMOS LOS USUARIOS PARA VERIFICAR QUE EXISTAN*/
        int user_id = 0;
        for (int i = 0; i < usuarios_maximos; i++) {
            if (seguidor_existe && user_existe){
                break;}
            if (bdd_users[i].primero.getUsuario().equals(user)){
                seguidor_existe = true;
                user_id = i;
                
            }else if (bdd_users[i].primero.getUsuario().equals(nuevo_seguidor)){
                user_existe = true;

            }
            
        }
        /*SI AMBOS EXISTEN, AGREGAMOS EL NUEVO SEGUIDOR AL ARRAY DEL USUARIO*/
        if (seguidor_existe && user_existe){
            bdd_users[user_id].insertar_seg(nuevo_seguidor);
        }
    }
    
    
    public void borrar_user(String user){
        /*CREAMOS UN NUEVO GRAFO CON UN ESPACIO MENOS*/
        Lista[] nueva = new Lista[usuarios_maximos-1];
        int indice_valido =0;
        /*RECORREMOS EL GRAFO VIEJO E INSERTAMOS LOS USERS EN EL NUEVO*/
         for (int i = 0; i < usuarios_maximos ; i++) {
             /*VERIFICAMOS QUE EL USUARIO QUE VAYAMOS A INSERTAR NO SEA EL QUE QUEREMOS ELIMINAR*/
                if (!bdd_users[i].primero.getUsuario().equals(user)){
                    nueva[indice_valido] = bdd_users[i];
                    nueva[indice_valido].eliminar_seguidor(user);
                    /*BUSCAMOS Y ELIMINAMOS EL USER EN LA LISTA DE ADYACENCIA DE LOS DEMÁS USUARIOS*/
                    indice_valido++;
                }
                
            }/*ACTUALIZAMOS EL ARRAY DEL GRAFO CON LA INFO DEL NUEVO*/
            bdd_users = nueva;
            usuarios_maximos--;
    
    }
    

    
   public void generarGrafo() {
       /*AQUI UTILIZAMOS LA LIBRERIA DE GRAPHSTREAM PARA GENERAR NUESTRO GRAFO*/
            Graph grafo = new SingleGraph("Grafo");
            Set nodos_insertados = new HashSet<>();

            /*SE CONFIGURA EL ESTILO DE LOS NODOS*/
            grafo.setAttribute("ui.stylesheet", "node { shape: circle; fill-color: #CCBF49; text-color: #000000; size: 30px; } edge { size: 2px; shape: line; fill-color: #D3D3D3; }");
            
            /*AQUI RECORREMOS NUESTRO GRAFO PARA INSERTAR CADA NODO AL GRAFO DE LA LIBRERIA*/ 
            for (int i = 0; i < usuarios_maximos; i++) {
              
                User seguidor = bdd_users[i].primero.getSiguiente();
                User user = bdd_users[i].primero;

                while (seguidor != null && user != null) {
                    /*VERIFICAMOS QUE EL SEGUIDOR NO ESTÁ INSERTADO AÚN*/
                    if (!nodos_insertados.contains(seguidor.getUsuario())) {
                        Node seg = grafo.addNode(String.valueOf(seguidor.getUsuario()));
                        seg.setAttribute("ui.label", String.valueOf(seguidor.getUsuario()));
                        nodos_insertados.add(seguidor.getUsuario());

                    }/*VERIFICAMOS QUE EL USUARIO TAMPOCO ESTE AGREGADO*/
                    if (!nodos_insertados.contains(user.getUsuario())) {
                        Node us = grafo.addNode(String.valueOf(user.getUsuario()));
                        us.setAttribute("ui.label", String.valueOf(user.getUsuario()));
                        nodos_insertados.add(user.getUsuario());
 
                    }
                    seguidor = seguidor.getSiguiente();
                }
            }
            /*RECORREMOS EL GRAFO CON EL ALGORITMO DE KOSARAJU PARA ENCONTRAR LOS NODOS FUERTEMENTE ENLAZADOS*/
            Lista[] kosaraju = algoritmo_kosaraju();
            /*GENERAMOS UN COLOR ALEATORIO PARA LAS ARISTAS*/
            String[] colores_aristas = new String[usuarios_maximos];
            for (int i = 0; i < usuarios_maximos; i++) {
                Random r = new Random();
                int a = r.nextInt(255);
                int b = r.nextInt(255);
                int d = r.nextInt(255);
                colores_aristas[i] = "fill-color: rgb(" + String.valueOf(a)+ "," + String.valueOf(b) + "," + String.valueOf(d) + ");";

            }
            /*AHORA AGREGAMOS LAS ARISTAS AL GRAFO*/
            for (int i = 0; i < usuarios_maximos; i++) {
                
                User user = bdd_users[i].primero;
                User seguidor = bdd_users[i].primero.getSiguiente();
                
                while (seguidor != null) {
                    /*BUSCAMOS AMBOS NODOS EN EL GRAFO*/
                    org.graphstream.graph.Node nodo1 = grafo.getNode(String.valueOf(user.getUsuario()));
                    org.graphstream.graph.Node nodo2 = grafo.getNode(String.valueOf(seguidor.getUsuario()));

                    if (nodo1 == null || nodo2 == null) {
                        System.out.println("Uno de los nodos no existe");
                    } else {
                        /*VERIFICAMOS QUE AMBOS NODOS ESTEN FUERTEMENTE CONECTADOS Y LE ASIGNAMOS EL COLOR*/

                        org.graphstream.graph.Edge arista = grafo.addEdge(String.valueOf(user.getUsuario()) + "-" + String.valueOf(seguidor.getUsuario()), nodo1, nodo2, true);
                        for (int j = 0; j < usuarios_maximos; j++) {
                            if(kosaraju[j].existe(seguidor.getUsuario()) && kosaraju[j].existe(user.getUsuario())){
                                arista.setAttribute("ui.style", colores_aristas[j]);
                            }
                        }       
                    }
                    seguidor = seguidor.getSiguiente();
                }
            }

            /*GENERAMOS EL GRAFO*/

    System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing");
            Viewer display = grafo.display();
    }
   
   public Lista[] algoritmo_kosaraju(){
     
        /*REALIZAMOS EL DFS*/
        Lista visitados = new Lista();
        Lista post_orden = new Lista();
        for (int i = 0; i < usuarios_maximos; i++) {
            User actual = bdd_users[i].primero;
            /*SI NO LO HEMOS VISITADO, HACEMOS EL DFS CON EL NODO*/
            if (!visitados.existe(actual.getUsuario())) {
                algoritmo_dfs(actual, visitados, post_orden);
            }
        }
        /*INVERTIMOS LA LISTA PARA TENER EL ORDEN CORRECTO*/
        Lista pst_ord = post_orden.invertir();
      

        /*REINICIAMOS LA LISTA DE VISITADOS*/
        visitados = new Lista();
        Lista nodos[] = new Lista[pst_ord.tamaño];
        /*INICIALIZAMOS CADA LISTA*/
        for (int i = 0; i < pst_ord.tamaño; i++) {
           nodos[i] = new Lista();
       }
        
        User aux = pst_ord.primero;
        for (int i = 0; i < pst_ord.tamaño; i++) {
            /*SI NO LO HEMOS VISITADO, LO AGREGAMOS A LA VISITADOS*/
            if (!visitados.existe(aux.getUsuario())) {
                Lista noditos = new Lista();
                algoritmo_dfs(aux, visitados, noditos);
                nodos[i] = noditos;
            }
            
            aux = aux.getSiguiente();
        }
        
        for (int i = 0; i < usuarios_maximos / 2; i++) {
            Lista aux1 = nodos[i];
            nodos[i] = nodos[usuarios_maximos - 1 - i];
            nodos[usuarios_maximos - 1 - i] = aux1;
        }
        /*ELIMINAMOS LOS REPETIDOS*/
        for (int a = 0; a < usuarios_maximos; a++) {
            nodos[a]=nodos[a].reps();
       }
        
        return nodos;
    
   }
   
   public void algoritmo_dfs(User nodo, Lista nodos_visitados, Lista lista_postorden) {
        nodos_visitados.insertar_seg(nodo.getUsuario());

        User vert = nodo.getSiguiente();
        while(vert!= null&& !nodos_visitados.existe(vert.getUsuario())){
            if (!nodos_visitados.existe(vert.getUsuario())) {
                User aux = buscar_nodo(vert.getUsuario());
                algoritmo_dfs(aux, nodos_visitados, lista_postorden);
            }
            vert = vert.getSiguiente();

        }
        if(!lista_postorden.existe(nodo.getUsuario())){
            User au = buscar_nodo(nodo.getUsuario()).getSiguiente();
            while(au!=null){
                
                if (!nodos_visitados.existe(au.getUsuario())) {
                    algoritmo_dfs(au, nodos_visitados, lista_postorden);
                }
                au = au.getSiguiente();
            }
            
            lista_postorden.insertar_seg(nodo.getUsuario());
        }
        
        lista_postorden.insertar_seg(nodo.getUsuario());
   }
        
   public User buscar_nodo(String usuario){
            for (int i = 0; i < usuarios_maximos; i++) { 
                if (bdd_users[i].primero.getUsuario().equals(usuario)){
                    return bdd_users[i].primero;
                }
            }
            return null;
        }   
   
    public String imprimir_usuarios(){
        String grafo = "";
        for (int i = 0; i < usuarios_maximos; i++) {
            if (bdd_users[i]!= null){
            grafo += (this.bdd_users[i].primero.getUsuario()) + "\n";   
        }}
        return grafo;
    }
    

}
    
    
