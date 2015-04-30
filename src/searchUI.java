//package ScanToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.print.attribute.standard.MediaSize.Other;

import org.apache.lucene.queryparser.classic.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class searchUI {

	protected Shell shlSeachInformation;
	private Text projectName;
	private Text date;
	private Text slot;
	//private Text link;
	private Text agency;
	//private Text agencyLink;
	private Text description;
	private Text location;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table;
	private OrgSyncSearch search;
	private ArrayList<EventProject> results;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			searchUI window = new searchUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSeachInformation.open();
		shlSeachInformation.layout();
		while (!shlSeachInformation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSeachInformation = new Shell();
		shlSeachInformation.setSize(1024, 600);
		shlSeachInformation.setText("Seach Information");
		shlSeachInformation.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlSeachInformation, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite_1 = new Composite(composite, SWT.NONE);
		
		/*Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(69, 10, 114, 26);
		lblNewLabel.setText("Project Name");
		
		projectName = new Text(composite_1, SWT.BORDER);
		projectName.setBounds(189, 7, 200, 26);
		*/
		/*
		Label lblDate = new Label(composite_1, SWT.NONE);
		lblDate.setTouchEnabled(true);
		lblDate.setText("Date");
		lblDate.setBounds(69, 42, 114, 26);
		
		date = new Text(composite_1, SWT.BORDER);
		date.setBounds(189, 42, 200, 26);
		*/
		Label lblSlot = new Label(composite_1, SWT.NONE);
		lblSlot.setTouchEnabled(true);
		lblSlot.setText("Minimum number of Openings");
		lblSlot.setBounds(69, 10, 220, 26);
		
		slot = new Text(composite_1, SWT.BORDER);
		slot.setBounds(290, 7, 100, 26);
		
		/*
		Label lblAgency = new Label(composite_1, SWT.NONE);
		lblAgency.setTouchEnabled(true);
		lblAgency.setText("Agency");
		lblAgency.setBounds(69, 121, 114, 26);
		
		agency = new Text(composite_1, SWT.BORDER);
		agency.setBounds(189, 108, 200, 26);
		*/
		
		
		Label lblDescriptionDate = new Label(composite_1, SWT.NONE);
		lblDescriptionDate.setTouchEnabled(true);
		lblDescriptionDate.setText("Search Items");
		lblDescriptionDate.setBounds(69, 42, 114, 26);
		
		description = new Text(composite_1, SWT.BORDER);
		description.setBounds(189, 42, 400, 26);
		
		Label lblLocation = new Label(composite_1, SWT.NONE);
		lblLocation.setTouchEnabled(true);
		lblLocation.setText("Max Distance in Miles");
		lblLocation.setBounds(69, 72, 165, 26);
		
		location = new Text(composite_1, SWT.BORDER);
		location.setBounds(230, 78, 250, 26);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				table = formToolkit.createTable(composite_1, SWT.NONE);
				table.setToolTipText("");
				table.setBounds(10, 155, 980,300);
				formToolkit.paintBordersFor(table);
				table.setHeaderVisible(true);
				table.setLinesVisible(true);
				 String[] titles = { "Projectname","URL", "Date", "Slot", "Agency" ,
						 "Address"};
					    for (int i = 0; i < titles.length; i++) {
					    	TableColumn column = new TableColumn(table, SWT.NONE);
						      column.setText(titles[i]);
					    }
					    File file = new File("c:/org_projects_test.csv");
						Vector<EventProject> EventList= new Vector<EventProject>();
						try {
							EventList = Scantoken.getValueToken(file);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						int count = 0;
						System.out.println("Size is"+EventList.size());
						Vector outputTable = new Vector();
						ArrayList<EventProject> fullList= new ArrayList<EventProject>();
					    for (int i = 0; i < EventList.size(); i++) {
					    	EventProject eventproject=EventList.get(i);
					    	fullList.add(eventproject);
					    }
					    	try {
								 search=new OrgSyncSearch(fullList);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    	
					    	String[] query=new String[6];
							query[0]="";
							query[1]="";
							query[2]=slot.getText();
							query[3]="";
							query[4]=description.getText();
							query[5]=location.getText();
							
							try {
								results=search.search(query);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						 for (int i=0;i<results.size();i++){
							 outputTable.add(results.get(i));
							 
						 }
						System.out.println("Output"+outputTable.size());
					    for (int j = 0; j < outputTable.size(); j++) {
					      TableItem item = new TableItem(table, SWT.NONE);
					      EventProject outputEvent=(EventProject) outputTable.get(j);
					      //item.setText(0, outputEvent.getDiststring());
					      item.setText(0,outputEvent.getProjectName());
					      item.setText(2, outputEvent.getDate());
					      item.setText(3, outputEvent.getSlot());
					     
					      item.setText(4, outputEvent.getAgency());
					      item.setText(1, outputEvent.getLink());
					     
					
					      item.setText(4, outputEvent.getAddress());
					    }
					    for (int i = 0; i < titles.length; i++) {
						      table.getColumn(i).pack();
						}
			}
		});
		btnNewButton.setSelection(true);
		btnNewButton.setTouchEnabled(true);
		btnNewButton.setBounds(189, 109, 300, 40);
		btnNewButton.setText("Search for items or list all open projects");
	}
}
