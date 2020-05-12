package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

public class SuccessUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public SuccessUpdate() {
		setBounds(100, 100, 229, 158);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u6210\u529F\uFF01\u662F\u5426\u6536\u85CF\u4E3A\u5E38\u7528\u5730\u5740\uFF1F");
			contentPanel.add(lblNewLabel, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u662F");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String s = "";
						s = MainJFrame.ip + "/" + MainJFrame.dns + "/" + MainJFrame.gate;
						Set<String> set=new HashSet<String>(MainJFrame.save_list);
						if(set.contains(s)) {
							MainJFrame.success.setVisible(false);
							MainJFrame.str = "已存在收藏列表中，收藏失败！";
							MainJFrame.dialog = new Dialog();
							MainJFrame.dialog.setVisible(true);
						} else {
							MainJFrame.save_list.add(s);
							MainJFrame.write(MainJFrame.save_list);
							MainJFrame.success.setVisible(false);
							MainJFrame.str = "收藏成功！";
							//MainJFrame.updateTable();
							MainJFrame.dialog = new Dialog();
							MainJFrame.dialog.setVisible(true);
						}					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u5426");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MainJFrame.success.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
