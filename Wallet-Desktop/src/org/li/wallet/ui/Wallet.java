package org.li.wallet.ui;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.li.wallet.resources.ExpenseDto;
import org.li.wallet.resources.Expenses;
import org.li.wallet.resources.JsonProcessor;
import org.li.wallet.resources.MyDate;

import com.google.common.collect.Lists;

public class Wallet {
	private Text statisticText;
	private JsonProcessor processor = new JsonProcessor();
	private EditMode mode = EditMode.VIEW;

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
		shell = new Shell(SWT.MIN | SWT.RESIZE | SWT.CLOSE);
		shell.setSize(835, 629);
		shell.setText("Wallet");

		initFilterGroup();
		initStatisticGroup();
		initInfoTable();
		initOperatonGroup();
		initEditGroup();
		addAdapterForElements();
		editGroup.setEnabled(false);
		statistic();
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
		searchButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				search();
			}
		});
		searchButton.setText("search");
		searchButton.setBounds(230, 18, 86, 27);

		searchAllButton = new Button(filterGroup, SWT.NONE);
		searchAllButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				searchAll();
			}
		});
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

		statisticText = new Text(computeGroup, SWT.V_SCROLL | SWT.READ_ONLY
				| SWT.MULTI | SWT.BORDER);
		statisticText.setBounds(10, 22, 262, 68);
	}

	/**
	 * init the info table and the colums
	 */
	private void initInfoTable() {
		historyInfoTable = new Table(shell, SWT.FULL_SELECTION | SWT.MULTI
				| SWT.BORDER);
		historyInfoTable.setLinesVisible(true);
		historyInfoTable.setHeaderVisible(true);
		historyInfoTable.setBounds(10, 112, 655, 325);

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

		searchAll();
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

	private TableItem addNewItem(ExpenseDto dto) {
		TableItem newItem = new TableItem(historyInfoTable, SWT.CENTER);
		newItem.setText(new String[] { dto.getDate().toString(),
				dto.getAuthor(), String.valueOf(dto.getAmount()),
				dto.getReason() });
		return newItem;
	}

	private void editNewItem(ExpenseDto dto, int index) {
		TableItem newItem = historyInfoTable.getItem(index);
		newItem.setText(new String[] { dto.getDate().toString(),
				dto.getAuthor(), String.valueOf(dto.getAmount()),
				dto.getReason() });
	}

	/**
	 * initialize the edit group
	 */
	private void emptyEditGroup() {
		consumerText.setText("");
		Calendar today = Calendar.getInstance();
		consumeDate.setDate(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
		reasonText.setText("");
		amountText.setText("");
	}

	/**
	 * This method get <code>ExpenseDto</code> from the data of the edit group
	 * 
	 * @return
	 */
	private ExpenseDto generateExpenseDto() {
		String consumer = consumerText.getText();
		String reason = reasonText.getText();
		String amount = amountText.getText();
		double money;
		if (consumer.equals("") || reason.equals("") || amount.equals("")) {
			MessageBox box = new MessageBox(shell, SWT.PUSH);
			box.setText("warning");
			box.setMessage("can not be empty");
			box.open();
			consumerText.setFocus();
			return null;
		}
		try {
			money = Double.valueOf(amount);
		} catch (NumberFormatException e) {
			MessageBox box = new MessageBox(shell, SWT.PUSH);
			box.setText("warning");
			box.setMessage("can not convert to Double");
			box.open();
			amountText.setFocus();
			return null;
		}
		return new ExpenseDto(consumer, money, reason, new MyDate(consumeDate
				.getYear(), consumeDate.getMonth(), consumeDate.getDay()));
	}

	private void addAdapterForElements() {
		// begin add adapter for operation group
		editButton.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				if (13 == e.keyCode) {
					beginEditExistedExpense();
				}
			}
		});

		editButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				beginEditExistedExpense();
			}
		});

		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {

				System.out.println(historyInfoTable.getSelectionCount());
				System.out.println(historyInfoTable.getSelectionIndex());
				int index = historyInfoTable.getSelectionIndex();
				int count = historyInfoTable.getSelectionCount();
				if (-1 == index) {
					return;
				}
				for (int i = 0; i < count; i++) {
					historyInfoTable.remove(index);
					processor.deleteExpense(index);
					index--;
				}
				statistic();
				mode = EditMode.VIEW;
			}
		});

		newButton.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				if (e.keyCode == 13) {
					beginEditNewExpense();
				}
			}
		});

		newButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				beginEditNewExpense();
			}
		});
		// end add adapter for operation group

		// begin add adapter for edit group
		amountText.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				if (13 == e.keyCode) {
					addNewExpense();
				}
			}
		});

		okButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				addNewExpense();
			}
		});

		okButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (13 == e.keyCode) {
					addNewExpense();
				}
			}
		});

		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				emptyEditGroup();
				editGroup.setEnabled(false);
				mode = EditMode.VIEW;
			}
		});
		// end of add adapter for edit group
	}

	private void beginEditExistedExpense() {
		TableItem[] selected = historyInfoTable.getSelection();
		if (selected.length == 0) {
			return;
		}

		TableItem first = selected[0];
		String date = first.getText(0);
		consumerText.setText(first.getText(1));
		reasonText.setText(first.getText(3));
		amountText.setText(first.getText(2));
		String[] splitDate = date.split("/");
		consumeDate.setYear(Integer.valueOf(splitDate[0]));
		consumeDate.setMonth(Integer.valueOf(splitDate[1]));
		consumeDate.setDay(Integer.valueOf(splitDate[2]));

		editGroup.setEnabled(true);
		consumerText.setFocus();
		mode = EditMode.EDIT;

	}

	/**
	 * begin to edit new expense
	 */
	private void beginEditNewExpense() {
		editGroup.setEnabled(true);
		emptyEditGroup();
		consumerText.setText(System.getProperty("user.name"));
		consumerText.setFocus();
		mode = EditMode.NEW;
	}

	/**
	 * the event of add new expense or edit the existed expense
	 */
	private void addNewExpense() {
		ExpenseDto dto = generateExpenseDto();
		if (null == dto) {
			return;
		}

		int index = historyInfoTable.getSelectionIndex();
		if (EditMode.NEW == mode) {
			historyInfoTable.setSelection(addNewItem(dto));
			processor.addExpense(dto);
		} else if (EditMode.EDIT == mode) {
			editNewItem(dto, index);
			processor.editExpense(dto, index);
		}

		editGroup.setEnabled(false);
		emptyEditGroup();
		newButton.setFocus();
		statistic();
		mode = EditMode.VIEW;
	}

	private void statistic() {
		Map<String, Double> map = new HashMap<String, Double>();
		Expenses expenses = processor.read();
		String consumer = null;
		Double sum = 0.0;
		for (ExpenseDto dto : expenses.getExpenses()) {
			consumer = dto.getAuthor();
			sum = map.get(consumer);
			if (sum != null) {
				map.put(consumer, sum + dto.getAmount());
			} else {
				map.put(consumer, dto.getAmount());
			}
		}

		StringBuilder builder = new StringBuilder();
		for (Entry<String, Double> entry : map.entrySet()) {
			builder.append(entry.getKey()).append(" : ").append(
					entry.getValue()).append("\n");
		}
		statisticText.setText(builder.toString());
	}

	private void searchAll() {
		consumerFilterText.setText("");
		historyInfoTable.removeAll();
		Expenses expenses = processor.read();
		for (ExpenseDto expense : expenses.getExpenses()) {
			addNewItem(expense);
		}
	}

	private void search() {
		Expenses expenses = processor.read();

		String consumer = consumerFilterText.getText();
		MyDate fromDate = new MyDate(filterFromDate.getYear(), filterFromDate
				.getMonth(), filterFromDate.getDay());
		MyDate toDate = new MyDate(filterToDate.getYear(), filterToDate
				.getMonth(), filterToDate.getDay());
		List<ExpenseDto> dtos = expenses.getExpenses();
		List<ExpenseDto> filtCounsumer = Lists.newArrayList();
		
		if ("".equals(consumer) || consumer == null) {
			filtCounsumer = dtos;
		} else {
			for (ExpenseDto dto : dtos) {
				if (dto.getAuthor().equals(consumer)) {
					System.out.println("equal");
					filtCounsumer.add(dto);
				}
			}
		}

		List<ExpenseDto> filtDate = Lists.newArrayList();
		for (ExpenseDto dto : filtCounsumer) {
			if (dto.getDate().compareTo(fromDate) >= 0
					&& dto.getDate().compareTo(toDate) <= 0) {
				filtDate.add(dto);
			}
		}

		historyInfoTable.removeAll();
		for (ExpenseDto expense : filtDate) {
			addNewItem(expense);
		}
	}

}
