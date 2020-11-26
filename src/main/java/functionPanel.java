import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class functionPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;

    private List<JTextField> fieldInputs = new ArrayList<>();

    private List<JLabel> outputLabels = new ArrayList<>();
    private List<String> outputStrings = new ArrayList<>();

    private Font boldFont = new Font("Arial", Font.BOLD, 30);
   
    public functionPanel(String description, List<String> inputs, List<String> outputs, Function<List<String>,List<String>> grpcFunction) {  
        JLabel label = new JLabel(description);
        label.setFont(boldFont);
        this.add(label);
        this.setPreferredSize(new Dimension(1500, 100));

        for(String input : inputs){
            JLabel labelInput = new JLabel(input);
            labelInput.setFont(boldFont);
            JTextField fieldInput = new JTextField();
            fieldInput.setPreferredSize(new Dimension(150, 50));
            fieldInput.setFont(boldFont);
            fieldInputs.add(fieldInput);
            this.add(labelInput);
            this.add(fieldInput);
        }

        JButton button = new JButton("Send");
        button.setPreferredSize(new Dimension(150,50));
        button.setFont(boldFont);
        button.addActionListener(e -> {
            updateOutputLabels(grpcFunction.apply(this.getFieldValues()));
        });
        this.add(button);

        outputStrings = new ArrayList<>(outputs);  
        for(String output: outputs){
            JLabel labelOutput = new JLabel(output);
            labelOutput.setFont(boldFont);
            outputLabels.add(labelOutput);
            this.add(labelOutput);
        }
        
    }

    public List<String> getFieldValues(){
        List<String> fieldValues = new ArrayList<>();
        for(JTextField fieldInput : fieldInputs){
            fieldValues.add(fieldInput.getText());
        }
        return fieldValues;
    }

    private void updateOutputLabels(List<String> outputs){
        System.out.println(outputs.toString());
        System.out.println(outputStrings.toString());
        int outputsSize = outputLabels.size();  
        for(int i = 0; i < outputsSize; i++){
            JLabel labelOutput = outputLabels.get(i);
            try{
                String labelString = outputStrings.get(i);
                try{
                    String value = outputs.get(i);
                    labelOutput.setText(labelString + ": " + value);
                }
                catch(IndexOutOfBoundsException e){
                    labelOutput.setText(labelString);
                }
            }
            catch(IndexOutOfBoundsException e){
            }
        }
    }
}