/*    */ package com.gildedgames.launcher.ui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ 
/*    */ public class FlatPasswordField extends javax.swing.JPasswordField
/*    */ {
/*    */   private final String placeholder;
/*    */   
/*    */   public FlatPasswordField(String placeholder)
/*    */   {
/* 14 */     setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8));
/* 15 */     setBackground(Color.WHITE);
/* 16 */     setForeground(new Color(30, 30, 30));
/* 17 */     setEchoChar('*');
/*    */     
/* 19 */     this.placeholder = placeholder;
/*    */   }
/*    */   
/*    */   protected void paintComponent(Graphics g)
/*    */   {
/* 24 */     super.paintComponent(g);
/*    */     
/* 26 */     if (getPassword().length > 0) {
/* 27 */       return;
/*    */     }
/*    */     
/* 30 */     Graphics2D g2 = (Graphics2D)g;
/*    */     
/* 32 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 33 */     g2.setColor(getDisabledTextColor());
/* 34 */     g2.drawString(this.placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\components\FlatPasswordField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */