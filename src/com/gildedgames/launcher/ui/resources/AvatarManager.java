/*    */ package com.gildedgames.launcher.ui.resources;
/*    */ 
/*    */ import com.google.common.util.concurrent.Futures;
/*    */ import com.google.common.util.concurrent.ListenableFuture;
/*    */ import com.skcraft.launcher.Launcher;
/*    */ import com.skcraft.launcher.auth.Account;
/*    */ import com.skcraft.launcher.auth.StoredSession;
/*    */ import java.awt.Image;
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ public class AvatarManager extends ImageCache
/*    */ {
/* 17 */   private static final Logger log = Logger.getLogger(AvatarManager.class.getName());
/*    */   
/* 19 */   public static final ImageIcon DEFAULT_HEAD = LauncherIcons.loadIcon("com/gildedgames/assets/images/heads/default.png");
/*    */   
/*    */   private static final String SERVICE_URL = "https://crafatar.com/renders/head/%s?scale=2";
/*    */   
/*    */   private static final int CACHE_TIME = 1800000;
/*    */   private Path cachePath;
/*    */   
/*    */   public ListenableFuture<Image> getAvatar(Account account)
/*    */   {
/* 28 */     if (account.getSession() == null) {
/* 29 */       return Futures.immediateFuture(DEFAULT_HEAD.getImage());
/*    */     }
/*    */     
/* 32 */     String uuid = account.getSession().getUuid();
/*    */     
/* 34 */     String remote = String.format("https://crafatar.com/renders/head/%s?scale=2", new Object[] { uuid });
/*    */     
/* 36 */     return obtain(uuid + ".png", remote, false);
/*    */   }
/*    */   
/*    */   public static AvatarManager load(Launcher launcher) {
/* 40 */     AvatarManager manager = (AvatarManager)com.skcraft.launcher.persistence.Persistence.load(new File(launcher.getCacheDir(), "avatar_cache.json"), AvatarManager.class);
/* 41 */     manager.cachePath = Paths.get(launcher.getCacheDir().getAbsolutePath(), new String[] { "avatars/heads" });
/*    */     
/* 43 */     return manager;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 47 */     clearImageCache();
/*    */   }
/*    */   
/*    */   protected long getMaxCacheTime()
/*    */   {
/* 52 */     return 1800000L;
/*    */   }
/*    */   
/*    */   protected Path getImageCacheFolder()
/*    */   {
/* 57 */     return this.cachePath;
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\resources\AvatarManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */