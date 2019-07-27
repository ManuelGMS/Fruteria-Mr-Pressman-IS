/**
 * 
 */
package presentacion.cliente;

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
import negocio.cliente.TCliente;
import presentacion.Controlador;
import presentacion.Events;
import presentacion.GUIMainImp;
import presentacion.GUIMensaje;

@SuppressWarnings("serial")
public class GUIClienteImp extends GUICliente {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIAltaClienteVIP gUIAltaClienteVIP;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIAltaClienteNO_VIP gUIAltaClienteNO_VIP;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIActualizarCliente gUIActualizarCliente;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBuscarCliente gUIBuscarCliente;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIBajaCliente gUIBajaCliente;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private GUIListarClientes gUIListarClientes;

	/** 
	 * (sin Javadoc)
	 * @see GUICliente#update(int event, Object res)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */

	protected String[] module = ("Alta Cliente VIP\n"+ "Alta Cliente No VIP\n"+ "Buscar\n"+ "Listar\n"+ "Actualizar\n"+ "Baja").split("\\n");
	protected int numModule = module.length;
	@SuppressWarnings("rawtypes")
	protected JComboBox comboBox;
	
	public GUIClienteImp(){
		super();
		gUIAltaClienteVIP = new GUIAltaClienteVIP();
		gUIAltaClienteNO_VIP = new GUIAltaClienteNO_VIP();
		gUIBuscarCliente = new GUIBuscarCliente();
		gUIListarClientes = new GUIListarClientes();
		gUIActualizarCliente = new GUIActualizarCliente();
		gUIBajaCliente = new GUIBajaCliente();
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Fruteria Mr. Pressman");
		JPanel mainPanel = new JPanel(new BorderLayout());
			JToolBar northPanel = new JToolBar();
			comboBox = new JComboBox<String>(GUIMainImp.module);
			comboBox.setSelectedIndex(4);
			comboBox.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent e){
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					switch ((String)cb.getSelectedItem()) {
					case "Paises": Controlador.getInstance().accion(Events.CREAR_GUI_PAIS, null); break;
					case "Marcas": Controlador.getInstance().accion(Events.CREAR_GUI_MARCA, null); break;
					case "Productos": Controlador.getInstance().accion(Events.CREAR_GUI_PRODUCTO, null); break;
					case "Ventas": Controlador.getInstance().accion(Events.CREAR_GUI_VENTA, null); break;
					}
					if (GUIMainImp.module[4] != (String)cb.getSelectedItem()) dispose();
				}
			});
			northPanel.add(new JLabel("Gestion de Clientes"));
			northPanel.add(comboBox);
		
			JPanel centerPanel = new JPanel(new GridLayout(numModule, 1));
			for (int i = 0; i < numModule; ++i){
				final int j = i;
				JButton button = new JButton(module[j]);
				button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						switch (module[j]) {
						case "Alta Cliente VIP": gUIAltaClienteVIP.clearData(); gUIAltaClienteVIP.setVisible(true); break;
						case "Alta Cliente No VIP": gUIAltaClienteNO_VIP.clearData(); gUIAltaClienteNO_VIP.setVisible(true); break;
						case "Buscar": gUIBuscarCliente.clearData(); gUIBuscarCliente.setVisible(true); break;
						case "Listar": gUIListarClientes.clearData(); gUIListarClientes.setVisible(true); break;
						case "Actualizar": gUIActualizarCliente.clearData(); gUIActualizarCliente.setVisible(true); break;
						case "Baja": gUIBajaCliente.clearData(); gUIBajaCliente.setVisible(true); break;
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
		TCliente tCl;
		GUIMensaje gM = new GUIMensaje();
		switch(event){
		case Events.RES_ALTA_CLIENTE_NO_VIP_OK:
			gM.showMessage("Se ha dado de alta correctamente al cliente no vip con id: "
			+ (int)res, "ALTA CLIENTE NO VIP", false);
			break;
		case Events.RES_ALTA_CLIENTE_NO_VIP_KO:
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
				, "ALTA CLIENTE NO VIP", true);
				break;
			case -2:
				gM.showMessage("Cliente no válido"
						, "ALTA CLIENTE NO VIP", true);
				break;
			case -3:
				gM.showMessage("El cliente ya estaba dado de alta"
						, "ALTA CLIENTE NO VIP", true);
				break;
			case -4:
				gM.showMessage("Para reactivar cliente deber ser un cliente vip"
						, "ALTA CLIENTE NO VIP", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de la Base de Datos"
						, "ALTA CLIENTE NO VIP", true);
				break;
			}
			
			break;
		case Events.RES_ALTA_CLIENTE_VIP_OK:
			gM.showMessage("Se ha dado de alta correctamente al cliente vip con id: "
			+ (int)res, "ALTA CLIENTE VIP", false);
			break;
		case Events.RES_ALTA_CLIENTE_VIP_KO:
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
				, "ALTA CLIENTE VIP", true);
				break;
			case -2:
				gM.showMessage("Cliente no válido"
						, "ALTA CLIENTE VIP", true);
				break;
			case -3:
				gM.showMessage("El cliente ya estaba dado de alta"
						, "ALTA CLIENTE VIP", true);
				break;
			case -4:
				gM.showMessage("Para reactivar cliente deber ser un cliente no vip"
						, "ALTA CLIENTE VIP", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de la Base de Datos"
						, "ALTA CLIENTE VIP", true);
				break;
			}
			break;
		case Events.RES_ACTUALIZAR_CLIENTE_OK:
			this.gUIActualizarCliente.buscarOrActualizar(false);
			this.gUIActualizarCliente.clearData();
			gM.showMessage("Se ha actualizado correctamente el cliente con id: "
			+ (int)res	, "ACTUALIZAR CLIENTE", false);
			break;
		
		case Events.RES_ACTUALIZAR_CLIENTE_KO:
			this.gUIActualizarCliente.buscarOrActualizar(true);
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
				, "ACTUALIZAR CLIENTE", true);
				break;
			case -2:
				gM.showMessage("Cliente no válido"
						, "ACTUALIZAR CLIENTE", true);
				break;
			case -3:
				gM.showMessage("No se ha encontrado el cliente"
						, "ACTUALIZAR CLIENTE", true);
				break;
			case -4:
				gM.showMessage("El cliente que se encontró no estaba activo"
						, "ACTUALIZAR CLIENTE", true);
				break;
			case -5:
				gM.showMessage("El cliente solicitado no estaba activo"
						, "ACTUALIZAR CLIENTE", true);
				break;
			case -6:
				gM.showMessage("Se ha querido actualizar el DNI a uno ya existente"
						, "ACTUALIZAR CLIENTE", true);
				break;
			case -100:
				gM.showMessage("Error en la gestión de la Base de Datos"
						, "ACTUALIZAR CLIENTE", true);
				break;

			}
			break;
		case Events.RES_BUSCAR_ACTUALIZAR_CLIENTE_OK:
			this.gUIActualizarCliente.buscarOrActualizar(true);
			this.gUIActualizarCliente.update((TCliente)res);
			break;
		case Events.RES_BUSCAR_ACTUALIZAR_CLIENTE_KO:
			this.gUIActualizarCliente.buscarOrActualizar(false);
			break;
		case Events.RES_BUSCAR_CLIENTE_OK:
			tCl = (TCliente) res;
			this.gUIBuscarCliente.update(tCl);
			break;
		case Events.RES_BUSCAR_CLIENTE_KO:
			gM.showMessage("No se encontro el cliente con id: "
			+(int)res, "BUSCAR CLIENTE", true);
			break;
		case Events.RES_LISTAR_CLIENTE_OK:
			this.gUIListarClientes.update((ArrayList<TCliente>) res);
			break;
		case Events.RES_LISTAR_CLIENTE_KO:
			gM.showMessage("No se pudieron listar los clientes", "LISTAR CLIENTES",
					true);	
			break;
		case Events.RES_BAJA_CLIENTE_OK:
			gM.showMessage("Se dio de baja correctamente al cliente con id: "
					+ (int)res	, "BAJA CLIENTE", false);
			break;
			
		case Events.RES_BAJA_CLIENTE_KO:
			switch((int)res){
			case -1:
				gM.showMessage("Error sintáctico"
				, "ACTUALIZAR CLIENTE", true);
				break;
			case -2:
				gM.showMessage("El cliente no existe"
						, "ACTUALIZAR CLIENTE", true);
				break;
			case -100:
				gM.showMessage("ERROR EN LA GESTIÓN DE LA BASE DE DATOS"
						, "ACTUALIZAR CLIENTE", true);
				break;
			}
			break;
		case Events.UPDATE_CLIENTE_COMBO_BOX:
			comboBox.setSelectedIndex(4);
			break;
		
		}
	}
}