package ar.edu.utn.singleton;

public class GestorArchivosConfiguracionSingleton {
	private static GestorArchivosConfiguracionSingleton gestorArchivosConfiguracion = null;

	private int puertoServer;
	private String ipServer;
	private String msj;
	
	
	private GestorArchivosConfiguracionSingleton() {
		// TODO Auto-generated constructor stub
		setMsj("Se levantaron correctamente los archivos de configuracion, ahora puede acceder a los parametros globales");
		setPuertoServer(8080);
		setIpServer("127.0.0.1");
		
	}
	
	public static GestorArchivosConfiguracionSingleton  getInstance(){
		if (gestorArchivosConfiguracion == null) {
			gestorArchivosConfiguracion = new GestorArchivosConfiguracionSingleton();
		}
		return gestorArchivosConfiguracion;
	}



	public static GestorArchivosConfiguracionSingleton getGestorArchivosConfiguracion() {
		return gestorArchivosConfiguracion;
	}

	public static void setGestorArchivosConfiguracion(GestorArchivosConfiguracionSingleton gestorArchivosConfiguracion) {
		GestorArchivosConfiguracionSingleton.gestorArchivosConfiguracion = gestorArchivosConfiguracion;
	}

	public int getPuertoServer() {
		return puertoServer;
	}

	public void setPuertoServer(int puertoServer) {
		this.puertoServer = puertoServer;
	}

	public String getIpServer() {
		return ipServer;
	}

	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}
	
	
	
}
