package hunt.hunt;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class which represents the window object.
 * @author Mikolaj Marmurowicz
 */
public class window extends javax.swing.JFrame {
    private final Manager manager;
    
    /**
     * Method that allows JPanel containing the talking jigsaw to be shared bewteen classes.
     * @return PhotoPanel1 JPanel containing the talking jigsaw
     */
    public JPanel getPhotoPanel() {
        return PhotoPanel1;
    } 
    
    /**
     * Creates new form window and covers some of its parts with texture.
     * It also creates the manager that will be used for the whole program.
     */
    public window() { 
        initComponents();
        BufferedImage png = null;
        try {
            png = ImageIO.read(new File(".\\pngs\\infoPane.png"));
        } catch (IOException e) {
            
        }
        JLabel pic = new JLabel(new ImageIcon(png));
        pic.setVisible(true);
        pic.setSize(258,150);
        InfoPanel.add(pic);
        InfoPanel.setSize(258, 150);
        InfoPanel.setVisible(true);
        
        BufferedImage png2 = null;
        try {
            png2 = ImageIO.read(new File(".\\pngs\\background.png"));
        } catch (IOException e) {
            
        }
        JLabel pic2 = new JLabel(new ImageIcon(png2));
        pic2.setVisible(true);
        pic2.setSize(960, 540);
        Background.add(pic2);
        Background.setSize(960, 540);
        Background.setVisible(true);
        jTextArea1.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
        
        PhotoPanel1.setVisible(false);
        this.remove.setVisible(false);
        this.drink.setVisible(false);
        this.hide.setVisible(false);
        this.eat.setVisible(false);
        
        this.manager = new Manager(Map, PredatorNum, PreyNum, remove, drink, eat, hide);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        InfoPanel = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        MenuPanel = new javax.swing.JPanel(){
            Image img = new ImageIcon(".\\pngs\\menuPanel.png").getImage();
            protected void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0,414,384,this);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        PredatorNum = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PreyNum = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        drink = new javax.swing.JButton();
        hide = new javax.swing.JButton();
        eat = new javax.swing.JButton();
        PhotoPanel = new javax.swing.JPanel() {
            Image image = Toolkit.getDefaultToolkit().createImage(".\\pngs\\normal.gif");
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0,150,150,this);
            }
        };
        PhotoPanel1 = new javax.swing.JPanel() {
            Image image = Toolkit.getDefaultToolkit().createImage(".\\pngs\\talking.gif");
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0,150,150,this);
            }
        };
        Map = new javax.swing.JPanel(){
            Image img = new ImageIcon(".\\pngs\\map.png").getImage();
            protected void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0,540,540,this);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hunt or be Hunted");
        setBackground(new java.awt.Color(255, 0, 0));
        setBounds(new java.awt.Rectangle(0, 0, 960, 540));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.red);
        setMinimumSize(new java.awt.Dimension(540, 270));
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));

        Background.setMaximumSize(new java.awt.Dimension(960, 540));
        Background.setMinimumSize(new java.awt.Dimension(960, 540));

        InfoPanel.setBackground(new java.awt.Color(255, 0, 0));
        InfoPanel.setMinimumSize(new java.awt.Dimension(40, 30));
        InfoPanel.setPreferredSize(new java.awt.Dimension(258, 150));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(153, 0, 0));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setOpaque(false);

        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        InfoPanelLayout.setVerticalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jTextArea1.getAccessibleContext().setAccessibleParent(InfoPanel);

        MenuPanel.setBackground(new java.awt.Color(102, 102, 255));
        MenuPanel.setForeground(new java.awt.Color(0, 102, 0));
        MenuPanel.setMinimumSize(new java.awt.Dimension(163, 97));
        MenuPanel.setPreferredSize(new java.awt.Dimension(414, 384));
        MenuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Predators:");
        MenuPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 87, -1, -1));

        jButton1.setText("Add Predators");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        MenuPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 126, -1, 40));

        PredatorNum.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        PredatorNum.setForeground(new java.awt.Color(255, 255, 255));
        MenuPanel.add(PredatorNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 87, 37, 33));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Prey:");
        MenuPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 87, -1, -1));

        PreyNum.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        PreyNum.setForeground(new java.awt.Color(255, 255, 255));
        MenuPanel.add(PreyNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 87, 37, 33));

        jButton2.setText("Add Prey");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        MenuPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 126, 106, 40));

        remove.setText("Remove");
        remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeMouseClicked(evt);
            }
        });
        MenuPanel.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 202, 101, 40));

        drink.setText("Go drink");
        drink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drinkMouseClicked(evt);
            }
        });
        MenuPanel.add(drink, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 254, 101, 40));

        hide.setText("Go hide");
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideMouseClicked(evt);
            }
        });
        MenuPanel.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 254, 101, 40));

        eat.setText("Go eat");
        eat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eatMouseClicked(evt);
            }
        });
        MenuPanel.add(eat, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 254, 101, 40));

        PhotoPanel.setBackground(new java.awt.Color(102, 255, 102));
        PhotoPanel.setMinimumSize(new java.awt.Dimension(40, 30));
        PhotoPanel.setPreferredSize(new java.awt.Dimension(150, 150));

        PhotoPanel1.setBackground(new java.awt.Color(102, 255, 102));
        PhotoPanel1.setMinimumSize(new java.awt.Dimension(40, 30));
        PhotoPanel1.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout PhotoPanel1Layout = new javax.swing.GroupLayout(PhotoPanel1);
        PhotoPanel1.setLayout(PhotoPanel1Layout);
        PhotoPanel1Layout.setHorizontalGroup(
            PhotoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        PhotoPanel1Layout.setVerticalGroup(
            PhotoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PhotoPanelLayout = new javax.swing.GroupLayout(PhotoPanel);
        PhotoPanel.setLayout(PhotoPanelLayout);
        PhotoPanelLayout.setHorizontalGroup(
            PhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PhotoPanelLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(PhotoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PhotoPanelLayout.setVerticalGroup(
            PhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PhotoPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PhotoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Map.setBackground(new java.awt.Color(255, 153, 0));
        Map.setOpaque(false);

        javax.swing.GroupLayout MapLayout = new javax.swing.GroupLayout(Map);
        Map.setLayout(MapLayout);
        MapLayout.setHorizontalGroup(
            MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        MapLayout.setVerticalGroup(
            MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(Map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(InfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Map, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.manager.addPredator();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.manager.addPrey();
    }//GEN-LAST:event_jButton2MouseClicked

    private void removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMouseClicked
        this.manager.removeSelected();
    }//GEN-LAST:event_removeMouseClicked

    private void drinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkMouseClicked
        this.manager.changeRoute(5);
    }//GEN-LAST:event_drinkMouseClicked

    private void hideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMouseClicked
        this.manager.changeRoute(4);
    }//GEN-LAST:event_hideMouseClicked

    private void eatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eatMouseClicked
        this.manager.changeRoute(3);
    }//GEN-LAST:event_eatMouseClicked

    /**
     * Method that allows the creation of the whole window.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new window().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JPanel Map;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel PhotoPanel;
    private javax.swing.JPanel PhotoPanel1;
    private javax.swing.JLabel PredatorNum;
    private javax.swing.JLabel PreyNum;
    private javax.swing.JButton drink;
    private javax.swing.JButton eat;
    private javax.swing.JButton hide;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables
}