/**
 * 
 */
package presentacion.producto;

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
import negocio.producto.TProducto;
import presentacion.Controlador;
import presentacion.Events;
import presentacion.GUIMainImp;
import presentacion.GUIMensaje;

@SuppressWarnings("serial")
public class GUIProductoImp extends GUIProducto {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIAltaProducto gUIAltaProducto;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBuscarProducto gUIBuscarProducto;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIListarProducto gUIListarProducto;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIActualizarProducto gUIActualizarProducto;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBajaProducto gUIBajaProducto;

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
	
	public GUIProductoImp(){
		super();
		gUIAltaProducto = new GUIAltaProducto();
		gUIBuscarProducto = new GUIBuscarProducto();
		gUIListarProducto = new GUIListarProducto();
		gUIActualizarProducto = new GUIActualizarProducto();
		gUIBajaProducto = new GUIBajaProducto();
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Fruteria Mr. Pressman");
		JPanel mainPanel = new JPanel(new BorderLayout());
			JToolBar northPanel = new JToolBar();
			comboBox = new JComboBox<String>(GUIMainImp.module);
			comboBox.setSelectedIndex(2);
			comboBox.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent e){
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					switch ((String)cb.getSelectedItem()) {
					case "Paises": Controlador.getInstance().accion(Events.CREAR_GUI_PAIS, null); break;
					case "Marcas": Controlador.getInstance().accion(Events.CREAR_GUI_MARCA, null); break;
					case "Ventas": Controlador.getInstance().accion(Events.CREAR_GUI_VENTA, null); break;
					case "Clientes": Controlador.getInstance().accion(Events.CREAR_GUI_CLIENTE, null); break;
					}
					if (GUIMainImp.module[2] != (String)cb.getSelectedItem()) dispose();
				}
			});
			northPanel.add(new JLabel("Gestion de Productos"));
			northPanel.add(comboBox);
		
			JPanel centerPanel = new JPanel(new GridLayout(numModule, 1));
			for (int i = 0; i < numModule; ++i){
				final int j = i;
				JButton button = new JButton(module[j]);
				button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						switch (module[j]) {
						case "Alta": gUIAltaProducto.clearData(); gUIAltaProducto.setVisible(true); break;
						case "Buscar": gUIBuscarProducto.clearData(); gUIBuscarProducto.setVisible(true); break;
						case "Listar": gUIListarProducto.clearData(); gUIListarProducto.setVisible(true); break;
						case "Actualizar": gUIActualizarProducto.clearData(); gUIActualizarProducto.setVisible(true); break;
						case "Baja": gUIBajaProducto.clearData(); gUIBajaProducto.setVisible(true); break;
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
		TProducto tPr;
		GUIMensaje gM = new GUIMensaje();
		switch(event){
		case Events.RES_ALTA_PRODUCTO_OK:
			gM.showMessage("Se ha dado de alta correctamente al producto con id: "
			+ (int) res, "ALTA PRODUCTO", false);
			break;
		case Events.RES_ALTA_PRODUCTO_KO:
			switch((int)res){
			case -1:
				gM.showMessage("El producto es nulo"
						, "ALTA PRODUCTO", true);
				break;
			case -2:
				gM.showMessage("Error sintáctico"
						, "ALTA PRODUCTO", true);
				break;
			case -3:
				gM.showMessage("No existe ninguna marca para dicho producto"
						, "ALTA PRODUCTO", true);
				break;
			case -4:
				gM.showMessage("La marca del producto no está activa"
						, "ALTA PRODUCTO", true);
				break;
			case -5:
				gM.showMessage("El producto ya está activo"
						, "ALTA PRODUCTO", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de Base de Datos"
						, "ALTA PRODUCTO", true);
				break;
			}
			break;
		case Events.RES_ACTUALIZAR_PRODUCTO_OK:
			gM.showMessage("Se ha actualizado correctamente el producto con id: "
			+ (int) res	, "ACTUALIZAR PRODUCTO", false);
			break;
		case Events.RES_ACTUALIZAR_PRODUCTO_KO:
			switch((int)res){
			case -1:
				gM.showMessage("El producto es nulo"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			case -2:
				gM.showMessage("Error sintáctico"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			case -3:
				gM.showMessage("No existe el producto a actualizar"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			case -4:
				gM.showMessage("El producto no está activo"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			case -5:
				gM.showMessage("No existe la marca de dicho producto"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			case -6:
				gM.showMessage("La marca de dicho producto no está activa"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			case -7:
				gM.showMessage("Se ha querido actualizar el nombre a otro ya existente"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de la Base de Datos"
						, "ACTUALIZAR PRODUCTO", true);
				break;
			}
			break;
		case Events.RES_BUSCAR_PRODUCTO_OK:
			tPr = (TProducto) res;
			this.gUIBuscarProducto.update(tPr);
			break;
		case Events.RES_BUSCAR_PRODUCTO_KO:
			tPr = (TProducto) res;
			gM.showMessage("No se encontro el producto con id: "
			+ tPr.getId(), "BUSCAR PRODUCTO", true);
			break;
		case Events.RES_LISTAR_PRODUCTO_OK:
			this.gUIListarProducto.update((ArrayList<TProducto>) res);
			break;
		case Events.RES_LISTAR_PRODUCTO_KO:
			gM.showMessage( "No se pudieron listar los productos", "LISTAR PRODUCTOS",
					true);	
			break;
		case Events.RES_BAJA_PRODUCTO_OK:
			gM.showMessage("Se dio de baja correctamente el producto con id: "
					+ (int) res	, "BAJA PRODUCTO", false);
			break;
		case Events.RES_BAJA_PRODUCTO_KO:
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
						, "BAJA PRODUCTO", true);
				break;
			case -2:
				gM.showMessage("El producto a dar de baja no existe"
						, "BAJA PRODUCTO", true);
				break;
			case -3:
				gM.showMessage("El producto a borrar ya ha sido borrado"
						, "BAJA PRODUCTO", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de Base de Datos"
						, "BAJA PRODUCTO", true);
				break;
			}
			break;
		case Events.UPDATE_PRODUCTO_COMBO_BOX:
			comboBox.setSelectedIndex(2);
			break;
		}
	}
}