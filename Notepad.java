import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.*;

class MyFrame extends JFrame implements ActionListener
{
   private JMenuBar mb;
   private JMenu menu_file,menu_edit;
   private JMenuItem mi;
   private JTextArea ta;
   private JScrollPane sb;
   private JFileChooser jfc;
   private FileNameExtensionFilter filter;

   public MyFrame()
   {
      super("Simple Notepad");

      mb= new JMenuBar();
      this.setJMenuBar(mb);

      menu_file=new JMenu("file");
      mb.add(menu_file);

      String arr1[]={"New","Save","Open","Exit"};
      String img1[]={"new.jpg","save.jpg","open.jpg","exit.jpg"};
     
      for(int i=0;i<arr1.length;i++)
      {
         mi=new JMenuItem(arr1[i],new ImageIcon("images/"+img1[i]));
         menu_file.add(mi);
         mi.addActionListener(this);
      }

      menu_edit=new JMenu("edit");
      mb.add(menu_edit);

      String arr2[]={"Cut","Copy","Paste"};
      String img2[]={"cut.jpg","copy.jpg","paste.jpg"};

      for(int i=0;i<arr2.length;i++)
      {
         mi=new JMenuItem(arr2[i],new ImageIcon("images/"+img2[i]));
         menu_edit.add(mi);
         mi.addActionListener(this);

      }

       ta=new JTextArea();
       ta.setFont(new Font("Gabriola",Font.BOLD,30));

       sb=new JScrollPane(ta);
       this.add(sb,BorderLayout.CENTER);

       jfc=new JFileChooser();
       String description[]={"C File","C++ File","Java File","Text File"};
       String extension[]={"c","cpp","java","txt"};

       for(int i=0;i<description.length;i++)
       {
         filter=new FileNameExtensionFilter(description[i],extension[i]);
         jfc.setFileFilter(filter);
       }
   
      this.setVisible(true);
      this.setSize(500,500);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }
   @Override
   public void actionPerformed(ActionEvent e)
   {
      String cmd=e.getActionCommand().toUpperCase();
      switch(cmd)
      {
          case"NEW":ta.setText("");break;
          case"SAVE": 
                int x=jfc.showSaveDialog(this);
                if(x==JFileChooser.APPROVE_OPTION)
                {
                  try
                  {
                        String file_data=ta.getText();
                        byte arr[]=file_data.getBytes();

                        File f=jfc.getSelectedFile();
                        FileOutputStream fout=new FileOutputStream(f);
                        fout.write(arr);
                        fout.close();
                        JOptionPane.showMessageDialog(this,"File Saved..");
                  }
                  catch(Exception ex)
                  {
                     JOptionPane.showMessageDialog(this,ex);
                  }
                }
          break;
         case"OPEN":
            int y=jfc.showOpenDialog(this);
            if(y==JFileChooser.APPROVE_OPTION)
            {
               try
               {
                  File f=jfc.getSelectedFile();
                  FileInputStream fin=new FileInputStream(f);
                  int file_size=fin.available();
                  byte arr[]=new byte[file_size];
                  fin.read(arr);
                  fin.close();
                  ta.setText(new String(arr));
               }
               catch(Exception ex)
               {
                  JOptionPane.showMessageDialog(this,ex);
               }
            }

            break;
         case"EXIT":this.dispose();break;
         case"COPY":ta.copy();break;
         case"PASTE":ta.paste();break;
         case"CUT":ta.cut();break;
      }
   }
   
}
class Notepad
{
   public static void main(String[] args) {
      
      new MyFrame();
   }
}