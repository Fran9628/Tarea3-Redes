import java.io.*;
import java.net.*;

public class Cliente {

  public static void main(final String[] args) throws IOException{

    Socket clienteSocket = null;
    DataOutputStream mensajeSalidaDelCliente = null;
    DataInputStream mensajeEntradaAlCliente = null;
    Integer servicioPuerto = 9876;
    String servidorIP = "localhost";
    String entradaRemota="";
    String mensaje_menu="";
    String mensajeUsuario="";
    String valor_peso="";
    String tipo_medida="";
    String convertir="";

    /* el write es el que manda las cosas al servidor*/

    try {
      System.out.println("Conectando ...");
      clienteSocket = new Socket(servidorIP, servicioPuerto);
      System.out.println("Conectando al servidor con IP: " + servidorIP + ", al puerto: " + servicioPuerto);
    } catch (final UnknownHostException e) {
      System.err.println("No conosco el servidor con IP: "+servidorIP);
      System.exit(1);
    } catch (final IOException e) {
      System.err.println("No puedo conectarme al servidor con IP:  "+servidorIP);
      System.exit(1);
    }

    final BufferedReader entradaEstandar = new BufferedReader(new InputStreamReader(System.in));
    mensajeSalidaDelCliente = new DataOutputStream(clienteSocket.getOutputStream());
    mensajeEntradaAlCliente = new DataInputStream(clienteSocket.getInputStream());

    entradaRemota = mensajeEntradaAlCliente.readUTF();
    System.out.println("Mensaje del servidor --> " +entradaRemota);

    while (true) {
      System.out.println("\t\tMenu\n");
      System.out.println("1) Ingresar peso\n2) Ver medidas de peso\n3) Salir");
      System.out.print("\t\t(Seleccione el numero de su opcion)\nOpcion: ");
      mensaje_menu = entradaEstandar.readLine(); // lee lo que el usuario ingrese por teclado

      if(mensaje_menu != null && mensaje_menu.equals("1")){
        System.out.println("\nSelecciono Ingresar peso");
        System.out.println("-Ejemplo 1: 35 Gramos\n-Ejemplo 2: 60 g\n-Ejemplo 3: 1 gramo\n\n");
        System.out.print("Ingresar peso: ");
        mensajeUsuario = entradaEstandar.readLine(); // lee lo que el usuario ingrese por teclado -> ejemplo 35 gramos
        final String[] Split = mensajeUsuario.split(" "); // separo de la cadena el peso del tipo de medida
        valor_peso = Split[0]; // aqui ira el valor del peso (numero) 35 
        tipo_medida = Split[1]; // aqui ira el tipo de medida (kilogramos, kilogramo, g)
        // System.out.println("valor peso: "+valor_peso+" medida: "+tipo_medida); // debugeo para saber si el split funciono

        if(tipo_medida != null && tipo_medida.toLowerCase().equals("kilogramo") || tipo_medida.toLowerCase().equals("kilogramos") || tipo_medida.toLowerCase().equals("kg")){
          System.out.println("\nPasar de Kilogramos a: ");
          System.out.println("1) Hectogramo\n2) Decagramo\n3) Gramo\n4) Decigramo\n5) Centigramo\n6) Miligramo\n");
          System.out.print("Seleccione una opcion: ");
          convertir= entradaEstandar.readLine(); // leo a que deseo convertir

          if(convertir != null && convertir.equals("1")){
            convertir="Hectogramo";
          }else if(convertir != null && convertir.equals("2")){
            convertir="Decagramo";
          }else if(convertir != null && convertir.equals("3")){
            convertir="Gramo";
          }else if(convertir != null && convertir.equals("4")){
            convertir="Decigramo";
          }else if(convertir != null && convertir.equals("5")){
            convertir="Centigramo";
          }else if(convertir != null && convertir.equals("6")){
            convertir="Miligramo";
          }else{
            System.out.println("ERROR ENTRADA NO VALIDA");
          }

          mensajeSalidaDelCliente.writeUTF("Pasar de "+valor_peso+" "+tipo_medida.toLowerCase()+" a "+convertir);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.print("\nMensaje del servidor --> " +entradaRemota);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.println("Mensaje del servidor --> " +entradaRemota);

        }else if(tipo_medida != null && tipo_medida.toLowerCase().equals("hectogramo") || tipo_medida.toLowerCase().equals("hectogramos") || tipo_medida.toLowerCase().equals("hg")){
          System.out.println("\nPasar de Hectogramo a: ");
          System.out.println("1) Kilogramo\n2) Decagramo\n3) Gramo\n4) Decigramo\n5) Centigramo\n6) Miligramo\n");
          System.out.print("Seleccione una opcion: ");
          convertir= entradaEstandar.readLine(); // leo a que deseo convertir

          if(convertir != null && convertir.equals("1")){
            convertir="Kilogramo";
          }else if(convertir != null && convertir.equals("2")){
            convertir="Decagramo";
          }else if(convertir != null && convertir.equals("3")){
            convertir="Gramo";
          }else if(convertir != null && convertir.equals("4")){
            convertir="Decigramo";
          }else if(convertir != null && convertir.equals("5")){
            convertir="Centigramo";
          }else if(convertir != null && convertir.equals("6")){
            convertir="Miligramo";
          }else{
            System.out.println("ERROR ENTRADA NO VALIDA");
          }

          mensajeSalidaDelCliente.writeUTF("Pasar de "+valor_peso+" "+tipo_medida.toLowerCase()+" a "+convertir);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.print("\nMensaje del servidor --> " +entradaRemota);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.println("Mensaje del servidor --> " +entradaRemota);

        }else  if(tipo_medida.toLowerCase().equals("decagramo") || tipo_medida.toLowerCase().equals("decagramos") || tipo_medida.toLowerCase().equals("dag")){
          System.out.println("\nPasar de Decagramo a: ");
          System.out.println("1) Kilogramo\n2) Hectogramo\n3) Gramo\n4) Decigramo\n5) Centigramo\n6) Miligramo\n");
          System.out.print("Seleccione una opcion: ");
          convertir= entradaEstandar.readLine(); // leo a que deseo convertir

          if(convertir != null && convertir.equals("1")){
            convertir="Kilogramo";
          }else if(convertir != null && convertir.equals("2")){
            convertir="Hectogramo";
          }else if(convertir != null && convertir.equals("3")){
            convertir="Gramo";
          }else if(convertir != null && convertir.equals("4")){
            convertir="Decigramo";
          }else if(convertir != null && convertir.equals("5")){
            convertir="Centigramo";
          }else if(convertir != null && convertir.equals("6")){
            convertir="Miligramo";
          }else{
            System.out.println("ERROR ENTRADA NO VALIDA");
          }

          mensajeSalidaDelCliente.writeUTF("Pasar de "+valor_peso+" "+tipo_medida.toLowerCase()+" a "+convertir);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.print("\nMensaje del servidor --> " +entradaRemota);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.println("Mensaje del servidor --> " +entradaRemota);

        }else  if(tipo_medida.toLowerCase().equals("gramo") || tipo_medida.toLowerCase().equals("gramos") || tipo_medida.toLowerCase().equals("g")){
          System.out.println("\nPasar de Gramo a: ");
          System.out.println("1) Kilogramo\n2) Hectogramo\n3) Decagramo\n4) Decigramo\n5) Centigramo\n6) Miligramo\n");
          System.out.print("Seleccione una opcion: ");
          convertir= entradaEstandar.readLine(); // leo a que deseo convertir

          if(convertir != null && convertir.equals("1")){
            convertir="Kilogramo";
          }else if(convertir != null && convertir.equals("2")){
            convertir="Hectogramo";
          }else if(convertir != null && convertir.equals("3")){
            convertir="Decagramo";
          }else if(convertir != null && convertir.equals("4")){
            convertir="Decigramo";
          }else if(convertir != null && convertir.equals("5")){
            convertir="Centigramo";
          }else if(convertir != null && convertir.equals("6")){
            convertir="Miligramo";
          }else{
            System.out.println("ERROR ENTRADA NO VALIDA");
          }

          mensajeSalidaDelCliente.writeUTF("Pasar de "+valor_peso+" "+tipo_medida.toLowerCase()+" a "+convertir);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.print("\nMensaje del servidor --> " +entradaRemota);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.println("Mensaje del servidor --> " +entradaRemota);

        }else  if(tipo_medida.toLowerCase().equals("decigramo") || tipo_medida.toLowerCase().equals("decigramos") || tipo_medida.toLowerCase().equals("dg")){
          System.out.println("\nPasar de Decigramo a: ");
          System.out.println("1) Kilogramo\n2) Hectogramo\n3) Decagramo\n4) Gramo\n5) Centigramo\n6) Miligramo\n");
          System.out.print("Seleccione una opcion: ");
          convertir= entradaEstandar.readLine(); // leo a que deseo convertir

          if(convertir != null && convertir.equals("1")){
            convertir="Kilogramo";
          }else if(convertir != null && convertir.equals("2")){
            convertir="Hectogramo";
          }else if(convertir != null && convertir.equals("3")){
            convertir="Decagramo";
          }else if(convertir != null && convertir.equals("4")){
            convertir="Gramo";
          }else if(convertir != null && convertir.equals("5")){
            convertir="Centigramo";
          }else if(convertir != null && convertir.equals("6")){
            convertir="Miligramo";
          }else{
            System.out.println("ERROR ENTRADA NO VALIDA");
          }

          mensajeSalidaDelCliente.writeUTF("Pasar de "+valor_peso+" "+tipo_medida.toLowerCase()+" a "+convertir);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.print("\nMensaje del servidor --> " +entradaRemota);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.println("Mensaje del servidor --> " +entradaRemota);

        }else  if(tipo_medida.toLowerCase().equals("centigramo") || tipo_medida.toLowerCase().equals("centigramos") || tipo_medida.toLowerCase().equals("cg")){
          System.out.println("\nPasar de Centigramo a: ");
          System.out.println("1) Kilogramo\n2) Hectogramo\n3) Decagramo\n4) Gramo\n5) Decigramo\n6) Miligramo\n");
          System.out.print("Seleccione una opcion: ");
          convertir= entradaEstandar.readLine(); // leo a que deseo convertir

          if(convertir != null && convertir.equals("1")){
            convertir="Kilogramo";
          }else if(convertir != null && convertir.equals("2")){
            convertir="Hectogramo";
          }else if(convertir != null && convertir.equals("3")){
            convertir="Decagramo";
          }else if(convertir != null && convertir.equals("4")){
            convertir="Gramo";
          }else if(convertir != null && convertir.equals("5")){
            convertir="Decigramo";
          }else if(convertir != null && convertir.equals("6")){
            convertir="Miligramo";
          }else{
            System.out.println("ERROR ENTRADA NO VALIDA");
          }

          mensajeSalidaDelCliente.writeUTF("Pasar de "+valor_peso+" "+tipo_medida.toLowerCase()+" a "+convertir);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.print("\nMensaje del servidor --> " +entradaRemota);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.println("Mensaje del servidor --> " +entradaRemota);

        }else  if(tipo_medida.toLowerCase().equals("miligramo") || tipo_medida.toLowerCase().equals("miligramos") || tipo_medida.toLowerCase().equals("mg")){
          System.out.println("\nPasar de Miligramo a: ");
          System.out.println("1) Kilogramo\n2) Hectogramo\n3) Decagramo\n4) Gramo\n5) Decigramo\n6) Centigramo\n");
          System.out.print("Seleccione una opcion: ");
          convertir= entradaEstandar.readLine(); // leo a que deseo convertir

          if(convertir != null && convertir.equals("1")){
            convertir="Kilogramo";
          }else if(convertir != null && convertir.equals("2")){
            convertir="Hectogramo";
          }else if(convertir != null && convertir.equals("3")){
            convertir="Decagramo";
          }else if(convertir != null && convertir.equals("4")){
            convertir="Gramo";
          }else if(convertir != null && convertir.equals("5")){
            convertir="Decigramo";
          }else if(convertir != null && convertir.equals("6")){
            convertir="Centigramo";
          }else{
            System.out.println("ERROR ENTRADA NO VALIDA");
          }

          mensajeSalidaDelCliente.writeUTF("Pasar de "+valor_peso+" "+tipo_medida.toLowerCase()+" a "+convertir);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.print("\nMensaje del servidor --> " +entradaRemota);
          entradaRemota=mensajeEntradaAlCliente.readUTF();
          System.out.println("Mensaje del servidor --> " +entradaRemota);

        }else{
          System.out.println("ERROR OPCION NO VALIDA\n");
        }

      }else if(mensaje_menu != null && mensaje_menu.equals("2")){
        System.out.println("\t\tMedidas");
        System.out.println("- Kilogramo --> kg\n- Hectogramo --> hg\n- Decagramo --> dag\n- Gramo --> g\n- Decigramo --> dg\n- Centigramo --> cg\n- Miligramo --> mg\n\n");

      }else if(mensaje_menu != null && mensaje_menu.equals("3")){
        break;
      }else{
        System.out.println("ERROR OPCION NO VALIDA\n");
      }

    }

    System.out.println("Desconectandose del servidor ...");
    mensajeSalidaDelCliente.close();
    mensajeEntradaAlCliente.close();
    clienteSocket.close();
    System.out.println("Desconectado del servidor.");
  }
}
