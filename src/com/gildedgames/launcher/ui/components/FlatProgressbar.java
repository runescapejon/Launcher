/*    */ package com.gildedgames.launcher.ui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ 
/*    */ public class FlatProgressbar extends javax.swing.JProgressBar
/*    */ {
/*  9 */   private static final Color COLOR_UNFILLED = new Color(119, 119, 119);
/*    */   
/* 11 */   private static final Color COLOR_FILLED = new Color(687314);
/*    */   
/*    */   public void paintComponent(Graphics g)
/*    */   {
/* 15 */     Graphics2D g2 = (Graphics2D)g;
/*    */     
/* 17 */     g2.clearRect(0, 2, getWidth(), getHeight() - 4);
/*    */     
/* 19 */     g2.setColor(COLOR_UNFILLED);
/* 20 */     g2.fillRect(0, 2, getWidth(), getHeight() - 4);
/*    */     
/* 22 */     int fill = (int)Math.floor(getWidth() / getMaximum() * getValue());
/*    */     
/* 24 */     g2.setColor(COLOR_FILLED);
/* 25 */     g2.fillRect(0, 2, fill, getHeight() - 4);
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\components\FlatProgressbar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */