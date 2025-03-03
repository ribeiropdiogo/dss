package MediaCenter_GUI;

import Client.MediaCenterInterface;
import Exceptions.UtilizadorRepetidoException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class addUserForm extends JFrame {

    public void closeFrame() {
        super.dispose();
    }

    public addUserForm(MediaCenterInterface mediacenter, DefaultTableModel tm)
    {
        super( "Add User" );

        JPanel pnPanel0 = new JPanel();
        ButtonGroup rbgPanel0 = new ButtonGroup();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        JLabel lbUsernameLabel = new JLabel("username:");
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 6;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.EAST;
        gbcPanel0.insets = new Insets( 15,15,5,0 );
        gbPanel0.setConstraints(lbUsernameLabel, gbcPanel0 );
        pnPanel0.add(lbUsernameLabel);

        JLabel lbEmailLabel = new JLabel("email:");
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 6;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.EAST;
        gbcPanel0.insets = new Insets( 0,15,5,0 );
        gbPanel0.setConstraints(lbEmailLabel, gbcPanel0 );
        pnPanel0.add(lbEmailLabel);

        JLabel lbPasswordLabel = new JLabel("password:");
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 7;
        gbcPanel0.gridwidth = 6;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.EAST;
        gbcPanel0.insets = new Insets( 0,15,15,0 );
        gbPanel0.setConstraints(lbPasswordLabel, gbcPanel0 );
        pnPanel0.add(lbPasswordLabel);

        // user
        JTextField tfText0 = new JTextField();
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 9;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 15,0,5,35 );
        gbPanel0.setConstraints(tfText0, gbcPanel0 );
        pnPanel0.add(tfText0);

        // email
        JTextField tfText1 = new JTextField();
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 9;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,0,5,35 );
        gbPanel0.setConstraints(tfText1, gbcPanel0 );
        pnPanel0.add(tfText1);

        // pass
        JTextField tfText2 = new JTextField();
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 7;
        gbcPanel0.gridwidth = 9;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,0,15,35 );
        gbPanel0.setConstraints(tfText2, gbcPanel0 );
        pnPanel0.add(tfText2);

        JRadioButton rbRdBut0 = new JRadioButton("User");
        rbRdBut0.setSelected( true );
        rbgPanel0.add(rbRdBut0);
        gbcPanel0.gridx = 3;
        gbcPanel0.gridy = 10;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.EAST;
        gbPanel0.setConstraints(rbRdBut0, gbcPanel0 );
        pnPanel0.add(rbRdBut0);

        JRadioButton rbRdBut1 = new JRadioButton("Admin");
        rbgPanel0.add(rbRdBut1);
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 10;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbPanel0.setConstraints(rbRdBut1, gbcPanel0 );
        pnPanel0.add(rbRdBut1);

        JButton btBut1 = new JButton("Create");
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 13;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,0,15,15 );
        gbPanel0.setConstraints(btBut1, gbcPanel0 );
        pnPanel0.add(btBut1);

        setContentPane(pnPanel0);
        pack();
        setLocationRelativeTo(null);
        setSize(250, 200);
        setVisible( true );


        btBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = "utilizador";
                String user = tfText0.getText();
                String email = tfText1.getText();
                String pass = tfText2.getText();

                if(!user.isBlank() && !email.isBlank() && !pass.isBlank()) {
                    try {
                        if(rbRdBut1.isSelected())
                            type = "admin";

                        mediacenter.newConta(type, user, email, pass);

                        tm.addRow(new Object[]{user, email, "Remove"});

                        closeFrame();
                    } catch (Exception exc) {
                        new MessageDialog("Error", exc.getMessage());
                    }
                }
                else
                    new MessageDialog("Error", "You must fill in all the fields!");
            }
        });
    }

}
