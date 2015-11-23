package PackageBD;

public class FacadeLogin {
	private CourtierBDUtilisateur cBDUtilisateur;
	
	public Utilisateur authentification(String username, String password){
		cBDUtilisateur = new CourtierBDUtilisateur();
		Utilisateur user = cBDUtilisateur.getUtilisateur(username,password);
		return user;
	}
}
