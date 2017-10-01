package felulet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import alaposztalyok.Employee;
import alaposztalyok.Region;
import alaposztalyok.Territory;
import db.DBConnection;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DBPanel extends JPanel {
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	JComboBox comboBox = new JComboBox();
	private List<Employee> list;
	private Region region;

	private String sql = "select * from Employees";

	/**
	 * Create the panel.
	 */
	public DBPanel() {
		initUI();
	}

	private void initUI() {

		model.addColumn("ID");
		model.addColumn("Last Name");
		model.addColumn("First Name");
		model.addColumn("City");

		JButton btnNewButton = new JButton("Táblázat feltöltése");
		btnNewButton.setBounds(10, 11, 149, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				feltolt();
				dropdown();
				btnNewButton.setEnabled(false);
			}
		});
		setLayout(null);
		add(btnNewButton);
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int sor = table.getSelectedRow();
				int empID =(int) table.getValueAt(sor, 0);
				//System.out.println(empID);
				region=DBConnection.getRegion(empID);
				int i=0;
				String message ="REGION\t\tTERRITORY\n "+region.getDescription();
				for (Territory territory: region.getTerritories()) {
					message+=region.getTerritories().get(i).getDescription()+ "\n"+"\t\t";
					i++;
				}
				JOptionPane.showMessageDialog(null, new JTextArea( message));
			}
		});
		table.setBounds(157, 173, 1, 1);
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(41, 53, 382, 202);
		add(scrollPane);

		JButton btnNewButton_1 = new JButton("Módosítás");
		btnNewButton_1.setBounds(118, 276, 107, 23);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Új");
		btnNewButton_2.setBounds(235, 276, 89, 23);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Törlés");
		btnNewButton_3.setBounds(334, 276, 89, 23);
		add(btnNewButton_3);

		comboBox.setBounds(254, 12, 115, 20);
		add(comboBox);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String city = comboBox.getSelectedItem().toString();
				if (city == "Összes") {
					sql = "select * from Employees";
				} else {
					sql = "select * from Employees where City = '" + city+"'";
				}
				szures();
			}
		});

		JLabel lblVros = new JLabel("Város:");
		lblVros.setBounds(201, 15, 43, 14);
		add(lblVros);
	}

	

	public void szures() {
		feltolt();
	}

	public void feltolt() {
		model.setRowCount(0);
		list = DBConnection.empBe(sql);
		for (Employee employee : list) {
			model.addRow(new Object[] { employee.getId(), employee.getLastName(), employee.getFirstName(),
					employee.getCity() });
		}
		
	}
	public void dropdown() {
		comboBox.addItem("Összes");

		String[] varosok = new String[model.getRowCount()];
		int i = 0;
		for (Employee employee : list) {
			varosok[i] = employee.getCity();
			i++;
		}
		Set<String> h = new HashSet<String>(Arrays.asList(varosok));
		String[] v2 = h.toArray(new String[h.size()]);
		Arrays.sort(v2);
		for (int j = 0; j < v2.length; j++) {
			comboBox.addItem(v2[j]);
		}
		
	}
}
