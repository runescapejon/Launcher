/*    */ package com.gildedgames.launcher.util;
/*    */ 
/*    */ public class Pair<L, R> { private final L left;
/*    */   private final R right;
/*    */   
/*  6 */   public L getLeft() { return (L)this.left; }
/*    */   
/*    */   public R getRight() {
/*  9 */     return (R)this.right;
/*    */   }
/*    */   
/*    */   public Pair(L left, R right) {
/* 13 */     this.left = left;
/* 14 */     this.right = right;
/*    */   }
/*    */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\util\Pair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */