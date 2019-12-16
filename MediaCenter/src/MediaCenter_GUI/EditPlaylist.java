package MediaCenter_GUI;

import Client.MediaCenterInterface;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPlaylist extends JFrame{

    JPanel pnPanel0;
    JLabel lbPlaylistName;
    JTextArea taDescription;
    JTable tbTable0;
    JButton btConfirmButton;
    JButton btAddButton;
    JButton btRemoveButton;
    JButton btAlbum;

    public EditPlaylist(MediaCenterInterface mediacenter, int idPlaylist)
    {
        super( "Edit Playlist" );

        pnPanel0 = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        lbPlaylistName = new JLabel( "Nome da Playlist"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 13;
        gbcPanel0.gridheight = 3;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.WEST;
        gbcPanel0.insets = new Insets( 0,15,0,15 );
        gbPanel0.setConstraints( lbPlaylistName, gbcPanel0 );
        pnPanel0.add( lbPlaylistName );

        taDescription = new JTextArea(2,10);
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 5;
        gbcPanel0.gridwidth = 19;
        gbcPanel0.gridheight = 5;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,15,15,15 );
        gbPanel0.setConstraints( taDescription, gbcPanel0 );
        pnPanel0.add( taDescription );

        String [][]dataTable0 = new String[][] { new String[] {"11", "21"},
                new String[] {"12", "22"},
                new String[] {"13", "23"} };
        String []colsTable0 = new String[] { "", "" };
        tbTable0 = new JTable( dataTable0, colsTable0 );
        tbTable0.setSelectionBackground( new Color( 212,212,212 ) );
        tbTable0.setSelectionForeground( new Color( 0,0,0 ) );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 11;
        gbcPanel0.gridwidth = 13;
        gbcPanel0.gridheight = 10;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 0,15,15,15 );
        gbPanel0.setConstraints( tbTable0, gbcPanel0 );
        pnPanel0.add( tbTable0 );

        btConfirmButton = new JButton( "Confirm"  );
        gbcPanel0.gridx = 16;
        gbcPanel0.gridy = 18;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 3;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.SOUTH;
        gbcPanel0.insets = new Insets( 0,0,15,0 );
        gbPanel0.setConstraints( btConfirmButton, gbcPanel0 );
        pnPanel0.add( btConfirmButton );

        btAddButton = new JButton( "Adicionar Conteudo"  );
        gbcPanel0.gridx = 16;
        gbcPanel0.gridy = 11;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btAddButton, gbcPanel0 );
        pnPanel0.add( btAddButton );

        btAlbum = new JButton( "Adicionar Album"  );
        gbcPanel0.gridx = 16;
        gbcPanel0.gridy = 14;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btAlbum, gbcPanel0 );
        pnPanel0.add( btAlbum );

        btRemoveButton = new JButton( "Remover Conteudo"  );
        gbcPanel0.gridx = 16;
        gbcPanel0.gridy = 17;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btRemoveButton, gbcPanel0 );
        pnPanel0.add( btRemoveButton );

        setContentPane( pnPanel0 );
        pack();
        setLocationRelativeTo(null);
        setSize(350, 250);
        setResizable(false);
        setVisible( true );

        btAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addAlbumForm(mediacenter, idPlaylist);
            }
        });

    }
}
