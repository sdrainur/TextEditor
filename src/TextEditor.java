import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSize;
    JButton fontColor;
    JComboBox font;

    public TextEditor(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Текстовый редактор");
        setSize(800, 600);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN,  20));
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        fontSize = new JSpinner();
        fontSize.setPreferredSize(new Dimension(60, 30));
        fontSize.setValue(14);
        fontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int)fontSize.getValue()));
            }
        });
        fontColor = new JButton("Цвет");
        fontColor.addActionListener(this);
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        font = new JComboBox(fonts);
        font.addActionListener(this);
        font.setSelectedItem("Times New Roman");
        add(fontSize);
        add(fontColor);
        add(font);
        add(scrollPane);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==fontColor){
            JColorChooser colorChooser = new JColorChooser();
            Color color = colorChooser.showDialog(null, "Выберите цвет", Color.BLACK);
            textArea.setForeground(color);
        }
        if(e.getSource()==font){
            textArea.setFont(new Font((String)font.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }
    }
}
