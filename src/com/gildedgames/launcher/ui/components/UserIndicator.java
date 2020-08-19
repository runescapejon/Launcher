/*    */ package com.gildedgames.launcher.ui.components;
/*    */ 
/*    */ import com.gildedgames.launcher.ui.resources.AvatarManager;
/*    */ import com.gildedgames.launcher.ui.resources.LauncherFonts;
/*    */ import com.skcraft.launcher.auth.Account;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Font;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import java.awt.RenderingHints;
/*    */ 
/*    */ public class UserIndicator extends javax.swing.JComponent
/*    */ {
/* 16 */   private static final Font FONT_HEADER = LauncherFonts.OPEN_SANS_REGULAR.deriveFont(10.0F);
/* 17 */   private static final Font FONT_USERNAME = LauncherFonts.OPEN_SANS_REGULAR.deriveFont(14.0F);
/*    */   
/*    */   private final AvatarManager avatarManager;
/*    */   
/*    */   private Account account;
/*    */   private Image avatar;
/*    */   
/*    */   public UserIndicator(AvatarManager avatarManager)
/*    */   {
/* 26 */     this.avatarManager = avatarManager;
/*    */   }
/*    */   
/*    */   public Dimension getPreferredSize()
/*    */   {
/* 31 */     return new Dimension(120, 50);
/*    */   }
/*    */   
/*    */   public Dimension getMinimumSize()
/*    */   {
/* 36 */     return getPreferredSize();
/*    */   }
/*    */   
/*    */   public void setAccount(Account account) {
/* 40 */     this.account = account;
/*    */     
/* 42 */     if (account == null) {
/* 43 */       this.avatar = null;
/*    */     } else {
/* 45 */       com.google.common.util.concurrent.ListenableFuture<Image> image = this.avatarManager.getAvatar(account);
/*    */       
/* 47 */       com.google.common.util.concurrent.Futures.addCallback(image, new com.google.common.util.concurrent.FutureCallback()
/*    */       {
/*    */         public void onSuccess(@javax.annotation.Nullable Image result) {
/* 50 */           UserIndicator.this.avatar = result;
/*    */           
/* 52 */           UserIndicator.this.repaint(); } public void onFailure(Throwable t) {} }, com.skcraft.launcher.util.SwingExecutor.INSTANCE);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void paint(Graphics g)
/*    */   {
/* 65 */     Graphics2D g2 = (Graphics2D)g;
/* 66 */     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*    */     
/* 68 */     Image avatar = AvatarManager.DEFAULT_HEAD.getImage();
/*    */     
/* 70 */     if (this.avatar != null) {
/* 71 */       avatar = this.avatar;
/*    */     }
/*    */     
/* 74 */     g.drawImage(avatar, 7, 10, null);
/*    */     
/* 76 */     g2.setFont(FONT_HEADER);
/* 77 */     g2.setColor(Color.WHITE);
/* 78 */     g2.drawString("LOGGED IN AS", 56, 23);
/*    */     
/* 80 */     String username = this.account == null ? "nobody" : this.account.toString();
/* 81 */     g2.setFont(FONT_USERNAME);
/* 82 */     g2.drawString(username, 56, 40);
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\components\UserIndicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */