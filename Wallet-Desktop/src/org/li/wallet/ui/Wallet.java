package org.li.wallet.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class Wallet {

	protected Shell shell;

	// the elements of the filter group
	private Group filterGroup;
	private Label consumerFilterLabel;
	private Text consumerFilterText;
	private Label filterDateLabel;
	private DateTime filterFromDate;
	private Label toDateLabel;
	private DateTime filterToDate;
	private Button searchButton;
	private Button searchAllButton;

	// the elements of the statistic group
	private Table historyInfoTable;
	private Group computeGroup;

	// the elements of the info table
	private TableColumn seqColumn;
	private TableColumn dateColumn;
	private TableColumn consumerColumn;
	private TableColumn amountColumn;
	private TableColumn descriptionColumn;

	// the elements of the operation group
	private Group operationGroup;
	private Button editButton;
	private Button deleteButton;
	private Button newButton;

	// the elements of the edit group
	private Group editGroup;
	private Label consumerLabel;
	private Label dateLabel;
	private DateTime consumeDate;
	private Label amountLabel;
	private Button okButton;
	private Button cancelButton;
	private Text amountText;
	private Text reasonText;
	private Text consumerText;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Wallet window = new Wallet();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(835, 629);
		shell.setText("Wallet");

		initFilterGroup();
		initStatisticGroup();
		initInfoTable();
		initOperatonGroup();
		initEditGroup();
	}

	/**
	 * init the filter group and the inner elements
	 */
	private void initFilterGroup() {
		filterGroup = new Group(shell, SWT.NONE);
		filterGroup.setText("Filter");
		filterGroup.setBounds(10, 6, 452, 100);

		consumerFilterLabel = new Label(filterGroup, SWT.NONE);
		consumerFilterLabel.setBounds(10, 24, 64, 17);
		consumerFilterLabel.setText("consumer:");

		consumerFilterText = new Text(filterGroup, SWT.BORDER);
		consumerFilterText.setBounds(80, 21, 114, 25);

		filterDateLabel = new Label(filterGroup, SWT.NONE);
		filterDateLabel.setBounds(10, 65, 64, 17);
		filterDateLabel.setText("date:");

		filterFromDate = new DateTime(filterGroup, SWT.NONE);
		filterFromDate.setBounds(80, 61, 114, 25);

		toDateLabel = new Label(filterGroup, SWT.NONE);
		toDateLabel.setBounds(204, 65, 21, 17);
		toDateLabel.setText("to:");

		filterToDate = new DateTime(filterGroup, SWT.NONE);
		filterToDate.setBounds(230, 60, 114, 25);

		searchButton = new Button(filterGroup, SWT.NONE);
		searchButton.setText("search");
		searchButton.setBounds(230, 18, 86, 27);

		searchAllButton = new Button(filterGroup, SWT.NONE);
		searchAllButton.setBounds(322, 19, 87, 27);
		searchAllButton.setText("search all");
	}

	/**
	 * init the statistic group and the inner elements
	 */
	private void initStatisticGroup() {
		computeGroup = new Group(shell, SWT.NONE);
		computeGroup.setText("Statistic");
		computeGroup.setBounds(499, 6, 302, 100);
	}

	/**
	 * init the info table and the colums
	 */
	private void initInfoTable() {
		historyInfoTable = new Table(shell, SWT.BORDER);
		historyInfoTable.setLinesVisible(true);
		historyInfoTable.setHeaderVisible(true);
		historyInfoTable.setBounds(10, 112, 655, 325);

		seqColumn = new TableColumn(historyInfoTable, SWT.NONE);
		seqColumn.setWidth(100);
		seqColumn.setText("seq");

		dateColumn = new TableColumn(historyInfoTable, SWT.NONE);
		dateColumn.setWidth(117);
		dateColumn.setText("date");

		consumerColumn = new TableColumn(historyInfoTable, SWT.NONE);
		consumerColumn.setWidth(117);
		consumerColumn.setText("consumer");

		amountColumn = new TableColumn(historyInfoTable, SWT.NONE);
		amountColumn.setWidth(108);
		amountColumn.setText("amount");

		descriptionColumn = new TableColumn(historyInfoTable, SWT.NONE);
		descriptionColumn.setWidth(192);
		descriptionColumn.setText("description");
	}

	private void initOperatonGroup() {
		operationGroup = new Group(shell, SWT.NONE);
		operationGroup.setText("operation");
		operationGroup.setBounds(671, 108, 130, 221);

		editButton = new Button(operationGroup, SWT.NONE);
		editButton.setBounds(25, 34, 77, 35);
		editButton.setText("Edit");

		deleteButton = new Button(operationGroup, SWT.NONE);
		deleteButton.setBounds(25, 87, 77, 35);
		deleteButton.setText("Delete");

		newButton = new Button(operationGroup, SWT.NONE);
		newButton.setBounds(25, 147, 77, 35);
		newButton.setText("New");
	}

	/**
	 * init the edit group and the inner elements
	 */
	private void initEditGroup() {
		editGroup = new Group(shell, SWT.NONE);
		editGroup.setText("Edit");
		editGroup.setBounds(10, 464, 655, 117);

		consumerLabel = new Label(editGroup, SWT.NONE);
		consumerLabel.setBounds(10, 29, 64, 17);
		consumerLabel.setText("consumer:");

		consumerText = new Text(editGroup, SWT.BORDER);
		consumerText.setBounds(80, 26, 114, 25);

		dateLabel = new Label(editGroup, SWT.NONE);
		dateLabel.setBounds(257, 33, 64, 17);
		dateLabel.setText("date:");

		consumeDate = new DateTime(editGroup, SWT.NONE);
		consumeDate.setBounds(327, 29, 114, 25);

		final Label reasonLabel = new Label(editGroup, SWT.NONE);
		reasonLabel.setBounds(10, 85, 64, 17);
		reasonLabel.setText("reason:");

		reasonText = new Text(editGroup, SWT.BORDER);
		reasonText.setBounds(80, 82, 114, 25);

		amountLabel = new Label(editGroup, SWT.NONE);
		amountLabel.setBounds(257, 85, 64, 17);
		amountLabel.setText("amount:");

		amountText = new Text(editGroup, SWT.BORDER);
		amountText.setBounds(327, 82, 114, 25);

		okButton = new Button(editGroup, SWT.NONE);
		okButton.setText("OK");
		okButton.setBounds(488, 28, 80, 33);

		cancelButton = new Button(editGroup, SWT.NONE);
		cancelButton.setBounds(488, 69, 80, 33);
		cancelButton.setText("cancel");
	}
}
