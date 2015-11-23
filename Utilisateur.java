package PackageBD;
		//Classe un peu bidon, juste parceque j'aimais pas avoir une erreur vu que ca existait pas.
public class Utilisateur {
	private String username;
	private String password;
	
	Utilisateur(String usr,String pwd){
		setUsername(usr);
		setPassword(pwd);
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public void setUsername(String usr){
		username = usr;
	}
	public void setPassword(String pwd){
		password = pwd;
	}
}
