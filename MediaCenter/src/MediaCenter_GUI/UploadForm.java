package MediaCenter_GUI;

//======================================================
// Source code generated by jvider v1.8.1 UNREGISTERED version.
// http://www.jvider.com/
//======================================================

import Client.MediaCenterInterface;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UploadForm extends JFrame {
    static UploadForm theupload;

    JPanel pnMainPanel;
    JButton btFileButton;
    JLabel lbFileNameLabel;
    JLabel lbValidLabel;
    JTextField tfName;
    JTextField tfArtistas;
    JTextField tfCategorias;
    JTextField tfAlbum;
    JButton btUploadButton;

    File selectedFile;

    public void CloseFrame() {
        super.dispose();
    }

    public UploadForm(MediaCenterInterface mediacenter, String username) {
        super("Upload");

        pnMainPanel = new JPanel();
        GridBagLayout gbMainPanel = new GridBagLayout();
        GridBagConstraints gbcMainPanel = new GridBagConstraints();
        pnMainPanel.setLayout(gbMainPanel);

        btFileButton = new JButton("Select File");
        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 1;
        gbcMainPanel.gridwidth = 1;
        gbcMainPanel.gridheight = 1;
        gbcMainPanel.fill = GridBagConstraints.NONE;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 1;
        gbcMainPanel.anchor = GridBagConstraints.WEST;
        gbMainPanel.setConstraints(btFileButton, gbcMainPanel);
        pnMainPanel.add(btFileButton);

        lbFileNameLabel = new JLabel("");
        gbcMainPanel.gridx = 3;
        gbcMainPanel.gridy = 1;
        gbcMainPanel.gridwidth = 1;
        gbcMainPanel.gridheight = 1;
        gbcMainPanel.fill = GridBagConstraints.NONE;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 1;
        gbcMainPanel.anchor = GridBagConstraints.WEST;
        gbMainPanel.setConstraints(lbFileNameLabel, gbcMainPanel);
        pnMainPanel.add(lbFileNameLabel);

        lbValidLabel = new JLabel();
        gbcMainPanel.gridx = 6;
        gbcMainPanel.gridy = 1;
        gbcMainPanel.gridwidth = 1;
        gbcMainPanel.gridheight = 1;
        gbcMainPanel.fill = GridBagConstraints.NONE;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 1;
        gbcMainPanel.anchor = GridBagConstraints.EAST;
        gbcMainPanel.insets = new Insets(0, 0, 0, 5);
        gbMainPanel.setConstraints(lbValidLabel, gbcMainPanel);
        pnMainPanel.add(lbValidLabel);

        tfName = new JTextField("Nome");
        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 3;
        gbcMainPanel.gridwidth = 6;
        gbcMainPanel.gridheight = 3;
        gbcMainPanel.fill = GridBagConstraints.BOTH;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 0;
        gbcMainPanel.anchor = GridBagConstraints.CENTER;
        gbcMainPanel.insets = new Insets(0, 15, 0, 15);
        gbMainPanel.setConstraints(tfName, gbcMainPanel);
        pnMainPanel.add(tfName);

        tfArtistas = new JTextField("Artista(s)");
        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 7;
        gbcMainPanel.gridwidth = 6;
        gbcMainPanel.gridheight = 3;
        gbcMainPanel.fill = GridBagConstraints.BOTH;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 0;
        gbcMainPanel.anchor = GridBagConstraints.CENTER;
        gbcMainPanel.insets = new Insets(0, 15, 0, 15);
        gbMainPanel.setConstraints(tfArtistas, gbcMainPanel);
        pnMainPanel.add(tfArtistas);

        tfCategorias = new JTextField("Categoria(s)");
        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 11;
        gbcMainPanel.gridwidth = 6;
        gbcMainPanel.gridheight = 3;
        gbcMainPanel.fill = GridBagConstraints.BOTH;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 0;
        gbcMainPanel.anchor = GridBagConstraints.CENTER;
        gbcMainPanel.insets = new Insets(0, 15, 0, 15);
        gbMainPanel.setConstraints(tfCategorias, gbcMainPanel);
        pnMainPanel.add(tfCategorias);

        tfAlbum = new JTextField("Álbum");
        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 15;
        gbcMainPanel.gridwidth = 6;
        gbcMainPanel.gridheight = 3;
        gbcMainPanel.fill = GridBagConstraints.BOTH;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 0;
        gbcMainPanel.anchor = GridBagConstraints.NORTH;
        gbcMainPanel.insets = new Insets(0, 15, 0, 15);
        gbMainPanel.setConstraints(tfAlbum, gbcMainPanel);
        pnMainPanel.add(tfAlbum);

        btUploadButton = new JButton("Upload");
        gbcMainPanel.gridx = 6;
        gbcMainPanel.gridy = 19;
        gbcMainPanel.gridwidth = 1;
        gbcMainPanel.gridheight = 1;
        gbcMainPanel.fill = GridBagConstraints.NONE;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 0;
        gbcMainPanel.anchor = GridBagConstraints.EAST;
        gbcMainPanel.insets = new Insets(0, 0, 0, 15);
        gbMainPanel.setConstraints(btUploadButton, gbcMainPanel);
        pnMainPanel.add(btUploadButton);

        setContentPane(pnMainPanel);
        pack();
        setLocationRelativeTo(null);
        setSize(400, 200);
        setResizable(false);
        setVisible(true);

        btFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileFilter audioFilter = new FileNameExtensionFilter("Music file", "mp3", "mp4", "flac");
                FileFilter videoFilter = new FileNameExtensionFilter("Video file", "webm", "mp4", "flv", "mkv", "gif", "avi", "mpg", "mpeg");
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.addChoosableFileFilter(videoFilter);
                jfc.addChoosableFileFilter(audioFilter);
                jfc.setFileFilter(videoFilter);
                jfc.setFileFilter(audioFilter);
                jfc.setAcceptAllFileFilterUsed(false);

                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = jfc.getSelectedFile();
                    lbFileNameLabel.setText(selectedFile.getName());
                    System.out.println(selectedFile.getAbsolutePath());
                }
            }
        });

        btUploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MessageDialog dialog;
                if (selectedFile != null) {
                    String nome = null;
                    String autor = null;
                    String album = null;
                    String categoria = null;
                    String path = null;

                    if (tfName.getText().equals("Nome") || tfName.getText().equals("")) {
                        nome = selectedFile.getName();
                    } else {
                        nome = tfName.getText();
                    }

                    if (tfArtistas.getText().equals("Artista(s)") || tfArtistas.getText().equals("")) {
                        autor = "undefined";
                    } else {
                        autor = tfArtistas.getText();
                    }

                    if (tfAlbum.getText().equals("Álbum") || tfAlbum.getText().equals("")) {
                        album = "undefined";
                    } else {
                        album = tfAlbum.getText();
                    }

                    if (tfCategorias.getText().equals("Categoria(s)") || tfCategorias.getText().equals("")) {
                        categoria = "undefined";
                    } else {
                        categoria = tfCategorias.getText();
                    }
                    //dialog = new MessageDialog("aaa",nome+", "+autor+", "+album+", "+categoria);
                    mediacenter.uploadFile(username, nome, autor, album, selectedFile);
                    CloseFrame();
                } else {
                    dialog = new MessageDialog("Error", "Please select a file");
                }
            }
        });
    }

}
