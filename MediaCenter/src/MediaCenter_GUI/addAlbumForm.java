package MediaCenter_GUI;

import Client.MediaCenterInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class addAlbumForm extends JFrame {

    static addAlbumForm theaaa;

    JPanel pnMainview;
    JSplitPane sppSplitPane1;

    JPanel pnLeftPanel;
    JLabel lbUsernameLabel;
    JList lsCategorias;
    JButton btLogoutButton;
    JLabel lbCategoriasLabel;


    JPanel pnRightPanel;
    JTable tbContentTable;
    private String[][] data;
    JPanel pnPanelReproducao;
    JButton btPrev;
    JButton btPlay;
    JButton btNext;
    JButton btShuffle;


    JPanel pnHead;

    public void CloseFrame() {
        super.dispose();
    }

    public addAlbumForm(MediaCenterInterface mediacenter, int idPlaylist) {
        super("Add Album");

        //Painel Master
        pnMainview = new JPanel();
        GridBagLayout gbMainview = new GridBagLayout();
        GridBagConstraints gbcMainview = new GridBagConstraints();
        pnMainview.setLayout(gbMainview);

        //Painel da Direita
        pnRightPanel = new JPanel();
        GridBagLayout gbRightPanel = new GridBagLayout();
        GridBagConstraints gbcRightPanel = new GridBagConstraints();
        pnRightPanel.setLayout(gbRightPanel);

        String[] columnNames = new String[]{"ID", "Name"};
        data = mediacenter.getAllAlbuns();

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            public boolean isCellEditable(int row, int column){
                return column >= 2;
            }
        };
        tbContentTable = new JTable(data, columnNames);
        tbContentTable.setModel(model);

        final JPopupMenu uselesspopupmenu = new JPopupMenu("Useless");

        tbContentTable.setAutoCreateRowSorter(true);
        tbContentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbContentTable.setAutoscrolls(false);
        //tbContentTable.setEnabled( false );
        tbContentTable.setName("");
        JScrollPane scpContentTable = new JScrollPane(tbContentTable);
        gbcRightPanel.gridx = 0;
        gbcRightPanel.gridy = 0;
        gbcRightPanel.gridwidth = 1;
        gbcRightPanel.gridheight = 2;
        gbcRightPanel.fill = GridBagConstraints.BOTH;
        gbcRightPanel.weightx = 1;
        gbcRightPanel.weighty = 1;
        gbcRightPanel.anchor = GridBagConstraints.CENTER;
        gbRightPanel.setConstraints(scpContentTable, gbcRightPanel);
        pnRightPanel.add(scpContentTable);

        // Painel de Reprodução
        pnPanelReproducao = new JPanel();
        GridBagLayout gbPanelReproducao = new GridBagLayout();
        GridBagConstraints gbcPanelReproducao = new GridBagConstraints();
        pnPanelReproducao.setLayout(gbPanelReproducao);
        gbcRightPanel.gridx = 0;
        gbcRightPanel.gridy = 2;
        gbcRightPanel.gridwidth = 1;
        gbcRightPanel.gridheight = 1;
        gbcRightPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcRightPanel.weightx = 0;
        gbcRightPanel.weighty = 0;
        gbcRightPanel.anchor = GridBagConstraints.SOUTH;
        gbRightPanel.setConstraints(pnPanelReproducao, gbcRightPanel);
        pnRightPanel.add(pnPanelReproducao);

        //Botões do Painel de Reprodução
        btNext = new JButton("Add");
        gbcPanelReproducao.gridx = 3;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 0;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.CENTER;
        gbPanelReproducao.setConstraints(btNext, gbcPanelReproducao);
        pnPanelReproducao.add(btNext);


        btLogoutButton = new JButton("Cancel");
        gbcPanelReproducao.gridx = 0;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 1;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.WEST;
        gbPanelReproducao.setConstraints(btLogoutButton, gbcPanelReproducao);
        pnPanelReproducao.add(btLogoutButton);

        pnMainview.add(pnRightPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(pnMainview);
        setSize(900, 666);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btLogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CloseFrame();
            }
        });


        btNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = tbContentTable.getSelectedRow();
                if(index == -1) {
                    new MessageDialog("Error!", "You must select one album to add!");
                } else {
                    mediacenter.addAlbum(idPlaylist, Integer.parseInt((String)tbContentTable.getValueAt(index, 0)));
                    new MessageDialog("Success", "The album has sucessfully been added to your playlist!");
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
    }



}
