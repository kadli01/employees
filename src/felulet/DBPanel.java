package felulet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import alaposztalyok.Employee;
import db.DBConnection;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;


public class DBPanel extends JPanel {
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	JComboBox comboBox = new JComboBox();

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

		JButton btnNewButton = new JButton("T\u00E1bl\u00E1zat felt\u00F6lt\u00E9se");
		btnNewButton.setBounds(10, 11, 149, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				feltolt();
			}
		});
		setLayout(null);
		add(btnNewButton);
		table = new JTable(model);
		table.setBounds(157, 173, 1, 1);
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(41, 53, 382, 202);
		add(scrollPane);

		JButton btnNewButton_1 = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnNewButton_1.setBounds(118, 276, 107, 23);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u00DAj");
		btnNewButton_2.setBounds(235, 276, 89, 23);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("T\u00F6rl\u00E9s");
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

		JLabel lblVros = new JLabel("V\u00E1ros:");
		lblVros.setBounds(201, 15, 43, 14);
		add(lblVros);
	}

	protected void szures() {
		feltolt();
	}

	protected void feltolt() {
		model.setRowCount(0);
		List<Employee> list = DBConnection.empBe(sql);
		for (Employee employee : list) {
			model.addRow(new Object[] { employee.getId(), employee.getLastName(), employee.getFirstName(),
					employee.getCity() });
		}
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
