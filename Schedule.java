import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

//time


//change layouts and colours, make functionsout of stuff
//A band stuff - stages, days, file download, clear table



public class Schedule implements ActionListener{
    private final int MaxnumInputs = 3;
    private final int numPanels = 3;
    private final int maxNumDays = 3;

    private String name, priority, day, StartTime, EndTime;
    private int tableIndex, priorityNum,dayNum;
    private int numDays;
    private int numInputs = MaxnumInputs;
    private String mainEventTime = "23:00";
    
    
    JFrame frame = new JFrame("Festival Schedule 2022");
    JPanel panel[] = new JPanel[numPanels];
    JTextField textField[] = new JTextField[MaxnumInputs];
    JLabel label[] = new JLabel[MaxnumInputs];
    JButton sButton = new JButton("Insert");

    DefaultTableModel dtm[] = new DefaultTableModel[maxNumDays];
    JTable tabel[] = new JTable[maxNumDays];
    JPanel tabelPanel[] = new JPanel[maxNumDays];


    List list = new List();


    public Schedule(String _name, int _numDays){
        panel[0] = new JPanel(new BorderLayout());
        frame.setContentPane(panel[0]);
        frame.setTitle(_name);

        initInputPanel(_numDays);
        initTablePanel(_numDays);

        numDays = _numDays;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(750, 250);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        name = textField[0].getText();
        priority = textField[1].getText();
        
        if(numDays == 1){
            day = "1";
        }
        else{
            day = textField[2].getText();
        }
        

        if(StartMenu.isNumeric(priority) == true &&
        StartMenu.isNumeric(day) == true &&
        priority.length() > 0 && name.length() > 0
        && day.length() > 0){
            
            priorityNum = StartMenu.strToInt(priority);
            dayNum = StartMenu.strToInt(day);

            if(priorityNum > 0 && priorityNum < 4 &&
            validDay(numDays, dayNum) == true){
                tableIndex = list.getActIndex(priorityNum);
                
                if(tableIndex != Integer.MIN_VALUE){
                    StartTime = setStartTime(tableIndex);
                    EndTime = setEndTime(tableIndex);
                    
                    dtm[dayNum - 1].insertRow(tableIndex, new Object[] {StartTime, "End Timey" , name});
                }
                else{
                    textField[1].setText("Please set a valid POSITIVE integer input within range");
                }
            }
        }
        else{
            textField[1].setText("Please set a valid positive INTEGER input");
        }
    }
    
    
    //new functions---------------------------------------
    private String setStartTime(int _tableIndex){
        String time = mainEventTime;
        
        int hours=23;
        int minutes=0;

        int c = 0;
        
        //code to set time at given index
        //if empty, time = main event time
        //for loop(up to index)
        //minus 20 mins, if there is hour overlap deaal with this
        //return x
        if(_tableIndex == 0){
            time = mainEventTime;
        }
        else{
            for(int i = 0; i < _tableIndex; i++){
                minutes -= 30;
                if(minutes == -30){
                    hours -= 1;
                    minutes *= -1;
                }       
            }
            time = hours + ":" + minutes;
        }

        


        return time;
    }

   private void setEndTime(int _tableIndex){
       
   }
    
    
    private void updateTimes(int _tableIndex){
        //code to update all following row times
        //for loop(length - index)
        //add 20 mins to x, if x goes over hour deal with this
        //return x
        for(int j = _tableIndex + 1; j < list.getListSize(); j++){
            hours
            
            
            dtm.setValueAt()
        }
    }
    
    
    //--------------------------------------------
    private void initInputPanel(int _days){
        panel[1] = new JPanel(new FlowLayout());

        label[0] = new JLabel("Act Name: ");
        label[1] = new JLabel("Priority (1-3): ");
        
        switch(_days){
            case 1:
                break;
            case 2:
                label[2] = new JLabel("Day (1-2): ");
                break;
            case 3:
                label[2] = new JLabel("Day (1-3): ");
                break;
        }
        
        
        if(_days == 1){
            numInputs = 2;
        }

        for(int i = 0; i < numInputs; i++){
            textField[i] = new JTextField(20);
            
            panel[1].add(label[i]);
            panel[1].add(textField[i]);
        }

        sButton.addActionListener(this);
        panel[1].add(sButton);

        panel[0].add(panel[1], BorderLayout.WEST);
    }

    private void initTablePanel(int _days){
        panel[2] = new JPanel(new FlowLayout());

        for(int i = 0; i < _days ; i++){
            dtm[i] = new DefaultTableModel();
            tabel[i] = new JTable(dtm[i]);
            
            dtm[i].addColumn("Start Time");
            dtm[i].addColumn("End Time");
            dtm[i].addColumn("Act");

            panel[2].add(new JScrollPane(tabel[i]));

        }
        panel[0].add(panel[2], BorderLayout.EAST);
    }

    private boolean validDay(int x, int y){
        boolean b;
        switch(x){
            case 1:
                if(y!=1) b = false;

            case 2:
                if(y<1 || y>2) b = false;

            case 3:
                if(y<1 || y>3) b = false;
 
            default:
                b = true;    
            }

            return b;
    }
}