
import java.net.*;
import java.io.*;
public class Server {
	public static void main(String[] args) throws IOException {
		
		
 
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1998);
		} catch (IOException e) {
			System.err.println("I/O exception: " + e.getMessage());
			System.exit(1);
		}
		System.out.println("Sunucu baslatildi. Baglanti bekleniyor...");
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept(); // ba�lant� gelene kadar burada bekler
							      
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
 
		System.out.println(clientSocket.getLocalAddress() + " baglandi.");
 
		// input stream ve output stream yarat�l�yor...
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
		String inputLine, outputLine="";
		//System.out.println("�stemciden girdi bekleniyor...");
		while ((inputLine = in.readLine()) != null) { // istemciden gelen string okunuyor...
							      

			
			Decryption dec=new Decryption();
			outputLine=dec.decrypt(inputLine);
			out.println("(Encrypted message: "+inputLine+") ==> ("+"Decrypted message: "+outputLine+")");
			
		 
			if (outputLine.equals("q")) // istemciden "q" gelmi�se d�ng�den ��k.
				break;
			System.out.println("***SUMMARY***");
			System.out.println("istemciden gelen :" + inputLine+" sunucudan gelen : "+outputLine);
		}
		
		// stream ve socketleri kapat.
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
		System.out.println(clientSocket.getLocalSocketAddress()+ " baglantisi kesildi.");
		
	}
}