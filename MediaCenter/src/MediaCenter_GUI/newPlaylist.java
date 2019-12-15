package MediaCenter_GUI;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
/**
 * @author  Administrator
 * @created December 12, 2019
 */
public class newPlaylist extends JFrame
{
    static newPlaylist thenewPlaylist;

    JPanel pnnewPlaylist;
    JTextField tfNome;
    JTextArea taDescricao;
    JComboBox cmbCombo;
    JButton btCancel;
    JButton btConfirm;
    JTextField tfText1;

    public newPlaylist()
    {
        super( "TITLE" );

        pnnewPlaylist = new JPanel();
        GridBagLayout gbnewPlaylist = new GridBagLayout();
        GridBagConstraints gbcnewPlaylist = new GridBagConstraints();
        pnnewPlaylist.setLayout( gbnewPlaylist );

        tfNome = new JTextField( );
        gbcnewPlaylist.gridx = 2;
        gbcnewPlaylist.gridy = 1;
        gbcnewPlaylist.gridwidth = 14;
        gbcnewPlaylist.gridheight = 3;
        gbcnewPlaylist.fill = GridBagConstraints.HORIZONTAL;
        gbcnewPlaylist.weightx = 1;
        gbcnewPlaylist.weighty = 0;
        gbcnewPlaylist.anchor = GridBagConstraints.CENTER;
        gbcnewPlaylist.insets = new Insets( 15,15,0,15 );
        gbnewPlaylist.setConstraints( tfNome, gbcnewPlaylist );
        pnnewPlaylist.add( tfNome );

        taDescricao = new JTextArea(2,10);
        gbcnewPlaylist.gridx = 2;
        gbcnewPlaylist.gridy = 5;
        gbcnewPlaylist.gridwidth = 14;
        gbcnewPlaylist.gridheight = 6;
        gbcnewPlaylist.fill = GridBagConstraints.BOTH;
        gbcnewPlaylist.weightx = 1;
        gbcnewPlaylist.weighty = 1;
        gbcnewPlaylist.anchor = GridBagConstraints.NORTH;
        gbcnewPlaylist.insets = new Insets( 15,15,15,15 );
        gbnewPlaylist.setConstraints( taDescricao, gbcnewPlaylist );
        pnnewPlaylist.add( taDescricao );

        String []dataCombo = { "Chocolate", "Ice Cream", "Apple Pie" };
        cmbCombo = new JComboBox( dataCombo );
        gbcnewPlaylist.gridx = 2;
        gbcnewPlaylist.gridy = 12;
        gbcnewPlaylist.gridwidth = 14;
        gbcnewPlaylist.gridheight = 3;
        gbcnewPlaylist.fill = GridBagConstraints.BOTH;
        gbcnewPlaylist.weightx = 1;
        gbcnewPlaylist.weighty = 0;
        gbcnewPlaylist.anchor = GridBagConstraints.NORTH;
        gbcnewPlaylist.insets = new Insets( 0,15,0,15 );
        gbnewPlaylist.setConstraints( cmbCombo, gbcnewPlaylist );
        pnnewPlaylist.add( cmbCombo );

        btCancel = new JButton( "Cancel"  );
        gbcnewPlaylist.gridx = 2;
        gbcnewPlaylist.gridy = 16;
        gbcnewPlaylist.gridwidth = 6;
        gbcnewPlaylist.gridheight = 3;
        gbcnewPlaylist.fill = GridBagConstraints.NONE;
        gbcnewPlaylist.weightx = 0;
        gbcnewPlaylist.weighty = 0;
        gbcnewPlaylist.anchor = GridBagConstraints.WEST;
        gbcnewPlaylist.insets = new Insets( 0,15,15,0 );
        gbnewPlaylist.setConstraints( btCancel, gbcnewPlaylist );
        pnnewPlaylist.add( btCancel );

        btConfirm = new JButton( "Confirm"  );
        gbcnewPlaylist.gridx = 10;
        gbcnewPlaylist.gridy = 16;
        gbcnewPlaylist.gridwidth = 6;
        gbcnewPlaylist.gridheight = 3;
        gbcnewPlaylist.fill = GridBagConstraints.NONE;
        gbcnewPlaylist.weightx = 0;
        gbcnewPlaylist.weighty = 0;
        gbcnewPlaylist.anchor = GridBagConstraints.EAST;
        gbcnewPlaylist.insets = new Insets( 0,0,15,15 );
        gbnewPlaylist.setConstraints( btConfirm, gbcnewPlaylist );
        pnnewPlaylist.add( btConfirm );

        tfText1 = new JTextField( );
        gbcnewPlaylist.gridx = 2;
        gbcnewPlaylist.gridy = 15;
        gbcnewPlaylist.gridwidth = 14;
        gbcnewPlaylist.gridheight = 1;
        gbcnewPlaylist.fill = GridBagConstraints.BOTH;
        gbcnewPlaylist.weightx = 1;
        gbcnewPlaylist.weighty = 0;
        gbcnewPlaylist.anchor = GridBagConstraints.CENTER;
        gbcnewPlaylist.insets = new Insets( 15,15,15,15 );
        gbnewPlaylist.setConstraints( tfText1, gbcnewPlaylist );
        pnnewPlaylist.add( tfText1 );

        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( pnnewPlaylist );
        pack();
        setLocationRelativeTo(null);
        setVisible( true );
    }
}