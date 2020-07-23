import java.net.*;
import java.io.*;


public class Servidor {

	static ServerSocket servidorSocket = null;

	public static void main(String[] args) throws IOException {

	
		Socket clienteSocket=null;
		DataOutputStream mensajeSalidaDelServidor = null;
		DataInputStream mensajeEntradaAlServidor = null;
		Integer servicioPuerto = 9876;
		String entradaRemota="";
		boolean escuchando = true;
		float valor_peso;
		String tipo_medida="";
		String convertir= "";

		try {
			System.out.println("Creando el servicio en el puerto "+ servicioPuerto+" ...");
			servidorSocket = new ServerSocket(servicioPuerto);
			System.out.println("Servicio en el puerto "+ servicioPuerto+" creado exitosamente ...");
		} catch (IOException e) {
			System.err.println("No se puede utilizar el puerto: "+servicioPuerto);
			System.exit(-1);
		}

		System.out.println("Servidor listo para aceptar requerimiento de clientes ....");


		Runtime.getRuntime().addShutdownHook(new Thread() {
		        public void run() {
							System.out.println("Cerrando servicio ....");
							try{
								servidorSocket.close();
								System.out.println("Servicio cerrado.");
							} catch (IOException e) {
								e.printStackTrace();
							}
		        }
		    });

				while (escuchando) {
					System.out.println("Escuchando ....");
					clienteSocket=servidorSocket.accept();
					System.out.println("se conecto un cliente desde: "+ clienteSocket.getInetAddress());
					try {
						mensajeSalidaDelServidor = new DataOutputStream(clienteSocket.getOutputStream());
						mensajeEntradaAlServidor = new DataInputStream(clienteSocket.getInputStream());

						mensajeSalidaDelServidor.writeUTF("El Servidor esta listo para recibir mensajes...");
						System.out.println("Esperando mensajes enviados desde el Cliente ...");

						while ((entradaRemota = mensajeEntradaAlServidor.readUTF()) != null) {
							// aqui hay que hacer el desarrollo para pasar la info de cliente a la conversion y enviarle el resultado

							System.out.println("Llego desde el cliente el mensaje:\n"+entradaRemota); // aqui imprimiriamos ej : 35 gramos
							mensajeSalidaDelServidor.writeUTF("mensaje <<" + entradaRemota + ">> recibido.");
							/* haces la conversion que serian muchos if y luego imprimes el resultado
							 en mensajeSalidaDelServidor.writeUTF("mensaje <<" + entradaRemota + ">> recibido.");*/
								String[] Split = entradaRemota.split(" ");
								valor_peso= Float.parseFloat(Split[2]);
								tipo_medida= Split[3];
								convertir = Split[5];

								//Si quiere convertir x gramos a alguna unidad de medida, ingresará a este if y hará la conversion correspondiente
								if(tipo_medida.equals("gramo") || tipo_medida.equals("gramos") || tipo_medida.equals("g")) {
									if(convertir.equals("Kilogramo")){
										System.out.println(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/1000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Hectogramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decagramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Centigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Miligramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*1000)+" "+convertir+"\n");
									}
								}
								//Si se quiere convertir de x kilogramos a alguna unidad de medida, ingresará a este if y hará la conversión correspondiente
								else if(tipo_medida.equals("kilogramo") || tipo_medida.equals("kilogramos") || tipo_medida.equals("kg")){
									if(convertir.equals("Gramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*1000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Hectogramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decagramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Centigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*100000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*100000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Miligramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*1000000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*1000000)+" "+convertir+"\n");
									}
								}
								//Si quiero convertir de x hectogramo a alguna unidad de medida, ingresará a este if y hará la conversion correspondiente
								else if(tipo_medida.equals("hectogramo") || tipo_medida.equals("hectogramos") || tipo_medida.equals("hg")){
									if(convertir.equals("Kilogramo")){
										System.out.println(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Gramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decagramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*1000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Centigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Miligramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*100000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*100000)+" "+convertir+"\n");
									}
								}
								//Si quiero convertir de x decagramo a alguna unidad de medida, ingresará a este if y hará la conversion correspondiente
								else if(tipo_medida.equals("decagramo") || tipo_medida.equals("decagramos") || tipo_medida.equals("dag")){
									if(convertir.equals("Kilogramo")){
										System.out.println(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Gramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Hectogramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Centigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*1000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Miligramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10000)+" "+convertir+"\n");
									}
								}
								//Si quiero convertir de x decigramo a alguna unidad de medida, ingresará a este if y hará la conversion correspondiente
								else if(tipo_medida.equals("decigramo") || tipo_medida.equals("decigramos") || tipo_medida.equals("dg")){
									if(convertir.equals("Kilogramo")){
										System.out.println(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Gramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Hectogramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/1000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decagramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Centigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Miligramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*100)+" "+convertir+"\n");
									}
								}
								//Si quiero convertir de x centigramo a alguna unidad de medida, ingresará a este if y hará la conversion correspondiente
								else if (tipo_medida.equals("centigramo") || tipo_medida.equals("centigramos") || tipo_medida.equals("cg")){
									if(convertir.equals("Kilogramo")){
										System.out.println(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/100000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/100000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Gramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Hectogramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decagramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/1000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10)+" "+convertir+"\n");
									}
									else if(convertir.equals("Miligramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso*10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso*10)+" "+convertir+"\n");
									}
								}
								//Si quiero convertir de x miligramo a alguna unidad de medida, ingresará a este if y hará la conversion correspondiente
								else if (tipo_medida.equals("miligramo") || tipo_medida.equals("miligramos") || tipo_medida.equals("mg")){
									if(convertir.equals("Kilogramo")){
										System.out.println(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/1000000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/1000000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Gramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/1000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/1000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Hectogramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/100000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/100000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decagramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10000)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10000)+" "+convertir+"\n");
									}
									else if(convertir.equals("Decigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/100)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/100)+" "+convertir+"\n");
									}
									else if(convertir.equals("Centigramo")){
										System.out.print(""+valor_peso+" "+tipo_medida+" = "+(valor_peso/10)+" "+convertir);
										mensajeSalidaDelServidor.writeUTF(" "+valor_peso+" "+tipo_medida+" son "+(valor_peso/10)+" "+convertir+"\n");
									}
								}


						}
					} catch (IOException e) {
						System.out.println("Cliente " +clienteSocket.getInetAddress()+ " se desconecto.");
						mensajeSalidaDelServidor.close();
						mensajeEntradaAlServidor.close();
						clienteSocket.close();
						escuchando = false;
					}
				}
	}
}
