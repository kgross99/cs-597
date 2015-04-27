package ScanToken;

import java.io.File;
import java.util.Vector;

import javax.print.attribute.standard.MediaSize.Other;

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
	private Text descriptDate;
	private Text location;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table;

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
		shlSeachInformation.setSize(531, 579);
		shlSeachInformation.setText("Seach Information");
		shlSeachInformation.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlSeachInformation, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite_1 = new Composite(composite, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(69, 10, 114, 26);
		lblNewLabel.setText("Project Name");
		
		projectName = new Text(composite_1, SWT.BORDER);
		projectName.setBounds(189, 7, 200, 26);
		
		Label lblDate = new Label(composite_1, SWT.NONE);
		lblDate.setTouchEnabled(true);
		lblDate.setText("Date");
		lblDate.setBounds(69, 42, 114, 26);
		
		date = new Text(composite_1, SWT.BORDER);
		date.setBounds(189, 42, 200, 26);
		
		Label lblSlot = new Label(composite_1, SWT.NONE);
		lblSlot.setTouchEnabled(true);
		lblSlot.setText("Slot");
		lblSlot.setBounds(69, 77, 114, 26);
		
		slot = new Text(composite_1, SWT.BORDER);
		slot.setBounds(189, 74, 200, 26);
		
		
		Label lblAgency = new Label(composite_1, SWT.NONE);
		lblAgency.setTouchEnabled(true);
		lblAgency.setText("Agency");
		lblAgency.setBounds(69, 141, 114, 26);
		
		agency = new Text(composite_1, SWT.BORDER);
		agency.setBounds(189, 138, 200, 26);
		
		
		
		Label lblDescriptionDate = new Label(composite_1, SWT.NONE);
		lblDescriptionDate.setTouchEnabled(true);
		lblDescriptionDate.setText("Description Date");
		lblDescriptionDate.setBounds(69, 205, 114, 26);
		
		descriptDate = new Text(composite_1, SWT.BORDER);
		descriptDate.setBounds(189, 202, 200, 26);
		
		Label lblLocation = new Label(composite_1, SWT.NONE);
		lblLocation.setTouchEnabled(true);
		lblLocation.setText("Location");
		lblLocation.setBounds(69, 237, 114, 26);
		
		location = new Text(composite_1, SWT.BORDER);
		location.setBounds(189, 234, 200, 26);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				table = formToolkit.createTable(composite_1, SWT.NONE);
				table.setToolTipText("");
				table.setBounds(10, 345, 495, 167);
				formToolkit.paintBordersFor(table);
				table.setHeaderVisible(true);
				table.setLinesVisible(true);
				 String[] titles = { "Projectname", "Date", "Slot", "Agency" ,"Description date",
						 "Distance"};
					    for (int i = 0; i < titles.length; i++) {
					    	TableColumn column = new TableColumn(table, SWT.NONE);
						      column.setText(titles[i]);
					    }
					    File file = new File("D:\\Test.xlsx");
						Vector<EventProject> EventList=Scantoken.getValueToken(file);
						int count = 0;
						System.out.println("Size is"+EventList.size());
						Vector outputTable = new Vector();
					    for (int i = 0; i < EventList.size(); i++) {
					    	EventProject eventproject=EventList.get(i);
							if(projectName.getText().equals(eventproject.getProjectName())==true){
								outputTable.add(eventproject);
								count++;
							}
							if(date.getText().equals(eventproject.getDate())==true){
								outputTable.add(eventproject);
								count++;
							}
							if(slot.getText().equals(eventproject.getSlot())==true){
								outputTable.add(eventproject);
								count++;
							}
							
							if(agency.getText().equals(eventproject.getAgency())==true){
								outputTable.add(eventproject);
								count++;
							}
							
							if(descriptDate.getText().equals(eventproject.getDescriptionDate())==true){
								outputTable.add(eventproject);
								count++;
							}
							if(location.getText().equals(eventproject.getDistance())==true){
								outputTable.add(eventproject);
								count++;
							}
							
						} 
						System.out.println("Output"+outputTable.size());
					    for (int j = 0; j < count; j++) {
					      TableItem item = new TableItem(table, SWT.NONE);
					      EventProject outputEvent=(EventProject) outputTable.get(j);
					      item.setText(0, outputEvent.getProjectName());
					      item.setText(1, outputEvent.getDate());
					      item.setText(2, outputEvent.getSlot());
					     
					      item.setText(4, outputEvent.getAgency());
					     
					      item.setText(6, outputEvent.getDescriptionDate());
					      item.setText(6, outputEvent.getDistance());
					    }
					    for (int i = 0; i < titles.length; i++) {
						      table.getColumn(i).pack();
						}
			}
		});
		btnNewButton.setSelection(true);
		btnNewButton.setTouchEnabled(true);
		btnNewButton.setBounds(189, 285, 143, 40);
		btnNewButton.setText("Search button");
	}
}
