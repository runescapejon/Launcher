/*    */ package com.gildedgames.launcher.ui.views.game;
/*    */ 
/*    */ import com.gildedgames.launcher.ui.components.FlatButton;
/*    */ import java.awt.FlowLayout;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class ConsolePanel extends JPanel
/*    */ {
/*    */   private com.gildedgames.launcher.ui.panels.MessageLog log;
/*    */   private com.gildedgames.launcher.ui.panels.ProgressIndicatorPanel indicator;
/*    */   private JPanel buttons;
/*    */   
/*    */   public com.gildedgames.launcher.ui.panels.MessageLog getLog()
/*    */   {
/* 15 */     return this.log;
/*    */   }
/*    */   
/* 18 */   public com.gildedgames.launcher.ui.panels.ProgressIndicatorPanel getIndicator() { return this.indicator; }
/*    */   
/*    */ 
/*    */ 
/*    */   public ConsolePanel()
/*    */   {
/* 24 */     setLayout(new java.awt.BorderLayout());
/*    */     
/* 26 */     setBackground(com.gildedgames.launcher.ui.resources.LauncherStyles.LAUNCHER_BACKGROUND);
/*    */     
/* 28 */     initComponents();
/*    */   }
/*    */   
/*    */   private void initComponents() {
/* 32 */     this.log = new com.gildedgames.launcher.ui.panels.MessageLog(600, true);
/*    */     
/* 34 */     add(this.log, "Center");
/*    */     
/* 36 */     FlowLayout buttonLayout = new FlowLayout();
/* 37 */     buttonLayout.setVgap(10);
/* 38 */     buttonLayout.setAlignment(2);
/*    */     
/* 40 */     this.buttons = new JPanel(buttonLayout);
/* 41 */     this.buttons.setOpaque(false);
/*    */     
/* 43 */     FlatButton forceClose = new FlatButton("Force close", com.gildedgames.launcher.ui.resources.LauncherFonts.OPEN_SANS_REGULAR.deriveFont(12.0F));
/* 44 */     forceClose.setStyle(com.gildedgames.launcher.ui.components.FlatButton.ButtonStyle.NORMAL);
/*    */     
/* 46 */     this.buttons.add(forceClose);
/*    */     
/* 48 */     add(this.buttons, "South");
/*    */   }
/*    */   
/*    */   public void setProcess(Process process) {}
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\views\game\ConsolePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */