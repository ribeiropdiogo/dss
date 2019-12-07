package MediaCenter_GUI;

import MediaCenterSystem.MediaCenter;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPasswordForm extends JFrame {

    static ResetPasswordForm theresetPasswordform;

    JPanel pnResetPasswordPanel;
    JTextField tfUsernameField;
    JLabel lbUsernameLabel;
    JLabel lbEmailLabel;
    JTextField tfEmailField;
    JButton btSendButton;

    public void CloseFrame(){
        super.dispose();
    }

    public ResetPasswordForm(MediaCenter mediacenter)
    {
        super("Reset Password");

        pnResetPasswordPanel = new JPanel();
        GridBagLayout gbResetPasswordPanel = new GridBagLayout();
        GridBagConstraints gbcResetPasswordPanel = new GridBagConstraints();
        pnResetPasswordPanel.setLayout( gbResetPasswordPanel );

        tfUsernameField = new JTextField( );
        gbcResetPasswordPanel.gridx = 6;
        gbcResetPasswordPanel.gridy = 1;
        gbcResetPasswordPanel.gridwidth = 16;
        gbcResetPasswordPanel.gridheight = 3;
        gbcResetPasswordPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcResetPasswordPanel.weightx = 1;
        gbcResetPasswordPanel.weighty = 0;
        gbcResetPasswordPanel.anchor = GridBagConstraints.WEST;
        gbcResetPasswordPanel.insets = new Insets( 15,0,0,15 );
        gbResetPasswordPanel.setConstraints( tfUsernameField, gbcResetPasswordPanel );
        pnResetPasswordPanel.add( tfUsernameField );

        lbUsernameLabel = new JLabel( "username:"  );
        gbcResetPasswordPanel.gridx = 1;
        gbcResetPasswordPanel.gridy = 1;
        gbcResetPasswordPanel.gridwidth = 4;
        gbcResetPasswordPanel.gridheight = 3;
        gbcResetPasswordPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcResetPasswordPanel.weightx = 0;
        gbcResetPasswordPanel.weighty = 0;
        gbcResetPasswordPanel.anchor = GridBagConstraints.EAST;
        gbcResetPasswordPanel.insets = new Insets( 15,15,0,0 );
        gbResetPasswordPanel.setConstraints( lbUsernameLabel, gbcResetPasswordPanel );
        pnResetPasswordPanel.add( lbUsernameLabel );

        lbEmailLabel = new JLabel( "email:"  );
        gbcResetPasswordPanel.gridx = 2;
        gbcResetPasswordPanel.gridy = 5;
        gbcResetPasswordPanel.gridwidth = 3;
        gbcResetPasswordPanel.gridheight = 3;
        gbcResetPasswordPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcResetPasswordPanel.weightx = 0;
        gbcResetPasswordPanel.weighty = 0;
        gbcResetPasswordPanel.anchor = GridBagConstraints.EAST;
        gbcResetPasswordPanel.insets = new Insets( 0,42,0,0 );
        gbResetPasswordPanel.setConstraints( lbEmailLabel, gbcResetPasswordPanel );
        pnResetPasswordPanel.add( lbEmailLabel );

        tfEmailField = new JTextField( );
        gbcResetPasswordPanel.gridx = 6;
        gbcResetPasswordPanel.gridy = 5;
        gbcResetPasswordPanel.gridwidth = 16;
        gbcResetPasswordPanel.gridheight = 3;
        gbcResetPasswordPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcResetPasswordPanel.weightx = 1;
        gbcResetPasswordPanel.weighty = 0;
        gbcResetPasswordPanel.anchor = GridBagConstraints.WEST;
        gbcResetPasswordPanel.insets = new Insets( 0,0,0,15 );
        gbResetPasswordPanel.setConstraints( tfEmailField, gbcResetPasswordPanel );
        pnResetPasswordPanel.add( tfEmailField );

        btSendButton = new JButton( "send"  );
        gbcResetPasswordPanel.gridx = 16;
        gbcResetPasswordPanel.gridy = 9;
        gbcResetPasswordPanel.gridwidth = 6;
        gbcResetPasswordPanel.gridheight = 3;
        gbcResetPasswordPanel.fill = GridBagConstraints.NONE;
        gbcResetPasswordPanel.weightx = 0;
        gbcResetPasswordPanel.weighty = 0;
        gbcResetPasswordPanel.anchor = GridBagConstraints.EAST;
        gbcResetPasswordPanel.insets = new Insets( 0,0,15,15 );
        gbResetPasswordPanel.setConstraints( btSendButton, gbcResetPasswordPanel );
        pnResetPasswordPanel.add( btSendButton );


        setContentPane( pnResetPasswordPanel );
        pack();
        setLocationRelativeTo(null);
        setSize(220,120);
        setResizable(false);
        setVisible( true );

        btSendButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               String username = tfUsernameField.getText();
               String email = tfEmailField.getText();

               if (username.isBlank() || email.isBlank()){
                   MessageDialog md = new MessageDialog("Error","You need to type your username and email");
               } else {
                   mediacenter.forgottenPassword(username,email);
                   CloseFrame();
               }
            }
        });
    }
}
