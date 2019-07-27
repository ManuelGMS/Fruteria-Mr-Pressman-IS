/**
 * 
 */
package presentacion.venta;

import javax.swing.JFrame;

import negocio.venta.TVenta;

@SuppressWarnings("serial")
public abstract class GUIVenta extends JFrame {
	protected TVenta tV;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static GUIVenta instance;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param event
	 * @param res
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract void update(int event, Object res);

	public abstract TVenta getVenta();
	public abstract void setVenta(TVenta tV);
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static GUIVenta getInstance() {
		if(instance == null)
			instance = new GUIVentaImp();
		instance.setVisible(true);
		return instance;
	}
}