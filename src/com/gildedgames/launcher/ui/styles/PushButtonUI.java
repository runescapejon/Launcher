/*    */ package com.gildedgames.launcher.ui.styles;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JComponent;
/*    */ 
/*    */ public class PushButtonUI extends javax.swing.plaf.basic.BasicButtonUI
/*    */ {
/* 13 */   private static final Color UNSELECTED_HOVER_FILL = new Color(6710886);
/* 14 */   private static final Color UNSELECTED_FILL = new Color(5592405);
/*    */   
/* 16 */   private static final Color SELECTED_HOVER_FILL = new Color(660616);
/* 17 */   private static final Color SELECTED_FILL = new Color(687314);
/*    */   
/* 19 */   private static final Color KNOB_FILL = new Color(16777215);
/*    */   
/*    */   private boolean hovered;
/*    */   
/*    */   public void installUI(final JComponent component)
/*    */   {
/* 25 */     super.installUI(component);
/*    */     
/* 27 */     component.setOpaque(false);
/* 28 */     component.addMouseListener(new java.awt.event.MouseAdapter()
/*    */     {
/*    */       public void mouseEntered(MouseEvent e) {
/* 31 */         PushButtonUI.this.hovered = true;
/*    */         
/* 33 */         component.repaint();
/*    */       }
/*    */       
/*    */       public void mouseExited(MouseEvent e)
/*    */       {
/* 38 */         PushButtonUI.this.hovered = false;
/*    */         
/* 40 */         component.repaint();
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public void paint(Graphics g, JComponent c)
/*    */   {
/* 47 */     JCheckBox checkbox = (JCheckBox)c;
/*    */     
/* 49 */     Graphics2D g2 = (Graphics2D)g;
/*    */     
/* 51 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 52 */     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*    */     
/* 54 */     int y = c.getHeight() / 2 - 11;
/*    */     
/* 56 */     java.awt.geom.RoundRectangle2D track = new java.awt.geom.RoundRectangle2D.Double(2.0D, y + 2, 42.0D, 18.0D, 18.0D, 18.0D);
/*    */     
/* 58 */     if (checkbox.isSelected()) {
/* 59 */       g2.setColor(this.hovered ? SELECTED_HOVER_FILL : SELECTED_FILL);
/* 60 */       g2.fill(track);
/*    */     } else {
/* 62 */       g2.setColor(this.hovered ? UNSELECTED_HOVER_FILL : UNSELECTED_FILL);
/* 63 */       g2.fill(track);
/*    */     }
/*    */     
/* 66 */     java.awt.geom.Ellipse2D knob = new java.awt.geom.Ellipse2D.Double(checkbox.isSelected() ? 30.0D : 6.0D, y + 6, 10.0D, 10.0D);
/*    */     
/* 68 */     g2.setColor(KNOB_FILL);
/* 69 */     g2.fill(knob);
/*    */     
/* 71 */     g2.setColor(c.getForeground());
/* 72 */     g2.setFont(c.getFont());
/* 73 */     g2.drawString(((JCheckBox)c).getText(), 55, y + 16);
/*    */   }
/*    */   
/*    */   public java.awt.Dimension getPreferredSize(JComponent c)
/*    */   {
/* 78 */     JCheckBox checkbox = (JCheckBox)c;
/*    */     
/* 80 */     int height = 22;
/* 81 */     int width = 55 + c.getFontMetrics(c.getFont()).stringWidth(checkbox.getText()) + 2;
/*    */     
/* 83 */     return new java.awt.Dimension(width, height);
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\styles\PushButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */