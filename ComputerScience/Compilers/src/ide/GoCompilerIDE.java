package ComputerScience.Compilers.src.ide;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.undo.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import ComputerScience.Compilers.src.parser.grammars.*;
import ComputerScience.Compilers.src.semantic.*;
import ComputerScience.Compilers.src.codegen.*;
import ComputerScience.Compilers.src.optimizer.*;
import ComputerScience.Compilers.src.error.*;

/**
 * GO Language Compiler IDE
 */
public class GoCompilerIDE extends JFrame {
    // UI Components
    private JTextArea codeEditor;
    private JTextArea outputArea;
    private JTextArea tacArea;
    private JTextArea optTacArea;
    private JTextArea asmArea;
    private JTextArea symbolTableArea;
    private JTabbedPane resultTabs;
    private JComboBox<String> exampleSelector;
    private JButton compileButton;
    private JButton clearButton;
    private JButton newButton;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JCheckBox optimizeCheckBox;
    private JCheckBox verboseCheckBox;
    private JLabel statusLabel;
    
    // Undo manager
    private UndoManager undoManager;
    
    // Current file
    private File currentFile = null;
    private boolean isModified = false;
    
    public GoCompilerIDE() {
        super("GO Compiler IDE v1.0");
        initComponents();
        setupMenuBar();
        setupLayout();
        setupKeyBindings();
        loadExampleCode("hello");
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create undo manager
        undoManager = new UndoManager();
        
        // Code editor
        codeEditor = new JTextArea(20, 50);
        Font monoFont = new Font("Monaspace Neon NF", Font.BOLD, 20);
        codeEditor.setFont(monoFont);
        codeEditor.setTabSize(4);
        codeEditor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        codeEditor.getDocument().addUndoableEditListener(e -> {
            undoManager.addEdit(e.getEdit());
            setModified(true);
        });
        
        // Output areas
        Font outputFont = new Font("Monaspace Neon NF", Font.BOLD, 20);
        
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setFont(outputFont);
        outputArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        tacArea = new JTextArea(10, 50);
        tacArea.setEditable(false);
        tacArea.setFont(outputFont);
        tacArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        optTacArea = new JTextArea(10, 50);
        optTacArea.setEditable(false);
        optTacArea.setFont(outputFont);
        optTacArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        asmArea = new JTextArea(10, 50);
        asmArea.setEditable(false);
        asmArea.setFont(outputFont);
        asmArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        symbolTableArea = new JTextArea(10, 50);
        symbolTableArea.setEditable(false);
        symbolTableArea.setFont(outputFont);
        symbolTableArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Example selector
        String[] examples = {
            "hello - Hello World",
            "arithmetic - Arithmetic",
            "factorial - Factorial",
            "fibonacci - Fibonacci",
            "loop - Loop",
            "array - Array",
            "complex - Complex",
            "nested_loops - Nested Loops"
        };
        exampleSelector = new JComboBox<>(examples);
        exampleSelector.addActionListener(e -> {
            String selected = (String) exampleSelector.getSelectedItem();
            if (selected != null) {
                String exampleName = selected.split(" - ")[0];
                loadExampleCode(exampleName);
            }
        });
        
        // Buttons
        newButton = new JButton("New");
        newButton.setToolTipText("New File (Ctrl+N)");
        newButton.addActionListener(e -> newFile());
        
        openButton = new JButton("Open");
        openButton.setToolTipText("Open File (Ctrl+O)");
        openButton.addActionListener(e -> openFile());
        
        saveButton = new JButton("Save");
        saveButton.setToolTipText("Save File (Ctrl+S)");
        saveButton.addActionListener(e -> saveFile());
        
        saveAsButton = new JButton("Save As");
        saveAsButton.setToolTipText("Save As... (Ctrl+Shift+S)");
        saveAsButton.addActionListener(e -> saveFileAs());
        
        compileButton = new JButton(">> Compile");
        compileButton.setToolTipText("Compile and Run (F5)");
        compileButton.setBackground(new Color(76, 175, 80));
        compileButton.setForeground(Color.BLUE);
        compileButton.setFocusPainted(false);
        compileButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        compileButton.addActionListener(e -> compile());
        
        clearButton = new JButton("Clear");
        clearButton.setToolTipText("Clear Editor");
        clearButton.addActionListener(e -> clearEditor());
        
        // Options
        optimizeCheckBox = new JCheckBox("Enable Optimization", true);
        optimizeCheckBox.setToolTipText("Enable TAC optimization");
        
        verboseCheckBox = new JCheckBox("Verbose Output", true);
        verboseCheckBox.setToolTipText("Show detailed information");
        
        // Result tabs
        resultTabs = new JTabbedPane();
        resultTabs.addTab("Output", new JScrollPane(outputArea));
        resultTabs.addTab("TAC", new JScrollPane(tacArea));
        resultTabs.addTab("Optimized", new JScrollPane(optTacArea));
        resultTabs.addTab("Assembly", new JScrollPane(asmArea));
        resultTabs.addTab("Symbols", new JScrollPane(symbolTableArea));
        
        // Status bar
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }
    
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        
        JMenuItem newItem = new JMenuItem("New", 'N');
        newItem.setAccelerator(KeyStroke.getKeyStroke("control N"));
        newItem.addActionListener(e -> newFile());
        fileMenu.add(newItem);
        
        JMenuItem openItem = new JMenuItem("Open...", 'O');
        openItem.setAccelerator(KeyStroke.getKeyStroke("control O"));
        openItem.addActionListener(e -> openFile());
        fileMenu.add(openItem);
        
        fileMenu.addSeparator();
        
        JMenuItem saveItem = new JMenuItem("Save", 'S');
        saveItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
        saveItem.addActionListener(e -> saveFile());
        fileMenu.add(saveItem);
        
        JMenuItem saveAsItem = new JMenuItem("Save As...", 'A');
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        saveAsItem.addActionListener(e -> saveFileAs());
        fileMenu.add(saveAsItem);
        
        fileMenu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Exit", 'X');
        exitItem.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
        exitItem.addActionListener(e -> exitApplication());
        fileMenu.add(exitItem);
        
        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        
        JMenuItem undoItem = new JMenuItem("Undo", 'U');
        undoItem.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        undoItem.addActionListener(e -> {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        });
        editMenu.add(undoItem);
        
        JMenuItem redoItem = new JMenuItem("Redo", 'R');
        redoItem.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        redoItem.addActionListener(e -> {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        });
        editMenu.add(redoItem);
        
        editMenu.addSeparator();
        
        JMenuItem findItem = new JMenuItem("Find...", 'F');
        findItem.setAccelerator(KeyStroke.getKeyStroke("control F"));
        findItem.addActionListener(e -> showFindDialog());
        editMenu.add(findItem);
        
        JMenuItem replaceItem = new JMenuItem("Replace...", 'H');
        replaceItem.setAccelerator(KeyStroke.getKeyStroke("control H"));
        replaceItem.addActionListener(e -> showReplaceDialog());
        editMenu.add(replaceItem);
        
        editMenu.addSeparator();
        
        JMenuItem selectAllItem = new JMenuItem("Select All", 'A');
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke("control A"));
        selectAllItem.addActionListener(e -> codeEditor.selectAll());
        editMenu.add(selectAllItem);
        
        // Run menu
        JMenu runMenu = new JMenu("Run");
        runMenu.setMnemonic('R');
        
        JMenuItem compileItem = new JMenuItem("Compile", 'C');
        compileItem.setAccelerator(KeyStroke.getKeyStroke("F5"));
        compileItem.addActionListener(e -> compile());
        runMenu.add(compileItem);
        
        // Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        
        JMenuItem aboutItem = new JMenuItem("About", 'A');
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);
        
        JMenuItem usageItem = new JMenuItem("Usage", 'U');
        usageItem.addActionListener(e -> showUsageDialog());
        helpMenu.add(usageItem);
        
        // Add to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(runMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void setupLayout() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
        
        setSize(1400, 900);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Toolbar
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        toolbar.setBorder(BorderFactory.createEtchedBorder());
        Font neon=new Font("Monaspace Neon NF",Font.BOLD,15);
        // File group
        JPanel fileGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        TitledBorder fileBorder=BorderFactory.createTitledBorder("File");
        fileBorder.setTitleFont(neon);
        fileGroup.setBorder(fileBorder);
        fileGroup.add(newButton);
        fileGroup.add(openButton);
        fileGroup.add(saveButton);
        fileGroup.add(saveAsButton);
        
        // Example group
        JPanel exampleGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        TitledBorder exampleBorder=BorderFactory.createTitledBorder("Examples");
        exampleBorder.setTitleFont(neon);
        exampleGroup.setBorder(exampleBorder);
        exampleGroup.add(exampleSelector);
        
        // Action group
        JPanel actionGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        TitledBorder actionBorder=BorderFactory.createTitledBorder("Action");
        actionBorder.setTitleFont(neon);
        actionGroup.setBorder(actionBorder);
        actionGroup.add(compileButton);
        actionGroup.add(clearButton);
        
        // Options group
        JPanel optionsGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        TitledBorder optionsBorder=BorderFactory.createTitledBorder("Options");
        optionsBorder.setTitleFont(neon);
        optionsGroup.setBorder(optionsBorder);
        optionsGroup.add(optimizeCheckBox);
        optionsGroup.add(verboseCheckBox);
        
        toolbar.add(fileGroup);
        toolbar.add(exampleGroup);
        toolbar.add(actionGroup);
        toolbar.add(optionsGroup);
        
        // Editor panel
        JPanel editorPanel = new JPanel(new BorderLayout());
        editorPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "GO Source Code Editor",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));
        
        // Line numbers
        JTextArea lineNumbers = new JTextArea("1");
        lineNumbers.setEditable(false);
        lineNumbers.setBackground(new Color(240, 240, 240));
        lineNumbers.setFont(codeEditor.getFont());
        lineNumbers.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JScrollPane editorScroll = new JScrollPane(codeEditor);
        editorScroll.setRowHeaderView(lineNumbers);
        
        // Update line numbers
        codeEditor.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateLineNumbers(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateLineNumbers(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateLineNumbers(); }
            
            private void updateLineNumbers() {
                int lines = codeEditor.getLineCount();
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= lines; i++) {
                    sb.append(i).append("\n");
                }
                lineNumbers.setText(sb.toString());
            }
        });
        
        editorPanel.add(editorScroll, BorderLayout.CENTER);
        
        // Result panel
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Compilation Results",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));
        resultPanel.add(resultTabs, BorderLayout.CENTER);
        
        // Split pane
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            editorPanel,
            resultPanel
        );
        splitPane.setDividerLocation(650);
        splitPane.setResizeWeight(0.5);
        
        // Add to main panel
        mainPanel.add(toolbar, BorderLayout.NORTH);
        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void setupKeyBindings() {
        // F5 for compile
        InputMap inputMap = codeEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = codeEditor.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke("F5"), "compile");
        actionMap.put("compile", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                compile();
            }
        });
    }
    
    private void newFile() {
        if (isModified) {
            int result = JOptionPane.showConfirmDialog(
                this,
                "File modified. Save changes?",
                "New File",
                JOptionPane.YES_NO_CANCEL_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                saveFile();
            } else if (result == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        
        codeEditor.setText("package main\n\nfunc main() {\n    // Write your code here\n    \n}\n");
        currentFile = null;
        isModified = false;
        undoManager.discardAllEdits();
        updateTitle();
        updateStatus("New file created");
        clearResults();
    }
    
    private void openFile() {
        if (isModified) {
            int result = JOptionPane.showConfirmDialog(
                this,
                "File modified. Save changes?",
                "Open File",
                JOptionPane.YES_NO_CANCEL_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                saveFile();
            } else if (result == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("examples"));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".go");
            }
            public String getDescription() {
                return "GO Source Files (*.go)";
            }
        });
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                codeEditor.setText(content);
                codeEditor.setCaretPosition(0);
                currentFile = file;
                isModified = false;
                undoManager.discardAllEdits();
                updateTitle();
                updateStatus("Opened: " + file.getName());
                clearResults();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Failed to open file: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    private void saveFile() {
        if (currentFile == null) {
            saveFileAs();
        } else {
            try {
                Files.write(currentFile.toPath(), codeEditor.getText().getBytes(StandardCharsets.UTF_8));
                isModified = false;
                updateTitle();
                updateStatus("Saved: " + currentFile.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Failed to save file: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("examples"));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".go");
            }
            public String getDescription() {
                return "GO Source Files (*.go)";
            }
        });
        
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".go")) {
                file = new File(file.getAbsolutePath() + ".go");
            }
            
            if (file.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "File exists. Overwrite?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            try {
                Files.write(file.toPath(), codeEditor.getText().getBytes(StandardCharsets.UTF_8));
                currentFile = file;
                isModified = false;
                updateTitle();
                updateStatus("Saved: " + file.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Failed to save file: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    private void clearEditor() {
        if (codeEditor.getText().length() > 0) {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Clear editor?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                codeEditor.setText("");
                clearResults();
                updateStatus("Editor cleared");
            }
        }
    }
    
    private void clearResults() {
        outputArea.setText("");
        tacArea.setText("");
        optTacArea.setText("");
        asmArea.setText("");
        symbolTableArea.setText("");
    }
    
    private void loadExampleCode(String exampleName) {
        try {
            String fileName = "C:\\Users\\yaoyu\\Desktop\\VSCode\\ComputerScience\\Compilers\\examples\\" + exampleName + ".go";
            File file = new File(fileName);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                codeEditor.setText(content);
                codeEditor.setCaretPosition(0);
                currentFile = null;
                isModified = false;
                undoManager.discardAllEdits();
                updateTitle();
                updateStatus("Loaded example: " + exampleName + ".go");
                clearResults();
            }
        } catch (IOException e) {
            updateStatus("Failed to load example: " + e.getMessage());
        }
    }
    
    private void showFindDialog() {
        String searchText = JOptionPane.showInputDialog(this, "Find:", "Find", JOptionPane.PLAIN_MESSAGE);
        if (searchText != null && !searchText.isEmpty()) {
            String text = codeEditor.getText();
            int index = text.indexOf(searchText, codeEditor.getCaretPosition());
            if (index >= 0) {
                codeEditor.setCaretPosition(index);
                codeEditor.moveCaretPosition(index + searchText.length());
                updateStatus("Found: " + searchText);
            } else {
                index = text.indexOf(searchText);
                if (index >= 0) {
                    codeEditor.setCaretPosition(index);
                    codeEditor.moveCaretPosition(index + searchText.length());
                    updateStatus("Found: " + searchText + " (from start)");
                } else {
                    JOptionPane.showMessageDialog(this, "Not found: " + searchText, "Find", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    private void showReplaceDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField findField = new JTextField();
        JTextField replaceField = new JTextField();
        panel.add(new JLabel("Find:"));
        panel.add(findField);
        panel.add(new JLabel("Replace:"));
        panel.add(replaceField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Replace", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String findText = findField.getText();
            String replaceText = replaceField.getText();
            if (!findText.isEmpty()) {
                String text = codeEditor.getText();
                String newText = text.replace(findText, replaceText);
                codeEditor.setText(newText);
                updateStatus("Replaced all: " + findText);
            }
        }
    }
    
    private void showAboutDialog() {
        String message = "GO Language Compiler v1.0\n\n" +
                        "Features:\n" +
                        "- Complete GO compilation pipeline\n" +
                        "- Three-address code generation\n" +
                        "- Code optimization (5 techniques)\n" +
                        "- x86-64 assembly generation\n" +
                        "- Symbol table management\n" +
                        "- Error detection and reporting\n\n" +
                        "Technology: Java + ANTLR4\n" +
                        "Target: Linux x86-64";
        JOptionPane.showMessageDialog(this, message, "About", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showUsageDialog() {
        String message = "Usage:\n\n" +
                        "1. Write Code\n" +
                        "   - New/Open GO source file\n" +
                        "   - Or select example code\n\n" +
                        "2. Compile\n" +
                        "   - Click >> Compile or press F5\n" +
                        "   - View results in tabs\n\n" +
                        "3. Shortcuts\n" +
                        "   Ctrl+N - New file\n" +
                        "   Ctrl+O - Open file\n" +
                        "   Ctrl+S - Save file\n" +
                        "   Ctrl+F - Find\n" +
                        "   Ctrl+H - Replace\n" +
                        "   Ctrl+Z - Undo\n" +
                        "   Ctrl+Y - Redo\n" +
                        "   F5 - Compile\n\n" +
                        "4. Options\n" +
                        "   - Enable Optimization: TAC optimization\n" +
                        "   - Verbose Output: Detailed info";
        JOptionPane.showMessageDialog(this, message, "Usage", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exitApplication() {
        if (isModified) {
            int result = JOptionPane.showConfirmDialog(
                this,
                "File modified. Save changes?",
                "Exit",
                JOptionPane.YES_NO_CANCEL_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                saveFile();
            } else if (result == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        System.exit(0);
    }
    
    private void setModified(boolean modified) {
        isModified = modified;
        updateTitle();
    }
    
    private void updateTitle() {
        String title = "GO Compiler IDE v1.0";
        if (currentFile != null) {
            title += " - " + currentFile.getName();
        }
        if (isModified) {
            title += " *";
        }
        setTitle(title);
    }
    
    private void updateStatus(String message) {
        statusLabel.setText(message + " | GO Compiler v1.0");
    }
    
    private void compile() {
        clearResults();
        
        updateStatus("Compiling...");
        outputArea.append("============================================================\n");
        outputArea.append("Compilation Started\n");
        outputArea.append("============================================================\n\n");
        
        try {
            // Save to temp file
            File tempFile = File.createTempFile("gocode", ".go");
            Files.write(tempFile.toPath(), codeEditor.getText().getBytes(StandardCharsets.UTF_8));
            
            // Error handler
            ErrorHandler errorHandler = new ErrorHandler();
            
            // Stage 1: Lexical and syntax analysis
            outputArea.append("[Stage 1] Lexical and Syntax Analysis...\n");
            CharStream input = CharStreams.fromFileName(tempFile.getAbsolutePath());
            GoLangLexer lexer = new GoLangLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GoLangParser parser = new GoLangParser(tokens);
            
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg,
                                      RecognitionException e) {
                    errorHandler.error(line, charPositionInLine, msg);
                }
            });
            
            ParseTree tree = parser.program();
            
            if (errorHandler.hasErrors()) {
                outputArea.append("X Syntax analysis failed\n\n");
                for (CompileError err : errorHandler.getErrors()) {
                    outputArea.append(err.toString() + "\n");
                }
                resultTabs.setSelectedIndex(0);
                tempFile.delete();
                updateStatus("Compilation failed - Syntax error");
                return;
            }
            outputArea.append("OK Syntax analysis successful\n\n");
            
            // Stage 2: Semantic analysis
            outputArea.append("[Stage 2] Semantic Analysis...\n");
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(errorHandler);
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(semanticAnalyzer, tree);
            
            if (errorHandler.hasErrors()) {
                outputArea.append("X Semantic analysis failed\n\n");
                for (CompileError err : errorHandler.getErrors()) {
                    outputArea.append(err.toString() + "\n");
                }
                resultTabs.setSelectedIndex(0);
                tempFile.delete();
                updateStatus("Compilation failed - Semantic error");
                return;
            }
            outputArea.append("OK Semantic analysis successful\n\n");
            
            // Symbol table
            if (verboseCheckBox.isSelected()) {
                symbolTableArea.append("=== Global Symbol Table ===\n\n");
                GlobalScope globalScope = semanticAnalyzer.getGlobalScope();
                for (Symbol sym : globalScope.getSymbols().values()) {
                    symbolTableArea.append(sym.toString() + "\n");
                }
            }
            
            // Stage 3: TAC generation
            outputArea.append("[Stage 3] Three-Address Code Generation...\n");
            CodeGenerator codeGen = new CodeGenerator(
                semanticAnalyzer.getTypes(),
                semanticAnalyzer.getScopes()
            );
            walker.walk(codeGen, tree);
            
            TACGenerator tacGen = codeGen.getTAC();
            java.util.List<Quadruple> tacCode = tacGen.getCode();
            
            outputArea.append("OK Generated " + tacCode.size() + " TAC instructions\n\n");
            
            // Display TAC
            for (Quadruple quad : tacCode) {
                tacArea.append(quad.toString() + "\n");
            }
            
            // Stage 4: Optimization
            java.util.List<Quadruple> optimizedCode = tacCode;
            if (optimizeCheckBox.isSelected()) {
                outputArea.append("[Stage 4] Code Optimization...\n");
                TACOptimizer optimizer = new TACOptimizer(tacCode);
                optimizedCode = optimizer.optimize();
                
                int removed = tacCode.size() - optimizedCode.size();
                double percent = tacCode.size() > 0 ? (100.0 * removed / tacCode.size()) : 0;
                
                outputArea.append("Instructions before: " + tacCode.size() + "\n");
                outputArea.append("Instructions after: " + optimizedCode.size() + "\n");
                outputArea.append("Instructions removed: " + removed + "\n");
                outputArea.append(String.format("Optimization rate: %.2f%%\n\n", percent));
            }
            
            // Display optimized TAC
            for (Quadruple quad : optimizedCode) {
                optTacArea.append(quad.toString() + "\n");
            }
            
            // Stage 5: Assembly generation
            outputArea.append("[Stage 5] Assembly Code Generation...\n");
            AssemblyGenerator asmGen = new AssemblyGenerator(optimizedCode);
            String asmCode = asmGen.generate();
            asmArea.append(asmCode);
            outputArea.append("OK Assembly code generated\n\n");
            
            // Done
            outputArea.append("============================================================\n");
            outputArea.append("Compilation Completed Successfully!\n");
            outputArea.append("============================================================\n");
            
            // Switch to TAC tab
            resultTabs.setSelectedIndex(1);
            
            // Cleanup
            tempFile.delete();
            
            updateStatus("Compilation successful");
            
        } catch (Exception e) {
            outputArea.append("\nError: " + e.getMessage() + "\n");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            outputArea.append(sw.toString());
            resultTabs.setSelectedIndex(0);
            updateStatus("Compilation failed - Internal error");
        }
    }
    
    public static void main(String[] args) {
        // Set default encoding
        System.setProperty("file.encoding", "UTF-8");
        
        SwingUtilities.invokeLater(() -> {
            GoCompilerIDE ide = new GoCompilerIDE();
            ide.setVisible(true);
        });
    }
}
