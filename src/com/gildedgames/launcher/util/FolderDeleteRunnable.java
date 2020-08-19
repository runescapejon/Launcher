/*    */ package com.gildedgames.launcher.util;
/*    */ 
/*    */ import com.skcraft.concurrency.ProgressObservable;
/*    */ import java.nio.file.DirectoryStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.LinkOption;
/*    */ import java.nio.file.Path;
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ public class FolderDeleteRunnable implements Callable<Void>, ProgressObservable
/*    */ {
/*    */   private final Path[] folders;
/*    */   private String currentFile;
/*    */   
/*    */   public FolderDeleteRunnable(Path[] folders)
/*    */   {
/* 17 */     this.folders = folders;
/*    */   }
/*    */   
/*    */   public Void call() throws Exception
/*    */   {
/* 22 */     for (Path path : this.folders) {
/* 23 */       deleteFolder(path);
/*    */     }
/*    */     
/* 26 */     return null;
/*    */   }
/*    */   
/*    */   private void deleteFolder(Path directory) throws java.io.IOException {
/* 30 */     DirectoryStream<Path> stream = Files.newDirectoryStream(directory);Throwable localThrowable3 = null;
/* 31 */     try { for (Path entry : stream) {
/* 32 */         if (Files.isDirectory(entry, new LinkOption[0])) {
/* 33 */           deleteFolder(entry);
/*    */         } else {
/* 35 */           this.currentFile = entry.toString();
/*    */           
/* 37 */           Files.delete(entry);
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Throwable localThrowable5)
/*    */     {
/* 30 */       localThrowable3 = localThrowable5;throw localThrowable5;
/*    */ 
/*    */ 
/*    */ 
/*    */     }
/*    */     finally
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/* 40 */       if (stream != null) if (localThrowable3 != null) try { stream.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else stream.close();
/*    */     }
/*    */   }
/*    */   
/*    */   public double getProgress() {
/* 45 */     return -1.0D;
/*    */   }
/*    */   
/*    */   public String getStatus()
/*    */   {
/* 50 */     return "Deleting " + this.currentFile;
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\util\FolderDeleteRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */