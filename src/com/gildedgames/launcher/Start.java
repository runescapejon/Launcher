/*    */ package com.gildedgames.launcher;
/*    */ 
/*    */ import com.gildedgames.launcher.ui.LauncherFrame;
/*    */ import com.google.common.base.Supplier;
/*    */ import com.skcraft.launcher.Launcher;
/*    */ import com.skcraft.launcher.swing.SwingHelper;
/*    */ import java.awt.Window;
/*    */ import java.io.InputStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.OpenOption;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.security.KeyStore;
/*    */ import java.security.cert.CertificateFactory;
/*    */ import java.security.cert.X509Certificate;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.TrustManagerFactory;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.UIDefaults;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ public class Start
/*    */ {
/* 26 */   private static final Logger log = Logger.getLogger(Start.class.getName());
/*    */   
/*    */   public static void main(String[] args)
/*    */   {
/* 30 */     Launcher.setupLogger();
/*    */     
/* 32 */     addLetsEncryptSSL();
/*    */     
/* 34 */     SwingUtilities.invokeLater(new Runnable()
/*    */     {
/*    */       public void run() {
/*    */         try {
/* 38 */           Thread.currentThread().setContextClassLoader(Start.class.getClassLoader());
/* 39 */           UIManager.getLookAndFeelDefaults().put("ClassLoader", Start.class.getClassLoader());
/* 40 */           UIManager.getDefaults().put("SplitPane.border", javax.swing.BorderFactory.createEmptyBorder());
/*    */           
/* 42 */           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*    */           
/* 44 */           Launcher launcher = Launcher.createFromArguments(this.val$args);
/* 45 */           launcher.setMainWindowSupplier(new Start.CustomWindowSupplier(launcher, null));
/* 46 */           launcher.showLauncherWindow();
/*    */         } catch (Throwable t) {
/* 48 */           Start.log.log(Level.WARNING, "Load failure", t);
/* 49 */           SwingHelper.showErrorDialog(null, "Uh oh! The updater couldn't be opened because a problem was encountered.", "Launcher error", t);
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   private static void addLetsEncryptSSL()
/*    */   {
/*    */     try {
/* 58 */       KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
/* 59 */       Path ksPath = Paths.get(System.getProperty("java.home"), new String[] { "lib", "security", "cacerts" });
/* 60 */       keyStore.load(Files.newInputStream(ksPath, new OpenOption[0]), "changeit".toCharArray());
/*    */       
/* 62 */       CertificateFactory cf = CertificateFactory.getInstance("X.509");
/*    */       
/* 64 */       InputStream caInput = new java.io.BufferedInputStream(Start.class.getResourceAsStream("cert.der"));
/*    */       try
/*    */       {
/* 67 */         java.security.cert.Certificate crt = cf.generateCertificate(caInput);
/*    */         
/* 69 */         log.info("Added Cert for " + ((X509Certificate)crt).getSubjectDN());
/*    */         
/* 71 */         keyStore.setCertificateEntry("DSTRootCAX3", crt);
/*    */       } finally {
/* 73 */         caInput.close();
/*    */       }
/*    */       
/* 76 */       TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
/* 77 */       tmf.init(keyStore);
/*    */       
/* 79 */       SSLContext ctx = SSLContext.getInstance("TLS");
/* 80 */       ctx.init(null, tmf.getTrustManagers(), null);
/*    */       
/* 82 */       SSLContext.setDefault(ctx);
/*    */     } catch (Exception e) {
/* 84 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   private static class CustomWindowSupplier implements Supplier<Window>
/*    */   {
/*    */     private final Launcher launcher;
/*    */     
/*    */     private CustomWindowSupplier(Launcher launcher) {
/* 93 */       this.launcher = launcher;
/*    */     }
/*    */     
/*    */     public Window get()
/*    */     {
/* 98 */       return new LauncherFrame(this.launcher);
/*    */     }
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\Start.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */