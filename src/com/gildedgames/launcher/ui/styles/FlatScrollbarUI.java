/*     */ package com.gildedgames.launcher.ui.styles;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.RoundRectangle2D.Float;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JScrollBar;
/*     */ 
/*     */ public class FlatScrollbarUI extends javax.swing.plaf.basic.BasicScrollBarUI
/*     */ {
/*     */   private boolean isHovered;
/*     */   private boolean isPressed;
/*     */   private boolean vertical;
/*     */   
/*     */   public FlatScrollbarUI(final JScrollBar bar)
/*     */   {
/*  20 */     this.vertical = (bar.getOrientation() == 1);
/*     */     
/*  22 */     bar.setOpaque(false);
/*     */     
/*  24 */     bar.addMouseListener(new java.awt.event.MouseAdapter()
/*     */     {
/*     */       public void mousePressed(MouseEvent e) {
/*  27 */         FlatScrollbarUI.this.isPressed = true;
/*  28 */         bar.repaint();
/*     */       }
/*     */       
/*     */       public void mouseReleased(MouseEvent e)
/*     */       {
/*  33 */         FlatScrollbarUI.this.isPressed = false;
/*  34 */         bar.repaint();
/*     */       }
/*     */       
/*     */       public void mouseEntered(MouseEvent e)
/*     */       {
/*  39 */         FlatScrollbarUI.this.isHovered = true;
/*  40 */         bar.repaint();
/*     */       }
/*     */       
/*     */       public void mouseExited(MouseEvent e)
/*     */       {
/*  45 */         FlatScrollbarUI.this.isHovered = false;
/*  46 */         bar.repaint();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void installDefaults()
/*     */   {
/*  53 */     super.installDefaults();
/*     */     
/*  55 */     this.scrollBarWidth = 16;
/*     */   }
/*     */   
/*     */   protected void paintTrack(Graphics g, javax.swing.JComponent c, Rectangle trackBounds)
/*     */   {
/*  60 */     g.setColor(new java.awt.Color(255, 255, 255, 60));
/*     */     
/*  62 */     Graphics2D g2 = (Graphics2D)g;
/*  63 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  65 */     if (this.vertical) {
/*  66 */       g2.fill(new RoundRectangle2D.Float(trackBounds.x + 8, trackBounds.y, trackBounds.width - 8, trackBounds.height, 8.0F, 8.0F));
/*     */     } else {
/*  68 */       g2.fill(new RoundRectangle2D.Float(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height - 8, 8.0F, 8.0F));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintThumb(Graphics g, javax.swing.JComponent c, Rectangle thumbBounds)
/*     */   {
/*  74 */     g.setColor((this.isHovered) || (this.isPressed) ? new java.awt.Color(255, 255, 255, 160) : new java.awt.Color(255, 255, 255, 90));
/*     */     
/*  76 */     Graphics2D g2 = (Graphics2D)g;
/*  77 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  79 */     if (this.vertical) {
/*  80 */       g2.fill(new RoundRectangle2D.Float(thumbBounds.x + 8, thumbBounds.y, thumbBounds.width - 8, thumbBounds.height, 8.0F, 8.0F));
/*     */     } else {
/*  82 */       g2.fill(new RoundRectangle2D.Float(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height - 8, 8.0F, 8.0F));
/*     */     }
/*     */   }
/*     */   
/*     */   protected JButton createDecreaseButton(int orientation)
/*     */   {
/*  88 */     JButton b = new JButton();
/*  89 */     b.setVisible(false);
/*  90 */     b.setPreferredSize(new java.awt.Dimension(0, 0));
/*  91 */     b.setMaximumSize(new java.awt.Dimension(0, 0));
/*     */     
/*  93 */     return b;
/*     */   }
/*     */   
/*     */   protected JButton createIncreaseButton(int orientation)
/*     */   {
/*  98 */     JButton b = new JButton();
/*  99 */     b.setVisible(false);
/* 100 */     b.setPreferredSize(new java.awt.Dimension(0, 0));
/* 101 */     b.setMaximumSize(new java.awt.Dimension(0, 0));
/*     */     
/* 103 */     return b;
/*     */   }
/*     */   
/*     */   protected void configureScrollBarColors() {}
/*     */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\styles\FlatScrollbarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */