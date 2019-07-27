/**
 * 
 */
package presentacion.marca;

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
import negocio.marca.TMarca;
import presentacion.Controlador;
import presentacion.Events;
import presentacion.GUIMainImp;
import presentacion.GUIMensaje;

@SuppressWarnings("serial")
public class GUIMarcaImp extends GUIMarca {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIAltaMarca gUIAltaMarca;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBuscarMarca gUIBuscarMarca;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIListarMarcas gUIListarMarcas;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIActualizarMarca gUIActualizarMarca;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBajaMarca gUIBajaMarca;

	/** 
	 * (sin Javadoc)
	 * @see GUIMarca#update(int event, Object res)
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
	
	public GUIMarcaImp(){
		super();
		gUIAltaMarca = new GUIAltaMarca();
		gUIBuscarMarca = new GUIBuscarMarca();
		gUIListarMarcas = new GUIListarMarcas();
		gUIActualizarMarca = new GUIActualizarMarca();
		gUIBajaMarca = new GUIBajaMarca();
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Fruteria Mr. Pressman");
		JPanel mainPanel = new JPanel(new BorderLayout());
			JToolBar northPanel = new JToolBar();
			comboBox = new JComboBox<String>(GUIMainImp.module);
			comboBox.setSelectedIndex(1);
			comboBox.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent e){
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					switch ((String)cb.getSelectedItem()) {
					case "Paises": Controlador.getInstance().accion(Events.CREAR_GUI_PAIS, null); break;
					case "Productos": Controlador.getInstance().accion(Events.CREAR_GUI_PRODUCTO, null); break;
					case "Ventas": Controlador.getInstance().accion(Events.CREAR_GUI_VENTA, null); break;
					case "Clientes": Controlador.getInstance().accion(Events.CREAR_GUI_CLIENTE, null); break;
					}
					if (GUIMainImp.module[1] != (String)cb.getSelectedItem()) dispose();
				}
			});
			northPanel.add(new JLabel("Gestion de Marcas"));
			northPanel.add(comboBox);
		
			JPanel centerPanel = new JPanel(new GridLayout(numModule, 1));
			for (int i = 0; i < numModule; ++i){
				final int j = i;
				JButton button = new JButton(module[j]);
				button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						switch (module[j]) {
						case "Alta": gUIAltaMarca.clearData(); gUIAltaMarca.setVisible(true); break;
						case "Buscar": gUIBuscarMarca.clearData(); gUIBuscarMarca.setVisible(true); break;
						case "Listar": gUIListarMarcas.clearData(); gUIListarMarcas.setVisible(true); break;
						case "Actualizar": gUIActualizarMarca.clearData(); gUIActualizarMarca.setVisible(true); break;
						case "Baja": gUIBajaMarca.clearData(); gUIBajaMarca.setVisible(true); break;
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
		TMarca tM;
		GUIMensaje gM = new GUIMensaje();
		switch(event){
		case Events.RES_ALTA_MARCA_OK:
			gM.showMessage("Se ha dado de alta correctamente a la marca con id: "
			+ (int) res, "ALTA MARCA", false);
			break;
		case Events.RES_ALTA_MARCA_KO:
			switch((int)res){
				case -1:
					gM.showMessage("Error sintáctico"
							, "ALTA MARCA", true);
				break;
				case -2:
					gM.showMessage("Marca inválida"
							, "ALTA MARCA", true);
				break;
				case -3:
					gM.showMessage("No se ha encontrado el país"
							, "ALTA MARCA", true);
				break;
				case -4:
					gM.showMessage("País no está activo"
							, "ALTA MARCA", true);
				break;
				case -5:
					gM.showMessage("Marca ya estaba activa"
					, "ALTA MARCA", true);
				break;
				case -100:
					gM.showMessage("Error en la gestión de Base de Datos"
					, "ALTA MARCA", true);
				break;
				default:
					gM.showMessage("No se pudo dar de alta a la marca con id: "
							+ (int)res, "ALTA MARCA", true);
					break;
			}
		break;	
		case Events.RES_ACTUALIZAR_MARCA_OK:
			gM.showMessage("Se ha actualizado correctamente la marca con id: "
			+ (int)res	, "ACTUALIZAR MARCA", false);
			break;
		case Events.RES_ACTUALIZAR_MARCA_KO:
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
						, "ACTUALIZAR MARCA", true);
			break;
			case -2:
				gM.showMessage("Marca inválida"
						, "ACTUALIZAR MARCA", true);
			break;
			case -3:
				gM.showMessage("No se ha encontrado la marca"
						, "ACTUALIZAR MARCA", true);
			break;
			case -4:
				gM.showMessage("Se ha querido actualizar el nombre de la marca por uno ya existente"
						, "ACTUALIZAR MARCA", true);
			break;
			case -5:
				gM.showMessage("No se encontró el país"
				, "ACTUALIZAR MARCA", true);
			break;
			case -6:
				gM.showMessage("País no estaba activo"
				, "ACTUALIZAR MARCA",true);
			break;
			case -7:
				gM.showMessage("Marca no está activa"
				, "ACTUALIZAR MARCA", true);
			break;
			case -8:
				gM.showMessage("Marca de la base de datos no está activa"
				, "ACTUALIZAR MARCA", true);
			break;
			case -100:
				gM.showMessage("Error en la gestión de la Base de Datos"
				, "ACTUALIZAR MARCA", true);
			break;
			default:
				gM.showMessage("No se pudo actualizar la marca con id: "
						+ (int)res, "ACTUALIZAR MARCA",true);
				break;
		}
			break;
		case Events.RES_BUSCAR_MARCA_OK:
			tM = (TMarca) res;
			this.gUIBuscarMarca.update(tM);
			break;
		case Events.RES_BUSCAR_MARCA_KO:
			gM.showMessage("No se encontro la marca con id: "
			+ (int) res, "BUSCAR MARCA",true);
			break;
		case Events.RES_LISTAR_MARCA_OK:
			this.gUIListarMarcas.update((ArrayList<TMarca>) res);
			break;
		case Events.RES_LISTAR_MARCA_KO:
			gM.showMessage( "No se pudieron listar las marcas", "LISTAR MARCA",
					true);	
			break;
		case Events.RES_BAJA_MARCA_OK:
			gM.showMessage("Se dio de baja correctamente la marca con id: "
					+ (int)res	, "BAJA MARCA", false);
			break;
		case Events.RES_BAJA_MARCA_KO:
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
						, "BAJA MARCA", true);
			break;
			case -2:
				gM.showMessage("No se ha encontrado la marca"
						, "BAJA MARCA", true);
			break;
			case -3:
				gM.showMessage("La marca no está activa y no puede eliminarse"
						, "BAJA MARCA", true);
			break;
			case -4:
				gM.showMessage("La marca tiene productos asociados y no puede eliminarse"
						, "BAJA MARCA", true);
			break;
			case -100:
				gM.showMessage("Error en la base de datos"
						, "BAJA MARCA", true);
			break;
			default:
				gM.showMessage("No se pudo dar de baja la marca con id: "
					+ (int)res	, "BAJA MARCA", true);
			break;
			}
			break;
		case Events.UPDATE_MARCA_COMBO_BOX:
			comboBox.setSelectedIndex(1);
			break;
		}
	}
}