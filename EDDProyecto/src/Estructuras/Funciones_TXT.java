package Estructuras;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;


public class Funciones_TXT {
    
    public Funciones_TXT(){}
    public void sobrescribirTXT(Grafo grafo,String direccion_txt){
        String datos = "";
        String linea;
        String datos_txt = "";
        String path = direccion_txt;
        File file = new File(path);
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.write("usuarios" + "\n");
            for (int i = 0; i < grafo.usuarios_maximos; i++) {
                try{
                    pw.write(grafo.bdd_users[i].primero.getUsuario() + "\n");

                }catch (Exception e){
                    System.out.println("En " + i + " no hay usuario registrado");
                }
            }
            pw.write("relaciones");
            for (int i = 0; i < grafo.usuarios_maximos; i++) {
                User seguidor = grafo.bdd_users[i].primero;
                while(seguidor.getSiguiente() != null){
                    pw.write("\n" + grafo.bdd_users[i].primero.getUsuario() + ", " + seguidor.getSiguiente().getUsuario());
                    seguidor = seguidor.getSiguiente();
                }
            }
            
            
            pw.close();
        }catch( Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        }
        public String agregar_arroba(String usuario){
            if(!usuario.contains("@")){
                usuario ="@"+usuario; 
            }
            usuario = usuario.replace(" ", "");
            
            return usuario;
        }
    
    public Grafo leerTXT(String direccion_txt){
        Grafo grafo = new Grafo(2);
//        for (int i = 0; i < grafo.usuarios_maximos; i++) {
//            grafo.bdd_users[i] = new Lista();
//        }
        String datos = "";
        String linea;
        String datos_txt = "";
        String path = direccion_txt;
        int modo = 1;
        File file = new File(path);
        String lineas[]; 
        try{
            if (!file.exists()){
                file.createNewFile();
            }else{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while((linea = br.readLine())!= null){
                    if(!linea.isEmpty() && !linea.equals("usuarios")){
                        if(linea.equals("relaciones")){
                            modo =2;
                        }else if (modo == 1){
                            grafo.nuevo_user(linea);
                        }else if (modo ==2){
                            linea = linea.replace(",", "");
                            lineas = linea.split(" ");
                            grafo.agregar_seguidor(lineas[0], lineas[1]);
                        }                      
                    }
                }
                if (!"".equals(datos_txt)){
                    datos = datos_txt;
                    

                }
                br.close();
                JOptionPane.showMessageDialog(null, "Lectura Exitosa");
            }
        }catch( Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }

        return grafo;
    }
        
}
