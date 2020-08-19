/*    */ package com.gildedgames.launcher.ui.resources;
/*    */ 
/*    */ import com.gildedgames.launcher.ui.LauncherFrame;
/*    */ import java.awt.Image;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ public class LauncherIcons
/*    */ {
/* 12 */   private static final Logger log = Logger.getLogger(LauncherIcons.class.getName());
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 34 */   public static final ImageIcon GEAR = loadIcon("com/gildedgames/assets/icons/16/gear.png");
/* 35 */   public static final ImageIcon DISCORD; public static final ImageIcon ADD = loadIcon("com/gildedgames/assets/icons/16/add.png");
/* 36 */   public static final ImageIcon REMOVE = loadIcon("com/gildedgames/assets/icons/16/remove.png");
/* 37 */   public static final ImageIcon REFRESH = loadIcon("com/gildedgames/assets/icons/16/refresh.png"); public static final ImageIcon SWITCH_USER; public static final ImageIcon BUG;
/* 38 */   static { DISCORD = loadIcon("com/gildedgames/assets/icons/16/discord.png");
/* 39 */     SWITCH_USER = loadIcon("com/gildedgames/assets/icons/16/switch_user.png");
/* 40 */     BUG = loadIcon("com/gildedgames/assets/icons/16/bug.png");
/* 41 */     WARN = loadIcon("com/gildedgames/assets/icons/16/warn.png");
/*    */     
/* 43 */     KKMC = loadIcon("com/gildedgames/assets/icons/16/kkmc.png"); }
/* 44 */   public static final ImageIcon WARN; public static final ImageIcon SHOP = loadIcon("com/gildedgames/assets/icons/16/shop.png");
/* 45 */   public static final ImageIcon WEB = loadIcon("com/gildedgames/assets/icons/16/web.png");
/*    */   public static final ImageIcon KKMC;
/* 47 */   public static final ImageIcon WINDOW_MINIMIZE = loadIcon("com/gildedgames/assets/icons/16/minimize.png");
/* 48 */   public static final ImageIcon WINDOW_CLOSE = loadIcon("com/gildedgames/assets/icons/16/close.png");
/* 49 */   public static final ImageIcon WINDOW_MAXIMIZE = loadIcon("com/gildedgames/assets/icons/16/maximize.png");
/*    */   
/* 51 */   public static final ImageIcon WINDOW_ICON = loadIcon("com/gildedgames/assets/titlebar/window-icon.png");
/*    */   
/* 53 */   public static final ImageIcon INPUT_VALID = loadIcon("com/gildedgames/assets/icons/16/valid.png");
/* 54 */   public static final ImageIcon INPUT_INVALID = loadIcon("com/gildedgames/assets/icons/16/invalid.png");
/*    */   
/* 56 */   public static final ImageIcon GITHUB = loadIcon("com/gildedgames/assets/icons/48/github.png");
/* 57 */   public static final ImageIcon NET = loadIcon("com/gildedgames/assets/icons/48/net.png");
/*    */   
/*    */ 
/*    */ 
/*    */   public static ImageIcon loadIcon(String path)
/*    */   {
/* 63 */     Image image = load(path);
/*    */     
/* 65 */     if (image == null) {
/* 66 */       return null;
/*    */     }
/*    */     
/* 69 */     return new ImageIcon(image);
/*    */   }
/*    */   
/*    */   public static Image load(String path)
/*    */   {
/*    */     try {
/* 75 */       InputStream stream = LauncherFrame.class.getClassLoader().getResourceAsStream(path);
/*    */       
/* 77 */       if (stream == null) {
/* 78 */         throw new IOException("Couldn't open stream");
/*    */       }
/*    */       
/* 81 */       return javax.imageio.ImageIO.read(stream);
/*    */     } catch (IOException e) {
/* 83 */       e.printStackTrace();
/*    */       
/* 85 */       log.severe("Couldn't load image " + path);
/*    */     }
/*    */     
/* 88 */     return null;
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\resources\LauncherIcons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */