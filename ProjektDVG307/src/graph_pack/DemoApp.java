package graph_pack;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dijkstrasAlgorithm.Graph;
import dijkstrasAlgorithm.Vertex;

@SuppressWarnings("serial")
public class DemoApp extends JFrame
  {
  private MapPanel mapPanel;
  private Graph graph = new Graph(); //"Falun", 40000, 10, 3 //"Gävle", 100000, 100, 8

  public DemoApp()
    {
    super("Dijkstra visualization");
    Font font = new Font(Font.MONOSPACED, Font.BOLD, 16);
    mapPanel = new MapPanel(graph);
    mapPanel.setFont(font);
    add(mapPanel);
    makeMenues();
    setBackground(Color.WHITE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(640, 1024);
    setLocationRelativeTo(null);
    setVisible(true);
    }
  
  public void makeMenues()
    {
    JMenuBar menuBar = new JMenuBar();
    JMenu help = new JMenu("Help");
    JMenuItem mouseMI = new JMenuItem("Mouse commands");
    mouseMI.addActionListener((e) -> showMouseHelp());
    JMenuItem keybMI = new JMenuItem("Keyboard commands");
    keybMI.addActionListener((e) -> showKeyboardHelp());
    JMenuItem aboutMI = new JMenuItem("About this tool");
    aboutMI.addActionListener((e) -> showAbout());
    help.add(mouseMI);
    help.add(keybMI);
    help.add(aboutMI);
    menuBar.add(help);
    this.setJMenuBar(menuBar);
    }
  
  public void showKeyboardHelp()
    {
    String html = "<html><table>"
                + "<tr><th align=\"left\">Key</th><th align=\"left\">Action</th></tr>"
                + "<tr><td><b>&#8592</b></td><td>Rotate left</td></tr>"
                + "<tr><td><b>&#8594</b></td><td>Rotate right</td></tr>"
                + "<tr><td><b>C</b></td><td>Reset</td></tr>"
                + "<tr><td><b>ENTER</b></td><td>Choose points</td></tr>"
                + "<tr><td><b>SPACE</b></td><td>Choose only start point</td></tr>"
                + "</table>";
    JOptionPane.showMessageDialog(this, html, "Keyboard Commands", JOptionPane.PLAIN_MESSAGE);
    }

  public void showMouseHelp()
    {
    String html = "<html><p>Klick and drag to rotate</p><p>Klick vertex to show spanning tree";
    JOptionPane.showMessageDialog(this, html, "Mouse Commands", JOptionPane.PLAIN_MESSAGE);
    }

  public void showAbout()
    {
    String html = "<html><table>"
                + "<tr><th align=\"center\">Dijkstra visualization</th></tr>"
                + "<tr><td></td></tr>"
                + "<tr><td align=\"center\">Version 1.0</td></tr>"
                + "<tr><td align=\"center\">2024-05-15</td></tr>"
                + "<tr><td align=\"center\">Copyright &#169 2024 - 2030 Jonas Boustedt</td></tr>"
                + "<tr><td align=\"center\">Alla rättigheter förbehålls</td></tr>"
                + "</table>";
    JOptionPane.showMessageDialog(this, html, "About Dijkstra visualization", JOptionPane.PLAIN_MESSAGE); 
    }
  private void loadData()
    {
    graph.readVertexFile("H:\\git\\DVG307-Project\\ProjektDVG307\\src\\760_tatorter.csv");
    graph.readEdgeFile("H:\\git\\DVG307-Project\\ProjektDVG307\\src\\edges_760_tatorter.csv");
    // Demo. Replace by reading the data files
	  /*
    graph.addVertex("Gävle", 100000, 100, 8);
    graph.addVertex("Valbo", 5000, 90, 5);
    graph.addVertex("Sandviken", 30000, 79, 4);
    graph.addVertex("Högbo", 500, 83, 10);
    graph.addVertex("Ockelbo", 20000, 85, 40);
    graph.addVertex("Hagsta", 10, 99, 40);
    graph.addVertex("Falun", 40000, 10, 3);
    graph.addVertex("Edsbyn", 10000, 20, 85);
    graph.addVertex("Bollnäs", 30000, 56, 80);
    graph.addVertex("Tönnebro", 10, 95, 60);
    graph.connectVertices("Gävle", "Valbo", 10000);
    graph.connectVertices("Gävle", "Hagsta", 30000);
    graph.connectVertices("Hagsta", "Ockelbo", 20000);
    graph.connectVertices("Hagsta", "Tönnebro", 10000);
    graph.connectVertices("Ockelbo", "Högbo", 35000);
    graph.connectVertices("Högbo", "Sandviken", 5000);
    graph.connectVertices("Valbo", "Sandviken", 11000);
    graph.connectVertices("Sandviken", "Falun", 69000);
    graph.connectVertices("Falun", "Edsbyn", 100000);
    graph.connectVertices("Edsbyn", "Bollnäs", 35000);
    graph.connectVertices("Bollnäs", "Tönnebro", 50000);
    */
    }
  
  public static void main(String [] args)
    {
    SwingUtilities.invokeLater(new Runnable() {
    public void run()
      {
      DemoApp app = new DemoApp();
      app.loadData();
      }
    });
    }
  }