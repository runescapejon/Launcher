/*     */ package com.gildedgames.launcher.ui.dialogs;
/*     */ 
/*     */ import com.gildedgames.launcher.ui.components.PlaceholderTextField;
/*     */ import com.skcraft.launcher.Configuration;
/*     */ import com.skcraft.launcher.Launcher;
/*     */ import com.skcraft.launcher.dialog.ConsoleFrame;
/*     */ import com.skcraft.launcher.swing.ActionListeners;
/*     */ import com.skcraft.launcher.swing.FormPanel;
/*     */ import com.skcraft.launcher.swing.LinedBoxPanel;
/*     */ import com.skcraft.launcher.swing.ObjectSwingMapper;
/*     */ import com.skcraft.launcher.swing.SwingHelper;
/*     */ import com.skcraft.launcher.util.SharedLocale;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SpinnerNumberModel;
/*     */ 
/*     */ public class NewConfigurationDialog extends JDialog
/*     */ {
/*     */   private final Configuration config;
/*     */   private final ObjectSwingMapper mapper;
/*  34 */   private final JPanel tabContainer = new JPanel(new java.awt.BorderLayout());
/*  35 */   private final JTabbedPane tabbedPane = new JTabbedPane();
/*  36 */   private final FormPanel javaSettingsPanel = new FormPanel();
/*  37 */   private final JTextField jvmPathText = new PlaceholderTextField(SharedLocale.tr("options.jvmPath.placeholder"));
/*  38 */   private final JTextField jvmArgsText = new PlaceholderTextField(SharedLocale.tr("options.jvmArguments.placeholder"));
/*  39 */   private final JCheckBox jvmAutoMem = new JCheckBox(SharedLocale.tr("options.autoManageMemory"));
/*  40 */   private final JSpinner minMemorySpinner = new JSpinner();
/*  41 */   private final JSpinner maxMemorySpinner = new JSpinner();
/*  42 */   private final JSpinner youngGenSpinner = new JSpinner();
/*  43 */   private final JSpinner[] memSpinners = { this.minMemorySpinner, this.maxMemorySpinner, this.youngGenSpinner };
/*  44 */   private final FormPanel gameSettingsPanel = new FormPanel();
/*  45 */   private final JSpinner widthSpinner = new JSpinner();
/*  46 */   private final JSpinner heightSpinner = new JSpinner();
/*  47 */   private final FormPanel proxySettingsPanel = new FormPanel();
/*  48 */   private final JCheckBox useProxyCheck = new JCheckBox(SharedLocale.tr("options.useProxyCheck"));
/*  49 */   private final JTextField proxyHostText = new JTextField();
/*  50 */   private final JSpinner proxyPortText = new JSpinner();
/*  51 */   private final JTextField proxyUsernameText = new JTextField();
/*  52 */   private final JPasswordField proxyPasswordText = new JPasswordField();
/*  53 */   private final FormPanel generalPanel = new FormPanel();
/*  54 */   private final JTextField gameKeyText = new JTextField();
/*  55 */   private final LinedBoxPanel buttonsPanel = new LinedBoxPanel(true);
/*  56 */   private final JButton okButton = new JButton(SharedLocale.tr("button.ok"));
/*  57 */   private final JButton cancelButton = new JButton(SharedLocale.tr("button.cancel"));
/*  58 */   private final JButton aboutButton = new JButton(SharedLocale.tr("options.about"));
/*  59 */   private final JButton logButton = new JButton(SharedLocale.tr("options.launcherConsole"));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NewConfigurationDialog(Window owner, @lombok.NonNull final Launcher launcher)
/*     */   {
/*  68 */     super(owner, java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
/*  67 */     if (launcher == null) { throw new NullPointerException("launcher");
/*     */     }
/*     */     
/*  70 */     this.config = launcher.getConfig();
/*  71 */     this.mapper = new ObjectSwingMapper(this.config);
/*     */     
/*  73 */     setTitle(SharedLocale.tr("options.title"));
/*  74 */     initComponents();
/*  75 */     setDefaultCloseOperation(2);
/*  76 */     setSize(new Dimension(720, 480));
/*  77 */     setResizable(false);
/*  78 */     setLocationRelativeTo(owner);
/*     */     
/*  80 */     this.mapper.map(this.jvmPathText, "jvmPath");
/*  81 */     this.mapper.map(this.jvmArgsText, "jvmArgs");
/*  82 */     this.mapper.map(this.jvmAutoMem, "memoryManaged");
/*  83 */     this.mapper.map(this.minMemorySpinner, "minMemory");
/*  84 */     this.mapper.map(this.maxMemorySpinner, "maxMemory");
/*  85 */     this.mapper.map(this.youngGenSpinner, "youngGen");
/*  86 */     this.mapper.map(this.widthSpinner, "windowWidth");
/*  87 */     this.mapper.map(this.heightSpinner, "widowHeight");
/*  88 */     this.mapper.map(this.useProxyCheck, "proxyEnabled");
/*  89 */     this.mapper.map(this.proxyHostText, "proxyHost");
/*  90 */     this.mapper.map(this.proxyPortText, "proxyPort");
/*  91 */     this.mapper.map(this.proxyUsernameText, "proxyUsername");
/*  92 */     this.mapper.map(this.proxyPasswordText, "proxyPassword");
/*  93 */     this.mapper.map(this.gameKeyText, "gameKey");
/*     */     
/*  95 */     this.mapper.copyFromObject();
/*     */     
/*  97 */     for (JSpinner spinner : this.memSpinners) {
/*  98 */       spinner.setEnabled(!this.jvmAutoMem.isSelected());
/*     */     }
/*     */     
/* 101 */     this.jvmAutoMem.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent actionEvent) {
/* 104 */         boolean enabled = NewConfigurationDialog.this.jvmAutoMem.isSelected();
/*     */         
/* 106 */         if (enabled) {
/* 107 */           launcher.setOptimizedMemoryConfig();
/*     */         }
/*     */         
/* 110 */         for (JSpinner spinner : NewConfigurationDialog.this.memSpinners) {
/* 111 */           spinner.setEnabled(!enabled);
/*     */         }
/*     */         
/* 114 */         NewConfigurationDialog.this.maxMemorySpinner.setValue(Integer.valueOf(NewConfigurationDialog.this.config.getMaxMemory()));
/* 115 */         NewConfigurationDialog.this.minMemorySpinner.setValue(Integer.valueOf(NewConfigurationDialog.this.config.getMinMemory()));
/* 116 */         NewConfigurationDialog.this.youngGenSpinner.setValue(Integer.valueOf(NewConfigurationDialog.this.config.getYoungGen()));
/*     */       }
/*     */       
/*     */ 
/* 120 */     });
/* 121 */     this.minMemorySpinner.setModel(new SpinnerNumberModel(this.config.getMinMemory(), 128, Launcher.is32BitJava() ? 1024 : 16384, 64));
/* 122 */     this.maxMemorySpinner.setModel(new SpinnerNumberModel(this.config.getMaxMemory(), 512, Launcher.is32BitJava() ? 1024 : 16384, 64));
/*     */     
/* 124 */     this.youngGenSpinner.setModel(new SpinnerNumberModel(this.config.getYoungGen(), 32, Launcher.is32BitJava() ? 1024 : 16384, 64));
/*     */     
/* 126 */     SwingHelper.removeFocusBorder(new Component[] { this.tabbedPane, this.javaSettingsPanel, this.gameSettingsPanel, this.proxySettingsPanel, this.generalPanel });
/*     */   }
/*     */   
/*     */   private void initComponents() {
/* 130 */     this.javaSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.jvmPath")), this.jvmPathText);
/* 131 */     this.javaSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.jvmArguments")), this.jvmArgsText);
/* 132 */     this.javaSettingsPanel.addRow(Box.createVerticalStrut(15));
/* 133 */     if (Launcher.is32BitJava()) {
/* 134 */       this.javaSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.32BitJavaWarning")));
/* 135 */       this.javaSettingsPanel.addRow(Box.createVerticalStrut(6));
/*     */     }
/*     */     
/* 138 */     this.javaSettingsPanel.addRow(this.jvmAutoMem);
/* 139 */     this.javaSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.maxMemory")), this.maxMemorySpinner);
/* 140 */     this.javaSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.minMemory")), this.minMemorySpinner);
/* 141 */     this.javaSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.youngGen")), this.youngGenSpinner);
/*     */     
/* 143 */     SwingHelper.removeOpaqueness(new Component[] { this.javaSettingsPanel });
/*     */     
/* 145 */     this.gameSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.windowWidth")), this.widthSpinner);
/* 146 */     this.gameSettingsPanel.addRow(new JLabel(SharedLocale.tr("options.windowHeight")), this.heightSpinner);
/* 147 */     SwingHelper.removeOpaqueness(new Component[] { this.gameSettingsPanel });
/*     */     
/* 149 */     this.proxySettingsPanel.addRow(this.useProxyCheck);
/* 150 */     this.proxySettingsPanel.addRow(new JLabel(SharedLocale.tr("options.proxyHost")), this.proxyHostText);
/* 151 */     this.proxySettingsPanel.addRow(new JLabel(SharedLocale.tr("options.proxyPort")), this.proxyPortText);
/* 152 */     this.proxySettingsPanel.addRow(new JLabel(SharedLocale.tr("options.proxyUsername")), this.proxyUsernameText);
/* 153 */     this.proxySettingsPanel.addRow(new JLabel(SharedLocale.tr("options.proxyPassword")), this.proxyPasswordText);
/* 154 */     SwingHelper.removeOpaqueness(new Component[] { this.proxySettingsPanel });
/*     */     
/* 156 */     this.generalPanel.addRow(new JLabel(SharedLocale.tr("options.gameKey")), this.gameKeyText);
/* 157 */     SwingHelper.removeOpaqueness(new Component[] { this.generalPanel });
/*     */     
/* 159 */     this.buttonsPanel.addElement(this.logButton);
/* 160 */     this.buttonsPanel.addElement(this.aboutButton);
/* 161 */     this.buttonsPanel.addGlue();
/* 162 */     this.buttonsPanel.addElement(this.cancelButton);
/* 163 */     this.buttonsPanel.addElement(this.okButton);
/*     */     
/* 165 */     this.tabbedPane.addTab(SharedLocale.tr("options.generalTab"), SwingHelper.alignTabbedPane(this.generalPanel));
/* 166 */     this.tabbedPane.addTab(SharedLocale.tr("options.javaTab"), SwingHelper.alignTabbedPane(this.javaSettingsPanel));
/*     */     
/*     */ 
/*     */ 
/* 170 */     this.tabContainer.add(this.tabbedPane, "Center");
/* 171 */     this.tabContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
/* 172 */     add(this.tabContainer, "Center");
/* 173 */     add(this.buttonsPanel, "South");
/*     */     
/* 175 */     this.okButton.setPreferredSize(new Dimension(60, 32));
/*     */     
/* 177 */     this.aboutButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 180 */         com.skcraft.launcher.dialog.AboutDialog.showAboutDialog(NewConfigurationDialog.this);
/*     */       }
/* 182 */     });
/* 183 */     this.aboutButton.setPreferredSize(new Dimension(80, 32));
/*     */     
/* 185 */     this.logButton.addActionListener(new ActionListener()
/*     */     {
/*     */ 
/*     */       public void actionPerformed(ActionEvent e) {}
/*     */ 
/* 190 */     });
/* 191 */     this.logButton.setPreferredSize(new Dimension(80, 32));
/*     */     
/* 193 */     this.cancelButton.addActionListener(ActionListeners.dispose(this));
/* 194 */     this.cancelButton.setPreferredSize(new Dimension(70, 32));
/*     */     
/* 196 */     this.okButton.setPreferredSize(new Dimension(70, 32));
/* 197 */     this.okButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 200 */         NewConfigurationDialog.this.save();
/*     */       }
/*     */       
/* 203 */     });
/* 204 */     SwingHelper.equalWidth(new Component[] { this.okButton, this.cancelButton });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void save()
/*     */   {
/* 211 */     this.mapper.copyFromSwing();
/* 212 */     com.skcraft.launcher.persistence.Persistence.commitAndForget(this.config);
/* 213 */     dispose();
/*     */   }
/*     */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\dialogs\NewConfigurationDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */