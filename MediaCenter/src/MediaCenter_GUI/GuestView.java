package MediaCenter_GUI;

import Client.MediaCenterInterface;
import MediaPlayer.MediaPlayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

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
    private boolean playing;
    private boolean shuffle;

    public void CloseFrame() {
        super.dispose();
    }

    public GuestView(MediaCenterInterface mediacenter) {
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

        String[] columnNames = new String[]{"Name", "Author", "Duration","Options"};
        data = mediacenter.getListaMusicas();

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            public boolean isCellEditable(int row, int column){
                return column >= 3;
            }
        };
        tbContentTable = new JTable(data, columnNames);
        tbContentTable.setModel(model);

        // Inicializar o MediaPlayer
        MediaPlayer mp = new MediaPlayer();

        final JPopupMenu uselesspopupmenu = new JPopupMenu("Useless");

        Action addToQueue = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("> Add file "+data[tbContentTable.getSelectedRow()][5]+" to Queue");
                String filename = mediacenter.downloadForReproduction(Integer.parseInt(data[tbContentTable.getSelectedRow()][5]));
                mp.addToQueue(System.getProperty("user.dir")+"/src/Client/.reproduction/"+filename);
                //System.out.println(filename);
            }
        };

        ButtonColumn buttonColumnDown = new ButtonColumn(tbContentTable, addToQueue, 3, uselesspopupmenu);
        buttonColumnDown.setMnemonic(KeyEvent.VK_D);

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

        btShuffle = new JButton("shuffle \u25CB");
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
        shuffle = false;


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


        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selectedItem = (String) lsCategorias.getSelectedValue();
                    data = mediacenter.getListaMusicas(selectedItem);

                    DefaultTableModel tableModel = (DefaultTableModel) tbContentTable.getModel();
                    int rowCount = tableModel.getRowCount();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        tableModel.removeRow(i);
                    }
                    for (int i = 0; i < data.length; i++) {
                        tableModel.addRow(data[i]);
                    }

                    tbContentTable.setModel(tableModel);
                    tableModel.fireTableDataChanged();
                }
            }
        };

        lsCategorias.addMouseListener(mouseListener);


        btLogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mediacenter.logout();
                mp.exit();
                CloseFrame();
            }
        });

        btPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    if (!playing){
                        btPlay.setText("\u007C\u007C");
                        mp.play();
                        playing=true;
                    }else {
                        btPlay.setText("\u25B6");
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

        btShuffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shuffle) {
                    btShuffle.setText("shuffle \u25CB");
                    mp.shuffleOFF();
                    shuffle = false;
                } else {
                    btShuffle.setText("shuffle \u25CF");
                    mp.shuffleON();
                    shuffle = true;
                }
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
