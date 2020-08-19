/*    */ package com.gildedgames.launcher.ui.animations;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ public class Animation
/*    */ {
/*  9 */   private static final Timer TIMER = new Timer();
/*    */   
/* 11 */   private static final List<TimerTask> tasks = new java.util.ArrayList();
/*    */   
/*    */   public void queue(TimerTask task, int ticks) {
/* 14 */     TIMER.scheduleAtFixedRate(task, 0L, ticks);
/* 15 */     TIMER.purge();
/*    */     
/* 17 */     tasks.add(task);
/*    */   }
/*    */   
/*    */   public static void stopAll() {
/* 21 */     for (TimerTask task : tasks) {
/* 22 */       task.cancel();
/*    */     }
/*    */     
/* 25 */     tasks.clear();
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\ui\animations\Animation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */