package ar.edu.unlu.diezmil.vista.grafica;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ar.edu.unlu.diezmil.control.Controlador;
import ar.edu.unlu.diezmil.control.IVista;
import ar.edu.unlu.diezmil.modelo.Cara;
import ar.edu.unlu.diezmil.modelo.IJugador;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;

import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.ListModel;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class VistaGrafica implements IVista {
	private Controlador miControlador;
	private JFrame frame;
	private JPanel pPrincipal;
	private final String vPrincipal = "vPrincipal";
	private final String sJugando = "JUEGO EN CURSO";
	private final ImageIcon iTitulo = new ImageIcon(VistaGrafica.class.getResource("/galeria/titulo.png"));
	private final ImageIcon iPuntuacion = new ImageIcon(VistaGrafica.class.getResource("/galeria/puntaje.png"));
	private final ImageIcon iFondo = new ImageIcon(VistaGrafica.class.getResource("/galeria/fondo.png"));
	private final ImageIcon iDado1 = new ImageIcon(VistaGrafica.class.getResource("/galeria/adado1.png"));
	private final ImageIcon iDado2 = new ImageIcon(VistaGrafica.class.getResource("/galeria/adado2.png"));
	private final ImageIcon iDado3 = new ImageIcon(VistaGrafica.class.getResource("/galeria/adado3.png"));
	private final ImageIcon iDado4 = new ImageIcon(VistaGrafica.class.getResource("/galeria/adado4.png"));
	private final ImageIcon iDado5 = new ImageIcon(VistaGrafica.class.getResource("/galeria/adado5.png"));
	private final ImageIcon iDado6 = new ImageIcon(VistaGrafica.class.getResource("/galeria/adado6.png"));
	private final JButton btnIniciar = new JButton("Iniciar Partida");
	private final JButton btnAddJug = new JButton("Agregar Jugador");
	private final JButton btnTirar = new JButton("Tirar"); 
	private final JButton btnSumar = new JButton("Sumar Puntos");
	private final JButton btnSalir = new JButton("Salir");
	private final JButton btnGanadores = new JButton("Historial Ganadores");
	private JLabel lblPuntuacion;
	private JLabel lblTitJugando;
	private JLabel dado1;
	private JLabel dado2;
	private JLabel dado3;
	private JLabel dado4;
	private JLabel dado5;
	private JList listJug = new JList();
	
	public VistaGrafica() {
		// Se arma la estructura de panel que comparten todos los menues
		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(10, 10, 900, 600);
		this.frame.setLocationRelativeTo(null);
		CardLayout cardLayout = new CardLayout();
		Container contentPane = this.frame.getContentPane();
		contentPane.setLayout(cardLayout);
		contentPane.add(vPrincipal(), vPrincipal);
		
		// Puntuacion
		lblPuntuacion = new JLabel();
		lblPuntuacion.setIcon(iPuntuacion);
		lblPuntuacion.setBounds(333, 145, 567, 312);
		pPrincipal.add(lblPuntuacion);
		
		// Fondo
		JLabel lblFondo = new JLabel();
		lblFondo.setIcon(iFondo);
		lblFondo.setBounds(0, 0, 900, 600);
		pPrincipal.add(lblFondo);
		
		
		frame.setVisible(false);
	}	

	private JPanel vPrincipal() {
		// Panel de la pantalla de inicio
		pPrincipal = new JPanel();
		frame.getContentPane().add(pPrincipal, "name_2844592874195");
		pPrincipal.setLayout(null);
		
		// Boton Agregar Jugador 
		btnAddJug.setBounds(352, 493, 145, 39);
		btnAddJug.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pPrincipal.add(btnAddJug);
		btnAddJug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarJugador();
			}
		});
		
		btnSalir.setBounds(700, 493,145, 39);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pPrincipal.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        int result = JOptionPane.showConfirmDialog(frame, "¿Desea guardar la partida?");
		        if (result == 0)
		        	guardar();
		        else if (result == 1)
		        	System.exit(0);
		       // Si no es 0 o 1, presiono cancelar, no se hace nada		        		
			}
		});
		btnGanadores.setBounds(94, 493,163, 39);
		btnGanadores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pPrincipal.add(btnGanadores);
		btnGanadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<IJugador> ganadores= miControlador.getGanadores();
				int i = 1;
				String msj = "Ultimos Ganadores:";
				for(IJugador g : ganadores) {
					msj = msj + "\n" + i + ": " + g.getNombre();	
					i++;
				}
				JOptionPane.showMessageDialog(frame, msj);
			}
		});
		
		
		// COMPONENTES DE JUEGO EN CURSO
		// Titulo jugando
		lblTitJugando = new JLabel();
		lblTitJugando.setBounds(345, 212, 500, 47);
		lblTitJugando.setFont(new Font("Tahoma", Font.PLAIN, 26));
		pPrincipal.add(lblTitJugando);
		
		// Titulo
		JLabel jlTitulo = new JLabel();
		jlTitulo.setIcon(iTitulo);
		jlTitulo.setBounds(138, 0, 642, 169);
		pPrincipal.add(jlTitulo);
		listJug.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		listJug.setBorder(new TitledBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), 
										   "Lista de Jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), 
							               "Lista de Jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//Lista de Jugadores
		listJug.setVisibleRowCount(11);
		listJug.setBackground(SystemColor.control);
		listJug.setBounds(34, 171, 265, 300);
		pPrincipal.add(listJug);
		
		//Boton Iniciar Partida 
		btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIniciar.setBounds(529, 493, 145, 39);
		pPrincipal.add(btnIniciar);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miControlador.IniciarPartida();
			}
		});	
		
		//Boton Tirar 
		btnTirar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTirar.setBounds(352, 493, 145, 39);
		pPrincipal.add(btnTirar);
		btnTirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSumar.setEnabled(true);
				miControlador.tirar();
			}
		});				
		
		//Boton Sumar
		btnSumar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSumar.setBounds(529, 493, 145, 39);
		pPrincipal.add(btnSumar);
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miControlador.sumarPuntos();
			}
		});	
		
		// Dados:
		dado1 = new JLabel();
		dado1.setIcon(iDado1);
		dado1.setVisible(false);
		dado1.setBounds(364, 310, 85, 67);
		pPrincipal.add(dado1);
		dado2 = new JLabel();
		dado2.setIcon(iDado1);
		dado2.setBounds(443, 310, 85, 67);
		dado2.setVisible(false);
		pPrincipal.add(dado2);
		dado3 = new JLabel();
		dado3.setIcon(iDado1);
		dado3.setBounds(522, 310, 85, 67);
		dado3.setVisible(false);
		pPrincipal.add(dado3);
		dado4 = new JLabel();
		dado4.setIcon(iDado1);
		dado4.setBounds(601, 310, 85, 67);
		dado4.setVisible(false);
		pPrincipal.add(dado4);
		dado5 = new JLabel();
		dado5.setIcon(iDado1);
		dado5.setBounds(680, 310, 85, 67);
		dado5.setVisible(false);
		pPrincipal.add(dado5);
		
		this.frame.setVisible(true);
		return pPrincipal;
	}

	private void agregarJugador() {
		String name ="";
		while ( (!(name == null)) && ((name.isBlank()) || (name.isEmpty())) ) {
			name = JOptionPane.showInputDialog(frame, "Ingrese nombre del nuevo jugador:");
		}
		if (!(name == null)) {
			miControlador.agregarJugador(name);	
		}
	}

	@Override
	public void mostrarJugadores() { 	
		
		String[] values = new String[] {"","","","","","","","","",""};
		listJug.setModel((ListModel) new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		ArrayList<IJugador> misJugadores = miControlador.getJugadores();
		if (misJugadores.isEmpty()) {
			values[1] = "- V A C I A -";		
		} else {
			int i = 2;
			for(IJugador j : misJugadores) {
				values[i] = ( j.getNombre().toUpperCase() + "  -  Puntos: "+ j.getPuntos()+ "\n");
				i++;
			}	
		}		
	}

	@Override
	public void mostrarTirada() {		
		//Oculto todos los dados para que solo muestre la cantidad que tire
		dado1.setVisible(false);
		dado2.setVisible(false);
		dado3.setVisible(false);
		dado4.setVisible(false);
		dado5.setVisible(false);
		
		// Obtengo los dados y los muestro
		Cara[] tirada = miControlador.getDados();
		int i = 1;
		for(Cara c: tirada) {
			mostrarDado(i,c);
			i++;
		}		
	}

	private void mostrarDado(int i, Cara c) {
		ImageIcon dado = new ImageIcon();
		switch (c) {
		case UNO:
			dado = iDado1;
			break;
		case DOS:
			dado = iDado2;
			break;	
		case TRES:
			dado = iDado3;
			break;
		case CUATRO:
			dado = iDado4;
			break;
		case CINCO:
			dado = iDado5;
			break;
		case SEIS:
			dado = iDado6;
			break;
		}
		
		switch (i) {
		case 1:
			dado1.setIcon(dado);
			dado1.setVisible(true);
			break;
		case 2:
			dado2.setIcon(dado);
			dado2.setVisible(true);
			break;	
		case 3:
			dado3.setIcon(dado);
			dado3.setVisible(true);
			break;
		case 4:
			dado4.setIcon(dado);
			dado4.setVisible(true);
			break;
		case 5:
			dado5.setIcon(dado);
			dado5.setVisible(true);
			break;
		}	
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.miControlador = controlador;
	}

	@Override
	public void mostrarJugando() {
		lblPuntuacion.setVisible(false);
		btnAddJug.setVisible(false);
		btnIniciar.setVisible(false);
		
		lblTitJugando.setText(sJugando + " - El es turno de " + miControlador.getJugadorActual().toUpperCase());
		lblTitJugando.setVisible(true);
		btnTirar.setVisible(true);
		btnSumar.setVisible(true);
		btnSumar.setEnabled(false);	
		
		dado1.setVisible(false);
		dado2.setVisible(false);
		dado3.setVisible(false);
		dado4.setVisible(false);
		dado5.setVisible(false);
	}

	@Override
	public void mostrarConfiguracion() {
		mostrarJugadores();
		lblPuntuacion.setVisible(true);
		btnIniciar.setVisible(true);
		btnAddJug.setVisible(true);
		btnSumar.setVisible(false); 
		btnTirar.setVisible(false);
		lblTitJugando.setVisible(false);
		frame.setVisible(true);	
	}

	@Override
	public void mostrarFinal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarGanador() {
		String msj = "FINALIZO EL JUEGO. El ganador es: "+ miControlador.getGanador().toUpperCase();
		JOptionPane.showMessageDialog(frame, msj);
	}

	@Override
	public void mostrarFaltanJugadores() {
		JOptionPane.showMessageDialog(frame, "Debe ingresar al menos dos jugadores antes de comenzar el Juego");
		
	}

	@Override
	public void mostarPerdio() {
		String msj = ( miControlador.getJugadorActual().toUpperCase() + " Perdio su turno");
		JOptionPane.showMessageDialog(frame, msj);			
	}

	@Override
	public void mostrarContinua() {
		String msj = ("Continua su turno."
						+ "\nPuntos ultima tirada: " +  miControlador.getPuntosTirada()
						+ "\nPuntos acumulados: " +  miControlador.getPuntosParciales()
						+ "\nDados disponibles: " +  miControlador.getDadoDisponibles());
		JOptionPane.showMessageDialog(frame, msj);	
		btnSumar.setEnabled(true);	
	}
	

	public void guardar() { 
		miControlador.guardar();
	}
}
