/*    */ package com.gildedgames.launcher.util;
/*    */ 
/*    */ import java.nio.file.DirectoryStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ 
/*    */ public class FolderSizeCalculator implements java.util.concurrent.Callable<Long>
/*    */ {
/*    */   private final Path[] folders;
/*    */   
/*    */   public FolderSizeCalculator(Path... folders)
/*    */   {
/* 13 */     this.folders = folders;
/*    */   }
/*    */   
/*    */   public Long call() throws Exception
/*    */   {
/* 18 */     long size = 0L;
/*    */     
/* 20 */     for (Path path : this.folders) {
/* 21 */       size += sizeFolder(path);
/*    */     }
/*    */     
/* 24 */     return Long.valueOf(size);
/*    */   }
/*    */   
/*    */   private long sizeFolder(Path directory) throws java.io.IOException {
/* 28 */     long size = 0L;
/*    */     
/* 30 */     DirectoryStream<Path> stream = Files.newDirectoryStream(directory);Throwable localThrowable3 = null;
/* 31 */     try { for (Path entry : stream) {
/* 32 */         if (Files.isDirectory(entry, new java.nio.file.LinkOption[0])) {
/* 33 */           size += sizeFolder(entry);
/*    */         } else {
/* 35 */           size += Files.size(entry);
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Throwable localThrowable5)
/*    */     {
/* 30 */       localThrowable3 = localThrowable5;throw localThrowable5;
/*    */ 
/*    */ 
/*    */     }
/*    */     finally
/*    */     {
/*    */ 
/*    */ 
/* 38 */       if (stream != null) if (localThrowable3 != null) try { stream.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else stream.close();
/*    */     }
/* 40 */     return size;
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\util\FolderSizeCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */