/*    */ package com.gildedgames.launcher.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class BrowserUtil
/*    */ {
/*    */   public static void openPage(String url)
/*    */   {
/*    */     try {
/* 10 */       java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
/*    */     } catch (IOException e) {
/* 12 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\util\BrowserUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */