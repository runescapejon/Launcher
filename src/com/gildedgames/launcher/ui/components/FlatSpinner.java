/*    */ package com.gildedgames.launcher.ui.components;
/*    */ 
/*    */ import com.gildedgames.launcher.ui.animations.LoopingAnimation;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Insets;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Area;
/*    */ 
/*    */ public class FlatSpinner extends javax.swing.JComponent
/*    */ {
/* 12 */   private final LoopingAnimation animation = new LoopingAnimation(this);
/*    */   private int size;
/*    */   
/*    */   public FlatSpinner(int size)
/*    */   {
/* 17 */     setVisible(true);
/* 18 */     setForeground(java.awt.Color.WHITE);
/*    */     
/* 20 */     this.size = size;
/*    */   }
/*    */   
/*    */   public java.awt.Dimension getPreferredSize()
/*    */   {
/* 25 */     Insets insets = getBorder().getBorderInsets(this);
/*    */     
/* 27 */     return new java.awt.Dimension(this.size + insets.left + insets.right, this.size + insets.top + insets.bottom);
/*    */   }
/*    */   
/*    */   public void setVisible(boolean val)
/*    */   {
/* 32 */     if (!val) {
/* 33 */       this.animation.stop();
/*    */     } else {
/* 35 */       this.animation.run(75);
/*    */     }
/*    */     
/* 38 */     super.setVisible(val);
/*    */   }
/*    */   
/*    */   public void paint(Graphics g)
/*    */   {
/* 43 */     Graphics2D g2 = (Graphics2D)g.create();
/*    */     
/* 45 */     g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
/* 46 */     g2.setPaint(getForeground());
/*    */     
/* 48 */     Insets b = getInsets();
/*    */     
/* 50 */     int barRectWidth = getWidth() - b.right - b.left;
/* 51 */     int barRectHeight = getHeight() - b.top - b.bottom;
/*    */     
/* 53 */     if ((barRectWidth <= 0) || (barRectHeight <= 0)) {
/* 54 */       return;
/*    */     }
/*    */     
/* 57 */     double degree = 360.0D - 360.0D * this.animation.getProgress();
/*    */     
/* 59 */     double sz = Math.min(barRectWidth, barRectHeight);
/*    */     
/* 61 */     double cx = b.left + barRectWidth * 0.5D;
/* 62 */     double cy = b.top + barRectHeight * 0.5D;
/* 63 */     double or = sz * 0.5D;
/* 64 */     double ir = or * 0.7D;
/*    */     
/* 66 */     Shape inner = new java.awt.geom.Ellipse2D.Double(cx - ir, cy - ir, ir * 2.0D, ir * 2.0D);
/* 67 */     Shape outer = new java.awt.geom.Arc2D.Double(cx - or, cy - or, sz, sz, degree, 150.0D, 2);
/*    */     
/* 69 */     Area area = new Area(outer);
/* 70 */     area.subtract(new Area(inner));
/*    */     
/* 72 */     g2.fill(area);
/* 73 */     g2.dispose();
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\components\FlatSpinner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */