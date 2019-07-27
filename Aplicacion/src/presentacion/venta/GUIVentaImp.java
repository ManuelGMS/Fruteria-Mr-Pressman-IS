/**
 * 
 */
package presentacion.venta;

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
import negocio.venta.TVenta;
import presentacion.Controlador;
import presentacion.Events;
import presentacion.GUIMainImp;
import presentacion.GUIMensaje;

@SuppressWarnings("serial")
public class GUIVentaImp extends GUIVenta {
	
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIAbrirVenta gUIAbrirVenta;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIAnadirProducto gUIAnadirProducto;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBuscarVenta gUIBuscarVenta;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUICerrarVenta gUICerrarVenta;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIDevolucion gUIDevolucion;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIListarVentas gUIListarVentas;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIModificarProducto gUIModificarProducto;

	protected String[] module = ("Abrir Venta\n"+ "Buscar\n"+ "Listar\n"+ "Cerrar Venta\n"+ "Anadir Producto\n"+ "Modificar Producto\n"+ "Devolucion").split("\\n");
	protected int numModule = module.length;
	@SuppressWarnings("rawtypes")
	protected JComboBox comboBox;
	protected JButton[] button = new JButton[numModule];
	
	public GUIVentaImp(){
		super();
		gUIAbrirVenta = new GUIAbrirVenta();
		gUIBuscarVenta = new GUIBuscarVenta();
		gUIListarVentas = new GUIListarVentas();
		gUICerrarVenta = new GUICerrarVenta();
		gUIAnadirProducto = new GUIAnadirProducto();
		gUIModificarProducto = new GUIModificarProducto();
		gUIDevolucion = new GUIDevolucion();
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Fruteria Mr. Pressman");
		JPanel mainPanel = new JPanel(new BorderLayout());
		JToolBar northPanel = new JToolBar();
		comboBox = new JComboBox<String>(GUIMainImp.module);
		comboBox.setSelectedIndex(3);
		comboBox.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				switch ((String)cb.getSelectedItem()) {
				case "Paises": Controlador.getInstance().accion(Events.CREAR_GUI_PAIS, null); break;
				case "Marcas": Controlador.getInstance().accion(Events.CREAR_GUI_MARCA, null); break;
				case "Productos": Controlador.getInstance().accion(Events.CREAR_GUI_PRODUCTO, null); break;
				case "Clientes": Controlador.getInstance().accion(Events.CREAR_GUI_CLIENTE, null); break;
				}
				if (GUIMainImp.module[3] != (String)cb.getSelectedItem()) dispose();
			}
		});
		northPanel.add(new JLabel("Gestion de Ventas"));
		northPanel.add(comboBox);
		
			JPanel centerPanel = new JPanel(new GridLayout(numModule, 1));
			for (int i = 0; i < numModule; ++i){
				final int j = i;
				button[i] = new JButton(module[j]);
				button[i].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						switch (module[j]) {
						case "Abrir Venta": gUIAbrirVenta.clearData(); gUIAbrirVenta.setVisible(true); break;
						case "Buscar": gUIBuscarVenta.clearData(); gUIBuscarVenta.setVisible(true); break;
						case "Listar": gUIListarVentas.clearData(); gUIListarVentas.setVisible(true); break;
						case "Cerrar Venta": gUICerrarVenta.clearData(); gUICerrarVenta.setVisible(true);
						GUIVenta.getInstance().update(Events.CERRAR_VENTA_UPDATE_LINEA_VENTA, getVenta());break;
						case "Anadir Producto": gUIAnadirProducto.clearData(); gUIAnadirProducto.setVisible(true); break;
						case "Modificar Producto": gUIModificarProducto.clearData(); gUIModificarProducto.setVisible(true); break;
						case "Devolucion": gUIDevolucion.clearData(); gUIDevolucion.setVisible(true); break;
						}
					}
					
				});
				centerPanel.add(button[i]);
			}
			setEnaDis(false, "Cerrar Venta\nAnadir Producto\nModificar Producto");
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
		TVenta tV;
		GUIMensaje gM = new GUIMensaje();
		switch(event){
		case Events.RES_ABRIR_VENTA_OK:
			this.tV = (TVenta) res;
			setEnaDis(false, "Abrir Venta");
			setEnaDis(true, "Cerrar Venta\nAnadir Producto\nModificar Producto");
			gM.showMessage("Se ha abierto correctamente la venta"
			, "ABRIR VENTA", false);
			break;
		case Events.RES_ABRIR_VENTA_KO:
			gM.showMessage("No se pudo abrir la venta", "ABRIR VENTA", true);
			break;
		case Events.RES_AGREGAR_PRODUCTO_OK:
			tV = (TVenta) res;
			gM.showMessage("Se ha agregado el producto correctamente a la venta", "AGREGAR PRODUCTO", false);
			break;
		case Events.RES_AGREGAR_PRODUCTO_KO:
			switch((int)res){
				case -1:
					gM.showMessage("Error sintáctico"
					, "AGREGAR PRODUCTO", true);
					break;
				case -2:
					gM.showMessage("La venta no está abierta"
					, "AGREGAR PRODUCTO", true);
					break;
			}
			break;
		case Events.RES_MODIFICAR_PRODUCTO_OK:
			tV = (TVenta) res;
			gM.showMessage("Se modifico correctamente el producto a la venta", "MODIFICAR PRODUCTO", false);
			break;
		case Events.RES_MODIFICAR_PRODUCTO_KO:
			switch((int)res){
			case -1:
				gM.showMessage("La venta en la que se quiere modificar un producto es nula"
					, "MODIFICAR_PRODUCTO", true);
				break;
			case -2:
				gM.showMessage("El producto a modificar no existe en la venta"
						, "MODIFICAR_PRODUCTO", true);
				break;
			case -3:
				gM.showMessage("No se admiten unidades negativas"
						, "MODIFICAR_PRODUCTO", true);
				break;
			}
			
			break;
		case Events.RES_CERRAR_VENTA_OK:
			setEnaDis(true, "Abrir Venta");
			setEnaDis(false, "Cerrar Venta\nAnadir Producto\nModificar Producto");
			gM.showMessage("Se cerro correctamente la venta con id: "
			+ (int) res	, "CERRAR VENTA", false);
			this.gUICerrarVenta.update(GUIVenta.getInstance().getVenta());
			break;
			
		case Events.RES_CERRAR_VENTA_KO:
			setEnaDis(true, "Abrir Venta");
			setEnaDis(false, "Cerrar Venta\nAnadir Producto\nModificar Producto");
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
					, "CERRAR VENTA", true);
				break;
			case -2:
				gM.showMessage("La venta a cerrar no es válida"
						, "CERRAR VENTA", true);
				break;
			case -3:
				gM.showMessage("La venta a cerrar no estaba abierta"
						, "CERRAR VENTA", true);
				break;
			case -4:
				gM.showMessage("No se ha encontrado al cliente "
						, "CERRAR VENTA", true);
				break;
			case -5:
				gM.showMessage("El cliente no está activo"
						, "CERRAR VENTA", true);
				break;
			case -6:
				gM.showMessage("La venta no posee una línea de ventas"
						, "CERRAR VENTA", true);
				break;
			case -7:
				gM.showMessage("La línea de ventas quedó vacía"
						, "CERRAR VENTA", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de la base de datos"
						, "CERRAR VENTA", true);
				break;
			}
			
			break;
		case Events.RES_BUSCAR_VENTA_OK:
			tV = (TVenta) res;
			this.gUIBuscarVenta.update(tV);
			break;
		case Events.RES_BUSCAR_VENTA_KO:
			gM.showMessage("No se encontro la venta con id: "
			+(int)res, "BUSCAR VENTA", true);
			break;
		case Events.RES_LISTAR_VENTA_OK:
			this.gUIListarVentas.update((ArrayList<TVenta>) res);
			break;
		case Events.RES_LISTAR_VENTA_KO:
			gM.showMessage( "No se pudieron listar las ventas", "LISTAR VENTAS",
					true);	
			break;
		case Events.RES_DEVOLUCION_VENTA_OK:
			gM.showMessage("Se realizo correctamente la devolucion de la venta con id: "
					+ (int) res	, "DEVOLUCION VENTA", false);
			break;
		
		case Events.RES_DEVOLUCION_VENTA_KO:
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
				, "DEVOLUCION VENTA", true);
				break;
			case -8:
				gM.showMessage("La venta no existe"
				, "DEVOLUCION VENTA", true);
				break;
			case -9:
				gM.showMessage("El producto no existe o no está activo"
				, "DEVOLUCION VENTA", true);
				break;
			case -10:
				gM.showMessage("No existe la línea de venta"
				, "DEVOLUCION VENTA", true);
				break;
			case -11:
				gM.showMessage("Las unidades a devolver superan las de la venta"
				, "DEVOLUCION VENTA", true);
				break;
			case -100:
				gM.showMessage("Errar en la gestión de la Base de Datos"
				, "DEVOLUCION VENTA", true);
				break;
			}
			
			break;
		case Events.UPDATE_VENTA_COMBO_BOX:
			comboBox.setSelectedIndex(3);
			break;
		case Events.CERRAR_VENTA_UPDATE_LINEA_VENTA:
			this.gUICerrarVenta.update((TVenta) res);
			break;
		}
	}
	
	public void setEnaDis(Boolean enaDis, String text){
		String[] texts = text.split("\\n");
		for (int i = 0; i < numModule; ++i)
			for (int j = 0; j < texts.length; ++j)
				if (module[i].equalsIgnoreCase(texts[j]))
					button[i].setEnabled(enaDis);
	}
	
	@Override
	public TVenta getVenta(){
		return this.tV;
	}

	@Override
	public void setVenta(TVenta tV) {
		this.tV = tV;
	}
}