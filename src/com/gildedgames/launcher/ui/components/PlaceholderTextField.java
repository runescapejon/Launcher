/*    */ package com.gildedgames.launcher.ui.components;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ 
/*    */ public class PlaceholderTextField extends javax.swing.JTextField
/*    */ {
/*    */   private final String placeholder;
/*    */   
/*    */   public PlaceholderTextField(String placeholder)
/*    */   {
/* 12 */     this.placeholder = placeholder;
/*    */   }
/*    */   
/*    */   protected void paintComponent(Graphics g)
/*    */   {
/* 17 */     super.paintComponent(g);
/*    */     
/* 19 */     if (getText().length() > 0) {
/* 20 */       return;
/*    */     }
/*    */     
/* 23 */     Graphics2D g2 = (Graphics2D)g;
/*    */     
/* 25 */     g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
/* 26 */     g2.setColor(getDisabledTextColor());
/* 27 */     g2.drawString(this.placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\components\PlaceholderTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */