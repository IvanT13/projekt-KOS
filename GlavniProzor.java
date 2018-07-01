package potrosnja;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import model.ModelPot;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GlavniProzor {

	ArrayList<ModelPot> lista = new ArrayList<ModelPot>();
	
	
	protected static Shell shlGlavni;
	public static Text txtTrosilo1;
	public static Text txtTrosilo2;
	public static Text txtTrosilo3;
	public static Text txtTrosilo4;
	public static Text txtVrijeme;
	public static Text txtTrosilo1Sim;
	public static Text txtTrosilo2Sim;
	public static Text txtTrosilo3Sim;
	public static Text txtTrosilo4Sim;
	public static Text txtUkupnoSim;
	public static Text txtVrijemSim;
	public static Display display = Display.getDefault();
	public static Text txtIspis;
	public static int provjera = -1;
	public static int brojacTimer = 0;
	
	//SIM
	public static double ukupnaPotrosnja = 0;
	public static double potrosnjaT1 = 0;
	public static double potrosnjaT2 = 0;
	public static double potrosnjaT3 = 0;
	public static double potrosnjaT4 = 0;
	public static int vrijemeSim = 0;
	
	//UNOS
	public static double snagaT1 = 0;
	public static double snagaT2 = 0;
	public static double snagaT3 = 0;
	public static double snagaT4 = 0;
	
	
	
	public static class Timer implements Runnable{
		
		MessageBox msg = new MessageBox(shlGlavni, SWT.ERROR);
		MessageBox msg1 = new MessageBox(shlGlavni, SWT.ERROR);
		
		public void run(){
			
			for(int i = 0; i<vrijemeSim; i++){
			
				
				
				try{
					Thread.currentThread().sleep(1000);
					
					doUpdate(display, txtVrijemSim, String.valueOf(i+1));
					
					
				}catch(InterruptedException ie){
					
					msg.setMessage("TIMER INTERRUPTED");
				}
				
			}
			
		}
	}

	
	
	
	public static class Trosilo1 implements Runnable{
		
		MessageBox msg = new MessageBox(shlGlavni, SWT.ERROR);
		
		public void run(){
			
			potrosnjaT1 = 0;
			
			for(int i = 0; i<vrijemeSim; i++){
			
				try{
					
					Thread.currentThread().sleep(1000);
					
					potrosnjaT1 += snagaT1;
					
					doUpdate(display, txtTrosilo1Sim, String.valueOf(potrosnjaT1));
				
					
				}catch(InterruptedException ie){
					
					msg.setMessage("THREAD TROSILO1 INTERRUPTED");
				}
				
			}
			
		}
	}
	
	public static class Trosilo2 implements Runnable{
		
		MessageBox msg = new MessageBox(shlGlavni, SWT.ERROR);
		
		public void run(){
			
			
			potrosnjaT2 = 0;
			
			for(int i = 0; i<vrijemeSim; i++){
			
				try{
					
					Thread.currentThread().sleep(1000);
					
					potrosnjaT2 += snagaT2;
					
					doUpdate(display, txtTrosilo2Sim, String.valueOf(potrosnjaT2));
				
					
				}catch(InterruptedException ie){
					
					msg.setMessage("THREAD TROSILO2 INTERRUPTED");
				}
				
			}
			
		}
	}
	
	public static class Trosilo3 implements Runnable{
		
		MessageBox msg = new MessageBox(shlGlavni, SWT.ERROR);
		
		public void run(){
			
			
			potrosnjaT3 = 0;
			
			for(int i = 0; i<vrijemeSim; i++){
			
				try{
					
					Thread.currentThread().sleep(1000);
					
					potrosnjaT3 += snagaT3;
					
					doUpdate(display, txtTrosilo3Sim, String.valueOf(potrosnjaT3));
					
					
				}catch(InterruptedException ie){
					
					msg.setMessage("THREAD TROSILO3 INTERRUPTED");
				}
				
			}
			
		}
	}
	
	public static class Trosilo4 implements Runnable{
		
		MessageBox msg = new MessageBox(shlGlavni, SWT.ERROR);
		
		public void run(){
			
			
			potrosnjaT4 = 0;
			
			for(int i = 0; i<vrijemeSim; i++){
			
				try{
					
					Thread.currentThread().sleep(1000);
					
					potrosnjaT4 += snagaT4;
					
					doUpdate(display, txtTrosilo4Sim, String.valueOf(potrosnjaT4));
					
					
				}catch(InterruptedException ie){
					
					msg.setText("GRESKA!");
					msg.setMessage("THREAD TROSILO4 INTERRUPTED");
					msg.open();
					
					
				}
				
			}
			
		}
	}
	
	public static class Ukupno implements Runnable{
		
		MessageBox msg = new MessageBox(shlGlavni, SWT.ERROR);
		
		public void run(){
			
			ukupnaPotrosnja = 0;
			
			for(int i = 0; i<vrijemeSim; i++){
			
				try{
					
					Thread.currentThread().sleep(1000);
					
					ukupnaPotrosnja += snagaT1 + snagaT2 + snagaT3 + snagaT4;
					doUpdate(display, txtUkupnoSim, String.valueOf(ukupnaPotrosnja));
					
				}catch(InterruptedException ie){
					
					msg.setText("GRESKA!");
					msg.setMessage("THREAD UKUPNO INTERRUPTED");
					msg.open();
					
					
				}
				
			}
			
		}
	}
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GlavniProzor window = new GlavniProzor();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlGlavni.open();
		shlGlavni.layout();
		while (!shlGlavni.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlGlavni = new Shell();
		shlGlavni.setSize(537, 600);
		shlGlavni.setText("Simulacija potrosnje elektricne energije");
		
		Group grpUnos = new Group(shlGlavni, SWT.NONE);
		grpUnos.setText("Unesite snagu u W");
		grpUnos.setBounds(10, 10, 206, 141);
		
		Label lblTrosilo1 = new Label(grpUnos, SWT.NONE);
		lblTrosilo1.setBounds(10, 29, 55, 15);
		lblTrosilo1.setText("Trosilo 1");
		
		Label lblTrosilo2 = new Label(grpUnos, SWT.NONE);
		lblTrosilo2.setText("Trosilo 2");
		lblTrosilo2.setBounds(10, 57, 55, 15);
		
		Label lblTrosilo3 = new Label(grpUnos, SWT.NONE);
		lblTrosilo3.setText("Trosilo 3");
		lblTrosilo3.setBounds(10, 88, 55, 15);
		
		Label lblTrosilo4 = new Label(grpUnos, SWT.NONE);
		lblTrosilo4.setText("Trosilo 4");
		lblTrosilo4.setBounds(10, 118, 55, 15);
		
		txtTrosilo1 = new Text(grpUnos, SWT.BORDER);
		txtTrosilo1.setBounds(97, 26, 76, 21);
		
		txtTrosilo2 = new Text(grpUnos, SWT.BORDER);
		txtTrosilo2.setBounds(97, 54, 76, 21);
		
		txtTrosilo3 = new Text(grpUnos, SWT.BORDER);
		txtTrosilo3.setBounds(97, 82, 76, 21);
		
		txtTrosilo4 = new Text(grpUnos, SWT.BORDER);
		txtTrosilo4.setBounds(97, 112, 76, 21);
		
		Label lblKw1 = new Label(grpUnos, SWT.NONE);
		lblKw1.setBounds(179, 29, 17, 15);
		lblKw1.setText("W");
		
		Label lblKw2 = new Label(grpUnos, SWT.NONE);
		lblKw2.setText("W");
		lblKw2.setBounds(179, 57, 17, 15);
		
		Label lblKw3 = new Label(grpUnos, SWT.NONE);
		lblKw3.setText("W");
		lblKw3.setBounds(179, 88, 17, 15);
		
		Label lblKw4 = new Label(grpUnos, SWT.NONE);
		lblKw4.setText("W");
		lblKw4.setBounds(179, 118, 17, 15);
		
		Label lblTSimulacije = new Label(shlGlavni, SWT.NONE);
		lblTSimulacije.setText("Vrijeme simulacije (s)");
		lblTSimulacije.setBounds(10, 157, 112, 15);
		
		txtVrijeme = new Text(shlGlavni, SWT.BORDER);
		txtVrijeme.setBounds(10, 178, 112, 21);
		
		Button btnStart = new Button(shlGlavni, SWT.NONE);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox msg = new MessageBox(shlGlavni, SWT.ERROR);
				
				String pt1;
				String pt2;
				String pt3;
				String pt4;
				String pvrijemeSim;
				
				pt1 = txtTrosilo1.getText();
				pt2 = txtTrosilo2.getText();
				pt3 = txtTrosilo3.getText();
				pt4 = txtTrosilo4.getText();
				pvrijemeSim = txtVrijeme.getText();
				
				double ppt1 = 0;
				double ppt2 = 0;
				double ppt3 = 0;
				double ppt4 = 0;
				int ppvrijeme = 1;
				provjera = -1;
				
				try{
					
					ppt1 = Double.valueOf(txtTrosilo1.getText());
					ppt2 = Double.valueOf(txtTrosilo2.getText());
					ppt3 = Double.valueOf(txtTrosilo3.getText());
					ppt4 = Double.valueOf(txtTrosilo4.getText());
					ppvrijeme = Integer.valueOf(txtVrijeme.getText());
					
					provjera = 1;
					
				}catch(NumberFormatException nfe){
					
					provjera = 0;
					
				}
				
				//PROVJERA ISPUNE
				if(pt1.equals("") || pt2.equals("") || pt3.equals("") || pt4.equals("") || pvrijemeSim.equals("")){
					
					provjera = 0;
					msg.setMessage("SVA POLJA MORAJU BITI POPUNJENA");
					msg.setText("GRESKA!");
					msg.open();
				}
				else if(provjera == 0){
					
					msg.setMessage("UNOSI MORAJU BITI BROJEVI");
					msg.setText("GRESKA!");
					msg.open();
					
					txtTrosilo1.setText("");
					txtTrosilo2.setText("");
					txtTrosilo3.setText("");
					txtTrosilo4.setText("");
					txtVrijeme.setText("");
					
				}
				else if(ppt1 < 0){
					
					provjera = 0;
					
					msg.setMessage("UNOSI MORAJU BITI POZITIVNI");
					msg.setText("GRESKA!");
					msg.open();
					
					txtTrosilo1.setText("");
					
					
					
				}
				else if(ppt2 < 0){
					
					provjera = 0;
					
					msg.setMessage("UNOSI MORAJU BITI POZITIVNI");
					msg.setText("GRESKA!");
					msg.open();
					
					
					txtTrosilo2.setText("");
					
					
				}
				else if(ppt3 < 0){
					
					provjera = 0;
					
					msg.setMessage("UNOSI MORAJU BITI POZITIVNI");
					msg.setText("GRESKA!");
					msg.open();
					
					
					txtTrosilo3.setText("");
					
					
					
				}
				else if(ppt4 < 0){
					
					provjera = 0;
					
					msg.setMessage("UNOSI MORAJU BITI POZITIVNI");
					msg.setText("GRESKA!");
					msg.open();
					
					
					txtTrosilo4.setText("");
					
				}
				else if(ppvrijeme < 1){
					
					provjera = 0;
					
					msg.setMessage("VRIJEME MORA BITI VECE OD 0");
					msg.setText("GRESKA!");
					msg.open();
					
					txtVrijeme.setText("");
				}
				if(provjera == 1){
					
					snagaT1 = Double.valueOf(txtTrosilo1.getText());
					snagaT2 = Double.valueOf(txtTrosilo2.getText());
					snagaT3 = Double.valueOf(txtTrosilo3.getText());
					snagaT4 = Double.valueOf(txtTrosilo4.getText());
					
					txtIspis.setText("");
					
					vrijemeSim = vrijemeSimulacije();
					
					//DRETVE
					Thread dretvaTimer = new Thread(new Timer());
					Thread dretvaT1 = new Thread(new Trosilo1());
					Thread dretvaT2 = new Thread(new Trosilo2());
					Thread dretvaT3 = new Thread(new Trosilo3());
					Thread dretvaT4 = new Thread(new Trosilo4());
					Thread dretvaUkupno = new Thread(new Ukupno());
					
					dretvaTimer.start();
					dretvaUkupno.start();
					dretvaT1.start();
					dretvaT2.start();
					dretvaT3.start();
					dretvaT4.start();
				}
				
			}
		});
		btnStart.setBounds(10, 205, 112, 25);
		btnStart.setText("Pokreni simulaciju");
		
		Group grpSimulacija = new Group(shlGlavni, SWT.NONE);
		grpSimulacija.setText("Simulacija");
		grpSimulacija.setBounds(10, 236, 206, 196);
		
		Label lblTrosilo1Sim = new Label(grpSimulacija, SWT.NONE);
		lblTrosilo1Sim.setText("Trosilo 1");
		lblTrosilo1Sim.setBounds(10, 50, 55, 15);
		
		txtTrosilo1Sim = new Text(grpSimulacija, SWT.BORDER | SWT.READ_ONLY);
		txtTrosilo1Sim.setBounds(87, 47, 76, 21);
		
		Label lblTrosilo2Sim = new Label(grpSimulacija, SWT.NONE);
		lblTrosilo2Sim.setText("Trosilo 2");
		lblTrosilo2Sim.setBounds(10, 78, 55, 15);
		
		txtTrosilo2Sim = new Text(grpSimulacija, SWT.BORDER | SWT.READ_ONLY);
		txtTrosilo2Sim.setBounds(87, 75, 76, 21);
		
		Label lblTrosilo3Sim = new Label(grpSimulacija, SWT.NONE);
		lblTrosilo3Sim.setText("Trosilo 3");
		lblTrosilo3Sim.setBounds(10, 109, 55, 15);
		
		txtTrosilo3Sim = new Text(grpSimulacija, SWT.BORDER | SWT.READ_ONLY);
		txtTrosilo3Sim.setBounds(87, 103, 76, 21);
		
		Label lblTrosilo4Sim = new Label(grpSimulacija, SWT.NONE);
		lblTrosilo4Sim.setText("Trosilo 4");
		lblTrosilo4Sim.setBounds(10, 139, 55, 15);
		
		txtTrosilo4Sim = new Text(grpSimulacija, SWT.BORDER | SWT.READ_ONLY);
		txtTrosilo4Sim.setBounds(87, 130, 76, 21);
		
		Label lblUkupnoSim = new Label(grpSimulacija, SWT.NONE);
		lblUkupnoSim.setText("Ukupno");
		lblUkupnoSim.setBounds(10, 167, 55, 15);
		
		txtUkupnoSim = new Text(grpSimulacija, SWT.BORDER | SWT.READ_ONLY);
		txtUkupnoSim.setBounds(87, 161, 76, 21);
		
		Label lblKws1 = new Label(grpSimulacija, SWT.NONE);
		lblKws1.setText("W/s");
		lblKws1.setBounds(169, 50, 27, 15);
		
		Label lblKws2 = new Label(grpSimulacija, SWT.NONE);
		lblKws2.setText("W/s");
		lblKws2.setBounds(169, 78, 27, 15);
		
		Label lblKws3 = new Label(grpSimulacija, SWT.NONE);
		lblKws3.setText("W/s");
		lblKws3.setBounds(169, 109, 27, 15);
		
		Label lblKws4 = new Label(grpSimulacija, SWT.NONE);
		lblKws4.setText("W/s");
		lblKws4.setBounds(169, 136, 27, 15);
		
		Label lblKws5 = new Label(grpSimulacija, SWT.NONE);
		lblKws5.setText("W/s");
		lblKws5.setBounds(169, 164, 27, 15);
		
		Label lblVrijeme = new Label(grpSimulacija, SWT.NONE);
		lblVrijeme.setText("Vrijeme");
		lblVrijeme.setBounds(10, 23, 55, 15);
		
		txtVrijemSim = new Text(grpSimulacija, SWT.BORDER | SWT.READ_ONLY);
		txtVrijemSim.setBounds(87, 20, 76, 21);
		
		Label lblS = new Label(grpSimulacija, SWT.NONE);
		lblS.setText("s");
		lblS.setBounds(169, 23, 27, 15);
		
		Button btnSpremiRezultate = new Button(shlGlavni, SWT.NONE);
		btnSpremiRezultate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox msge = new MessageBox(shlGlavni, SWT.ERROR);
				MessageBox msg = new MessageBox(shlGlavni, SWT.OK);
				
				if(provjera != 1){
					
					msge.setMessage("SPREMANJE NIJE USPJELO");
					msge.setText("GREŠKA!");
					msge.open(); 
				}
				if(provjera == 1){
					
					ModelPot model = new ModelPot();
					
					model.setSnagaTrosilo1(Double.valueOf(txtTrosilo1.getText()));
					model.setSnagaTrosilo2(Double.valueOf(txtTrosilo2.getText()));
					model.setSnagaTrosilo3(Double.valueOf(txtTrosilo3.getText()));
					model.setSnagaTrosilo4(Double.valueOf(txtTrosilo4.getText()));
					model.setRezTrosilo1(Double.valueOf(txtTrosilo1Sim.getText()));
					model.setRezTrosilo2(Double.valueOf(txtTrosilo2Sim.getText()));
					model.setRezTrosilo3(Double.valueOf(txtTrosilo3Sim.getText()));
					model.setRezTrosilo4(Double.valueOf(txtTrosilo4Sim.getText()));
					model.setRezUkupno(Double.valueOf(txtUkupnoSim.getText()));
					model.setVrijemeSim(Integer.valueOf(txtVrijeme.getText()));
					
					lista.add(model);
					spremi();
					
					msg.setMessage("SPREMANJE USPJELO");
					msg.setText("INFO");
					msg.open();
					
				}
			}
		});
		btnSpremiRezultate.setBounds(10, 438, 137, 25);
		btnSpremiRezultate.setText("Spremi rezultate");
		
		Button btnReset = new Button(shlGlavni, SWT.NONE);
		btnReset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				txtTrosilo1.setText("");
				txtTrosilo2.setText("");
				txtTrosilo3.setText("");
				txtTrosilo4.setText("");
				txtVrijeme.setText("");
				
				txtTrosilo1Sim.setText("");
				txtTrosilo2Sim.setText("");
				txtTrosilo3Sim.setText("");
				txtTrosilo4Sim.setText("");
				txtVrijemSim.setText("");
				txtUkupnoSim.setText("");
				
				potrosnjaT1 = 0;
				potrosnjaT2 = 0;
				potrosnjaT3 = 0;
				potrosnjaT4 = 0;
				
				ukupnaPotrosnja = 0;
				brojacTimer = 0;
				provjera = 0;
				
				
			}
		});
		btnReset.setBounds(10, 469, 137, 25);
		btnReset.setText("Reset");
		
		Button btnIzlaz = new Button(shlGlavni, SWT.NONE);
		btnIzlaz.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlGlavni.close();
			}
		});
		btnIzlaz.setBounds(10, 531, 137, 25);
		btnIzlaz.setText("Izlaz");
		
		Button btnUcitaj = new Button(shlGlavni, SWT.NONE);
		btnUcitaj.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox msge = new MessageBox(shlGlavni, SWT.ERROR);
				
				ucitaj();
				
				if(lista.isEmpty()){
					
					msge.setMessage("LISTA JE PRAZNA!");
					msge.setText("GRESKA!");
					msge.open();
				}
				else{
					
					
					
					String ispis = "";
					
					
					for(int index = 0; index < lista.size(); index++){
						
						ispis = ispis + "SIM: " + (index+1) +  "\n" + "VRIJEME SIM: " + lista.get(index).getVrijemeSim() + "s" + "\n---" + "\n" + "SNAGE:" + "\n" + 
								"TROSILO 1: " + lista.get(index).getSnagaTrosilo1() + "W" + "\n" + "TROSILO 2: " + lista.get(index).getSnagaTrosilo2() + "W" + "\n" +
								"TROSILO 3: " + lista.get(index).getSnagaTrosilo3() + "W" + "\n" + "TROSILO 4: " + lista.get(index).getSnagaTrosilo4() + "W" + "\n---" + "\n" + 
								"POTROSNJE:" + "\n" +
								"TROSILO 1: " + lista.get(index).getRezTrosilo1() + "W/s" + "\n" + "TROSILO 2: " + lista.get(index).getRezTrosilo2() + "W/s" + "\n" +
								"TROSILO 3: " + lista.get(index).getRezTrosilo3() + "W/s" + "\n" + "TROSILO 4: " + lista.get(index).getRezTrosilo4() + "W/s" + "\n---" +  "\n" + 
								"UKUPNO: " + lista.get(index).getRezUkupno() + "W/s" + "\n============================" + "\n\n";
					}
					
					txtIspis.setText(ispis);
					
				}
							
				
				
				
			}
		});
		btnUcitaj.setBounds(10, 500, 137, 25);
		btnUcitaj.setText("Ucitaj prijasnje rezultate");
		
		txtIspis = new Text(shlGlavni, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL);
		txtIspis.setBounds(234, 10, 277, 515);
		
		Button btnOcistiListu = new Button(shlGlavni, SWT.NONE);
		btnOcistiListu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox msg = new MessageBox(shlGlavni, SWT.OK);
				MessageBox msge = new MessageBox(shlGlavni, SWT.ERROR);
				
				ucitaj();
				
				if(lista.isEmpty()){
					
					msge.setMessage("LISTA JE PRAZNA!");
					msge.setText("GRESKA!");
					msge.open();
					
				}
				else{
					
					MessageDialog dialog = new MessageDialog(shlGlavni, "IZBRISI SVE REZULTATE", null,
						    "JESI LI SIGURAN?", MessageDialog.QUESTION, new String[] { "IZBRISI", "ODUSTANI" }, 0);
						int result = dialog.open();
						
						if(result == 0){
							
							txtIspis.setText("");
							lista.clear();
							spremi();
							msg.setMessage("REZULTATI SU IZBRISANI!");
							msg.setText("INFO");
							msg.open();
						}
						if(result == 1){
							
							dialog.close();
						}
					
				}

			}
		});
		btnOcistiListu.setBounds(234, 531, 110, 25);
		btnOcistiListu.setText("Izbrisi sve rezultate");

	}
	
	private static void doUpdate(final Display display, final Text target, final String value) {
	    display.asyncExec(new Runnable() {
	      @Override
	      public void run() {
	        if (!target.isDisposed()) {
	          target.setText(value);
	          target.getParent().layout();
	        }
	      }
	    });
	  }
	
	public static int vrijemeSimulacije(){
		
		int vrijemeSim = 0;
		
		vrijemeSim = Integer.valueOf(txtVrijeme.getText());
		
		return vrijemeSim;
	}
	
	
	
	//SPREMANJE U DATOTEKU
		public void spremi () {
			try {
					FileOutputStream upisUDatoteku = new FileOutputStream("rezultati.dat"); 
					ObjectOutputStream upisObjekta = new ObjectOutputStream(upisUDatoteku);
					upisObjekta.writeObject (lista);
					upisObjekta.close();
				
			}
			
			catch (IOException e){
				
					e.printStackTrace();
				}
		}
		
		//CITANJE IZ DATOTEKE
		public void ucitaj () {
			try {
				FileInputStream citanjeDatoteke = new FileInputStream("rezultati.dat");
				
				if (citanjeDatoteke.available() > 0) {
					
					ObjectInputStream citajObjekt = new ObjectInputStream (citanjeDatoteke);
					lista = (ArrayList<ModelPot>) citajObjekt.readObject(); 
					citajObjekt.close();
					
					
					
				}
			}
			
			catch (ClassNotFoundException e){
				
				e.printStackTrace();
				
			}
			
			catch (IOException e){
				e.printStackTrace();
				}
		}
}
