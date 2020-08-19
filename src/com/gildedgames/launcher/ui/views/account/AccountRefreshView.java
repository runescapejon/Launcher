/*    */ package com.gildedgames.launcher.ui.views.account;
/*    */ 
/*    */ import com.gildedgames.launcher.ui.LauncherFrame;
/*    */ import com.google.common.util.concurrent.FutureCallback;
/*    */ import com.skcraft.launcher.Launcher;
/*    */ import com.skcraft.launcher.auth.Account;
/*    */ import com.skcraft.launcher.auth.Session;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class AccountRefreshView extends AccountAddView
/*    */ {
/*    */   private final Account account;
/*    */   
/*    */   public AccountRefreshView(LauncherFrame frame, Launcher launcher, Account account)
/*    */   {
/* 16 */     super(frame, launcher);
/*    */     
/* 18 */     this.account = account;
/*    */     
/* 20 */     this.usernameField.setText(account.getMojangId());
/* 21 */     this.usernameField.setEditable(false);
/*    */   }
/*    */   
/*    */   protected FutureCallback<Session> getSessionCallback() {
/* 25 */     new FutureCallback()
/*    */     {
/*    */       public void onSuccess(Session result) {
/* 28 */         Account account = AccountRefreshView.this.account;
/* 29 */         account.setLastUsed(new java.util.Date());
/* 30 */         account.setSession((com.skcraft.launcher.auth.StoredSession)result);
/*    */         
/* 32 */         AccountRefreshView.this.returnResult(account);
/*    */       }
/*    */       
/*    */ 
/*    */ 
/*    */       public void onFailure(Throwable t) {}
/*    */     };
/*    */   }
/*    */   
/*    */ 
/*    */   protected String getTitle()
/*    */   {
/* 44 */     return "Welcome back";
/*    */   }
/*    */   
/*    */   protected String getHeader()
/*    */   {
/* 49 */     return "We ran into an issue while logging you in. Please enter your details again.";
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\views\account\AccountRefreshView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */