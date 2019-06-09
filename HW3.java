import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.ScrollPane;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.AbstractListModel;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.Point;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import java.awt.List;
import java.awt.Choice;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import java.awt.Rectangle;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class HW3 extends javax.swing.JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField actor_1;
	private JTextField actor_2;
	private JTextField actor_3;
	private JTextField actor_4;
	private JTextField director;
	private JTextField value_text;
	private JTextField from_text;
	private JTextField to_text;
	Jdbc jdbc=new Jdbc();
	ArrayList<String> genre=new ArrayList<String>();
	ArrayList<String> country=new ArrayList<String>();
	ArrayList<String> tags=new ArrayList<String>();
	String movieID;
	static JTextPane query_text;
	private JTextField search_text;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HW3 frame = new HW3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HW3() {
		setDefaultCloseOperation(HW3.EXIT_ON_CLOSE);
		setBounds(100, 100, 1326, 1022);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextArea txtrMovieAttributes = new JTextArea();
		txtrMovieAttributes.setBounds(5, 13, 538, 60);
		txtrMovieAttributes.setTabSize(16);
		txtrMovieAttributes.setRows(1);
		txtrMovieAttributes.setForeground(new Color(255, 255, 224));
		txtrMovieAttributes.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtrMovieAttributes.setBackground(new Color(47, 79, 79));
		txtrMovieAttributes.setText("                                                       \r\n                                                   Movie Attributes :");
		contentPane.setLayout(null);
		contentPane.add(txtrMovieAttributes);
		
	    query_text = new JTextPane();
		query_text.setBackground(SystemColor.menu);
		query_text.setBounds(15, 444, 528, 291);
		contentPane.add(query_text);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(15, 80, 528, 300);
		contentPane.add(tabbedPane_1);
		
		Panel panel = new Panel();
		tabbedPane_1.addTab("Genres & Year", null, panel, null);
		panel.setForeground(new Color(95, 158, 160));
		panel.setBackground(SystemColor.menu);
		

		JCheckBox chckbxShort = new JCheckBox("Short");
		chckbxShort.setBounds(163, 25, 163, 23);
		chckbxShort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Short");
			}
		});
		panel.setLayout(null);
		
		JComboBox and_or_1 = new JComboBox();
		and_or_1.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		and_or_1.setBounds(309, 39, 109, 22);
		panel.add(and_or_1);
		
		JLabel label_3 = new JLabel("TO :");
		label_3.setBounds(324, 155, 78, 31);
		label_3.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		panel.add(label_3);
		
		JLabel label_2 = new JLabel("FROM :");
		label_2.setBounds(324, 102, 78, 31);
		label_2.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		panel.add(label_2);
		
		JLabel label_4 = new JLabel("Search Between Attributes' Values :");
		label_4.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		label_4.setBounds(252, 2, 265, 31);
		panel.add(label_4);
		panel.add(chckbxShort);
		
		JCheckBox chckbxScifi = new JCheckBox("Sci-Fi");
		chckbxScifi.setBounds(0, 25, 163, 23);
		chckbxScifi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Sci-Fi");
			}
			
		});
		panel.add(chckbxScifi);
		
		JCheckBox chckbxRomance = new JCheckBox("Romance");
		chckbxRomance.setBounds(0, 48, 163, 23);
		chckbxRomance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Romance");
			}
		});
		panel.add(chckbxRomance);
		
		JCheckBox chckbxMystery = new JCheckBox("Mystery");
		chckbxMystery.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		chckbxMystery.setBounds(0, 71, 163, 23);
		chckbxMystery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!genre.contains("Mystery"))
				{
				genre.add("Mystery");
				}
				else
				{
					genre.remove("Mystery");
				}
			}
		});
		panel.add(chckbxMystery);
		
		JCheckBox chckbxMusical = new JCheckBox("Musical");
		chckbxMusical.setBounds(0, 94, 163, 23);
		chckbxMusical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!genre.contains("Musical"))
				{
				genre.add("Musical");
				}
				else
				{
					genre.remove("Musical");
				}
			}
		});
		panel.add(chckbxMusical);
		
		JCheckBox chckbxImax = new JCheckBox("IMAX");
		chckbxImax.setBounds(0, 117, 163, 23);
		chckbxImax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!genre.contains("IMAX"))
				{
				genre.add("IMAX");
				}
				else
				{
					genre.remove("IMAX");
				}
			}
		});
		panel.add(chckbxImax);
		
		JCheckBox chckbxHorror = new JCheckBox("Horror");
		chckbxHorror.setBounds(0, 140, 163, 23);
		chckbxHorror.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!genre.contains("Horror"))
				{
				genre.add("Horror");
				}
				else
				{
					genre.remove("Horror");
				}
			}
		});
		panel.add(chckbxHorror);
		
		JCheckBox chckbxFilmnoir = new JCheckBox("Film-Noir");
		chckbxFilmnoir.setBounds(0, 163, 163, 23);
		chckbxFilmnoir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Film-Noir");
			}
		});
		panel.add(chckbxFilmnoir);
		
		JCheckBox chckbxFantasy = new JCheckBox("Fantasy");
		chckbxFantasy.setBounds(0, 186, 163, 23);
		chckbxFantasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Fantasy");
			}
		});
		panel.add(chckbxFantasy);
		
		JCheckBox chckbxWestern = new JCheckBox("Western");
		chckbxWestern.setBounds(0, 209, 163, 23);
		chckbxWestern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Western");
			}
		});
		panel.add(chckbxWestern);
		
		JCheckBox chckbxWar = new JCheckBox("War");
		chckbxWar.setBounds(163, 2, 163, 23);
		chckbxWar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("War");
			}
		});
		panel.add(chckbxWar);
		
		JCheckBox chckbxAction = new JCheckBox("Action");
		chckbxAction.setBounds(0, 1, 163, 23);
		chckbxAction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Action");
			}
		});
		panel.add(chckbxAction);
		
		JCheckBox chckbxAdventure = new JCheckBox("Adventure");
		chckbxAdventure.setBounds(163, 47, 163, 25);
		chckbxAdventure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Adventure");
			}
		});
		panel.add(chckbxAdventure);
		
		JCheckBox chckbxAnimation = new JCheckBox("Animation");
		chckbxAnimation.setBounds(163, 70, 163, 25);
		chckbxAnimation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Animation");
			}
		});
		panel.add(chckbxAnimation);
		
		JCheckBox chckbxChildren = new JCheckBox("Children");
		chckbxChildren.setBounds(163, 93, 163, 25);
		chckbxChildren.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Children");
			}
		});
		panel.add(chckbxChildren);
		
		JCheckBox chckbxComedy = new JCheckBox("Comedy");
		chckbxComedy.setBounds(163, 116, 163, 25);
		chckbxComedy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Comedy");
			}
		});
		panel.add(chckbxComedy);
		
		JCheckBox chckbxCrime = new JCheckBox("Crime");
		chckbxCrime.setBounds(163, 139, 163, 25);
		chckbxCrime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Crime");
			}
		});
		panel.add(chckbxCrime);
		
		JCheckBox chckbxDocumentary = new JCheckBox("Documentary");
		chckbxDocumentary.setBounds(163, 162, 163, 25);
		chckbxDocumentary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Documentary");
			}
		});
		panel.add(chckbxDocumentary);
		
		JCheckBox chckbxDrama = new JCheckBox("Drama");
		chckbxDrama.setBounds(163, 185, 163, 25);
		chckbxDrama.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Drama");
			}
		});
		panel.add(chckbxDrama);
		
		JCheckBox checkBox = new JCheckBox("Thriller");
		checkBox.setBounds(163, 208, 163, 25);
		checkBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genre.add("Thriller");
			}
		});
		panel.add(checkBox);
		
		JLabel label_1 = new JLabel("Select the Period");
		label_1.setBounds(339, 67, 125, 31);
		label_1.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		panel.add(label_1);
		
		from_text = new JTextField();
		from_text.setBounds(387, 106, 100, 22);
		from_text.setColumns(10);
		panel.add(from_text);
		
		to_text = new JTextField();
		to_text.setBounds(387, 159, 100, 22);
		to_text.setColumns(10);
		panel.add(to_text);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("Countries", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton ok_2 = new JButton("OK");
		ok_2.setBounds(426, 232, 97, 25);
		panel_2.add(ok_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 523, 230);
		panel_2.add(scrollPane_1);
		
		Panel panel_country = new Panel();
		scrollPane_1.setViewportView(panel_country);
		panel_country.setSize(521,500);
		panel_country.setLayout(new GridLayout(0, 4, 0, 0));
		
		JComboBox and_or_2 = new JComboBox();
		and_or_2.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		and_or_2.setBounds(154, 235, 117, 22);
		panel_2.add(and_or_2);
		
		JLabel lblAndor = new JLabel("AND/OR :");
		lblAndor.setBounds(86, 236, 56, 16);
		panel_2.add(lblAndor);
		ok_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String and_or=and_or_2.getSelectedItem().toString();
				jdbc.executeFilterCountry(country,and_or);
				
			}
		});
		
		
		
		JButton ok_1 = new JButton("OK");
		ok_1.setBounds(367, 221, 97, 25);
		ok_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int from=Integer.parseInt(from_text.getText().trim());
				int to=Integer.parseInt(to_text.getText().trim());
				String and_or=and_or_1.getSelectedItem().toString();
				
				ArrayList<String>countries=jdbc.executeQueryForGenre(genre,from,to,and_or);
				
				int i=0;
				for(String string_country : countries)
				{
				int increaseY=24*i;
				JCheckBox x = new JCheckBox(string_country);
				x.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(!country.contains(string_country))
						{
						country.add(string_country);
						}
						else
						{
							country.remove(string_country);
						}
					}
				});
				x.setBounds(0, 1+increaseY, 163, 23);
				panel_country.add(x);
				i++;
				}


			}
		});
		panel.add(ok_1);
		
		
		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("Cast", null, panel_3, null);
		panel_3.setLayout(null);
		
		actor_1 = new JTextField();
		actor_1.setBounds(12, 97, 204, 22);
		panel_3.add(actor_1);
		actor_1.setColumns(10);
		
		JLabel lblActorsActresses = new JLabel("Actors / Actresses");
		lblActorsActresses.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		lblActorsActresses.setBounds(12, 47, 204, 45);
		panel_3.add(lblActorsActresses);
		
		actor_2 = new JTextField();
		actor_2.setColumns(10);
		actor_2.setBounds(12, 132, 204, 22);
		panel_3.add(actor_2);
		
		actor_3 = new JTextField();
		actor_3.setColumns(10);
		actor_3.setBounds(12, 167, 204, 22);
		panel_3.add(actor_3);
		
		actor_4 = new JTextField();
		actor_4.setColumns(10);
		actor_4.setBounds(12, 202, 204, 22);
		panel_3.add(actor_4);
		
		JLabel label = new JLabel("Director");
		label.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		label.setBounds(291, 47, 204, 45);
		panel_3.add(label);
		
		director = new JTextField();
		director.setColumns(10);
		director.setBounds(291, 97, 204, 22);
		panel_3.add(director);
	
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEditable(true);
//		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"gdxc", "vzxc", "v", "xcz", "zfg", "xcv", "cx", "gvxdfd", "g", "gdz", "zdg", "zf", "vxf", "", "df"}));
		comboBox_2.setBounds(291, 132, 204, 22);
		panel_3.add(comboBox_2);
	
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String search=search_text.getText();
				search=search.trim();
				String query="Select distinct m.actorName from movie_actors m, result1 r  where r.movieID=m.movieID and actorName like \'%"+search+"%\';";
				ArrayList<String> castname=jdbc.searchCastName(query);
				query="Select distinct d.directorName from movie_directors d, result1 r  where r.movieID=d.movieID and directorName like \'%"+search+"%\';";
				castname.addAll(jdbc.searchCastName(query));
				String[] cast_array = castname.toArray(new String[0]);
				comboBox_2.setModel(new DefaultComboBoxModel(cast_array));

			}
		});
		btnSearch.setBounds(255, 11, 97, 25);
		panel_3.add(btnSearch);
		
		
		search_text = new JTextField();
		search_text.setBounds(71, 12, 172, 22);
		panel_3.add(search_text);
		search_text.setColumns(10);
		

		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("Tag IDs and Values", null, panel_4, null);
		panel_4.setLayout(null);
		

		JComboBox and_or_3 = new JComboBox();
		and_or_3.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		and_or_3.setBounds(369, 167, 117, 22);
		panel_3.add(and_or_3);
		
		Button ok_3 = new Button("OK");
		ok_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<String> actors=new ArrayList<String>();
				if(!actor_1.getText().equals(""))
				actors.add(actor_1.getText().trim());
				if(actor_2.getText().equals(""))
				actors.add(actor_2.getText().trim());
				if(actor_3.getText().equals(""))
				actors.add(actor_3.getText().trim());
				if(actor_4.getText().equals(""))
				actors.add(actor_4.getText().trim());
				String director1="";
				String sql="";
				if(!director.getText().equals(""))
				{
				director1=director.getText().trim();
				sql="create or replace view result2(movieID) as select distinct r.movieID from movie_actors a, result1 r, movie_directors d where r.movieID=a.movieID and r.movieID=d.movieID and d.directorName=\'"+director1+"\' and (";
				}
				else
				{
					sql="create or replace view result2(movieID) as select distinct r.movieID from movie_actors a, result1 r, movie_directors d where r.movieID=a.movieID and r.movieID=d.movieID and (";
				}
				String and_or=and_or_3.getSelectedItem().toString();
				System.out.println(sql);
				jdbc.executeActorDirector(actors,sql,and_or);
				
				
				
				System.out.println("418");
				
				JPanel tag_panel = new JPanel();
				tag_panel.setBounds(0, 0, 521, 178);
				panel_4.add(tag_panel);
				tag_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				tag_panel.setLayout(null);
				
		
				 sql="Select distinct t.id, t.value from movie_tags m, result r,tags t where r.movieID=m.movieID and t.id=m.tagID;";
				 ArrayList<String> tag=jdbc.executeTags(sql);
				 System.out.println("423");
				 int i=0;
				 for(String s:tag)
				 {
					 int increaseY=24*i;
						JCheckBox x = new JCheckBox(s);
						x.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if(!tags.contains(s.split(" ")[0]))
								{
									tags.add(s.split(" ")[0]);
								}
								else
								{
									tags.remove(s.split(" ")[0]);
								}
								
								System.out.println(tags);
							}
						});
						x.setBounds(0, 1+increaseY, 163, 23);
						tag_panel.add(x);
						i++; 
				 }
				
			}

		});
		ok_3.setBounds(402, 217, 79, 24);
		panel_3.add(ok_3);
		
		
		JLabel label_5 = new JLabel("AND/OR :");
		label_5.setBounds(301, 168, 56, 16);
		panel_3.add(label_5);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "<", ">", "=", "<=", ">="}));
		comboBox.setBounds(153, 193, 135, 28);
		panel_4.add(comboBox);
		
				
		
		JLabel lblNewLabel = new JLabel("Tag Weight :");
		lblNewLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 196, 117, 22);
		panel_4.add(lblNewLabel);
		
		JLabel lblValues = new JLabel("Value :");
		lblValues.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		lblValues.setBounds(10, 237, 117, 22);
		panel_4.add(lblValues);
		
		value_text = new JTextField();
		value_text.setBounds(153, 237, 135, 22);
		panel_4.add(value_text);
		value_text.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(573, 80, 531, 353);
		contentPane.add(scrollPane_2);
		
		JPanel panel_movie = new JPanel();
		scrollPane_2.setViewportView(panel_movie);

		JComboBox and_or_4 = new JComboBox();
		and_or_4.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		and_or_4.setBounds(373, 191, 117, 22);
		panel_4.add(and_or_4);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sign=comboBox.getSelectedItem().toString();
				int weight=Integer.parseInt(value_text.getText());
				String and_or=and_or_4.getSelectedItem().toString();
				ArrayList<String> movies=jdbc.executeTagMovies(tags,sign,weight,and_or);
				int i=0;
				for(String movie:movies)
				{
					int y=24*i;
					JRadioButton x = new JRadioButton(movie);
					x.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							System.out.println("609"+x.getText());
							//String z[]=x.getText().split("|");
							String s="";
							char ch=movie.charAt(0);
							int a=0;
							while(ch!='|')
							{
								s=s+ch;
								
								a++;
								ch=movie.charAt(a);
							}
							movieID=s;
							System.out.println("613="+movieID);
							
						}
					});
					x.setBounds(583,y, 127, 25);
					panel_movie.add(x);
					String z[]=movie.split("|");
					System.out.println(z[0]);
					i++;
				}
			}
		});
		btnOk.setBounds(414, 234, 97, 25);
		panel_4.add(btnOk);
		

		
		JLabel label_6 = new JLabel("AND/OR :");
		label_6.setBounds(305, 192, 56, 16);
		panel_4.add(label_6);
		
		JTextArea txtrShowQueryHere = new JTextArea();
		txtrShowQueryHere.setText("                                                       \r\n                                             Show Query Here :");
		txtrShowQueryHere.setTabSize(16);
		txtrShowQueryHere.setRows(1);
		txtrShowQueryHere.setForeground(new Color(255, 255, 224));
		txtrShowQueryHere.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtrShowQueryHere.setBackground(new Color(47, 79, 79));
		txtrShowQueryHere.setBounds(5, 382, 538, 60);
		contentPane.add(txtrShowQueryHere);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(573, 517, 528, 373);
		contentPane.add(scrollPane);
		
		JPanel panel_user = new JPanel();
		scrollPane.setViewportView(panel_user);
		
		
		JButton btnExecuteUserQuery = new JButton("Execute User Query");
		btnExecuteUserQuery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sql="select t.userID from user_taggedmovies t where t.movieID="+movieID+";";
				System.out.println(sql);
				ArrayList<String>result=jdbc.executeUserQuery(sql);
				System.out.println(result);
				int i=0;
				for(String s: result)
				{
					int y=24*i;
					JLabel label=new JLabel("User ID"+s);
					label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
					label.setBounds(240, 240+y, 240, 240);
					panel_user.add(label);
					i++;
				}
				HW3.query_text.setText(sql);
				
			}
		});
		btnExecuteUserQuery.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		btnExecuteUserQuery.setBounds(140, 750, 223, 68);
		
		contentPane.add(btnExecuteUserQuery);
		
		JTextArea txtrMovieResults = new JTextArea();
		txtrMovieResults.setText("                                                       \r\n                                                   Movie Results :");
		txtrMovieResults.setTabSize(16);
		txtrMovieResults.setRows(1);
		txtrMovieResults.setForeground(new Color(255, 255, 224));
		txtrMovieResults.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtrMovieResults.setBackground(new Color(47, 79, 79));
		txtrMovieResults.setBounds(573, 15, 528, 60);
		contentPane.add(txtrMovieResults);
		
		JTextArea txtrUserResults = new JTextArea();
		txtrUserResults.setText("                                                       \r\n                                                   User Results :");
		txtrUserResults.setTabSize(16);
		txtrUserResults.setRows(1);
		txtrUserResults.setForeground(new Color(255, 255, 224));
		txtrUserResults.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtrUserResults.setBackground(new Color(47, 79, 79));
		txtrUserResults.setBounds(573, 444, 528, 60);
		contentPane.add(txtrUserResults);
		
		
		
		
		
		
		
		

	}
}
class Jdbc {
	Statement statement;
	Connection connection;
	public Jdbc()
	{
		String driver = "com.mysql.jdbc.Driver";  
	    String url = "jdbc:mysql://localhost/test";
	    String username = "root";
	    String password = "tiger";  
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      this.connection = DriverManager.getConnection(url,username,password);
	      this.statement = connection.createStatement();
	          
	     
	   }catch(Exception e){
	      e.printStackTrace();
	   }
	}
	public ArrayList<String> searchCastName(String sql)
	{
		ArrayList<String> castname=new ArrayList<String>();
		try {
			ResultSet rs=statement.executeQuery(sql);
    		System.out.println(sql);

			while(rs.next())
			{
				castname.add(rs.getString(1).trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return castname;
		
	}
	public ArrayList<String> executeUserQuery(String sql)
	{
		ArrayList<String> result=new ArrayList<String>();
		try {
			ResultSet rs=statement.executeQuery(sql);
    		System.out.println(sql);

			while(rs.next())
			{
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<String> executeTags(String sql)
	{
		ArrayList<String> tags=new ArrayList<String>();
		try {
			ResultSet rs=statement.executeQuery(sql);
    		System.out.println(sql);

			while(rs.next())
			{
				tags.add(rs.getString(1)+" "+rs.getString(2));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HW3.query_text.setText(HW3.query_text.getText()+"/n"+sql);
		return tags;
	}
	public void executeActorDirector(ArrayList<String> actors,String query,String and_or)
	{
		int size=actors.size();
		for(int i=0; i<size-1;i++)
		{
			String value=actors.get(i);
			query=query+"a.actorName=\'"+value+"\' "+and_or+" ";
		}
		String value="a.actorName=\'"+actors.get(size-1)+"\'";
		query=query+value+");";
		try {
			statement.execute(query);
			System.out.println(query);

			/*String replace_view="create or replace view result (movieID) as (select movieID from result1);";
			statement.execute(replace_view);
			System.out.println(replace_view);

			statement.execute("drop view result1;");
			System.out.println("drop view result1;");*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		HW3.query_text.setText(query);
	}
	public void executeFilterCountry(ArrayList<String> country,String and_or)
	{
		
		String query="create or replace view result1(movieID) as Select distinct r.movieID from result r, movie_countries c where r.movieID=c.movieID and country = \'";
		int size=country.size();
		for(int i=0; i<size-1;i++)
		{
			String value=country.get(i);
			query=query+value+"\' "+and_or+" country=\'";
		}
		String value=country.get(size-1);
		query=query+value+"\';";
		try {
			
			statement.execute(query);
			System.out.println(query);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HW3.query_text.setText(query);
	}
    public void insertData(String sql)
   {
	   try {
		statement.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    
	    System.out.println(sql);
		e.printStackTrace();
	}
   }
    public  ArrayList<String> executeTagMovies(ArrayList<String> tags,String sign,int weight,String and_or)
    {
    	String sql="select distinct r1.movieID from result2 r1, movie_tags m1 where ";
    	String suffix="and ("+weight+sign+" ANY (select distinct m2.tagWeight from result2 r2, movie_tags m2 where r2.movieID=m2.movieID ))";
		ArrayList<String> movies=new ArrayList<String>();
		System.out.println(tags);
    	for(int i=0;i<tags.size()-1;i++)
    	{
    		
    		sql=sql+" tagID= \'"+tags.get(i)+"\' "+and_or+" ";
    	}
    	if(tags.size()!=1)
    	{
    		sql=sql+"tagID= \'"+tags.get(tags.size()-1)+"\' ";
    	}
    	
    	sql+=suffix;
    	String sql2=sql;
    	sql+=";";
    	try {
    		String view_sql="create or replace view result3(movieID) as ("+sql2+");";
    		
			statement.execute(view_sql);
    		System.out.println(view_sql);

			
			
			
			sql="select distinct m.id,m.title,g.genre, m.year,m.rtAudienceScore,m.rtAudienceNumRatings from movies m, result3 r, movie_genres g where m.id=r.movieID and g.movieID=r.movieID;";
			ResultSet rs=statement.executeQuery(sql);
			System.out.println(sql);

			String ans="";
			while(rs.next())
			{
				ans=rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6);
				movies.add(ans);
			}
			HW3.query_text.setText(view_sql+"\n"+sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;

    }
   public ArrayList<String> executeQueryForGenre(ArrayList<String> genre,int from,int to,String and_or)
   {
	   int size=genre.size();
	 
	   String query="Select distinct c.country from movie_countries c, movie_genres g,movies m where m.id=c.movieID and c.movieID=g.movieID and m.year between "+from+" and "+to+" and (";
	   String view_query2="Create or replace view result (movieID) as Select distinct c.movieID from movie_countries c, movie_genres g, movies m where m.id=c.movieID and c.movieID=g.movieID and m.year between "+from+" and "+to+" and (";
	   for(int i=0;i<size-1;i++)
	   {
		   String value="genre="+"\'"+genre.get(i)+"\' "+and_or+" ";
		   query+=value;
		   view_query2+=value;
	   }
	   		String value="genre="+"\'"+genre.get(size-1)+"\'"+");";
	   		query+=value;
			view_query2+=value;
	   		ResultSet rs;
	   		ArrayList<String> countries=new ArrayList<String>();

			try {
				statement.execute(view_query2);
	    		System.out.println(view_query2);

				rs = statement.executeQuery(query);
	    		System.out.println(query);

	   		while(rs.next())
	   		{
	   				countries.add(rs.getString("country"));
	   			
	   			
	   		}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HW3.query_text.setText(query);
	   		return countries;
	   
   }
   public void close()
   {
	   try {
		statement.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    
		e.printStackTrace();
	}
	   try {
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    

		e.printStackTrace();
	}
   }
   
}