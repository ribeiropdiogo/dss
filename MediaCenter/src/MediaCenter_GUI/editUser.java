package MediaCenter_GUI;

import Client.MediaCenterInterface;
import Exceptions.PasswordFracaException;
import Exceptions.PasswordIncorretaException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editUser extends JFrame {


    public void closeFrame() {
        super.dispose();
    }

    public editUser(MediaCenterInterface mediacenter, String username)
    {
        super( "Edit settings" );

        JPanel pnPanel0 = new JPanel();
        ButtonGroup rbgPanel0 = new ButtonGroup();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        JLabel lbUsernameLabel = new JLabel("new email:");
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

        JLabel lbEmailLabel = new JLabel("old pass:");
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

        JLabel lbPasswordLabel = new JLabel("new pass:");
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

        JLabel lbPasswordLabel2 = new JLabel("confirm pass:");
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 10;
        gbcPanel0.gridwidth = 6;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.EAST;
        gbcPanel0.insets = new Insets( 0,15,15,0 );
        gbPanel0.setConstraints(lbPasswordLabel2, gbcPanel0 );
        pnPanel0.add(lbPasswordLabel2);

        // email
        JTextField tfText0 = new JTextField();
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 20;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 15,0,5,35 );
        gbPanel0.setConstraints(tfText0, gbcPanel0 );
        pnPanel0.add(tfText0);

        // new pass
        JPasswordField tfText1 = new JPasswordField();
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 20;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,0,5,35 );
        gbPanel0.setConstraints(tfText1, gbcPanel0 );
        pnPanel0.add(tfText1);

        // confirm pass
        JPasswordField tfText2 = new JPasswordField();
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 7;
        gbcPanel0.gridwidth = 20;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,0,15,35 );
        gbPanel0.setConstraints(tfText2, gbcPanel0 );
        pnPanel0.add(tfText2);

        JPasswordField tfText3 = new JPasswordField();
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 10;
        gbcPanel0.gridwidth = 20;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,0,15,35 );
        gbPanel0.setConstraints(tfText3, gbcPanel0 );
        pnPanel0.add(tfText3);

        JButton btBut1 = new JButton("Apply");
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

        JButton btButCancel = new JButton("Cancel");
        gbcPanel0.gridx = 3;
        gbcPanel0.gridy = 13;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,0,15,15 );
        gbPanel0.setConstraints(btButCancel, gbcPanel0 );
        pnPanel0.add(btButCancel);

        setContentPane(pnPanel0);
        setSize(250, 200);
        pack();
        setLocationRelativeTo(null);
        setVisible( true );


        btBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfText0.getText();
                String oldpass = String.valueOf(tfText1.getPassword());
                String pass = String.valueOf(tfText2.getPassword());
                String confirmpass = String.valueOf(tfText3.getPassword());

                if(!email.isBlank())
                    mediacenter.alteraEmail(username, email);

                if(!pass.isBlank() && !confirmpass.isBlank() && !oldpass.isBlank()) {
                    if(!pass.equals(confirmpass))
                        new MessageDialog("Error", "Password confirmation is wrong!");
                    else {
                        try {
                            mediacenter.alteraPass(username,oldpass,pass,confirmpass);
                            new MessageDialog("Success", "Settings have been edited with success!");
                            closeFrame();
                        } catch(PasswordIncorretaException exc) {
                            new MessageDialog("Error", exc.getMessage());
                        } catch (PasswordFracaException exc) {
                            new MessageDialog("Error", exc.getMessage());
                        }
                    }
                }
            }
        });

        btButCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame();
            }
        });
    }

}
