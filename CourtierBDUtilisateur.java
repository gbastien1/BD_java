package PackageBD;

import java.util.List;

public class CourtierBDUtilisateur {

		public Utilisateur getUtilisateur(String username, String motDePasse){
			try{
				//Erreur va partir une fois qu'on implemente Hibernate.
				@SuppressWarnings("rawtypes")
				List utilisateur = sessionFactory.getCurrentSession().createQuery("FROM Utilisateur WHERE" + 
					" username = '" + username + "'AND motdepasse = '" + motDePasse + "'").list();
			
			if(utilisateur.isEmpty()){
				return null;
			}
			return (Utilisateur) utilisateur.get(0);
		} catch(RuntimeException e){
			
		}
	}
}
