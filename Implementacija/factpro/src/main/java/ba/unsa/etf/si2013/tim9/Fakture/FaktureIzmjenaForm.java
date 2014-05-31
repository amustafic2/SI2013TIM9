package ba.unsa.etf.si2013.tim9.Fakture;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.wb.swt.IzmjenaFaktureTrenutneForm;
import org.eclipse.wb.swt.SWTResourceManager;

public class FaktureIzmjenaForm extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
//	protected Shell this;
	private Table table;
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			FaktureIzmjenaForm shell = new FaktureIzmjenaForm(display);
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
	public FaktureIzmjenaForm(Display display) {
		super(display, SWT.SHELL_TRIM);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
//		this = new Shell();
		this.setImage(SWTResourceManager.getImage(FaktureIzmjenaForm.class, "/images/1396674611_invoice.png"));
		this.setSize(610, 478);
		this.setText("Izmjena fakture");
		
		Group grpPretragaFakture = new Group(this, SWT.NONE);
		grpPretragaFakture.setText("Pretraga fakture");
		grpPretragaFakture.setBounds(10, 10, 575, 162);
		
		Combo combo = new Combo(grpPretragaFakture, SWT.NONE);
		combo.setItems(new String[] {"Naziv firme", "PDV broj"});
		combo.setBounds(112, 84, 142, 23);
		combo.setText("Naziv firme");
		
		Label label = new Label(grpPretragaFakture, SWT.NONE);
		label.setText("Kirterij pretrage:");
		label.setBounds(10, 87, 96, 15);
		
		Button button = new Button(grpPretragaFakture, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell();
				MessageDialog.openInformation(shell, "Info", "Uspješno je izvršena pretraga faktura. Fakture su prikazane u tabeli ispod.");
			}
		});
		button.setText("Pretraga");
		button.setImage(SWTResourceManager.getImage(FaktureIzmjenaForm.class, "/images/1398199827_search_magnifying_glass_find.png"));
		button.setBounds(427, 117, 116, 35);
		
		Group group_1 = new Group(grpPretragaFakture, SWT.NONE);
		group_1.setText("Odabir");
		group_1.setBounds(10, 22, 142, 56);
		
		Button button_1 = new Button(group_1, SWT.RADIO);
		button_1.setText("Firma");
		button_1.setSelection(true);
		button_1.setBounds(54, 10, 90, 16);
		
		Button button_2 = new Button(group_1, SWT.RADIO);
		button_2.setText("Fizi\u010Dko lice");
		button_2.setBounds(54, 32, 78, 16);
		
		Combo combo_1 = new Combo(grpPretragaFakture, SWT.NONE);
		combo_1.setBounds(394, 84, 149, 23);
		combo_1.setText("Mercator");
		
		Label label_1 = new Label(grpPretragaFakture, SWT.NONE);
		label_1.setText("Odabir firme:");
		label_1.setBounds(302, 87, 91, 15);
		
		Label lblIzaberiteeljenuFakturu = new Label(this, SWT.NONE);
		lblIzaberiteeljenuFakturu.setText("Izaberite \u017Eeljenu fakturu:");
		lblIzaberiteeljenuFakturu.setBounds(10, 186, 136, 15);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 202, 575, 179);
		
		TableColumn tblclmnIdFakture = new TableColumn(table, SWT.NONE);
		tblclmnIdFakture.setWidth(100);
		tblclmnIdFakture.setText("ID fakture");
		
		TableColumn tblclmnUkupnaCijena = new TableColumn(table, SWT.NONE);
		tblclmnUkupnaCijena.setWidth(137);
		tblclmnUkupnaCijena.setText("Ukupna cijena");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Datum kreiranja");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Naziv firme");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(132);
		tableColumn_4.setText("PDV broj");
		
		Button button_3 = new Button(this, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		button_3.setText("Izlaz");
		button_3.setImage(SWTResourceManager.getImage(FaktureIzmjenaForm.class, "/images/1398195841_DeleteRed.png"));
		button_3.setBounds(469, 388, 116, 42);
		
		Button btnIzmjeni = new Button(this, SWT.NONE);
		btnIzmjeni.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FaktureTrenutnaIzmjenaForm a= new FaktureTrenutnaIzmjenaForm(null);
				a.open();
//				IzmjenaFaktureTrenutneForm f =new IzmjenaFaktureTrenutneForm();
//				f.open();
				
			}
		});
		btnIzmjeni.setText("Izmjeni");
		btnIzmjeni.setImage(SWTResourceManager.getImage(FaktureIzmjenaForm.class, "/images/1398574614_switch_on.png"));
		btnIzmjeni.setBounds(346, 388, 116, 42);
		
		Button button_5 = new Button(this, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell();
				MessageDialog.openInformation(shell, "Info", "Uspjesno je generisan '.pdf' fakture.");
			}
		});
		button_5.setText("Generi\u0161i .pdf");
		button_5.setImage(SWTResourceManager.getImage(FaktureIzmjenaForm.class, "/images/1398206257_pdf.png"));
		button_5.setBounds(10, 388, 132, 42);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
