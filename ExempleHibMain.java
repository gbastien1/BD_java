package PackageBD;

import org.hibernate.sessionFactory;
import org.hibernate.*;
import java.util.*;

public class ExempleHibMain {

    public static void main(String[] args) {

        // Une première session Hibernate qui insère deux éditeurs et deux livres
        System.out.println();
        System.out.println("Une premiere session Hibernate qui insere deux editeurs et deux livres");
        
        Session uneSession = HibernateUtil.getSessionFactory().openSession();
        Transaction uneTransaction = uneSession.beginTransaction();

        Editeur unEditeur = new Editeur("maMaison","Montreal");
        Editeur unAutreEditeur = new Editeur("Addison-Wesley","Reading, MA");
	    Livre unLivre = new Livre("1-111-1111","SGBD",2000,unEditeur);
	    unEditeur.getLesLivres().add(unLivre);
	    Livre unAutreLivre = new Livre("2-222-2222","le titre",1999,unEditeur);
	    unEditeur.getLesLivres().add(unAutreLivre);

        uneSession.save(unEditeur);
        uneSession.save(unAutreEditeur);

        uneTransaction.commit();
        uneSession.close();


        // Une deuxième session Hibernate qui lit les données de la première session et les affiche 
        /*System.out.println();
        System.out.println("Une deuxieme session Hibernate qui lit les donnees de la premiere session et les affiche");

        uneSession = HibernateUtil.getSessionFactory().openSession();
        uneTransaction = uneSession.beginTransaction();

        List lesEditeurs =
            uneSession.createQuery("from Editeur e order by e.nomEditeur asc").list();

        System.out.println( lesEditeurs.size() + " editeurs trouves:" );

        for ( Iterator iterEditeurs = lesEditeurs.iterator(); iterEditeurs.hasNext(); ) {
            Editeur unEditeurCharge = (Editeur) iterEditeurs.next();
            System.out.println("Editeur:"+ unEditeurCharge.getNomEditeur() );
        	System.out.println("Livres de l'editeur:" );

        	for ( Iterator iterLivres = unEditeurCharge.getLesLivres().iterator(); iterLivres.hasNext(); ) {
            	Livre unLivreCharge = (Livre) iterLivres.next();
            	System.out.println("   "+unLivreCharge.getTitre() );
		}
        }

        uneTransaction.commit();
        uneSession.close();


        // Une troisième session Hibernate qui extrait les éditeurs de Paris ou Longueuil 
        System.out.println();
        System.out.println("Une troisieme session Hibernate qui extrait les editeurs de Paris ou Longueuil");

        uneSession = HibernateUtil.getSessionFactory().openSession();
        uneTransaction = uneSession.beginTransaction();

        lesEditeurs =
            uneSession.createQuery("from Editeur e where e.ville = 'Paris' or e.ville = 'Longueuil' order by e.nomEditeur asc").list();

        System.out.println( lesEditeurs.size() + " editeurs trouves pour Paris ou Longueuil:" );

        for ( Iterator iterEditeurs = lesEditeurs.iterator(); iterEditeurs.hasNext(); ) {
            Editeur unEditeurCharge = (Editeur) iterEditeurs.next();
            System.out.println("Editeur:"+ unEditeurCharge.getNomEditeur() );
        	System.out.println("Livres de l'editeur:" );

        	for ( Iterator iterLivres = unEditeurCharge.getLesLivres().iterator(); iterLivres.hasNext(); ) {
            	Livre unLivreCharge = (Livre) iterLivres.next();
            	System.out.println("   "+unLivreCharge.getTitre() );
		}
        }

        uneTransaction.commit();
        uneSession.close();


        // Une quatrième session Hibernate qui modifie le titre de ISBN:1-111-1111 
        System.out.println();
        System.out.println("Une quatrieme session Hibernate qui modifie le titre de ISBN:1-111-1111");

        uneSession = HibernateUtil.getSessionFactory().openSession();
        
        // Modifie le titre du livre intitulé SGBD
        uneTransaction = uneSession.beginTransaction();
        List lesLivres =
            uneSession.createQuery("from Livre l where l.ISBN = '1-111-1111'  ").list();
        unLivre = (Livre)lesLivres.iterator().next();
        unLivre.setTitre("UnNouveauTitre");
        uneTransaction.commit();
        
        uneTransaction = uneSession.beginTransaction();
        lesLivres =
            uneSession.createQuery("from Livre l where l.ISBN = '1-111-1111'  ").list();
        unLivre = (Livre)lesLivres.iterator().next();
        
        System.out.println("Titre modifie pour ISBN :"+unLivre.getISBN());
        System.out.println("Titre :"+unLivre.getTitre());
        uneTransaction.commit();

        uneSession.close();
        

        // Une cinquième session Hibernate qui supprime un livre 
        System.out.println();
        System.out.println("Une cinquieme session Hibernate qui supprime un livre");

        uneSession = HibernateUtil.getSessionFactory().openSession();
        
        // Supprimer le livre ISBN = 1-111-1111
        uneTransaction = uneSession.beginTransaction();
        lesLivres =
            uneSession.createQuery("from Livre l where l.ISBN = '1-111-1111'  ").list();
        unLivre = (Livre)lesLivres.iterator().next();
        uneSession.delete(unLivre);
        uneTransaction.commit();
        
        // Afficher les données
        uneSession = HibernateUtil.getSessionFactory().openSession();
        uneTransaction = uneSession.beginTransaction();

        lesEditeurs =
            uneSession.createQuery("from Editeur e order by e.nomEditeur asc").list();

        System.out.println( lesEditeurs.size() + " editeurs trouves:" );

        for ( Iterator iterEditeurs = lesEditeurs.iterator(); iterEditeurs.hasNext(); ) {
            Editeur unEditeurCharge = (Editeur) iterEditeurs.next();
            System.out.println("Editeur:"+ unEditeurCharge.getNomEditeur() );
        	System.out.println("Livres de l'editeur:" );

        	for ( Iterator iterLivres = unEditeurCharge.getLesLivres().iterator(); iterLivres.hasNext(); ) {
            	Livre unLivreCharge = (Livre) iterLivres.next();
            	System.out.println("   "+unLivreCharge.getTitre() );
		}
        }

        uneTransaction.commit();

        uneSession.close();


        // Une sixième session Hibernate qui supprime les éditeurs (les livres sont supprimés par référence!)
        System.out.println();
        System.out.println("Une sixieme session Hibernate qui supprime les editeurs (les livres sont supprimes par reference!)");

        uneSession = HibernateUtil.getSessionFactory().openSession();
        uneTransaction = uneSession.beginTransaction();

        lesEditeurs =
            uneSession.createQuery("from Editeur e order by e.nomEditeur asc").list();

        System.out.println( lesEditeurs.size() + " editeurs trouves:" );

        for ( Iterator iterEditeurs = lesEditeurs.iterator(); iterEditeurs.hasNext(); ) {
            Editeur unEditeurCharge = (Editeur) iterEditeurs.next();
            System.out.println("Suppression de l'editeur : "+unEditeurCharge.getNomEditeur());
            uneSession.delete(unEditeurCharge);
        }
        uneTransaction.commit();

        
        // Afficher les données
        uneSession = HibernateUtil.getSessionFactory().openSession();
        uneTransaction = uneSession.beginTransaction();

        lesEditeurs =
            uneSession.createQuery("from Editeur e order by e.nomEditeur asc").list();

        System.out.println( lesEditeurs.size() + " editeurs trouves:" );

        for ( Iterator iterEditeurs = lesEditeurs.iterator(); iterEditeurs.hasNext(); ) {
            Editeur unEditeurCharge = (Editeur) iterEditeurs.next();
            System.out.println("Editeur:"+ unEditeurCharge.getNomEditeur() );
        	System.out.println("Livres de l'editeur:" );

        	for ( Iterator iterLivres = unEditeurCharge.getLesLivres().iterator(); iterLivres.hasNext(); ) {
            	Livre unLivreCharge = (Livre) iterLivres.next();
            	System.out.println("   "+unLivreCharge.getTitre() );
		}
        }

        uneTransaction.commit();

        uneSession.close();*/
        
        
        // Arrêt de l'application Hibernate
        HibernateUtil.shutdown();
    }
}
