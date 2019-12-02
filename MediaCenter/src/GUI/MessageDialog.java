package GUI;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
/**
 * @author  Administrator
 * @created December 2, 2019
 */
public class MessageDialog extends JFrame
{
    static MessageDialog thedialogmessage;

    JPanel pnPanel0;
    JLabel lbMessage;
    /**
     */
    public static void main( String args[] )
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");
        }
        catch ( ClassNotFoundException e )
        {
        }
        catch ( InstantiationException e )
        {
        }
        catch ( IllegalAccessException e )
        {
        }
        catch ( UnsupportedLookAndFeelException e )
        {
        }
        thedialogmessage = new MessageDialog(args[0],args[1]);
    }

    /**
     */
    public MessageDialog(String title, String message)
    {
        super( title);

        pnPanel0 = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        lbMessage = new JLabel( message);
        gbcPanel0.gridx = 5;
        gbcPanel0.gridy = 5;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 15,15,15,15);
        gbPanel0.setConstraints( lbMessage, gbcPanel0 );
        pnPanel0.add( lbMessage );

        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( pnPanel0 );
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible( true );
    }
}
