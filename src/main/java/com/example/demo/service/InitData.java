package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class InitData {

    @Autowired
    private EntityManager em;

    public void insertTestData() {
        
		Client client = new Client();
		client.setNom("Kerouani");
		client.setPrenom("Florian");
		client.setAge("24");
		em.persist(client);
		
		Client client1 = new Client();
		client1.setNom("Martin");
		client1.setPrenom("Lucas");
		client1.setAge("17");
        em.persist(client1);
		
		Client client2 = new Client();
		client2.setNom("Farmer");
		client2.setPrenom("Mylène");
		client2.setAge("57");
        em.persist(client2);
		
		Client client3 = new Client();
		client3.setNom("Forissier");
		client3.setPrenom("Gwenaelle");
		client3.setAge("28");
        em.persist(client3);
		
		Client client4 = new Client();
		client4.setNom("Hani");
		client4.setPrenom("Katia");
		client4.setAge("22");
        em.persist(client4);
		
		Client client5 = new Client();
		client5.setNom("Dupont");
		client5.setPrenom("Anthony");
		client5.setAge("40");
        em.persist(client5);

		Article article = new Article();
		artcle.setLibelle("Clé USB");
		artcle.setPrix(9.90);
        em.persist(article);
		
        Article article1 = new Article();
		artcle1.setLibelle("Carte mère ASROCK 2345");
		artcle1.setPrix(79.90);
        em.persist(article1);
		
		Article article2 = new Article();
		article2.setLibelle("GTX 1080Ti 11Go");
		article2.setPrix(800);
        em.persist(article2);
		
		Article article3 = new Article();
		article3.setLibelle("MSI GeForce GTX 1080 GAMING 8G");
		article3.setPrix(512.91);
        em.persist(article3);
		
		Article article4 = new Article();
		article4.setLibelle("Core i9-9900K");
		article4.setPrix(500);
        em.persist(article4);

		Facture facture = new Facture();
		facture.setClient(client);
		em.persist(facture);
		
		Facture facture1 = new Facture();
		facture1.setClient(client1);
		em.persist(facture1);
		
		Facture facture2 = new Facture();
		facture2.setClient(client2);
		em.persist(facture2);
		
		Facture facture3 = new Facture();
		facture3.setClient(client3);
		em.persist(facture3);
		
		Facture facture4 = new Facture();
		facture4.setClient(client4);
		em.persist(facture4);
		
		Facture facture5 = new Facture();
		facture5.setClient(client5);
		em.persist(facture5);
		
		LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setFacture(facture);
        ligneFacture.setArticle(article);
        ligneFacture.setQuantite(1);
        em.persist(ligneFacture);
		
		LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article1);
        ligneFacture1.setQuantite(1);
        em.persist(ligneFacture1);
        
        LigneFacture ligneFacture2 = new LigneFacture();
        ligneFacture2.setFacture(facture);
        ligneFacture2.setArticle(article2);
        ligneFacture2.setQuantite(1);
        em.persist(ligneFacture2);
        
        LigneFacture ligneFacture3 = new LigneFacture();
        ligneFacture3.setFacture(facture);
        ligneFacture3.setArticle(article3);
        ligneFacture3.setQuantite(1);
        em.persist(ligneFacture3);
        
        LigneFacture ligneFacture4 = new LigneFacture();
        ligneFacture4.setFacture(facture2);
        ligneFacture4.setArticle(article);
        ligneFacture4.setQuantite(1);
        em.persist(ligneFacture4);
        
        LigneFacture ligneFacture5 = new LigneFacture();
        ligneFacture5.setFacture(facture2);
        ligneFacture5.setArticle(article2);
        ligneFacture5.setQuantite(1);
        em.persist(ligneFacture5);
        
        LigneFacture ligneFacture6 = new LigneFacture();
        ligneFacture6.setFacture(facture2);
        ligneFacture6.setArticle(article3);
        ligneFacture6.setQuantite(1);
        em.persist(ligneFacture6);
        
        LigneFacture ligneFacture7 = new LigneFacture();
        ligneFacture7.setFacture(facture3);
        ligneFacture7.setArticle(article);
        ligneFacture7.setQuantite(1);
        em.persist(ligneFacture7);
        
        LigneFacture ligneFacture8 = new LigneFacture();
        ligneFacture8.setFacture(facture3);
        ligneFacture8.setArticle(article2);
        ligneFacture8.setQuantite(1);
        em.persist(ligneFacture8);
        
        LigneFacture ligneFacture9 = new LigneFacture();
        ligneFacture9.setFacture(facture3);
        ligneFacture9.setArticle(article3);
        ligneFacture9.setQuantite(1);
        em.persist(ligneFacture9);
    }
}
