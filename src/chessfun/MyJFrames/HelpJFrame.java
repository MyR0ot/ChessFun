/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.MyJFrames;

import chessfun.Enums.ModeChess;

/**
 *
 * @author My
 */
public class HelpJFrame extends javax.swing.JFrame {

    private static  ModeChess modeGame;
    
    /**
     * Creates new form HelpJFrame
     */
    public HelpJFrame(ModeChess modeChess) {
        initComponents();
        modeGame = modeChess;
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setLineWrap(true);
        switch(modeChess)
        {
            case CLASSIC : 
                jLabel1.setText("Классический режим");
                jTextArea1.setText(rulesForClassic());
                break;
            case FISHER: 
                jLabel1.setText("Шахматы Фишера");
                jTextArea1.setText(rulesForFisher() + rulesForClassic());
                break;
            case KING_HILL: 
                jLabel1.setText("Царь горы");
                jTextArea1.setText(rulesForKing_Hill());
                break;
            default: System.err.append("Undefined type of game");
        }
    }

    public String rulesForClassic(){
// <editor-fold defaultstate="collapsed" desc="Заполнение правил">
        String str = "    Превращение пешки является частью того хода, которым она перемещается на последнюю горизонталь. Таким образом, если, например, "
                        + "превращённая из пешки фигура угрожает королю противника, то этот король в результате хода пешкой на последнюю горизонталь "
                        + "немедленно оказывается под шахом."
                        + "   Игра заключается в том, что игроки поочерёдно делают ходы. Первый ход делают белые. За исключением взятия на проходе и "
                        + "рокировки, описанных ниже, ход заключается в том, что игрок перемещает одну из своих фигур на другое поле по следующим правилам:"
                        + "\n" + 
                        "--Фигуры, кроме коня, во время хода считаются передвигающимися по прямой линии в плоскости доски, то есть «проходящими» все поля "
                        + "между начальным и конечным, поэтому все эти поля должны быть свободны. Если на пути фигуры находится другая фигура, то "
                        + "переместить фигуру на поле за ней невозможно. Исключением является ход коня.\n" + 
                        "--Ход на поле, занятое своей фигурой, невозможен.\n" +
                        "--При ходе на поле, занятое чужой фигурой, она снимается с доски (взятие).\n" +
                        "--Король ходит на расстояние 1 по вертикали, горизонтали или диагонали.\n" +
                        "--Ферзь ходит на любое расстояние по вертикали, горизонтали или диагонали.\n" +
                        "--Ладья ходит на любое расстояние по вертикали или горизонтали.\n" +
                        "--Слон ходит на любое расстояние по диагонали.\n" +
                        "--Конь ходит на поле, находящееся на расстоянии 2 по вертикали и 1 по горизонтали или 1 по вертикали и 2 по горизонтали от текущего положения. В отличие от "
                        + "всех остальных шахматных фигур, ход коня делается вне плоскости доски, то есть конь непосредственно перемещается («перепрыгивает»)"
                        + " с начального поля на конечное и никакие фигуры, стоящие на других полях, ходу коня помешать не могут. В частности, конь может "
                        + "ходить на поле, даже если оно полностью окружено своими или чужими фигурами.\n" +
                        "--Пешка ходит со взятием по диагонали на одно поле вперёд-вправо или вперёд-влево, а без взятия — по вертикали на одно поле вперёд. Если пешка в данной "
                        + "партии ещё не делала ходов, она может сделать ход без взятия на два поля вперёд. Направлением «вперёд» называется направление "
                        + "к восьмой горизонтали для белых или к первой для чёрных. Когда пешка ходит на последнюю горизонталь (для белых — на восьмую, "
                        + "для чёрных — на первую), ходящий должен заменить её на любую другую фигуру того же цвета, кроме короля (превращение пешки). "
                        + "\n" + "\n" +
                        "   Кроме того, есть два специальных хода: " + "\n" + 
                        "--Рокировка — если король и одна из ладей того же цвета не двигались с начала игры, то король и эта ладья могут в один ход "
                        + "одновременно сменить положение (рокироваться). При рокировке король сдвигается на 2 клетки по направлению к ладье, а ладья "
                        + "ставится на поле между начальной и конечной позицией короля. Рокировка невозможна, если король или соответствующая ладья уже "
                        + "ходили. Рокировка временно невозможна, если поле, на котором стоит король, или поле, которое он должен пересечь, или поле, "
                        + "которое он должен занять, находится под ударом одной из фигур противника, или если между рокируемыми королём и ладьёй находится "
                        + "какая-либо фигура. Рокировка считается ходом короля, а не ладьи, поэтому рокировку следует начинать с перестановки короля, а не "
                        + "ладьи." + "\n" + 
                        "--Взятие на проходе — когда пешка совершает ход на две клетки через битое поле, находящееся под ударом пешки противника, то "
                        + "ответным ходом она может быть взята этой пешкой противника. При этом пешка противника перемещается на битое поле, а сбитая пешка "
                        + "снимается с доски (пример см. на диаграмме). Взятие на проходе возможно только непосредственно в ответ на ход пешки через битое "
                        + "поле, на следующих ходах оно уже не разрешено." + "\n" + "\n" +
                        "   Шах, мат и пат" + "\n" +
                        "--Король, находящийся на битом поле, называется «стоящим под шахом». Сделать ход, после которого король противника оказывается под "
                        + "шахом, значит «объявить шах». Ходы, после которых король сделавшего ход остаётся или оказывается под шахом, запрещены; игрок, "
                        + "король которого находится под шахом, обязан немедленно его устранить." + "\n" +
                        "--Если король игрока находится под шахом и игрок не имеет ни одного хода, позволяющего устранить этот шах, этот игрок называется "
                        + "«получившим мат» и, соответственно, он терпит поражение. Цель игры и состоит в том, чтобы поставить"
                        + " мат королю противника."+ "\n" +
                        "--Если игрок при своей очереди хода не имеет возможности сделать ни одного хода по правилам, но король игрока не находится "
                        + "под шахом, такая ситуация называется пат.";
// </editor-fold> 
        return str;
    }
    
    public String rulesForFisher(){
// <editor-fold defaultstate="collapsed" desc="Заполнение правил">
        String str = "  Поле, фигуры, ходы" + 
                "В шахматы Фишера играют на обычной 64‑клеточной шахматной доске, стандартным набором фигур, ходящих по обычным правилам "
                + "классических шахмат, за исключением расстановки фигур.\n";
// </editor-fold>
        return str;
    }
    
        public String rulesForKing_Hill(){
// <editor-fold defaultstate="collapsed" desc="Заполнение правил">
        String str = "  Царь горы - увлекательный вариант шахмат, где цель игры - привести короля в центр доски, так сказать, на \"вершину горы\"." + "\n" +
                "--Партия заканчивается, когда игрок ставит своего короля на одно из полей в центре доски." + "\n" +
                "--Партия может закончиться и обычным матом, патом или просрочкой времени." + "\n" + 
                "--Поля e4, d4, e5 и d5 - \"вершина горы\". Когда белые или черные ставят на одно из них своего короля, партия заканчивается "
                + "независимо от того, у кого лучше позиция.";
// </editor-fold>
        return str;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("jLabel1");

        jButton1.setText("Ок");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HelpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HelpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HelpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HelpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new HelpJFrame(modeGame).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
