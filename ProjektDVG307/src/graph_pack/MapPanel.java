package graph_pack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriterSettings;

import dijkstrasAlgorithm.Edge;
import dijkstrasAlgorithm.Vertex;

@SuppressWarnings("serial")
public class MapPanel extends JPanel
  {
  private final double SCALE = 0.9;
  private IGraph model;
  private double rotation = 0.0;
  private double x_min_data, x_max_data, y_min_data, y_max_data;
  private double x_mid_data, y_mid_data, x_scale, y_scale;
  private Stroke bs1 = new BasicStroke(1);
  private Stroke bs4 = new BasicStroke(3);

  public MapPanel(IGraph model)
    {
    super(true);
    this.model = model;
    this.setBackground(Color.WHITE);
    setFocusable(true);
    requestFocus();
    addListeners();
    }
  private void findLimits()
    {
    boolean first = true;
    for(Vertex v : model.getVertices())
      {
      double x = v.getLongitude();
      double y = v.getLatitude();
      if(first)
        {
        x_min_data = x_max_data = x;
        y_min_data = y_max_data = y;
        first = false;
        }
      else
        {
        if(x < x_min_data)
          x_min_data = x;
        if(x > x_max_data)
          x_max_data = x;
        if(y < y_min_data)
          y_min_data = y;
        if(y > y_max_data)
          y_max_data = y;
        }
      }  
    }
  //Runs Dijkstra's algorithm with a specified start vertex
  public void runTestOneRun() {
		model.clear();
		JPanel dialog = new JPanel(new GridLayout(1, 2));
		
		JTextField fromVertex = new JTextField();
		
		dialog.add(new JLabel("Start"));
		dialog.add(fromVertex);
		
		int dialogResult = JOptionPane.showConfirmDialog(null, dialog, "Choose start vertex", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (dialogResult == 0)
			if(!fromVertex.getText().isBlank()) {
				// set fromVertex
				for(Vertex v : model.getVertices()) {
					if(model.getStartVertex() == null && v.getName().equals(fromVertex.getText())) model.setStartVertex(v);
				}
				
				if(model.getStartVertex() != null) {
					int times = 10;
					double t1 = 0;
					double t2 = 0;
					for(int i = 0; i <times;i++ ) {
						Vertex startVertex = model.getStartVertex();
						t1 += System.currentTimeMillis();
						model.findShortestPath(startVertex); // run algorithm
						t2 += System.currentTimeMillis();
						model.clear();
						model.setStartVertex(startVertex);
					}
					t1 = t1/times;
					t2 = t2/times;
					String timeForTest = "The time it took to do Dijkstra on node "+model.getStartVertex()+" was: "+(t2-t1)+" ms";
					JOptionPane.showMessageDialog(null, new JLabel(timeForTest));
				}else {
					String errOut = "";
					if(model.getStartVertex() == null) errOut += "'" + fromVertex.getText() + "' does not exist | ";
					JOptionPane.showMessageDialog(null, new JLabel(errOut));
				}
			}
	}
  //runs Dijkstra's algorithm on all vertexes
  public void runTest() {
	  double t1 = System.currentTimeMillis();
	  int totNodes= 0;
	  for(Vertex v : model.getVertices()) {
		  totNodes++;
		  model.setStartVertex(v);
		  model.findShortestPath(v);
		  model.clear();
	  }
	  double t2 = System.currentTimeMillis();
	  String timeForTest = "The time it took to do Dijikstra on vertexes "+totNodes+" was: "+(t2-t1)+" ms";
	  JOptionPane.showMessageDialog(null, new JLabel(timeForTest));
  }
  
  //loads data for Dijkstra
  public void loadFiles() {
  		model.readVertexFile("H:\\git\\DVG307-Project\\ProjektDVG307\\src\\760_tatorter.csv");
	  	model.readEdgeFile("H:\\git\\DVG307-Project\\ProjektDVG307\\src\\edges_760_tatorter.csv");
  }
  //loads data for Dijkstra
  public void loadFiles(String vertexPath, String edgePath) {
		model.readVertexFile(vertexPath);
	  	model.readEdgeFile(edgePath);
  }

  //Gets the file path from the user, for the data to be use in Dijkstra
  public void getPaths()
  { 
	  	model.clear();
		JPanel dialog = new JPanel(new GridLayout(2, 2));
		
		JTextField vertexFileName = new JTextField();
		JTextField edgeFileName = new JTextField();
		String vertexFile;
		String edgeFile;
		
		dialog.add(new JLabel("Vertex File Name"));
		dialog.add(vertexFileName);
		dialog.add(new JLabel("Edge File Name"));
		dialog.add(edgeFileName);
		
		int dialogResult = JOptionPane.showConfirmDialog(null, dialog, "Choose file names", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (dialogResult == 0)
			if(!vertexFileName.getText().isBlank() && !edgeFileName.getText().isBlank()) {
				vertexFile = "H:\\git\\DVG307-Project\\ProjektDVG307\\src\\"+vertexFileName.getText()+".csv";
				edgeFile = "H:\\git\\DVG307-Project\\ProjektDVG307\\src\\"+edgeFileName.getText()+".csv";
				if(!vertexFile.equals(edgeFile))
					loadFiles(vertexFile, edgeFile); // run algorithm
				else {
					String errOut = "Cant be same file name!!";
					JOptionPane.showMessageDialog(null, new JLabel(errOut));
				}
			}
      // first create file object for file placed at location 
      // specified by file path 
  } 
  
  //makes dummy data to test time complexity of Dijkstra
  public void writeDataForTest() 
  { 
	  	model.clear();
		JPanel dialog = new JPanel(new GridLayout(3, 2));
		
		JTextField vertexFileName = new JTextField();
		JTextField edgeFileName = new JTextField();
		JTextField number = new JTextField();
		String vertexFile;
		String edgeFile;
		int nbrElements = 0;
		
		dialog.add(new JLabel("Vertex File Name"));
		dialog.add(vertexFileName);
		dialog.add(new JLabel("Edge File Name"));
		dialog.add(edgeFileName);
		dialog.add(new JLabel("Number Of Elements"));
		dialog.add(number);
		
		int dialogResult = JOptionPane.showConfirmDialog(null, dialog, "Choose file names", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (dialogResult == 0)
			if(!vertexFileName.getText().isBlank() && !edgeFileName.getText().isBlank()&& !number.getText().isBlank()) {
				vertexFile = "H:\\git\\DVG307-Project\\ProjektDVG307\\src\\"+vertexFileName.getText()+".csv";
				edgeFile = "H:\\git\\DVG307-Project\\ProjektDVG307\\src\\"+edgeFileName.getText()+".csv";
				nbrElements = Integer.parseInt(number.getText());
				if(!vertexFile.equals(edgeFile))
					makeFiles(vertexFile,edgeFile,nbrElements); // run algorithm
				else {
					String errOut = "Cant be same file name!!";
					JOptionPane.showMessageDialog(null, new JLabel(errOut));
				}
			}
      // first create file object for file placed at location 
      // specified by filepath 
  } 
  
  //Simple script to do dummy data for vertexes and edges
  public void makeFiles(String vertexFile,String edgeFile, int nbrOfElements) {
	  File fileVertex = new File(vertexFile); 
	  File fileEdge = new File(edgeFile); 
      try { 
          FileWriter outputfilevertex = new FileWriter(fileVertex); 
          CsvWriterSettings settings = new CsvWriterSettings();
          settings.getFormat().setLineSeparator("\n");
          settings.trimValues(false);
          CsvWriter writerVertex = new CsvWriter(outputfilevertex,settings); 
    
          String[] headerVertex = { "TATORT;BEF;lon;lat"};
          
          FileWriter outputfileEdges = new FileWriter(fileEdge); 
          CsvWriter writerEdges = new CsvWriter(outputfileEdges,settings); 
    
          String[] headerEdges = { "From;To;Length in meter"}; 
          writerVertex.writeRow(headerVertex);
          writerEdges.writeRow(headerEdges); 
          for(int i = 0; i < nbrOfElements;i++) {
        	  int bef = 5000+(int)(50000*Math.random()+1);
        	  String dataVertex =  "a"+i+";"+bef+";"+"15,774151;61,099355";
        	  writerVertex.writeRow(dataVertex);
          }
          writerVertex.close();
          
          //all vertex will have a edge two too four edges
          for(int i = 0; i < nbrOfElements-1;i++) {
        	  int dist = 2000 +(int)(50000*Math.random()+1);
        	  String dataEdges = "a"+i+";a"+(i+1)+";"+dist;
        	  writerEdges.writeRow(dataEdges);  
        	  if(i<nbrOfElements/2) {
        		  dist = 2000 +(int)(50000*Math.random()+1);
        		  dataEdges = "a"+i+";a"+((nbrOfElements/2)+i)+";"+dist;
        		  writerEdges.writeRow(dataEdges);
        	  }
          }
         
          writerEdges.close();
      } 
      catch (IOException e) { 
          e.printStackTrace(); 
      } 
  }
  
  //Runs Dijkstra's algorithm with a specified start and end vertex, the result will show the shortest path between start and end vertex
	public void dijkstraWithStartEnd() {
		model.clear();
		JPanel dialog = new JPanel(new GridLayout(2, 2));
		
		JTextField fromVertex = new JTextField();
		JTextField toVertex = new JTextField();
		
		dialog.add(new JLabel("Start"));
		dialog.add(fromVertex);
		dialog.add(new JLabel("End"));
		dialog.add(toVertex);
		
		int dialogResult = JOptionPane.showConfirmDialog(null, dialog, "Choose route", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (dialogResult == 0)
			if(!fromVertex.getText().isBlank() && !toVertex.getText().isBlank()) {
				// set fromVertex and toVertex
				for(Vertex v : model.getVertices()) {
					if(model.getStartVertex() == null && v.getName().equals(fromVertex.getText())) model.setStartVertex(v);
					if(model.getTargetVertex() == null && v.getName().equals(toVertex.getText())) model.setTargetVertex(v);
				}
				
				if(model.getStartVertex() != null && model.getTargetVertex() != null)
					model.findShortestPath(model.getStartVertex()); // run algorithm
				else {
					String errOut = "";
					if(model.getStartVertex() == null) errOut += "'" + fromVertex.getText() + "' does not exist | ";
					if(model.getTargetVertex() == null) errOut += "'" + toVertex.getText() + "' does not exist";
					JOptionPane.showMessageDialog(null, new JLabel(errOut));
				}
			}
	}
	//Runs Dijkstra's algorithm with a specified start, the result will show the shortest path from start vertex to every other vertex
	public void dijkstraWithStart() {
		model.clear();
		JPanel dialog = new JPanel(new GridLayout(1, 2));
		
		JTextField fromVertex = new JTextField();
		
		dialog.add(new JLabel("Start"));
		dialog.add(fromVertex);
		
		int dialogResult = JOptionPane.showConfirmDialog(null, dialog, "Choose route", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (dialogResult == 0)
			if(!fromVertex.getText().isBlank()) {
				// set fromVertex and toVertex
				for(Vertex v : model.getVertices()) {
					if(model.getStartVertex() == null && v.getName().equals(fromVertex.getText())) model.setStartVertex(v);
				}
				
				if(model.getStartVertex() != null)
					model.findShortestPath(model.getStartVertex()); // run algorithm
				else {
					String errOut = "";
					if(model.getStartVertex() == null) errOut += "'" + fromVertex.getText() + "' does not exist | ";
					JOptionPane.showMessageDialog(null, new JLabel(errOut));
				}
			}
	}
  
  public void addListeners()
    {
    this.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e)
      {
      switch(e.getKeyCode())
        {
        case KeyEvent.VK_ENTER: // run Dijkstra on a specific start node and end node
        	dijkstraWithStartEnd();
        	break;
        case KeyEvent.VK_SPACE: // run Dijkstra on a specific start node
        	dijkstraWithStart(); 
        	break;
        case KeyEvent.VK_C: // clear
          model.clear();
          break;
        case KeyEvent.VK_S: // search
            getPaths();
            break;
        case KeyEvent.VK_X: // super clear
            model.superClear();
            break;
            
        case KeyEvent.VK_W: // write dummy data
            writeDataForTest();
            break;
        case KeyEvent.VK_L: // load standard data
        	loadFiles();
            break;
        case KeyEvent.VK_T: // run Dijkstra on all nodes
            runTest();
            break;
        case KeyEvent.VK_O: // run Dijkstra on a specific node
        	runTestOneRun();
            break;
        case KeyEvent.VK_LEFT: // rot left
          rotation -= 0.05;
        break;
        case KeyEvent.VK_RIGHT: // rot right
          rotation += 0.05;
          break;
        }
      repaint();
      }

    public void keyReleased(KeyEvent e)
      {
	    	 repaint();
      }
    });
    
    MouseAdapter ma = new MouseAdapter()
      {
      private double x3, y3;
      private void handle(MouseEvent e, boolean dragged)
        {
        if(!dragged) // find startVertex
          {
          x3 = e.getX();
          y3 = e.getY();
          Vertex v = findVertex(x3, y3);
          if(v != null)
            {
            Vertex sv = model.getStartVertex();
            if(sv != null && v != sv)
              model.setTargetVertex(v);
            else
            	if(model.getStartVertex() != null)
            		model.findShortestPath(model.getStartVertex());
            	else System.err.println("StartVertex is null");
            }
          else
            model.clear();
          }
        else // Rotation by drag
          {
          double x2 = getWidth()/2.0;
          double y2 = getHeight()/2.0;
          double x1 = e.getX();
          double y1 = e.getY();
          double dotp = (x1 - x2) * (x3 - x2) + (y1 - y2) * (y3 - y2);
          double mag = Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) * ((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2)));
          if(mag == 0)
            return;
          double cosv = dotp/mag;
          double v = Math.acos(cosv);//*180/Math.PI;
          double crossp = (x1 - x2) * (y3 - y2) - (y1 - y2) * (x3 - x2);
          if(crossp <= 0)
            rotation += v;
          else
            rotation -= v;  
          x3 = x1;
          y3 = y1;
          }
        repaint();
        }
      
      @Override
      public void mousePressed(MouseEvent e)
        {
        handle(e, false);
        }
      
      @Override
      public void mouseReleased(MouseEvent e)
        {
        }
      
      @Override
      public void mouseDragged(MouseEvent e)
        {
        handle(e, true);
        }
      };
    this.addMouseListener(ma);
    this.addMouseMotionListener(ma);
    }
  
  public void paintComponent(Graphics g)
    {
    super.paintComponent(g);
    int width = getWidth();
    int height = getHeight();
    var g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    findLimits();
    double data_width = x_max_data - x_min_data;
    double data_height = y_max_data - y_min_data;
    x_mid_data = x_min_data + data_width/2;
    y_mid_data = y_min_data + data_height/2;
    x_scale = width/data_width;
    y_scale = height/data_height;
    if(model.getStartVertex() != null)
      {
      if(model.getTargetVertex() != null)
        {
        Vertex tv = model.getTargetVertex();
        printShortestPath(g2d, tv, 5, 15, 15);
        }
      else
        g2d.drawString(model.getStartVertex().toString(), 5, 15);
      }
    Color color = g2d.getColor();
    g2d.setColor(color);
    g2d.drawRect(0, 0, width, height);
    g2d.translate(width/2, height/2);
    g2d.rotate(rotation);
    for(Vertex v : model.getVertices())
      {
      double x = v.getLongitude();
      double y = v.getLatitude();
      int pixel_x = (int)(0.5 + SCALE*x_scale*(x - x_mid_data));
      int pixel_y = (int)(0.5 - SCALE*y_scale*(y - y_mid_data));
      g2d.setColor(Color.black);
      g2d.setStroke(bs1);
      int radius = (v.equals(model.getStartVertex()))? 12 : 4;
      g2d.fillOval(pixel_x-radius/2, pixel_y-radius/2, radius, radius);
      g2d.drawOval(pixel_x-radius/2, pixel_y-radius/2, radius, radius);
      g2d.setStroke(bs1);
      g2d.setColor(Color.lightGray);
      for(Edge e : v.getEdges())
        {
        Vertex v2 = e.getToVertex();
        double x2 = v2.getLongitude();
        double y2 = v2.getLatitude();
        int pixel_x2 = (int)(0.5 + SCALE*x_scale*(x2 - x_mid_data));
        int pixel_y2 = (int)(0.5 - SCALE*y_scale*(y2 - y_mid_data));
        g2d.drawLine(pixel_x, pixel_y, pixel_x2, pixel_y2);
        }
      }
    g2d.setStroke(bs4);
    // if start and target selected - only draw that path
    if(model.getTargetVertex() != null && model.getTargetVertex() != null)
      {
      drawThisPath(g2d, model.getTargetVertex());
      }
    else // otherwise - draw from all to start
      {
      for(Vertex v : model.getVertices()) // spanning tree
        {
        drawThisPath(g2d, v);
        }
      }    
    }
  
  private void drawThisPath(Graphics2D g2d, Vertex target)
    {
    for(Vertex v2 = target; v2 != null && v2 != model.getStartVertex(); v2 = v2.getPredecessor())
      {
      double x = v2.getLongitude();
      double y = v2.getLatitude();
      int pixel_x = (int)(0.5 + SCALE*x_scale*(x - x_mid_data));
      int pixel_y = (int)(0.5 - SCALE*y_scale*(y - y_mid_data));
      Vertex pv = v2.getPredecessor();
      g2d.setColor(Color.blue);
      if(pv != null)
        {
        double x2 = pv.getLongitude();
        double y2 = pv.getLatitude();
        int pixel_x2 = (int)(0.5 + SCALE*x_scale*(x2 - x_mid_data));
        int pixel_y2 = (int)(0.5 - SCALE*y_scale*(y2 - y_mid_data));
        g2d.drawLine(pixel_x, pixel_y, pixel_x2, pixel_y2);
        }
      }      
    }
  
  private void printShortestPath(Graphics2D g2d, Vertex target, int x, int y, int yoffs)
    {
    while(target != null)
      {
      String out = String.format("%4.0f km %s", target.getDistance()/1000.0, target.toString());
      g2d.drawString(out, x, y);
      y += yoffs;
      target = target.getPredecessor();
      }
    }


  
  private Vertex findVertex(double mx, double my)
    {
    Vertex result = null;
    int width = getWidth();
    int height = getHeight();
    findLimits();
    double data_width = x_max_data - x_min_data;
    double data_height = y_max_data - y_min_data;
    double x_mid_data = x_min_data + data_width/2;
    double y_mid_data = y_min_data + data_height/2;
    double x_scale = width/data_width;
    double y_scale = height/data_height;
    //g2d.translate(width/2, height/2);
    mx -= width/2;
    my -= height/2;
    // rotera runt mittpunkten
    double mxt = mx*Math.cos(-rotation) - my*Math.sin(-rotation);
    my = mx*Math.sin(-rotation) + my*Math.cos(-rotation);
    mx = mxt;

    for(Vertex v : model.getVertices())
      {
      double vx = v.getLongitude();
      double vy = v.getLatitude();
      
      double tx = x_mid_data + mx/(SCALE*x_scale);
      double ty = y_mid_data - my/(SCALE*y_scale);
      double r = 5;
      double tol = (r/SCALE) * Math.max(1.0/x_scale, 1.0/y_scale);
      
      if((vx-tx)*(vx-tx)+(vy-ty)*(vy-ty) < tol*tol )
        return v;
      }
    return result;
    }
  }