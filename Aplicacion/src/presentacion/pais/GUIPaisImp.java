/**
 * 
 */
package presentacion.pais;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import main.Main;
import negocio.pais.TPais;
import presentacion.Controlador;
import presentacion.Events;
import presentacion.GUIMainImp;
import presentacion.GUIMensaje;

@SuppressWarnings("serial")
public class GUIPaisImp extends GUIPais {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIAltaPais gUIAltaPais;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBuscarPais gUIBuscarPais;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIListarPaises gUIListarPaises;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIActualizarPais gUIActualizarPais;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBajaPais gUIBajaPais;

	/** 
	 * (sin Javadoc)
	 * @see GUIPais#update(int event, Object res)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected String[] module = (
			  "Alta\n"
			+ "Buscar\n"
			+ "Listar\n"
			+ "Actualizar\n"
			+ "Baja"
			).split("\\n");
	protected int numModule = module.length;
	@SuppressWarnings("rawtypes")
	protected JComboBox comboBox;
	
	public GUIPaisImp(){
		super();
		gUIAltaPais = new GUIAltaPais();
		gUIBuscarPais = new GUIBuscarPais();
		gUIListarPaises = new GUIListarPaises();
		gUIActualizarPais = new GUIActualizarPais();
		gUIBajaPais = new GUIBajaPais();
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Fruteria Mr. Pressman");
		JPanel mainPanel = new JPanel(new BorderLayout());
			JToolBar northPanel = new JToolBar();
				comboBox = new JComboBox<String>(GUIMainImp.module);
				comboBox.setSelectedIndex(0);
				comboBox.addActionListener(new ActionListener() {
					@SuppressWarnings("unchecked")
					@Override
					public void actionPerformed(ActionEvent e){
						JComboBox<String> cb = (JComboBox<String>)e.getSource();
						switch ((String)cb.getSelectedItem()) {
						case "Marcas": Controlador.getInstance().accion(Events.CREAR_GUI_MARCA, null); break;
						case "Productos": Controlador.getInstance().accion(Events.CREAR_GUI_PRODUCTO, null); break;
						case "Ventas": Controlador.getInstance().accion(Events.CREAR_GUI_VENTA, null); break;
						case "Clientes": Controlador.getInstance().accion(Events.CREAR_GUI_CLIENTE, null); break;
						}
						if (GUIMainImp.module[0] != (String)cb.getSelectedItem()) dispose();
					}
				});
			northPanel.add(new JLabel("Gestion de Paises"));
			northPanel.add(comboBox);
		
			JPanel centerPanel = new JPanel(new GridLayout(numModule, 1));
			for (int i = 0; i < numModule; ++i){
				final int j = i;
				JButton button = new JButton(module[j]);
				button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						switch (module[j]) {
						case "Alta": gUIAltaPais.clearData(); gUIAltaPais.setVisible(true); break;
						case "Buscar": gUIBuscarPais.clearData(); gUIBuscarPais.setVisible(true); break;
						case "Listar": gUIListarPaises.clearData(); gUIListarPaises.setVisible(true); break;
						case "Actualizar": gUIActualizarPais.clearData(); gUIActualizarPais.setVisible(true); break;
						case "Baja": gUIBajaPais.clearData(); gUIBajaPais.setVisible(true); break;
						}
					}
					
				});
				centerPanel.add(button);
			}
			mainPanel.add(northPanel, BorderLayout.NORTH);
			mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	
	@SuppressWarnings("unchecked")
	public void update(int event, Object res) {
		TPais tP;
		GUIMensaje gM = new GUIMensaje();
		switch(event){
		case Events.RES_ALTA_PAIS_OK:
			gM.showMessage("Se ha dado de alta correctamente al pais con id: "
					+ (int) res,  "ALTA PAIS", false);
			break;
		case Events.RES_ALTA_PAIS_KO:
			switch ((int)res){
			case -1:
				gM.showMessage("País nulo"
				, "ALTA PAIS", true);
				break;
			case -2:
				gM.showMessage("Error sintáctico"
				, "ALTA PAIS", true);
				break;
			case -3:
				gM.showMessage("País ya activo, no se puede volver a crear"
				, "ALTA PAIS",true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de la Base de Datos"
				, "ALTA PAIS",true);
				break;
			default:
				gM.showMessage("No se pudo dar de alta al país"
				, "ALTA PAIS", true);
				break;	
			}
			break;
		case Events.RES_ACTUALIZAR_PAIS_OK:
			gM.showMessage("Se ha actualizado correctamente al pais con id: "
			+ (int) res	, "ACTUALIZAR PAIS", false);
			break;
		case Events.RES_ACTUALIZAR_PAIS_KO:
			switch ((int)res){
			case -2:
				gM.showMessage("Error sintáctico"
				, "ACTUALIZAR PAIS", true);
				break;
			case -3:
				gM.showMessage("País que se quiere actualizar no existe"
				, "ACTUALIZAR PAIS", true);
				break;
			case -4:
				gM.showMessage("País no está activo"
				, "ACTUALIZAR PAIS", true);
				break;
			case -5:
				gM.showMessage("Se ha querido actualizar el nombre a uno ya existente"
				, "ACTUALIZAR PAIS", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de Base de Datos"
				, "ACTUALIZAR PAIS", true);
				break;
			default:
				gM.showMessage("No se pudo actualizar el país"
				, "ACTUALIZAR PAIS", true);
				break;	
			}
			break;
		case Events.RES_BUSCAR_PAIS_OK:
			tP = (TPais) res;
			this.gUIBuscarPais.update(tP);
			break;
		case Events.RES_BUSCAR_PAIS_KO:
			gM.showMessage("No se encontro el país con id: "
			+ (int) res, "BUSCAR PAIS", true);
			break;
		case Events.RES_LISTAR_PAIS_OK:
			this.gUIListarPaises.update((ArrayList<TPais>) res);
			break;
		case Events.RES_LISTAR_PAIS_KO:
			gM.showMessage("No se pudieron listar los países", "LISTAR PAIS",
					true);	
			break;
		case Events.RES_BAJA_PAIS_OK:
			gM.showMessage("Se dio de baja correctamente al pais con id: "
					+ (int)res	, "BAJA PAIS", false);
			break;
		case Events.RES_BAJA_PAIS_KO:
			switch ((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
				, "BAJA PAIS", true);
				break;
			case -2:
				gM.showMessage("País a dar de baja no existe"
				, "BAJA PAIS", true);
				break;
			case -3:
				gM.showMessage("País ya ha sido dado de baja"
				, "BAJA PAIS", true);
				break;
			case -4:
				gM.showMessage("País tiene dependencias con marcas"
				, "BAJA PAIS", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de Base de Datos"
				, "BAJA PAIS", true);
				break;
			default:
				gM.showMessage("No se pudo dar de baja al país con id"
				+ (int) res		
				, "BAJA PAIS", true);
				break;	
			}
			break;
		case Events.UPDATE_PAIS_COMBO_BOX:
			comboBox.setSelectedIndex(0);
			break;
		}
		
	}
}