/*     */ package com.gildedgames.launcher.ui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ 
/*     */ public class FlatButton extends javax.swing.JButton
/*     */ {
/*     */   private ImageIcon buttonIcon;
/*     */   
/*     */   public static enum ButtonStyle
/*     */   {
/*  16 */     NORMAL(new Color(687314), new Color(660616), new Color(2493330), Color.WHITE), 
/*  17 */     DISABLED(new Color(7829367), new Color(7829367), new Color(7829367), new Color(4473924)), 
/*  18 */     LIGHT(new Color(687314), new Color(660616), new Color(2493330), Color.WHITE), 
/*  19 */     HIGHLIGHTED(new Color(687314), new Color(660616), new Color(2493330), Color.WHITE), 
/*  20 */     TRANSPARENT(null, null, new Color(0, 0, 0, 40), Color.WHITE);
/*     */     
/*     */     private final Color bgNormal;
/*     */     
/*  24 */     public Color getText() { return this.text; }
/*     */     
/*     */     private final Color bgHover;
/*     */     
/*  28 */     private ButtonStyle(Color bgNormal, Color bgHover, Color bgPressed, Color text) { this.bgNormal = bgNormal;
/*  29 */       this.bgHover = bgHover;
/*  30 */       this.bgPressed = bgPressed;
/*  31 */       this.text = text; }
/*     */     
/*     */     private final Color bgPressed;
/*     */     private final Color text;
/*  35 */     public float getTextOpacity(boolean isHovered, boolean isPressed) { if (this == TRANSPARENT) {
/*  36 */         return isHovered ? 0.8F : isPressed ? 0.9F : 0.5F;
/*     */       }
/*     */       
/*  39 */       return 1.0F;
/*     */     }
/*     */     
/*     */     public Color getBackground(boolean isHovered, boolean isPressed) {
/*  43 */       return isHovered ? this.bgHover : isPressed ? this.bgPressed : this.bgNormal;
/*     */     }
/*     */   }
/*     */   
/*     */   public static enum AlignState {
/*  48 */     LEFT,  CENTER,  RIGHT;
/*     */     
/*     */     private AlignState() {} }
/*  51 */   public ImageIcon getButtonIcon() { return this.buttonIcon; }
/*  52 */   public void setButtonIcon(ImageIcon buttonIcon) { this.buttonIcon = buttonIcon; }
/*     */   
/*     */ 
/*  55 */   public ButtonStyle getStyle() { return this.style; } private ButtonStyle style = ButtonStyle.NORMAL;
/*  56 */   public void setStyle(ButtonStyle style) { this.style = style; }
/*     */   
/*     */ 
/*  59 */   public AlignState getAlign() { return this.align; } private AlignState align = AlignState.CENTER;
/*  60 */   public void setAlign(AlignState align) { this.align = align; }
/*     */   
/*     */ 
/*     */   private boolean hovered;
/*     */   
/*     */   private boolean pressed;
/*     */   public FlatButton(String text, java.awt.Font font)
/*     */   {
/*  68 */     this(text);
/*     */     
/*  70 */     setFont(font);
/*  71 */     setText(text);
/*     */   }
/*     */   
/*     */   public FlatButton(String text) {
/*  75 */     setText(text);
/*     */     
/*  77 */     setMargin(new java.awt.Insets(0, 0, 0, 0));
/*  78 */     setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 24, 12, 24));
/*     */     
/*  80 */     addMouseListener(new java.awt.event.MouseAdapter()
/*     */     {
/*     */       public void mouseEntered(MouseEvent e) {
/*  83 */         FlatButton.this.hovered = true;
/*  84 */         FlatButton.this.repaint();
/*     */       }
/*     */       
/*     */       public void mouseExited(MouseEvent e)
/*     */       {
/*  89 */         FlatButton.this.hovered = false;
/*  90 */         FlatButton.this.pressed = false;
/*     */         
/*  92 */         FlatButton.this.repaint();
/*     */       }
/*     */       
/*     */       public void mousePressed(MouseEvent e)
/*     */       {
/*  97 */         FlatButton.this.pressed = true;
/*  98 */         FlatButton.this.hovered = false;
/*     */         
/* 100 */         FlatButton.this.repaint();
/*     */       }
/*     */       
/*     */       public void mouseReleased(MouseEvent e)
/*     */       {
/* 105 */         FlatButton.this.pressed = false;
/* 106 */         FlatButton.this.hovered = false;
/*     */         
/* 108 */         FlatButton.this.repaint();
/*     */       }
/*     */       
/* 111 */     });
/* 112 */     setOpaque(false);
/*     */   }
/*     */   
/*     */   public void paint(Graphics g)
/*     */   {
/* 117 */     Graphics2D g2 = (Graphics2D)g;
/*     */     
/* 119 */     Color color = getStyle().getBackground(this.hovered, this.pressed);
/*     */     
/* 121 */     if (color != null) {
/* 122 */       g2.setColor(color);
/* 123 */       g2.fillRect(0, 0, getWidth(), getHeight());
/*     */     }
/*     */     
/* 126 */     g2.setColor(this.style.getText());
/*     */     
/* 128 */     FontMetrics fontMetrics = g.getFontMetrics();
/* 129 */     int textWidth = fontMetrics.stringWidth(getText());
/* 130 */     int textHeight = fontMetrics.getHeight();
/*     */     
/* 132 */     g2.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */     
/* 134 */     float textOpacity = getStyle().getTextOpacity(this.hovered, this.pressed);
/*     */     
/* 136 */     if (textOpacity < 1.0F) {
/* 137 */       java.awt.AlphaComposite composite = java.awt.AlphaComposite.getInstance(3, textOpacity);
/* 138 */       g2.setComposite(composite);
/*     */     }
/*     */     
/* 141 */     if (getButtonIcon() != null) {
/* 142 */       int x = 0;
/*     */       
/* 144 */       if (getAlign() == AlignState.LEFT) {
/* 145 */         x = 12;
/* 146 */       } else if (getAlign() == AlignState.RIGHT) {
/* 147 */         x = getWidth() - textWidth - 36;
/* 148 */       } else if (getAlign() == AlignState.CENTER) {
/* 149 */         x = getWidth() / 2 - (getButtonIcon().getIconWidth() + textWidth + 24) / 2 + 6;
/*     */       }
/*     */       
/* 152 */       drawIcon(g2, x, getHeight() / 2 - getButtonIcon().getIconHeight() / 2);
/*     */       
/* 154 */       if (getText() != null) {
/* 155 */         g2.setFont(getFont());
/* 156 */         g2.drawString(getText(), x + 24, getHeight() / 2 - textHeight / 2 + fontMetrics.getAscent());
/*     */       }
/*     */     } else {
/* 159 */       int x = 0;
/*     */       
/* 161 */       if (getAlign() == AlignState.LEFT) {
/* 162 */         x = 12;
/* 163 */       } else if (getAlign() == AlignState.CENTER) {
/* 164 */         x = getWidth() / 2 - textWidth / 2;
/* 165 */       } else if (getAlign() == AlignState.RIGHT) {
/* 166 */         x = getWidth() - textWidth - 24;
/*     */       }
/*     */       
/* 169 */       if (getText() != null) {
/* 170 */         g2.setFont(getFont());
/* 171 */         g2.drawString(getText(), x, getHeight() / 2 - textHeight / 2 + fontMetrics.getAscent());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void drawIcon(Graphics2D g2, int x, int y) {
/* 177 */     g2.drawImage(getButtonIcon().getImage(), x, y, null);
/*     */   }
/*     */   
/*     */   public void setText(String text)
/*     */   {
/* 182 */     super.setText(text.toUpperCase());
/*     */   }
/*     */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\components\FlatButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */