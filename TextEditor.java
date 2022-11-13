import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;

    TextEditor() {
        // Initilize
        frame = new JFrame();

        // initilize menubar
        menuBar = new JMenuBar();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        menuBar.add(file);
        menuBar.add(edit);

        // textarea
        textArea = new JTextArea();

        // jMenu item
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);


        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // JMenu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // scrool bar
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel jp = new JPanel();

        jp.setBorder(new EmptyBorder(5 ,5, 5, 5));
        jp.setLayout(new BorderLayout( 8, 0));
        jp.add(scrollPane);

        frame.add(jp);
        frame.setJMenuBar(menuBar);
        frame.add(textArea);


        frame.setBounds(50, 50, 400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Text Editor");
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
        if(e.getSource() == saveFile){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setApproveButtonText("Save");
            int chooseOption = fileChooser.showSaveDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                String filePath = file.getPath();
                try {
                    BufferedWriter outFile = null;
                    outFile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outFile);
                    outFile.close();
                } catch(Exception exception){
                    System.out.println(exception);
                }
            }
        }
        if(e.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseFileOption = fileChooser.showOpenDialog(null);

            if (chooseFileOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String current = "", output="";
                    while ((current = bufferedReader.readLine())!=null){
                        output += current + "\n";
                    }
                    textArea.setText(output);
                }catch (Exception c){

                }
            }
        }
        if(e.getSource() == cut){
            textArea.cut();
        }
        if(e.getSource() == copy){
            textArea.copy();
        }
        if(e.getSource() == paste){
            textArea.paste();
        }
        if(e.getSource() == selectAll){
            textArea.selectAll();
        }
        if(e.getSource() == close){
            System.exit(0);
        }
    }
}
