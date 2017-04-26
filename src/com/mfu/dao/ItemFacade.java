

	package com.mfu.dao;

	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;
	import javax.persistence.Query;

	import com.mfu.entity.Item;

	public class ItemFacade {

		private static final EntityManagerFactory emfInstance  = Persistence
				.createEntityManagerFactory("transactions-optional");
		private EntityManager em = null;
		
		public ItemFacade() {
			em = emfInstance.createEntityManager();
		}
		
		public List<Item> getAllItem() {
			List<Item> item = null;
			
			try {
				Query q = em.createQuery("SELECT p FROM Item p");
				item= q.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return item;
		}
		
		public Item findItemByKey(String key) {
			Item item  = null;
			
			try {
				Query q = em.createQuery("select p from Item p where p.key=:key");
				q.setParameter("key", key);
				List<Item> itemList = q.getResultList();
				item = itemList.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return item;
		}
		
		public void saveItem(Item item) {
			
			try {
				em.persist(item);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public void closeEntityManager() {
			if(em != null) {
				em.close();
			}
		}
		
		public void deleteItem(Item item) {
			if(item != null) {
				em.remove(item);
			}
		}
	}

