/*     */ package com.gildedgames.launcher.ui.panels;
/*     */ 
/*     */ import com.gildedgames.launcher.ui.resources.NewsFeedManager.NewsFeed;
/*     */ import com.gildedgames.launcher.ui.resources.NewsFeedManager.NewsSection;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ 
/*     */ public class NewsFeedPanel extends JPanel
/*     */ {
/*     */   private JPanel list;
/*     */   
/*     */   public NewsFeedPanel()
/*     */   {
/*  19 */     setLayout(new java.awt.BorderLayout());
/*     */     
/*  21 */     initComponents();
/*     */   }
/*     */   
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  27 */     this.list = new JPanel();
/*  28 */     this.list.setLayout(new javax.swing.BoxLayout(this.list, 1));
/*  29 */     this.list.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  30 */     this.list.setOpaque(false);
/*     */     
/*  32 */     add(this.list, "North");
/*     */     
/*  34 */     setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*     */   }
/*     */   
/*     */   public void repopulate(com.gildedgames.launcher.ui.resources.NewsFeedManager manager, NewsFeedManager.NewsFeed feed) {
/*  38 */     this.list.removeAll();
/*     */     
/*  40 */     for (NewsFeedManager.NewsSection section : feed.getSections()) {
/*  41 */       Color color = new Color(section.getColor());
/*     */       
/*  43 */       JPanel row = new TransparentPanel(new java.awt.BorderLayout());
/*  44 */       row.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
/*  45 */       row.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
/*     */       
/*  47 */       this.list.add(row);
/*     */       
/*  49 */       row.add(createLabel(section.getTitle()), "North");
/*     */       
/*  51 */       JPanel cards = new JPanel();
/*  52 */       cards.setOpaque(false);
/*  53 */       cards.setLayout(new java.awt.FlowLayout(0));
/*  54 */       cards.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
/*     */       
/*  56 */       for (com.gildedgames.launcher.ui.resources.NewsFeedManager.NewsPost post : section.getPosts()) {
/*  57 */         com.google.common.util.concurrent.ListenableFuture<java.awt.Image> image = manager.getImage((String)post.getImages().get("launcher_preview"));
/*     */         
/*  59 */         com.gildedgames.launcher.ui.components.NewsTile tile = new com.gildedgames.launcher.ui.components.NewsTile(post, image);
/*     */         
/*  61 */         cards.add(tile);
/*     */       }
/*     */       
/*  64 */       JScrollPane cardsPane = new JScrollPane(cards);
/*  65 */       cardsPane.setHorizontalScrollBarPolicy(30);
/*  66 */       cardsPane.setVerticalScrollBarPolicy(21);
/*     */       
/*  68 */       JScrollBar horBar = cardsPane.getHorizontalScrollBar();
/*  69 */       horBar.setUI(new com.gildedgames.launcher.ui.styles.FlatScrollbarUI(horBar));
/*  70 */       horBar.setOpaque(false);
/*  71 */       horBar.setUnitIncrement(9);
/*     */       
/*  73 */       cardsPane.setOpaque(false);
/*  74 */       cardsPane.setBorder(BorderFactory.createEmptyBorder());
/*  75 */       cardsPane.getViewport().setOpaque(false);
/*     */       
/*  77 */       row.add(cardsPane, "Center");
/*     */     }
/*     */   }
/*     */   
/*     */   private JLabel createLabel(String title) {
/*  82 */     JLabel label = new JLabel(title.toUpperCase());
/*  83 */     label.setFont(com.gildedgames.launcher.ui.resources.LauncherFonts.OSWALD_NORMAL.deriveFont(2).deriveFont(18.0F));
/*  84 */     label.setForeground(new Color(230, 230, 230, 255));
/*  85 */     label.setBorder(BorderFactory.createEmptyBorder(20, 4, 12, 0));
/*     */     
/*  87 */     return label;
/*     */   }
/*     */   
/*     */   public class TransparentPanel extends JPanel {
/*     */     public TransparentPanel() {
/*  92 */       this(null);
/*     */     }
/*     */     
/*     */     public TransparentPanel(java.awt.LayoutManager manager) {
/*  96 */       super();
/*     */       
/*  98 */       setOpaque(false);
/*     */     }
/*     */     
/*     */     public void paintComponent(Graphics g) {
/* 102 */       g.setColor(getBackground());
/* 103 */       g.fillRect(0, 0, getWidth(), getHeight());
/*     */       
/* 105 */       super.paintComponent(g);
/*     */     }
/*     */   }
/*     */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\panels\NewsFeedPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */