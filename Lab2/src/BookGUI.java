import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.swing.Timer;

public class BookGUI {
    JTextArea bookTextArea = new JTextArea();
    String[] columnNames = { "ISBN", "Title", "Avaliable" };
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    JTable bookJTable = new JTable(model);

    JLabel ISBNLabel = new JLabel("ISBN: ");
    JTextField ISBNTextField = new JTextField(10);
    JLabel titleLabel = new JLabel("Title: ");
    JTextField titleTextField = new JTextField(10);

    JButton testDataButton = new JButton("Load Test Data");
    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");
    JButton editButton = new JButton("Edit");
    JButton editSaveButton = new JButton("Save");
    JButton searchButton = new JButton("Search");
    JButton moreButton = new JButton("More>>");

    JButton borrowButton = new JButton("Borrow");
    JButton returnButton = new JButton("Return");
    JButton reserveButton = new JButton("Reserve");
    JButton queueButton = new JButton("Waiting Queue");

    JButton LoadTestButton = new JButton("Load Test Data");
    JButton displayButton = new JButton("Display All");
    JButton sortISBNButton = new JButton("Display All by ISBN");
    JButton sortTitleButton = new JButton("Display All by Title");
    JButton exitButton = new JButton("Exit");

    private MyLinkedList<Book> bookList = new MyLinkedList<Book>();
    private int editIndex;

    JFrame bigframe = new JFrame("Library Admin System");

    JPanel topPanel = new JPanel(new GridLayout(1, 1));
    JLabel namLabel = new JLabel("Student Name and ID: Lee Kin Nang 20062785D");
    JLabel timeLabel = new JLabel("");

    JPanel midPanel = new JPanel(new GridLayout());
    JScrollPane scrollPane = new JScrollPane(bookJTable);
    JPanel bottomJPanel = new JPanel(new GridLayout(3, 1));
    JPanel bottomRow1 = new JPanel(new FlowLayout());
    JPanel bottomRow2 = new JPanel(new FlowLayout());
    JPanel bottomRow3 = new JPanel(new FlowLayout());

    JFrame moreFrame = new JFrame();
    JPanel moretopPanel = new JPanel(new GridLayout(1, 1));
    JTextArea bookdetailArea = new JTextArea();
    JLabel moreISBNLabel = new JLabel();
    JLabel moreTitleLabel = new JLabel();
    JLabel moreAvailableLabel = new JLabel();

    JPanel moremidPanel = new JPanel(new FlowLayout());

    JPanel morebottomJPanel = new JPanel(new GridLayout(1, 1));
    JTextArea resultsArea = new JTextArea();

    public BookGUI() {
        prepareGUI();
        addListeners();
    }

    public void time() {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",
                        java.util.Locale.ENGLISH);
                Date date = new Date();
                bookTextArea.setText("Student Name and ID: Lee Kin Nang 20062785D\n" + formatter.format(date) + "\n");
            }
        });
        timer.start();
    }

    public boolean isDuplicateISBN(String isbn) {
        for (Book book : bookList) {
            if (book.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicateTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void updateTable() {
        for (Book book : bookList) {
            String[] data = { book.getISBN(), book.getTitle(), book.getAvailable() ? "true" : "false" };
            model.addRow(data);
        }
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public void clearTextFields() {
        ISBNTextField.setText("");
        titleTextField.setText("");
    }

    public void loadTestData() {
        Book book1 = new Book();
        book1.setTitle("HTML How to Program");
        book1.setISBN("0131450913");
        book1.setAvailable(true);
        Book book2 = new Book();
        book2.setTitle("C++ How to Program");
        book2.setISBN("0131857576");
        book2.setAvailable(true);
        Book book3 = new Book();
        book3.setISBN("0132222205");
        book3.setTitle("Java How to Program");
        book3.setAvailable(true);

        // Check if the books already exist in the book list
        if (isDuplicateISBN(book1.getISBN()) || isDuplicateISBN(book2.getISBN()) || isDuplicateISBN(book3.getISBN())) {
            JOptionPane.showMessageDialog(null, "Test data already loaded.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        bookList.addLast(book1);
        bookList.addLast(book2);
        bookList.addLast(book3);
        clearTable();
        updateTable();
        clearTextFields();
    }

    public void displayBooks(List<Book> books) {
        clearTable();
        for (Book book : books) {
            String[] data = { book.getISBN(), book.getTitle(), book.getAvailable() ? "true" : "false" };
            model.addRow(data);
        }

    }

    public void prepareGUI() {
        time();
        bigframe.setSize(800, 600);
        bigframe.setLayout(new BorderLayout(2, 1));
        bigframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bookTextArea.append("Student Name and ID: Lee Kin Nang 20062785D\n");
        bookTextArea.setEditable(false);
        topPanel.add(bookTextArea);

        bookJTable.setRowHeight(20);
        bookJTable.setGridColor(Color.BLACK);
        bookJTable.setSelectionBackground(Color.LIGHT_GRAY);
        bookJTable.setSelectionForeground(Color.DARK_GRAY);
        bookJTable.setShowGrid(true);
        bookJTable.setShowVerticalLines(true);
        bookJTable.setShowHorizontalLines(true);
        bookJTable.setRowSelectionAllowed(true);
        bookJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookJTable.setPreferredScrollableViewportSize(new Dimension(500, 300));

        bottomRow1.add(ISBNLabel);
        bottomRow1.add(ISBNTextField);
        bottomRow1.add(titleLabel);
        bottomRow1.add(titleTextField);

        bottomRow2.add(addButton);
        bottomRow2.add(deleteButton);
        bottomRow2.add(editButton);
        editSaveButton.setEnabled(false);
        bottomRow2.add(editSaveButton);
        bottomRow2.add(searchButton);
        bottomRow2.add(moreButton);
        // bottomRow2.add(borrowButton);
        // bottomRow2.add(returnButton);
        // bottomRow2.add(reserveButton);

        bottomRow3.add(LoadTestButton);
        bottomRow3.add(displayButton);
        bottomRow3.add(sortISBNButton);
        bottomRow3.add(sortTitleButton);
        bottomRow3.add(exitButton);

        bottomJPanel.add(bottomRow1);
        bottomJPanel.add(bottomRow2);
        bottomJPanel.add(bottomRow3);

        bigframe.add(topPanel, BorderLayout.NORTH);
        bigframe.add(scrollPane, BorderLayout.CENTER);
        bigframe.add(bottomJPanel, BorderLayout.SOUTH);

        bigframe.setVisible(true);
    }

    public void addListeners() {

        bookJTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = bookJTable.getSelectedRow();
                if (selectedRow != -1) {
                    Book book = bookList.get(selectedRow);
                    String title = book.getTitle();
                    editIndex = selectedRow;

                    if (!title.isEmpty()) {
                        ISBNTextField.setText(book.getISBN());
                        titleTextField.setText(title);
                    }
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookJTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a book to edit.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                addButton.setEnabled(false);
                editButton.setEnabled(false);
                editSaveButton.setEnabled(true);
                deleteButton.setEnabled(false);
                searchButton.setEnabled(false);
                moreButton.setEnabled(false);
                LoadTestButton.setEnabled(false);
                displayButton.setEnabled(false);
                sortISBNButton.setEnabled(false);
                sortTitleButton.setEnabled(false);
                exitButton.setEnabled(false);

                editIndex = selectedRow;
                Book book = bookList.get(editIndex);
                ISBNTextField.setText(book.getISBN());
                titleTextField.setText(book.getTitle());
            }
        });

        editSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String isbn = ISBNTextField.getText().trim();
                String title = titleTextField.getText().trim();

                if (isbn.isEmpty() || title.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter ISBN and title.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (isDuplicateISBN(isbn) && !isbn.equals(bookList.get(editIndex).getISBN())) {
                    JOptionPane.showMessageDialog(null, "Book with the same ISBN already exists.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Book book = bookList.get(editIndex);
                book.setISBN(isbn);
                book.setTitle(title);
                clearTable();
                updateTable();
                clearTextFields();

                addButton.setEnabled(true);
                editButton.setEnabled(true);
                editSaveButton.setEnabled(false);
                deleteButton.setEnabled(true);
                searchButton.setEnabled(true);
                moreButton.setEnabled(true);
                LoadTestButton.setEnabled(true);
                displayButton.setEnabled(true);
                sortISBNButton.setEnabled(true);
                sortTitleButton.setEnabled(true);
                exitButton.setEnabled(true);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookJTable.getSelectedRow();
                Book book;
                String title;
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a book to delete.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    book = bookList.get(selectedRow);
                    title = book.getTitle();
                    if (!title.isEmpty()) {
                        ISBNTextField.setText(book.getISBN());
                        titleTextField.setText("");
                    }
                }

                String isbn = ISBNTextField.getText().trim();

                if (!isbn.equals(book.getISBN())) {
                    JOptionPane.showMessageDialog(null, "Invalid ISBN. Please select the correct book.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    bookList.remove(selectedRow);
                    clearTable();
                    updateTable();
                    clearTextFields();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = ISBNTextField.getText();
                String title = titleTextField.getText();

                if (isbn.isEmpty() && title.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter ISBN and Title.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Title.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (isbn.length() != 10) {
                    JOptionPane.showMessageDialog(null, "Invalid ISBN length. ISBN should be 10 characters long.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (isDuplicateISBN(isbn)) {
                    JOptionPane.showMessageDialog(null, "ISBN already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Add the book to the bookList
                    Book book = new Book();
                    book.setISBN(isbn);
                    book.setTitle(title);
                    book.setAvailable(true);
                    bookList.add(book);
                    clearTable();
                    updateTable();
                    clearTextFields();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = ISBNTextField.getText().trim();
                String title = titleTextField.getText().trim();

                List<Book> searchResults = new ArrayList<>();

                for (Book book : bookList) {
                    boolean matchesISBN = isbn.isEmpty() || book.getISBN().contains(isbn);
                    boolean matchesTitle = title.isEmpty() || book.getTitle().contains(title);

                    if (matchesISBN && matchesTitle) {
                        searchResults.add(book);
                    }
                }

                if (searchResults.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No matching books found.", "Search Results",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    displayBooks(searchResults);
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearTable();
                for (Book book : bookList) {
                    String[] data = { book.getISBN(), book.getTitle(), book.getAvailable() ? "true" : "false" };
                    model.addRow(data);
                }
            }
        });

        sortISBNButton.addActionListener(new ActionListener() {
            private boolean ascendingOrder = true;

            public void actionPerformed(ActionEvent e) {
                List<Book> bookListCopy = new ArrayList<>(bookList);
                Comparator<Book> comparator = Comparator.comparing(Book::getISBN);
                if (ascendingOrder) {
                    Collections.sort(bookListCopy, comparator);
                } else {
                    Collections.sort(bookListCopy, comparator.reversed());
                }
                displayBooks(bookListCopy);
                ascendingOrder = !ascendingOrder;
            }
        });

        sortTitleButton.addActionListener(new ActionListener() {
            private boolean ascendingOrder = true;

            public void actionPerformed(ActionEvent e) {
                List<Book> bookListCopy = new ArrayList<>(bookList);
                if (ascendingOrder) {
                    Collections.sort(bookListCopy, Comparator.comparing(Book::getTitle));
                } else {
                    Collections.sort(bookListCopy, Comparator.comparing(Book::getTitle).reversed());
                }

                displayBooks(bookListCopy);
                ascendingOrder = !ascendingOrder;
            }
        });

        LoadTestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTestData();
            }
        });

        moreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookJTable.getSelectedRow();
                editIndex = selectedRow;
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a book to view more details.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String isbn = ISBNTextField.getText().trim();
                    String title = titleTextField.getText().trim();
                    boolean avaiable = bookList.get(selectedRow).getAvailable();
                    moreFrame.setTitle(title);
                    moreFrame.setSize(600, 400);

                    bookdetailArea.setText("ISBN: " + isbn + "\n" + "Title: " + title + "\n" + "Available: "
                            + avaiable + "\n");
                    bookdetailArea.setEditable(false);
                    moretopPanel.add(bookdetailArea);

                    moreFrame.add(moretopPanel, BorderLayout.NORTH);

                    moremidPanel.add(borrowButton);
                    moremidPanel.add(returnButton);
                    moremidPanel.add(reserveButton);
                    moremidPanel.add(queueButton);
                    moreFrame.add(moremidPanel, BorderLayout.CENTER);

                    morebottomJPanel.add(resultsArea);
                    moreFrame.add(morebottomJPanel, BorderLayout.SOUTH);
                    moreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    moreFrame.setVisible(true);
                    if (avaiable) {
                        borrowButton.setEnabled(true);
                        returnButton.setEnabled(false);
                        reserveButton.setEnabled(true);
                        queueButton.setEnabled(false);
                        resultsArea.setText("");
                    } else {
                        borrowButton.setEnabled(false);
                        returnButton.setEnabled(true);
                        reserveButton.setEnabled(true);
                        queueButton.setEnabled(true);
                        resultsArea.setText("The book is borrowed.");
                    }
                }
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book book = bookList.get(editIndex);
                String isbn = book.getISBN();
                String title = book.getTitle();
                boolean avaiable = book.getAvailable();

                if (avaiable) {
                    book.setAvailable(false);
                    clearTable();
                    updateTable();
                    clearTextFields();
                    avaiable = book.getAvailable();
                    borrowButton.setEnabled(false);
                    returnButton.setEnabled(true);
                    reserveButton.setEnabled(true);
                    queueButton.setEnabled(true);
                    resultsArea.setText("The book is borrowed.");
                    bookdetailArea.setText("ISBN: " + isbn + "\n" + "Title: " + title + "\n" + "Available: "
                            + avaiable + "\n");
                    bookdetailArea.repaint();
                }
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book book = bookList.get(editIndex);
                // String isbn = book.getISBN();
                // String title = book.getTitle();
                boolean available = book.isAvailable();
                if (!available) {
                    String borrowerName = JOptionPane.showInputDialog(null, "What's your name?");
                    if (borrowerName != null && !borrowerName.trim().isEmpty()) {
                        resultsArea.setText("The book is reserved by " + borrowerName + ".");
                        book.getReservedQueue().enqueue(borrowerName);
                    }
                }
            }
        });

        queueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book book = bookList.get(editIndex);
                MyQueue<String> waitingQueue = book.getReservedQueue();
                MyLinkedList<String> waitingList = waitingQueue.getList();
                if (waitingQueue.getSize() == 0) {
                    // resultsArea.setText("No one is waiting for this book.");
                } else {
                    StringBuilder message = new StringBuilder("The waiting queue:\n");
                    for (String name : waitingList) {
                        message.append(name + "\n");
                    }
                    resultsArea.setText(message.toString());
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book book = bookList.get(editIndex);
                String isbn = book.getISBN();
                String title = book.getTitle();
                boolean avaiable = book.getAvailable();
                MyQueue<String> waitingQueue = book.getReservedQueue();
                MyLinkedList<String> waitingList = waitingQueue.getList();
                if (!avaiable) {

                    if (waitingList.isEmpty()) {
                        book.setAvailable(true);
                        borrowButton.setEnabled(true);
                        returnButton.setEnabled(false);
                        reserveButton.setEnabled(false);
                        queueButton.setEnabled(false);
                        resultsArea.setText("The book is returned.");
                        avaiable = book.getAvailable();
                        bookdetailArea.setText("ISBN: " + isbn + "\n" + "Title: " + title + "\n" + "Available: "
                                + avaiable + "\n");
                    } else {
                        resultsArea.setText("The book is returned.");
                        String borrowerName = waitingQueue.dequeue();
                        resultsArea.append("\n" + "The book is now borrowed by " + borrowerName);
                        bookdetailArea.setText("ISBN: " + isbn + "\n" + "Title: " + title + "\n" + "Available: "
                                + avaiable);
                    }
                    clearTable();
                    updateTable();
                    clearTextFields();

                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    bigframe.dispose();
                    JOptionPane.showMessageDialog(null, "Application terminated.");
                    System.exit(0);
                }
            }
        });

        bigframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    bigframe.dispose();
                    JOptionPane.showMessageDialog(null, "Application terminated.");
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        new BookGUI();
    }

}
