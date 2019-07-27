/**
 * 
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

@SuppressWarnings("serial")
public class GUIMainImp extends GUIMain {
	/** 
	 * (sin Javadoc)
	 * @see GUIMain#update(int event, Object data)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static String[] module = (
			  "Paises\n"
			+ "Marcas\n"
			+ "Productos\n"
			+ "Ventas\n"
			+ "Clientes"
			).split("\\n");
	protected int numModule = module.length;
	
	public GUIMainImp(){
		super();
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Fruteria Mr. Pressman");
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(new JLabel("Gestion de Fruteria"), BorderLayout.PAGE_START);;
		
			JPanel centerPanel = new JPanel(new GridLayout(numModule, 1));
			for (int i = 0; i < numModule; ++i){
				final int j = i;
				JButton button = new JButton(module[j]);
				button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						switch (module[j]) {
						case "Paises": Controlador.getInstance().accion(Events.CREAR_GUI_PAIS, null); break;
						case "Marcas": Controlador.getInstance().accion(Events.CREAR_GUI_MARCA, null); break;
						case "Productos": Controlador.getInstance().accion(Events.CREAR_GUI_PRODUCTO, null); break;
						case "Ventas": Controlador.getInstance().accion(Events.CREAR_GUI_VENTA, null); break;
						case "Clientes": Controlador.getInstance().accion(Events.CREAR_GUI_CLIENTE, null); break;
						}
					}
					
				});
				centerPanel.add(button);
			}
			mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowDim = new Dimension((int) (Main.WINDOW_DIM.getWidth()), (int) (Main.WINDOW_DIM.getHeight() * (module.length + 1)));
		this.setBounds(
				(int) (dim.getWidth() / 2 - windowDim.getWidth() / 2),
				(int) (dim.getHeight() / 2 - windowDim.getHeight() / 2),
				(int) (windowDim.getWidth()),
				(int) (windowDim.getHeight())
				);
		this.setVisible(true);
	}
	
	
}