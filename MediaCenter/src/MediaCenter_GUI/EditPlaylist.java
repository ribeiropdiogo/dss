package MediaCenter_GUI;

import Client.MediaCenterInterface;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;

public class EditPlaylist extends JFrame{

    JPanel pnPanel0;
    JLabel lbLabel0;
    JList lsList0;
    JButton btBut0;
    JButton btBut1;
    JButton btBut2;
    public EditPlaylist(MediaCenterInterface mediacenter, String username)
    {
        super( "Edit Playlist" );

        pnPanel0 = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        lbLabel0 = new JLabel( "Categorias"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 11;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 0,15,0,0 );
        gbPanel0.setConstraints( lbLabel0, gbcPanel0 );
        pnPanel0.add( lbLabel0 );

        String []dataList0 = { "Chocolate", "Ice Cream", "Apple Pie" };
        lsList0 = new JList( dataList0 );
        lsList0.setSelectionBackground( new Color( 212,212,212 ) );
        lsList0.setSelectionForeground( new Color( 0,0,0 ) );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 11;
        gbcPanel0.gridheight = 7;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 0,15,15,15 );
        gbPanel0.setConstraints( lsList0, gbcPanel0 );
        pnPanel0.add( lsList0 );

        btBut0 = new JButton( "Alterar"  );
        gbcPanel0.gridx = 14;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btBut0, gbcPanel0 );
        pnPanel0.add( btBut0 );

        btBut1 = new JButton( "Adicionar"  );
        gbcPanel0.gridx = 14;
        gbcPanel0.gridy = 7;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btBut1, gbcPanel0 );
        pnPanel0.add( btBut1 );

        btBut2 = new JButton( "Remover"  );
        gbcPanel0.gridx = 14;
        gbcPanel0.gridy = 8;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btBut2, gbcPanel0 );
        pnPanel0.add( btBut2 );

        setContentPane( pnPanel0 );
        pack();
        setLocationRelativeTo(null);
        setSize(300, 220);
        setResizable(false);
        setVisible( true );
    }
}
