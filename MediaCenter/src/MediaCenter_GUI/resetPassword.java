package MediaCenter_GUI;

import Client.MediaCenterInterface;

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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class resetPassword extends JFrame
{
    static resetPassword theresetPassword;

    JPanel pnAlterarPassword;
    JLabel lbUsernameLabel;
    JLabel lbEmailLabel;
    JTextField tfOldPassword;
    JTextField tfNewPassword;
    JButton btOkButton;

    public void closeWindow() {
        super.dispose();
    }

    public resetPassword(MediaCenterInterface mediacenter)
    {
        super( "Reset Password" );
        theresetPassword = this;
        pnAlterarPassword = new JPanel();
        GridBagLayout gbAlterarPassword = new GridBagLayout();
        GridBagConstraints gbcAlterarPassword = new GridBagConstraints();
        pnAlterarPassword.setLayout( gbAlterarPassword );

        lbUsernameLabel = new JLabel( "Username:"  );
        gbcAlterarPassword.gridx = 1;
        gbcAlterarPassword.gridy = 1;
        gbcAlterarPassword.gridwidth = 7;
        gbcAlterarPassword.gridheight = 3;
        gbcAlterarPassword.fill = GridBagConstraints.NONE;
        gbcAlterarPassword.weightx = 0;
        gbcAlterarPassword.weighty = 0;
        gbcAlterarPassword.anchor = GridBagConstraints.EAST;
        gbcAlterarPassword.insets = new Insets( 0,15,0,0 );
        gbAlterarPassword.setConstraints( lbUsernameLabel, gbcAlterarPassword );
        pnAlterarPassword.add( lbUsernameLabel );

        lbEmailLabel = new JLabel( "Email:"  );
        gbcAlterarPassword.gridx = 1;
        gbcAlterarPassword.gridy = 5;
        gbcAlterarPassword.gridwidth = 7;
        gbcAlterarPassword.gridheight = 3;
        gbcAlterarPassword.fill = GridBagConstraints.NONE;
        gbcAlterarPassword.weightx = 0;
        gbcAlterarPassword.weighty = 0;
        gbcAlterarPassword.anchor = GridBagConstraints.EAST;
        gbcAlterarPassword.insets = new Insets( 0,15,0,0 );
        gbAlterarPassword.setConstraints( lbEmailLabel, gbcAlterarPassword );
        pnAlterarPassword.add( lbEmailLabel );

        tfOldPassword = new JTextField( );
        gbcAlterarPassword.gridx = 9;
        gbcAlterarPassword.gridy = 1;
        gbcAlterarPassword.gridwidth = 17;
        gbcAlterarPassword.gridheight = 3;
        gbcAlterarPassword.fill = GridBagConstraints.HORIZONTAL;
        gbcAlterarPassword.weightx = 1;
        gbcAlterarPassword.weighty = 0;
        gbcAlterarPassword.anchor = GridBagConstraints.WEST;
        gbcAlterarPassword.insets = new Insets( 0,0,0,15 );
        gbAlterarPassword.setConstraints( tfOldPassword, gbcAlterarPassword );
        pnAlterarPassword.add( tfOldPassword );

        tfNewPassword = new JTextField( );
        gbcAlterarPassword.gridx = 9;
        gbcAlterarPassword.gridy = 5;
        gbcAlterarPassword.gridwidth = 17;
        gbcAlterarPassword.gridheight = 3;
        gbcAlterarPassword.fill = GridBagConstraints.HORIZONTAL;
        gbcAlterarPassword.weightx = 1;
        gbcAlterarPassword.weighty = 0;
        gbcAlterarPassword.anchor = GridBagConstraints.WEST;
        gbcAlterarPassword.insets = new Insets( 0,0,0,15 );
        gbAlterarPassword.setConstraints( tfNewPassword, gbcAlterarPassword );
        pnAlterarPassword.add( tfNewPassword );

        btOkButton = new JButton( "Send"  );
        gbcAlterarPassword.gridx = 21;
        gbcAlterarPassword.gridy = 9;
        gbcAlterarPassword.gridwidth = 5;
        gbcAlterarPassword.gridheight = 3;
        gbcAlterarPassword.fill = GridBagConstraints.NONE;
        gbcAlterarPassword.weightx = 1;
        gbcAlterarPassword.weighty = 0;
        gbcAlterarPassword.anchor = GridBagConstraints.EAST;
        gbcAlterarPassword.insets = new Insets( 0,0,0,15 );
        gbAlterarPassword.setConstraints( btOkButton, gbcAlterarPassword );
        pnAlterarPassword.add( btOkButton );

        btOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theresetPassword.closeWindow();
            }
        });

        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( pnAlterarPassword );
        pack();
        setLocationRelativeTo(null);
        setVisible( true );
    }
}
