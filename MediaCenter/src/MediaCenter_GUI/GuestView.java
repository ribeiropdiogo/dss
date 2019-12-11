package MediaCenter_GUI;

import Client.MediaCenterInterface;
import MediaPlayer.MediaPlayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuestView extends JFrame {
    static MainView theaaa;

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

    public GuestView(MediaCenterInterface mediacenter) {
        super("Media Center");

        //Painel Master
        pnMainview = new JPanel();
        GridBagLayout gbMainview = new GridBagLayout();
        GridBagConstraints gbcMainview = new GridBagConstraints();
        pnMainview.setLayout(gbMainview);

        //Divisória do Painel
        sppSplitPane1 = new JSplitPane();
        sppSplitPane1.setDividerLocation(138);
        sppSplitPane1.setForeground(new Color(0, 0, 0));
        sppSplitPane1.setLastDividerLocation(211);

        //Painel da Esquerda

        pnLeftPanel = new JPanel();
        GridBagLayout gbLeftPanel = new GridBagLayout();
        GridBagConstraints gbcLeftPanel = new GridBagConstraints();
        pnLeftPanel.setLayout(gbLeftPanel);

        lbUsernameLabel = new JLabel("Guest");
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 0;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.NONE;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets(5, 0, 0, 0);
        gbLeftPanel.setConstraints(lbUsernameLabel, gbcLeftPanel);
        pnLeftPanel.add(lbUsernameLabel);


        btLogoutButton = new JButton("Logout");
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 1;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.NONE;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbLeftPanel.setConstraints(btLogoutButton, gbcLeftPanel);
        pnLeftPanel.add(btLogoutButton);

        // Secção das Categorias
        lbCategoriasLabel = new JLabel("Categorias");
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 2;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets(5, 5, 0, 0);
        gbLeftPanel.setConstraints(lbCategoriasLabel, gbcLeftPanel);
        pnLeftPanel.add(lbCategoriasLabel);
        sppSplitPane1.setLeftComponent(pnLeftPanel);

        String[] dataCategorias = mediacenter.getCategorias();

        lsCategorias = new JList(dataCategorias);
        lsCategorias.setName("Categorias");
        lsCategorias.setBackground(new Color(238, 238, 238));
        lsCategorias.setSelectionBackground(new Color(212, 212, 212));
        lsCategorias.setVisibleRowCount(10);
        JScrollPane scpCategorias = new JScrollPane(lsCategorias);
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 3;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 1;
        gbcLeftPanel.weighty = 1;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets(5, 5, 0, 0);
        gbLeftPanel.setConstraints(scpCategorias, gbcLeftPanel);
        pnLeftPanel.add(scpCategorias);


        //Painel da Direita
        pnRightPanel = new JPanel();
        GridBagLayout gbRightPanel = new GridBagLayout();
        GridBagConstraints gbcRightPanel = new GridBagConstraints();
        pnRightPanel.setLayout(gbRightPanel);

        String[] columnNames = new String[]{"Name", "Owner", "Duration"};
        data = new String[][]{new String[]{"musica1", "owner", "1:11", "1"},
                new String[]{"musica2", "owner", "1:11", "1"},
                new String[]{"musica3", "owner", "1:11",  "1"},
                new String[]{"musica4", "owner", "1:11", "1"},
                new String[]{"musica5", "owner", "1:11", "1"},
                new String[]{"musica6", "owner", "1:11", "1"},
                new String[]{"musica6", "owner", "1:11", "1"},
                new String[]{"musica6", "owner", "1:11", "1"},
                new String[]{"musica6", "owner", "1:11", "1"},
                new String[]{"musica7", "owner", "1:11", "1"}};

        tbContentTable = new JTable() {
            public boolean isCellEditable(int nRow, int nCol) {
                return false;
            }
        };

        DefaultTableModel musicTableModel = (DefaultTableModel) tbContentTable.getModel();
        musicTableModel.setColumnIdentifiers(columnNames);

        DefaultTableModel tableModel = (DefaultTableModel) tbContentTable.getModel();
        for (int i = 0; i < data.length; i++) {
            tableModel.addRow(data[i]);
        }

        tbContentTable.setModel(tableModel);


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
        btNext = new JButton("next");
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

        btPlay = new JButton("\u25B6");
        gbcPanelReproducao.gridx = 2;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 0;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.CENTER;
        gbPanelReproducao.setConstraints(btPlay, gbcPanelReproducao);
        pnPanelReproducao.add(btPlay);

        btPrev = new JButton("prev");
        gbcPanelReproducao.gridx = 1;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 0;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.CENTER;
        gbPanelReproducao.setConstraints(btPrev, gbcPanelReproducao);
        pnPanelReproducao.add(btPrev);

        btShuffle = new JButton("shuffle");
        gbcPanelReproducao.gridx = 0;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 1;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.WEST;
        gbPanelReproducao.setConstraints(btShuffle, gbcPanelReproducao);
        pnPanelReproducao.add(btShuffle);


        sppSplitPane1.setRightComponent(pnRightPanel);
        gbcMainview.gridx = 0;
        gbcMainview.gridy = 0;
        gbcMainview.gridwidth = 37;
        gbcMainview.gridheight = 26;
        gbcMainview.fill = GridBagConstraints.BOTH;
        gbcMainview.weightx = 1;
        gbcMainview.weighty = 1;
        gbcMainview.anchor = GridBagConstraints.CENTER;
        gbMainview.setConstraints(sppSplitPane1, gbcMainview);
        pnMainview.add(sppSplitPane1);


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(pnMainview);
        pack();
        setLocationRelativeTo(null);
        setSize(900, 666);
        setVisible(true);


        MediaPlayer mp = new MediaPlayer();


        btLogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mediacenter.logout();
                mp.exit();
                CloseFrame();
            }
        });

        btPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //tbContentTable.getValueAt(tbContentTable.getSelectedRow(), 3);
                int row = tbContentTable.getSelectedRow();
                System.out.println("Selected row " + row + " - id = " + data[row][3]);
                tableModel.removeRow(row);
                tableModel.fireTableDataChanged();
            }
        });

        btNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mp.nextContent();
            }
        });

        btPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mp.prevContent();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mp.exit();
                super.windowClosing(e);
            }
        });
    }
}
