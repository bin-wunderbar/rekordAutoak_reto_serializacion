package administracion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import clases.Vehiculo;
import edicion.EditarVehiculo;
import funciones.Archivos;
import funciones.Salir;
import navegacion.CrearOrdenPrim;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 *
 */
public class AdministrarVehiculos extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	public static DefaultListModel<Vehiculo> dlmVehiculos;
	private JList<Vehiculo> lstVehiculos;

	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnAgregar;

	private Vehiculo vehiculo;

	public AdministrarVehiculos()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar vehiculos");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lstVehiculos = new JList<Vehiculo>();
		lstVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 14));
		btnVolver.setBounds(10, 445, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar material");
		btnAgregar.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnAgregar.setBounds(62, 60, 269, 62);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar material");
		btnEditar.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnEditar.setBounds(380, 60, 269, 62);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollVehiculos = new JScrollPane();
		scrollVehiculos.setBounds(62, 159, 584, 203);
		panelPrincipal.add(scrollVehiculos);

		scrollVehiculos.setViewportView(lstVehiculos);
		
		// ===== modelos =====
		// --- crear ---
		dlmVehiculos = new DefaultListModel<Vehiculo>();
		dlmVehiculos.addAll(Archivos.cargarTodosVehiculos());

		// --- asignar ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		lstVehiculos.setModel(dlmVehiculos);

		// ===== Listeners =====
		// --- Window ---
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
			EditarVehiculo ev = new EditarVehiculo();
			ev.setLocationRelativeTo(null);
			ev.setVisible(true);
		}
		else if (o == btnEditar)
		{
			if (lstVehiculos.getSelectedIndex() >= 0)
			{
				vehiculo = lstVehiculos.getSelectedValue();

				EditarVehiculo ev = new EditarVehiculo();
				ev.modoEdicion(vehiculo);
			
				ev.setLocationRelativeTo(null);
				ev.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ningún vehículo seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnVolver)
		{
			CrearOrdenPrim cop = new CrearOrdenPrim();
			cop.setLocationRelativeTo(null);
			cop.setVisible(true);
			
			this.dispose();
		} 
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		Salir.siNo();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// comportamiento por defecto
	}
}