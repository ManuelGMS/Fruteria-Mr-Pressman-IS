/**
 * 
 */
package presentacion;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GUIMensaje extends JDialog {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param message
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void showMessage(String message,String message2, boolean error) {
		if(!error){
			JOptionPane.showMessageDialog(new JFrame(),message	, message2, JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(new JFrame(),message	, message2, JOptionPane.ERROR_MESSAGE);
	}
}