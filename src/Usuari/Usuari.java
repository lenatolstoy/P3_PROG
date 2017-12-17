/** Practica 3. Classe Biblioteca.
 * 
 * 
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Usuari;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Dades.*;
import Exceptions.*;

public class Usuari extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String nombre;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Usuari() {
		setResizable(false);
		BibliotecaUsuari biblio = new BibliotecaUsuari();
		biblio.llegir();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 634, 35);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(72, 11, 178, 14);
		panel.add(lblNewLabel);

		JLabel lblUsuari = new JLabel("USUARI:");
		lblUsuari.setBounds(10, 11, 46, 14);
		panel.add(lblUsuari);
		JLabel lblNewLabel_1 = new JLabel("N");
		lblNewLabel_1.setBounds(231, 11, 75, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("numero");
		lblNewLabel_2.setBounds(316, 11, 46, 14);
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(null);
		panel_1.setBounds(0, 35, 634, 226);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

		tabbedPane.setAlignmentY(Component.TOP_ALIGNMENT);
		tabbedPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setFocusable(false);
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 634, 226);
		panel_1.add(tabbedPane);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_2.setBorder(null);
		tabbedPane.addTab("Consultar libro", null, panel_2, null);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 523, 37);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblTitol = new JLabel("TITULO DEL LIBRO :");
		lblTitol.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitol.setBounds(10, 10, 126, 14);
		panel_4.add(lblTitol);

		textField = new JTextField();
		textField.setBounds(151, 8, 236, 20);
		panel_4.add(textField);
		textField.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");

		btnConsultar.setBounds(397, 8, 89, 23);
		panel_4.add(btnConsultar);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 35, 523, 186);
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		JList list = new JList();

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Llibre aux = (Llibre) list.getSelectedValue();
				if (biblio.accedir(nombre)) {
					int seleccion = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Selector de opciones",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Reservar", "Préstec" }, // null para YES, NO y CANCEL
							"opcion 1");

					if (seleccion == 0) {
						String data = JOptionPane.showInputDialog(null, list.getSelectedValue() + "\nUsuario: " + nombre
								+ "\nDia de la teva reserva: (dd-MM-yyyy)");
						SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date fecha = null;
						try {
							fecha = formato.parse(data);
						} catch (Exception e) {

						}

						int result = JOptionPane.showConfirmDialog(null, "Confirmar reserva el dia " + fecha);

						if (result == JOptionPane.YES_OPTION) {
							Reserva reserva = new Reserva(aux.getCodi(), nombre, fecha);
							try {
								biblio.ferReserva(reserva);

							} catch (ClassNotFoundException | IOException e) {
								e.printStackTrace();
							} catch (LlibreNoDisponible e) {
								JOptionPane.showMessageDialog(null, "El llibre no està disponible per aquesta data",
										"AVÍS", JOptionPane.ERROR_MESSAGE);
							} catch (ReservesDiaSuperior30 e) {
								JOptionPane.showMessageDialog(null, "Maxim de reserves per aquest dia", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							}

						}

					}

					if (seleccion == 1) {

						String data = JOptionPane.showInputDialog(null, list.getSelectedValue() + "\nUsuario: " + nombre
								+ "\nDia del teu préstec: (dd/MM/yyyy)");
						int result = JOptionPane.showConfirmDialog(null, "Confirmar prestec el dia: " + data);

						if (result == JOptionPane.YES_OPTION)
							try {
								biblio.ferPrestec(aux.getCodi(), nombre, data);
							} catch (LlibreNoTrobat e) {
								e.printStackTrace();
							} catch (SociInexistent e) {
								e.printStackTrace();
							} catch (MaximPrestecs e) {
								JOptionPane.showMessageDialog(null, "Ha assolit el nombre maxim de prestecs", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							} catch (PrestecJaExisteix e) {
								JOptionPane.showMessageDialog(null, "Ja te aquest llibre en prestec actualment", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							} catch (LlibreNoDisponible e) {
								JOptionPane.showMessageDialog(null,
										"Per la data indicada el llibre no es  troba en prestec", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							}

					}

				} else {
					int seleccion = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Selector de opciones",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Reservar" }, // null para YES, NO y CANCEL
							"opcion 1");
					if (seleccion == 0) {
						String data = JOptionPane.showInputDialog(null, list.getSelectedValue() + "\nUsuario: " + nombre
								+ "\nDia del teu préstec (dd-MM-yyyy):");
						SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date fecha = null;
						try {
							fecha = formato.parse(data);
						} catch (Exception e) {

						}

						int result = JOptionPane.showConfirmDialog(null, "Confirmar prestec el dia: " + data);
						if (result == JOptionPane.YES_OPTION) {
							Reserva reserva = new Reserva(aux.getCodi(), nombre, fecha);
							try {
								biblio.ferReserva(reserva);
							} catch (ClassNotFoundException | IOException e) {
								e.printStackTrace();
							} catch (LlibreNoDisponible e) {
								JOptionPane.showMessageDialog(null, "El llibre no està disponible per aquesta data",
										"AVÍS", JOptionPane.ERROR_MESSAGE);
							} catch (ReservesDiaSuperior30 e) {
								JOptionPane.showMessageDialog(null, "Maxim de reserves per aquest dia", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					}

				}
			}

		});
		list.setBounds(10, 11, 477, 164);
		panel_6.add(list);

		btnConsultar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				DefaultListModel modelo = new DefaultListModel();
				if (textField.getText().length() == 0) {
					for (int i = 0; i < biblio.getLlibres().getNumllibres(); i++) {
						modelo.addElement(biblio.getLlibres().getLlistallibres()[i]);
					}
				} else {
					Llibre[] aux = biblio.consultaLlibresPerTitol(textField.getText());
					int i = 0;
					while (i < aux.length && aux[i] != null) {
						modelo.addElement(aux[i].Duplicat());
						list.setForeground(Color.blue);
						i++;
					}

				}
				list.setModel(modelo);
			}
		});
		tabbedPane.setBackgroundAt(0, Color.WHITE);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(null);
		tabbedPane.addTab("Consultar tematica", null, panel_3, null);
		panel_3.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 523, 34);
		panel_3.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblConsultarTematica = new JLabel("CONSULTAR TEMATICA");
		lblConsultarTematica.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConsultarTematica.setBounds(10, 11, 161, 14);
		panel_5.add(lblConsultarTematica);

		textField_1 = new JTextField();
		textField_1.setBounds(166, 9, 204, 20);
		panel_5.add(textField_1);
		textField_1.setColumns(10);

		JList list_1 = new JList();
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Llibre aux = (Llibre) list_1.getSelectedValue();

				if (biblio.accedir(nombre)) {
					int seleccion = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Selector de opciones",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Reservar", "Préstec" }, // null para YES, NO y CANCEL
							"opcion 1");

					if (seleccion == 0) {
						String data = JOptionPane.showInputDialog(null, list_1.getSelectedValue() + "\nUsuario: "
								+ nombre + "\nDia de la teva reserva: (dd-MM-yyyy)");
						SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date fecha = null;
						try {
							fecha = formato.parse(data);
						} catch (Exception e) {

						}

						Reserva reserva = new Reserva(aux.getCodi(), nombre, fecha);
						try {
							biblio.ferReserva(reserva);

						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						} catch (LlibreNoDisponible e) {
							JOptionPane.showMessageDialog(null, "El llibre no està disponible per aquesta data", "AVÍS",
									JOptionPane.ERROR_MESSAGE);
						} catch (ReservesDiaSuperior30 e) {
							JOptionPane.showMessageDialog(null, "Maxim de reserves per aquest dia", "AVÍS",
									JOptionPane.ERROR_MESSAGE);
						}

					}

					if (seleccion == 1) {
						String data = JOptionPane.showInputDialog(null,
								list_1.getSelectedValue() + "\nUsuario: " + nombre + "\nDia del teu préstec:");
						Prestec prestec = new Prestec(aux.getCodi(), nombre, data);
						int result = JOptionPane.showConfirmDialog(null, "Confirmar prestec el dia: " + data);

						if (result == JOptionPane.YES_OPTION) {
							try {
								biblio.ferPrestec(aux.getCodi(), nombre, data);
							} catch (LlibreNoTrobat e) {
								e.printStackTrace();
							} catch (SociInexistent e) {
								e.printStackTrace();
							} catch (MaximPrestecs e) {
								JOptionPane.showMessageDialog(null, "Ha assolit el nombre maxim de prestecs", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							} catch (PrestecJaExisteix e) {
								JOptionPane.showMessageDialog(null, "Ja te aquest llibre en prestec actualment", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							} catch (LlibreNoDisponible e) {
								JOptionPane.showMessageDialog(null,
										"Per la data indicada el llibre no es  troba en prestec", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}

				} else {
					int seleccion = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Selector de opciones",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Reservar" }, // null para YES, NO y CANCEL
							"opcion 1");

					if (seleccion == 0) {
						String data = JOptionPane.showInputDialog(null,
								list_1.getSelectedValue() + "\nUsuario: " + nombre + "\nDia del teu préstec:");
						SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date fecha = null;
						try {
							fecha = formato.parse(data);
						} catch (Exception e) {

						}
						int result = JOptionPane.showConfirmDialog(null, "Confirmar prestec el dia: " + data);
						if (result == JOptionPane.YES_OPTION) {
							Reserva reserva = new Reserva(aux.getCodi(), nombre, fecha);
							try {
								biblio.ferReserva(reserva);

							} catch (ClassNotFoundException | IOException e) {
								e.printStackTrace();
							} catch (LlibreNoDisponible e) {
								JOptionPane.showMessageDialog(null, "El llibre no està disponible per aquesta data",
										"AVÍS", JOptionPane.ERROR_MESSAGE);
							} catch (ReservesDiaSuperior30 e) {
								JOptionPane.showMessageDialog(null, "Maxim de reserves per aquest dia", "AVÍS",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}

			}
		});
		list_1.setBounds(10, 11, 476, 167);
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 32, 523, 189);
		panel_3.add(panel_7);
		panel_7.setLayout(null);
		panel_7.add(list_1);

		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel modelo2 = new DefaultListModel();
				if (textField_1.getText().length() == 0) {
					for (int i = 0; i < biblio.getLlibres().getNumllibres(); i++) {
						modelo2.addElement(biblio.getLlibres().getLlistallibres()[i]);

					}
				} else {
					Llibre[] aux = biblio.consultaLlibresPerTema(textField_1.getText());

					int i = 0;
					while (i < aux.length && aux[i] != null) {
						modelo2.addElement(aux[i].Duplicat());
						list_1.setForeground(Color.blue);
						i++;
					}

				}
				list_1.setModel(modelo2);
			}
		});
		btnBuscar.setBounds(390, 8, 89, 23);
		panel_5.add(btnBuscar);

		JPanel panel_8 = new JPanel();

		tabbedPane.addTab("Els teus prestecs", null, panel_8, null);
		panel_8.setLayout(null);

		panel_8.setVisible(true);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(29, 65, 436, 103);
		panel_8.add(scroll);

		String titulos[] = { "Codigo", "Titulo", "Fecha" };

		String[][] datos = new String[5][3];

		JTable table = new JTable(datos, titulos);

		table.setEnabled(true);
		scroll.setViewportView(table);

		JLabel lblLlistaDelsTeus = new JLabel("Llista dels teus prestecs:");
		lblLlistaDelsTeus.setBounds(29, 23, 255, 14);
		panel_8.add(lblLlistaDelsTeus);

		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tabbedPane.getSelectedIndex() == 2) {
					Prestec[] n = biblio.consultarPrestecs(nombre);
					int i = 0;
					if (n != null) {
						while (i < 5 && n[i] != null) {
							Llibre aux = biblio.getLlibres().retornaLlibre(n[i].getId_llibre()).Duplicat();
							datos[i][0] = aux.getCodi();
							datos[i][1] = aux.getTitol();
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							String data = String.valueOf(dateFormat.format(n[i].getData_ini()));
							datos[i][2] = data;
							i++;
						}

						JTable tabla3 = new JTable(datos, titulos);
						tabla3.setEnabled(true);
						scroll.setViewportView(tabla3);

					} else {
						for (int j = 0; j < 5; j++) {
							datos[i][0] = null;
							datos[i][1] = null;
							datos[i][2] = null;

						}

					}
				}

			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if (biblio.accedir(nombre)) {
					lblNewLabel.setText(nombre);
					lblNewLabel_1.setText("N\u00BA Prestecs: ");
					String n = String.valueOf(biblio.getSocis().retornaSoci(nombre).getNum_prestecs());
					lblNewLabel_2.setText(n);

				} else {
					lblNewLabel.setText("No ets soci");
					lblNewLabel_1.setText("");
					lblNewLabel_2.setText("");
					tabbedPane.setEnabledAt(2, false);
				}

			}
		});

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				int result = JOptionPane.showConfirmDialog(null, "Quieres guardar los datos antes de cerrar? ");
				if (result == JOptionPane.YES_OPTION) {
					biblio.guardar();
				}
			}
		});
	}

}
