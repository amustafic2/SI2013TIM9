package ba.unsa.etf.si2013.tim9.Korisnici;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import ba.unsa.etf.si2013.tim9.HibernateUtil;

public class KorisniciPretragaForm extends Shell {

	protected Shell shlPretragaKorisnika;
	private Table table;
	private Text text;
	private Text text_1;

	List<Korisnik> korisnici;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			KorisniciPretragaForm shell = new KorisniciPretragaForm(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public KorisniciPretragaForm(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(KorisniciPretragaForm.class, "/images/1396674475_user_male2.png"));
		setSize(656, 448);
		setText("Pretraga i ispis korisnika");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION );
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 134, 626, 206);
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(30);
		tblclmnId.setText("ID");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("Ime");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("Prezime");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Korisni\u010Dko ime");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("E-mail");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(120);
		tableColumn_4.setText("Broj telefona");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("Uloga");
		
		
		
		Group group = new Group(this, SWT.NONE);
		group.setText("Pretraga");
		group.setBounds(10, 10, 575, 107);
		
		final Combo combo = new Combo(group, SWT.NONE);
		combo.setItems(new String[] {"Ime ", "Prezime", "Uloga"});
		combo.setBounds(112, 35, 142, 23);
		combo.setText("Izaberite kriterij");
		
		Label label = new Label(group, SWT.NONE);
		label.setText("Kirterij pretrage:");
		label.setBounds(10, 38, 96, 15);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(380, 35, 163, 21);
		
		Label lblUnesiteImeI = new Label(group, SWT.NONE);
		lblUnesiteImeI.setText("Unesite podatatk:");
		lblUnesiteImeI.setBounds(292, 38, 65, 15);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction t = session.beginTransaction();
				if(combo.getSelectionIndex()==0){
											
		        Query q = session.createQuery("from Korisnik where ime=:ime and deleted=:deleted");
		        q.setString("ime", text_1.getText());
		        q.setInteger("deleted", 0);
		        korisnici=q.list();
		        t.commit();
		        session.close();
		        Korisnik k=new Korisnik();
		        		        
		        for (int i=0; i<korisnici.size(); i++){
		        	k = (Korisnik) korisnici.get(i);
		        	if(k.getDeleted()==0){
		        TableItem item = new TableItem(table, 0, i);
		        
           	   item.setText(0,Long.toString(k.getId()));
           	    item.setText(1,k.getIme());
             	item.setText(2,k.getPrezime());
             	item.setText(3,k.getUsername());
           	    item.setText(4,k.getAdresa());
           	    item.setText(5,k.getTelefon());
           	    
           	    
           	    item.setText(6, k.getPozicija());}
		       }
		        }
				
			
			}
			
		});
		button.setText("Pretraga");
		button.setImage(SWTResourceManager.getImage(KorisniciPretragaForm.class, "/images/1398199827_search_magnifying_glass_find.png"));
		button.setBounds(427, 62, 116, 35);
		
		Button buttonOK = new Button(this, SWT.NONE);
		buttonOK.setText("OK");
		buttonOK.setImage(SWTResourceManager.getImage(KorisniciPretragaForm.class, "/images/1398195801_tick_32.png"));
		buttonOK.setBounds(376, 358, 116, 42);
		
		Button buttonIzlaz = new Button(this, SWT.NONE);
		buttonIzlaz.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		buttonIzlaz.setText("Izlaz");
		buttonIzlaz.setImage(SWTResourceManager.getImage(KorisniciPretragaForm.class, "/images/1398195841_DeleteRed.png"));
		buttonIzlaz.setBounds(506, 358, 116, 42);
		
		Button buttonGenerisiPDF = new Button(this, SWT.NONE);
		buttonGenerisiPDF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Document document = new Document(PageSize.A4, 50, 50, 50, 50);
				try {
					File f = new File(System.getProperty("user.home")+"\\factpro\\pdfovi\\");
					f.mkdirs();
					PdfWriter.getInstance(document,new FileOutputStream(System.getProperty("user.home")+"\\factpro\\pdfovi\\korisnik.pdf"));
					//PdfWriter.getInstance(document,new FileOutputStream("target/korisnik.pdf"));
		          //SADRZAJ
		            document.open();
		            //zaglavlje dokumenta

		            document.addAuthor("Factpro");
		            document.addCreationDate();
		            document.addLanguage("EN");

		            document.add(new Paragraph("Factpro",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
		            document.add(new Paragraph("________________________________________________________________________"
		            		+ "_________________________________________________________________________________"
		            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
		            
		            Paragraph naslov=new Paragraph("\n Podaci o korisniku",new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD));
		            naslov.setAlignment(Element.ALIGN_CENTER);
		            document.add(naslov);
		            Font pisanje=new Font(Font.FontFamily.HELVETICA  , 14, Font.NORMAL);
		            
		            document.add(new Chunk("\n",pisanje));        
		            
		            Korisnik ulogika=new Korisnik(); 
		            int i=table.getSelectionIndex();
		            
		            document.add(new Chunk("\n Ime i Prezime: "+korisnici.get(i).getIme()+" "+korisnici.get(i).getPrezime(),pisanje)); 
		            document.add(new Chunk("\n Email: "+korisnici.get(i).getAdresa(),pisanje)); 
		            document.add(new Chunk("\n Telefon: "+korisnici.get(i).getTelefon(),pisanje));
		            document.add(new Chunk("\n Username: "+korisnici.get(i).getUsername(),pisanje));
		            document.add(new Chunk("\n Pozicija: "+korisnici.get(i).getPozicija(),pisanje));
		            Font pisanje2=new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
		            Paragraph potpis=new Paragraph("\n \n \n \n \n \n __________________________________________  \n \n Potpis izdavaca ",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
		            potpis.setAlignment(Element.ALIGN_RIGHT);
		            document.add(potpis);
		            Paragraph footer= new Paragraph("________________________________________________________________________"
		            		+ "_________________________________________________________________________________"
		            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
		            footer.setAlignment(Element.ALIGN_BASELINE);
		            document.add(footer);
		            //KRAJ
		            document.close();;
			      
			      Shell shell1 = new Shell();
				MessageDialog.openInformation(shell1, "Generisanje pdf", "PDF je generisan!");
				
				
			//	Desktop.getDesktop().open((new FileOutputStream("target/korisnk.pdf"));
				
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			
				
			
		});
		buttonGenerisiPDF.setText("Generi\u0161i .pdf");
		buttonGenerisiPDF.setImage(SWTResourceManager.getImage(KorisniciPretragaForm.class, "/images/1398206257_pdf.png"));
		buttonGenerisiPDF.setBounds(10, 353, 119, 47);
		
		
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		shlPretragaKorisnika = new Shell();
		Group group = new Group(shlPretragaKorisnika, SWT.NONE);
		group.setText("Pretraga");
		group.setBounds(10, 10, 575, 107);
	}

	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
