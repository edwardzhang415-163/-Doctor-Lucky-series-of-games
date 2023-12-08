package src.view;


import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Represents the text panel.
 */
public class TextPanel extends JPanel {
  private JTextArea outputTextArea;

  /**
   * Constructs a text panel object.
   */
  public TextPanel() {
    setLayout(new BorderLayout());
    outputTextArea = new JTextArea(10, 42);
    outputTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(outputTextArea);

    add(scrollPane, BorderLayout.CENTER);

  }

  public void updateGameOutput(String format) {
    outputTextArea.append(format + "\n");
  }

}