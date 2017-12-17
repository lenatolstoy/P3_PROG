/**
 * Practica 3. Classe login.
 *
 *
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Usuari;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.MouseInfo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private int x;
	private int y;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point point = MouseInfo.getPointerInfo().getLocation();
				setLocation(point.x - x, point.y - y);
			}
		});
		setTitle("Login");
		setUndecorated(true);

		setResizable(false);
		setBackground(new Color(138, 43, 226));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Acceder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(50, 120, 250));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Usuari soci_frame = new Usuari();
				soci_frame.nombre = textField.getText();
				soci_frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(145, 130, 270, 48);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		textField.setBackground(new Color(230, 230, 230));
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textField.setBounds(145, 74, 270, 45);
		contentPane.add(textField);
		textField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(40, 102, 204));
		panel.setBounds(0, 0, 123, 200);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 29, 123, 146);
		panel.add(lblNewLabel_1);
		// ImageIcon user_p = new ImageIcon(getClass().getResource("16480.png"));
		// Icon icono = new
		// ImageIcon(user_p.getImage().getScaledInstance(lblNewLabel_1.getWidth(),
		// lblNewLabel_1.getHeight(), Image.SCALE_DEFAULT));
		// lblNewLabel_1.setIcon(icono);
		this.repaint();
		btnNewButton_1 = new JButton("X");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(220, 220, 220));
		btnNewButton_1.setForeground(new Color(220, 20, 60));
		btnNewButton_1.setBounds(422, 0, 28, 23);
		contentPane.add(btnNewButton_1);

		lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(145, 22, 56, 36);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
	}
}
