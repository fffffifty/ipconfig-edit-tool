package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class MainJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField ip_txt;
	private JTextField dns_txt;
	private JTextField gate_txt;
	JTable table;
	JScrollPane scrollPane;
	JButton btn_update;
	
	public static SuccessUpdate success = new SuccessUpdate();
	public static Dialog dialog = new Dialog();
	public static List<String> save_list = new ArrayList<String>();
	public static String ip = "dhcp";
	public static String dns = "dhcp";
	public static String gate = "-";
	String ip_cmd = "";
	String dns_cmd = "";
	String bat_url = ".\\update.bat";
	static String save_url = ".\\save.dat";
	public static String str = "";
	String selected_ip = "";
	String selected_dns = "";
	String selected_gate = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainJFrame() throws IOException {
		
		save_list = read(new File(save_url));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP\u5730\u5740\uFF1A");
		lblNewLabel.setBounds(32, 29, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DNS\uFF1A");
		lblNewLabel_1.setBounds(49, 64, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		ip_txt = new JTextField();
		ip_txt.setBounds(206, 26, 110, 21);
		contentPane.add(ip_txt);
		ip_txt.setColumns(10);
		ip_txt.setText("-");
		ip_txt.setVisible(false);
		
		dns_txt = new JTextField();
		dns_txt.setColumns(10);
		dns_txt.setBounds(206, 61, 110, 21);
		contentPane.add(dns_txt);
		dns_txt.setText("-");
		dns_txt.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("\u9ED8\u8BA4\u7F51\u5173\uFF1A");
		lblNewLabel_2.setBounds(25, 102, 78, 15);
		lblNewLabel_2.setVisible(false);
		contentPane.add(lblNewLabel_2);
		
		gate_txt = new JTextField();
		gate_txt.setColumns(10);
		gate_txt.setBounds(206, 99, 110, 21);
		gate_txt.setText("-");
		gate_txt.setVisible(false);
		contentPane.add(gate_txt);
		
		JButton btn_clear = new JButton("\u6E05\u7A7A");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ip_txt.setText("");
				dns_txt.setText("");
				gate_txt.setText("");
			}
		});
		btn_clear.setBounds(238, 126, 78, 23);
		contentPane.add(btn_clear);
		btn_clear.setVisible(false);
		
		JRadioButton ip_dhcp = new JRadioButton("\u52A8\u6001");
		ip_dhcp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == ip_dhcp) {
					ip = "dhcp";
					gate = "-";
					ip_txt.setText("-");
					ip_txt.setVisible(false);
					lblNewLabel_2.setVisible(false);
					gate_txt.setText("-");
					gate_txt.setVisible(false);
					if(dns == "dhcp") {
						btn_clear.setVisible(false);
					}
				}
			}
		});
		ip_dhcp.setSelected(true);
		ip_dhcp.setBounds(81, 25, 54, 23);
		contentPane.add(ip_dhcp);
		
		JRadioButton ip_static = new JRadioButton("\u9759\u6001");
		ip_static.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == ip_static) {
					ip_txt.setText("");
					gate_txt.setText("");
					ip_txt.setVisible(true);
					lblNewLabel_2.setVisible(true);
					gate_txt.setVisible(true);
					btn_clear.setVisible(true);
					ip = "";
					gate = "";
				}
			}
		});
		ip_static.setBounds(139, 25, 54, 23);
		contentPane.add(ip_static);
		
		JRadioButton dns_dhcp = new JRadioButton("\u52A8\u6001");
		dns_dhcp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == dns_dhcp) {
					dns = "dhcp";
					dns_txt.setText("-");
					dns_txt.setVisible(false);
					if(ip == "dhcp") {
						btn_clear.setVisible(false);
					}
				}
			}
		});
		dns_dhcp.setSelected(true);
		dns_dhcp.setBounds(81, 60, 54, 23);
		contentPane.add(dns_dhcp);
		
		JRadioButton dns_static = new JRadioButton("\u9759\u6001");
		dns_static.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == dns_static) {
					dns_txt.setText("");
					dns_txt.setVisible(true);
					btn_clear.setVisible(true);
					dns = "";
				}
			}
		});
		dns_static.setBounds(139, 60, 54, 23);
		contentPane.add(dns_static);
		
		//定义按钮组
		ButtonGroup ip_group=new ButtonGroup();
		ip_group.add(ip_dhcp);
		ip_group.add(ip_static);
		//定义按钮组
		ButtonGroup dns_group=new ButtonGroup();
		dns_group.add(dns_dhcp);
		dns_group.add(dns_static);
		
		JButton btn_ok = new JButton("\u4FEE\u6539");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ip != "dhcp") {
					ip = ip_txt.getText();
					gate = gate_txt.getText();
				}
				if(dns != "dhcp") {
					dns = dns_txt.getText();
				}
				if(ip.equals("dhcp")) {
					ip_cmd = "netsh interface ip set address name=\"以太网\" source=dhcp" + "\r\n" + "netsh interface ip set address name=\"本地连接\" source=dhcp" + "\r\n";
				}else {
					ip_cmd = "netsh interface ip set address name=\"以太网\" source=static addr=" + ip + " mask=255.255.255.0 gateway=" + gate + "\r\n"
							+ "netsh interface ip set address name=\"本地连接\" source=static addr=" + ip + " mask=255.255.255.0 gateway=" + gate + "\r\n";
				}
				if(dns.equals("dhcp")) {
					dns_cmd = "netsh interface ip set dns name=\"以太网\" source=dhcp" + "\r\n" + "netsh interface ip set dns name=\"本地连接\" source=dhcp" + "\r\n";
				}else {
					dns_cmd = "netsh interface ip set dns name=\"以太网\" source=static addr=" + dns + "\r\n" 
							+ "netsh interface ip set dns name=\"本地连接\" source=static addr=" + dns + "\r\n";
				}
				System.out.println("ip_cmd:" + ip_cmd);
				System.out.println("dns_cmd:" + dns_cmd);
				try {
					String cmd = "chcp 65001" + "\r\n" + "%1 mshta vbscript:CreateObject(\"Shell.Application\").ShellExecute(\"cmd.exe\",\"/c %~s0 ::\",\"\",\"runas\",1)(window.close)&&exit" + "\r\n"+ ip_cmd + dns_cmd;
					// 生成bat文件
					File file = new File(bat_url);
//					if(!file.exists()) {
//						file.mkdir();
//					}
					file.delete();
					file.createNewFile();
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
					writer.write(cmd);
					writer.close();
					// 运行bat文件
					StringBuilder sb = new StringBuilder();
			        try {
			            Process child = Runtime.getRuntime().exec(bat_url);
			            try {
			                child.waitFor();
			            } catch (InterruptedException e1) {
			                System.out.println(e1);
			            }
			            success.setVisible(true);        
			        } catch (IOException e1) {
			        	str = "修改失败！请输入正确地址！";
			        	dialog = new Dialog();
			        	dialog.setVisible(true);
			            System.out.println(e1);
			        }
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn_ok.setBounds(126, 126, 78, 23);
		contentPane.add(btn_ok);

        save_list = read(new File(save_url));
        table = createTable(save_list);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getValueAt(table.getSelectedRow(), 0) != null) {
					btn_update.setVisible(true);
					selected_ip = (String) table.getValueAt(table.getSelectedRow(), 0);
					selected_dns = (String) table.getValueAt(table.getSelectedRow(), 1);
					selected_gate = (String) table.getValueAt(table.getSelectedRow(), 2);
				}
			}
		});
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
        
        scrollPane=new JScrollPane(table);
        scrollPane.setLocation(10, 200);
        scrollPane.setBackground(Color.red);
        scrollPane.setSize(309, 157);
        contentPane.add(scrollPane);
        
        btn_update = new JButton("\u70B9\u51FB\u8BBE\u7F6E");
        btn_update.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {       		
//        		System.out.println("点击设置:" + selected_ip + "/" + selected_dns + "/" + selected_gate);
//        		System.out.println(selected_ip.equals("dhcp"));
				if(selected_ip.equals("dhcp")) {
					ip_cmd = "netsh interface ip set address name=\"以太网\" source=dhcp" + "\r\n" + "netsh interface ip set address name=\"本地连接\" source=dhcp" + "\r\n";
				}else {
					ip_cmd = "netsh interface ip set address name=\"以太网\" source=static addr=" + selected_ip + " mask=255.255.255.0 gateway=" + selected_gate + "\r\n"
							+ "netsh interface ip set address name=\"本地连接\" source=static addr=" + selected_ip + " mask=255.255.255.0 gateway=" + selected_gate + "\r\n";
				}
				if(selected_dns.equals("dhcp")) {
					dns_cmd = "netsh interface ip set dns name=\"以太网\" source=dhcp" + "\r\n" + "netsh interface ip set dns name=\"本地连接\" source=dhcp" + "\r\n";
				}else {
					dns_cmd = "netsh interface ip set dns name=\"以太网\" source=static addr=" + selected_dns + "\r\n" 
							+ "netsh interface ip set dns name=\"本地连接\" source=static addr=" + selected_dns + "\r\n";
				}
				
				try {
					String cmd = "chcp 65001" + "\r\n" + "%1 mshta vbscript:CreateObject(\"Shell.Application\").ShellExecute(\"cmd.exe\",\"/c %~s0 ::\",\"\",\"runas\",1)(window.close)&&exit" + "\r\n"+ ip_cmd + dns_cmd;
					// 生成bat文件
					File file = new File(bat_url);
//					if(!file.exists()) {
//						file.mkdir();
//					}
					file.delete();
					file.createNewFile();
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
					writer.write(cmd);
					writer.close();
					// 运行bat文件
					StringBuilder sb = new StringBuilder();
			        try {
			            Process child = Runtime.getRuntime().exec(bat_url);
			            try {
			                child.waitFor();
			            } catch (InterruptedException e1) {
			                System.out.println(e1);
			            }
			            str = "修改成功！";
			        	dialog = new Dialog();
			        	dialog.setVisible(true);        
			        } catch (IOException e1) {
			        	str = "修改失败！";
			        	dialog = new Dialog();
			        	dialog.setVisible(true); 
			            System.out.println(e1);
			        }
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        btn_update.setBounds(126, 367, 90, 23);
        contentPane.add(btn_update);
        btn_update.setVisible(false);
        
        JLabel lblNewLabel_3 = new JLabel("\u4ECE\u6536\u85CF\u5217\u8868\u4E2D\u9009\u62E9\uFF1A");
        lblNewLabel_3.setBounds(10, 175, 194, 15);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("\u624B\u52A8\u8BBE\u7F6E\u5730\u5740\uFF08\u4FEE\u6539\u6210\u529F\u5EFA\u8BAE\u6536\u85CF\uFF09\uFF1A");
        lblNewLabel_4.setBounds(10, 4, 225, 15);
        contentPane.add(lblNewLabel_4);
        
//        JTextArea textArea = new JTextArea();
//        textArea.setBounds(352, 4, 204, 157);
//        textArea.setText("实现功能：" + "\n" + "1.手动设置地址" + "\n" + "2.添加常用收藏地址供选择" + "\n" + "功能待完善：" + "\n" + "1.手动输入地址未加判定条件" + "\n"  + "2.收藏成功后列表不刷新，需重启");
//        contentPane.add(textArea);
	}
	
	static void write(List<String> list) {
		FileWriter fw;
		try {
			fw = new FileWriter(save_url);
			for (int i = 0; i < list.size(); i++) {
			      fw.write(list.get(i) + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static List<String> read(File file) throws IOException {
		if(file.exists()) {
			BufferedReader in = new BufferedReader(new FileReader(file));  //
			String line;  //一行数据
			List<String> list = new ArrayList<>();
			while((line = in.readLine()) != null){
				String[] temp = line.split("\t");
				list.add(line);
			}
			in.close();
			return list;
		} else {
			List<String> list = new ArrayList<>();
			return list;
		}
	}
	JTable createTable(List<String> list) {
		DefaultTableModel model;
		if(list.size() == 0) {
			Object[][] data = new String[0][3];
			String[] name = {"IP", "DNS", "默认网关"};
			model = new DefaultTableModel(data, name);
		} else {
			Object[][] data = new String[list.size()][3];
			String[] name = {"IP", "DNS", "默认网关"};
			for(int i = 0; i < list.size(); i++) {
				String str[] = list.get(i).split("/");
				for(int j = 0; j < str.length; j++) {
					data[i][j] = str[j];
					System.out.println("==============" + data[i][j]);
				}
				System.out.println("//////////////" + data[i]);
			}
			model = new DefaultTableModel(data, name);
		}
		JTable table  = new JTable(model);
		return table;
	}
}
