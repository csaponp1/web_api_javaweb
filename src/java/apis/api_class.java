/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapón Pérez
 */
public class api_class {
    private String get(){
        String salida="";
        try{
            URL url = new URL("https://localhost:5001/api/clientes");
            HttpURLConnection c_api= (HttpURLConnection) url.openConnection();
            c_api.setRequestMethod("GET");
            c_api.setRequestProperty("Accept","application/json");
                
                if(c_api.getResponseCode()==200){
                    InputStreamReader entrada = new InputStreamReader(c_api.getInputStream());
                    BufferedReader lectura = new BufferedReader(entrada);
                    salida = lectura.readLine();

                }
                else{
                    System.out.println("no se puede conectar a la api:"+c_api.getResponseCode());
                }
            
            c_api.disconnect();
            }
        catch(IOException ex){
            System.out.println("xxxx Error xxxx"+ex.getMessage());
            }
        return salida;
    }
    
    public DefaultTableModel leer(){
      DefaultTableModel tabla = new DefaultTableModel();
      try{
          String encabezado[] = {"id","nit","nombres","apellidos","direccion","telefono","nacimiento"};
          tabla.setColumnIdentifiers(encabezado);
          String[] datos = new String[7];
          JSONArray arreglo = new JSONArray(get());
          
          for(int i = 0 ; i < arreglo.length(); i++){
              JSONObject atributo = arreglo.getJSONObject(i);
              datos[0] = String.valueOf(atributo.getInt("id_cliente"));
              datos[1]  =   atributo.getString("nit");
              datos[2]  =   atributo.getString("nombres");
              datos[3]  =   atributo.getString("apellidos");
              datos[4]  =   atributo.getString("direccion");
              datos[5]  =   atributo.getString("telefono");
              datos[6]  =   atributo.getString("fecha_nacimiento");
              tabla.addRow(datos);
          }
      }catch(JSONException ex){

           System.out.println("xxxxXerrorXxxxxx" + ex.getMessage());
      }

      return tabla;
  }
}
