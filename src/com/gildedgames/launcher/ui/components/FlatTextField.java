/*    */ package com.gildedgames.launcher.ui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class FlatTextField extends JTextField
/*    */ {
/*    */   private final String placeholder;
/*    */   
/*    */   public FlatTextField(String placeholder)
/*    */   {
/* 14 */     setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8));
/* 15 */     setEditable(true);
/*    */     
/* 17 */     this.placeholder = placeholder;
/*    */   }
/*    */   
/*    */   protected void paintComponent(Graphics g)
/*    */   {
/* 22 */     super.paintComponent(g);
/*    */     
/* 24 */     if (getText().length() > 0) {
/* 25 */       return;
/*    */     }
/*    */     
/* 28 */     Graphics2D g2 = (Graphics2D)g;
/*    */     
/* 30 */     g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
/* 31 */     g2.setColor(getDisabledTextColor());
/* 32 */     g2.drawString(this.placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
/*    */   }
/*    */   
/*    */   public void setEditable(boolean isEditable)
/*    */   {
/* 37 */     super.setEditable(isEditable);
/*    */     
/* 39 */     if (isEditable) {
/* 40 */       setBackground(Color.WHITE);
/* 41 */       setForeground(new Color(30, 30, 30));
/*    */     } else {
/* 43 */       setBackground(new Color(140, 140, 140));
/* 44 */       setForeground(new Color(60, 60, 60));
/*    */     }
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\components\FlatTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */