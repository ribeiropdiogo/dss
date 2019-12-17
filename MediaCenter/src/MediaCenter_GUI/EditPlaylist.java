package MediaCenter_GUI;

import Client.MediaCenterInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPlaylist extends JFrame{

    JPanel pnPanel0;
    JTextArea lbPlaylistName;
    JTextArea taDescription;
    JTable tbTable0;
    JButton btConfirmButton;
    JButton btAddButton;
    JButton btRemoveButton;
    JButton btAlbum;
    JButton btRemovePlay;

    public void closeWindow() {
        super.dispose();
    }

    public EditPlaylist(MediaCenterInterface mediacenter, String username, int idPlaylist)
    {
        super( "Edit Playlist" );

        pnPanel0 = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        lbPlaylistName = new JTextArea( "New Name"  );
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

        taDescription = new JTextArea("New Description", 2,10);
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 19;
        gbcPanel0.gridheight = 5;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,15,15,15 );
        gbPanel0.setConstraints( taDescription, gbcPanel0 );
        pnPanel0.add( taDescription );

        String [][]dataTable0 = mediacenter.getAllConteudoBasic(idPlaylist);
        String []colsTable0 = new String[] { "Name", "Author" };
        DefaultTableModel model = new DefaultTableModel(dataTable0, colsTable0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tbTable0 = new JTable( dataTable0, colsTable0 );
        tbTable0.setModel(model);
        tbTable0.setAutoCreateRowSorter(true);
        tbTable0.setAutoscrolls(false);
        tbTable0.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbTable0.setEnabled(true);
        tbTable0.setName("");
        JScrollPane scpContentTable = new JScrollPane(tbTable0);
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
        gbcPanel0.gridy = 25;
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

        btRemoveButton = new JButton( "Remover Conteudo"  );
        gbcPanel0.gridx = 16;
        gbcPanel0.gridy = 14;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btRemoveButton, gbcPanel0 );
        pnPanel0.add( btRemoveButton );

        btAlbum = new JButton( "Adicionar Album"  );
        gbcPanel0.gridx = 16;
        gbcPanel0.gridy = 17;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btAlbum, gbcPanel0 );
        pnPanel0.add( btAlbum );

        btRemovePlay = new JButton( "Remover Playlist"  );
        gbcPanel0.gridx = 16;
        gbcPanel0.gridy = 20;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btRemovePlay, gbcPanel0 );
        pnPanel0.add( btRemovePlay );


        setContentPane( pnPanel0 );
        pack();
        setLocationRelativeTo(null);
        setSize(500, 250);
        setResizable(false);
        setVisible( true );

        btAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addAlbumForm(mediacenter, idPlaylist);
            }
        });

        btAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addContentForm(mediacenter, idPlaylist);
            }
        });

        btRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tbTable0.getSelectedRow();
                if(index == -1)
                    new MessageDialog("Error", "You must select one content to remove");
                else {
                    mediacenter.removeContent(idPlaylist, Integer.parseInt(dataTable0[index][2]));
                    new MessageDialog("Success", "Content removed successfully!");
                }
            }
        });

        btConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = lbPlaylistName.getText();
                String desc = taDescription.getText();

                if(!name.isBlank() && !name.equals("New Name")) {
                    mediacenter.editPName(idPlaylist, name);
                }

                if(!desc.isBlank() && !desc.equals("New Description")) {
                    mediacenter.editPDesc(idPlaylist, desc);
                }

                closeWindow();
            }
        });

        btRemovePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediacenter.removePlaylist(username, idPlaylist);
                new MessageDialog("Success", "Your playlist has been deleted with success");
                closeWindow();
            }
        });

    }
}
