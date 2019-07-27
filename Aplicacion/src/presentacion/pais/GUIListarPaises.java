/**
 * 
 */
package presentacion.pais;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import main.Main;
import negocio.pais.TPais;
import presentacion.Controlador;
import presentacion.Events;

@SuppressWarnings("serial")
public class GUIListarPaises extends JFrame {
	
	private JTable table;
	private String[] columnNames = { "#", "Id", "Nombre" }; 
	private DefaultTableModel tableModel;
	
	public GUIListarPaises(){
		super();
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Fruteria Mr. Pressman");
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
			JToolBar northPanel = new JToolBar();
			northPanel.add(new JLabel("Listar Paises"));
			
			table = new JTable();
			
			tableModel = new DefaultTableModel(){
				
				@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
				
			};
			tableModel.setColumnCount(0);
			for (int i = 0; i < columnNames.length; ++i)
				tableModel.addColumn(columnNames[i]);
			table.setModel(tableModel);
				
			JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			
			JButton button = new JButton("Listar");
			button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Events.LISTAR_PAIS, null);
					toFront();
				}
				
			});
			
			southPanel.add(button);
			
		mainPanel.add(northPanel, BorderLayout.PAGE_START);
		mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowDim = new Dimension((int) (Main.WINDOW_DIM.getWidth()), (int) (Main.WINDOW_DIM.getHeight() * (5)));
		this.setBounds(
				(int) (dim.getWidth() / 2 - windowDim.getWidth() / 2),
				(int) (dim.getHeight() / 2 - windowDim.getHeight() / 2),
				(int) (windowDim.getWidth()),
				(int) (windowDim.getHeight())
				);
	}
	
	public void update (ArrayList<TPais> res){
		 tableModel.setRowCount(0);
		 //tableModel.edit
		 for (int i = 0; i < res.size(); i++)
			 tableModel.insertRow(i, new Object[] 
					 { i, res.get(i).getId(), res.get(i).getNombre() });
		 table.setModel(tableModel);
	}

	public void clearData() {
		tableModel.setRowCount(0);
		table.setModel(tableModel);
	}
}