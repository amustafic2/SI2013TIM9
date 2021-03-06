package ba.unsa.etf.si2013.tim9.Usluge;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si2013.tim9.HibernateUtil;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Point;


public class UslugeIzmjenaForm extends Shell {
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
//	protected Shell this;
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			UslugeIzmjenaForm shell = new UslugeIzmjenaForm(display);
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
	public UslugeIzmjenaForm(Display display) {
		super(display, SWT.SHELL_TRIM);
		setMinimumSize(new Point(200, 47));
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
//		this = new Shell();
		this.setImage(SWTResourceManager.getImage(UslugeIzmjenaForm.class, "/images/1396674353_services.png"));
		this.setSize(399, 445);
		this.setText("Izmjena usluge");
		
		Group group = new Group(this, SWT.NONE);

		group.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
			}
		});
		group.setBounds(10, 10, 361, 313);

		group.setBounds(10, 10, 363, 339);

		final List list = new List(group, SWT.BORDER);
		list.setBounds(26, 65, 311, 50);
		list.setItems(new String[] {});
		Group group_1 = new Group(group, SWT.NONE);
		group_1.setText("Tip usluge");
		group_1.setBounds(26, 166, 239, 65);
		
		final Button button_1 = new Button(group_1, SWT.RADIO);
		button_1.setText("Servis");
		button_1.setBounds(89, 24, 90, 16);
		
		final Button button_2 = new Button(group_1, SWT.RADIO);
		button_2.setText("Konsultacije");
		button_2.setBounds(89, 46, 90, 16);
		
		Label lblOdaberiteUslugu = new Label(group, SWT.NONE);
		lblOdaberiteUslugu.setBounds(26, 10, 105, 15);
		lblOdaberiteUslugu.setText("Odaberite uslugu:");
		
		Button btnIzmjeni = new Button(group, SWT.NONE);
		btnIzmjeni.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				Usluga k=new Usluga();
				int ind=list.getSelectionIndex();
				String s=list.getItem(ind);
				 String[] parts = s.split(" ");
			        String part1 = parts[0]; // 004
			        String part2 = parts[1]; // 034556
				//TableItem ti=table.getItem(ind);
				Usluga ku = 
	                    (Usluga)session.get(Usluga.class, (long)(Integer.parseInt(part1))); 
				
				if(text.getText()!=""){
				
		         ku.setNaziv( text.getText() );
		         list.remove(ind);
		         list.add(part1 + " " +text.getText(), ind);
				 					
				}
				
				if(text_1.getText()!=""){
					
			         ku.setCijena( Integer.parseInt(text_1.getText()) );
			        // list.add(text_1.getText(), ind);
					 					
					}
				
				if(button_1.getEnabled()){
					
			         ku.setTipUsluge( (button_1.getText()) );
			         
					 				
					}
				if(button_2.getEnabled()){
					
			         ku.setTipUsluge( (button_2.getText()) );
			         
					 				
					}
				session.update(ku); 
		        session.getTransaction().commit();
		        Shell shell=new Shell();
			MessageDialog.openInformation(shell, "Info", "Uspjesno je izvrsena izmjena.");
				}catch(Exception e45){}
			}
		});
		btnIzmjeni.setBounds(248, 279, 105, 50);
		btnIzmjeni.setImage(SWTResourceManager.getImage(UslugeIzmjenaForm.class, "/images/1396674353_services.png"));
		btnIzmjeni.setText("Izmijeni");
		

		Button btnIspisiUsluge = new Button(group, SWT.NONE);
		btnIspisiUsluge.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction t = session.beginTransaction();
				java.util.List<Usluga> usluge;
				
					
			        Query q = session.createQuery("from Usluga where deleted=:deleted");
			        q.setInteger("deleted", 0);
			       
			        usluge=q.list();
			        t.commit();
			        session.close();
			        Usluga k=new Usluga();
			        		        
			        for (int i=0; i<usluge.size(); i++){
			        	k = (Usluga) usluge.get(i);
			        	if(k.getDeleted()==0)
			        	list.add(k.getId()+" "+k.getNaziv(), i);
			       
			        }
				}catch(Exception e12){}
			}
		});
		btnIspisiUsluge.setBounds(26, 34, 311, 25);
		btnIspisiUsluge.setText("Ispisi usluge");

		Label label = new Label(group, SWT.NONE);
		label.setText("Naziv usluge:");
		label.setBounds(26, 133, 78, 15);
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(114, 130, 223, 21);
		
		
		
		
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("Cijena usluge(KM):");
		label_1.setBounds(26, 250, 100, 15);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(139, 247, 78, 21);

		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		button.setText("Izlaz");
		button.setImage(SWTResourceManager.getImage(UslugeIzmjenaForm.class, "/images/1398195841_DeleteRed.png"));
		button.setBounds(255, 355, 116, 42);
		
		Button btnOk = new Button(this, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell();
				MessageDialog.openInformation(shell, "Potvrda", "Izmjene završene.");
			}
		});
		btnOk.setText("Ok");
		btnOk.setImage(SWTResourceManager.getImage(UslugeIzmjenaForm.class, "/images/1398195801_tick_32.png"));
		btnOk.setBounds(124, 355, 116, 42);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
