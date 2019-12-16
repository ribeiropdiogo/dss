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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FriendsMenu extends JFrame{

    static FriendsMenu themenuamigos;

    JPanel pnMenuAmigos;
    JList cmbCombo0;
    JButton btAcceptButton;
    JButton btRefuseButton;
    JList lsList0;
    JButton btSendButton;
    JButton btRemoveButton;
    JTextField tfText0;

    public FriendsMenu(MediaCenterInterface mediacenter, String username)
    {
        super( "Friends Menu" );

        pnMenuAmigos = new JPanel();
        GridBagLayout gbMenuAmigos = new GridBagLayout();
        GridBagConstraints gbcMenuAmigos = new GridBagConstraints();
        pnMenuAmigos.setLayout( gbMenuAmigos );

        String []dataCombo0 = mediacenter.getPedidos(username);
        cmbCombo0 = new JList( dataCombo0 );
        gbcMenuAmigos.gridx = 1;
        gbcMenuAmigos.gridy = 1;
        gbcMenuAmigos.gridwidth = 1;
        gbcMenuAmigos.gridheight = 1;
        gbcMenuAmigos.fill = GridBagConstraints.BOTH;
        gbcMenuAmigos.weightx = 1;
        gbcMenuAmigos.weighty = 0;
        gbcMenuAmigos.anchor = GridBagConstraints.CENTER;
        gbcMenuAmigos.insets = new Insets( 15,15,0,0 );
        gbMenuAmigos.setConstraints( cmbCombo0, gbcMenuAmigos );
        pnMenuAmigos.add( cmbCombo0 );

        btAcceptButton = new JButton( "Accept"  );
        gbcMenuAmigos.gridx = 3;
        gbcMenuAmigos.gridy = 1;
        gbcMenuAmigos.gridwidth = 1;
        gbcMenuAmigos.gridheight = 1;
        gbcMenuAmigos.fill = GridBagConstraints.NONE;
        gbcMenuAmigos.weightx = 1;
        gbcMenuAmigos.weighty = 0;
        gbcMenuAmigos.anchor = GridBagConstraints.CENTER;
        gbcMenuAmigos.insets = new Insets( 5,0,0,0 );
        gbMenuAmigos.setConstraints( btAcceptButton, gbcMenuAmigos );
        pnMenuAmigos.add( btAcceptButton );

        btRefuseButton = new JButton( "Refuse"  );
        gbcMenuAmigos.gridx = 3;
        gbcMenuAmigos.gridy = 3;
        gbcMenuAmigos.gridwidth = 1;
        gbcMenuAmigos.gridheight = 1;
        gbcMenuAmigos.fill = GridBagConstraints.NONE;
        gbcMenuAmigos.weightx = 1;
        gbcMenuAmigos.weighty = 0;
        gbcMenuAmigos.anchor = GridBagConstraints.CENTER;
        gbcMenuAmigos.insets = new Insets( 0,0,15,0 );
        gbMenuAmigos.setConstraints( btRefuseButton, gbcMenuAmigos );
        pnMenuAmigos.add( btRefuseButton );

        String []dataList0 = mediacenter.getAmigos(username);
        lsList0 = new JList( dataList0 );
        lsList0.setSelectionBackground( new Color( 212,212,212 ) );
        lsList0.setSelectionForeground( new Color( 0,0,0 ) );
        gbcMenuAmigos.gridx = 7;
        gbcMenuAmigos.gridy = 1;
        gbcMenuAmigos.gridwidth = 7;
        gbcMenuAmigos.gridheight = 9;
        gbcMenuAmigos.fill = GridBagConstraints.BOTH;
        gbcMenuAmigos.weightx = 1;
        gbcMenuAmigos.weighty = 1;
        gbcMenuAmigos.anchor = GridBagConstraints.NORTH;
        gbcMenuAmigos.insets = new Insets( 15,15,5,15 );
        gbMenuAmigos.setConstraints( lsList0, gbcMenuAmigos );
        pnMenuAmigos.add( lsList0 );

        btSendButton = new JButton( "Send Request"  );
        gbcMenuAmigos.gridx = 3;
        gbcMenuAmigos.gridy = 7;
        gbcMenuAmigos.gridwidth = 1;
        gbcMenuAmigos.gridheight = 1;
        gbcMenuAmigos.fill = GridBagConstraints.NONE;
        gbcMenuAmigos.weightx = 1;
        gbcMenuAmigos.weighty = 0;
        gbcMenuAmigos.anchor = GridBagConstraints.CENTER;
        gbMenuAmigos.setConstraints( btSendButton, gbcMenuAmigos );
        gbcMenuAmigos.insets = new Insets(0,0,15,0);
        pnMenuAmigos.add( btSendButton );

        btRemoveButton = new JButton( "Remove Friend"  );
        gbcMenuAmigos.gridx = 7;
        gbcMenuAmigos.gridy = 11;
        gbcMenuAmigos.gridwidth = 7;
        gbcMenuAmigos.gridheight = 2;
        gbcMenuAmigos.fill = GridBagConstraints.NONE;
        gbcMenuAmigos.weightx = 1;
        gbcMenuAmigos.weighty = 0;
        gbcMenuAmigos.anchor = GridBagConstraints.CENTER;
        gbcMenuAmigos.insets = new Insets( 0,0,15,3 );
        gbMenuAmigos.setConstraints( btRemoveButton, gbcMenuAmigos );
        pnMenuAmigos.add( btRemoveButton );

        tfText0 = new JTextField( );
        gbcMenuAmigos.gridx = 1;
        gbcMenuAmigos.gridy = 7;
        gbcMenuAmigos.gridwidth = 1;
        gbcMenuAmigos.gridheight = 1;
        gbcMenuAmigos.fill = GridBagConstraints.HORIZONTAL;
        gbcMenuAmigos.weightx = 1;
        gbcMenuAmigos.weighty = 0;
        gbcMenuAmigos.anchor = GridBagConstraints.CENTER;
        gbcMenuAmigos.insets = new Insets( 0,15,0,0 );
        gbMenuAmigos.setConstraints( tfText0, gbcMenuAmigos );
        pnMenuAmigos.add( tfText0 );


        setContentPane( pnMenuAmigos );
        setLocationRelativeTo(null);
        setSize(400, 200);
        setVisible( true );

        btAcceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = cmbCombo0.getSelectedIndex();

                if(index == -1)
                    new MessageDialog("Error", "You must select one friend request!");
                else {
                    String user = (String)cmbCombo0.getSelectedValue();

                    mediacenter.respondePedido(username, user, true);

                    new MessageDialog("Accepted", user + "'s friend request has been accepted!");

                    //((DefaultTableModel) (cmbCombo0.getModel())).removeRow(index);

                    //((DefaultTableModel) (cmbCombo0.getModel())).addRow(new Object[]{user});
                }
            }
        });

        btRefuseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = cmbCombo0.getSelectedIndex();

                if(index == -1)
                    new MessageDialog("Error", "You must select one friend request!");
                else {
                    String user = (String)cmbCombo0.getSelectedValue();

                    mediacenter.respondePedido(username, user, false);

                    new MessageDialog("Remove", user + "'s friend request has been declined!");

                    //((DefaultTableModel)cmbCombo0.getModel()).removeRow(index);
                }
            }
        });

        btSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dest = tfText0.getText();

                if(dest.isBlank())
                    new MessageDialog("Error", "You indicate whom you want to add");
                else {
                    try {
                        mediacenter.formConvite(username, dest);
                        new MessageDialog("Sucess", "A friend request has been made!");
                    } catch (Exception exc) {
                        new MessageDialog("Error", exc.getMessage());
                    }
                }
            }
        });

        btRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lsList0.getSelectedIndex();

                if(index == -1)
                    new MessageDialog("Error", "You must select one friend!");
                else {
                    String user = (String)lsList0.getSelectedValue();

                    mediacenter.removerAmigo(username, user);

                    new MessageDialog("Remove", user + "'s friendship with you has been terminated!");

                    //((DefaultTableModel)cmbCombo0.getModel()).removeRow(index);
                }
            }
        });
    }
}
