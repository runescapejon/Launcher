/*    */ package com.gildedgames.launcher.ui.resources;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class LauncherFonts
/*    */ {
/* 10 */   private static final Logger log = Logger.getLogger(LauncherFonts.class.getName());
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 19 */   public static final Font OPEN_SANS_BOLD = load("com/gildedgames/assets/fonts/OpenSans-Bold.ttf");
/* 20 */   public static final Font OPEN_SANS_REGULAR = load("com/gildedgames/assets/fonts/OpenSans-Regular.ttf");
/* 21 */   public static final Font OSWALD_NORMAL = load("com/gildedgames/assets/fonts/Oswald-Regular.ttf");
/*    */   
/*    */   private static Font load(String path)
/*    */   {
/*    */     try {
/* 26 */       InputStream stream = LauncherFonts.class.getClassLoader().getResourceAsStream(path);
/*    */       
/* 28 */       return Font.createFont(0, stream);
/*    */     } catch (IOException|java.awt.FontFormatException e) {
/* 30 */       log.severe("Couldn't read font " + path);
/*    */       
/* 32 */       e.printStackTrace();
/*    */     }
/*    */     
/* 35 */     return Font.getFont("SansSerif");
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\resources\LauncherFonts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */