package MediaCenter_GUI;

import Client.MediaCenterInterface;
import MediaPlayer.MediaPlayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame {
    static MainView theaaa;

    JPanel pnMainview;
    JSplitPane sppSplitPane1;

    JPanel pnLeftPanel;
    JLabel lbUsernameLabel;
    JList lsCategorias;
    JButton btLogoutButton;
    JLabel lbCategoriasLabel;
    JLabel lbPlaylistsLabel;
    JList lsPlaylists;
    JLabel lbAmigosLabel;
    JList lsAmigos;


    JButton btAmigosButton;
    JButton btNewPlaylistButton;


    JPanel pnRightPanel;
    JTable tbContentTable;
    JPanel pnPanelReproducao;
    JButton btPrev;
    JButton btPlay;
    JButton btNext;
    JButton btAddConteudo;
    final JFileChooser fc;
    String[][] dataContentTable;
    JButton btShuffle;


    JPanel pnHead;

    private boolean playing;

    public void CloseFrame() {
        super.dispose();
    }

    public MainView(MediaCenterInterface mediacenter, String username) {
        super("Media Center");

        playing = false;
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

        lbUsernameLabel = new JLabel(username);
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


        // Secção das Playlists
        lbPlaylistsLabel = new JLabel("Playlists");
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 4;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets(5, 5, 0, 0);
        gbLeftPanel.setConstraints(lbPlaylistsLabel, gbcLeftPanel);
        pnLeftPanel.add(lbPlaylistsLabel);
        sppSplitPane1.setLeftComponent(pnLeftPanel);

        String[] dataPlaylists = mediacenter.getCategorias();
        lsPlaylists = new JList(dataPlaylists);
        lsPlaylists.setName("Playlists");
        lsPlaylists.setBackground(new Color(238, 238, 238));
        lsPlaylists.setSelectionBackground(new Color(212, 212, 212));
        lsPlaylists.setVisibleRowCount(7);
        JScrollPane scpPlaylists = new JScrollPane(lsPlaylists);
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 5;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 1;
        gbcLeftPanel.weighty = 1;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets(5, 5, 0, 0);
        gbLeftPanel.setConstraints(scpPlaylists, gbcLeftPanel);
        pnLeftPanel.add(scpPlaylists);


        // Secção dos Amigos
        lbAmigosLabel = new JLabel("Amigos");
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 6;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets(5, 5, 0, 0);
        gbLeftPanel.setConstraints(lbAmigosLabel, gbcLeftPanel);
        pnLeftPanel.add(lbAmigosLabel);
        sppSplitPane1.setLeftComponent(pnLeftPanel);

        String[] dataAmigos = {"Chuck", "Arnold", "Kevinho", "DJ Kaled", "Poney", "A", "Tua", "Prima"};
        lsAmigos = new JList(dataAmigos);
        lsAmigos.setName("Amigos");
        lsAmigos.setBackground(new Color(238, 238, 238));
        lsAmigos.setFocusable(false);
        lsAmigos.setSelectionBackground(new Color(238, 238, 238));
        lsAmigos.setSelectionForeground(new Color(0, 0, 0));
        lsAmigos.setSelectionMode(1);
        lsAmigos.setVisibleRowCount(7);
        JScrollPane scpAmigos = new JScrollPane(lsAmigos);
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 7;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 1;
        gbcLeftPanel.weighty = 1;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets(5, 5, 0, 0);
        gbLeftPanel.setConstraints(scpAmigos, gbcLeftPanel);
        pnLeftPanel.add(scpAmigos);


        btAmigosButton = new JButton("Menu Amigos");
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 8;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.NONE;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.CENTER;
        gbLeftPanel.setConstraints(btAmigosButton, gbcLeftPanel);
        pnLeftPanel.add(btAmigosButton);

        btNewPlaylistButton = new JButton("Nova Playlist");
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 9;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.NONE;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.CENTER;
        gbLeftPanel.setConstraints(btNewPlaylistButton, gbcLeftPanel);
        pnLeftPanel.add(btNewPlaylistButton);
        sppSplitPane1.setLeftComponent(pnLeftPanel);


        //Painel da Direita
        pnRightPanel = new JPanel();
        GridBagLayout gbRightPanel = new GridBagLayout();
        GridBagConstraints gbcRightPanel = new GridBagConstraints();
        pnRightPanel.setLayout(gbRightPanel);

        dataContentTable = mediacenter.getListaMusicas();
        String[] colsContentTable = new String[]{"Name", "Author", "Duration", "Options", ""};
        DefaultTableModel model = new DefaultTableModel(dataContentTable, colsContentTable);
        tbContentTable = new JTable(dataContentTable, colsContentTable);
        tbContentTable.setModel(model);
        tbContentTable.setAutoCreateRowSorter(true);
        tbContentTable.setAutoscrolls(false);
        tbContentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbContentTable.setEnabled(true);
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


        // Inicializar o MediaPlayer
        MediaPlayer mp = new MediaPlayer();

        //--- Coloca butões nas células
        final JPopupMenu popupmenu = new JPopupMenu("Edit");
        JMenuItem download = new JMenuItem("Download");
        JMenuItem changecat = new JMenuItem("Change Category");
        JMenuItem delete = new JMenuItem("Delete");
        popupmenu.add(download); popupmenu.add(changecat); popupmenu.add(delete);

        download.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                MessageDialog md = new MessageDialog("HEY!","HELLO MOTHERFUCKER!");
            }
        });

        changecat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ChangeCategory ct = new ChangeCategory(mediacenter,Integer.parseInt(dataContentTable[tbContentTable.getSelectedRow()][5]));
            }
        });

        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                MessageDialog md = new MessageDialog("HEY!","BITCH ASS NIGGER");
            }
        });

        final JPopupMenu uselesspopupmenu = new JPopupMenu("Useless");

        Action addToQueue = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("> Add file "+dataContentTable[tbContentTable.getSelectedRow()][5]+" to Queue");
                String filename = mediacenter.downloadForReproduction(Integer.parseInt(dataContentTable[tbContentTable.getSelectedRow()][5]));
                mp.addToQueue(System.getProperty("user.dir")+"/src/Client/.reproduction/"+filename);
            }
        };

        Action altCategoria = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("> Alterar categoria do ficheiro "+tbContentTable.getSelectedRow());
            }
        };

        ButtonColumn buttonColumnDown = new ButtonColumn(tbContentTable, addToQueue, 3, uselesspopupmenu);
        buttonColumnDown.setMnemonic(KeyEvent.VK_D);

        ButtonColumn buttonColumnOpt = new ButtonColumn(tbContentTable, altCategoria, 4, popupmenu);
        buttonColumnOpt.setMnemonic(KeyEvent.VK_D);

        //-----------------------------

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

        btAddConteudo = new JButton("add");
        gbcPanelReproducao.gridx = 5;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 1;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.EAST;
        gbPanelReproducao.setConstraints(btAddConteudo, gbcPanelReproducao);
        pnPanelReproducao.add(btAddConteudo);

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
        fc = new JFileChooser();

        setContentPane(pnMainview);
        pack();
        setLocationRelativeTo(null);
        setSize(900, 666);
        setVisible(true);

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selectedItem = (String) lsCategorias.getSelectedValue();
                    dataContentTable = mediacenter.getListaMusicas(selectedItem);

                    DefaultTableModel tableModel = (DefaultTableModel) tbContentTable.getModel();
                    int rowCount = tableModel.getRowCount();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        tableModel.removeRow(i);
                    }
                    for (int i = 0; i < dataContentTable.length; i++) {
                        tableModel.addRow(dataContentTable[i]);
                    }

                    tbContentTable.setModel(tableModel);
                    tableModel.fireTableDataChanged();
                }
            }
        };

        lsCategorias.addMouseListener(mouseListener);

        btLogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mp.pause();
                mediacenter.logout();
                CloseFrame();
            }
        });

        btPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!playing){
                    mp.play();
                    playing=true;
                }else {
                    mp.pause();
                    playing=false;
                }
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

        btAddConteudo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UploadForm uf = new UploadForm(mediacenter, username);
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
