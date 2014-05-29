package ba.unsa.etf.si2013.tim9.Ponude;

import java.io.Serializable;

import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;	
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;

import ba.unsa.etf.si2013.tim9.HibernateUtil;

@Entity 
@Table (name = "TB_Ponude")
public class Ponude implements Serializable {
	

	 @Id 
	 @GeneratedValue
	 long id;
	 
	 private int idstavka;

	 private int idzaposlenik;
	 
	 private int idklijent;
	 
	 private int cijena;

	private String nacin_placanja;
	private String datum_kreiranja;
	private String komentar;
		
		public Ponude(){} 
		public Ponude(int idstavka, int idzaposlenik, int idklijent, int cijena, String nacin_placanja, String datum_kreiranja, String komentar) {
			this.setIDStavka(idstavka);
			this.setIDZaposlenik(idzaposlenik);
			this.setIDKlijent(idklijent);
			this.setCijena(cijena);
			this.setNacinPlacanja(nacin_placanja);
			this.setDatumKreiranja(datum_kreiranja);
			this.setKomentar(komentar);
					
			
		// TODO Auto-generated constructor stub
		}
			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			
			public int getIDStavka() {
				return idstavka;
			}
			public void setIDStavka(int idstavka) {
				this.idstavka = idstavka;
			}
			
			public int getIDZaposlenik() {
				return idzaposlenik;
			}
			public void setIDZaposlenik(int idzaposlenik) {
				this.idzaposlenik = idzaposlenik;
			}
			
			public int getIDKlijent() {
				return idklijent;
			}
			public void setIDKlijent(int idklijent) {
				this.idklijent = idklijent;
			}
			
			public int getCijena() {
				return cijena;
			}
			public void setCijena(int cijena) {
				this.cijena = cijena;
			}
			
	public String getNacinPlacanja() {
		return nacin_placanja;
	}
	public void setNacinPlacanja(String nacin_placanja) {
		this.nacin_placanja = nacin_placanja;
	}
	
	public String getDatumKreiranja() {
		return datum_kreiranja;
	}
	public void setDatumKreiranja(String datum_kreiranja) {
		this.datum_kreiranja = datum_kreiranja;
	}
	String getKomentar() {
		return komentar;
	}
	void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public void spasiUBazu() {
		 	Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			session.save(this);
			t.commit();
			session.close();
		 	}
	
	private static List dajKlijente() { 
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
 
        List ponude = session.createQuery("from Ponude").list();
        t.commit();
        session.close();
        return ponude;
		 }
	
	public void izbrisiIzBaze(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(this);
		t.commit();
		session.close();
	}
}