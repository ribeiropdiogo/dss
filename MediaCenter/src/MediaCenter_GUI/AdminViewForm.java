package MediaCenter_GUI;

import Client.MediaCenterInterface;
import MediaPlayer.MediaPlayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AdminViewForm extends JFrame {

    static AdminViewForm theaaa;

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

    public AdminViewForm(MediaCenterInterface mediacenter, String username) {
        super("Admin Panel");

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

        String[] columnNames = new String[]{"Username", "Email", ""};
        data = mediacenter.getAccounts();

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            public boolean isCellEditable(int row, int column){
                return column >= 2;
            }
        };
        tbContentTable = new JTable(data, columnNames);
        tbContentTable.setModel(model);

        // Inicializar o MediaPlayer
        MediaPlayer mp = new MediaPlayer();

        final JPopupMenu uselesspopupmenu = new JPopupMenu("Useless");

        Action removeElem = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int row = tbContentTable.getSelectedRow();
                System.out.println("> Remove user " +  tbContentTable.getValueAt(row,0));
                mediacenter.removeAccount((String)tbContentTable.getValueAt(row,0));
                ((DefaultTableModel)tbContentTable.getModel()).removeRow(row);
            }
        };

        ButtonColumn buttonColumnDown = new ButtonColumn(tbContentTable, removeElem, 2, uselesspopupmenu);
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
        btNext = new JButton("Add");
        gbcPanelReproducao.gridx = 3;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 1;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.EAST;
        gbPanelReproducao.setConstraints(btNext, gbcPanelReproducao);
        pnPanelReproducao.add(btNext);


        btLogoutButton = new JButton("Logout");
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

        lbUsernameLabel = new JLabel(username);
        gbcPanelReproducao.gridx = 10;
        gbcPanelReproducao.gridy = 0;
        gbcPanelReproducao.gridwidth = 1;
        gbcPanelReproducao.gridheight = 1;
        gbcPanelReproducao.fill = GridBagConstraints.NONE;
        gbcPanelReproducao.weightx = 0;
        gbcPanelReproducao.weighty = 0;
        gbcPanelReproducao.anchor = GridBagConstraints.CENTER;
        gbcPanelReproducao.insets = new Insets(5, 0, 0, 0);
        gbPanelReproducao.setConstraints(lbUsernameLabel, gbcPanelReproducao);
        pnPanelReproducao.add(lbUsernameLabel);

        pnMainview.add(pnRightPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(pnMainview);
        setSize(900, 666);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btLogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mediacenter.logout();
                mp.exit();
                CloseFrame();
            }
        });


        btNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUserForm auf = new addUserForm(mediacenter, (DefaultTableModel)tbContentTable.getModel());
            }
        });

        lbUsernameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new editUser(mediacenter, username);
            }
        });
    }


}
